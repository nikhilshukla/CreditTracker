package in.qubit.credittracker.assets;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import in.qubit.credittracker.R;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListCredit extends ArrayAdapter<ParseObject> {
	private final Activity context;
	List<ParseObject> parseObjectList;
	String[] name;
	final NumberFormat formater = new DecimalFormat("##,##,##,###.##");
	
	public CustomListCredit(Activity context, List<ParseObject> objects) {
		super(context, R.layout.single_list_item_drawer, objects);
		//Log.i("Object SIze", Integer.toString(objects.size()));
		this.context = context;
		//this.parseObjectList = objects;
		//this.name = name;
		this.parseObjectList = objects;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.single_list_item_fragment_credit, null, true);
		TextView name = (TextView) rowView.findViewById(R.id.credit_list_name);
		TextView date = (TextView) rowView.findViewById(R.id.credit_list_date);
		TextView amount = (TextView) rowView.findViewById(R.id.credit_list_amount);
		
		ParseObject parObject = this.parseObjectList.get(position);
		name.setText(parObject.getString("customerId"));
		name.setTypeface(CustomTypeface.comicRelief(context));
		date.setText(parObject.getString("date"));
		date.setTypeface(CustomTypeface.comicRelief(context));
		amount.setText(formater.format(parObject.getDouble("amount")));
		amount.setTypeface(CustomTypeface.comicRelief(context));
		
		
		return rowView;
	}
}
