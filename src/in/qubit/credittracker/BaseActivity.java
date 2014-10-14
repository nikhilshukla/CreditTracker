package in.qubit.credittracker;

import java.util.List;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import in.qubit.credittracker.assets.CustomList;
import net.simonvt.menudrawer.MenuDrawer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class BaseActivity extends FragmentActivity
{
	public MenuDrawer mDrawer;
	
	ListView list;
	  String[] web = {
	    "Dashboard",
	      "Add Customer",
	      "Add Credit",
	      "SMS",
	      "Settings",
	      "Logout"
	  } ;
	  Integer[] imageId = {
	      R.drawable.icon_dashboard,
	      R.drawable.icon_drawer_add_cust,
	      R.drawable.icon_drawer_add_credit,
	      R.drawable.icon_drawer_sms,
	      R.drawable.icon_drawer_setting,
	      R.drawable.icon_drawer_logout
	  };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDrawer = MenuDrawer.attach(this);
        mDrawer.setMenuView(R.layout.leftmenu);
        mDrawer.setBackgroundColor(1);
   
        CustomList adapter = new CustomList(BaseActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.listDrawer);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				ActivityManager am = (ActivityManager)BaseActivity.this.getSystemService(ACTIVITY_SERVICE);
				List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);
				String currentActivity = taskInfo.get(0).topActivity.getClassName();
				switch(position) {
				
				case 0:
					if(currentActivity.compareTo(MainActivity.class.getName())!=0) {
						Intent mainActivity = new Intent(BaseActivity.this, MainActivity.class);
						startActivity(mainActivity);
					}
					mDrawer.closeMenu();
					break;
				case 1:
					if(currentActivity.compareTo(AddCustomer.class.getName())!=0) {
						Intent addCustomerActivity = new Intent(BaseActivity.this, AddCustomer.class);
						addCustomerActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(addCustomerActivity);
					}
					mDrawer.closeMenu();
					break;
				case 2:
					if(currentActivity.compareTo(AddCredit.class.getName())!=0) {
						Intent addCreditActivity = new Intent(BaseActivity.this, AddCredit.class);
						addCreditActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(addCreditActivity);
					}
					mDrawer.closeMenu();
					break;
				case 5:
					try {
						ParseObject.unpinAll();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ParseUser.logOut();
					Intent loginActivity = new Intent(BaseActivity.this, LoginActivity.class);
					startActivity(loginActivity);
					finish();
					break;
				}
			}
        });
	}
}
