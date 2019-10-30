package workshop2UML.workshop2.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleMenu {
    private Scanner scanner;

    public enum MenuChoices {
        ListMemberMenu(1),
        AddMember(2),
        DeleteMember(3),
        SeeMember(4),
        UpdateMember(5),
        RegisterBoat(6),
        RemoveBoat(7),
        UpdateBoat(8),
        Search(9),
        Exit(0),
        CompactList(11),
        VerboseList(12),
        NameSearch(21),
        YearSearch(22),
        MonthSearch(23),
        AgeSearch(24),
        BoatTypeSearch(25),
        INVALID(-1);

        private int choice;
        private static Map<Integer, MenuChoices> map = new HashMap<Integer, MenuChoices>();

        private MenuChoices(int choice) {
            this.choice = choice;
        }

        static {
            for (MenuChoices value : MenuChoices.values())
                map.put(value.choice, value);
        }

        public static MenuChoices valueOf (int input) {
            return map.get(input);
        }
    }

    public ConsoleMenu(Console console) {
        this.scanner = console.getScanner();
    }

    public MenuChoices printMenu () {
        int choice=-1;
        boolean reminder=false;

        System.out.println("\n");
        System.out.println("Welcome in the Jolly Pirate Club ! Please select an action");
        System.out.println("\tShow all the registered members : press 1");
        System.out.println("\tAdd a member (registered users only) : press 2");
        System.out.println("\tDelete a member (registered users only) : press 3");
        System.out.println("\tSee informations about a member : press 4");
        System.out.println("\tUpdate informations about a member (registered users only) : press 5");
        System.out.println("\tRegister a new boat (registered users only) : press 6");
        System.out.println("\tRemove a registered boat (registered users only) : press 7");
        System.out.println("\tUpdate informations about a registered boat (registered users only) : press 8");
        System.out.println("\tSearch a specific member : press 9");
        System.out.println("\tPress 0 to exit");

        while (choice<0 || choice>9) {
            if (reminder)
                System.out.println("You have to type your choice between 0 and 9 !");
            reminder = true;

            choice = Console.scanValidInt(scanner);

        }

        return MenuChoices.valueOf(choice);
    }

    public MenuChoices printListMenu() {
        int choice=-1;
        boolean reminder=false;

        System.out.println("Please choose the format desired for the member list");
        System.out.println("Show the compact list (without some informations) : press 1");
        System.out.println(" Show the verbose list : press 2");

        while (choice<1 || choice>2) {
            if (reminder)
                System.out.println("You have to type your choice between 1 or 2 !");
            reminder = true;

            choice = Console.scanValidInt(scanner);
        }
        return MenuChoices.valueOf(choice+10);
    }

    public MenuChoices askForSearch() {
        int search=-1;
        boolean reminder = false;

        System.out.println("For searching a name pattern, press 1");
        System.out.println("For searching about a specific year of birth, press 2");
        System.out.println("For searching about a specific month of birth, press 3");
        System.out.println("For searching the members older than a specific age, press 4");
        System.out.println("For searching all the owners of a specific type of boat, press 5");

        while (search<1 || search>5) {
            if (reminder)
                System.out.println("You have to type your choice between 1 and 5 !");
            reminder = true;

            search = Console.scanValidInt(scanner);
        }

        return MenuChoices.valueOf(search+20);
    }
}
