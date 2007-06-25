package bugcrawler.runtime.editor;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import bugcrawler.runtime.Activator;

public class BugFormPage extends FormPage {

	UIBug uibug;

	public BugFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		uibug = (UIBug) editor.getEditorInput();
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText("test");
		form.setBackgroundImage(Activator.getImagestore().get("form_banner.gif"));

		GridLayout layout = new GridLayout();
		form.getBody().setLayout(layout);

		toolkit.createText(form.getBody(), "Test");
	}

}
