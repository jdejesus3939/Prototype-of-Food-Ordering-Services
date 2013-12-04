package com.jproject.foodorderingapps;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
 
import com.jproject.foodorderingapps.library.DatabaseHandler;
import com.jproject.foodorderingapps.library.UserFunctions;
 
public class DashboardActivity extends Activity {
    private static UserFunctions userFunctions = null;
    private static DatabaseHandler db;
    
    Button btnLogout;
    TextView welcomeUser;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        /**
         * Dashboard Screen for the application
         * */
        // Check login status in database
        db = DatabaseHandler.getDatabaseHandler(getApplicationContext());
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
       // user already logged in show dashboard
            
            // welcome user name
            welcomeUser = (TextView) findViewById(R.id.tvWelcomeUser);
            String user = db.getUserName();
            
            welcomeUser.setText(user);
            
            btnLogout = (Button) findViewById(R.id.btnLogout);
 
            btnLogout.setOnClickListener(new View.OnClickListener() {
 
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    userFunctions.logoutUser(getApplicationContext());

    				Intent login = new Intent(getApplicationContext(), LoginActivity.class);
    		        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		        startActivity(login);                    
                }
            });
 
        }else{
        	btnLogout = (Button) findViewById(R.id.btnLogout);
        	 
            btnLogout.setOnClickListener(new View.OnClickListener() {
 
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                }
            });
        }
        
        
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Check login status in database
        db = DatabaseHandler.getDatabaseHandler(getApplicationContext());
        //userFunctions = new UserFunctions();

        welcomeUser = (TextView) findViewById(R.id.tvWelcomeUser);

        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        // user already logged in
            String user = db.getUserName();
            welcomeUser.setText(user);
        }
        else
        {
        	welcomeUser.setText("");
        }
        
        //db.close();
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		// Check login status in database
        db = DatabaseHandler.getDatabaseHandler(getApplicationContext());
        //userFunctions = new UserFunctions();

        welcomeUser = (TextView) findViewById(R.id.tvWelcomeUser);

        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        // user already logged in
            String user = db.getUserName();
            welcomeUser.setText(user);
        }
        else
        {
        	welcomeUser.setText("");
        }
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		// Check login status in database
        db = DatabaseHandler.getDatabaseHandler(getApplicationContext());
        //userFunctions = new UserFunctions();

        welcomeUser = (TextView) findViewById(R.id.tvWelcomeUser);

        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        // user already logged in
            String user = db.getUserName();
            welcomeUser.setText(user);
        }
        else
        {
        	welcomeUser.setText("");
        }
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		db.close();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		db.close();
	}


}