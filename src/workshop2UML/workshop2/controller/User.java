package workshop2UML.workshop2.controller;

import workshop2UML.workshop2.model.Boat;
import workshop2UML.workshop2.model.Member;
import workshop2UML.workshop2.model.Register;
import workshop2UML.workshop2.model.TypeOfBoat;
import workshop2UML.workshop2.view.Console;
import workshop2UML.workshop2.view.FileBackup;

import java.io.IOException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.InputMismatchException;

// TODO : Encrypt all the passwords
// TODO : Export encrypted passwords in the save file (if the user want it)

public class User {
    private Console console;
    private Register register;
    private FileBackup backup;
    private boolean logged;
    private HashMap<Integer, String> users;

    public User(Console console, Register register) {
        this.console = console;
        this.register = register;
        this.users = new HashMap<>();
        this.logged = false;

        users.put(0, "root");

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
        if (logged || logIn()) {
            console.informAboutChoice(2);
            String name = console.askForName();

            String personalNumber = console.askForPersonalNumber();
            if (register.containsMember(personalNumber)) {
                console.printErrorAboutPersonalNumber();
                return;
            }

            int ID = register.createMember(name, personalNumber);

            if (console.askForCreateAUser()) {
                String password = console.askPassword();
                users.put(ID, password);
            }
        }
    }

    private void deleteMember() {
        if (logged || logIn()) {
            console.informAboutChoice(3);
            console.printMembersID(register.membersList());

            int ID;
            try {
                ID = console.askForMemberID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingMemberID();
                return;
            }
            register.deleteMember(ID);
        }
    }

    private void seeInformationsAboutAMember() {
        console.informAboutChoice(4);
        console.printMembersID(register.membersList());

        int ID;
        try {
            ID = console.askForMemberID();
        } catch (InputMismatchException error) {
            console.printErrorWhileAskingMemberID();
            return;
        }

        Member member = register.getMember(ID);
        console.printMemberInformations(member);
    }

    private void updateMemberInformations() {
        if (logged || logIn()) {
            console.informAboutChoice(5);
            console.printMembersID(register.membersList());

            int ID;
            try {
                ID = console.askForMemberID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            Member member = register.getMember(ID);
            console.printMemberInformations(member);
            String name = console.askForName();
            String personalNumber = console.askForPersonalNumber();
            register.changeMemberInformation(ID, name, personalNumber);
        }
    }

    private void registerBoat() {
        if (logged || logIn()) {
            console.informAboutChoice(6);
            console.printMembersID(register.membersList());

            int memberID;
            try {
                memberID = console.askForMemberID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            if (!register.containsMemberID(memberID)) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            TypeOfBoat type = console.askForTypeOfBoat();
            double length = console.askForBoatLength();

            register.addNewBoat(memberID, type, length);
        }
    }

    private void deleteBoat() {
        if (logged || logIn()) {
            console.informAboutChoice(7);
            console.printMembersID(register.membersList());

            int memberID;
            try {
                memberID = console.askForMemberID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            if (!register.containsMemberID(memberID)) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            Member member = register.getMember(memberID);

            for (Boat boat : member.getListOfBoats())
                console.printBoatInformations(memberID, boat);

            int boatID;
            try {
                boatID = console.askForBoatID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingBoatID();
                return;
            }

            if (member.getBoat(boatID) == null) {
                console.printErrorWhileAskingBoatID();
                return;
            }

            register.removeBoat(memberID, boatID);
        }
    }

    private void updateBoatInformations() {
        if (logged || logIn()) {
            console.informAboutChoice(8);
            console.printMembersID(register.membersList());

            int memberID;
            try {
                memberID = console.askForMemberID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            if (!register.containsMemberID(memberID)) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            Member member = register.getMember(memberID);

            for (Boat boat : member.getListOfBoats())
                console.printBoatInformations(memberID, boat);

            int boatID;
            try {
                boatID = console.askForBoatID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingBoatID();
                return;
            }

            if (member.getBoat(boatID) == null) {
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

    private boolean logIn() {
        int ID = console.askIDForAuthentification();
        String password = console.askPassword();

        if (users.containsKey(ID) && users.get(ID).equals(password)) {
            logged = true;
            console.authSuccess(true);
            return true;
        }
        else {
            console.authSuccess(false);
            return false;
        }
    }
}