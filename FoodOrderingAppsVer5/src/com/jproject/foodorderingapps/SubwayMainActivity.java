package com.jproject.foodorderingapps;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.jproject.foodorderingapps.library.CheckoutFunctions;
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

public class SubwayMainActivity extends Activity {
	
	private RadioGroup radioBreadGroup,
	                   radioFlavorGroup,
	                   radioCheeseGroup,
	                   radioSauceGroup;
	                  
	private RadioButton radioBreadButton,
	                    radioFlavorButton,
	                    radioCheeseButton,
	                    radioSauceButton;
	                   
	
	private CheckBox check6inch,
					 check12inch,
	                 checkLettuce,
	                 checkTomatoes,
	                 checkCucumbers,
	                 checkPickles,
	                 checkBellPeppers,
	                 checkBlackOlives,
	                 checkRedOnions,
	                 checkJalapenoPeppers,
	                 checkBananaPeppers,
	                 checkSpinach,
	                 checkSalt,
	                 checkPepper,
	                 checkDrink,
	                 checkChips,
	                 checkCookies;
	
	private TextView price6inch,
					 price12inch,
					 priceDrink,
					 priceChips,
					 priceCookies,
					 subwayOrderHistory;
		
	private Button btnSubwayProceed,
				   btnSubwayClearHistory;
	
	private static String SUCCESS = "success";
	private static String ERROR = "error";
	
