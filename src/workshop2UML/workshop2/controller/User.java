package workshop2UML.workshop2.controller;

import workshop2UML.workshop2.model.Boat;
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
        int choice=1;

        while (choice>=1 && choice<=9) {
            choice=console.printMenu();

            switch (choice) {
                case 1: listMember();
                case 2: addMember();
                case 3: deleteMember();
                case 4: seeInformationsAboutAMember();
                case 5: updateMemberInformations();
                case 6: registerBoat();
                case 7: deleteBoat();
                case 8: updateBoatInformations();
                default: console.informAboutChoice(0);
            }
        }

        return true;
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
        console.informAboutChoice(5);
        int ID = console.askForMemberID();
        Member member = register.getMember(ID);
        console.printMemberInformations(member);
        String name = console.askForName();
        String personnalNumber = console.askForPersonnalNumber();
        register.changeMemberInformation(ID, name, personnalNumber);
    }

    private void registerBoat() {
        console.informAboutChoice(6);

        int memberID = console.askForMemberID();
        Boat.TypeOfBoat type = console.askForTypeOfBoat();
        double length = console.askForBoatLength();

        register.addNewBoat(memberID, type, length);
    }

    private void deleteBoat() {
        console.informAboutChoice(7);

        int memberID = console.askForMemberID();
        int boatID = console.askForBoatID();

        register.removeBoat(memberID, boatID);
    }

    private void updateBoatInformations() {
        console.informAboutChoice(8);

        int memberID = console.askForMemberID();
        int boatID = console.askForBoatID();
        Boat.TypeOfBoat type = console.askForTypeOfBoat();
        double length = console.askForBoatLength();

        Boat boat = register.getMember(memberID).getBoat(boatID);
        register.removeBoat(memberID, boatID);
        console.printBoatInformations(memberID, boat);
        boat.setLength(length);
        boat.setTypeOfBoat(type);
        register.getMember(memberID).addBoat(boat);
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