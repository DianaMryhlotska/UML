package workshop2UML.workshop2.controller;

import workshop2UML.workshop2.model.TheSystem;
import workshop2UML.workshop2.view.Console;
public class User {

    public boolean Start(Console a_view, TheSystem a_session) {

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