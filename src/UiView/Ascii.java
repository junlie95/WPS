package UiView;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Ascii extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea jTextArea1 = new JTextArea();
    String asii = "";

    public Ascii() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        jTextArea1.addMouseListener(new Ascii_jTextArea1_mouseAdapter(this));
        this.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTextArea1);
        for (int i = 33; i < 256; i++) {
            asii = asii + i + "  (" + (char) i + ")\n";
        }
        jTextArea1.setText(asii);
        jTextArea1.setCaretPosition(0);
    }

    public void jTextArea1_mouseReleased(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String line = jTextArea1.getSelectedText();
            //String c=(char)(line+33);
            int pos = Docx.jTextArea.getCaretPosition();
            Docx.jTextArea.insert(line, pos);
        }
    }
}

class Ascii_jTextArea1_mouseAdapter extends MouseAdapter {
    private Ascii adaptee;

    Ascii_jTextArea1_mouseAdapter(Ascii adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseReleased(MouseEvent e) {
        adaptee.jTextArea1_mouseReleased(e);
    }
}
