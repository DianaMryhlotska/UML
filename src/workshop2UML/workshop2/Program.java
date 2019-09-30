package workshop2UML.workshop2;

import workshop2UML.workshop2.model.TheSystem;
import workshop2UML.workshop2.view.Console;
import workshop2UML.workshop2.controller.User;


public class Program {

    public static void main(String[] args) {

        TheSystem session = new TheSystem();
        Console v = new Console();
        User c = new User();

        while(c.Start(v, session));

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
