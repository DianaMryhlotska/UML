package workshop2UML.workshop2.controller;

import workshop2UML.workshop2.model.Register;
import workshop2UML.workshop2.view.Console;

// TODO : Controls interactions between Models and View !
// TODO : Review all !

public class User {
    private Console console;
    private Register register;

    public User(Console console, Register register) {
        this.console = console;
        this.register = register;
    }

    public boolean startSystem() {
        int choice=console.printMenu();

        switch (choice) {
            case 1: listMember();
            case 2:;
            case 3:;
            case 4:;
            case 5:;
            case 6:;
            case 7:;
            case 8:;
        }
    }

    public void listMember() {
        int choice = console.printListMenu();
        if (choice==1)
            console.printCompactList(register.membersList());
        else
            console.printVerboseList(register.membersList());
    }

    /*public boolean Start(Console a_view, ClubSystem a_session) {

        a_view.collectEvents();

        if (a_view.wantsToStart()) {
            a_session.start();
        } else if (a_view.wantsToCreateMember()) {
            String name = a_view.inputName();
            String personalNum = a_view.inputPesonalNum();
            a_session.createNewMember(name, personalNum);
        }

        return true;
    }*/
}