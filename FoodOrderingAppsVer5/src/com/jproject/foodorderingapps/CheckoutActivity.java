package com.jproject.foodorderingapps;

import org.json.JSONException;
import org.json.JSONObject;

import com.jproject.foodorderingapps.library.CheckoutFunctions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

public class CheckoutActivity extends Activity {
	
	private Button checkoutButton;
	private TextView tvCheckout1,
	                 tvCheckout2,
	                 tvCheckout3;
	
	private static String SUCCESS = "success";
	private static String ERROR = "error";
	private static String SUB_TOTAL = "subtotal";
	
	int subtotal = 0;
	String checkID;
	String EINSTEIN = "einstein",
			SIMPLY = "simplytogo",
			STARBUCK = "starbucks",
			SUBWAY = "subway",
			BAJA = "baja",
			UC = "uc";
				
	private static String [] subwayStringCheckout = new String[12];
	private static String [] einsteinStringCheckout = new String[12];
	private static String [] simplytogoStringCheckout = new String[9];
	private static String [] starbucksStringCheckout = new String[12];
	private static String [] ultimateBajaStringCheckout = new String[14];
	private static String [] ucStringCheckout = new String[12];
	private static String [] ucClassicsCheckout = new String[15];
	
	private int[] subwayPriceCheckout = new int[3];
	private int[] einsteinPriceCheckout = new int[12];
	private int[] simplytogoPriceCheckout = new int[9];
	private int[] starbucksPriceCheckout = new int[12];
	private int[] ultimateBajaPriceCheckout = new int[8];
	private int[] ucPriceCheckout = new int[12];
	
	String PHPSESSID = null;
	
	
	//private ProgressBar progressBar;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.checkout);
		
		checkoutButton = (Button) findViewById(R.id.btnCheckout);
		tvCheckout1 = (TextView) findViewById(R.id.checkout1);
		tvCheckout2 = (TextView) findViewById(R.id.checkout2);
		tvCheckout3 = (TextView) findViewById(R.id.checkout3);	
		//progressBar = (ProgressBar) findViewById(R.id.progressBar);
		
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		//String checkString;
		checkID = extras.getString("intentKey");
		
		/*
		*  Einstein Bros
		*/
		
		if (checkID.equals(EINSTEIN))
		{
		    einsteinStringCheckout = extras.getStringArray("activityKey");
		    einsteinPriceCheckout = extras.getIntArray("price");
		    PHPSESSID = extras.getString("Set-Cookie");
		    
		    tvCheckout1.setText("Please review your order\n\n");
						
			String snack = "";
			if (einsteinStringCheckout[0].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[0] + " ($ " + einsteinPriceCheckout[0] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[0];
			}
			if (einsteinStringCheckout[1].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[1] + " ($ " + einsteinPriceCheckout[1] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[1];
			}
			if (einsteinStringCheckout[2].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[2] + " ($ " + einsteinPriceCheckout[2] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[2];
			}
			if (einsteinStringCheckout[3].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[3] + " ($ " + einsteinPriceCheckout[3] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[3];
			}
			if (einsteinStringCheckout[4].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[4] + " ($ " + einsteinPriceCheckout[4] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[4];
			}
			if (einsteinStringCheckout[5].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[5] + " ($ " + einsteinPriceCheckout[5] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[5];
			}
			if (einsteinStringCheckout[6].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[6] + " ($ " + einsteinPriceCheckout[6] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[6];
			}
			if (einsteinStringCheckout[7].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[7] + " ($ " + einsteinPriceCheckout[7] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[7];
			}
			if (einsteinStringCheckout[8].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[8] + " ($ " + einsteinPriceCheckout[8] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[8];
			}
			if (einsteinStringCheckout[9].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[9] + " ($ " + einsteinPriceCheckout[9] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[9];
			}
			if (einsteinStringCheckout[10].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[10] + " ($ " + einsteinPriceCheckout[10] + ")" + "\n";
				subtotal = subtotal + einsteinPriceCheckout[10];
			}
			if (einsteinStringCheckout[11].equals("No"))
				snack = snack;
			else
			{
				snack = snack + einsteinStringCheckout[11] + " ($ " + einsteinPriceCheckout[11] + ")";
				subtotal = subtotal + einsteinPriceCheckout[11];
			}
			
			tvCheckout2.setText(snack);
			tvCheckout3.setText("\n\nSubtotal is $ " + subtotal);
			
			checkoutButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//begin_progress(v);
					CheckoutFunctions checkoutFunction = new CheckoutFunctions();
	                JSONObject json = checkoutFunction.checkoutEinsteinBros(checkID, einsteinStringCheckout, PHPSESSID, getApplicationContext());
	                //Log.e("check# here @checkoutActivity", subjson.toString());
	                // check for success process
	                try {
	                    if (json.getString(SUCCESS) != null) {
	                        String res = json.getString(SUCCESS);
	                        Log.e("check# res is ", res);
	                        if(Integer.parseInt(res) == 1){
	                        	String subtotal = json.getString(SUB_TOTAL);
	                            //Toast toast = Toast.makeText(getApplicationContext(), "Order is submitted and the total is $" + subtotal, Toast.LENGTH_SHORT);
	                            //toast.show();
	                            
	                            Intent thankyou = new Intent(getApplicationContext(), ThankYouActivity.class);
	                            //thankyou.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                            Bundle extras = new Bundle();
	                            extras.putString("total", subtotal);
	                            extras.putString("id", checkID);
	                            extras.putString("Set-Cookie", PHPSESSID);
	                            thankyou.putExtras(extras);
	                            startActivity(thankyou);
	                            //finish();
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
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	                //end_progress(v);
				}
			});
		}// end if einstein
		