	int[] subwayPrice = new int[5];
	String [] subwayString = new String [20];
	String subwaytvhistory;
	String [] subwayHistoryString = new String[20];
	String checkID = "subway";
	String PHPSESSID = null;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subwaymain);
		
		Intent myIntent = getIntent();
		PHPSESSID = myIntent.getStringExtra("Set-Cookie");
		//Log.e("Set-Cookie",PHPSESSID);

		//price retrieval
		InfoRetrieved price = new InfoRetrieved();
	    JSONObject json = price.pricelist("subway", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    // check for success process
	    try {
	        if (json.getString(SUCCESS) != null) {
	            String res = json.getString(SUCCESS);
	            Log.e("check# res subway main is ", res);
	            if(Integer.parseInt(res) == 1){
	            	subwayPrice[0] = Integer.parseInt(json.getString("6inch"));
	            	subwayPrice[1] = Integer.parseInt(json.getString("12inch"));
	            	subwayPrice[2] = Integer.parseInt(json.getString("drink"));
	            	subwayPrice[3] = Integer.parseInt(json.getString("chips"));
	            	subwayPrice[4] = Integer.parseInt(json.getString("cookies"));
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
	    JSONObject jsonhist = history.orderhistorylist("subway", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    subwaytvhistory = "Your last order with this vendor was:\n";
	    // check for success process
	    try {
	        if (jsonhist.getString(SUCCESS) != null) {
	            String reshist = jsonhist.getString(SUCCESS);
	            Log.e("check# reshist subway main is ", reshist);
	            if(Integer.parseInt(reshist) == 1){
	            	subwayHistoryString[0] = jsonhist.getString("foot");
	            	subwayHistoryString[1] = jsonhist.getString("bread");
	            	subwayHistoryString[2] = jsonhist.getString("flavor");
	            	subwayHistoryString[3] = jsonhist.getString("cheese");
	            	subwayHistoryString[4] = jsonhist.getString("sauce");
	            	subwayHistoryString[5] = jsonhist.getString("drink");
	            	subwayHistoryString[6] = jsonhist.getString("chips");
	            	subwayHistoryString[7] = jsonhist.getString("cookies");
	            	subwayHistoryString[8] = jsonhist.getString("veg_1");
	            	subwayHistoryString[9] = jsonhist.getString("veg_2");
	            	subwayHistoryString[10] = jsonhist.getString("veg_3");
	            	subwayHistoryString[11] = jsonhist.getString("veg_4");
	            	subwayHistoryString[12] = jsonhist.getString("veg_5");
	            	subwayHistoryString[13] = jsonhist.getString("veg_6");
	            	subwayHistoryString[14] = jsonhist.getString("veg_7");
	            	subwayHistoryString[15] = jsonhist.getString("veg_8");
	            	subwayHistoryString[16] = jsonhist.getString("veg_9");
	            	subwayHistoryString[17] = jsonhist.getString("veg_10");
	            	subwayHistoryString[18] = jsonhist.getString("veg_11");
	            	subwayHistoryString[19] = jsonhist.getString("veg_12");
	            	
	            
		            
		            for(int i=0 ; i<subwayHistoryString.length; i++)
		            {
		            	if(!subwayHistoryString[i].equals("No"))
		            	{
		            		subwaytvhistory = subwaytvhistory + subwayHistoryString[i] + "\n";
		            	}
		            }
	            }
	            
	            if(Integer.parseInt(reshist) == 0){
	            	Log.e("check@subway fail-hist-retrived", reshist);
	            	subwaytvhistory = subwaytvhistory + "No history order is retrieved\n";
	            }
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }

	    subwayOrderHistory = (TextView) findViewById(R.id.subwayOrderHistory);
	    
		price6inch = (TextView) findViewById(R.id.tvPrice6inch);
		price12inch = (TextView) findViewById(R.id.tvPrice12inch);
		priceDrink = (TextView) findViewById(R.id.tvPriceDrink);
		priceChips = (TextView) findViewById(R.id.tvPriceChips);
		priceCookies = (TextView) findViewById(R.id.tvPriceCookies);
		
		radioBreadGroup = (RadioGroup) findViewById(R.id.radioBread);
		radioFlavorGroup = (RadioGroup) findViewById(R.id.radioFlavor);
		radioCheeseGroup = (RadioGroup) findViewById(R.id.radioCheese);
		radioSauceGroup = (RadioGroup) findViewById(R.id.radioSauce);
		
		check6inch = (CheckBox) findViewById(R.id.check6inch);
		check12inch = (CheckBox) findViewById(R.id.check12inch);
		checkLettuce = (CheckBox) findViewById(R.id.checkLettuce);
        checkTomatoes = (CheckBox) findViewById(R.id.checkTomatoes);
        checkCucumbers = (CheckBox) findViewById(R.id.checkCucumbers);
        checkPickles = (CheckBox) findViewById(R.id.checkPickles);
        checkBellPeppers = (CheckBox) findViewById(R.id.checkBellPeppers);
        checkBlackOlives = (CheckBox) findViewById(R.id.checkBlackOlives);
        checkRedOnions = (CheckBox) findViewById(R.id.checkRedOnions);
        checkJalapenoPeppers = (CheckBox) findViewById(R.id.checkJalapenoPeppers);
        checkBananaPeppers = (CheckBox) findViewById(R.id.checkBananaPeppers);
        checkSpinach = (CheckBox) findViewById(R.id.checkSpinach);
        checkSalt = (CheckBox) findViewById(R.id.checkSalt);
        checkPepper = (CheckBox) findViewById(R.id.checkPepper);
        checkDrink = (CheckBox) findViewById(R.id.checkDrink);
        checkChips = (CheckBox) findViewById(R.id.checkChips);
        checkCookies = (CheckBox) findViewById(R.id.checkCookies);
		
        subwayOrderHistory.setText(subwaytvhistory);
        
        price6inch.setText("$ " + subwayPrice[0]);
    	price12inch.setText("$ " + subwayPrice[1]);
    	priceDrink.setText("$ " + subwayPrice[2]);
    	priceChips.setText("$ " + subwayPrice[3]);
    	priceCookies.setText("$ " + subwayPrice[4]);
        
		btnSubwayProceed = (Button) findViewById(R.id.btnSubwayProceed);
		
		btnSubwayProceed.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//get the id of selected radio button from radio group
				if (!check6inch.isChecked() && !check12inch.isChecked() &&
					!checkLettuce.isChecked() && !checkTomatoes.isChecked() && !checkCucumbers.isChecked() &&
		            !checkPickles.isChecked() && !checkBellPeppers.isChecked() && !checkBlackOlives.isChecked() &&
		            !checkRedOnions.isChecked() && !checkJalapenoPeppers.isChecked() && !checkBananaPeppers.isChecked() &&
		            !checkSpinach.isChecked() && !checkSalt.isChecked() && !checkPepper.isChecked() &&
		            !checkDrink.isChecked() && !checkChips.isChecked() && !checkCookies.isChecked())
					{
						AlertDialog.Builder alert = new AlertDialog.Builder(SubwayMainActivity.this);
			            alert.setTitle("Error"); 
			            alert.setMessage("Select at least one menu");
			            alert.setPositiveButton("OK", null); 
			            alert.show();
					}
					else
					{
						int selectedBread = radioBreadGroup.getCheckedRadioButtonId();
						int selectedFlavor = radioFlavorGroup.getCheckedRadioButtonId();
						int selectedCheese = radioCheeseGroup.getCheckedRadioButtonId();
						int selectedSauce = radioSauceGroup.getCheckedRadioButtonId();
						
						
						//set the id to the radio button
						radioBreadButton = (RadioButton) findViewById(selectedBread);
						radioFlavorButton = (RadioButton) findViewById(selectedFlavor);
						radioCheeseButton = (RadioButton) findViewById(selectedCheese);
						radioSauceButton = (RadioButton) findViewById(selectedSauce);
						
						
						//assign all selected radio buttons to string
						subwayString[1] = radioBreadButton.getText().toString();
						subwayString[2] = radioFlavorButton.getText().toString();
						subwayString[3] = radioCheeseButton.getText().toString();
						subwayString[4] = radioSauceButton.getText().toString();
						
						//check box, if checked set the string
						if(check6inch.isChecked())
							subwayString[0] = check6inch.getText().toString();
						if(check12inch.isChecked())
							subwayString[0] = check12inch.getText().toString();
						
						if(checkDrink.isChecked())
							subwayString[5] = checkDrink.getText().toString();
						else subwayString[5] = "No";
						if(checkChips.isChecked())
							subwayString[6] = checkChips.getText().toString();
						else subwayString[6] = "No";
						if(checkCookies.isChecked())
							subwayString[7] = checkCookies.getText().toString();
						else subwayString[7] = "No";
		
						if(checkLettuce.isChecked())
							subwayString[8] = "lettuce";
						else subwayString[8] = "No";
						if(checkTomatoes.isChecked())
							subwayString[9] = "tomatoes";
						else subwayString[9] = "No";
						if(checkCucumbers.isChecked())
							subwayString[10] = "cucumber";
						else subwayString[10] = "No";
						if(checkPickles.isChecked())
							subwayString[11] = "pickles";
						else subwayString[11] = "No";
						if(checkBellPeppers.isChecked())
							subwayString[12] = "bellpepper";
						else subwayString[12] = "No";
						if(checkBlackOlives.isChecked())
							subwayString[13] = "olive";
						else subwayString[13] = "No";
						if(checkRedOnions.isChecked())
							subwayString[14] = "redonion";
						else subwayString[14] = "No";
						if(checkJalapenoPeppers.isChecked())
							subwayString[15] = "jalapenopepper";
						else subwayString[15] = "No";
						if(checkBananaPeppers.isChecked())
							subwayString[16] = "bananapepper";
						else subwayString[16] = "No";				
						if(checkSpinach.isChecked())
							subwayString[17] = "spinach";
						else subwayString[17] = "No";
						if(checkSalt.isChecked())
							subwayString[18] = "salt";
						else subwayString[18] = "No";
						if(checkPepper.isChecked())
							subwayString[19] = "pepper";
						else subwayString[19] = "No";
						
						//indicator for checkout activity
						String intentID = "subway";
						
						Intent subwayCheckout = new Intent(getApplicationContext(), CheckoutActivity.class);
						//subwayCheckout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Bundle extras = new Bundle();
						//putExtra to the Bundle
						extras.putString("intentKey", intentID);
						extras.putString("Set-Cookie", PHPSESSID);
						extras.putStringArray("activityKey", subwayString);
						extras.putIntArray("price", subwayPrice);
						subwayCheckout.putExtras(extras);
						startActivity(subwayCheckout);
				
				}
			}
			
		});
		
		
		btnSubwayClearHistory = (Button) findViewById(R.id.btnSubwayClearHistory);
		
		btnSubwayClearHistory.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClearHistory clearHistory = new ClearHistory();
                JSONObject json = clearHistory.clearSubway(checkID, PHPSESSID, getApplicationContext());
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
