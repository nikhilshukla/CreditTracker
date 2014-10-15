package in.qubit.credittracker;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewCustomAdapter extends BaseAdapter
{
	public String note[];
	public String money[];
	public String date[];

	public Activity context;
	public LayoutInflater inflater;

	public ListViewCustomAdapter(Activity context,String[] note, String[] money, String[] date) {
		super();

		this.context = context;
		this.note = note;
		this.money = money;
		this.date = date;

	    this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return note.length;
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

		holder.txtViewdate.setText(date[position]);
		holder.txtViewnote.setText(note[position]);
		holder.txtViewmoney.setText(money[position]);

		return convertView;
	}

}