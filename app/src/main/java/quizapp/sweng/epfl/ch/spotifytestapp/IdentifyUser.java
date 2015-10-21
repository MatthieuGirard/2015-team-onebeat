package quizapp.sweng.epfl.ch.spotifytestapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

/**
 * Created by Karim on 21-Oct-15.
 *
 * The following class will be used to identify a user. We will identify a user via their Android
 * email/account so that they can use the service across all of their devices.
 */
public class IdentifyUser {

    //@TODO Make this Asynchronous when we display a loading screen

    /**
     * The context provided needs to be a long term one (for the whole app lifecycle) provided by
     * getApplicationContext(), which requires an instance of the Activity object
     * @param context
     * @return The master account of provided by the user.
     */
    public String getAccount(Context context) {
        AccountManager manager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        Account[] accounts = manager.getAccounts();

        String masterAccount;

        for (Account account : accounts) {
            Log.d("KEINFO", account.type);
            // A google account should by of type com.google but I am not sure
            if (account.type.equalsIgnoreCase("com.google")) {
                masterAccount = account.name;
                return masterAccount;
            }
        }
        return null;
    }
}
