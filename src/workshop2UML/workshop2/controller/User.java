package workshop2UML.workshop2.controller;

import workshop2UML.workshop2.view.Console;

// TODO : Controls interactions between Models and View !
// TODO : Review all !

public class User {

    public boolean Start(Console a_view, ClubSystem a_session) {

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