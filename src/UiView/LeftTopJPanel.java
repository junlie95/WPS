package UiView;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

/*
      树目录是直接添加到LeftPane面板容器当中的，当把面板容器添加到窗体Docx上的时候，目录就会显示在LeftPane中。
  因为LeftTopJPanel继承(拓展)了LeftPane，所以LeftPane中所定义的成员它都可以使用，包括构造函数。树目录本来是
  显示在LeftPane当中的，但是当添加了jSplitPane1_2分割面板之后，就必须重新绘制，之前的就不再显示。

*/

public class LeftTopJPanel extends LeftPane {

    /*
    BorderLayout borderLayout1 = new BorderLayout();
    JTabbedPane swichPane = new JTabbedPane();
    JScrollPane treeScrollPane = new JScrollPane();

    public LeftTopJPanel() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        this.add(swichPane, java.awt.BorderLayout.CENTER);
        //fileTree=new DragTree();
        Docx.fileTree.setAutoscrolls(true);
        treeScrollPane.getViewport().add(Docx.fileTree, null);//将目录树添加到左边的面板中
        swichPane.addTab("目录", treeScrollPane);
        swichPane.add("特殊字符", new Ascii());
    }
    */

}


