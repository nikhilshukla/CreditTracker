package in.qubit.credittracker;

import net.simonvt.menudrawer.MenuDrawer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

@SuppressLint("NewApi")
public class BaseActivity extends Activity
{
	public MenuDrawer mDrawer;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDrawer = MenuDrawer.attach(this);
        mDrawer.setMenuView(R.layout.leftmenu);
   
        
	}
}
