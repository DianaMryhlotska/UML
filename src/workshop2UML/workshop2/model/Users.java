package workshop2UML.workshop2.model;

import java.util.HashMap;

public class Users {
    private HashMap<Integer, String> users;

    public Users() {
        this.users = new HashMap<Integer, String>();
        users.put(0, "root");
    }

    public HashMap<Integer, String> getUsers() {
        return users;
    }

    public void addAccess(int ID, String password) {
        users.put(ID, password);
    }

    public boolean userExist(int ID) {
        return users.containsKey(ID);
    }

    public String getUser(int ID) {
        return users.get(ID);
    }
}
