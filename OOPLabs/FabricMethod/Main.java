package FabricMethod;

interface INotifier {
    void send(String message);    
}

abstract class AlertService {
    // фабричный метод
    protected abstract INotifier createNotifier();

    public void alert(String msg) {
        INotifier notifier = createNotifier();
        notifier.send(msg);
    }
}

final class SmsAlert extends AlertService {
    @Override
    protected INotifier createNotifier() {
        return m -> System.out.println("Sms: " + m);
    }
}

final class EmailAlert extends AlertService {
    @Override
    protected INotifier createNotifier() {
        return m -> System.out.println("Email: " + m);
    }
}

public class Main {
    public static void main (String[] args) {
        AlertService svc = (args.length > 1 && args[1] == "sms") ?
        new SmsAlert() : new EmailAlert();
        svc.alert("Hello");
    }
}