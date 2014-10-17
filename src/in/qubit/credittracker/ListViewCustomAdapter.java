package in.qubit.credittracker;

import in.qubit.credittracker.assets.CustomTypeface;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewCustomAdapter extends BaseAdapter
{

	public Activity context;
	public LayoutInflater inflater;
	private List<ParseObject> _creditOfSingleCustomer;
	final NumberFormat formater = new DecimalFormat("##,##,##,###.##");

	public ListViewCustomAdapter(Activity context, List<ParseObject> creditOfSingleCustomer) {
		super();

		this.context = context;
		this._creditOfSingleCustomer = creditOfSingleCustomer;
		
	    this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static class ViewHolder
	{
		TextView txtViewdate;
		TextView txtViewnote;
		TextView txtViewmoney;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.single_list_item_credit_detail, null);

			holder.txtViewdate = (TextView) convertView.findViewById(R.id.credit_list_date);
			holder.txtViewnote = (TextView) convertView.findViewById(R.id.credit_list_notes);
			holder.txtViewmoney = (TextView) convertView.findViewById(R.id.credit_list_amount);

			convertView.setTag(holder);
		}
		else
			holder=(ViewHolder)convertView.getTag();

		holder.txtViewdate.setTypeface(CustomTypeface.comicRelief(context));
		holder.txtViewdate.setText(this._creditOfSingleCustomer.get(position).getString("creditDate"));
		holder.txtViewnote.setTypeface(CustomTypeface.comicRelief(context));
		holder.txtViewnote.setText(this._creditOfSingleCustomer.get(position).getString("notes"));
		holder.txtViewmoney.setTypeface(CustomTypeface.comicRelief(context));
		holder.txtViewmoney.setText(formater.format(this._creditOfSingleCustomer.get(position).getDouble("amount")));
		

		return convertView;
	}



	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this._creditOfSingleCustomer.size();
	}

}