package ChainOfResponsibilityAndCommandPattern;

// Цепочка ответственности задача

class UserRequest {
    private String login;
    private String password;
    private String smsCode;
    private String secretAnswer;

    public UserRequest(String login, String password, String smsCode, String secretAnswer) {
        this.login = login;
        this.password = password;
        this.smsCode = smsCode;
        this.secretAnswer = secretAnswer;
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getSmsCode() { return smsCode; }
    public String getSecretAnswer() { return secretAnswer; }
}

interface AuthChain {
    void setNextChain(AuthChain nextChain);
    void authenticate(UserRequest request);
}

abstract class AbstractAuthHandler implements AuthChain {
    protected AuthChain next;

    @Override
    public void setNextChain(AuthChain nextChain) {
        this.next = nextChain;
    }
}

class PasswordAuthHandler extends AbstractAuthHandler {
    @Override
    public void authenticate(UserRequest request) {
        if ("admin".equals(request.getLogin()) && "12345".equals(request.getPassword())) {
            System.out.println("Шаг 1 пройден");
            if (next != null) next.authenticate(request);
        } else {
            System.out.println("Ошибка: Неверный логин или пароль. Доступ заблокирован.");
        }
    }
}

class SmsAuthHandler extends AbstractAuthHandler {
    @Override
    public void authenticate(UserRequest request) {
        if ("5555".equals(request.getSmsCode())) {
            System.out.println("Шаг 2 пройден");
            if (next != null) next.authenticate(request);
        } else {
            System.out.println("Ошибка: Неверный SMS-код. Доступ заблокирован.");
        }
    }
}

class SecretQuestionAuthHandler extends AbstractAuthHandler {
    @Override
    public void authenticate(UserRequest request) {
        String correctAnswer = "Барсик"; 
        
        if (correctAnswer.equalsIgnoreCase(request.getSecretAnswer())) {
            System.out.println("Шаг 3 пройден");
            System.out.println("Доступ разрешен");
        } else {
            System.out.println("Ошибка: Неверный ответ на секретный вопрос. Доступ заблокирован.");
        }
    }
}

public class Authentication {
    public static void main(String[] args) {
        AuthChain passwordHandler = new PasswordAuthHandler();
        AuthChain smsHandler = new SmsAuthHandler();
        AuthChain secretHandler = new SecretQuestionAuthHandler();

        passwordHandler.setNextChain(smsHandler);
        smsHandler.setNextChain(secretHandler);

        UserRequest goodUser = new UserRequest("admin", "12345", "5555", "Барсик");
        passwordHandler.authenticate(goodUser);

        System.out.println("\n");
        UserRequest badSecretUser = new UserRequest("admin", "12345", "5555", "Шарик");
        passwordHandler.authenticate(badSecretUser);
        
        System.out.println("\n");
        UserRequest badPasswordUser = new UserRequest("admin", "qwerty", "5555", "Барсик");
        passwordHandler.authenticate(badPasswordUser);
    }
}