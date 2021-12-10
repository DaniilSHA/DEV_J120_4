import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SimpleEditorListener extends WindowAdapter implements ActionListener, AutoCloseable{

    private SimpleEditor editor;
    private File file;
    private FileReader reader;
    private FileWriter writer;

    public SimpleEditorListener(SimpleEditor editor) {
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "open": {


                break;
            }
            case "save": break;
            case "cancel": break;
            case "exit": editor.dispose(); System.exit(0); break;
        }
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
