package in.qubit.credittracker;

import com.parse.Parse;

import android.app.Application;
import android.content.res.Configuration;

public class CreditTrackerApplication extends Application {
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
 
	@Override
	public void onCreate() {
		super.onCreate();
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "gCbZZE9zTTYHneADidwaNALuQA4w514bPncz0RaT", "l7ZyESQVlJloSwuezCDe22Kl4GzAVdJeXo3imJc1");
	}
 
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
 
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}