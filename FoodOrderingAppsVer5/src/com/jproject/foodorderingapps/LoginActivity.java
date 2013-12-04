package com.jproject.foodorderingapps;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jproject.foodorderingapps.library.HashAlgorithm;
import com.jproject.foodorderingapps.library.UserFunctions;
import com.jproject.foodorderingapps.library.DatabaseHandler;

public class LoginActivity extends Activity {
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputAnumber;
	EditText inputPassword;
	TextView loginErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_ANUMBER = "anumber";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	private static DatabaseHandler db;

	String Acipher = "", anumber;
	String Pcipher = "", password;
	String PHPSESSID = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		db = DatabaseHandler.getDatabaseHandler(getApplicationContext());

		// Importing all assets like buttons, text fields
		inputAnumber = (EditText) findViewById(R.id.loginAnumber);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				anumber = inputAnumber.getText().toString();
				password = inputPassword.getText().toString();

				/*
				 * //check validity of A# number length if (anumber.length() ==
				 * 8) { anumber = "A" + anumber; } else { // anumber.length() <
				 * 8 anumber = ""; }
				 */

				HashAlgorithm hash = new HashAlgorithm();
				try {
					Pcipher = hash.SHA1(password);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.loginUser(anumber);

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						loginErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS);
						if (Integer.parseInt(res) == 1) {
							//grabs the encrypted password from database
							String Penc = json.getString("encrypted_password");
							//Log.e("Pcipher from JSON", Penc);
							

							//check whether password is the same as decrypted ones
							if (Pcipher.equals(Penc)) {
								// user successfully logged in
								// Store user details in SQLite Database
								//DatabaseHandler db = DatabaseHandler.getDatabaseHandler(getApplicationContext());
								JSONObject json_user = json.getJSONObject("user");
								PHPSESSID = json.getString("Set-Cookie");

								// Clear all previous data in database
								//userFunction.logoutUser(getApplicationContext());
								db.addUser(json_user.getString(KEY_ANUMBER), json_user.getString(KEY_NAME),	json_user.getString(KEY_EMAIL),	json_user.getString(KEY_CREATED_AT));
								
								// Launch Hallway Screen
								Intent hallway = new Intent(getApplicationContext(), HallwayActivity.class);

								// Close all views before launching new activity
								hallway.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								hallway.putExtra("Set-Cookie", PHPSESSID);
								startActivity(hallway);

								// Close Login Screen
								//finish();
							} else {
								// Error in login
								loginErrorMsg.setText("Incorrect password");
							}
						} else {
							// Error in login
							loginErrorMsg.setText("Incorrect A number/password");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						RegisterActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				//finish();
			}
		});
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		db.close();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		db.close();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	
}