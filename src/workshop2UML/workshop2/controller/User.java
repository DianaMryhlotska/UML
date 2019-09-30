package workshop2UML.workshop2.controller;

public class User {


    public boolean playGame(workshop2UML.workshop2.view.Console a_view, workshop2UML.workshop2.model.ProgramFacade a_session) {
//        a_view.presentInstructions(!a_session.isGameOver() && a_session.getDealerScore() > 0);


        a_view.collectEvents();

        if (a_view.wantsToStart()) {
            a_session.start();
        } else if (a_view.wantsToCreateMember()) {
            String name = a_view.inputName();
            String personalNum = a_view.inputPesonalNum();
            a_session.createNewMember(name, personalNum);
        }

        return true;
    }
}