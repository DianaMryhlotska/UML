package workshop2UML.workshop2.view;

public class Console {

    private int m_input;

    protected int getInputChar() {
        try {
            int c = System.in.read();
            while (c == '\r' || c =='\n') {
                c = System.in.read();
            }
            return c;
        } catch (java.io.IOException e) {
            System.out.println("" + e);
            return 0;
        }
    }

    public void presentInstructions(boolean a_ProgramStarted) {
        System.out.println("=======================================================");
        System.out.println("Welcome to the yacht club “The jolly pirate”");
        if (a_ProgramStarted) {
            System.out.println("To create a member press c, " +
                    "\nto delete member press d, " +
                    "\nto show a compact list press l\n" +
                    "to show a verbose list of members press v\n" +
                    "to quit press q");
        } else {
            System.out.println("p to start, q to quit");
        }
        System.out.println("-------------------------------------------------------");
    }

    public void collectEvents() {
        m_input = getInputChar();
    }

    public boolean wantsToStart() {
        return m_input == 'p';
    }

    public boolean wantsToCreateMember() {
        return m_input == 'c';
    }

    public boolean wantsToDeleteMember() {
        return m_input == 'd';
    }

    public boolean wantsToShowCompact() {
        return m_input == 'l';
    }

    public boolean wantsToShowVerbose() {
        return m_input == 'v';
    }

    public boolean wantsToQuit() {
        return m_input == 'q';
    }
}
