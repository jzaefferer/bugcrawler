package bugcrawler.runtime.editor;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import bugcrawler.runtime.Activator;
import bugcrawler.runtime.data.Bug;

public class BugFormPage extends FormPage {

	Bug bug;

	public BugFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		bug = getBug((UIBug) editor.getEditorInput());
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText(bug.getSummary());
		form.setBackgroundImage(Activator.getImagestore().get("form_banner.gif"));

		GridLayout layout = new GridLayout();
		form.getBody().setLayout(layout);

		toolkit.createText(form.getBody(), bug.getOwner());
	}
	
	private Bug getBug(UIBug uibug){
		return uibug.getBug();
	}
	

}
