package workshop2UML.workshop2.model;

import java.text.ParseException;
import java.time.Instant;
import java.util.*;

public class Register {
    private HashMap<Integer, Member> register;
    private int numberOfMembers;
    private int numberOfBoats;

    public Register() {
        this.register = new HashMap<Integer, Member>();
        this.numberOfMembers = 1;
        this.numberOfBoats = 1;
    }

    public boolean containsMemberID(int memberID) {
        return register.containsKey(memberID);
    }

    public boolean containsMember(String personalNumber) {
        for (Map.Entry<Integer, Member> member : register.entrySet()) {
            if (member.getValue().getPersonalNumber().equals(personalNumber))
                return true;
        }
        return false;
    }

    public int createMember(String name, String personalNum) throws ParseException {
        Member member = new Member(name, personalNum, numberOfMembers);

        if (register.containsValue(member))
            return -1;

        register.put(numberOfMembers, member);
        numberOfMembers++;
        return numberOfMembers-1;
    }

    public boolean deleteMember(int memberID){
        Member member = register.remove(memberID);
        return member != null;
    }

    public HashMap<Integer, Member> getRegister() {
        return register;
    }

    public List<Member> membersList() {
        ArrayList<Member> listOfMember = new ArrayList<>();
        for (Map.Entry<Integer, Member> member : register.entrySet())
            listOfMember.add(member.getValue());
        return listOfMember;
    }

    public boolean changeMemberInformation (Integer memberID, String name, String personalNumber) throws IllegalArgumentException{
        if (!register.containsKey(memberID))
            throw new IllegalArgumentException("Member " + memberID + " does not exist !");

        Member member = register.get(memberID);

        if (name!=null)
            member.setName(name);

        if (personalNumber!=null)
            member.setPersonalNumber(personalNumber);

        return (register.replace(memberID, member)!=null);
    }

    public Member getMember (Integer memberID) throws IllegalArgumentException{
        if (!register.containsKey(memberID))
            throw new IllegalArgumentException("Member " + memberID + " does not exist !");

        return register.get(memberID);
    }

    public boolean addNewBoat (Integer memberID, TypeOfBoat type, double length) throws IllegalArgumentException{
        if (!register.containsKey(memberID))
            throw new IllegalArgumentException("Member " + memberID + " does not exist yet !");

        Member member = register.get(memberID);

        Boat newBoat = new Boat(numberOfBoats, type, length);
        numberOfBoats++;
        member.addBoat(newBoat);

        return (register.replace(memberID, member)!=null);
    }

    public boolean removeBoat (Integer memberID, Integer boatID) throws IllegalArgumentException{
        if (!register.containsKey(memberID))
            throw new IllegalArgumentException("Member " + memberID + " does not exist yet !");

        Member member = register.get(memberID);

        member.removeBoat(boatID);

        return (register.replace(memberID, member)!=null);
    }


    public int getNumberOfMembers() {
        return numberOfMembers-1;
    }

    public int getNumberOfBoats() {
        return numberOfBoats-1;
    }

    public Member searchAboutPersonalNumber(String personalNumber) {
        for (Member member : register.values()) {
            if (member.getPersonalNumber().equals(personalNumber))
                return member;
        }
        return null;
    }

    public List<Member> searchAboutYearOfBirth (int year) throws IllegalArgumentException{
        if (!(year>1900 && year<2050) && !(year>=0 && year<=100))
            throw new IllegalArgumentException("Date is not valid !");

        if (year>1900) // Convert the format of the year
            year=year%100;

        ArrayList<Member> results = new ArrayList<>();

        for (Member member : register.values()) {
            if (member.getDateOfBirth().getYear() == year)
                results.add(member);
        }

        return results;
    }

    public List<Member> searchAboutMonthOfBirth (int month) throws IllegalArgumentException{
        if (month<1 || month>12)
            throw new IllegalArgumentException("Date is not valid !");

        ArrayList<Member> results = new ArrayList<>();

        for (Member member : register.values()) {
            if (member.getDateOfBirth().getMonth()+1 == month)
                results.add(member);
        }

        return results;
    }

    public List<Member> searchAboutAge (int age) {
        ArrayList<Member> results = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -age);

        for (Member member : register.values()) {
            if (member.getDateOfBirth().compareTo(calendar.getTime())<=0)
                results.add(member);
        }

        return results;
    }

    public List<Member> searchAboutPatternsInName (String pattern) {
        ArrayList<Member> results = new ArrayList<>();

        for (Member member : register.values()) {
            if (member.getName().toLowerCase().contains(pattern.toLowerCase()))
                results.add(member);
        }

        return results;
    }

    public List<Member> searchAboutTypeOfBoatsOwned (TypeOfBoat type) {
        ArrayList<Member> results = new ArrayList<>();

        for (Member member : register.values()) {
            for (Boat boat : member.getListOfBoats()) {
                if (boat.getTypeOfBoat().equals(type)) {
                    results.add(member);
                    break;
                }
            }
        }

        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Register register1 = (Register) o;
        return register.equals(register1.register);
    }

    @Override
    public int hashCode() {
        return Objects.hash(register);
    }
}
