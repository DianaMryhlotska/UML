package workshop2UML.workshop2.model;

import java.util.Objects;
import java.util.Scanner;
import java.util.LinkedList;


public class Member {
    private String Name;
    private String PersonalNumber;
    private int MemberId;
    private LinkedList<Boat> listOfBoats;


    public Member(String name, String personalNumber, int memberId) {
        Name = name;
        PersonalNumber = personalNumber;
        MemberId = memberId;
        listOfBoats = new LinkedList<>();
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

    public void addBoat(Boat boat){
        if (boat==null)
            throw new IllegalArgumentException("Inexisting boat !");

        listOfBoats.add(boat);
    }

    /*public void lookSpecInfo(Member member){
        // TODO : Factoriser le scanner, pour le créer qu'une seule fois dans Program
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
            System.out.println(this.listOfBoats);
        }
        else{
            System.out.println(("Please input the right number!"));
        }
    }*/

    public void registerBoat(Boat boat){
        // TODO : Insérer des vérifications (pour être sûr qu'on fasse un ajout/retrait valide)
        this.listOfBoats.add(boat);
    }

    public void deleteBoat(Boat boat){
        this.listOfBoats.remove(boat);
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
