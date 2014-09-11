package in.qubit.credittracker;

import in.qubit.credittracker.assets.CustomTypeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;

public class RegistrationActivity extends ActionBarActivity {
	
	Button registrationButton;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		registrationButton = (Button)findViewById(R.id.button_register);
		registrationButton.setTypeface(CustomTypeface.comicRelief(this));
		
		actionBar = getSupportActionBar();
		actionBar.hide();
	}
	
}
