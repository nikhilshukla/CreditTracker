package in.qubit.credittracker;

import in.qubit.credittracker.assets.CustomTypeface;

import java.util.List;
import java.util.ListIterator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class AddCustomer extends BaseActivity {
	
	android.app.ActionBar actionbar;
	Button buttonAddCustomer, test;
	EditText inputName, inputPhone, inputAddress;
	public ImageView sliderbtn;
	String name,phone,address;
	int cust_counter=0;
	int press_flag=1;
	LinearLayout but_lay;
	ParseObject object;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDrawer.setContentView(R.layout.activity_add_customer);
		actionbar = getActionBar();
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text); 
		mTitleTextView.setTypeface(CustomTypeface.helveticaLightItalic(this));
		mTitleTextView.setText("Add Customer\t");
		actionbar.setCustomView(mCustomView);
		actionbar.setDisplayShowCustomEnabled(true);
		
		sliderbtn = (ImageView) findViewById(R.id.actionslide);
		sliderbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				mDrawer.toggleMenu();
				//mDrawer.openMenu();
			}
		});
		
		inputName = (EditText)findViewById(R.id.edittext_add_customer_activity_cust_name);
		inputPhone = (EditText)findViewById(R.id.edittext_add_customer_activity_phone);
		inputAddress = (EditText)findViewById(R.id.edittext_add_customer_activity_address);
		inputName.setTypeface(CustomTypeface.comicRelief(this));
		inputPhone.setTypeface(CustomTypeface.comicRelief(this));
		inputAddress.setTypeface(CustomTypeface.comicRelief(this));
		
		buttonAddCustomer = (Button)findViewById(R.id.button_add_customer_activity_tick);
		but_lay = (LinearLayout) findViewById(R.id.button_lay);
		but_lay.setBackgroundDrawable(getResources().getDrawable(R.drawable.bottom_bar));
		buttonAddCustomer.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				if(press_flag==1)
				{
				name = inputName.getText().toString();
				phone = inputPhone.getText().toString();
				address = inputAddress.getText().toString();
				if(validate(name, phone, address)) {
					object = new ParseObject("Customers");
      				ParseQuery<ParseObject> query = ParseQuery.getQuery("Customers");
      				query.fromLocalDatastore();
					query.whereEqualTo("name", name);
					query.findInBackground(new FindCallback<ParseObject>() {
					    public void done(List<ParseObject> scoreList, ParseException e) {	    	
					        if (scoreList.isEmpty()==false) 
					        {
					            Toast.makeText(getApplicationContext(), "Customer already exists", 2000).show();
					        	Log.d("cust_name", "Retrieved " + scoreList.size() + " scores");
					        } 
					        else 
					        {
					  			object.put("name", name);
								object.put("phone", phone);
								object.put("address", address);
								object.put("userId", ParseUser.getCurrentUser().getObjectId());
								object.pinInBackground(new SaveCallback() {
									
									
									public void done(ParseException e) {
										Toast.makeText(getApplicationContext(), "Saved on device", 2000).show();
									}
								});
								object.saveEventually(new SaveCallback() {
								
									public void done(ParseException e) {
										// TODO Auto-generated method stub
										Toast.makeText(getApplicationContext(), "Saved on net", 4000).show();
									}
								});
					        }
					    }
					});
				}
				but_lay.setBackgroundDrawable(getResources().getDrawable(R.drawable.bottom_bar_dim));
			}			
				press_flag=0;
			}
		});
		
		//test = (Button)findViewById(R.id.test);
		//test.setOnClickListener(this);
	}
	
	/*public void onBackPressed() 
	{
		Intent a = new Intent(this,MainActivity.class);
		 a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		 startActivity(a);	 
	}*/
	
	private boolean validate(String name, String phone, String address) {
		if(name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
			Toast.makeText(getApplicationContext(), "Oops! Something is missing", 3000).show();
			return false;
		}
		else {
			return true;
		}
	}

}
