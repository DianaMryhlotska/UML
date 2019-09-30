package workshop2UML.workshop2.model;

public class Boat {
    private int ID;
    private double length;

    public enum TypeOfBoat{
        SAILBOAT,
        MOTORSAILER,
        KAYAK,
        OTHER
    }


    private TypeOfBoat typeOfBoat;

    public Boat(int ID, TypeOfBoat typeOfBoat, double length) {
        this.ID = ID;
        this.typeOfBoat = typeOfBoat;
        this.length = length;
    }

    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public TypeOfBoat getTypeOfBoat() {
        return typeOfBoat;
    }

    public void setTypeOfBoat(TypeOfBoat typeOfBoat) {
        this.typeOfBoat = typeOfBoat;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Boat{" +
                "ID=" + ID +
                ", typeOfBoat=" + typeOfBoat +
                ", length=" + length +
                '}';
    }
}
