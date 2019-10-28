package workshop2UML.workshop2.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.LinkedList;

public class Member {
    private String Name;
    private String PersonalNumber;
    private Date dateOfBirth;
    private int MemberId;
    private LinkedList<Boat> listOfBoats;


    public Member (String name, String personalNumber, int memberId) {
        Name = name;
        PersonalNumber = personalNumber;
        MemberId = memberId;
        listOfBoats = new LinkedList<>();

        try {
            String[] format = personalNumber.split("-");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            dateFormat.setLenient(false);
            this.dateOfBirth = dateFormat.parse(format[0]);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error while parsing the personnal number !");
        }
        catch (NullPointerException n) {
            n.printStackTrace();
            throw new NullPointerException("Error while parsing the personnal number !");
        }
    }


    public String getName() {
        return Name;
    }

    protected void setName(String name) {
        Name = name;
    }

    public String getPersonalNumber() {
        return PersonalNumber;
    }

    public int getMemberId() {
        return MemberId;
    }

    protected void setPersonalNumber(String personalNumber) {
            PersonalNumber = personalNumber;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return MemberId == member.MemberId &&
                Name.equals(member.Name) &&
                PersonalNumber.equals(member.PersonalNumber) &&
                listOfBoats.equals(member.listOfBoats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, PersonalNumber, MemberId, listOfBoats);
    }
}
