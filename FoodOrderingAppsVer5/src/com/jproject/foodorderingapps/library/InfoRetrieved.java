package com.jproject.foodorderingapps.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class InfoRetrieved {
	
	private JSONParser jsonParser;
	
	private static String PriceURL = "http://jproject.podserver.info/android_api_login/pricelist_retrieved.php";
	private static String OrderHistoryURL = "http://jproject.podserver.info/android_api_login/orderhistory_retrieved.php";
	
	String name = null;
	String anumber = null;
	
    // constructor
    public InfoRetrieved(){
    	jsonParser = new JSONParser();
    }

    /**
     * Function checkout process 
     */
    public JSONObject pricelist (String ID_KEY, String mySession, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	
    	JSONObject json = jsonParser.getJSONFromUrl(PriceURL, mySession, params);
    	// return json
        //Log.e("JSON pricelist @PriceRetrieved.java", json.toString());
        
        return json;
    }
    
    public JSONObject orderhistorylist (String ID_KEY, String mySession, Context context)
    {
    	DatabaseHandler db = DatabaseHandler.getDatabaseHandler(context);
    	name = db.getUserName();
    	anumber = db.getAnumber();
    	
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("vendor", ID_KEY));
    	params.add(new BasicNameValuePair("name", name));
    	params.add(new BasicNameValuePair("anumber", anumber));
    	
    	JSONObject json = jsonParser.getJSONFromUrl(OrderHistoryURL, mySession, params);
    	// return json
        //Log.e("JSON orderhistorylist @InfoRetrieved.java", json.toString());
        
        return json;
    }
    
}