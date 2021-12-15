import javax.swing.*;
import java.awt.*;

public class SimpleEditor extends JFrame {

    private Container cp;
    private JLabel fileName;
    private JTextArea text;
    private JMenuBar bar;
    private JMenu[] menu;
    private JMenuItem[] commandMenu;
    private JButton[] commandButton;
    private SimpleEditorListener listener;

    protected SimpleEditor(){
        setTitle("Simple text editor");
        init();
        createMenu();
        setVisible(true);
    }

    private void init() {
        setSize(500,500);
        setLocation(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        listener = new SimpleEditorListener(this);
        cp = getContentPane();

        createButtons();
        fileName = new JLabel();
        fileName.setText("MODE: new file -------------- FILE NAME: undefined");
        fileName.setHorizontalAlignment(SwingConstants.CENTER);
        text = new JTextArea();

        cp.setLayout(new BorderLayout());
        JPanel generalButtonsPanel = new JPanel();
        generalButtonsPanel.setLayout(new FlowLayout());
        JPanel fileButtonsPanel = new JPanel();
        fileButtonsPanel.setLayout(new BorderLayout());
        JPanel editButtonsPanel = new JPanel();
        editButtonsPanel.setLayout(new BorderLayout());

        cp.add(fileName, BorderLayout.NORTH);
        cp.add(text, BorderLayout.CENTER);
        cp.add(generalButtonsPanel, BorderLayout.SOUTH);

        generalButtonsPanel.add(fileButtonsPanel);
        generalButtonsPanel.add(editButtonsPanel);

        fileButtonsPanel.add(commandButton[0], BorderLayout.WEST);
        fileButtonsPanel.add(commandButton[3], BorderLayout.EAST);
        editButtonsPanel.add(commandButton[1], BorderLayout.WEST);
        editButtonsPanel.add(commandButton[2], BorderLayout.EAST);
    }

    private void createMenu() {
        bar = new JMenuBar();
        menu = new JMenu[] {new JMenu("File"), new JMenu("Edit")};
        commandMenu = new JMenuItem[] {new JMenuItem("Open"), new JMenuItem("Save"),
                new JMenuItem("Cancel"), new JMenuItem("Exit")};

        commandMenu[0].addActionListener(listener);
        commandMenu[0].setActionCommand("open");
        commandMenu[1].addActionListener(listener);
        commandMenu[1].setActionCommand("save");
        commandMenu[2].addActionListener(listener);
        commandMenu[2].setActionCommand("cancel");
        commandMenu[3].addActionListener(listener);
        commandMenu[3].setActionCommand("exit");

        menu[0].add(commandMenu[0]);
        menu[0].add(commandMenu[3]);
        menu[1].add(commandMenu[1]);
        menu[1].add(commandMenu[2]);

        bar.add(menu[0]);
        bar.add(menu[1]);

        setJMenuBar(bar);
    }

    private void createButtons() {
        commandButton = new JButton[] {new JButton("Open"), new JButton("Save"),
                new JButton("Cancel"), new JButton("Exit")};

        commandButton[0].addActionListener(listener);
        commandButton[0].setActionCommand("open");
        commandButton[1].addActionListener(listener);
        commandButton[1].setActionCommand("save");
        commandButton[2].addActionListener(listener);
        commandButton[2].setActionCommand("cancel");
        commandButton[3].addActionListener(listener);
        commandButton[3].setActionCommand("exit");

    }

    void appendText(String str, boolean append) {
        if (append) text.setText("");
        text.append(str);
    }

    String getText() {
        return text.getText();
    }

    public void setJLabelText (String str){
        fileName.setText(str);
    }

}
