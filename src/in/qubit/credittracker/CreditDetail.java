package in.qubit.credittracker;

import in.qubit.credittracker.ListActivity.AppSectionsPagerAdapter;
import in.qubit.credittracker.assets.CustomTypeface;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreditDetail extends BaseActivity 
{
        AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	    ViewPager mViewPager;
	    
	    ImageView sliderbtn;
	    TextView mTitleTextView;

	
	public void onCreate(Bundle savedInstanceState)
	{
    super.onCreate(savedInstanceState);
    

    // Create the adapter that will return a fragment for each of the three primary sections
    // of the app.
    mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

    // Set up the action bar.
    final ActionBar actionBar = getActionBar();

    
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
}