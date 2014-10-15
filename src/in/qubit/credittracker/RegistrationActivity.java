package in.qubit.credittracker;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import in.qubit.credittracker.assets.CustomTypeface;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends ActionBarActivity implements OnClickListener {
	
	Button registrationButton;
	ActionBar actionBar;
	EditText emailInput, nameInput, phoneInput, passwordInput;
	
	String username, password, name, phone;
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		dialog = new ProgressDialog(this);
		
		registrationButton = (Button)findViewById(R.id.button_register);
		registrationButton.setTypeface(CustomTypeface.comicRelief(this));
		
		actionBar = getSupportActionBar();
		actionBar.hide();
		
		emailInput = (EditText)findViewById(R.id.edittext_email_reg);
		nameInput = (EditText)findViewById(R.id.edittext_name_reg);
		phoneInput = (EditText)findViewById(R.id.edittext_phone_reg);
		passwordInput = (EditText)findViewById(R.id.edittext_password_reg);
		setTypeface();
		
		registrationButton.setOnClickListener(this);
	}
	
	private void setTypeface() {
		emailInput.setTypeface(CustomTypeface.comicRelief(this));
		nameInput.setTypeface(CustomTypeface.comicRelief(this));
		phoneInput.setTypeface(CustomTypeface.comicRelief(this));
		passwordInput.setTypeface(CustomTypeface.comicRelief(this));
	}
	
	private void validate() {
		username = emailInput.getText().toString();
		password = passwordInput.getText().toString();
		name = nameInput.getText().toString();
		phone = phoneInput.getText().toString();
		
		if(username.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()) {
			Toast toast = Toast.makeText(getApplicationContext(), "Please Complete the Form.", 4000);
			toast.show();
		}
		else if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
			Toast.makeText(getApplicationContext(), "Please enter a valid email address.", 4000).show();
		}
		else {
			register();
		}
	}
	
	private void register() {
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMessage("Thank you for Registring. There you go...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
		
		ParseUser user = new ParseUser();
		
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(username);
		user.put("phone", phone);
		user.signUpInBackground(new SignUpCallback() {
			
			@Override
			public void done(ParseException e) {
				// TODO Auto-generated method stub
				if(e == null) {
					dialog.dismiss();
					Toast toast = Toast.makeText(getApplicationContext(), "Registration Successful", 2000);
					toast.show();
					Intent mainActivity = new Intent(RegistrationActivity.this, MainActivity.class);
					startActivity(mainActivity);
				}
				else {
					dialog.dismiss();
					Log.e("RegAct", e.getMessage());
					Toast toast = Toast.makeText(getApplicationContext(), "Registration Failed", 2000);
					toast.show();
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.button_register:
			validate();
			break;	
		}
	}
	
}
