package workshop2UML.workshop2.model;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Random;


public class Member {
    private String Name;
    private int PersonalNumber;
    LinkedList<Boat> m_boat;


    public Member(String name, int personalNumber) {
        Name = name;
        PersonalNumber = personalNumber;
        m_boat = new LinkedList<>();
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPersonalNumber() {
        return PersonalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        PersonalNumber = personalNumber;
    }

    public void add(Boat boat){
        m_boat.add(boat);
        System.out.println("" + boat);
    }

    public void createNewMember(String name){
        Random rand = new Random();
        this.setPersonalNumber(rand.nextInt(99999));
        this.setName(name);
    }

    public void deleteMember(){}

    public void changeInfoMember(){}

    public void lookSpecInfo(Member member){
        int choise;
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to look? Press 1 - Name, press 2 - PersonalID, press 3 - list of boats");

        if()
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




}
