package in.qubit.credittracker;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import in.qubit.credittracker.R;
import in.qubit.credittracker.assets.CustomListCredit;
import in.qubit.credittracker.assets.CustomTypeface;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CredDetailList extends BaseActivity
{
	ActionBar actionbar;
	String user;
	Bundle extras;
	public ImageView sliderbtn;
	TextView mTitleTextView;
	List notelist,datelist,moneylist;
	ListView money;
	
	@SuppressWarnings("null")
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	mDrawer.setContentView(R.layout.credit_list_detail);
	ActionBar actionbar = getActionBar();
	actionbar.setDisplayShowHomeEnabled(false);
	actionbar.setDisplayShowTitleEnabled(false);
	extras = getIntent().getExtras();
	user= extras.getString("selected");
	String[] mlist;
	
	LayoutInflater mInflater = LayoutInflater.from(this);

	View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
	mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text); 
	mTitleTextView.setTypeface(CustomTypeface.helveticaLightItalic(this));
	mTitleTextView.setText(user+"\t");
	actionbar.setCustomView(mCustomView);
	actionbar.setDisplayShowCustomEnabled(true);
	
	money = (ListView) findViewById(R.id.fragment_list_credit_detail);
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
		queryOnCredit.fromLocalDatastore();
	
		ParseObject tempParseObject = null;
		queryOnCredit.whereContains("customerId", user);
		List<ParseObject> creditObjectList = null;
		try {
			creditObjectList = queryOnCredit.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notelist = new LinkedList();
		moneylist = new LinkedList();
		datelist = new LinkedList();
		
		for(ParseObject pobj : creditObjectList)
		{
			moneylist.add(pobj.getInt("amount"));
			notelist.add(pobj.getString("notes"));
			datelist.add(pobj.getDate("createdAt"));
		}
		
		//mlist =  moneylist.toArray(new String[moneylist.size()]);
		ArrayAdapter<String> listAdapter = new CustomListAdapter(this, R.layout.single_list_item_credit_detail);
	}
	
	class CustomListAdapter extends ArrayAdapter<String> {
	    public CustomListAdapter(Context context, int textViewResourceId) {
	        super(context, textViewResourceId);
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
       
	        ((TextView)convertView.findViewById(R.id.credit_list_notes)).setText(getItem(position));
	        ((TextView)convertView.findViewById(R.id.credit_list_date)).setText(getItem(position));
	        ((TextView)convertView.findViewById(R.id.credit_list_amount)).setText(getItem(position));
	        
	        
	        return convertView;
	    }
	}


}

