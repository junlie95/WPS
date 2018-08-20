package xml;

import javax.swing.filechooser.FileFilter;
import java.io.*;

public class XmlFileFilter extends FileFilter {
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        else
            return f.getName().endsWith(".xml");
    }

    public String getDescription() {
        return "*.xml";
    }
}
