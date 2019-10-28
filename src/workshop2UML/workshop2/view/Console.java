package workshop2UML.workshop2.view;

import workshop2UML.workshop2.model.Boat;
import workshop2UML.workshop2.model.Member;
import workshop2UML.workshop2.model.TypeOfBoat;

import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.interfaces.RSAKey;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Console {
    private Scanner scanner;

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
            while (choice<0 || choice>9) {
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

                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                }

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
        if (choice<0 || choice>10)
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
            case 9: System.out.println("You choose to do a search in our database.");
                break;
            case 10: System.out.println("You want to add an other criteria to your search.");
                break;
            default: System.out.println("You are exiting the system. Goodbye !");
                break;
        }

    }


    public String askForName() {
        System.out.println("Please enter the name");
        return scanner.nextLine();
    }

    public String askForPersonalNumber() {
        String personalNumber;

        boolean lengthIsValid=false;
        boolean formatIsValid=false;
        boolean dateIsValid = false;

        do {
            System.out.println("Please enter the personal number (10 digits : format YYMMDD-XXXX)");
            personalNumber = scanner.nextLine();

            lengthIsValid = (personalNumber.length()==11);

            // if statement are needed to avoid any NullPointerException (for length=1 for example)
            if (lengthIsValid) {
                String[] format = personalNumber.split("-");
                formatIsValid = (format.length==2 && format[0].length()==6 && format[1].length()==4);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");

                Date date;
                try {
                    dateFormat.setLenient(false);
                    date = dateFormat.parse(format[0]);
                    dateIsValid = true;
                } catch (ParseException e) {
                    dateIsValid = false;
                }
            }
        } while (!lengthIsValid || !formatIsValid || !dateIsValid);

        return personalNumber;
    }

    public int askForMemberID(){
        System.out.println("Please enter the member ID");
        return scanValidInt();
    }

    public int askForBoatID(){
        System.out.println("Please enter the boat ID");
        return scanValidInt();
    }

    public TypeOfBoat askForTypeOfBoat() {
        System.out.println("Please select the type of the boat :");
        System.out.println("For a Sailboat, press 1");
        System.out.println("For a Motorsailer, press 2");
        System.out.println("For a Kayak, press 3");
        System.out.println("For an other type, press 4");
        int type=-1;

        while (type<1 || type>4) {
            type = scanValidInt();
        }

        return getTypeOfBoat(type);
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

    public int askIDForAuthentification() {
        System.out.println("You must be logged to proceed !");
        return askForMemberID();
    }

    public String askPassword() {
        System.out.println("Please enter the password :");
        return scanner.nextLine();
    }

    public boolean askForCreateAUser() {
        System.out.println("Do you want to create a password for this member and give him access rights ? (Type \"yes\" or \"ja\")");
        String answer = scanner.nextLine();
        return answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("ja");
    }

    public void authSuccess(boolean success) {
        if (success)
            System.out.println("Authentification successful !");
        else
            System.out.println("Authentification has failed !");
    }

    public int askForSearch() {
        System.out.println("For searching a name pattern, press 1");
        System.out.println("For searching about a specific year of birth, press 2");
        System.out.println("For searching about a specific month of birth, press 3");
        System.out.println("For searching the members older than a specific age, press 4");
        System.out.println("For searching all the owners of a specific type of boat, press 5");
        int search=-1;

        while (search<1 || search>5) {
            search = scanValidInt();
        }

        return search;
    }

    public String askForSearchingAboutPatternsInName() {
        System.out.println("You want to search a certain pattern in members' name. Please type your pattern :");
        return scanner.nextLine();
    }

    public int askForSearchingAboutYearOfBirth() {
        System.out.println("You want to search about a specific year of birth. Please type the year :");
        int year=-1;

        while (year<1900 || year>2050) {
            year = scanValidInt();
        }

        return year;
    }

    public int askForSearchingAboutMonthOfBirth() {
        System.out.println("You want to search about a specific month of birth. Please type the month :");
        int month=-1;

        while (month<1 || month>12) {
            month = scanValidInt();
        }

        return month;
    }

    public int askForSearchingAboutAge() {
        System.out.println("You want to search all the members older than a certain age. Please type the age :");
        int age=-1;

        while (age<0 || age>119) { // Impossible to register someone who was born before 1900
            age = scanValidInt();
        }

        return age;
    }

    public TypeOfBoat askForSearchingAboutTypeOfBoat() {
        System.out.println("You want to search all the owners of a specific type of boat.");
        System.out.println("Press 1 for a Sailboat, 2 for a Motorsailer, 3 for a Kayak/Canoe or 4 for the others " +
                "types");
        int type=-1;

        while (type<1 || type>4) {
            type = scanValidInt();
        }

        return getTypeOfBoat(type);
    }

    public boolean askForAnOtherCriteria() {
        System.out.println("Do you want to add an other criteria for your search ? (Type \"yes\" or \"ja\")");
        String answer = scanner.nextLine();
        return answer.toLowerCase().equals("yes") || answer.toLowerCase().equals("ja");
    }

    public void searchingResults() {
        System.out.println("Here are the results of your search in our database :");
    }



    // ---- Generalization for data validation

    private int scanValidInt() {
        boolean validInt=false;
        int ID=-1;

        do {
            try {
                ID = scanner.nextInt();
                scanner.nextLine();
                validInt = true;
            } catch (InputMismatchException e) {
                validInt = false;
            }
        } while (!validInt);
        return ID;
    }

    private TypeOfBoat getTypeOfBoat(int type) {
        switch (type) {
            case 1: return TypeOfBoat.SAILBOAT;
            case 2: return TypeOfBoat.MOTORSAILER;
            case 3: return TypeOfBoat.KAYAK;
            case 4: return TypeOfBoat.OTHER;
            default: return null;
        }
    }
}
