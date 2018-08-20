package xml;

import java.util.*;
import java.io.*;
import javax.swing.tree.*;

import UiView.Docx;
import org.jdom.*;
import org.jdom.output.*;
import unzip.LeftBottomRightJPanel;

public class Transform {
    Element nElement;
    int index, i, j, n;
    DefaultMutableTreeNode t;
    Element rootElement;
    Document doc;
    BufferedReader reader;
    String line;
    DefaultMutableTreeNode flag;

    public void toXML() {
        t = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(LeftBottomRightJPanel.root, 0);
        TreePath treepath = LeftBottomRightJPanel.jtree_3.getSelectionPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) (treepath.getLastPathComponent());
        if (LeftBottomRightJPanel.treeModel.getChildCount(LeftBottomRightJPanel.root) != 1) {
            if (node == LeftBottomRightJPanel.root) {
                t = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(LeftBottomRightJPanel.root, 0);
                rootElement = new Element(t.getUserObject().toString());
            } else {
                DefaultMutableTreeNode child;
                for (i = 0; i < LeftBottomRightJPanel.treeModel.getChildCount(LeftBottomRightJPanel.root); i++) {
                    child = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(LeftBottomRightJPanel.root, i);
                    isChild(node);
                    if (node == child || flag == child) {
                        t = child;
                        rootElement = new Element(t.getUserObject().toString());
                        break;
                    }
                    if (i >= LeftBottomRightJPanel.treeModel.getChildCount(LeftBottomRightJPanel.root))
                        t = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(LeftBottomRightJPanel.root, 0);
                    rootElement = new Element(t.getUserObject().toString());
                }
            }
        } else {
            t = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(LeftBottomRightJPanel.root, 0);
            rootElement = new Element(t.getUserObject().toString());
        }
        doc = new Document(rootElement);
        try {
            addElement(rootElement, t);
        } catch (Exception e) {
        }
        XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat().setEncoding("gb2312"));

        try {
            Docx.jTextArea.setText("");
            File file = new File("/my.xml");
            FileWriter writer = new FileWriter(file);
            xmlOut.output(doc, writer);
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                Docx.jTextArea.append(line + "\n");
            }
            reader.close();
            writer.close();
            file.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void isChild(DefaultMutableTreeNode node) {
        if (node != LeftBottomRightJPanel.root && (DefaultMutableTreeNode) node.getParent() != LeftBottomRightJPanel.root) {
            DefaultMutableTreeNode father = (DefaultMutableTreeNode) node.getParent();
            for (j = 0; j < LeftBottomRightJPanel.treeModel.getChildCount(LeftBottomRightJPanel.root); j++) {
                DefaultMutableTreeNode n = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(LeftBottomRightJPanel.root, j);
                if (father == n) {
                    flag = father;
                    break;
                }
            }
            if (j >= LeftBottomRightJPanel.treeModel.getChildCount(LeftBottomRightJPanel.root))
                isChild(father);
        }
    }

    public void addElement(Element ele, DefaultMutableTreeNode node) {
        for (index = 0; index < LeftBottomRightJPanel.treeModel.getChildCount(node); index++) {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(node, index);
            if (n.getUserObject().toString().equals("NODE_ATTRIBUTE")) {
                for (int i = 0; i < n.getChildCount(); i++) {
                    DefaultMutableTreeNode attnode = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(n, i);
                    String s = attnode.toString();
                    String string[] = new String[2];
                    string = getAttrAndValue(s);
                    ele.setAttribute(string[0], string[1]);
                }
            } else if (n.getUserObject().toString().equals("NODE_CONTENT")) {
                DefaultMutableTreeNode contentNode = (DefaultMutableTreeNode) LeftBottomRightJPanel.treeModel.getChild(n, 0);
                String s = contentNode.toString();
                ele.addContent(s);
            } else {
                nElement = new Element(n.getUserObject().toString());
                ele.addContent(nElement);

                if (LeftBottomRightJPanel.treeModel.getChildCount(n) > 0)
                    addElement(nElement, n);
            }
            index = LeftBottomRightJPanel.treeModel.getIndexOfChild(node, n);
        }
    }

    public String[] getAttrAndValue(String s) {
        StringTokenizer anynize = new StringTokenizer(s, "=");
        String[] string = new String[2];
        string[0] = anynize.nextToken();
        string[1] = anynize.nextToken();
        return string;
    }
}

