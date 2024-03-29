package workshop2UML.workshop2.view;

import workshop2UML.workshop2.model.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class FileBackup {
    private File file;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private Scanner loader;

    public FileBackup() throws IOException {
        this.file = new File("data.txt");
        this.fileReader = new FileReader(file);
        this.loader = new Scanner(fileReader);
    }

    public void saveRegisterIntoFile(Register register, Users users) throws IOException {
        if (!file.exists())
            System.out.println("Backup successfully created !");
        else
            System.out.println("Backup successfully updated !");

        // We want to erase the old file !
        this.fileWriter = new FileWriter(file, false);

        for (Member member : register.membersList()) {
            fileWriter.write(member.getMemberId() + "\t" + member.getName() + "\t" + member.getPersonalNumber() + "\t");

            if (users.userExist(member.getMemberId()))
                fileWriter.write(users.getUser(member.getMemberId()) + "\t");
            else
                fileWriter.write("-XXXXX-\t");

            for (Boat boat : member.getListOfBoats())
                fileWriter.write(boat.getID() + "/" + boat.getLength() + "/" + boat.getTypeOfBoat() + " // ");

            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    public void loadRegisterFromFile(Register register, Users users) throws IOException {
        if (!file.exists())
            return;

        while (loader.hasNext()) {
            String data = loader.nextLine();

            try {
                String[] memberInfo = data.split("\t");
                if (memberInfo.length != 4 && memberInfo.length != 5)
                    throw new IOException("Data error about members !");

                int memberID = register.createMember(memberInfo[1], memberInfo[2]);

                if (!memberInfo[3].equals("-XXXXX-"))
                    users.addAccess(memberID, memberInfo[3]);

                if (memberInfo.length == 5) {
                    String[] boats = memberInfo[4].split(" // ");

                    for (String boat : boats) {
                        String[] boatInfo = boat.split("/");

                        if (boatInfo.length != 3)
                            throw new IOException("Data error about boats !");

                        register.addNewBoat(memberID, findTypeOfBoatFromString(boatInfo[2]),
                                Double.parseDouble(boatInfo[1]));
                    }
                }
            } catch (IOException | ParseException ioe) {
                System.out.println("Data line had a parsing error - skipped");
                // In case of some errors in the data, we just skip the line...
            }
        }
    }

    private TypeOfBoat findTypeOfBoatFromString(String string) {
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
