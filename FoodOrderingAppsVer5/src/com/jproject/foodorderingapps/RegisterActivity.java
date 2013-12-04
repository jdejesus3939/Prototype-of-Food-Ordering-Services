package com.jproject.foodorderingapps;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;
 
import com.jproject.foodorderingapps.library.HashAlgorithm;
import com.jproject.foodorderingapps.library.UserFunctions;
import com.jproject.foodorderingapps.library.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
public class RegisterActivity extends Activity {
    Button btnRegister;
    Button btnLinkToLogin;
    EditText inputAnumber;
    EditText inputFullName;
    EditText inputEmail;
    EditText inputPassword;
    TextView registerErrorMsg;
 
    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_ANUMBER = "anumber";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
    
    private static DatabaseHandler db;
    
    String Acipher = "", anumber = null;
    String Ncipher = "", name = null;
    String Ecipher = "", email = null;
    String Pcipher = "", password = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
 
        // Importing all assets like buttons, text fields
        inputAnumber = (EditText) findViewById(R.id.registerAnumber);
        inputFullName = (EditText) findViewById(R.id.registerName);
        inputEmail = (EditText) findViewById(R.id.registerEmail);
        inputPassword = (EditText) findViewById(R.id.registerPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        registerErrorMsg = (TextView) findViewById(R.id.register_error);
 
        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	anumber = inputAnumber.getText().toString();
                name = inputFullName.getText().toString();
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();

                
                HashAlgorithm hash = new HashAlgorithm();
                try {
                	//Acipher = hash.SHA1(anumber);
                	//Ncipher = hash.SHA1(name);
					//Ecipher = hash.SHA1(email);
					Pcipher = hash.SHA1(password);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                Log.e("registration anumber", anumber);
                Log.e("registration name", name);
                Log.e("registration email", email);
                Log.e("registration Pcipher", Pcipher);
                
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.registerUser(anumber, name, email, Pcipher);
 
                // check for login response
                try {
                    if (json.getString(KEY_SUCCESS) != null) {
                        registerErrorMsg.setText("");
                        String res = json.getString(KEY_SUCCESS);
                        if(Integer.parseInt(res) == 1){
                            // user successfully registered
                            // Store user details in SQLite Database
                            db = DatabaseHandler.getDatabaseHandler(getApplicationContext());
                            JSONObject json_user = json.getJSONObject("user");
 
                            // Clear all previous data in database
                            //userFunction.logoutUser(getApplicationContext());
                            db.addUser(json_user.getString(KEY_ANUMBER), json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json_user.getString(KEY_CREATED_AT));
                            // Launch Dashboard Screen
                            Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
                            // Close all views before launching Dashboard
                            dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(dashboard);
                            // Close Registration Screen
                            finish();
                        }else{
                            // Error in registration
                            registerErrorMsg.setText("Error occurred in registration");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
 
        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                // Close Registration View
                finish();
            }
        });
    }
}