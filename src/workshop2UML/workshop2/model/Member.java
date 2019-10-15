package workshop2UML.workshop2.model;

import java.util.Objects;
import java.util.Scanner;
import java.util.LinkedList;

// TODO : Insert checks about data when we add/remove something

public class Member {
    private String Name;
    private String PersonnalNumber;
    private int MemberId;
    private LinkedList<Boat> listOfBoats;


    public Member(String name, String personalNumber, int memberId) {
        Name = name;
        PersonnalNumber = personalNumber;
        MemberId = memberId;
        listOfBoats = new LinkedList<>();
    }


    public String getName() {
        return Name;
    }

    protected void setName(String name) {
        Name = name;
    }

    public String getPersonnalNumber() {
        return PersonnalNumber;
    }

    public int getMemberId() {
        return MemberId;
    }

    protected void setPersonnalNumber(String personalNumber) {
        PersonnalNumber = personalNumber;
    }

    private void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public int getNumberOfBoats() {
        return listOfBoats.size();
    }

    public LinkedList<Boat> getListOfBoats() {
        return listOfBoats;
    }

    protected void setListOfBoats(LinkedList<Boat> listOfBoats) {
        this.listOfBoats = listOfBoats;
    }

    public Boat getBoat(Integer boatID) {
        for (Boat boat : listOfBoats) {
            if (boat.getID()==boatID)
                return boat;
        }

        return null;
    }

    public void addBoat(Boat boat){
        if (boat==null)
            throw new IllegalArgumentException("Inexisting boat !");

        listOfBoats.add(boat);
    }

    public boolean removeBoat(Integer boatID) {
        Boat boat = getBoat(boatID);

        if (boat!=null)
            return listOfBoats.remove(getBoat(boatID));
        else
            return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return MemberId == member.MemberId &&
                Name.equals(member.Name) &&
                PersonnalNumber.equals(member.PersonnalNumber) &&
                listOfBoats.equals(member.listOfBoats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, PersonnalNumber, MemberId, listOfBoats);
    }
}
