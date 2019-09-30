package workshop2UML.workshop2.model;

import java.util.LinkedList;


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

    public void showInfo(){
        System.out.println("Name" + this.Name + "Boats:" + this.m_boat);
    }

    /*
    public LinkedList<Boat> getM_boat() {
        return m_boat;
    }

    public void setM_boat(LinkedList<Boat> m_boat) {
        this.m_boat = m_boat;
    }

    */


}
