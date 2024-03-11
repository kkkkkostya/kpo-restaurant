import authentication.AuthenticationService;
import controllers.AuthenticationController;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        AuthenticationService service = new AuthenticationService();
        AuthenticationController controller = new AuthenticationController(service);
        controller.run();
    }
}
