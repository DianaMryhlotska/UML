package workshop2UML.workshop2.model;

public class TheSystem {

    Boat m_boat;
    Member m_member;

    public TheSystem() {
        m_boat = new Boat();
        m_member = new Member();
    }

    public void start() {
        m_member.start();
    }

    public Member createNewMember(String name, String personalNum) {
        m_member.createMember(name, personalNum);
    }

}
