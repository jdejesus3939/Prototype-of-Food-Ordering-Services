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
import android.widget.Spinner;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class StarbucksMainActivity extends Activity {
	
	private CheckBox checkBrewedCoffeeA,
					 checkBrewedCoffeeB,
					 checkBrewedCoffeeC,
					 checkChocolateA,
					 checkChocolateB,
					 checkChocolateC,
					 checkEspressoA,
					 checkEspressoB,
					 checkEspressoC,
					 checkFrappuccinoA,
					 checkFrappuccinoB,
					 checkFrappuccinoC;
	
	private TextView priceBrewedCoffeeA,
					 priceBrewedCoffeeB,
					 priceBrewedCoffeeC,
					 priceChocolateA,
					 priceChocolateB,
					 priceChocolateC,
					 priceEspressoA,
					 priceEspressoB,
					 priceEspressoC,
					 priceFrappuccinoA,
					 priceFrappuccinoB,
					 priceFrappuccinoC,
					 starbucksOrderHistory;
	
	private Spinner spinnerLattes,
					spinnerMacchiatos,
					spinnerCappuccinos;
	
	private Button btnStarbucksProceed,
				   btnStarbucksClearHistory;
	
	private static String SUCCESS = "success";
	private static String ERROR = "error";
	
	int[] starbucksPrice = new int[12];
	String [] starbucksString = new String [15];

	String startvhistory;
	String [] starbucksHistoryString = new String[15];
	
	String checkID = "starbucks";
	String PHPSESSID = null;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.starbucksmain);
		
		Intent myIntent = getIntent();
		PHPSESSID = myIntent.getStringExtra("Set-Cookie");
		//Log.e("Set-Cookie",PHPSESSID);
		
		InfoRetrieved price = new InfoRetrieved();
	    JSONObject json = price.pricelist("starbucks", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    // check for success process
	    try {
	        if (json.getString(SUCCESS) != null) {
	            String res = json.getString(SUCCESS);
	            Log.e("check# res starbucks main is ", res);
	            if(Integer.parseInt(res) == 1){
	            	starbucksPrice[0] = Integer.parseInt(json.getString("drink1A"));
	            	starbucksPrice[1] = Integer.parseInt(json.getString("drink1B"));
	            	starbucksPrice[2] = Integer.parseInt(json.getString("drink1C"));
	            	starbucksPrice[3] = Integer.parseInt(json.getString("drink2A"));
	            	starbucksPrice[4] = Integer.parseInt(json.getString("drink2B"));
	            	starbucksPrice[5] = Integer.parseInt(json.getString("drink2C"));
	            	starbucksPrice[6] = Integer.parseInt(json.getString("drink3A"));
	            	starbucksPrice[7] = Integer.parseInt(json.getString("drink3B"));
	            	starbucksPrice[8] = Integer.parseInt(json.getString("drink3C"));
	            	starbucksPrice[9] = Integer.parseInt(json.getString("drink4A"));
	            	starbucksPrice[10] = Integer.parseInt(json.getString("drink4B"));
	            	starbucksPrice[11] = Integer.parseInt(json.getString("drink4C"));
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
	    JSONObject jsonhist = history.orderhistorylist("starbucks", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    startvhistory = "Your last order with this vendor was:\n";
	    // check for success process
	    try {
	        if (jsonhist.getString(SUCCESS) != null) {
	            String reshist = jsonhist.getString(SUCCESS);
	            Log.e("check# reshist starbucks main is ", reshist);
	            if(Integer.parseInt(reshist) == 1){
	            	starbucksHistoryString[0] = jsonhist.getString("drinkAA");
	            	starbucksHistoryString[1] = jsonhist.getString("drinkAB");
	            	starbucksHistoryString[2] = jsonhist.getString("drinkAC");
	            	starbucksHistoryString[3] = jsonhist.getString("drinkBA");
	            	starbucksHistoryString[4] = jsonhist.getString("drinkBB");
	            	starbucksHistoryString[5] = jsonhist.getString("drinkBC");
	            	starbucksHistoryString[6] = jsonhist.getString("drinkCA");
	            	starbucksHistoryString[7] = jsonhist.getString("drinkCAshot");
	            	starbucksHistoryString[8] = jsonhist.getString("drinkCB");
	            	starbucksHistoryString[9] = jsonhist.getString("drinkCBshot");
	            	starbucksHistoryString[10] = jsonhist.getString("drinkCC");
	            	starbucksHistoryString[11] = jsonhist.getString("drinkCCshot");
	            	starbucksHistoryString[12] = jsonhist.getString("drinkDA");
	            	starbucksHistoryString[13] = jsonhist.getString("drinkDB");
	            	starbucksHistoryString[14] = jsonhist.getString("drinkDC");
	            
		            
		            for(int i=0 ; i<starbucksHistoryString.length; i++)
		            {
		            	if(!starbucksHistoryString[i].equals("No"))
		            	{
		            		if(i == 6 && !starbucksHistoryString[6].equals("No"))
		            		{
		            			startvhistory = startvhistory + starbucksHistoryString[6] + " (" + starbucksHistoryString[7] + ")\n";
		            		}
		            		else if(i == 8 && !starbucksHistoryString[8].equals("No"))
		            		{
		            			startvhistory = startvhistory + starbucksHistoryString[8] + " (" + starbucksHistoryString[9] + ")\n";
		            		}
		            		else if(i == 10 && !starbucksHistoryString[10].equals("No"))
		            		{
		            			startvhistory = startvhistory + starbucksHistoryString[10] + " (" + starbucksHistoryString[11] + ")\n";
		            		}
		            		else if(i == 7)
		            		{
		            			startvhistory = startvhistory;
		            		}
		            		else if(i == 9)
		            		{
		            			startvhistory = startvhistory;
		            		}
		            		else if(i == 11)
		            		{
		            			startvhistory = startvhistory;
		            		}
		            		else
		            		{
		            			startvhistory = startvhistory + starbucksHistoryString[i] + "\n";
		            		}
		            	}
		            }
	            }
	            
	            //if no history are retrieved
	            //can be deleted or a new user
	            if(Integer.parseInt(reshist) == 0){
	            	Log.e("check@starbucks fail-hist-retrived", reshist);
	            	startvhistory = startvhistory + "No history order is retrieved\n";
	            }
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }

	    starbucksOrderHistory = (TextView) findViewById(R.id.starbucksOrderHistory);
	    
	    priceBrewedCoffeeA = (TextView) findViewById(R.id.priceBrewedCoffeeA);
		priceBrewedCoffeeB = (TextView) findViewById(R.id.priceBrewedCoffeeB);
		priceBrewedCoffeeC = (TextView) findViewById(R.id.priceBrewedCoffeeC);
		priceChocolateA = (TextView) findViewById(R.id.priceChocolateA);
		priceChocolateB = (TextView) findViewById(R.id.priceChocolateB);
		priceChocolateC = (TextView) findViewById(R.id.priceChocolateC);
		priceEspressoA = (TextView) findViewById(R.id.priceEspressoA);
		priceEspressoB = (TextView) findViewById(R.id.priceEspressoB);
		priceEspressoC = (TextView) findViewById(R.id.priceEspressoC);
		priceFrappuccinoA = (TextView) findViewById(R.id.priceFrappuccinoA);
		priceFrappuccinoB = (TextView) findViewById(R.id.priceFrappuccinoB);
		priceFrappuccinoC = (TextView) findViewById(R.id.priceFrappuccinoC);
	    
		checkBrewedCoffeeA = (CheckBox) findViewById(R.id.checkBrewedCoffeeA);
		checkBrewedCoffeeB = (CheckBox) findViewById(R.id.checkBrewedCoffeeB);
		checkBrewedCoffeeC = (CheckBox) findViewById(R.id.checkBrewedCoffeeC);
        
		checkChocolateA = (CheckBox) findViewById(R.id.checkChocolateA);
		checkChocolateB = (CheckBox) findViewById(R.id.checkChocolateB);
		checkChocolateC = (CheckBox) findViewById(R.id.checkChocolateC);
        
		checkEspressoA = (CheckBox) findViewById(R.id.checkEspressoA);
		checkEspressoB = (CheckBox) findViewById(R.id.checkEspressoB);
		checkEspressoC = (CheckBox) findViewById(R.id.checkEspressoC);
		
		checkFrappuccinoA = (CheckBox) findViewById(R.id.checkFrappuccinoA);
		checkFrappuccinoB = (CheckBox) findViewById(R.id.checkFrappuccinoB);
		checkFrappuccinoC = (CheckBox) findViewById(R.id.checkFrappuccinoC);
		
		spinnerLattes = (Spinner) findViewById(R.id.spinnerLattes);
		spinnerMacchiatos = (Spinner) findViewById(R.id.spinnerMacchiatos);
		spinnerCappuccinos = (Spinner) findViewById(R.id.spinnerCappuccinos);
		
		starbucksOrderHistory.setText(startvhistory);
		
		priceBrewedCoffeeA.setText("$ " + starbucksPrice[0]);
		priceBrewedCoffeeB.setText("$ " + starbucksPrice[1]);
		priceBrewedCoffeeC.setText("$ " + starbucksPrice[2]);
		priceChocolateA.setText("$ " + starbucksPrice[3]);
		priceChocolateB.setText("$ " + starbucksPrice[4]);
		priceChocolateC.setText("$ " + starbucksPrice[5]);
		priceEspressoA.setText("$ " + starbucksPrice[6]);
		priceEspressoB.setText("$ " + starbucksPrice[7]);
		priceEspressoC.setText("$ " + starbucksPrice[8]);
		priceFrappuccinoA.setText("$ " + starbucksPrice[9]);
		priceFrappuccinoB.setText("$ " + starbucksPrice[10]);
		priceFrappuccinoC.setText("$ " + starbucksPrice[11]);
        		
		btnStarbucksProceed = (Button) findViewById(R.id.btnStarbucksProceed);
		
		btnStarbucksProceed.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//check box, if checked set the string
				if (!checkBrewedCoffeeA.isChecked() && !checkBrewedCoffeeB.isChecked() && !checkBrewedCoffeeC.isChecked() &&
					!checkChocolateA.isChecked() && !checkChocolateB.isChecked() && !checkChocolateC.isChecked() &&
					!checkEspressoA.isChecked() && !checkEspressoB.isChecked() && !checkEspressoC.isChecked() &&
					!checkFrappuccinoA.isChecked() && !checkFrappuccinoB.isChecked() && !checkFrappuccinoC.isChecked())
					{
						AlertDialog.Builder alert = new AlertDialog.Builder(StarbucksMainActivity.this);
			            alert.setTitle("Error"); 
			            alert.setMessage("Select at least one menu");
			            alert.setPositiveButton("OK", null); 
			            alert.show();
					}
					else
					{
						if(checkBrewedCoffeeA.isChecked())
							starbucksString[0] = (String) checkBrewedCoffeeA.getText();
						else starbucksString[0] = "No";
						if(checkBrewedCoffeeB.isChecked())
							starbucksString[1] = (String) checkBrewedCoffeeB.getText();
						else starbucksString[1] = "No";
						if(checkBrewedCoffeeC.isChecked())
							starbucksString[2] = (String) checkBrewedCoffeeC.getText();
						else starbucksString[2] = "No";
						
						if(checkChocolateA.isChecked())
							starbucksString[3] = (String) checkChocolateA.getText();
						else starbucksString[3] = "No";
						if(checkChocolateB.isChecked())
							starbucksString[4] = (String) checkChocolateB.getText();
						else starbucksString[4] = "No";
						if(checkChocolateC.isChecked())
							starbucksString[5] = (String) checkChocolateC.getText();
						else starbucksString[5] = "No";
						
						if(checkEspressoA.isChecked())
						{
							//coffee is selected
							starbucksString[6] = (String) checkEspressoA.getText();
							//number of shot
							starbucksString[7] = String.valueOf(spinnerLattes.getSelectedItem());
							Log.e("spinner latte", starbucksString[7]);
						}
						else
						{
							starbucksString[6] = "No";
							starbucksString[7] = "No";
						}
						if(checkEspressoB.isChecked())
						{
							//coffee is selected
							starbucksString[8] = (String) checkEspressoB.getText();
							//number of shot
							starbucksString[9] = String.valueOf(spinnerMacchiatos.getSelectedItem());
							Log.e("spinner macchiato", starbucksString[9]);
						}
						else
						{
							starbucksString[8] = "No";
							starbucksString[9] = "No";
						}
						if(checkEspressoC.isChecked())
						{
							//coffee is selected
							starbucksString[10] = (String) checkEspressoC.getText();
							//number of shot
							starbucksString[11] = String.valueOf(spinnerCappuccinos.getSelectedItem());
							Log.e("spinner cappuccino", starbucksString[11]);
						}
						else
						{
							starbucksString[10] = "No";
							starbucksString[11] = "No";
						}
						
						if(checkFrappuccinoA.isChecked())
							starbucksString[12] = (String) checkFrappuccinoA.getText();
						else starbucksString[12] = "No";
						if(checkFrappuccinoB.isChecked())
							starbucksString[13] = (String) checkFrappuccinoB.getText();
						else starbucksString[13] = "No";
						if(checkFrappuccinoC.isChecked())
							starbucksString[14] = (String) checkFrappuccinoC.getText();
						else starbucksString[14] = "No";
						
							
						//indicator for checkout activity
						String intentID = "starbucks";
						
						Intent starbucksCheckout = new Intent(getApplicationContext(), CheckoutActivity.class);
						//starbucksCheckout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Bundle extras = new Bundle();
						//putExtra to the Bundle
						extras.putString("intentKey", intentID);
						extras.putString("Set-Cookie", PHPSESSID);
						extras.putStringArray("activityKey", starbucksString);
						extras.putIntArray("price", starbucksPrice);
						starbucksCheckout.putExtras(extras);
						startActivity(starbucksCheckout);
					}
				
			}
			
		});  // end of button listener
		
		
		btnStarbucksClearHistory = (Button) findViewById(R.id.btnStarbucksClearHistory);
		
		btnStarbucksClearHistory.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClearHistory clearHistory = new ClearHistory();
                JSONObject json = clearHistory.clearStarbucks(checkID, PHPSESSID, getApplicationContext());
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
