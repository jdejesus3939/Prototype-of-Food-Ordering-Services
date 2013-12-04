package com.jproject.foodorderingapps.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class ClearHistory {

	private JSONParser jsonParser;
	
	private static String ClearHistoryURL = "http://jproject.podserver.info/android_api_login/clear_history.php";
	
	String name = null;
	String anumber = null;
	
    // constructor
    public ClearHistory(){
    	jsonParser = new JSONParser();
    }

    /**
     * Function checkout process 
     */
    public JSONObject clearEinsteinBros (String ID_KEY, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	
    	JSONObject json = jsonParser.getJSONFromUrl(ClearHistoryURL, PHPSESSID, params);
    	// return json
        Log.e("JSON Einstein @checkoutFunction.java", json.toString());
        
        return json;
    }

    /**
     * Function checkout process 
     */
    public JSONObject clearSubway (String ID_KEY, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	
    	JSONObject json = jsonParser.getJSONFromUrl(ClearHistoryURL, PHPSESSID, params);
    	// return json
        Log.e("JSON subway @checkoutFunction.java", json.toString());
        
        return json;
    }

    /**
     * Function checkout process 
     */
    public JSONObject clearSimplyToGo (String ID_KEY, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	
    	JSONObject json = jsonParser.getJSONFromUrl(ClearHistoryURL, PHPSESSID, params);
    	// return json
        Log.e("JSON simplytogo @checkoutFunction.java", json.toString());
        
        return json;
    }
    
    /**
     * Function checkout process 
     */
    public JSONObject clearStarbucks (String ID_KEY, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	
    	JSONObject json = jsonParser.getJSONFromUrl(ClearHistoryURL, PHPSESSID, params);
    	// return json
        Log.e("JSON starbucks @checkoutFunction.java", json.toString());
        
        return json;
    }
    
    /**
     * Function checkout process 
     */
    public JSONObject clearUltimateBaja (String ID_KEY, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	//Log.e("the name", name);
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	
    	JSONObject json = jsonParser.getJSONFromUrl(ClearHistoryURL, PHPSESSID, params);
    	// return json
        Log.e("JSON ultimate baja @checkoutFunction.java", json.toString());
        
        return json;
    }

    /**
     * Function checkout process 
     */
    public JSONObject clearUnivCenter (String ID_KEY, String PHPSESSID, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	
    	JSONObject json = jsonParser.getJSONFromUrl(ClearHistoryURL, PHPSESSID, params);
    	// return json
        Log.e("JSON UnivCenter @checkoutFunction.java", json.toString());
        
        return json;
    }

	
}
