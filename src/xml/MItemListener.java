package xml;

import unzip.LeftBottomRightJPanel;

import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.*;

public class MItemListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        try {
            DefaultMutableTreeNode thenode;
            if (e.getSource() == LeftBottomRightJPanel.addn) {
                TreePath parentPath = LeftBottomRightJPanel.jtree_3.getSelectionPath();
                thenode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
                if (parentPath != null && thenode != LeftBottomRightJPanel.root) {
                    DefaultMutableTreeNode addnode = new DefaultMutableTreeNode("name");
                    addnode.setAllowsChildren(true);

                    LeftBottomRightJPanel.treeModel.insertNodeInto(addnode, thenode, thenode.getChildCount());
                    LeftBottomRightJPanel.jtree_3.scrollPathToVisible(new TreePath(addnode.getPath()));
                }
            } else if (e.getSource() == LeftBottomRightJPanel.dele) {
                TreePath treepath = LeftBottomRightJPanel.jtree_3.getSelectionPath();
                if (treepath != null) {
                    DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) treepath.getLastPathComponent();
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectionNode.getParent();
                    if (parent != null && selectionNode != null && selectionNode != LeftBottomRightJPanel.xmlfile) {
                        LeftBottomRightJPanel.treeModel.removeNodeFromParent(selectionNode);
                    }
                }
            } else if (e.getSource() == LeftBottomRightJPanel.allClear) {
                int i;
                DefaultMutableTreeNode child;
                TreePath treepath = LeftBottomRightJPanel.jtree_3.getSelectionPath();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) (treepath.getLastPathComponent());
                Transform tran = new Transform();
                tran.isChild(node);
                if (node == LeftBottomRightJPanel.root) {
                    LeftBottomRightJPanel.root.removeAllChildren();
                    LeftBottomRightJPanel.xmlfile.removeAllChildren();
                    LeftBottomRightJPanel.treeModel.insertNodeInto(LeftBottomRightJPanel.xmlfile, LeftBottomRightJPanel.root, LeftBottomRightJPanel.root.getChildCount());
                } else if (node != LeftBottomRightJPanel.xmlfile && tran.flag != LeftBottomRightJPanel.xmlfile) {
                    for (i = 1; i < LeftBottomRightJPanel.treeModel.getChildCount(LeftBottomRightJPanel.root); i++) {
                        child = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(LeftBottomRightJPanel.root, i);

                        if (node == child || tran.flag == child) {
                            child.removeAllChildren();
                            LeftBottomRightJPanel.treeModel.removeNodeFromParent(child);
                            LeftBottomRightJPanel.treeModel.insertNodeInto(child, LeftBottomRightJPanel.root, LeftBottomRightJPanel.root.getChildCount());
                        }
                    }
                } else {
                    LeftBottomRightJPanel.xmlfile.removeAllChildren();

                }
                LeftBottomRightJPanel.treeModel.reload();
            } else if (e.getSource() == LeftBottomRightJPanel.addt) {
                String content = JOptionPane.showInputDialog("content:");
                TreePath treepath = LeftBottomRightJPanel.jtree_3.getSelectionPath();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) (treepath.getLastPathComponent());
                String info = node.getUserObject().toString();

                if (node.isLeaf() || onlyAttributeChild(node)) {
                    DefaultMutableTreeNode father = new DefaultMutableTreeNode("NODE_CONTENT");
                    LeftBottomRightJPanel.treeModel.insertNodeInto(father, node, node.getChildCount());
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(content);
                    child.setAllowsChildren(false);
                    LeftBottomRightJPanel.treeModel.insertNodeInto(child, father, father.getChildCount());
                    LeftBottomRightJPanel.jtree_3.scrollPathToVisible(new TreePath(child.getPath()));
                }
            } else if (e.getSource() == LeftBottomRightJPanel.addatt) {
                String attribute = JOptionPane.showInputDialog("属性:(名和值用等号(=)连接)");
                int i;
                DefaultMutableTreeNode father = null;
                TreePath treepath = LeftBottomRightJPanel.jtree_3.getSelectionPath();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) (treepath.getLastPathComponent());
                String info = node.getUserObject().toString();
                DefaultMutableTreeNode cchild = new DefaultMutableTreeNode(attribute);
                cchild.setAllowsChildren(false);
                for (i = 0; i < node.getChildCount(); i++) {
                    father = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(node, i);
                    if (father.getUserObject().toString().equals("NODE_ATTRIBUTE"))
                        break;
                }
                if (i >= node.getChildCount()) {

                    DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("NODE_ATTRIBUTE");
                    LeftBottomRightJPanel.treeModel.insertNodeInto(child1, node, node.getChildCount());
                    LeftBottomRightJPanel.treeModel.insertNodeInto(cchild, child1, child1.getChildCount());
                    LeftBottomRightJPanel.jtree_3.scrollPathToVisible(new TreePath(cchild.getPath()));

                } else {
                    LeftBottomRightJPanel.treeModel.insertNodeInto(cchild, father, father.getChildCount());
                    LeftBottomRightJPanel.jtree_3.scrollPathToVisible(new TreePath(cchild.getPath()));
                }
            }
        } catch (Exception ex) {
        }
    }

    public boolean onlyAttributeChild(DefaultMutableTreeNode node) {
        if (node.getChildCount() == 1) {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.
                    getChild(node, 0);
            if (n.getUserObject().toString().equals("NODE_ATTRIBUTE"))
                return true;
        }
        return false;
    }
}
