import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.*;

public class SimpleEditorListener extends WindowAdapter implements ActionListener, AutoCloseable {

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
                openOperation();
                break;
            }
            case "save": {
                saveOperation();
                break;
            }
            case "cancel": {
                cancelOperation();
                break;
            }
            case "exit":
                exitOperation();
                break;
        }
    }



    public void openOperation() {
        if (file == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Выберите файл для редактирования");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = fileChooser.showOpenDialog(editor);
            if (res == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                try (FileReader reader = new FileReader(file)) {
                    char[] buffer = new char[1024];
                    while (reader.ready()) {
                        reader.read(buffer);
                        editor.appendText(new String(buffer), false);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(editor, "Не верно указан путь файла");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(editor, "Ошибка чтения файла");
                }
            }
            editor.setJLabelText("MODE: redaction -------------- FILE NAME: " + file.getName());
        } else {
            Object[] choose = {"save", "cancel", "nothing"};
            int userChoose = JOptionPane.showOptionDialog(editor, "Файл уже открыт. Сохранить изменения или нет?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choose, null);
            if (userChoose == 0) saveOperation();
            if (userChoose == 1) cancelOperation();
        }
    }

    public void saveOperation() {
        if (file == null) {
            createNewFile();
            editor.setJLabelText("MODE: redaction -------------- FILE NAME: " + file.getName());
        }
        try (FileWriter writer = new FileWriter(file, false)) {
            String resultText = editor.getText();
            writer.write(resultText);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(editor, "Ошибка записи файла");
        }
    }

    public void cancelOperation() {
        file = null;
        editor.setJLabelText("MODE: new file -------------- FILE NAME: undefined");
        editor.appendText("", true);
    }

    public void exitOperation() {
        if (file != null) {
            Object[] choose = {"save", "cancel"};
            int userChoose = JOptionPane.showOptionDialog(editor, "Файл сейчас открыт. Сохранить изменения или нет?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choose, null);
            if (userChoose == 0) saveOperation();
            if (userChoose == 1) cancelOperation();
        }
        editor.dispose();
        System.exit(0);
    }

    public void createNewFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл для сохранения");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = fileChooser.showSaveDialog(editor);
        if (res == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
