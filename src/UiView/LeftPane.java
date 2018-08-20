package UiView;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

public class LeftPane extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    JTabbedPane swichPane = new JTabbedPane();
    JScrollPane treeScrollPane = new JScrollPane();

    public LeftPane() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        this.add(swichPane, java.awt.BorderLayout.CENTER);
        Docx.fileTree.setAutoscrolls(true);
        treeScrollPane.getViewport().add(Docx.fileTree, null);//将目录树添加到左边的面板中
        swichPane.addTab("目录", treeScrollPane);
        swichPane.add("特殊字符", new Ascii());
    }
}

