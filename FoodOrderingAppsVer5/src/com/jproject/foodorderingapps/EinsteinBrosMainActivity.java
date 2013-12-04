package com.jproject.foodorderingapps;

import org.json.JSONException;
import org.json.JSONObject;

import com.jproject.foodorderingapps.library.ClearHistory;
import com.jproject.foodorderingapps.library.InfoRetrieved;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class EinsteinBrosMainActivity extends Activity {
	
	private CheckBox checkBagelA, checkBagelB, checkBagelC,
					 checkSandwichA, checkSandwichB, checkSandwichC,
					 checkBrownieA, checkBrownieB, checkBrownieC,
					 checkMuffinA, checkMuffinB, checkMuffinC;
	
	private TextView priceBagelA, priceBagelB, priceBagelC,
					 priceSandwichA, priceSandwichB, priceSandwichC,
					 priceBrownieA, priceBrownieB, priceBrownieC,
					 priceMuffinA, priceMuffinB, priceMuffinC,
					 einsteinOrderHistory;
	
	private Button btnEinsteinBrosProceed,
				   btnEinsteinBrosClearHistory;
	
	private static String SUCCESS = "success";
	private static String ERROR = "error";
	
	int[] einsteinPrice = new int[12];
	String [] einsteinString = new String [12];
	String einstvhistory;
	String [] einsteinHistoryString = new String[12];
	String checkID = "einstein";
	String PHPSESSID = null;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.einsteinbrosmain);
		
		Intent myIntent = getIntent();
		PHPSESSID = myIntent.getStringExtra("Set-Cookie");
		//Log.e("Set-Cookie",PHPSESSID);
		
		//price retrieval
		InfoRetrieved price = new InfoRetrieved();
	    JSONObject json = price.pricelist("einstein", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    // check for success process
	    try {
	        if (json.getString(SUCCESS) != null) {
	            String res = json.getString(SUCCESS);
	            Log.e("check# res einstein main is ", res);
	            if(Integer.parseInt(res) == 1){
	            	einsteinPrice[0] = Integer.parseInt(json.getString("bagelA"));
	            	einsteinPrice[1] = Integer.parseInt(json.getString("bagelB"));
	            	einsteinPrice[2] = Integer.parseInt(json.getString("bagelC"));
	            	einsteinPrice[3] = Integer.parseInt(json.getString("sandwichA"));
	            	einsteinPrice[4] = Integer.parseInt(json.getString("sandwichB"));
	            	einsteinPrice[5] = Integer.parseInt(json.getString("sandwichC"));
	            	einsteinPrice[6] = Integer.parseInt(json.getString("brownieA"));
	            	einsteinPrice[7] = Integer.parseInt(json.getString("brownieB"));
	            	einsteinPrice[8] = Integer.parseInt(json.getString("brownieC"));
	            	einsteinPrice[9] = Integer.parseInt(json.getString("muffinA"));
	            	einsteinPrice[10] = Integer.parseInt(json.getString("muffinB"));
	            	einsteinPrice[11] = Integer.parseInt(json.getString("muffinC"));
	            }
	        }
	        
	        if (json.getString(ERROR) != null) {
                String res1 = json.getString(ERROR);
                Log.e("check# res1 on pricing is ", res1);
                if (Integer.parseInt(res1) == 2){
                	Toast toast = Toast.makeText(getApplicationContext(), "Insufficient balance, please check sanddollar office", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (Integer.parseInt(res1) == 3){
                	Toast toast = Toast.makeText(getApplicationContext(), "User is not found, please check sanddollar office", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (Integer.parseInt(res1) == 4){
                	Log.e("check# error_msg is 4", json.toString());
                }
                if (Integer.parseInt(res1) == 5) {
                	Toast toast = Toast.makeText(getApplicationContext(), "Your session is expired.\nPlease re-login.", Toast.LENGTH_LONG);
                    toast.show();
                    // call main activity
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (Integer.parseInt(res1) == 6) {
                	Toast toast = Toast.makeText(getApplicationContext(), "You are not allowed\nto make a double order\nPlease re-login.", Toast.LENGTH_LONG);
                    toast.show();
                    //call main activity
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	    //history retrieval
	    InfoRetrieved history = new InfoRetrieved();
	    JSONObject jsonhist = history.orderhistorylist("einstein", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    einstvhistory = "Your last order with this vendor was:\n";
	    // check for success process
	    try {
	        if (jsonhist.getString(SUCCESS) != null) {
	            String reshist = jsonhist.getString(SUCCESS);
	            Log.e("check# reshist einstein main is ", reshist);
	            if(Integer.parseInt(reshist) == 1){
	            	einsteinHistoryString[0] = jsonhist.getString("bagelA");
	            	einsteinHistoryString[1] = jsonhist.getString("bagelB");
	            	einsteinHistoryString[2] = jsonhist.getString("bagelC");
	            	einsteinHistoryString[3] = jsonhist.getString("sandwichA");
	            	einsteinHistoryString[4] = jsonhist.getString("sandwichB");
	            	einsteinHistoryString[5] = jsonhist.getString("sandwichC");
	            	einsteinHistoryString[6] = jsonhist.getString("brownieA");
	            	einsteinHistoryString[7] = jsonhist.getString("brownieB");
	            	einsteinHistoryString[8] = jsonhist.getString("brownieC");
	            	einsteinHistoryString[9] = jsonhist.getString("muffinA");
	            	einsteinHistoryString[10] = jsonhist.getString("muffinB");
	            	einsteinHistoryString[11] = jsonhist.getString("muffinC");
	            
		            
		            for(int i=0 ; i<einsteinHistoryString.length; i++)
		            {
		            	if(!einsteinHistoryString[i].equals("No"))
		            	{
		            		einstvhistory = einstvhistory + einsteinHistoryString[i] + "\n";
		            	}
		            }
	            }
	            
	            if(Integer.parseInt(reshist) == 0){
	            	Log.e("check@einstein fail-hist-retrived", reshist);
	            	einstvhistory = einstvhistory + "No history order is retrieved\n";
	            }
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	    einsteinOrderHistory = (TextView) findViewById(R.id.einsteinOrderHistory);
	    
	    priceBagelA = (TextView) findViewById(R.id.priceBagelA);
	    priceBagelB = (TextView) findViewById(R.id.priceBagelB);
	    priceBagelC = (TextView) findViewById(R.id.priceBagelC);
		priceSandwichA = (TextView) findViewById(R.id.priceSandwichA);
		priceSandwichB = (TextView) findViewById(R.id.priceSandwichB);
		priceSandwichC = (TextView) findViewById(R.id.priceSandwichC);
		priceBrownieA = (TextView) findViewById(R.id.priceBrownieA);
		priceBrownieB = (TextView) findViewById(R.id.priceBrownieB);
		priceBrownieC = (TextView) findViewById(R.id.priceBrownieC);
		priceMuffinA = (TextView) findViewById(R.id.priceMuffinA);
		priceMuffinB = (TextView) findViewById(R.id.priceMuffinB);
		priceMuffinC = (TextView) findViewById(R.id.priceMuffinC);
	    
		checkBagelA = (CheckBox) findViewById(R.id.checkBagelA);
		checkBagelB = (CheckBox) findViewById(R.id.checkBagelB);
		checkBagelC = (CheckBox) findViewById(R.id.checkBagelC);
		
        checkSandwichA = (CheckBox) findViewById(R.id.checkSandwichA);
        checkSandwichB = (CheckBox) findViewById(R.id.checkSandwichB);
        checkSandwichC = (CheckBox) findViewById(R.id.checkSandwichC);
        
        checkBrownieA = (CheckBox) findViewById(R.id.checkBrownieA);
        checkBrownieB = (CheckBox) findViewById(R.id.checkBrownieB);
        checkBrownieC = (CheckBox) findViewById(R.id.checkBrownieC);
        
        checkMuffinA = (CheckBox) findViewById(R.id.checkMuffinA);
        checkMuffinB = (CheckBox) findViewById(R.id.checkMuffinB);
        checkMuffinC = (CheckBox) findViewById(R.id.checkMuffinC);
        
        einsteinOrderHistory.setText(einstvhistory);
        
        priceBagelA.setText("$ " + einsteinPrice[0]);
	    priceBagelB.setText("$ " + einsteinPrice[1]);
	    priceBagelC.setText("$ " + einsteinPrice[2]);
		priceSandwichA.setText("$ " + einsteinPrice[3]);
		priceSandwichB.setText("$ " + einsteinPrice[4]);
		priceSandwichC.setText("$ " + einsteinPrice[5]);
		priceBrownieA.setText("$ " + einsteinPrice[6]);
		priceBrownieB.setText("$ " + einsteinPrice[7]);
		priceBrownieC.setText("$ " + einsteinPrice[8]);
		priceMuffinA.setText("$ " + einsteinPrice[9]);
		priceMuffinB.setText("$ " + einsteinPrice[10]);
		priceMuffinC.setText("$ " + einsteinPrice[11]);
        
		btnEinsteinBrosProceed = (Button) findViewById(R.id.btnEinsteinBrosProceed);
		
		btnEinsteinBrosProceed.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//check box, if checked set the string
				if (!checkBagelA.isChecked() && !checkBagelB.isChecked() && !checkBagelC.isChecked() &&
					!checkSandwichA.isChecked() && !checkSandwichB.isChecked() && !checkSandwichC.isChecked() &&
					!checkBrownieA.isChecked() && !checkBrownieB.isChecked() && !checkBrownieC.isChecked() &&
					!checkMuffinA.isChecked() && !checkMuffinB.isChecked() && !checkMuffinC.isChecked())
				{
					AlertDialog.Builder alert = new AlertDialog.Builder(EinsteinBrosMainActivity.this);
	                alert.setTitle("Error"); 
	                alert.setMessage("Select at least one menu");
	                alert.setPositiveButton("OK", null); 
	                alert.show();
				}
				else
				{
					if(checkBagelA.isChecked())
						einsteinString[0] = (String) checkBagelA.getText();
					else einsteinString[0] = "No";
					if(checkBagelB.isChecked())
						einsteinString[1] = (String) checkBagelB.getText();
					else einsteinString[1] = "No";
					if(checkBagelC.isChecked())
						einsteinString[2] = (String) checkBagelC.getText();
					else einsteinString[2] = "No";
					
					if(checkSandwichA.isChecked())
						einsteinString[3] = (String) checkSandwichA.getText();
					else einsteinString[3] = "No";
					if(checkSandwichB.isChecked())
						einsteinString[4] = (String) checkSandwichB.getText();
					else einsteinString[4] = "No";
					if(checkSandwichC.isChecked())
						einsteinString[5] = (String) checkSandwichC.getText();
					else einsteinString[5] = "No";
					
					if(checkBrownieA.isChecked())
						einsteinString[6] = (String) checkBrownieA.getText();
					else einsteinString[6] = "No";
					if(checkBrownieB.isChecked())
						einsteinString[7] = (String) checkBrownieB.getText();
					else einsteinString[7] = "No";
					if(checkBrownieC.isChecked())
						einsteinString[8] = (String) checkBrownieC.getText();
					else einsteinString[8] = "No";
					
					if(checkMuffinA.isChecked())
						einsteinString[9] = (String) checkMuffinA.getText();
					else einsteinString[9] = "No";
					if(checkMuffinB.isChecked())
						einsteinString[10] = (String) checkMuffinB.getText();
					else einsteinString[10] = "No";
					if(checkMuffinC.isChecked())
						einsteinString[11] = (String) checkMuffinC.getText();
					else einsteinString[11] = "No";
					
					
					
					//indicator for checkout activity
					String intentID = "einstein";
					
					Intent einsteinCheckout = new Intent(getApplicationContext(), CheckoutActivity.class);
					//einsteinCheckout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					Bundle extras = new Bundle();
					//putExtra to the Bundle
					extras.putString("intentKey", intentID);
					extras.putString("Set-Cookie", PHPSESSID);
					extras.putStringArray("activityKey", einsteinString);
					extras.putIntArray("price", einsteinPrice);
					einsteinCheckout.putExtras(extras);
					startActivity(einsteinCheckout);
				}
				
			}
			
		});
		
		
		btnEinsteinBrosClearHistory = (Button) findViewById(R.id.btnEinsteinBrosClearHistory);
		
		btnEinsteinBrosClearHistory.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClearHistory clearHistory = new ClearHistory();
                JSONObject json = clearHistory.clearEinsteinBros(checkID, PHPSESSID, getApplicationContext());
                //Log.e("check# here @checkoutActivity", subjson.toString());
                // check for success process
                try {
                    if (json.getString(SUCCESS) != null) {
                        String res = json.getString(SUCCESS);
                        Log.e("check# res is ", res);
                        if(Integer.parseInt(res) == 1){
                        	Intent intent = getIntent();
                            overridePendingTransition(0, 0);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            finish();

                            overridePendingTransition(0, 0);
                            startActivity(intent);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                
                // check for error process
                try {
                    if (json.getString(ERROR) != null) {
                        String res1 = json.getString(ERROR);
                        Log.e("check# res1 is ", res1);
                        if (Integer.parseInt(res1) == 2){
                        	Toast toast = Toast.makeText(getApplicationContext(), "Insufficient balance, please check sanddollar office", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        if (Integer.parseInt(res1) == 3){
                        	Toast toast = Toast.makeText(getApplicationContext(), "User is not found, please check sanddollar office", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        if (Integer.parseInt(res1) == 4){
                        	Log.e("check# error_msg is 4", json.toString());
                        }
                        if (Integer.parseInt(res1) == 5) {
                        	Toast toast = Toast.makeText(getApplicationContext(), "Your session is expired.\nPlease re-login.", Toast.LENGTH_LONG);
                            toast.show();
                            //call main activity
                            Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                        if (Integer.parseInt(res1) == 6) {
                        	Toast toast = Toast.makeText(getApplicationContext(), "You are not allowed\nto make a double order\nPlease re-login.", Toast.LENGTH_LONG);
                            toast.show();
                            //call main activity
                            Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                        if (Integer.parseInt(res1) == 7){
                        	Toast toast = Toast.makeText(getApplicationContext(), "User is not found.\nCannot delete the history.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                
			}
			
		});
		
	}

}
