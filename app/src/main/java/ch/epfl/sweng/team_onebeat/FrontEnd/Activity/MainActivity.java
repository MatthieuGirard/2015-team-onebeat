package ch.epfl.sweng.team_onebeat.FrontEnd.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ch.epfl.sweng.team_onebeat.Exceptions.DisplayNotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.ControlerMainActivity;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.State;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.StaticMachine;
import ch.epfl.sweng.team_onebeat.R;


/*
    display the authentification with spotify

 */



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create the controler :
        ControlerMainActivity controler = new ControlerMainActivity(this);

        // now the controler is linked to the machine state : CONNECTION
        StaticMachine.get(StaticMachine.Type.CONNECTION).addObserver(controler);

    }


    public void connectionButton(View view){

        Log.d("mainAct", "click in the button :  connect");

        State tryConnect = State.Factory.provide(StaticMachine.ConnectionState.TRY_CONNECT);
        StaticMachine.get(StaticMachine.Type.CONNECTION).setState(tryConnect);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void display(StaticMachine.ConnectionState state) throws DisplayNotImplementedException {

            switch(  StaticMachine.ConnectionState.values()[ state.ordinal() ]  ){

                case OFF :
                    // new machine state history
                    Log.d("mainAct",  "main activity display off");
                    // TODO : display spotify field for connection
                    /* when the user click to connect update connection state :
                    StaticMachine.update(StaticMachine.Type.CONNECTION,
                            State.Factory.provide(StaticMachine.ConnectionState.TRY_CONNECT);
                    */
                    break;


                case TRY_CONNECT:
                    Log.d("mainAct",  "main activity display try connect");
                    View view = this.getWindow().getDecorView();
                    view.setBackgroundColor(Color.DKGRAY); // for the demo
                    // TODO : pending mode (loading component or blank layout)

                    break;

                case CONNECTED: // nothing to display : controler will change to RoomActivity
                    break;
                default: throw new DisplayNotImplementedException();


            }

        Log.d("mainAct",
                " machine state connection history : " +
                        StaticMachine.get(StaticMachine.Type.CONNECTION).getHistory().toString());

    }





}
