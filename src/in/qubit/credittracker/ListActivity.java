package in.qubit.credittracker;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import in.qubit.credittracker.assets.CustomList;
import in.qubit.credittracker.assets.CustomListCredit;
import in.qubit.credittracker.assets.CustomListCustomer;
import in.qubit.credittracker.assets.CustomTypeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class ListActivity extends BaseActivity implements ActionBar.TabListener {

	 /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    ViewPager mViewPager;
    
    ImageView sliderbtn;
    TextView mTitleTextView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        //actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        
        
        
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text); 
		mTitleTextView.setTypeface(CustomTypeface.helveticaLightItalic(this));
		mTitleTextView.setText("Credit\t");
		actionBar.setCustomView(mCustomView);
		actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mDrawer.setContentView(R.layout.activity_list);
		
		//View homeIcon = findViewById(R.id.home);
		//homeIcon.setVisibility(View.GONE);
		actionBar.setLogo(null);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
		View homeIcon = findViewById(
	            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? 
	                    android.R.id.home : android.support.v7.appcompat.R.id.home);
	    ((View) homeIcon.getParent()).setVisibility(View.GONE);
	    ((View) homeIcon).setVisibility(View.GONE);
		
		try { actionBar.setIcon(R.drawable.translogo); }
		catch(Exception e) {
			
		}
		
        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });
        
        LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_tab_credit, null);
        LinearLayout customerView = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_tab_customer, null);
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
        	if(i==0)
            actionBar.addTab(
                    actionBar.newTab()
                            //.setText(mAppSectionsPagerAdapter.getPageTitle(i))
                    		.setCustomView(view)
                            .setTabListener(this));
        	else 
        		actionBar.addTab(
                        actionBar.newTab()
                                //.setText(mAppSectionsPagerAdapter.getPageTitle(i))
                        		.setCustomView(customerView)
                                .setTabListener(this));
        }
        
        sliderbtn = (ImageView) findViewById(R.id.actionslide);
		sliderbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				mDrawer.toggleMenu();
				//mDrawer.openMenu();
			}
		});
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
        int tabPos = tab.getPosition();
        if(tabPos==0) {
            mTitleTextView.setText("Credit\t");
        }
        else if(tabPos==1) {
        	mTitleTextView.setText("Customers\t");
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    // The first section of the app is the most interesting -- it offers
                    // a launchpad into the other demonstrations in this example application.
                    return new LaunchpadSectionFragment();

                default:
                    // The other sections of the app are dummy placeholders.
                    Fragment fragment = new DummySectionFragment();
                    /*Bundle args = new Bundle();
                    args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    fragment.setArguments(args);*/
                    return fragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        /*@Override
        public CharSequence getPageTitle(int position) {
            return "Section " + (position + 1);
        }*/
    }

    /**
     * A fragment that launches other parts of the demo application.
     */
    public static class LaunchpadSectionFragment extends Fragment {

    	ListView list;
    	
    	List<ParseObject> objects, objectsCompleteList;
    	
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	ParseQuery<ParseObject> queryOnCustomer = ParseQuery.getQuery("Customers");
        	queryOnCustomer.fromLocalDatastore();
        	List<ParseObject> customerObjectList = null;
        	try {
				customerObjectList = queryOnCustomer.find();
				Log.i("Customer Find", customerObjectList.toString());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	ParseQuery<ParseObject> queryOnCredit = ParseQuery.getQuery("PendingMoney");
        	queryOnCredit.fromLocalDatastore();
        	
        	ParseObject tempParseObject = null;
        	
        	for(ParseObject customerParseObject : customerObjectList) {
        		tempParseObject = new ParseObject("AmountSum");
        		double sum=0;
        		queryOnCredit.whereContains("customerId", customerParseObject.getString("name"));
        		List<ParseObject> creditObjectList = null;
        		try {
					creditObjectList = queryOnCredit.find();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		for(ParseObject tempP : creditObjectList) {
        			sum+=tempP.getDouble("amount");
        		}
        		Log.i("Sum of Cost: "+customerParseObject.getString("name"), Double.toString(sum));
        		tempParseObject.put("customerId", customerParseObject.getString("name"));
        		tempParseObject.put("amount", sum);
        		
        		customerObjectList.add(tempParseObject);
        	}
        	
        	
        	/*ParseQuery<ParseObject> query = ParseQuery.getQuery("PendingMoney");
    		query.fromLocalDatastore();
    			try {
					objects = query.find();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
        	
            View rootView = inflater.inflate(R.layout.fragment_credit_list, container, false);
            
            CustomListCredit adapter = new CustomListCredit(getActivity(), customerObjectList);
            list=(ListView)rootView.findViewById(R.id.fragment_list_credit);
            list.setAdapter(adapter);
            
            

            return rootView;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "section_number";
        
        ListView list;
        List<ParseObject> objects;
        
        String[] a = {
    		    "Avdhesh Panwar",
    		      "Abhishek Upperwal",
    		      "Gaurav Gupta",
    		      "Anurag Sharma",
    		      "Pankaj Kumar",
    		      "Brijesh Panwar"
    		  } ;
    	
    	String[] b = {
    		    "9999647130",
    		      "9853744263",
    		      "7825485725",
    		      "9455654632",
    		      "7343465453",
    		      "9835927395"
    		  } ;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_customers_list, container, false);
            
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Customers");
    		query.fromLocalDatastore();
    			try {
					objects = query.find();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            
            CustomListCustomer adapter = new CustomListCustomer(getActivity(), objects);
            list=(ListView)rootView.findViewById(R.id.fragment_list_customers);
            list.setAdapter(adapter);
            
           // Bundle args = getArguments();
           // ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    //getString(R.string.dummy_section_text, args.getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
}