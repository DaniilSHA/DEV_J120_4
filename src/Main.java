public class Main {
    public static void main(String[] args) {
        SimpleEditor simpleEditor = new SimpleEditor();

        simpleEditor.appendText("Hellow world", false);
        System.out.println(simpleEditor.getText());
        simpleEditor.appendText(" - new word", false);
        System.out.println(simpleEditor.getText());
        simpleEditor.appendText("New text", true);
        System.out.println(simpleEditor.getText());
    }
}
