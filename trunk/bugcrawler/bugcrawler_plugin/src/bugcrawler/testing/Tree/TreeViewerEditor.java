package bugcrawler.testing.Tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TreeViewerEditor extends TreeEditor{
    
    private final TreeEditor editor = this;
    
    private Tree tree;
    
    private int EDITABLECOLUMN; 
    
    public TreeViewerEditor(Tree tree){
	super(tree);
	this.tree = tree;
	editor.horizontalAlignment = SWT.LEFT;
	editor.grabHorizontal = true;
	editor.minimumWidth = 50;
	EDITABLECOLUMN = 1; 
	addSelectionListener();
    }
    
    private void addSelectionListener(){
	tree.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			Control oldEditor = editor.getEditor();
			if (oldEditor != null) oldEditor.dispose();
			TreeItem item = (TreeItem)e.item;
			if (item == null) return;
			Text newEditor = new Text(tree, SWT.NONE);
			newEditor.setText(item.getText(EDITABLECOLUMN));
			newEditor.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					Text text = (Text)editor.getEditor();
					editor.getItem().setText(EDITABLECOLUMN, text.getText());
				}
			});
			newEditor.selectAll();
			newEditor.setFocus();
			editor.setEditor(newEditor, item, EDITABLECOLUMN);
		}
	});
    }
}