		/*
		*  Simply To-Go
		*/
		
		if (checkID.equals(SIMPLY))
		{
			simplytogoStringCheckout = extras.getStringArray("activityKey");
			simplytogoPriceCheckout = extras.getIntArray("price");
			PHPSESSID = extras.getString("Set-Cookie");
			
			tvCheckout1.setText("Please review your order\n\n");
						
			String snackies = "";
			if (simplytogoStringCheckout[0].equals("No"))
				snackies = snackies;
			else
			{
				snackies = snackies + simplytogoStringCheckout[0] + " ($ " + simplytogoPriceCheckout[0] + ")" + "\n";
				subtotal = subtotal + simplytogoPriceCheckout[0];
			}
			if (simplytogoStringCheckout[1].equals("No"))
				snackies = snackies;
			else
			{
				snackies = snackies + simplytogoStringCheckout[1] + " ($ " + simplytogoPriceCheckout[1] + ")" + "\n";
				subtotal = subtotal + simplytogoPriceCheckout[1];
			}
			if (simplytogoStringCheckout[2].equals("No"))
				snackies = snackies;
			else
			{
				snackies = snackies + simplytogoStringCheckout[2] + " ($ " + simplytogoPriceCheckout[2] + ")" + "\n";
				subtotal = subtotal + simplytogoPriceCheckout[2];
			}
			if (simplytogoStringCheckout[3].equals("No"))
				snackies = snackies;
			else
			{
				snackies = snackies + simplytogoStringCheckout[3] + " ($ " + simplytogoPriceCheckout[3] + ")" + "\n";
				subtotal = subtotal + simplytogoPriceCheckout[3];
			}
			if (simplytogoStringCheckout[4].equals("No"))
				snackies = snackies;
			else
			{
				snackies = snackies + simplytogoStringCheckout[4] + " ($ " + simplytogoPriceCheckout[4] + ")" + "\n";
				subtotal = subtotal + simplytogoPriceCheckout[4];
			}
			if (simplytogoStringCheckout[5].equals("No"))
				snackies = snackies;
			else
			{
				snackies = snackies + simplytogoStringCheckout[5] + " ($ " + simplytogoPriceCheckout[5] + ")" + "\n";
				subtotal = subtotal + simplytogoPriceCheckout[5];
			}
			if (simplytogoStringCheckout[6].equals("No"))
				snackies = snackies;
			else
			{
				snackies = snackies + simplytogoStringCheckout[6] + " ($ " + simplytogoPriceCheckout[6] + ")" + "\n";
				subtotal = subtotal + simplytogoPriceCheckout[6];
			}
			if (simplytogoStringCheckout[7].equals("No"))
				snackies = snackies;
			else
			{
				snackies = snackies + simplytogoStringCheckout[7] + " ($ " + simplytogoPriceCheckout[7] + ")" + "\n";
				subtotal = subtotal + simplytogoPriceCheckout[7];
			}
			if (simplytogoStringCheckout[8].equals("No"))
				snackies = snackies;
			else
			{
				snackies = snackies + simplytogoStringCheckout[8] + " ($ " + simplytogoPriceCheckout[8] + ")" + "\n";
				subtotal = subtotal + simplytogoPriceCheckout[8];
			}
			
			tvCheckout2.setText(snackies);
			tvCheckout3.setText("\n\nSubtotal is $ " + subtotal);
			
			checkoutButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//begin_progress(v);
					CheckoutFunctions checkoutFunction = new CheckoutFunctions();
	                JSONObject json = checkoutFunction.checkoutSimplyToGo(checkID, simplytogoStringCheckout, PHPSESSID, getApplicationContext());
	                //Log.e("check# here @checkoutActivity", subjson.toString());
	                // check for success process
	                try {
	                    if (json.getString(SUCCESS) != null) {
	                        String res = json.getString(SUCCESS);
	                        Log.e("check# res is ", res);
	                        if(Integer.parseInt(res) == 1){
	                        	String subtotal = json.getString(SUB_TOTAL);
	                            //Toast toast = Toast.makeText(getApplicationContext(), "Order is submitted and the total is $" + subtotal, Toast.LENGTH_SHORT);
	                            //toast.show();
	                            
	                            Intent thankyou = new Intent(getApplicationContext(), ThankYouActivity.class);
	                            //thankyou.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                            Bundle extras = new Bundle();
	                            extras.putString("total", subtotal);
	                            extras.putString("id", checkID);
	                            extras.putString("Set-Cookie", PHPSESSID);
	                            thankyou.putExtras(extras);
	                            startActivity(thankyou);
	                            //finish();
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
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	                //end_progress(v);
				}
			});
		}//end if simply
		
