package com.jproject.foodorderingapps;

import org.json.JSONException;
import org.json.JSONObject;

import com.jproject.foodorderingapps.library.UserFunctions;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HallwayActivity extends ListActivity {
	
	String PHPSESSID = null;
	int number = 0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // storing string resources into Array
        String[] food_products = getResources().getStringArray(R.array.food_products);
        
        // Binding Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, food_products));
        
        ListView lv = getListView();
        
        // Get the login session id
     	Intent i = getIntent();
     	PHPSESSID = i.getStringExtra("Set-Cookie");

        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	  
        	// selected item
        	  String product = ((TextView) view).getText().toString();
        	  
        	  char num = product.charAt(0);
        	  
        	  switch (num) {
        	  case '1':
        		      // Launching new Activity on selecting single List Item
    	              Intent univcenterInfo = new Intent(getApplicationContext(), UnivCenterInfoActivity.class);
    	              //univcenterInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	      // sending data to new activity
    	              univcenterInfo.putExtra("Set-Cookie",PHPSESSID);
            	      univcenterInfo.putExtra("prod", product);
            	      //Log.e("Set-Cookie",PHPSESSID);
    	              startActivity(univcenterInfo);
    	              break;
        	  case '2':
        	          // Launching new Activity on selecting single List Item
        	          Intent subwayInfo = new Intent(getApplicationContext(), SubwayInfoActivity.class);
        	          //subwayInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                	  // sending data to new activity
        	          subwayInfo.putExtra("Set-Cookie",PHPSESSID);
                	  subwayInfo.putExtra("prod", product);
                	  //Log.e("Set-Cookie",PHPSESSID);
        	          startActivity(subwayInfo);
        	          break;
        	  case '3':
        		      // Launching new Activity on selecting single List Item
    	              Intent einsteinbrosInfo = new Intent(getApplicationContext(), EinsteinBrosInfoActivity.class);
    	              //einsteinbrosInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	      // sending data to new activity
    	              einsteinbrosInfo.putExtra("Set-Cookie",PHPSESSID);
            	      einsteinbrosInfo.putExtra("prod", product);
            	      //Log.e("Set-Cookie",PHPSESSID);
    	              startActivity(einsteinbrosInfo);
    	              break;
        	  case '4':
        		      // Launching new Activity on selecting single List Item
    	              Intent ultimatebajaInfo = new Intent(getApplicationContext(), UltimateBajaInfoActivity.class);
    	              //ultimatebajaInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	      // sending data to new activity
    	              ultimatebajaInfo.putExtra("Set-Cookie",PHPSESSID);
            	      ultimatebajaInfo.putExtra("prod", product);
            	      //Log.e("Set-Cookie",PHPSESSID);
    	              startActivity(ultimatebajaInfo);
    	              break;
        	  case '5':
        		      // Launching new Activity on selecting single List Item
    	              Intent simplytogoInfo = new Intent(getApplicationContext(), SimplyToGoInfoActivity.class);
    	              //simplytogoInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	      // sending data to new activity
    	              simplytogoInfo.putExtra("Set-Cookie",PHPSESSID);
            	      simplytogoInfo.putExtra("prod", product);
            	      //Log.e("Set-Cookie",PHPSESSID);
    	              startActivity(simplytogoInfo);
    	              break;
        	  case '6':
        	    	  // Launching new Activity on selecting single List Item
        	          Intent starbucksInfo = new Intent(getApplicationContext(), StarbucksInfoActivity.class);
        	          //starbucksInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                	  // sending data to new activity
        	          starbucksInfo.putExtra("Set-Cookie",PHPSESSID);
                	  starbucksInfo.putExtra("prod", product);
                	  //Log.e("Set-Cookie",PHPSESSID);
        	          startActivity(starbucksInfo);
        	          break;
        	  default:
        	    	  break;
        	  }
          }
        });
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		UserFunctions userFunctions = new UserFunctions();
		userFunctions.logoutUser(getApplicationContext());
		
		Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
        // Closing this screen
        finish();
        
		return true;
	}
}