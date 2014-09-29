package in.qubit.credittracker;

import in.qubit.credittracker.assets.CustomTypeface;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddCustomer extends BaseActivity implements OnClickListener {
	
	android.app.ActionBar actionbar;
	Button buttonAddCustomer, test;
	EditText inputName, inputPhone, inputAddress;
	public ImageView sliderbtn;
	
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
		buttonAddCustomer.setOnClickListener(this);
		
		//test = (Button)findViewById(R.id.test);
		//test.setOnClickListener(this);
	}
	
	private boolean validate(String name, String phone, String address) {
		if(name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
			Toast.makeText(getApplicationContext(), "Oops! Something is missing", 3000).show();
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.button_add_customer_activity_tick:
			String name = inputName.getText().toString();
			String phone = inputPhone.getText().toString();
			String address = inputAddress.getText().toString();
			
			if(validate(name, phone, address)) {
				ParseObject object = new ParseObject("Customers");
				object.put("name", name);
				object.put("phone", phone);
				object.put("address", address);
				object.pinInBackground(new SaveCallback() {
					
					@Override
					public void done(ParseException e) {
						Toast.makeText(getApplicationContext(), "Saved on device", 2000).show();
					}
				});
				object.saveEventually(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Saved on net", 4000).show();
					}
					
				});
			}
			break;
//		case R.id.test:
//			ParseQuery<ParseObject> query = ParseQuery.getQuery("Customers");
//			query.fromLocalDatastore();
//			query.findInBackground(new FindCallback<ParseObject>() {
//
//				@Override
//				public void done(List<ParseObject> arg0, ParseException arg1) {
//					// TODO Auto-generated method stub
//					for(ParseObject object : arg0) {
//						Toast.makeText(getApplicationContext(), (CharSequence) object.getObjectId(), 4000).show();
//					}
//				}
//				
//			});
		}
	}
}
