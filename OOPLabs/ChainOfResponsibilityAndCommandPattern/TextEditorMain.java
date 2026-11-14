package ChainOfResponsibilityAndCommandPattern;

// Паттерн команда задача

interface Command {
    void execute();
}

class TextEditor {
    private String text = "Привет, мир!";
    private String clipboard = "";
    private String selectedText = "";

    public void setSelectedText(String text) {
        this.selectedText = text;
    }

    public void copy() {
        if (selectedText.isEmpty()) {
            System.out.println("Нечего копировать (текст не выделен).");
            return;
        }
        clipboard = selectedText;
        System.out.println("Скопировано в буфер: '" + clipboard + "'");
    }

    public void cut() {
        if (selectedText.isEmpty()) {
            System.out.println("Нечего вырезать");
            return;
        }
        clipboard = selectedText;
        text = text.replace(selectedText, ""); 
        System.out.println("Вырезано в буфер: '" + clipboard + "'");
        System.out.println("Текущий текст: '" + text + "'");
    }

    public void paste() {
        if (clipboard.isEmpty()) {
            System.out.println("Буфер обмена пуст.");
            return;
        }
        text = text + clipboard;
        System.out.println("Вставлено. Текущий текст: '" + text + "'");
    }
}

class CopyCommand implements Command {
    private TextEditor editor;

    public CopyCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.copy();
    }
}

class CutCommand implements Command {
    private TextEditor editor;

    public CutCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.cut();
    }
}

class PasteCommand implements Command {
    private TextEditor editor;

    public PasteCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.paste();
    }
}

class Keyboard {
    private Command ctrlC;
    private Command ctrlX;
    private Command ctrlV;

    public void setCtrlC(Command c) { this.ctrlC = c; }
    public void setCtrlX(Command c) { this.ctrlX = c; }
    public void setCtrlV(Command c) { this.ctrlV = c; }

    public void pressCtrlC() { ctrlC.execute(); }
    public void pressCtrlX() { ctrlX.execute(); }
    public void pressCtrlV() { ctrlV.execute(); }
}

public class TextEditorMain {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.setSelectedText("мир");

        Command copy = new CopyCommand(editor);
        Command cut = new CutCommand(editor);
        Command paste = new PasteCommand(editor);

        Keyboard keyboard = new Keyboard();
        keyboard.setCtrlC(copy);
        keyboard.setCtrlX(cut);
        keyboard.setCtrlV(paste);

        System.out.println("Нажимаем Ctrl+C");
        keyboard.pressCtrlC();

        System.out.println("\nНажимаем Ctrl+V");
        keyboard.pressCtrlV();

        System.out.println("\nНажимаем Ctrl+X");
        keyboard.pressCtrlX();

        System.out.println("\nНажимаем Ctrl+V");
        keyboard.pressCtrlV();
    }
}