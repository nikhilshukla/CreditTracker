package in.qubit.credittracker.assets;

import in.qubit.credittracker.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {
	private final Activity context;
	private String[] web;
	private final Integer[] imageId;
	public CustomList(Activity context,String[] web, Integer[] imageId) {
		super(context, R.layout.single_list_item_drawer, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.single_list_item_drawer, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.drawer_list_text);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.drawer_list_image);
		txtTitle.setText(web[position]);
		txtTitle.setTypeface(CustomTypeface.comicRelief(context));
		imageView.setImageResource(imageId[position]);
		return rowView;
	}
}
