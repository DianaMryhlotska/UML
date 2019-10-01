package workshop2UML.workshop2.view;

import workshop2UML.workshop2.model.Boat;
import workshop2UML.workshop2.model.Member;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Console {
    private Scanner scanner;

    private int m_input;

    public Console() {
        this.scanner = new Scanner(System.in);
    }

    /*protected int getInputChar() {
        try {
            int c = System.in.read();
            while (c == '\r' || c =='\n') {
                c = System.in.read();
            }
            return c;
        } catch (java.io.IOException e) {
            System.out.println("" + e);
            return 0;
        }
    }

    public void presentInstructions(boolean a_ProgramStarted) {
        System.out.println("=======================================================");
        System.out.println("Welcome to the yacht club “The jolly pirate”");
        if (a_ProgramStarted) {
            System.out.println("To create a member press c, " +
                    "\nto delete member press d, " +
                    "\nto show a compact list press l\n" +
                    "to show a verbose list of members press v\n" +
                    "to quit press q");
        } else {
            System.out.println("p to start, q to quit");
        }
        System.out.println("-------------------------------------------------------");
    }

    public void collectEvents() {
        m_input = getInputChar();
    }

    public boolean wantsToStart() {
        return m_input == 'p';
    }

    public boolean wantsToCreateMember() {
        return m_input == 'c';
    }

    public boolean wantsToDeleteMember() {
        return m_input == 'd';
    }

    public boolean wantsToShowCompact() {
        return m_input == 'l';
    }

    public boolean wantsToShowVerbose() {
        return m_input == 'v';
    }

    public boolean wantsToQuit() {
        return m_input == 'q';
    }

    public String inputName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        System.out.println("Hallo, \"" + name + "\" :)");
        return name;
    }

    public String inputPesonalNum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please personal number:");
        String personalNum = scanner.nextLine();
        System.out.println("Thanks, we have your personal number now");
        return personalNum;
    }*/

    public void printCompactList (List<Member> membersList) {
        for (Member member : membersList) {
            System.out.println("Member " + member.getMemberId() + "(" + member.getName() + ") : "
                    + member.getNumberOfBoats() + "boats owned");
        }
    }

    public void printVerboseList (List<Member> membersList) {
        for (Member member : membersList) {
            printMemberInformations(member);
        }
    }

    public void printMemberInformations (Member member) {
        System.out.println("Member " + member.getMemberId() + " : " + member.getName() + " (Personnal number: " +
                member.getPersonalNumber() + "). " + member.getNumberOfBoats() + "boats owned :");
        for (Boat boat : member.getListOfBoats())
            System.out.println(boat.toString());
    }

    public void printBoatInformations (Integer memberID, Boat boat) {
        System.out.println("Boat " + boat.getID() + "(owns by member " + memberID + ") : " + boat.getTypeOfBoat() +
                ", length=" + new DecimalFormat("$#.00").format(boat.getLength()) );
    }

    public int printMenu () {
        int choice=-1;
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
        }
        return choice;
    }

    /*public Member printAddMember() {
        System.out.println("You choosed to add a member. Please enter his/her name, or 0 to return to the menu");
    }*/

    public void informAboutChoice(int choice) {
        if (choice<0 || choice>8)
            throw new IllegalCallerException("No choice made yet !");

        switch (choice) {
            case 1: System.out.println("You choose to see the members list.");
            case 2: System.out.println("You choose to add a member.");
            case 3: System.out.println("You choose to delete a member.");
            case 4: System.out.println("You choose to see informations about a specific member.");
            case 5: System.out.println("You choose to update informations about a specific member.");
            case 6: System.out.println("You choose to register a new boat.");
            case 7: System.out.println("You choose to remove a registered boat.");
            case 8: System.out.println("You choose to update informations about a specific boat.");
            default: System.out.println("You are exiting the system. Goodbye !");
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

    public String askForPersonnalNumber() {
        System.out.println("Please enter the personnal number");
        String personnalNumber = scanner.nextLine();

        if (personnalNumber.equals("0"))
            return null;
        else
            return personnalNumber;
    }

    public int askForMemberID() {
        System.out.println("Please enter the member ID");
        int ID = scanner.nextInt();

        if (ID==0)
            return -1;
        else
            return ID;
    }

    public int askForBoatID() {
        System.out.println("Please enter the boat ID");
        int ID = scanner.nextInt();

        if (ID==0)
            return -1;
        else
            return ID;
    }

    public Boat.TypeOfBoat askForTypeOfBoat() {
        System.out.println("Please select the type of the boat :");
        System.out.println("For a Sailboat, press 1");
        System.out.println("For a Motorsailer, press 2");
        System.out.println("For a Kayak, press 3");
        System.out.println("For an other type, press 4");
        int type=-1;

        while (type<1 || type>4) {
            type = scanner.nextInt();
        }

        switch (type) {
            case 1: return Boat.TypeOfBoat.SAILBOAT;
            case 2: return Boat.TypeOfBoat.MOTORSAILER;
            case 3: return Boat.TypeOfBoat.KAYAK;
            case 4: return Boat.TypeOfBoat.OTHER;
        }

        return null; //never happens
    }

    public double askForBoatLength() {
        System.out.println("Please enter the length of the boat (in meters, with decimals) :");
        double length=-1;
        while (length<=0 || length >2000) {
            length = scanner.nextDouble();
        }
        return length;
    }
}
