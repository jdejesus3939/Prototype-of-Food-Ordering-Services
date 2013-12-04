package com.jproject.foodorderingapps.library;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.net.ParseException;
import android.util.Log;


public class HTMLParser {
	
	// constructor
		public HTMLParser() {

		}
	
	public String getXmlFromUrl(String url, String PHPSESSID) {
        String xml = null;
 
        try {
        	// Create a local instance of cookie store
            CookieStore cookieStore = new BasicCookieStore();
            // HttpContext
        	HttpContext httpcontext = JSONParser.getHttpcontext();
        	httpcontext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Cookie", "PHPSESSID=" + PHPSESSID +"; path=/");
 
            HttpResponse httpResponse = httpClient.execute(httpPost,httpcontext);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);
            
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
            Log.e("Error @HTMLParser @UnsupportedL: ", e.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e("Error @HTMLParser @Client: ", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error @HTMLParser @IO @getXML: ", e.toString());
        }
        // return XML
        return xml;
    }
		
	public Document getDomElement(String xml){
		Document doc = null;
        
        try {
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        	
        	//this option is set to true
        	//it grabs &amp; as &
        	//if it is false, then, when it hits &amp; , it will drop the rest of the text
        	dbf.setCoalescing(true);
        	
        	dbf.setExpandEntityReferences(true);
            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                doc = db.parse(is);
 
            } catch (ParseException e) {
                Log.e("Error @HTMLParser @ParserConf: ", e.getLocalizedMessage());
                return null;
            } catch (ParserConfigurationException e) {
                Log.e("Error @HTMLParser @ParserConf: ", e.getLocalizedMessage());
                return null;
            } catch (SAXException e) {
                Log.e("Error @HTMLParser @SAX: ", e.getLocalizedMessage());
                return null;
            } catch (IOException e) {
                Log.e("Error @HTMLParser @IO @getDom: ", e.getLocalizedMessage());
                return null;
            }
                // return DOM
            return doc;
	}
	 
/*
	public String getValue(Element elem, String key) {
		NodeList n = elem.getElementsByTagName(key);
		return n.item(0).getNodeValue();
	}
*/
	
	public String getValue(Element item, String str) {
	    NodeList n = item.getElementsByTagName(str);        
	    return this.getElementValue(n.item(0));
	}
	 
	public final String getElementValue( Node elem ) {
	         Node child;
	         if( elem != null){
	             if (elem.hasChildNodes()){
	                 for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
	                     if( child.getNodeType() == Node.TEXT_NODE  ){
	                    	 Log.e("childNodeValue",child.getNodeValue());
	                         return child.getNodeValue();
	                     }
	                 }
	             }
	         }
	         return "";
	  } 
}
