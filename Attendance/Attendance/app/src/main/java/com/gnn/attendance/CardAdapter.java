package com.gnn.attendance;

import android.widget.*;
import android.content.*;
import java.util.*;
import android.view.*;

public class CardAdapter extends ArrayAdapter<String>
{

	private TextView studName,day;

	public CardAdapter(Context context, List values) 
	{
		super(context, R.layout.card_item, values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.card_item, parent, false);

		day = (TextView)view.findViewById(R.id.day);

		studName = (TextView)view.findViewById(R.id.abs_students);

		String text = getItem(position);

		String[] a=text.split("@@");
		try
		{
			day.setText(a[0].trim().toString());
			studName.setText(a[1].trim().toString());
		}
		catch (Exception e)
		{}

		return view;
	}
}
