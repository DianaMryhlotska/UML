package workshop2UML.workshop2.model;

import java.util.Objects;

public class Boat {
    private int ID;
    private double length;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boat boat = (Boat) o;
        return ID == boat.ID &&
                Double.compare(boat.length, length) == 0 &&
                typeOfBoat == boat.typeOfBoat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, length, typeOfBoat);
    }
}
