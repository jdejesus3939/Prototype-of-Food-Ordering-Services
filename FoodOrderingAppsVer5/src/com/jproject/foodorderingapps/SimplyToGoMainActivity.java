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

public class SimplyToGoMainActivity extends Activity {
	
	private CheckBox checkSandwichA, checkSandwichB, checkSandwichC,
					 checkSnackA, checkSnackB, checkSnackC,
					 checkDrinkA, checkDrinkB, checkDrinkC;
	
	private TextView priceSandwichA, priceSandwichB, priceSandwichC,
					 priceSnackA, priceSnackB, priceSnackC,
					 priceDrinkA, priceDrinkB, priceDrinkC,
					 simplytogoOrderHistory;
	
	private Button btnSimplyToGoProceed,
				   btnSimplyToGoClearHistory;
	
	private static String SUCCESS = "success";
	private static String ERROR = "error";
	
	int[] simplytogoPrice = new int[9];
	String [] simplytogoString = new String [9];
	String simplytvhistory;
	String [] simplytogoHistoryString = new String[9];
	String checkID = "simplytogo";
	String PHPSESSID = null;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplytogomain);
		
		Intent myIntent = getIntent();
		PHPSESSID = myIntent.getStringExtra("Set-Cookie");
		//Log.e("Set-Cookie",PHPSESSID);
		
		InfoRetrieved price = new InfoRetrieved();
	    JSONObject json = price.pricelist("simplytogo", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    // check for success process
	    try {
	        if (json.getString(SUCCESS) != null) {
	            String res = json.getString(SUCCESS);
	            Log.e("check# res simply togo main is ", res);
	            if(Integer.parseInt(res) == 1){
	            	simplytogoPrice[0] = Integer.parseInt(json.getString("sandwichA"));
	            	simplytogoPrice[1] = Integer.parseInt(json.getString("sandwichB"));
	            	simplytogoPrice[2] = Integer.parseInt(json.getString("sandwichC"));
	            	simplytogoPrice[3] = Integer.parseInt(json.getString("snackA"));
	            	simplytogoPrice[4] = Integer.parseInt(json.getString("snackB"));
	            	simplytogoPrice[5] = Integer.parseInt(json.getString("snackC"));
	            	simplytogoPrice[6] = Integer.parseInt(json.getString("drinkA"));
	            	simplytogoPrice[7] = Integer.parseInt(json.getString("drinkB"));
	            	simplytogoPrice[8] = Integer.parseInt(json.getString("drinkC"));
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
	    
	    InfoRetrieved history = new InfoRetrieved();
	    JSONObject jsonhist = history.orderhistorylist("simplytogo", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    simplytvhistory = "Your last order with this vendor was:\n";
	    // check for success process
	    try {
	        if (jsonhist.getString(SUCCESS) != null) {
	            String reshist = jsonhist.getString(SUCCESS);
	            Log.e("check# reshist simplytogo main is ", reshist);
	            if(Integer.parseInt(reshist) == 1){
	            	simplytogoHistoryString[0] = jsonhist.getString("sandwichA");
	            	simplytogoHistoryString[1] = jsonhist.getString("sandwichB");
	            	simplytogoHistoryString[2] = jsonhist.getString("sandwichC");
	            	simplytogoHistoryString[3] = jsonhist.getString("snackA");
	            	simplytogoHistoryString[4] = jsonhist.getString("snackB");
	            	simplytogoHistoryString[5] = jsonhist.getString("snackC");
	            	simplytogoHistoryString[6] = jsonhist.getString("drinkA");
	            	simplytogoHistoryString[7] = jsonhist.getString("drinkB");
	            	simplytogoHistoryString[8] = jsonhist.getString("drinkC");
	            
		            
		            for(int i=0 ; i<simplytogoHistoryString.length; i++)
		            {
		            	if(!simplytogoHistoryString[i].equals("No"))
		            	{
		            		simplytvhistory = simplytvhistory + simplytogoHistoryString[i] + "\n";
		            	}
		            }
	            }
	            
	            if(Integer.parseInt(reshist) == 0){
	            	Log.e("check@simplytogo fail-hist-retrived", reshist);
	            	simplytvhistory = simplytvhistory + "No history order is retrieved\n";
	            }
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	    simplytogoOrderHistory = (TextView) findViewById(R.id.simplytogoOrderHistory);
	    
	    priceSandwichA = (TextView) findViewById(R.id.priceSandwichA);
	    priceSandwichB = (TextView) findViewById(R.id.priceSandwichB);
	    priceSandwichC = (TextView) findViewById(R.id.priceSandwichC);
		priceSnackA = (TextView) findViewById(R.id.priceSnackA);
		priceSnackB = (TextView) findViewById(R.id.priceSnackB);
		priceSnackC = (TextView) findViewById(R.id.priceSnackC);
		priceDrinkA = (TextView) findViewById(R.id.priceDrinkA);
		priceDrinkB = (TextView) findViewById(R.id.priceDrinkB);
		priceDrinkC = (TextView) findViewById(R.id.priceDrinkC);
	    
        checkSandwichA = (CheckBox) findViewById(R.id.checkSandwichA);
        checkSandwichB = (CheckBox) findViewById(R.id.checkSandwichB);
        checkSandwichC = (CheckBox) findViewById(R.id.checkSandwichC);
        
        checkSnackA = (CheckBox) findViewById(R.id.checkSnackA);
        checkSnackB = (CheckBox) findViewById(R.id.checkSnackB);
        checkSnackC = (CheckBox) findViewById(R.id.checkSnackC);
        
        checkDrinkA = (CheckBox) findViewById(R.id.checkDrinkA);
        checkDrinkB = (CheckBox) findViewById(R.id.checkDrinkB);
        checkDrinkC = (CheckBox) findViewById(R.id.checkDrinkC);
        
        simplytogoOrderHistory.setText(simplytvhistory);
        
        priceSandwichA.setText("$ " + simplytogoPrice[0]);
        priceSandwichB.setText("$ " + simplytogoPrice[1]);
        priceSandwichC.setText("$ " + simplytogoPrice[2]);
		priceSnackA.setText("$ " + simplytogoPrice[3]);
		priceSnackB.setText("$ " + simplytogoPrice[4]);
		priceSnackC.setText("$ " + simplytogoPrice[5]);
		priceDrinkA.setText("$ " + simplytogoPrice[6]);
		priceDrinkB.setText("$ " + simplytogoPrice[7]);
		priceDrinkC.setText("$ " + simplytogoPrice[8]);
        		
		btnSimplyToGoProceed = (Button) findViewById(R.id.btnSimplyToGoProceed);
		
		btnSimplyToGoProceed.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//check box, if checked set the string
				if (!checkSandwichA.isChecked() && !checkSandwichB.isChecked() && !checkSandwichC.isChecked() &&
					!checkSnackA.isChecked() && !checkSnackB.isChecked() && !checkSnackC.isChecked() &&
					!checkDrinkA.isChecked() && !checkDrinkB.isChecked() && !checkDrinkC.isChecked())
					{
						AlertDialog.Builder alert = new AlertDialog.Builder(SimplyToGoMainActivity.this);
		                alert.setTitle("Error"); 
		                alert.setMessage("Select at least one menu");
		                alert.setPositiveButton("OK", null); 
		                alert.show();
					}
					else
					{
						if(checkSandwichA.isChecked())
							simplytogoString[0] = (String) checkSandwichA.getText();
						else simplytogoString[0] = "No";
						if(checkSandwichB.isChecked())
							simplytogoString[1] = (String) checkSandwichB.getText();
						else simplytogoString[1] = "No";
						if(checkSandwichC.isChecked())
							simplytogoString[2] = (String) checkSandwichC.getText();
						else simplytogoString[2] = "No";
						
						if(checkSnackA.isChecked())
							simplytogoString[3] = (String) checkSnackA.getText();
						else simplytogoString[3] = "No";
						if(checkSnackB.isChecked())
							simplytogoString[4] = (String) checkSnackB.getText();
						else simplytogoString[4] = "No";
						if(checkSnackC.isChecked())
							simplytogoString[5] = (String) checkSnackC.getText();
						else simplytogoString[5] = "No";
						
						if(checkDrinkA.isChecked())
							simplytogoString[6] = (String) checkDrinkA.getText();
						else simplytogoString[6] = "No";
						if(checkDrinkB.isChecked())
							simplytogoString[7] = (String) checkDrinkB.getText();
						else simplytogoString[7] = "No";
						if(checkDrinkC.isChecked())
							simplytogoString[8] = (String) checkDrinkC.getText();
						else simplytogoString[8] = "No";
						
						
						
						//indicator for checkout activity
						String intentID = "simplytogo";
						
						Intent simplyCheckout = new Intent(getApplicationContext(), CheckoutActivity.class);
						//simplyCheckout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Bundle extras = new Bundle();
						//putExtra to the Bundle
						extras.putString("intentKey", intentID);
						extras.putString("Set-Cookie", PHPSESSID);
						extras.putStringArray("activityKey", simplytogoString);
						extras.putIntArray("price", simplytogoPrice);
						simplyCheckout.putExtras(extras);
						startActivity(simplyCheckout);
					}
				
			}
			
		});
		
		
		btnSimplyToGoClearHistory = (Button) findViewById(R.id.btnSimplyToGoClearHistory);
		
		btnSimplyToGoClearHistory.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClearHistory clearHistory = new ClearHistory();
                JSONObject json = clearHistory.clearSimplyToGo(checkID, PHPSESSID, getApplicationContext());
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
