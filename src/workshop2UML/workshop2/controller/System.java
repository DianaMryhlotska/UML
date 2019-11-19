package workshop2UML.workshop2.controller;

import workshop2UML.workshop2.model.*;
import workshop2UML.workshop2.view.Console;
import workshop2UML.workshop2.view.ConsoleMenu;
import workshop2UML.workshop2.view.FileBackup;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;

// TODO : Encrypt all the passwords

public class System {
    private Console console;
    private ConsoleMenu menu;
    private Register register;
    private FileBackup backup;
    private boolean logged;
    private Users users;

    public System(Console console, Register register) {
        this.console = console;
        this.menu = new ConsoleMenu(console);
        this.register = register;
        this.users = new Users();
        this.logged = false;

        try {
            this.backup = new FileBackup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean startSystem() {
        loadData();

        ConsoleMenu.MenuChoices choice;
        do {
            choice=menu.printMenu();
            switch (choice) {
                case ListMemberMenu: listMember();
                    break;
                case AddMember: addMember();
                    break;
                case DeleteMember: deleteMember();
                    break;
                case SeeMember: seeInformationsAboutAMember();
                    break;
                case UpdateMember: updateMemberInformations();
                    break;
                case RegisterBoat: registerBoat();
                    break;
                case RemoveBoat: deleteBoat();
                    break;
                case UpdateBoat: updateBoatInformations();
                    break;
                case Search: computeASearch();
                    break;
                case Exit: console.informAboutChoice(ConsoleMenu.MenuChoices.Exit);
                    break;
                default:
                    return false;
            }
        } while (choice != ConsoleMenu.MenuChoices.Exit);

        saveData();
        return true;
    }


    private void listMember() {
        console.informAboutChoice(ConsoleMenu.MenuChoices.ListMemberMenu);
        ConsoleMenu.MenuChoices choice = menu.printListMenu();

        switch (choice) {
            case CompactList: console.printCompactList(register.membersList());
                break;
            case VerboseList: console.printVerboseList(register.membersList());
                break;
            default: throw new IllegalArgumentException("Irrelevant choice !");
        }
    }

    private void addMember() {
        if (logged || logIn()) {
            console.informAboutChoice(ConsoleMenu.MenuChoices.AddMember);
            String name = console.askForName();

            String personalNumber = console.askForPersonalNumber();
            if (register.containsMember(personalNumber)) {
                console.printErrorAboutPersonalNumber();
                return;
            }

            int ID = 0;

            try {
                ID = register.createMember(name, personalNumber);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (console.askForCreateAUser()) {
                String password = console.askPassword();
                users.addAccess(ID, password);
            }
        }
    }

    private void deleteMember() {
        if (logged || logIn()) {
            console.informAboutChoice(ConsoleMenu.MenuChoices.DeleteMember);
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
        console.informAboutChoice(ConsoleMenu.MenuChoices.SeeMember);
        console.printMembersID(register.membersList());

        int ID;
        try {
            ID = console.askForMemberID();
        } catch (InputMismatchException error) {
            console.printErrorWhileAskingMemberID();
            return;
        }

        Member member;
        try {
            member = register.getMember(ID);
        } catch (IllegalArgumentException e) {
            console.printErrorWhileAskingMemberID();
            return;
        }
        console.printMemberInformations(member);
    }

    private void updateMemberInformations() {
        if (logged || logIn()) {
            console.informAboutChoice(ConsoleMenu.MenuChoices.UpdateMember);
            console.printMembersID(register.membersList());

            int ID;
            try {
                ID = console.askForMemberID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            Member member;
            try {
                member = register.getMember(ID);
            } catch (IllegalArgumentException e) {
                console.printErrorWhileAskingMemberID();
                return;
            }
            console.printMemberInformations(member);
            String name = console.askForName();
            String personalNumber = console.askForPersonalNumber();
            register.changeMemberInformation(ID, name, personalNumber);
        }
    }

    private void registerBoat() {
        if (logged || logIn()) {
            console.informAboutChoice(ConsoleMenu.MenuChoices.RegisterBoat);
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
            console.informAboutChoice(ConsoleMenu.MenuChoices.RemoveBoat);
            console.printMembersID(register.membersList());

            int memberID;
            try {
                memberID = console.askForMemberID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            Member member;
            try {
                member = register.getMember(memberID);
            } catch (IllegalArgumentException e) {
                console.printErrorWhileAskingMemberID();
                return;
            }

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
            console.informAboutChoice(ConsoleMenu.MenuChoices.UpdateBoat);
            console.printMembersID(register.membersList());

            int memberID;
            try {
                memberID = console.askForMemberID();
            } catch (InputMismatchException error) {
                console.printErrorWhileAskingMemberID();
                return;
            }

            Member member;
            try {
                member = register.getMember(memberID);
            } catch (IllegalArgumentException e) {
                console.printErrorWhileAskingMemberID();
                return;
            }

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

    private void computeASearch() {
        boolean criteria = true;
        ConsoleMenu.MenuChoices choice;
        ArrayList<Member> listAsked = (ArrayList<Member>) register.membersList();
        console.informAboutChoice(ConsoleMenu.MenuChoices.Search);
        while (criteria) {
            choice=menu.askForSearch();

            if (choice== ConsoleMenu.MenuChoices.NameSearch) {
                String pattern = console.askForSearchingAboutPatternsInName();
                listAsked.retainAll(register.searchAboutPatternsInName(pattern));
            }

            if (choice== ConsoleMenu.MenuChoices.YearSearch) {
                int year = console.askForSearchingAboutYearOfBirth();
                listAsked.retainAll(register.searchAboutYearOfBirth(year));
            }

            if (choice== ConsoleMenu.MenuChoices.MonthSearch) {
                int month = console.askForSearchingAboutMonthOfBirth();
                listAsked.retainAll(register.searchAboutMonthOfBirth(month));
            }

            if (choice== ConsoleMenu.MenuChoices.AgeSearch) {
                int age = console.askForSearchingAboutAge();
                listAsked.retainAll(register.searchAboutAge(age));
            }

            if (choice== ConsoleMenu.MenuChoices.BoatTypeSearch) {
                TypeOfBoat type = console.askForSearchingAboutTypeOfBoat();
                listAsked.retainAll(register.searchAboutTypeOfBoatsOwned(type));
            }

            criteria = console.askForAnOtherCriteria();
        }

        console.searchingResults();
        console.printCompactList(listAsked);
    }

    private void loadData() {
        try {
            backup.loadRegisterFromFile(register, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            backup.saveRegisterIntoFile(register, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean logIn() {
        console.printMembersID(register.membersList());
        int ID = console.askIDForAuthentification();
        String password = console.askPassword();

        if (users.userExist(ID) && users.getUser(ID).equals(password)) {
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