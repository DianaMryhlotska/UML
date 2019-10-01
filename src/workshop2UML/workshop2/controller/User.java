package workshop2UML.workshop2.controller;

import workshop2UML.workshop2.model.Member;
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
            case 2: addMember();
            case 3:;
            case 4:;
            case 5:;
            case 6:;
            case 7:;
            case 8:;
        }
    }

    private void listMember() {
        console.informAboutChoice(1);
        int choice = console.printListMenu();
        if (choice==1)
            console.printCompactList(register.membersList());
        else
            console.printVerboseList(register.membersList());
        startSystem();
    }

    private void addMember() {
        console.informAboutChoice(2);
        String name = console.askForName();
        String personnalNumber = console.askForPersonnalNumber();
        register.createMember(name, personnalNumber);
    }

    private void deleteMember() {
        console.informAboutChoice(3);
        int ID = console.askForMemberID();
        register.deleteMember(ID);
    }

    private void seeInformationsAboutAMember() {
        console.informAboutChoice(4);
        int ID = console.askForMemberID();
        Member member = register.getMember(ID);
        console.printMemberInformations(member);
    }

    private void updateMemberInformations() {
        // TODO : Gérer le cas des null si on ne veut pas tout changer
        console.informAboutChoice(5);
        int ID = console.askForMemberID();
        String name = console.askForName();
        String personnalNumber = console.askForPersonnalNumber();
        register.changeMemberInformation(ID, name, personnalNumber);
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

    private void safeExit(Object object) {
        if (object==null)
            startSystem();

        int o = (int) object;
        if (o==-1)
            startSystem();
    }
}