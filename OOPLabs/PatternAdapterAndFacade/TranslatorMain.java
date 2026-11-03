package PatternAdapterAndFacade;
interface EnglishSpeaker {
    void speakEnglish(String text);
}

class Englishman implements EnglishSpeaker {
    @Override
    public void speakEnglish(String text) {
        System.out.println("Englishman says: " + text);
    }
}

interface Translator {
    void translate(String text);
}

class EnglishToRussianTranslatorAdapter implements Translator {
    private EnglishSpeaker englishSpeaker;

    public EnglishToRussianTranslatorAdapter(EnglishSpeaker englishSpeaker) {
        this.englishSpeaker = englishSpeaker;
    }

    @Override
    public void translate(String text) {
        englishSpeaker.speakEnglish(text);

        System.out.println("Перевод на русский: " + simpleTranslate(text));
    }

    private String simpleTranslate(String text) {
        switch (text.toLowerCase()) {
            case "hello":
                return "Привет";
            case "how are you?":
                return "Как дела?";
            case "goodbye":
                return "До свидания";
            default:
                return "[перевод недоступен]";
        }
    }
}

public class TranslatorMain {
    public static void main(String[] args) {
        EnglishSpeaker englishman = new Englishman();

        Translator translator = new EnglishToRussianTranslatorAdapter(englishman);

        translator.translate("Hello");
        translator.translate("How are you?");
        translator.translate("Goodbye");
    }
}