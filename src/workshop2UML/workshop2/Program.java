package workshop2UML.workshop2;

import workshop2UML.workshop2.model.Register;
import workshop2UML.workshop2.view.Console;
import workshop2UML.workshop2.controller.User;

// TODO : Export registry to a text file
// TODO : An example, to test the software

public class Program {

    public static void main(String[] args) {
        Console console = new Console();
        Register register = new Register();
        User user = new User(console, register);

        user.startSystem();


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
