package com.jproject.foodorderingapps.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class CheckoutFunctions {
	
	private JSONParser jsonParser;
	
	private static String SubCheckoutURL = "http://jproject.podserver.info/android_api_login/checkout_subway.php";
	private static String EinsCheckoutURL = "http://jproject.podserver.info/android_api_login/checkout_einstein.php";
	private static String SimCheckoutURL = "http://jproject.podserver.info/android_api_login/checkout_simplytogo.php";
	private static String StarCheckoutURL = "http://jproject.podserver.info/android_api_login/checkout_starbucks.php";
	private static String BajaCheckoutURL = "http://jproject.podserver.info/android_api_login/checkout_baja.php";
	private static String UCCheckoutURL = "http://jproject.podserver.info/android_api_login/checkout_uc.php";
	
	String name = null;
	String anumber = null;
	
    // constructor
    public CheckoutFunctions(){
    	jsonParser = new JSONParser();
    }

    /**
     * Function checkout process 
     */
    public JSONObject checkoutEinsteinBros (String ID_KEY, String [] items, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	params.add(new BasicNameValuePair("bagelA",items[0]));
    	params.add(new BasicNameValuePair("bagelB",items[1]));
    	params.add(new BasicNameValuePair("bagelC",items[2]));
    	params.add(new BasicNameValuePair("sandwichA",items[3]));
    	params.add(new BasicNameValuePair("sandwichB",items[4]));
    	params.add(new BasicNameValuePair("sandwichC",items[5]));
    	params.add(new BasicNameValuePair("brownieA",items[6]));
    	params.add(new BasicNameValuePair("brownieB",items[7]));
    	params.add(new BasicNameValuePair("brownieC",items[8]));
    	params.add(new BasicNameValuePair("muffinA",items[9]));
    	params.add(new BasicNameValuePair("muffinB",items[10]));
    	params.add(new BasicNameValuePair("muffinC",items[11]));
    	//Log.e("check#7", "till this far");
    	
    	JSONObject json = jsonParser.getJSONFromUrl(EinsCheckoutURL, PHPSESSID, params);
    	// return json
        Log.e("JSON Einstein @checkoutFunction.java", json.toString());
        
        return json;
    }

    /**
     * Function checkout process 
     */
    public JSONObject checkoutSubway (String ID_KEY, String [] items, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	params.add(new BasicNameValuePair("foot",items[0]));
    	params.add(new BasicNameValuePair("bread",items[1]));
    	params.add(new BasicNameValuePair("flavor",items[2]));
    	params.add(new BasicNameValuePair("cheese",items[3]));
    	params.add(new BasicNameValuePair("sauce",items[4]));
    	params.add(new BasicNameValuePair("drink",items[5]));
    	params.add(new BasicNameValuePair("chips",items[6]));
    	params.add(new BasicNameValuePair("cookies",items[7]));
    	
    	params.add(new BasicNameValuePair("lettuce",items[8]));
    	params.add(new BasicNameValuePair("tomatoes",items[9]));
    	params.add(new BasicNameValuePair("cucumbers",items[10]));
    	params.add(new BasicNameValuePair("pickles",items[11]));
    	params.add(new BasicNameValuePair("bellpeppers",items[12]));
    	params.add(new BasicNameValuePair("olives",items[13]));
    	params.add(new BasicNameValuePair("redonions",items[14]));
    	params.add(new BasicNameValuePair("jalapenopeppers",items[15]));
    	params.add(new BasicNameValuePair("bananapeppers",items[16]));
    	//Log.e("check#5", items[10]);
    	params.add(new BasicNameValuePair("spinach",items[17]));
    	//Log.e("check#6", items[11]);
    	params.add(new BasicNameValuePair("salt",items[18]));
    	params.add(new BasicNameValuePair("pepper",items[19]));
    	//Log.e("check#7", "till this far");
    	
    	JSONObject json = jsonParser.getJSONFromUrl(SubCheckoutURL, PHPSESSID, params);
    	// return json
        Log.e("JSON subway @checkoutFunction.java", json.toString());
        
        return json;
    }

    /**
     * Function checkout process 
     */
    public JSONObject checkoutSimplyToGo (String ID_KEY, String [] items, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	params.add(new BasicNameValuePair("sandwichA",items[0]));
    	params.add(new BasicNameValuePair("sandwichB",items[1]));
    	params.add(new BasicNameValuePair("sandwichC",items[2]));
    	params.add(new BasicNameValuePair("snackA",items[3]));
    	params.add(new BasicNameValuePair("snackB",items[4]));
    	params.add(new BasicNameValuePair("snackC",items[5]));
    	params.add(new BasicNameValuePair("drinkA",items[6]));
    	params.add(new BasicNameValuePair("drinkB",items[7]));
    	params.add(new BasicNameValuePair("drinkC",items[8]));
    	//Log.e("check#7", "till this far");
    	
    	JSONObject json = jsonParser.getJSONFromUrl(SimCheckoutURL, PHPSESSID, params);
    	// return json
        Log.e("JSON simplytogo @checkoutFunction.java", json.toString());
        
        return json;
    }
    
    /**
     * Function checkout process 
     */
    public JSONObject checkoutStarbucks (String ID_KEY, String [] items, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	params.add(new BasicNameValuePair("drink1A",items[0]));
    	params.add(new BasicNameValuePair("drink1B",items[1]));
    	params.add(new BasicNameValuePair("drink1C",items[2]));
    	params.add(new BasicNameValuePair("drink2A",items[3]));
    	params.add(new BasicNameValuePair("drink2B",items[4]));
    	params.add(new BasicNameValuePair("drink2C",items[5]));
    	params.add(new BasicNameValuePair("drink3A",items[6]));
    	params.add(new BasicNameValuePair("drink3Ashot",items[7]));
    	params.add(new BasicNameValuePair("drink3B",items[8]));
    	params.add(new BasicNameValuePair("drink3Bshot",items[9]));
    	params.add(new BasicNameValuePair("drink3C",items[10]));
    	params.add(new BasicNameValuePair("drink3Cshot",items[11]));
    	params.add(new BasicNameValuePair("drink4A",items[12]));
    	params.add(new BasicNameValuePair("drink4B",items[13]));
    	params.add(new BasicNameValuePair("drink4C",items[14]));
    	//Log.e("check#7", "till this far");
    	
    	JSONObject json = jsonParser.getJSONFromUrl(StarCheckoutURL, PHPSESSID, params);
    	// return json
        Log.e("JSON starbucks @checkoutFunction.java", json.toString());
        
        return json;
    }
    
    /**
     * Function checkout process 
     */
    public JSONObject checkoutUltimateBaja (String ID_KEY, String [] items, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	//Log.e("the name", name);
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	params.add(new BasicNameValuePair("meal",items[0]));
    	params.add(new BasicNameValuePair("groundbeef",items[1]));
    	params.add(new BasicNameValuePair("steak",items[2]));
    	params.add(new BasicNameValuePair("chicken",items[3]));
    	params.add(new BasicNameValuePair("pork",items[4]));
    	params.add(new BasicNameValuePair("rice",items[5]));
    	params.add(new BasicNameValuePair("beans",items[6]));
    	params.add(new BasicNameValuePair("lettuce",items[7]));
    	params.add(new BasicNameValuePair("tomatoes",items[8]));
    	params.add(new BasicNameValuePair("redonion",items[9]));
    	Log.e("check#5", items[0]);
    	params.add(new BasicNameValuePair("cucumber",items[10]));
    	//Log.e("check#6", items[11]);
    	params.add(new BasicNameValuePair("salsa",items[11]));
    	params.add(new BasicNameValuePair("picodegallo",items[12]));
    	params.add(new BasicNameValuePair("creamcheese",items[13]));
    	
    	//Log.e("check#7", "till this far");
    	
    	JSONObject json = jsonParser.getJSONFromUrl(BajaCheckoutURL, PHPSESSID, params);
    	// return json
        Log.e("JSON ultimate baja @checkoutFunction.java", json.toString());
        
        return json;
    }

    /**
     * Function checkout process 
     */
    public JSONObject checkoutUnivCenter (String ID_KEY, String [] items, String [] classics, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	params.add(new BasicNameValuePair("drink",items[0]));
    	params.add(new BasicNameValuePair("pepperoni",items[1]));
    	params.add(new BasicNameValuePair("supreme",items[2]));
    	params.add(new BasicNameValuePair("veggie",items[3]));
    	params.add(new BasicNameValuePair("hamburger",items[4]));
    	params.add(new BasicNameValuePair("cheeseburger",items[5]));
    	params.add(new BasicNameValuePair("dcheeseburger",items[6]));
    	params.add(new BasicNameValuePair("turkey",items[7]));
    	params.add(new BasicNameValuePair("CAburger",items[8]));
    	params.add(new BasicNameValuePair("CAburgerNOPK",items[9]));
    	params.add(new BasicNameValuePair("CAnuggets6",items[10]));
    	params.add(new BasicNameValuePair("CAnuggets12",items[11]));
    	//Log.e("check#7", "till this far");
    	
    	//adding the classics
    	for(int i=0; i<classics.length; i++)
    		params.add(new BasicNameValuePair("tag"+Integer.toString(i),classics[i]));
    	
    	JSONObject json = jsonParser.getJSONFromUrl(UCCheckoutURL, PHPSESSID, params);
    	// return json
        Log.e("JSON UnivCenter @checkoutFunction.java", json.toString());
        
        return json;
    }

}
