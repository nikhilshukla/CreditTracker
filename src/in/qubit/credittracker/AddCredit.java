package in.qubit.credittracker;

import in.qubit.credittracker.assets.CustomListCredit;
import in.qubit.credittracker.assets.CustomTypeface;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddCredit extends BaseActivity implements OnClickListener {
	
	android.app.ActionBar actionbar;
	Button buttonAddCredit, test;
	AutoCompleteTextView inputName;
	EditText inputAmount, inputNotes;
	public ImageView sliderbtn;
	ArrayAdapter<String> adapter;
	List<ParseObject> obj;
	
	String[] androidBooks = 
		{
		"Hello, Android - Ed Burnette",
		"Professional Android 2 App Dev - Reto Meier",
		"Unlocking Android - Frank Ableson",
		"Android App Development - Blake Meike",
		"Pro Android 2 - Dave MacLean",
		"Beginning Android 2 - Mark Murphy",
		"Android Programming Tutorials - Mark Murphy",
		"Android Wireless App Development - Lauren Darcey",
		"Pro Android Games - Vladimir Silva",
		};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDrawer.setContentView(R.layout.activity_add_credit);
		actionbar = getActionBar();
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text); 
		mTitleTextView.setTypeface(CustomTypeface.helveticaLightItalic(this));
		mTitleTextView.setText("Add Credit\t");
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
		
		inputName = (AutoCompleteTextView)findViewById(R.id.autocomplete_add_credit_activity_cust_name);
		inputAmount = (EditText)findViewById(R.id.edittext_add_credit_activity_amount);
		inputNotes = (EditText)findViewById(R.id.edittext_add_credit_activity_notes);
		inputName.setTypeface(CustomTypeface.comicRelief(this));
		inputAmount.setTypeface(CustomTypeface.comicRelief(this));
		inputNotes.setTypeface(CustomTypeface.comicRelief(this));
		
		buttonAddCredit = (Button)findViewById(R.id.button_add_credit_activity_tick);
		buttonAddCredit.setOnClickListener(this);
		
		
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);
		inputName.setThreshold(1);
		inputName.setAdapter(adapter);
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Customers");
		query.fromLocalDatastore();
		try {
			obj = query.find();
			for(ParseObject object : obj) {
				adapter.add(object.getString("name"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test = (Button)findViewById(R.id.test);
		//test.setOnClickListener(this);
	}
	
	private boolean checkForCustomerInDatabase(String name) {
		List<ParseObject> obj;
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Customers");
		query.whereEqualTo("name", name);
		query.fromLocalDatastore();
		try {
			obj = query.find();
			if(obj.size()>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(ParseException e) {
			return false;
		}
	}
	
	private boolean validate(String name, String amount) {
		if(checkForCustomerInDatabase(name)) {
			if(name.isEmpty() || amount.isEmpty()) {
				Toast.makeText(getApplicationContext(), "Oops! Something is missing", 3000).show();
				return false;
			}
			else {
				return true;
			}
		}
		else {
			Toast.makeText(getApplicationContext(), "Please choose customer name from suggestion.", 3000).show();
			return false;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.button_add_credit_activity_tick:
			String name = inputName.getText().toString();
			String amount = inputAmount.getText().toString();
			String notes = inputNotes.getText().toString();
			
			if(validate(name, amount)) {
				ParseObject object = new ParseObject("PendingMoney");
				object.put("customerId", name);
				object.put("amount", Float.parseFloat(amount));
				object.put("notes", notes);
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