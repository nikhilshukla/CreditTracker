package in.qubit.credittracker;

import java.util.List;

import in.qubit.credittracker.R;
import in.qubit.credittracker.assets.CustomTypeface;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

	ActionBar actionbar;
	EditText username,password;
	Button login, signUp;
	TextView forgotPass, newUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ParseUser currentUser = ParseUser.getCurrentUser();
		if(currentUser!=null) {
			Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(mainActivity);
		}
		setContentView(R.layout.activity_login);
		forgotPass = (TextView)findViewById(R.id.forgotPassword);
		forgotPass.setTypeface(CustomTypeface.comicRelief(this));
		newUser = (TextView)findViewById(R.id.newUser);
		newUser.setTypeface(CustomTypeface.comicRelief(this));
		signUp = (Button)findViewById(R.id.signUpButton);
		signUp.setTypeface(CustomTypeface.comicRelief(this));
		actionbar = getSupportActionBar();
		actionbar.hide();
		bind();
	
	}
	
	private void saveDataOnLocalDatabase(String userId) {
		ParseQuery<ParseObject> queryCustomers = ParseQuery.getQuery("Customers");
		queryCustomers.whereEqualTo("userId", userId);
		queryCustomers.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				if(e == null) {
					ParseObject.pinAllInBackground(objects);
				}
			}
			
		});
		
		ParseQuery<ParseObject> queryCredit = ParseQuery.getQuery("PendingMoney");
		queryCredit.whereEqualTo("userId", userId);
		queryCredit.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				if(e == null) {
					ParseObject.pinAllInBackground(objects);
				}
			}
			
		});
	}
	
	private void login(String username, String password) {
		ParseUser.logInInBackground(username, password, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {
				if(user != null) {
					saveDataOnLocalDatabase(user.getObjectId());
					Log.d("Login", "Success");
					Toast toast = Toast.makeText(getApplicationContext(), "Login Success.", 2000);
					toast.show();
					Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(mainActivity);
				}
				else {
					Log.d("Login", "Fail");
					Toast toast = Toast.makeText(getApplicationContext(), "Username or Password is incorrect.", 2000);
					toast.show();
				}
			}
			
		});
	}

	public void bind()
	{
		username = (EditText) findViewById(R.id.user);
		password = (EditText) findViewById(R.id.pass);
		username.setTypeface(CustomTypeface.comicRelief(this));
		password.setTypeface(CustomTypeface.comicRelief(this));
		login = (Button) findViewById(R.id.lgoinbtn);
		login.setTypeface(CustomTypeface.comicRelief(this));
		login.setOnClickListener(this);
		signUp.setOnClickListener(this);
		
	}
	
	private void validateAndLogin() {
		String usernameText = username.getText().toString();
		String passwordText = password.getText().toString();
		if(usernameText.isEmpty()) {
			Toast.makeText(getApplicationContext(), "Enter Username", 3000).show();
		}
		else if(passwordText.isEmpty()) {
			Toast.makeText(getApplicationContext(), "Enter Password", 3000).show();
		}
		else {
			login(usernameText, passwordText);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.lgoinbtn:
			validateAndLogin();
			Log.d("Switch", "Login Btn");
			break;
		case R.id.signUpButton:
			Log.d("Switch", "Reg Btn");
			Intent registrationActivity = new Intent(LoginActivity.this, RegistrationActivity.class);
			startActivity(registrationActivity);
			break;
		}
			
	}
}
