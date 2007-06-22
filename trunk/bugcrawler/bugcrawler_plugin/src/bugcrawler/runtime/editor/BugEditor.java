package bugcrawler.runtime.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.forms.editor.FormEditor;

public class BugEditor extends FormEditor {
	
	@Override
	protected void addPages(){
		try{
			addPage(new BugFormPage(this,"1","Test"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {}

	@Override
	public void doSaveAs() {}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	@Override
	public String getTitleToolTip() {
		return "FUUFUFUF";
	}

}