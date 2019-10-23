package workshop2UML.workshop2;

import workshop2UML.workshop2.model.Register;
import workshop2UML.workshop2.view.Console;
import workshop2UML.workshop2.controller.User;

public class    Program {

    public static void main(String[] args) {
        Console console = new Console();
        Register register = new Register();
        User user = new User(console, register);

        user.startSystem();
    }
}
