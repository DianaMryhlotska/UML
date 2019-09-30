package workshop2UML.workshop2.model;

<<<<<<< HEAD:src/workshop2UML/workshop2/model/System.java
public class System {
=======
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
>>>>>>> 15b6995a5efff4db8d84d9517323965fd034a984:src/workshop2UML/workshop2/model/ProgramFacade.java
}
