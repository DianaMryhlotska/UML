package workshop2UML.workshop2.model;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Random;


public class Member {
    private String Name;
    private String PersonalNumber;
    private int MemberId;
    LinkedList<Boat> m_boat;


    public Member(String name, String personalNumber, int memberId) {
        Name = name;
        PersonalNumber = personalNumber;
        MemberId = memberId;
        m_boat = new LinkedList<>();
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPersonalNumber() {
        return PersonalNumber;
    }

    public int getMemberId() {
        return MemberId;
    }

    public void setPersonalNumber(String personalNumber) {
        PersonalNumber = personalNumber;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public void add(Boat boat){
        m_boat.add(boat);
        System.out.println("" + boat);
    }

    public void createMember(String name, String personalNum){
        Random rand = new Random();
        this.setMemberId(rand.nextInt(99999));
        this.setPersonalNumber(personalNum);
        this.setName(name);
    }

    public void deleteMember(Member member){


    }

    public void changeInfoMember(){}

    public void lookSpecInfo(Member member){
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to look? Press 1 - Name, press 2 - PersonalID, press 3 - list of boats");
        int choice = in.nextInt();
        if(choice == 1){
            System.out.println(this.Name);
        }
        if(choice == 2){
            System.out.println(this.PersonalNumber);
        }
        if(choice == 3){
            System.out.println(this.m_boat);
        }
        else{
            System.out.println(("Please input the right number!"));
        }
    }

    public void registerBoat(Boat boat){
        this.m_boat.add(boat);
    }

    public void deleteBoat(Boat boat){
        this.m_boat.remove(boat);
    }


    public void showInfo(){
        System.out.println("Name" + this.Name + "Boats:" + this.m_boat);
    }


    public void start() {
        return;
    }
}
