package bugcrawler.runtime.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.data.Bug;

public class BugFormPage extends FormPage {

	private Bug bug;
	
	private FormToolkit toolkit;
	
	private ScrolledForm form;
	
	private TableWrapData td;

	public BugFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		bug = getBug((UIBug) editor.getEditorInput());
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		form = managedForm.getForm();
		toolkit = managedForm.getToolkit();
		form.setText(bug.getSummary());
		form.setBackgroundImage(Activator.getImagestore().get("form_banner.gif"));
		createLayout(form);
		td = new TableWrapData();
		td.align = TableWrapData.LEFT;
		toolkit.createText(form.getBody(), "Creator");
		toolkit.createText(form.getBody(), bug.getOwner());
		createSection();

	}

	private void createLayout(ScrolledForm form){
		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		form.getBody().setLayout(layout);		
	}
	
	private void createSection(){
		Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION | Section.TITLE_BAR
				| Section.TWISTIE | Section.EXPANDED);
		section.setText("Expandable Composite title");
		Label client = toolkit.createLabel(section, bug.getOwner(), SWT.WRAP);
		section.setClient(client);
		section.setActiveToggleColor(toolkit.getHyperlinkGroup().getActiveForeground());
		section.setToggleColor(toolkit.getColors().getColor(FormColors.SEPARATOR));
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setLayoutData(td);
	}
	
	private Bug getBug(UIBug uibug) {
		return uibug.getBug();
	}

}
