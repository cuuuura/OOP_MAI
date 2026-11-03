package PatternAdapterAndFacade;
class AudioSystem {
    public void on() {
        System.out.println("Аудиосистема запущена");
    }

    public void off() {
        System.out.println("Aудиосистема выклоченя");
    }
}

class Screen {
    public void on() {
        System.out.println("Изображение включено");
    }
    
    public void off() {
        System.out.println("Изобрaжение выклочено");
    }
}

class HomeTheatherFacade {
    private AudioSystem audioSystem;
    private Screen screen;

    public HomeTheatherFacade(AudioSystem audioSystem, Screen screen) {
        this.audioSystem = audioSystem;
        this.screen = screen;
    }

    public void start() {
        System.out.println("Включаем домашний кинотеатр!");
        audioSystem.on();
        screen.on();
    }

    public void end() {
        System.out.println("Выключаем домашний кинотеатр");
        audioSystem.off();
        screen.off();
    }
}

public class AudioSystemMain {
    public static void main(String[] args) {
        AudioSystem audioSystem = new AudioSystem();
        Screen screen = new Screen();
        HomeTheatherFacade homeTheater = new HomeTheatherFacade
            (audioSystem, screen);
        homeTheater.start();
        homeTheater.end();
    }
}