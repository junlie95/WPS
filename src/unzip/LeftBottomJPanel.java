package unzip;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class LeftBottomJPanel extends JPanel {
    private static final long serialVersionUID = -1877527354792619586L;

    public static JTree jTree;
    public static DefaultTreeModel newModel;//一棵树
    public static DefaultMutableTreeNode Node;//节点
    static DefaultMutableTreeNode temp;
    public static String path = "F:\\子川\\";//需要遍历的目录

    public LeftBottomJPanel() {
        init();
    }

    public void init() {

        Node = traverseFolder(path);
        newModel = new DefaultTreeModel(Node);
        jTree = new JTree(newModel);
        //铺满面板
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(new BorderLayout(0, 0));
        this.add(jTree);//直接使用LeftBottomJPanel的滚动条
        setBackground(Color.white);//设置颜色
    }

    public static DefaultMutableTreeNode traverseFolder(String path) {
        DefaultMutableTreeNode fujiedian = new DefaultMutableTreeNode(new File(path).getName());
        File file = new File(path);

        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                if (file.isDirectory()) {//如果是空文件夹
                    DefaultMutableTreeNode dn = new DefaultMutableTreeNode(file.getName(), false);
                    return dn;
                }
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //是目录的话，生成节点，并添加里面的节点
                        fujiedian.add(traverseFolder(file2.getAbsolutePath()));//递归
                    } else {
                        //是文件的话直接生成节点，并把该节点加到对应父节点上
                        temp = new DefaultMutableTreeNode(file2.getName());
                        fujiedian.add(temp);
                    }
                }
            }
        } else {//文件不存在
            return null;
        }
        return fujiedian;
    }

}
