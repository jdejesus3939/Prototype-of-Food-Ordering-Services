package com.jproject.foodorderingapps;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jproject.foodorderingapps.library.ClearHistory;
import com.jproject.foodorderingapps.library.HTMLParser;
import com.jproject.foodorderingapps.library.InfoRetrieved;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class UnivCenterMainActivity extends Activity {
	
	private TableLayout queryTableLayout, queryTableLayout1;
		
	private CheckBox checkPepperoni,
					 checkSupreme,
				 	 checkVeggie,
				 	 checkHamburger,
					 checkCheeseburger,
					 checkDoubleCB,
					 checkTurkeyMelts,
					 checkChikenburger,
					 checkChickenburgerNOPK,
					 checkChickenNuggets6,
					 checkChickenNuggets12,
					 checkYesDrink;

	private TextView pricePepperoni,
					 priceSupreme,
					 priceVeggie,
					 priceHamburger,
					 priceCheeseburger,
					 priceDoubleCB,
					 priceTurkeyMelts,
					 priceChickenburger,
					 priceChickenburgerNOPK,
					 priceChickenNuggets6,
					 priceChickenNuggets12,
					 priceYes,
					 univcenterOrderHistory;
	
	private Button btnUCProceed,
				   btnUCClearHistory;
	
	private static String SUCCESS = "success";
	private static String ERROR = "error";
	
	int x = 0, counter = 0;
	int[] ucPrice = new int[12];
	String [] ucString = new String [12];  //stores the selected menus by the user
	String [] tempClassics = new String [15];  //stores the retrieved classics order from URL
	String [] ucClassics = new String [15];  //stores the checked value on classics order from application
	String uctvhistory;
	String [] ucHistoryString = new String[12];  //stores the uc history
	String [] ucclHistoryString = new String[15];  //stores the uc classics history
	String checkID = "uc";
	String PHPSESSID = null;
	

	private static String myClassicsURL = "http://jproject.podserver.info/android_api_login/uc_classics_main.php";
	private static String KEY_ITEM = "item";
	private static String KEY_SPAN = "span";
	
	Node child;


	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.univcentermain);
		
		Intent myIntent = getIntent();
		PHPSESSID = myIntent.getStringExtra("Set-Cookie");
		//Log.e("Set-Cookie",PHPSESSID);
		
		//initialize uc classics string array to "No"
		for(int i=0; i<ucClassics.length; i++)
		{
			ucClassics[i] = "No";
			tempClassics[i] = "No";
			ucclHistoryString[i] = "No";
		}
		
		for(int i=0; i<ucHistoryString.length; i++)
		{
			ucHistoryString[i] = "No";
		}
		
		//retrieving price from database
		InfoRetrieved price = new InfoRetrieved();
	    JSONObject json = price.pricelist("uc", PHPSESSID, getApplicationContext());
	    Log.e("check@UC price retrived", json.toString());
	    // check for success process
	    try {
	        if (json.getString(SUCCESS) != null) {
	            String res = json.getString(SUCCESS);
	            Log.e("check@UC price retrived is ", res);
	            if(Integer.parseInt(res) == 1){
	            	ucPrice[0] = Integer.parseInt(json.getString("drink"));
	            	ucPrice[1] = Integer.parseInt(json.getString("pizzaA"));
	            	ucPrice[2] = Integer.parseInt(json.getString("pizzaB"));
	            	ucPrice[3] = Integer.parseInt(json.getString("pizzaC"));
	            	ucPrice[4] = Integer.parseInt(json.getString("burgerA"));
	            	ucPrice[5] = Integer.parseInt(json.getString("burgerB"));
	            	ucPrice[6] = Integer.parseInt(json.getString("burgerC"));
	            	ucPrice[7] = Integer.parseInt(json.getString("burgerD"));
	            	ucPrice[8] = Integer.parseInt(json.getString("CAburger"));
	            	ucPrice[9] = Integer.parseInt(json.getString("CAburgerNOPK"));
	            	ucPrice[10] = Integer.parseInt(json.getString("CAnuggets6"));
	            	ucPrice[11] = Integer.parseInt(json.getString("CAnuggets12"));
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
	    
	    //retrieving order history from database
	    InfoRetrieved history = new InfoRetrieved();
	    JSONObject jsonhist = history.orderhistorylist("uc", PHPSESSID, getApplicationContext());
	    Log.e("check# here @UC hist retrived", jsonhist.toString());
	    uctvhistory = "Your last order with this vendor was:\n";
	    // check for success process
	    try {
	        if (jsonhist.getString("uc_success") != null) {
	            String uchist = jsonhist.getString("uc_success");
	            
	            if(Integer.parseInt(uchist) == 1){
	            	Log.e("check# UC hist retrived is ", uchist);
	            	ucHistoryString[0] = jsonhist.getString("drink");
	            	ucHistoryString[1] = jsonhist.getString("pizzaA");
	            	ucHistoryString[2] = jsonhist.getString("pizzaB");
	            	ucHistoryString[3] = jsonhist.getString("pizzaC");
	            	ucHistoryString[4] = jsonhist.getString("burgerA");
	            	ucHistoryString[5] = jsonhist.getString("burgerB");
	            	ucHistoryString[6] = jsonhist.getString("burgerC");
	            	ucHistoryString[7] = jsonhist.getString("burgerD");
	            	ucHistoryString[8] = jsonhist.getString("CAburger");
	            	ucHistoryString[9] = jsonhist.getString("CAburgerNOPK");
	            	ucHistoryString[10] = jsonhist.getString("CAnuggets6");
	            	ucHistoryString[11] = jsonhist.getString("CAnuggets12");
	            
		            //getting all the history order
		            for(int i=0 ; i<ucHistoryString.length; i++)
		            {
		            	if(!ucHistoryString[i].equals("No"))
		            	{
		            		uctvhistory = uctvhistory + ucHistoryString[i] + "\n";
		            	}
		            }
	            }
	            
	            if(Integer.parseInt(uchist) == 0){
	            	Log.e("check@UC fail-hist-retrived", uchist);
	            	uctvhistory = uctvhistory + "No history UC order is retrieved\n";
	            }
	            
	        }
	        
	        if (jsonhist.getString("uc_cl_success") != null) {
	            String ucclhist = jsonhist.getString("uc_cl_success");
	            
	            if(Integer.parseInt(ucclhist) == 1){
	            	Log.e("check# UC CL hist retrived is ", ucclhist);
	            	ucclHistoryString[0] = jsonhist.getString("classics0");
	            	ucclHistoryString[1] = jsonhist.getString("classics1");
	            	ucclHistoryString[2] = jsonhist.getString("classics2");
	            	ucclHistoryString[3] = jsonhist.getString("classics3");
	            	ucclHistoryString[4] = jsonhist.getString("classics4");
	            	ucclHistoryString[5] = jsonhist.getString("classics5");
	            	ucclHistoryString[6] = jsonhist.getString("classics6");
	            	ucclHistoryString[7] = jsonhist.getString("classics7");
	            	ucclHistoryString[8] = jsonhist.getString("classics8");
	            	ucclHistoryString[9] = jsonhist.getString("classics9");
	            	ucclHistoryString[10] = jsonhist.getString("classics10");
	            	ucclHistoryString[11] = jsonhist.getString("classics11");
	            	ucclHistoryString[12] = jsonhist.getString("classics12");
	            	ucclHistoryString[13] = jsonhist.getString("classics13");
	            	ucclHistoryString[14] = jsonhist.getString("classics14");
	            
	            	//getting all the history order
		            for(int i=0 ; i<ucclHistoryString.length; i++)
		            {
		            	if(!ucclHistoryString[i].equals("No"))
		            	{
		            		uctvhistory = uctvhistory + ucclHistoryString[i] + "\n";
		            	}
		            }
	            }
	            
	            if(Integer.parseInt(ucclhist) == 0){
	            	Log.e("check@UC CL fail-hist-retrived", ucclhist);
	            	uctvhistory = uctvhistory + "No history UC Classics order is retrieved\n";
	            }
	            
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	    univcenterOrderHistory = (TextView) findViewById(R.id.univcenterOrderHistory);

	    
	    queryTableLayout = (TableLayout) findViewById(R.id.queryTableLayout);
	    queryTableLayout1 = (TableLayout) findViewById(R.id.queryTableLayout1);
	    
	    //get the id of price text view
	    pricePepperoni = (TextView) findViewById(R.id.pricePepperoni);
		priceSupreme = (TextView) findViewById(R.id.priceSupreme);
		priceVeggie = (TextView) findViewById(R.id.priceVeggie);
		priceHamburger = (TextView) findViewById(R.id.priceHamburger);
		priceCheeseburger = (TextView) findViewById(R.id.priceCheeseburger);
		priceDoubleCB = (TextView) findViewById(R.id.priceDoubleCB);
		priceTurkeyMelts = (TextView) findViewById(R.id.priceTurkeyMelts);
		priceChickenburger = (TextView) findViewById(R.id.priceChickenburger);
		priceChickenburgerNOPK = (TextView) findViewById(R.id.priceChickenburgerNOPK);
		priceChickenNuggets6 = (TextView) findViewById(R.id.priceChickenNuggets6);
		priceChickenNuggets12 = (TextView) findViewById(R.id.priceChickenNuggets12);
		priceYes = (TextView) findViewById(R.id.priceYes);
	    
		//get the id of selected check button
		checkPepperoni = (CheckBox) findViewById(R.id.checkPepperoni);
		checkSupreme = (CheckBox) findViewById(R.id.checkSupreme);
		checkVeggie = (CheckBox) findViewById(R.id.checkVeggie);
		checkHamburger = (CheckBox) findViewById(R.id.checkHamburger);
		checkCheeseburger = (CheckBox) findViewById(R.id.checkCheeseburger);
		checkDoubleCB = (CheckBox) findViewById(R.id.checkDoubleCB);
		checkTurkeyMelts = (CheckBox) findViewById(R.id.checkTurkeyMelts);
		checkChikenburger = (CheckBox) findViewById(R.id.checkChikenburger);
		checkChickenburgerNOPK = (CheckBox) findViewById(R.id.checkChickenburgerNOPK);
		checkChickenNuggets6 = (CheckBox) findViewById(R.id.checkChickenNuggets6);
		checkChickenNuggets12 = (CheckBox) findViewById(R.id.checkChickenNuggets12);
		checkYesDrink = (CheckBox) findViewById(R.id.checkYes);
		
		univcenterOrderHistory.setText(uctvhistory);
		
		//set the price value
		pricePepperoni.setText("$ " + ucPrice[1]);
		priceSupreme.setText("$ " + ucPrice[2]);
		priceVeggie.setText("$ " + ucPrice[3]);
		priceHamburger.setText("$ " + ucPrice[4]);
		priceCheeseburger.setText("$ " + ucPrice[5]);
		priceDoubleCB.setText("$ " + ucPrice[6]);
		priceTurkeyMelts.setText("$ " + ucPrice[7]);
		priceChickenburger.setText("$ " + ucPrice[8]);
		priceChickenburgerNOPK.setText("$ " + ucPrice[9]);
		priceChickenNuggets6.setText("$ " + ucPrice[10]);
        priceChickenNuggets12.setText("$ " + ucPrice[11]);
		priceYes.setText("$ " + ucPrice[0]);
		
		/*
		//commented out as for now the uc website is not active
		//parsing html from the UC classics
		HTMLParser parser = new HTMLParser();
        String xml = parser.getXmlFromUrl(myClassicsURL,PHPSESSID); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element
        //Log.e("Document", doc.toString());
        
        //creating nodelist for every html nodes
        NodeList nl = doc.getElementsByTagName(KEY_ITEM);
        //Log.e("NodeList", nl.toString());
        
        //dynamically generated checkboxes
        for (int i = 0; i < nl.getLength(); i++) {
        	Element e = (Element) nl.item(i);
        	tempClassics[i] = parser.getValue(e, KEY_SPAN);
        	makeTagGUI(tempClassics[i], i);
        	//Log.e("ucClassics", ucClassics[i]);
        }
		*/
        
        
        //Log.e("tempClassics[1]",tempClassics[1]);
        //checks whether tempClassics are empty or default value ("No")
        for (int i = 0; i<tempClassics.length; i++)
        {
        	if(tempClassics[i].equals("No"))
        	{
        		counter++;
        	}
        }
        	
        //Log.e("counter",String.valueOf(counter));
        
        //if tempClassics is empty or default value, then
        //set TextView says "no classics menus are available"
        if(counter >= 13)
        {
        	//set no menu available
        	LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		
    		View newTagView = inflater.inflate(R.layout.new_tv_view,null);
    		
    		TextView textviewClassicsTag = (TextView) newTagView.findViewById(R.id.textviewClassicsTag);
    		textviewClassicsTag.setText("No classics menu is available");
    		
    		queryTableLayout1.addView(newTagView);
        }
        	
        
		btnUCProceed = (Button) findViewById(R.id.btnUCProceed);
		
		btnUCProceed.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub				
				//check box, if checked set the string
				if (!checkPepperoni.isChecked() && !checkSupreme.isChecked() && !checkVeggie.isChecked() &&
					!checkHamburger.isChecked() && !checkCheeseburger.isChecked() && !checkDoubleCB.isChecked() &&
					!checkTurkeyMelts.isChecked() && !checkChikenburger.isChecked() && !checkChickenburgerNOPK.isChecked() &&
					!checkChickenNuggets6.isChecked() && !checkChickenNuggets12.isChecked() && !checkYesDrink.isChecked())
					{
						AlertDialog.Builder alert = new AlertDialog.Builder(UnivCenterMainActivity.this);
			            alert.setTitle("Error"); 
			            alert.setMessage("Select at least one menu");
			            alert.setPositiveButton("OK", null); 
			            alert.show();
					}
					else
					{
						if(checkYesDrink.isChecked())
							ucString[0] = "Yes (with drink)";
						else ucString[0] = "No";
						if(checkPepperoni.isChecked())
							ucString[1] = "Pepperoni pizza";
						else ucString[1] = "No";
						if(checkSupreme.isChecked())
							ucString[2] = "Supreme pizza";
						else ucString[2] = "No";
						if(checkVeggie.isChecked())
							ucString[3] = "Veggie pizza";
						else ucString[3] = "No";
						if(checkHamburger.isChecked())
							ucString[4] = "Hamburger";
						else ucString[4] = "No";
						if(checkCheeseburger.isChecked())
							ucString[5] = "Cheeseburger";
						else ucString[5] = "No";
						if(checkDoubleCB.isChecked())
							ucString[6] = "Double cheeseburger";
						else ucString[6] = "No";
						if(checkTurkeyMelts.isChecked())
							ucString[7] = "Turkey melts";
						else ucString[7] = "No";
						if(checkChikenburger.isChecked())
							ucString[8] = "Chick-fil-A burger";
						else ucString[8] = "No";
						if(checkChickenburgerNOPK.isChecked())
							ucString[9] = "Chick-fil-A burger no PK";
						else ucString[9] = "No";
						if(checkChickenNuggets6.isChecked())
							ucString[10] = "Chick-fil-A nuggets 6 pcs";
						else ucString[10] = "No";
						if(checkChickenNuggets12.isChecked())
							ucString[11] = "Chick-fil-A nuggets 12 pcs";
						else ucString[11] = "No";
						
						//indicator for checkout activity
						String intentID = "uc";
						
						Intent ucCheckout = new Intent(getApplicationContext(), CheckoutActivity.class);
						//ucCheckout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Bundle extras = new Bundle();
						//putExtra to the Bundle
						extras.putString("intentKey", intentID);
						extras.putString("Set-Cookie", PHPSESSID);
						extras.putStringArray("activityKey", ucString);
						extras.putStringArray("ucClassics", ucClassics);
						extras.putIntArray("price", ucPrice);
						ucCheckout.putExtras(extras);
						startActivity(ucCheckout);
				}
			}
			
		});
		
		
		btnUCClearHistory = (Button) findViewById(R.id.btnUCClearHistory);
		
		btnUCClearHistory.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClearHistory clearHistory = new ClearHistory();
                JSONObject json = clearHistory.clearUnivCenter(checkID, PHPSESSID, getApplicationContext());
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

	
	private void makeTagGUI(String tag, int index) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View newTagView = inflater.inflate(R.layout.new_tag_view,null);
		
		CheckBox checkClassicsTag = (CheckBox) newTagView.findViewById(R.id.checkClassicsTag);
		checkClassicsTag.setText(tag);
		checkClassicsTag.setOnCheckedChangeListener(queryCheckListener);

		queryTableLayout.addView(newTagView, index);
	}
	

	//it listens to whenever the checkbox is checked or unchecked
	public OnCheckedChangeListener queryCheckListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
			// TODO Auto-generated method stub
			String selected = buttonView.getText().toString();
			
			if(isChecked)
			{	
				for(int i=0 ; i<ucClassics.length; i++)
				{
					if(ucClassics[i].equals("No"))
					{
						ucClassics[i] = selected;
						break;
					}
				}
				//Log.e("isChecked", "true block");
				//Log.e("isChecked selected", selected);
				//Log.e("isChecked ucClassics.length", Integer.toString(ucClassics.length));
			}
			else
			{
				for(int i=0 ; i<ucClassics.length; i++)
				{
					if(ucClassics[i].equals(selected))
					{
						ucClassics[i] = "No";
						break;
					}
				}
				
				//Log.e("isChecked not", selected);
				//Log.e("isChecked", "false block");
			}
			
		}
	};


	/* (non-Javadoc)
	 * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
	 */
	//it reloads the UC Classics menu whenever the orientation
	//is changed (horizontal or vertical)
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		//everything I need is handled by this line
		//-->it re-makes the inflater check boxes
		//-->it also includes the status of the check boxes (whether it is checked or not)
		super.onConfigurationChanged(newConfig);
		
		// Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    	//dynamically generated checkboxes
	        for (int i = 0; i < tempClassics.length; i++) {
	        	if(tempClassics[i].matches("No"))
	        	{
	        		//does nothing
	        	}
	        	else
	        	{
	        		//it should NOT re-making the inflater check box
	        		//does nothing
	        		//makeTagGUI(tempClassics[i], i);
	        	}
	        	//Log.e("ucClassics", ucClassics[i]);
	        }
	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	    	//dynamically generated checkboxes
	        for (int i = 0; i < tempClassics.length; i++) {
	        	if(tempClassics[i].matches("No"))
	        	{
	        		//does nothing
	        	}
	        	else
	        	{
	        		//it should NOT re-making the inflater check box
	        		//does nothing
	        		//makeTagGUI(tempClassics[i], i);
	        	}
	        	//Log.e("ucClassics", ucClassics[i]);
	        }
	    }
	}


}