package in.qubit.credittracker;

import in.qubit.credittracker.R;
import in.qubit.credittracker.assets.CustomTypeface;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
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
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity{

	ActionBar actionbar;
	EditText username,password;
	Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "gCbZZE9zTTYHneADidwaNALuQA4w514bPncz0RaT", "l7ZyESQVlJloSwuezCDe22Kl4GzAVdJeXo3imJc1");
		setContentView(R.layout.activity_login);
		actionbar = getSupportActionBar();
		actionbar.hide();
		validate();
	
	}
	
	private void login(String username, String password) {
		ParseUser.logInInBackground(username, password, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {
				if(user != null) {
					Log.d("Login", "Success");
					Toast toast = Toast.makeText(getApplicationContext(), "Login Success.", 4000);
					toast.show();
					Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(mainActivity);
				}
				else {
					Log.d("Login", "Fail");
					Toast toast = Toast.makeText(getApplicationContext(), "Username or Password is incorrect.", 4000);
					toast.show();
				}
			}
			
		});
	}

	public void validate()
	{
		//Typeface type = Typeface.createFromAsset(getAssets(),"fonts/ComicRelief.ttf"); 
		username = (EditText) findViewById(R.id.user);
		password = (EditText) findViewById(R.id.pass);
		username.setTypeface(CustomTypeface.comicRelief(this));
		password.setTypeface(CustomTypeface.comicRelief(this));
		//username.setTypeface(type);
		//password.setTypeface(type);
		login = (Button) findViewById(R.id.lgoinbtn);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				login(username.getText().toString(), password.getText().toString());
				
			}
		});
		
		login.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					login.setBackground(getResources().getDrawable(R.drawable.login_button_click));
				}
				if(event.getAction() == MotionEvent.ACTION_UP) {
					login.setBackground(getResources().getDrawable(R.drawable.login_button));
				}
				return false;
			}
			
		});
		
	}
}
