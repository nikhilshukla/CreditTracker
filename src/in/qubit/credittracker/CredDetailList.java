package in.qubit.credittracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	List<String> nlist;
	List<Date> dlist; 
	List<Integer> mlist;
	List<String> sdlist,smlist;
	ListView mainlist;
	
	@SuppressWarnings("null")
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
		/*nlist =  new ArrayList<String>();
		dlist = new ArrayList<Date>();
		mlist =  new ArrayList<Integer>();
		smlist = new ArrayList<String>();
		sdlist = new ArrayList<String>();
		
		for(ParseObject pobj : creditObjectList)
		{
			mlist.add(pobj.getInt("amount"));       //data in integer
			nlist.add(pobj.getString("notes"));     //data in string
			dlist.add(pobj.getDate("createdAt"));   //data in date
		}
		
		Iterator itr1 = mlist.iterator();
		while (itr1.hasNext()) 
		{
			smlist.add(String.valueOf(itr1.next()));
			
		}
		DateFormat df = new SimpleDateFormat("MMM dd,yyyy, HH:mm");
		Iterator itr2 = dlist.iterator();
		while (itr2.hasNext()) 
		{
			sdlist.add(df.format(itr2.next()));
			
		}
		
		
		String[] moneylist =   smlist.toArray(new String[smlist.size()]);
		String[] notelist =  nlist.toArray(new String[nlist.size()]);
		String[] datelist =  sdlist.toArray(new String[sdlist.size()]);
		
			
		adapter = new ListViewCustomAdapter(this, notelist, moneylist, datelist);
        mainlist.setAdapter(adapter);*/

	}
	


}

