package workshop2UML.workshop2.model;

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

    public boolean createMember(String name, String personalNum){
        Member member = new Member(name, personalNum, numberOfMembers);

        if (register.containsValue(member))
            return false;

        register.put(numberOfMembers, member);
        numberOfMembers++;
        return true;
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

    public boolean changeMemberInformation (Integer memberID, String name, String personalNumber) {
        if (!register.containsKey(memberID))
            System.out.println("Member " + memberID + " does not exist !");

        Member member = register.get(memberID);

        if (name!=null)
            member.setName(name);

        if (personalNumber!=null)
            member.setPersonalNumber(personalNumber);

        return (register.replace(memberID, member)!=null);
    }

    public Member getMember (Integer memberID) {
        if (!register.containsKey(memberID))
            System.out.println("Member " + memberID + " does not exist !");

        return register.get(memberID);
    }

    public boolean addNewBoat (Integer memberID, Boat.TypeOfBoat typeOfBoat, double length) {
        if (!register.containsKey(memberID))
            throw new IllegalArgumentException("Member " + memberID + " does not exist yet !");

        Member member = register.get(memberID);

        Boat newBoat = new Boat(numberOfBoats, typeOfBoat, length);
        numberOfBoats++;
        member.addBoat(newBoat);

        return (register.replace(memberID, member)!=null);
    }

    public boolean removeBoat (Integer memberID, Integer boatID) {
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
