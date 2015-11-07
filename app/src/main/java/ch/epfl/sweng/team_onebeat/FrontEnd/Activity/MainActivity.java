package ch.epfl.sweng.team_onebeat.FrontEnd.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ch.epfl.sweng.team_onebeat.Exceptions.DisplayNotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.StaticMachine;


/*
    display the authentification with spotify

 */



public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    // TODO : display spotify field for connection
                    /* when the user click to connect update connection state :
                    StaticMachine.update(StaticMachine.Type.CONNECTION,
                            State.Factory.provide(StaticMachine.ConnectionState.TRY_CONNECT);
                    */
                    break;


                case TRY_CONNECT:
                    // TODO : pending mode (loading component or blank layout)

                    break;

                case CONNECTED:
                    break;
                default: throw new DisplayNotImplementedException();


            }
        }





}
