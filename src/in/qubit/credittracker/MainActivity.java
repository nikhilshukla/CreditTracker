package in.qubit.credittracker;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import in.qubit.credittracker.assets.CustomTypeface;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends BaseActivity {
		android.app.ActionBar actionbar;
	
	private CharSequence mTitle;
	public TextView pending;
	public Typeface type;
	public ImageView sliderbtn; 
	Button addCustBtn, addCreditBtn, listBtn;
	int moneySum = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		actionbar = getActionBar();
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text); 
		mTitleTextView.setTypeface(CustomTypeface.helveticaLightItalic(this));
		mTitleTextView.setText("Dashboard\t");
		actionbar.setCustomView(mCustomView);
		actionbar.setDisplayShowCustomEnabled(true);
		
        mDrawer.setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
        
        

		sliderbtn = (ImageView) findViewById(R.id.actionslide);
		sliderbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				mDrawer.toggleMenu();
				//mDrawer.openMenu();
			}
		});
		
		addCustBtn = (Button)findViewById(R.id.button_main_activity_add_cust);
		addCustBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent addCust = new Intent(MainActivity.this, AddCustomer.class);
				startActivity(addCust);
				mDrawer.closeMenu();
			}
			
		});
		
		addCreditBtn = (Button)findViewById(R.id.button_main_activity_add_money);
		addCreditBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent addCust = new Intent(MainActivity.this, AddCredit.class);
				startActivity(addCust);
				mDrawer.closeMenu();
			}
			
		});
		
		listBtn = (Button)findViewById(R.id.button_main_activity_list);
		listBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent listDisplay = new Intent(MainActivity.this, ListActivity.class);
				startActivity(listDisplay);
				mDrawer.closeMenu();
			}
			
		});
		
		
	
	}
	
	protected void onResume() {
		super.onResume();
		
		getBalance();
	}
	
	public void getBalance()
	{	
		pending = (TextView) findViewById(R.id.pendingmoney);
		pending.setTypeface(type);
		pending.setText("\t");
		
		final NumberFormat formater = new DecimalFormat("##,##,##,###");
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		Log.i("User Info", currentUser.getObjectId());
		
		ParseQuery<ParseObject> queryOnCredit = ParseQuery.getQuery("PendingMoney");
		queryOnCredit.fromLocalDatastore();
		queryOnCredit.whereEqualTo("userId", currentUser.getObjectId());
		queryOnCredit.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				if(e == null) {
					for(ParseObject pendingMoneyObjects : objects) {
						moneySum += pendingMoneyObjects.getInt("amount");
					}
					pending.setText(formater.format(moneySum)+"\t");
				}
			}
			
		});
	}

}
