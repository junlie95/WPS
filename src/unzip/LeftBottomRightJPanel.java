package unzip;

import xml.MItemListener;
import xml.MyMouseListener;
import xml.Transform;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class LeftBottomRightJPanel extends JPanel implements TreeModelListener, TreeSelectionListener {

    public static String openFileName = "";
    public static DefaultTreeModel treeModel = null;
    public static DefaultMutableTreeNode root, xmlfile;
    public static JTree jtree_3;

    public static JMenuItem allClear, addn, addt, dele, addatt;//右键菜单
    public static JPopupMenu popupMenu;

    public LeftBottomRightJPanel() {
        super();
        setBackground(Color.white);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        init();
        poPup();
    }

    void init() {
        root = new DefaultMutableTreeNode("XML");
        treeModel = new DefaultTreeModel(root);
        xmlfile = new DefaultMutableTreeNode("root");
        treeModel.insertNodeInto(xmlfile, root, root.getChildCount());
        jtree_3 = new JTree(treeModel);
        jtree_3.setEditable(true);
        this.setLayout(new BorderLayout());//布局

        this.add(jtree_3);
    }
    void poPup(){
        popupMenu = new JPopupMenu();//“右拉式 (pull-right)”菜单
        allClear = new JMenuItem("清除所有");
        addn = new JMenuItem("添加节点");
        addt = new JMenuItem("添加目录");
        dele = new JMenuItem("删除节点");
        addatt = new JMenuItem("添加属性");
        allClear.addActionListener(new MItemListener());
        addn.addActionListener(new MItemListener());
        addt.addActionListener(new MItemListener());
        dele.addActionListener(new MItemListener());
        addatt.addActionListener(new MItemListener());

        popupMenu.add(addn);
        popupMenu.add(dele);
        popupMenu.addSeparator();
        popupMenu.add(addt);
        popupMenu.add(addatt);
        popupMenu.addSeparator();
        popupMenu.add(allClear);

        jtree_3.addMouseListener(new MyMouseListener());
        jtree_3.addTreeSelectionListener(this);
        treeModel.addTreeModelListener(this);
    }
    public void valueChanged(TreeSelectionEvent e) {
        try {
            new Transform().toXML();
        } catch (Exception xe) {
        }
    }
    public void treeNodesChanged(TreeModelEvent e) {
        try {
            new Transform().toXML();
        } catch (Exception xe) {
        }
    }
    public void treeNodesInserted(TreeModelEvent e) {
        try {
            new Transform().toXML();
        } catch (Exception xe) {
        }
    }
    public void treeNodesRemoved(TreeModelEvent e) {
        try {
            new Transform().toXML();
        } catch (Exception xe) {
        }
    }
    public void treeStructureChanged(TreeModelEvent e) {
        try {
            new Transform().toXML();
        } catch (Exception xe) {
        }
    }

}
