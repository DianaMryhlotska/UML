package workshop2UML.workshop2.controller;

import workshop2UML.workshop2.model.ProgramFacade;
import workshop2UML.workshop2.view.Console;
<<<<<<< HEAD
import workshop2UML.workshop2.model.TheSystem;
public class User {

    public boolean Start(Console a_view, TheSystem a_session) {
=======


public class User {


    public boolean playGame(Console a_view, ProgramFacade a_session) {
>>>>>>> fa73619074b94b98000e3dfe7e9b4eb198f73b7d
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