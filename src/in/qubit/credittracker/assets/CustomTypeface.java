package in.qubit.credittracker.assets;

import android.app.Activity;
import android.graphics.Typeface;

public class CustomTypeface {
	public static Typeface comicRelief(Activity act) {
		Typeface type = Typeface.createFromAsset(act.getAssets(), "fonts/ComicRelief.ttf");
		return type;
	}
}
