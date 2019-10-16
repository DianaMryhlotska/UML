package workshop2UML.workshop2.view;

import workshop2UML.workshop2.model.Boat;
import workshop2UML.workshop2.model.Member;
import workshop2UML.workshop2.model.TypeOfBoat;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Console {
    private Scanner scanner;

    private int m_input;

    public Console() {
        this.scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
    }

    public void printMembersID (List<Member> membersList) {
        for (Member member : membersList) {
            System.out.println("Member " + member.getMemberId() + "(" + member.getName() + ")");
        }
        System.out.println("\n");
    }

    public void printCompactList (List<Member> membersList) {
        for (Member member : membersList) {
            System.out.println("Member " + member.getMemberId() + "(" + member.getName() + ") : "
                    + member.getNumberOfBoats() + " boats owned");
        }
        System.out.println("\n");
    }

    public void printVerboseList (List<Member> membersList) {
        for (Member member : membersList) {
            printMemberInformations(member);
        }
        System.out.println("\n");
    }

    public void printMemberInformations (Member member) {
        System.out.println("Member " + member.getMemberId() + " : " + member.getName() + " (Personal number: " +
                member.getPersonalNumber() + "). " + member.getNumberOfBoats() + " boats owned :");
        for (Boat boat : member.getListOfBoats())
            System.out.println(boat.toString());
    }

    public void printBoatInformations (Integer memberID, Boat boat) {
        System.out.println("Boat " + boat.getID() + "(owns by member " + memberID + ") : " + boat.getTypeOfBoat() +
                ", length=" + new DecimalFormat("#.00").format(boat.getLength()) );
    }

    public int printMenu () {
        int choice=-1;
        System.out.println("\n");
            while (choice<0 || choice>8) {
                System.out.println("Welcome in the Jolly Pirate Club ! Please select an action");
                System.out.println("\tShow all the registered members : press 1");
                System.out.println("\tAdd a member : press 2");
                System.out.println("\tDelete a member : press 3");
                System.out.println("\tSee informations about a member : press 4");
                System.out.println("\tUpdate informations about a member : press 5");
                System.out.println("\tRegister a new boat : press 6");
                System.out.println("\tRemove a registered boat : press 7");
                System.out.println("\tUpdate informations about a registered boat : press 8");
                System.out.println("\tPress 0 to exit");
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            return choice;
    }

    public int printListMenu() {
        int choice=-1;
        while (choice<1 || choice>2) {
            System.out.println("Please choose the format desired for the member list");
            System.out.println("Show the compact list (without some informations) : press 1");
            System.out.println(" Show the verbose list : press 2");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }


    public void informAboutChoice(int choice) {
        if (choice<0 || choice>8)
            throw new IllegalCallerException("No choice made yet !");
        System.out.println("\n");
        switch (choice) {
            case 1: System.out.println("You choose to see the members list.");
                break;
            case 2: System.out.println("You choose to add a member.");
                break;
            case 3: System.out.println("You choose to delete a member.");
                break;
            case 4: System.out.println("You choose to see informations about a specific member.");
                break;
            case 5: System.out.println("You choose to update informations about a specific member.");
                break;
            case 6: System.out.println("You choose to register a new boat.");
                break;
            case 7: System.out.println("You choose to remove a registered boat.");
                break;
            case 8: System.out.println("You choose to update informations about a specific boat.");
                break;
            default: System.out.println("You are exiting the system. Goodbye !");
                return;
        }

    }


    // TODO : Insert a "return" choice for all of the methods above (with '0' ?)

    public String askForName() {
        System.out.println("Please enter the name");
        String name = scanner.nextLine();

        if (name.equals("0"))
            return null;
        else
            return name;
    }

    public String askForPersonalNumber() {
        String personalNumber;

        do {
            System.out.println("Please enter the personal number (12 digits)");
            personalNumber = scanner.nextLine();
        } while (personalNumber.length()!=12);

        /*if (personalNumber.equals("0"))
            return null;
        else*/
            return personalNumber;
    }

    public int askForMemberID() {
        System.out.println("Please enter the member ID");
        int ID = scanner.nextInt();
        scanner.nextLine();

        if (ID==0)
            return -1;
        else
            return ID;
    }

    public int askForBoatID() {
        System.out.println("Please enter the boat ID");
        int ID = scanner.nextInt();
        scanner.nextLine();

        if (ID==0)
            return -1;
        else
            return ID;
    }

    public TypeOfBoat askForTypeOfBoat() {
        System.out.println("Please select the type of the boat :");
        System.out.println("For a Sailboat, press 1");
        System.out.println("For a Motorsailer, press 2");
        System.out.println("For a Kayak, press 3");
        System.out.println("For an other type, press 4");
        int type=-1;

        while (type<1 || type>4) {
            type = scanner.nextInt();
            scanner.nextLine();
        }

        switch (type) {
            case 1: return TypeOfBoat.SAILBOAT;
            case 2: return TypeOfBoat.MOTORSAILER;
            case 3: return TypeOfBoat.KAYAK;
            case 4: return TypeOfBoat.OTHER;
            default: return null;
        }
    }

    public double askForBoatLength() {
        System.out.println("Please enter the length of the boat (in meters, with decimals) :");
        double length=-1;
        while (length==-1) {
            try {
                length = Double.parseDouble(scanner.nextLine());
            } catch(NumberFormatException e){
                // Check if the nextLine is indeed a double. Unfortunately, scanner.hasNextDouble and scanner.nextDouble
                // can't be used here...
            }
        }
        return length;
    }

    public void printErrorWhileAskingMemberID() {
        System.out.println("You enter an unknown member ID !");
    }

    public void printErrorAboutPersonalNumber() {
        System.out.println("This personal number is already linked to a registered member !");
    }

    public void printErrorWhileAskingBoatID() {
        System.out.println("This member does not own the boat asked !");
    }
}
