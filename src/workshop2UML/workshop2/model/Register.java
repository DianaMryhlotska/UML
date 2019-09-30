package workshop2UML.workshop2.model;

import java.util.*;

// TODO : "Change a boat's information"
// TODO : "Delete a boat"

public class Register {
    private HashMap<Integer, Member> register;
    private int numberOfMembers;

    public boolean createMember(String name, String personalNum){
        Member member = new Member(name, personalNum, numberOfMembers);

        if (register.containsValue(member))
            return false;

        register.put(numberOfMembers, member);
        numberOfMembers++;
        return true;
    }

    public boolean deleteMember(Member member){
        return register.remove(member.getMemberId(), member);
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

    public boolean changeMemberInformation (Integer memberID, String name, String personalNumber,
                                            LinkedList<Boat> listOfBoats) {
        if (!register.containsKey(memberID))
            System.out.println("Member " + memberID + " does not exist !");

        Member member = register.get(memberID);

        if (name!=null)
            member.setName(name);

        if (personalNumber!=null)
            member.setPersonalNumber(personalNumber);

        if (listOfBoats!=null)
            member.setListOfBoats(listOfBoats);

        return (register.replace(memberID, member)!=null);
    }

    public Member getMember (Integer memberID) {
        if (!register.containsKey(memberID))
            System.out.println("Member " + memberID + " does not exist !");

        return register.get(memberID);
    }

    public boolean addNewBoat (Integer memberID, Boat newBoat) {
        if (!register.containsKey(memberID))
            throw new IllegalArgumentException("Member " + memberID + " does not exist yet !");

        Member member = register.get(memberID);

        member.addBoat(newBoat);

        return (register.replace(memberID, member)!=null);
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
