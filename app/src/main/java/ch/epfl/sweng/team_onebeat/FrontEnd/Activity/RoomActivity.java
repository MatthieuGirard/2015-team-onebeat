package ch.epfl.sweng.team_onebeat.FrontEnd.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ch.epfl.sweng.team_onebeat.Exceptions.DisplayNotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.StaticMachine;
import ch.epfl.sweng.team_onebeat.R;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }




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
