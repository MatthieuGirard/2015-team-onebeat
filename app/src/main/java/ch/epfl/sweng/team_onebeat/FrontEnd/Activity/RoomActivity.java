package ch.epfl.sweng.team_onebeat.FrontEnd.Activity;

import ch.epfl.sweng.team_onebeat.Exceptions.DisplayNotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.StaticMachine;

/*
    display the rooms

 */

public class RoomActivity {




    public void display(StaticMachine.RoomState state) throws DisplayNotImplementedException {

        switch( state ){
            case INITIAL :
                // TODO : basic design without room information

            case PERSONAL :
                // TODO (optional) : display registered room
                 /* when the user click to the button search :
                    StaticMachine.update(StaticMachine.Type.ROOM,
                            State.Factory.provide(StaticMachine.RoomState.SEARCH);
                    when the user click on a room :
                    StaticMachine.update(StaticMachine.Type.ACTIVITY,
                            State.Factory.provide(StaticMachine.ActivityState.PLAYLIST);
                    */

                break;

            case PENDING:
                // TODO : pending mode (loading component or blank layout)

                break;


            case SEARCH :
                // TODO (optional) : search on a room :
                /* StaticMachine.update(StaticMachine.Type.ROOM,
                        State.Factory.provide(StaticMachine.RoomState.SEARCH);
                        */
                break;

            default: throw new DisplayNotImplementedException();


        }
    }


}
