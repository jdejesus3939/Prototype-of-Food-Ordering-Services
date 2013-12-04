package com.jproject.foodorderingapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
 
import com.jproject.foodorderingapps.library.DatabaseHandler;
import com.jproject.foodorderingapps.library.UserFunctions;
 
public class ThankYouActivity extends Activity {
	private static DatabaseHandler db;
    UserFunctions userFunctions;
    Button btnLogout;
    Button btnHallway;
    TextView welcomeUser, tvTotal, tvLocation;

    String EINSTEIN = "einstein",
			SIMPLY = "simplytogo",
			STARBUCK = "starbucks",
			SUBWAY = "subway",
			BAJA = "baja",
			UC = "uc";
    
    String PHPSESSID = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Check login status in database
        db = DatabaseHandler.getDatabaseHandler(getApplicationContext());
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
       // user already logged in show databoard
            setContentView(R.layout.thankyou);
            
            welcomeUser = (TextView) findViewById(R.id.tvWelcomeUser);
            tvTotal= (TextView) findViewById(R.id.tvTYTotal);
            tvLocation = (TextView) findViewById(R.id.tvLocation);
            
            // welcome user name
            String user = db.getUserName();
            welcomeUser.setText(user);
            
            Intent i = getIntent();
            Bundle extras = i.getExtras();
            // getting attached intent data
            String total = extras.getString("total");
            String location = extras.getString("id");
            PHPSESSID = extras.getString("Set-Cookie");
            // displaying selected product name
            tvTotal.setText("Your total is $ " + total);
            
            if(location.equals(EINSTEIN))
            	tvLocation.setText("Please pick up your order at\nIsland Hall");
            if(location.equals(SIMPLY))
            	tvLocation.setText("Please pick up your order at\nBay Hall");
            if(location.equals(STARBUCK))
            	tvLocation.setText("Please pick up your order at\nFaculty Hall");
            if(location.equals(SUBWAY))
            	tvLocation.setText("Please pick up your order at\nMary and Jeff Bell Library");
            if(location.equals(BAJA))
            	tvLocation.setText("Please pick up your order at\nCenter for Instruction Learning");
            if(location.equals(UC))
            	tvLocation.setText("Please pick up your order at\nThe University Center\n\nNote: if you have classics order from the University Center, then you need to make a payment to the cashier");
            
            btnLogout = (Button) findViewById(R.id.btnTYLogout);
            btnHallway = (Button) findViewById(R.id.btnTYHallway);
 
            btnLogout.setOnClickListener(new View.OnClickListener() {
 
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    userFunctions.logoutUser(getApplicationContext());
                    Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    // Closing dashboard screen
                    //finish();
                }
            });
            
            btnHallway.setOnClickListener(new View.OnClickListener() {
            	 
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    Intent hallway = new Intent(getApplicationContext(), HallwayActivity.class);
                    hallway.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    hallway.putExtra("Set-Cookie", PHPSESSID);
                    startActivity(hallway);
                    // Closing dashboard screen
                    //finish();
                }
            });
 
        }else{
            // user is not logged in show login screen
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            // Closing dashboard screen
            finish();
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}
	
	
	
}