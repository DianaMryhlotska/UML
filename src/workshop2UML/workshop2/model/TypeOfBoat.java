package workshop2UML.workshop2.model;

public enum TypeOfBoat {
    SAILBOAT,
    MOTORSAILER,
    KAYAK,
    OTHER;

    static public TypeOfBoat findTypeOfBoatFromString(String string) {
        switch (string) {
            case "SAILBOAT":
                return TypeOfBoat.SAILBOAT;
            case "MOTORSAILER":
                return TypeOfBoat.MOTORSAILER;
            case "KAYAK":
                return TypeOfBoat.KAYAK;
            default:
                return TypeOfBoat.OTHER;
        }
    }
}
