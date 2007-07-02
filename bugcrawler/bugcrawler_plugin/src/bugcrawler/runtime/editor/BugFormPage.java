package bugcrawler.runtime.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
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
import bugcrawler.utils.WeightedTableLayout;

/**
 * Creates a formpage to display a bug
 * 
 * @author TSO
 */
public class BugFormPage extends FormPage {

	/**
	 * the bug selected out of the bugTreeViewer
	 */
	private Bug bug;

	/**
	 * Toolkit for creating jface widgets
	 */
	private FormToolkit toolkit;

	/**
	 * the form in which this page is created on
	 */
	private ScrolledForm form;

	/**
	 * Data for positioning components
	 */
	private TableWrapData td;

	/**
	 * Instanciates this FormPage
	 * 
	 * @param editor
	 *            where this page is created on
	 * @param id
	 *            of this page
	 * @param title
	 *            of this formpage
	 */
	public BugFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		bug = getBug((UIBug) editor.getEditorInput());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		form = managedForm.getForm();
		toolkit = managedForm.getToolkit();
		form.setText(bug.getSummary());
		form.setBackgroundImage(Activator.getResourceStore().getImage("form_banner.gif"));
		createLayout(form);
		createBugOverviewSection();
		createBugDescriptionSection();
	}

	/**
	 * Creates a layout on this form
	 * 
	 * @param form
	 *            where to create the layout
	 */
	private void createLayout(ScrolledForm form) {
		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		form.getBody().setLayout(layout);
		td = new TableWrapData();
		td.align = TableWrapData.LEFT;
		td.grabHorizontal = true;
	}

	/**
	 * Creates a overview section for the bug choosen in the bugTreeViewer
	 */
	private void createBugOverviewSection() {
		Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION | Section.TITLE_BAR
				| Section.TWISTIE | Section.EXPANDED);
		section.setText("Bug Overview");
		section.setDescription("Hier alles zum Bug bla blubb");
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		GridLayout gl = new GridLayout();
		client.setLayout(gl);
		client.setLayoutData(gd);
		Table table = toolkit.createTable(client, SWT.NONE);
		table.setLinesVisible(false);
		table.setLayout(new WeightedTableLayout(new int[] { -1, 1 }, new int[] { 150, -1 }));
		table.setLayoutData(gd);
		new TableColumn(table, SWT.NONE);
		new TableColumn(table, SWT.NONE);
		new TableItem(table, SWT.NONE).setText(new String[] { "Ticket:", bug.getTicket() });
		new TableItem(table, SWT.NONE).setText(new String[] { "Component:", bug.getComponent() });
		new TableItem(table, SWT.NONE).setText(new String[] { "Version:", bug.getVersion() });
		new TableItem(table, SWT.NONE).setText(new String[] { "Milestone:", bug.getMilestone() });
		new TableItem(table, SWT.NONE).setText(new String[] { "Type:", bug.getType() });
		new TableItem(table, SWT.NONE).setText(new String[] { "Severity:", bug.getSeverity() });
		new TableItem(table, SWT.NONE).setText(new String[] { "Owner:", bug.getOwner() });
		new TableItem(table, SWT.NONE).setText(new String[] { "Created:", bug.getCreated().toString() });
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

	/**
	 * Creates a Section for the description of this bug if the bug has one
	 */
	private void createBugDescriptionSection() {
		if (!bug.getDescription().equals("")) {
			Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION | Section.TITLE_BAR
					| Section.TWISTIE | Section.EXPANDED);
			section.setText("Bug Description");
			section.setDescription("Hier alles zum Bug bla blubb");
			Label client = toolkit.createLabel(section, bug.getDescription(), SWT.WRAP);
			section.setClient(client);
			section.setActiveToggleColor(toolkit.getHyperlinkGroup().getActiveForeground());
			section.setToggleColor(toolkit.getColors().getColor(FormColors.SEPARATOR));
			section.addExpansionListener(new ExpansionAdapter() {
				public void expansionStateChanged(ExpansionEvent e) {
					form.reflow(true);
				}
			});
			td = new TableWrapData();
			td.grabHorizontal = true;
			section.setLayoutData(td);
		}
	}

	/**
	 * Gets the bug-object out of the Wrapper Class
	 * 
	 * @param uibug
	 *            in which the bug is wrapped in
	 * @return the Bug of the uibug
	 */
	private Bug getBug(UIBug uibug) {
		return uibug.getBug();
	}

}
