package workshop2UML.workshop2.model;

public class ProgramFacade {

    Boat m_boat;
    Member m_member;

    public ProgramFacade() {
        m_boat = new Boat();
        m_member = new Member();
    }

    public void start() {
        m_member.start();
    }
}
