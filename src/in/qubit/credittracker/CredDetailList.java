package in.qubit.credittracker;

import java.util.Date;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import in.qubit.credittracker.R;
import in.qubit.credittracker.assets.CustomTypeface;
import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CredDetailList extends BaseActivity
{
	ActionBar actionbar;
	String user;
	Bundle extras;
	public ImageView sliderbtn;
	TextView mTitleTextView;
	List<String> nlist;
	List<Date> dlist; 
	List<Integer> mlist;
	List<String> sdlist,smlist;
	ListView mainlist;
	
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	mDrawer.setContentView(R.layout.credit_list_detail);
	ActionBar actionbar = getActionBar();
	actionbar.setDisplayShowHomeEnabled(false);
	actionbar.setDisplayShowTitleEnabled(false);
	extras = getIntent().getExtras();
	user= extras.getString("selected");
	ListViewCustomAdapter adapter;
	
	LayoutInflater mInflater = LayoutInflater.from(this);

	View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
	mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text); 
	mTitleTextView.setTypeface(CustomTypeface.helveticaLightItalic(this));
	mTitleTextView.setText(user+"\t");
	actionbar.setCustomView(mCustomView);
	actionbar.setDisplayShowCustomEnabled(true);
	
	mainlist = (ListView) findViewById(R.id.fragment_list_credit_detail);
	sliderbtn = (ImageView) findViewById(R.id.actionslide);
	sliderbtn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			mDrawer.toggleMenu();
			//mDrawer.openMenu();
		}
	});

		ParseQuery<ParseObject> queryOnCredit = ParseQuery.getQuery("PendingMoney");
		queryOnCredit.orderByDescending("createdAt");
		queryOnCredit.fromLocalDatastore();
		queryOnCredit.whereContains("customerId", user);
		List<ParseObject> creditObjectList = null;
		try {
			creditObjectList = queryOnCredit.find();
			Log.i("Credit Detail(Note):", creditObjectList.get(0).getString("notes"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		adapter = new ListViewCustomAdapter(this, creditObjectList);
        mainlist.setAdapter(adapter);

	}
	


}

