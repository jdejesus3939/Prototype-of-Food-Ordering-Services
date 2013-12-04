package com.jproject.foodorderingapps.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
 
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.util.Log;
 
public class JSONParser {

	private static HttpContext httpcontext = null;
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
 
    // constructor
    public JSONParser() {
 
    }
 
    public static HttpContext getHttpcontext() {
        if( httpcontext == null ){
            JSONParser.setHttpcontext(new BasicHttpContext());
        }
        return httpcontext;
    }

    public static void setHttpcontext(HttpContext httpcontext) {
        JSONParser.httpcontext = httpcontext;
    }
       
    
    public JSONObject getJSONFromUrl(String url, String PHPSESSID, List<NameValuePair> params) {
    	 
        // Making HTTP request
        try {
        	// Create a local instance of cookie store
            CookieStore cookieStore = new BasicCookieStore();
            // HttpContext
        	HttpContext httpcontext = JSONParser.getHttpcontext();
        	httpcontext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            // defaultHttpClient
        	DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            httpPost.setHeader("Cookie", "PHPSESSID=" + PHPSESSID +"; path=/");
            
            
            //Log.e("reach here #1 UHUI", httpPost.toString());
            
            HttpResponse httpResponse = httpClient.execute(httpPost,httpcontext);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            
            /*
            Header [] header = httpResponse.getAllHeaders();
            for (int i=0; i<header.length; i++)
            {
            	if(header[i].getName().equals("Set-Cookie"))
            	{
            		Log.e("valueheader"+i, header[i].getValue());
            	}
            	//Log.e("nameheader"+i, header[i].getName());
            	//the 6th-indexed value is the PHPSESSID
            	//Log.e("valueheader"+i, header[i].getValue());
            }
            */
            
            //Log.e("header6th", header[6].getValue());
            //Log.e("httpResponse", httpResponse.toString());
            //Log.e("httpResponseLocale", httpResponse.getLocale().toString());
            //Log.e("httpResponseStatusLine", httpResponse.getStatusLine().toString());
            //Log.e("httpEntity", httpEntity.toString());
            //Log.e("IS", is.toString());
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("HTTP req #1 @JSONParser.java", "#1 " + e.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e("HTTP req #2 @JSONParser.java", "#2 " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("HTTP req #3 @JSONParser.java", "#3 " + e.toString());
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
            Log.e("JSON test# @JSONParser.java", json);
        } catch (Exception e) {
        	e.printStackTrace();
            Log.e("Buffer Error @JSONParser.java", "Error converting result " + e.toString());
        }
        
        //Log.e("BEFORE JSON Parser @JSONParser.java", "Error parsing data " + e.toString());
        
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
        	e.printStackTrace();
            Log.e("JSON Parser @JSONParser.java", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
    
    
    public JSONObject androidLogin(String url, List<NameValuePair> params) {
    	 
        // Making HTTP request
        try {
        	// Create a local instance of cookie store
            CookieStore cookieStore = new BasicCookieStore();
            // HttpContext
        	HttpContext httpcontext = JSONParser.getHttpcontext();
        	httpcontext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            // defaultHttpClient
        	DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            
            //Log.e("reach here #1", httpPost.toString());
            
            HttpResponse httpResponse = httpClient.execute(httpPost,httpcontext);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            
            Header [] header = httpResponse.getAllHeaders();
            for (int i=0; i<header.length; i++)
            {
            	if(header[i].getName().equals("Set-Cookie"))
            	{
            		Log.e("LOGINvalueheader"+i, header[i].getValue());
            	}
            	//Log.e("nameheader"+i, header[i].getName());
            	//the 6th-indexed value is the PHPSESSID
            	//Log.e("valueheader"+i, header[i].getValue());
            }
            
            //Log.e("header6th", header[6].getValue());
            //Log.e("httpResponse", httpResponse.toString());
            //Log.e("httpResponseLocale", httpResponse.getLocale().toString());
            //Log.e("httpResponseStatusLine", httpResponse.getStatusLine().toString());
            //Log.e("httpEntity", httpEntity.toString());
            //Log.e("IS", is.toString());
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("LOGIN HTTP req #1 @JSONParser.java", "#1 " + e.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e("LOGIN HTTP req #2 @JSONParser.java", "#2 " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("LOGIN HTTP req #3 @JSONParser.java", "#3 " + e.toString());
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
            Log.e("LOGIN JSON test# @JSONParser.java", json);
        } catch (Exception e) {
        	e.printStackTrace();
            Log.e("LOGIN Buffer Error @JSONParser.java", "Error converting result " + e.toString());
        }
        
        //Log.e("BEFORE JSON Parser @JSONParser.java", "Error parsing data " + e.toString());
        
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
        	e.printStackTrace();
            Log.e("LOGIN JSON Parser @JSONParser.java", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
    
    
    public JSONObject androidRegister(String url, List<NameValuePair> params) {
   	 
        // Making HTTP request
        try {
        	// Create a local instance of cookie store
            CookieStore cookieStore = new BasicCookieStore();
            // HttpContext
        	HttpContext httpcontext = JSONParser.getHttpcontext();
        	httpcontext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            // defaultHttpClient
        	DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            
            //Log.e("reach here #1 UHUI", httpPost.toString());
            
            HttpResponse httpResponse = httpClient.execute(httpPost,httpcontext);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            
            //Log.e("reach here #2 UHUI", is.toString());
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("REG HTTP req #1 @JSONParser.java", "#1 " + e.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e("REG HTTP req #2 @JSONParser.java", "#2 " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("REG HTTP req #3 @JSONParser.java", "#3 " + e.toString());
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
            Log.e("REG JSON test# @JSONParser.java", json);
        } catch (Exception e) {
        	e.printStackTrace();
            Log.e("REG Buffer Error @JSONParser.java", "Error converting result " + e.toString());
        }
        
        //Log.e("stop 1 @JSONParser.java", "Error parsing data " + json);
        
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
        	e.printStackTrace();
            Log.e("REG JSON Parser @JSONParser.java", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
    
}