		/*
		*  Starbucks
		*/
		
		if (checkID.equals(STARBUCK))
		{
			starbucksStringCheckout = extras.getStringArray("activityKey");
			starbucksPriceCheckout = extras.getIntArray("price");
			PHPSESSID = extras.getString("Set-Cookie");
			
			tvCheckout1.setText("Please review your order\n\n");
						
			String drinks = "";
			if (starbucksStringCheckout[0].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[0] + " ($ " + starbucksPriceCheckout[0] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[0];
			}
			if (starbucksStringCheckout[1].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[1] + " ($ " + starbucksPriceCheckout[1] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[1];
			}
			if (starbucksStringCheckout[2].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[2] + " ($ " + starbucksPriceCheckout[2] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[2];
			}
			if (starbucksStringCheckout[3].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[3] + " ($ " + starbucksPriceCheckout[3] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[3];
			}
			if (starbucksStringCheckout[4].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[4] + " ($ " + starbucksPriceCheckout[4] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[4];
			}
			if (starbucksStringCheckout[5].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[5] + " ($ " + starbucksPriceCheckout[5] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[5];
			}
			if (starbucksStringCheckout[6].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[6] + " (" + starbucksStringCheckout[7] + ") ($ " + starbucksPriceCheckout[6] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[6];
			}
			if (starbucksStringCheckout[8].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[8]  + " (" + starbucksStringCheckout[9] + ") ($ " + starbucksPriceCheckout[7] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[7];
			}
			if (starbucksStringCheckout[10].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[10]  + " (" + starbucksStringCheckout[11] + ") ($ " + starbucksPriceCheckout[8] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[8];
			}
			if (starbucksStringCheckout[12].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[12] + " ($ " + starbucksPriceCheckout[9] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[9];
			}
			if (starbucksStringCheckout[13].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[13] + " ($ " + starbucksPriceCheckout[10] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[10];
			}
			if (starbucksStringCheckout[14].equals("No"))
				drinks = drinks;
			else
			{
				drinks = drinks + starbucksStringCheckout[14] + " ($ " + starbucksPriceCheckout[11] + ")" + "\n";
				subtotal = subtotal + starbucksPriceCheckout[11];
			}
			
			tvCheckout2.setText(drinks);
			tvCheckout3.setText("\n\nSubtotal is $ " + subtotal);
			
			checkoutButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//begin_progress(v);
					CheckoutFunctions checkoutFunction = new CheckoutFunctions();
	                JSONObject json = checkoutFunction.checkoutStarbucks(checkID, starbucksStringCheckout, PHPSESSID, getApplicationContext());
	                //Log.e("check# here @checkoutActivity", subjson.toString());
	                // check for success process
	                try {
	                    if (json.getString(SUCCESS) != null) {
	                        String res = json.getString(SUCCESS);
	                        Log.e("check# res is ", res);
	                        if(Integer.parseInt(res) == 1){
	                        	String subtotal = json.getString(SUB_TOTAL);
	                            //Toast toast = Toast.makeText(getApplicationContext(), "Order is submitted and the total is $" + subtotal, Toast.LENGTH_SHORT);
	                            //toast.show();
	                            
	                            Intent thankyou = new Intent(getApplicationContext(), ThankYouActivity.class);
	                            //thankyou.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                            Bundle extras = new Bundle();
	                            extras.putString("total", subtotal);
	                            extras.putString("id", checkID);
	                            extras.putString("Set-Cookie", PHPSESSID);
	                            thankyou.putExtras(extras);
	                            startActivity(thankyou);
	                            //finish();
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
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	                //end_progress(v);
				}
			});
		}//end if starbuck
		
