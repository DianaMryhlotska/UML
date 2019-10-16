package workshop2UML.workshop2.controller;

import workshop2UML.workshop2.model.Boat;
import workshop2UML.workshop2.model.Member;
import workshop2UML.workshop2.model.Register;
import workshop2UML.workshop2.model.TypeOfBoat;
import workshop2UML.workshop2.view.Console;
import workshop2UML.workshop2.view.FileBackup;

import java.io.IOException;

// TODO : Controls interactions between Models and View !
// TODO : Review all !

public class User {
    private Console console;
    private Register register;
    private FileBackup backup;

    public User(Console console, Register register) {
        this.console = console;
        this.register = register;


        try {
            this.backup = new FileBackup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean startSystem() {
        loadData();

        int choice;
        do {
            choice=console.printMenu();
            // TODO : Find why you have to ask twice to exit !
            switch (choice) {
                case 1: listMember();
                    break;
                case 2: addMember();
                    break;
                case 3: deleteMember();
                    break;
                case 4: seeInformationsAboutAMember();
                    break;
                case 5: updateMemberInformations();
                    break;
                case 6: registerBoat();
                    break;
                case 7: deleteBoat();
                    break;
                case 8: updateBoatInformations();
                    break;
                case 0: console.informAboutChoice(0);
                    break;
                default:
                    return false;
            }
        } while (choice>=1 && choice<=9);

        saveData();
        return true;
    }


    private void listMember() {
        console.informAboutChoice(1);
        int choice = console.printListMenu();
        if (choice==1)
            console.printCompactList(register.membersList());
        else
            console.printVerboseList(register.membersList());
    }

    private void addMember() {
        console.informAboutChoice(2);
        String name = console.askForName();
      
        String personalNumber = console.askForPersonalNumber();
        if (register.containsMember(personalNumber)) {
            console.printErrorAboutPersonalNumber();
            return;
        }

        register.createMember(name, personalNumber);
    }

    private void deleteMember() {
        console.informAboutChoice(3);
        console.printMembersID(register.membersList());
        int ID = console.askForMemberID();
        register.deleteMember(ID);
    }

    private void seeInformationsAboutAMember() {
        console.informAboutChoice(4);
        console.printMembersID(register.membersList());
        int ID = console.askForMemberID();
        Member member = register.getMember(ID);
        console.printMemberInformations(member);
    }

    private void updateMemberInformations() {
        console.informAboutChoice(5);
        console.printMembersID(register.membersList());
        int ID = console.askForMemberID();
        Member member = register.getMember(ID);
        console.printMemberInformations(member);
        String name = console.askForName();
        String personalNumber = console.askForPersonalNumber();
        register.changeMemberInformation(ID, name, personalNumber);
    }

    private void registerBoat() {
        console.informAboutChoice(6);
        console.printMembersID(register.membersList());

        int memberID = console.askForMemberID();
        if (!register.containsMemberID(memberID)) {
            console.printErrorWhileAskingMemberID();
            return;
        }

        TypeOfBoat type = console.askForTypeOfBoat();
        double length = console.askForBoatLength();

        register.addNewBoat(memberID, type, length);
    }

    private void deleteBoat() {
        console.informAboutChoice(7);
        console.printMembersID(register.membersList());

        int memberID = console.askForMemberID();
        if (!register.containsMemberID(memberID)) {
            console.printErrorWhileAskingMemberID();
            return;
        }

        Member member = register.getMember(memberID);

        for (Boat boat : member.getListOfBoats())
            console.printBoatInformations(memberID, boat);

        int boatID = console.askForBoatID();
        if (member.getBoat(boatID)==null) {
            console.printErrorWhileAskingBoatID();
            return;
        }

        register.removeBoat(memberID, boatID);
    }

    private void updateBoatInformations() {
        console.informAboutChoice(8);
        console.printMembersID(register.membersList());

        int memberID = console.askForMemberID();
        if (!register.containsMemberID(memberID)) {
            console.printErrorWhileAskingMemberID();
            return;
        }

        Member member = register.getMember(memberID);

        for (Boat boat : member.getListOfBoats())
            console.printBoatInformations(memberID, boat);

        int boatID = console.askForBoatID();
        if (member.getBoat(boatID)==null) {
            console.printErrorWhileAskingBoatID();
            return;
        }

        TypeOfBoat type = console.askForTypeOfBoat();
        double length = console.askForBoatLength();

        Boat boat = register.getMember(memberID).getBoat(boatID);
        register.removeBoat(memberID, boatID);
        console.printBoatInformations(memberID, boat);
        boat.setLength(length);
        boat.setTypeOfBoat(type);
        register.getMember(memberID).addBoat(boat);
    }

    private void loadData() {
        try {
            backup.loadRegisterFromFile(register);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            backup.saveRegisterIntoFile(register);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}