package in.qubit.credittracker.assets;

import java.util.List;

import com.parse.ParseObject;

import in.qubit.credittracker.R;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListCustomer extends ArrayAdapter<ParseObject> {
	private final Activity context;
	List<ParseObject> parseObjectList;
	public CustomListCustomer(Activity context,List<ParseObject> objects) {
		super(context, R.layout.single_list_item_drawer, objects);
		this.context = context;
		this.parseObjectList = objects;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.single_list_item_fragment_customer, null, true);
		TextView name = (TextView) rowView.findViewById(R.id.customer_list_name);
		TextView phone = (TextView) rowView.findViewById(R.id.customer_list_phone);
		
		ParseObject parObject = this.parseObjectList.get(position);
		
		name.setText(parObject.getString("name"));
		name.setTypeface(CustomTypeface.comicRelief(context));
		phone.setText(parObject.getString("phone"));
		phone.setTypeface(CustomTypeface.comicRelief(context));
		return rowView;
	}
}