		/*
		*  Subway
		*/
		
		if (checkID.equals(SUBWAY))
		{
			//Toast.makeText(CheckoutActivity.this, subwayStringCheckout[0], Toast.LENGTH_SHORT).show();
		    subwayStringCheckout = extras.getStringArray("activityKey");
		    subwayPriceCheckout = extras.getIntArray("price");
		    PHPSESSID = extras.getString("Set-Cookie");
		    
		    //count total
		    if(subwayStringCheckout[0].equals("6 inch"))
		    	subtotal = subtotal + subwayPriceCheckout[0];
		    if(subwayStringCheckout[0].equals("12 inch"))
		    	subtotal = subtotal + subwayPriceCheckout[1];
		    if(subwayStringCheckout[5].equals("Drink"))
		    	subtotal = subtotal + subwayPriceCheckout[2];
		    if(subwayStringCheckout[6].equals("Chips"))
		    	subtotal = subtotal + subwayPriceCheckout[3];
		    if(subwayStringCheckout[7].equals("Cookies"))
		    	subtotal = subtotal + subwayPriceCheckout[4];
		    
		    //set text
		    tvCheckout1.setText("Please review your order\n\n" + "The sandwich is:\n" + subwayStringCheckout[0] + " of " +
		    		subwayStringCheckout[1]);
		    if(subwayStringCheckout[0].equals("6 inch"))
		        	tvCheckout1.append(" ($ " + subwayPriceCheckout[0] + ")\n");
		    if(subwayStringCheckout[0].equals("12 inch"))
		        	tvCheckout1.append(" ($ " + subwayPriceCheckout[1] + ")\n");
		    
		    //set text
			tvCheckout2.setText("with flavor:\n" + subwayStringCheckout[2] + "\n\n" +
					"with cheese:\n" + subwayStringCheckout[3] + "\n\n" +
					"with dressing:\n" + subwayStringCheckout[4] + "\n\n" + "is it meal?\n");
			
			if(subwayStringCheckout[5].equals("Drink"))
					tvCheckout2.append(subwayStringCheckout[5] + " ($ " + subwayPriceCheckout[2] + ")" + "\n");
			if(subwayStringCheckout[6].equals("Chips"))
					tvCheckout2.append(subwayStringCheckout[6] + " ($ " + subwayPriceCheckout[3] + ")" + "\n");
			if(subwayStringCheckout[7].equals("Cookies"))
					tvCheckout2.append(subwayStringCheckout[7] + " ($ " + subwayPriceCheckout[4] + ")" + "\n");
			
			//set text
			String vegies = "with vegetables:\n";
			if (subwayStringCheckout[8].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[8] + "\n";
			if (subwayStringCheckout[9].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[9] + "\n";
			if (subwayStringCheckout[10].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[10] + "\n";
			if (subwayStringCheckout[11].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[11] + "\n";
			if (subwayStringCheckout[12].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[12] + "\n";
			if (subwayStringCheckout[13].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[13] + "\n";
			if (subwayStringCheckout[14].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[14] + "\n";
			if (subwayStringCheckout[15].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[15] + "\n";
			if (subwayStringCheckout[16].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[16] + "\n";
			if (subwayStringCheckout[17].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[17] + "\n";
			if (subwayStringCheckout[18].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[18] + "\n";
			if (subwayStringCheckout[19].equals("No"))
				vegies = vegies;
			else
				vegies = vegies + subwayStringCheckout[19] + "\n";
			
			
			vegies = vegies + "\n\nSubtotal is $ " + subtotal;
			
			//if no vegetables are selected
			if(vegies.equals("with vegetables:\n"))
			{
				vegies = vegies + "none";
			    tvCheckout3.setText(vegies);
			}
			else
				tvCheckout3.setText(vegies);
			
			checkoutButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//begin_progress(v);
					CheckoutFunctions checkoutFunction = new CheckoutFunctions();
	                JSONObject json = checkoutFunction.checkoutSubway(checkID, subwayStringCheckout, PHPSESSID, getApplicationContext());
	                //Log.e("check# here @checkoutActivity", subjson.toString());
	                // check for success process
	                try {
	                    if (json.getString(SUCCESS) != null) {
	                        String res = json.getString(SUCCESS);
	                        Log.e("check# res is ", res);
	                        if(Integer.parseInt(res) == 1){
	                        	String subtotal = json.getString(SUB_TOTAL);
	                            //Toast toast = Toast.makeText(getApplicationContext(), "Order is submitted and the total is $" + subtotal, Toast.LENGTH_SHORT);
	                            //toast.show();
	                            
	                            Intent thankyou = new Intent(getApplicationContext(), ThankYouActivity.class);
	                            //thankyou.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                            Bundle extras = new Bundle();
	                            extras.putString("total", subtotal);
	                            extras.putString("id", checkID);
	                            extras.putString("Set-Cookie", PHPSESSID);
	                            thankyou.putExtras(extras);
	                            startActivity(thankyou);
	                            //finish();
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
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	                //end_progress(v);
				}
			});
		}// end if subway
		
		/*
		*  Ultimate Baja
		*/
		
		if (checkID.equals(BAJA))
		{
			ultimateBajaStringCheckout = extras.getStringArray("activityKey");
			ultimateBajaPriceCheckout = extras.getIntArray("price");
			PHPSESSID = extras.getString("Set-Cookie");
			
		    tvCheckout1.setText("Please review your order\n\n");
						
			String alimento = "";
			if (ultimateBajaStringCheckout[0].equals("Nacho"))
			{
				alimento = alimento + ultimateBajaStringCheckout[0] + " ($ " + ultimateBajaPriceCheckout[0] + ")\n" + "with\n";
				subtotal = subtotal + ultimateBajaPriceCheckout[0];
			}
			if (ultimateBajaStringCheckout[0].equals("Taco"))
			{
				alimento = alimento + ultimateBajaStringCheckout[0] + " ($ " + ultimateBajaPriceCheckout[1] + ")\n" + "with\n";
				subtotal = subtotal + ultimateBajaPriceCheckout[1];
			}
			if (ultimateBajaStringCheckout[0].equals("Quesadilla"))
			{
				alimento = alimento + ultimateBajaStringCheckout[0] + " ($ " + ultimateBajaPriceCheckout[2] + ")\n" + "with\n";
				subtotal = subtotal + ultimateBajaPriceCheckout[2];
			}
			if (ultimateBajaStringCheckout[0].equals("Burrito"))
			{
				alimento = alimento + ultimateBajaStringCheckout[0] + " ($ " + ultimateBajaPriceCheckout[3] + ")\n" + "with\n";
				subtotal = subtotal + ultimateBajaPriceCheckout[3];
			}
			
			if (ultimateBajaStringCheckout[1].equals("No"))
				alimento = alimento;
			else
			{
				alimento = alimento + ultimateBajaStringCheckout[1] + " ($ " + ultimateBajaPriceCheckout[4] + ")" + "\n";
				subtotal = subtotal + ultimateBajaPriceCheckout[4];
			}
			if (ultimateBajaStringCheckout[2].equals("No"))
				alimento = alimento;
			else
			{
				alimento = alimento + ultimateBajaStringCheckout[2] + " ($ " + ultimateBajaPriceCheckout[5] + ")" + "\n";
				subtotal = subtotal + ultimateBajaPriceCheckout[5];
			}
			if (ultimateBajaStringCheckout[3].equals("No"))
				alimento = alimento;
			else
			{
				alimento = alimento + ultimateBajaStringCheckout[3] + " ($ " + ultimateBajaPriceCheckout[6] + ")" + "\n";
				subtotal = subtotal + ultimateBajaPriceCheckout[6];
			}
			if (ultimateBajaStringCheckout[4].equals("No"))
				alimento = alimento;
			else
			{
				alimento = alimento + ultimateBajaStringCheckout[4] + " ($ " + ultimateBajaPriceCheckout[7] + ")" + "\n";
				subtotal = subtotal + ultimateBajaPriceCheckout[7];
			}
			if (ultimateBajaStringCheckout[5].equals("No"))
				alimento = alimento;
			else
				alimento = alimento + ultimateBajaStringCheckout[5] + "\n";
			if (ultimateBajaStringCheckout[6].equals("No"))
				alimento = alimento;
			else
				alimento = alimento + ultimateBajaStringCheckout[6] + "\n";
			if (ultimateBajaStringCheckout[7].equals("No"))
				alimento = alimento;
			else
				alimento = alimento + ultimateBajaStringCheckout[7] + "\n";
			if (ultimateBajaStringCheckout[8].equals("No"))
				alimento = alimento;
			else
				alimento = alimento + ultimateBajaStringCheckout[8] + "\n";
			if (ultimateBajaStringCheckout[9].equals("No"))
				alimento = alimento;
			else
				alimento = alimento + ultimateBajaStringCheckout[9] + "\n";
			if (ultimateBajaStringCheckout[10].equals("No"))
				alimento = alimento;
			else
				alimento = alimento + ultimateBajaStringCheckout[10] + "\n";
			if (ultimateBajaStringCheckout[11].equals("No"))
				alimento = alimento;
			else
				alimento = alimento + ultimateBajaStringCheckout[11] + "\n";
			if (ultimateBajaStringCheckout[12].equals("No"))
				alimento = alimento;
			else
				alimento = alimento + ultimateBajaStringCheckout[12] + "\n";
			if (ultimateBajaStringCheckout[13].equals("No"))
				alimento = alimento;
			else
				alimento = alimento + ultimateBajaStringCheckout[13];
			
			tvCheckout2.setText(alimento);
			tvCheckout3.setText("\n\nSubtotal is $ " + subtotal);
			
			checkoutButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//begin_progress(v);
					CheckoutFunctions checkoutFunction = new CheckoutFunctions();
	                JSONObject json = checkoutFunction.checkoutUltimateBaja(checkID, ultimateBajaStringCheckout, PHPSESSID, getApplicationContext());
	                //Log.e("check# here @checkoutActivity", subjson.toString());
	                // check for success process
	                try {
	                    if (json.getString(SUCCESS) != null) {
	                        String res = json.getString(SUCCESS);
	                        Log.e("check# res success is ", res);
	                        if(Integer.parseInt(res) == 1){
	                        	String subtotal = json.getString(SUB_TOTAL);
	                            //Toast toast = Toast.makeText(getApplicationContext(), "Order is submitted and the total is $" + subtotal, Toast.LENGTH_SHORT);
	                            //toast.show();
	                            
	                            Intent thankyou = new Intent(getApplicationContext(), ThankYouActivity.class);
	                            //thankyou.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                            Bundle extras = new Bundle();
	                            extras.putString("total", subtotal);
	                            extras.putString("id", checkID);
	                            extras.putString("Set-Cookie", PHPSESSID);
	                            thankyou.putExtras(extras);
	                            startActivity(thankyou);
	                            //finish();
	                        }
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	                
	                // check for error process
	                try {
	                    if (json.getString(ERROR) != null) {
	                        String res1 = json.getString(ERROR);
	                        Log.e("check# res1 error is ", res1);
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
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	                //end_progress(v);
				}
			});
		}//end if baja
		
		/*
		*  University Center
		*/
		
		if (checkID.equals(UC))
		{
			ucStringCheckout = extras.getStringArray("activityKey");
			ucClassicsCheckout = extras.getStringArray("ucClassics");
			ucPriceCheckout = extras.getIntArray("price");
			PHPSESSID = extras.getString("Set-Cookie");
			
		    tvCheckout1.setText("Please review your order\n\n");
						
			String ucMeal = "";
			if (ucStringCheckout[1].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[1] + " ($ " + ucPriceCheckout[1] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[1];
			}
			if (ucStringCheckout[2].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[2] + " ($ " + ucPriceCheckout[2] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[2];
			}
			if (ucStringCheckout[3].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[3] + " ($ " + ucPriceCheckout[3] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[3];
			}
			if (ucStringCheckout[4].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[4] + " ($ " + ucPriceCheckout[4] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[4];
			}
			if (ucStringCheckout[5].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[5] + " ($ " + ucPriceCheckout[5] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[5];
			}
			if (ucStringCheckout[6].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[6] + " ($ " + ucPriceCheckout[6] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[6];
			}
			if (ucStringCheckout[7].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[7] + " ($ " + ucPriceCheckout[7] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[7];
			}
			if (ucStringCheckout[8].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[8] + " ($ " + ucPriceCheckout[8] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[8];
			}
			if (ucStringCheckout[9].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[9] + " ($ " + ucPriceCheckout[9] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[9];
			}
			if (ucStringCheckout[10].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[10] + " ($ " + ucPriceCheckout[10] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[10];
			}
			if (ucStringCheckout[11].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[11] + " ($ " + ucPriceCheckout[11] + ")" + "\n";
				subtotal = subtotal + ucPriceCheckout[11];
			}
			if (ucStringCheckout[0].equals("No"))
				ucMeal = ucMeal;
			else
			{
				ucMeal = ucMeal + ucStringCheckout[0] + " ($ " + ucPriceCheckout[0] + ")\n";
				subtotal = subtotal + ucPriceCheckout[0];
			}
			
			ucMeal = ucMeal + "\n\nSubtotal is $ " + subtotal + "\n";
			
			tvCheckout2.setText(ucMeal);
			
			String ucClass = "The classics orders:\n";
			for(int x = 0; x < ucClassicsCheckout.length; x++)
			{
				if(ucClassicsCheckout[x].matches("No"))
				{
					//does nothing
				}
				else
					ucClass = ucClass + ucClassicsCheckout[x] + "\n";
			}
			Log.e("ucClassicsCheckout", Integer.toString(ucClassicsCheckout.length));
			ucClass = "\n" + ucClass + "\nThe classics order\nwill be charged separately\n";
			
			tvCheckout3.setText(ucClass);
			
			checkoutButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//begin_progress(v);
					CheckoutFunctions checkoutFunction = new CheckoutFunctions();
	                JSONObject json = checkoutFunction.checkoutUnivCenter(checkID, ucStringCheckout, ucClassicsCheckout, PHPSESSID, getApplicationContext());
	                //Log.e("check# here @checkoutActivity", subjson.toString());
	                // check for success process
	                try {
	                    if (json.getString(SUCCESS) != null) {
	                        String res = json.getString(SUCCESS);
	                        Log.e("check# res is ", res);
	                        if(Integer.parseInt(res) == 1){
	                        	String subtotal = json.getString(SUB_TOTAL);
	                            //Toast toast = Toast.makeText(getApplicationContext(), "Order is submitted and the total is $" + subtotal, Toast.LENGTH_SHORT);
	                            //toast.show();
	                            
	                            Intent thankyou = new Intent(getApplicationContext(), ThankYouActivity.class);
	                            //thankyou.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                            Bundle extras = new Bundle();
	                            extras.putString("total", subtotal);
	                            extras.putString("id", checkID);
	                            extras.putString("Set-Cookie", PHPSESSID);
	                            thankyou.putExtras(extras);
	                            startActivity(thankyou);
	                            //finish();
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
	                    }
	                } catch (JSONException e) {
	                    e.printStackTrace();
	                }
	                //end_progress(v);
				}
			});
		}//end if UC

	}

/*	
	private void begin_progress(View v) {
		progressBar.setVisibility(v.VISIBLE);
	}
	
	private void end_progress(View v) {
		progressBar.setVisibility(v.GONE);
	}
*/	
}
