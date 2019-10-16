package workshop2UML.workshop2.view;

import workshop2UML.workshop2.model.Boat;
import workshop2UML.workshop2.model.Member;
import workshop2UML.workshop2.model.Register;
import workshop2UML.workshop2.model.TypeOfBoat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileBackup {
    private File file;
    private FileWriter fileWriter;
    private FileReader fileReader;

    public FileBackup() throws IOException {
        this.file = new File("data.txt");
    }

    public void saveRegisterIntoFile(Register register) throws IOException {
        if (!file.exists())
            System.out.println("Backup successfully created !");

        fileWriter = new FileWriter(file);

        for (Member member : register.membersList()) {
            fileWriter.write(member.getMemberId() + "\t" + member.getName() + "\t" + member.getPersonalNumber() + "\t");

            for (Boat boat : member.getListOfBoats())
                fileWriter.write(boat.getID() + "/" + boat.getLength() + "/" + boat.getTypeOfBoat() + " // ");

            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    public void loadRegisterFromFile(Register register) throws IOException {
        if (!file.exists())
            return;

        fileReader = new FileReader(file);

        Scanner loader = new Scanner(fileReader);
        while (loader.hasNext()) {
            String data = loader.nextLine();

            String[] memberInfo = data.split("\t");
            if (memberInfo.length!=3 && memberInfo.length!=4)
                throw new IOException("Data error about members !");

            int memberID = register.createMember(memberInfo[1], memberInfo[2]);

            if (memberInfo.length==4) {
                String[] boats = memberInfo[3].split(" // ");

                for (String boat : boats) {
                    String[] boatInfo = boat.split("/");

                    if (boatInfo.length != 3)
                        throw new IOException("Data error about boats !");

                    register.addNewBoat(memberID, TypeOfBoat.findTypeOfBoatFromString(boatInfo[2]),
                            Double.parseDouble(boatInfo[1]));
                }
            }
        }
    }
}
