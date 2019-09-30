package workshop2UML.workshop2.model;

public class System {

    Boat m_boat;
    Member m_member;

    public System() {
        m_boat = new Boat();
        m_member = new Member();
    }

    public void start() {
        m_member.start();
    }

}
