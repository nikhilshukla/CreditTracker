package in.qubit.credittracker;

import in.qubit.credittracker.assets.CustomTypeface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class AddCredit extends BaseActivity implements OnClickListener {
	
	android.app.ActionBar actionbar;
	Button buttonAddCredit, test;
	AutoCompleteTextView inputName;
	EditText inputAmount, inputNotes;
	public ImageView sliderbtn;
	ArrayAdapter<String> adapter;
	List<ParseObject> obj;
	ProgressDialog dialog;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		dialog = new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
		
		mDrawer.setContentView(R.layout.activity_add_credit);
		actionbar = getActionBar();
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text); 
		mTitleTextView.setTypeface(CustomTypeface.helveticaLightItalic(this));
		mTitleTextView.setText("Add Credit\t\t");
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
				dialog.dismiss();
				Toast.makeText(getApplicationContext(), "Oops! Something is missing", 3000).show();
				return false;
			}
			else {
				return true;
			}
		}
		else {
			dialog.dismiss();
			Toast.makeText(getApplicationContext(), "Please choose customer name from suggestion.", 3000).show();
			return false;
		}
	}
	
	/*public void onBackPressed() 
	{
		Intent a = new Intent(this,MainActivity.class);
		 a.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		 finish();
		 startActivity(a);	 
	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.button_add_credit_activity_tick:
			String name = inputName.getText().toString();
			String amount = inputAmount.getText().toString();
			String notes = inputNotes.getText().toString();
			
			SimpleDateFormat creditDate = new SimpleDateFormat("dd MMMM yyyy", Locale.UK);
			
			if(validate(name, amount)) {
				dialog.setMessage("Adding Credit to this Customer.");
		        dialog.show();
		        
				ParseObject object = new ParseObject("PendingMoney");
				object.put("customerId", name);
				object.put("amount", Float.parseFloat(amount));
				object.put("notes", notes);
				object.put("creditDate", creditDate.format(new Date()));
				object.put("userId", ParseUser.getCurrentUser().getObjectId());
				object.pinInBackground(new SaveCallback() {
					
					@Override
					public void done(ParseException e) {
						dialog.dismiss();
						Toast.makeText(getApplicationContext(), "Credit Successfully Added.", 2000).show();
						finish();
						Intent in = new Intent(AddCredit.this,MainActivity.class);
						startActivity(in);
					}
				});
				object.saveEventually(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Credit Saved on Cloud.", 2000).show();
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
