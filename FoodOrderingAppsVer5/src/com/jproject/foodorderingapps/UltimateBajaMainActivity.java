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

public class UltimateBajaMainActivity extends Activity {

	private CheckBox checkNacho,
					 checkTacos,
					 checkQuesadilla,
					 checkBurrito,
					 checkGroundBeef,
					 checkSteak,
					 checkChicken,
					 checkPork,
					 checkRice,
					 checkBeans,
					 checkLettuce,
					 checkTomatoes,
					 checkRedOnion,
					 checkCucumber,
					 checkSalsa,
					 checkPicoDeGallo,
					 checkCreamCheese;

	private TextView priceNacho,
					 priceTacos,
					 priceQuesadilla,
					 priceBurrito,
					 priceGroundBeef,
					 priceSteak,
					 priceChicken,
					 pricePork,
					 bajaOrderHistory;
	
	private Button btnUltimateBajaProceed,
				   btnUltimateBajaClearHistory;

	private static String SUCCESS = "success";
	private static String ERROR = "error";
	
	int[] bajaPrice = new int[8];
	String[] ultimateBajaString = new String[14];
	String bajatvhistory;
	String [] bajaHistoryString = new String[14];
	String checkID = "baja";
	String PHPSESSID = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ultimatebajamain);
		
		Intent myIntent = getIntent();
		PHPSESSID = myIntent.getStringExtra("Set-Cookie");
		//Log.e("Set-Cookie",PHPSESSID);
		
		InfoRetrieved price = new InfoRetrieved();
	    JSONObject json = price.pricelist("baja", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    // check for success process
	    try {
	        if (json.getString(SUCCESS) != null) {
	            String res = json.getString(SUCCESS);
	            Log.e("check# res baja main is ", res);
	            if(Integer.parseInt(res) == 1){
	            	bajaPrice[0] = Integer.parseInt(json.getString("mealA"));
	            	bajaPrice[1] = Integer.parseInt(json.getString("mealB"));
	            	bajaPrice[2] = Integer.parseInt(json.getString("mealC"));
	            	bajaPrice[3] = Integer.parseInt(json.getString("mealD"));
	            	bajaPrice[4] = Integer.parseInt(json.getString("proteinA"));
	            	bajaPrice[5] = Integer.parseInt(json.getString("proteinB"));
	            	bajaPrice[6] = Integer.parseInt(json.getString("proteinC"));
	            	bajaPrice[7] = Integer.parseInt(json.getString("proteinD"));
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
	    JSONObject jsonhist = history.orderhistorylist("baja", PHPSESSID, getApplicationContext());
	    //Log.e("check@ baja hist retrieved", subjson.toString());
	    bajatvhistory = "Your last order with this vendor was:\n";
	    // check for success process
	    try {
	        if (jsonhist.getString(SUCCESS) != null) {
	            String reshist = jsonhist.getString(SUCCESS);
	            
	            if(Integer.parseInt(reshist) == 1){
	            	Log.e("check# reshist einstein main is ", reshist);
	            	bajaHistoryString[0] = jsonhist.getString("meal");
	            	bajaHistoryString[1] = jsonhist.getString("proteinA");
	            	bajaHistoryString[2] = jsonhist.getString("proteinB");
	            	bajaHistoryString[3] = jsonhist.getString("proteinC");
	            	bajaHistoryString[4] = jsonhist.getString("proteinD");
	            	bajaHistoryString[5] = jsonhist.getString("sidesA");
	            	bajaHistoryString[6] = jsonhist.getString("sidesB");
	            	bajaHistoryString[7] = jsonhist.getString("sidesC");
	            	bajaHistoryString[8] = jsonhist.getString("sidesD");
	            	bajaHistoryString[9] = jsonhist.getString("sidesE");
	            	bajaHistoryString[10] = jsonhist.getString("sidesF");
	            	bajaHistoryString[11] = jsonhist.getString("sauceA");
	            	bajaHistoryString[12] = jsonhist.getString("sauceB");
	            	bajaHistoryString[13] = jsonhist.getString("sauceC");
	            
		            
		            for(int i=0 ; i<bajaHistoryString.length; i++)
		            {
		            	if(!bajaHistoryString[i].equals("No"))
		            	{
		            		bajatvhistory = bajatvhistory + bajaHistoryString[i] + "\n";
		            	}
		            }
	            }
	            
	            if(Integer.parseInt(reshist) == 0){
	            	Log.e("check@baja fail-hist-retrived", reshist);
	            	bajatvhistory = bajatvhistory + "No history order is retrieved\n";
	            }
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	    bajaOrderHistory = (TextView) findViewById(R.id.bajaOrderHistory);
	    
	    priceNacho = (TextView) findViewById(R.id.priceNacho);
		priceTacos = (TextView) findViewById(R.id.priceTacos);
		priceQuesadilla = (TextView) findViewById(R.id.priceQuesadilla);
		priceBurrito = (TextView) findViewById(R.id.priceBurrito);
		priceGroundBeef = (TextView) findViewById(R.id.priceGroundBeef);
		priceSteak = (TextView) findViewById(R.id.priceSteak);
		priceChicken = (TextView) findViewById(R.id.priceChicken);
		pricePork = (TextView) findViewById(R.id.pricePork);

		checkNacho = (CheckBox) findViewById(R.id.checkNacho);
		checkTacos = (CheckBox) findViewById(R.id.checkTacos);
		checkQuesadilla = (CheckBox) findViewById(R.id.checkQuesadilla);
		checkBurrito = (CheckBox) findViewById(R.id.checkBurrito);
		checkGroundBeef = (CheckBox) findViewById(R.id.checkGroundBeef);
		checkSteak = (CheckBox) findViewById(R.id.checkSteak);
		checkChicken = (CheckBox) findViewById(R.id.checkChicken);
		checkPork = (CheckBox) findViewById(R.id.checkPork);
		checkRice = (CheckBox) findViewById(R.id.checkRice);
		checkBeans = (CheckBox) findViewById(R.id.checkBeans);
		checkLettuce = (CheckBox) findViewById(R.id.checkLettuce);
		checkTomatoes = (CheckBox) findViewById(R.id.checkTomatoes);
		checkRedOnion = (CheckBox) findViewById(R.id.checkRedOnion);
		checkCucumber = (CheckBox) findViewById(R.id.checkCucumber);
		checkSalsa = (CheckBox) findViewById(R.id.checkSalsa);
		checkPicoDeGallo = (CheckBox) findViewById(R.id.checkPicoDeGallo);
		checkCreamCheese = (CheckBox) findViewById(R.id.checkCreamCheese);
		
		bajaOrderHistory.setText(bajatvhistory);
		
		priceNacho.setText("$ " + bajaPrice[0]);
		priceTacos.setText("$ " + bajaPrice[1]);
		priceQuesadilla.setText("$ " + bajaPrice[2]);
		priceBurrito.setText("$ " + bajaPrice[3]);
		priceGroundBeef.setText("$ " + bajaPrice[4]);
		priceSteak.setText("$ " + bajaPrice[5]);
		priceChicken.setText("$ " + bajaPrice[6]);
		pricePork.setText("$ " + bajaPrice[7]);

		btnUltimateBajaProceed = (Button) findViewById(R.id.btnUltimateBajaProceed);

		btnUltimateBajaProceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// check box, if checked set the string
				if (!checkNacho.isChecked() && !checkTacos.isChecked() && !checkQuesadilla.isChecked() &&
					!checkBurrito.isChecked() && !checkGroundBeef.isChecked() && !checkSteak.isChecked() &&
					!checkChicken.isChecked() && !checkPork.isChecked() && !checkRice.isChecked() &&
					!checkBeans.isChecked() && !checkLettuce.isChecked() && !checkTomatoes.isChecked() &&
					!checkRedOnion.isChecked() && !checkCucumber.isChecked() && !checkSalsa.isChecked() &&
					!checkPicoDeGallo.isChecked() && !checkCreamCheese.isChecked())
					{
						AlertDialog.Builder alert = new AlertDialog.Builder(UltimateBajaMainActivity.this);
			            alert.setTitle("Error"); 
			            alert.setMessage("Select at least one menu");
			            alert.setPositiveButton("OK", null); 
			            alert.show();
					}
					else
					{
						if (checkNacho.isChecked())
							ultimateBajaString[0] = "Nacho";
						if (checkTacos.isChecked())
							ultimateBajaString[0] = "Taco";
						if (checkQuesadilla.isChecked())
							ultimateBajaString[0] = "Quesadilla";
						if (checkBurrito.isChecked())
							ultimateBajaString[0] = "Burrito";
						
						if (checkGroundBeef.isChecked())
							ultimateBajaString[1] = "Ground beef";
						else
							ultimateBajaString[1] = "No";
						if (checkSteak.isChecked())
							ultimateBajaString[2] = "Steak";
						else
							ultimateBajaString[2] = "No";
						if (checkChicken.isChecked())
							ultimateBajaString[3] = "Chicken";
						else
							ultimateBajaString[3] = "No";
						if (checkPork.isChecked())
							ultimateBajaString[4] = "Pork";
						else
							ultimateBajaString[4] = "No";
						if (checkRice.isChecked())
							ultimateBajaString[5] = "rice";
						else
							ultimateBajaString[5] = "No";
						if (checkBeans.isChecked())
							ultimateBajaString[6] = "beans";
						else
							ultimateBajaString[6] = "No";
						if (checkLettuce.isChecked())
							ultimateBajaString[7] = "lettuce";
						else
							ultimateBajaString[7] = "No";
						if (checkTomatoes.isChecked())
							ultimateBajaString[8] = "tomatoes";
						else
							ultimateBajaString[8] = "No";
						if (checkRedOnion.isChecked())
							ultimateBajaString[9] = "redonion";
						else
							ultimateBajaString[9] = "No";
						if (checkCucumber.isChecked())
							ultimateBajaString[10] = "cucumber";
						else
							ultimateBajaString[10] = "No";
						if (checkSalsa.isChecked())
							ultimateBajaString[11] = "salsa";
						else
							ultimateBajaString[11] = "No";
						if (checkPicoDeGallo.isChecked())
							ultimateBajaString[12] = "picodegallo";
						else
							ultimateBajaString[12] = "No";
						if (checkCreamCheese.isChecked())
							ultimateBajaString[13] = "cream cheese";
						else
							ultimateBajaString[13] = "No";
		
		
						// indicator for checkout activity
						String intentID = "baja";
		
						Intent ultimateBajaCheckout = new Intent(getApplicationContext(),
								CheckoutActivity.class);
						//ultimateBajaCheckout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Bundle extras = new Bundle();
						// putExtra to the Bundle
						extras.putString("intentKey", intentID);
						extras.putString("Set-Cookie", PHPSESSID);
						extras.putStringArray("activityKey", ultimateBajaString);
						extras.putIntArray("price", bajaPrice);
						ultimateBajaCheckout.putExtras(extras);
						startActivity(ultimateBajaCheckout);
					}
			}

		});
		
		
		btnUltimateBajaClearHistory = (Button) findViewById(R.id.btnUltimateBajaClearHistory);
		
		btnUltimateBajaClearHistory.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClearHistory clearHistory = new ClearHistory();
                JSONObject json = clearHistory.clearUltimateBaja(checkID, PHPSESSID, getApplicationContext());
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
