package workshop2UML.workshop2;

import workshop2UML.workshop2.model.Boat;
import workshop2UML.workshop2.model.Member;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type a sentence :");
        String str = scanner.nextLine();
        System.out.println("You write \"" + str + "\", right ? :)");

        /*Member member = new Member("Name1", 23);
        Member member2 = new Member("wd1", 43);
        Boat boat1 = new Boat(12, Boat.TypeOfBoat.SAILBOAT, 44.9);
        Boat boat3 = new Boat(11, Boat.TypeOfBoat.SAILBOAT, 44.9);
        Boat boat5 = new Boat(16, Boat.TypeOfBoat.MOTORSAILER, 44.9);

        member.add(boat1);
        member2.add(boat3);
        member2.add(boat5);


        member.showInfo();
        member2.showInfo();*/
    }
}
