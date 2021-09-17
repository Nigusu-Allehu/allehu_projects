package com.gnn.attendance;

import android.widget.*;
import android.content.*;
import android.view.*;
import java.util.*;

public class List_Adapter extends ArrayAdapter<String>
{

	private TextView textView;

	Context cn;

	Main m;

	private ProfileView imageView;

	private TextView studNo;
	public List_Adapter(Context context, List values) 
	{
		super(context, R.layout.item, values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		m = new Main(getContext());
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.item, parent, false);

		String text = getItem(position);

		textView = (TextView) view.findViewById(R.id.student_name);
		studNo = (TextView)view.findViewById(R.id.std_no);
		imageView = (ProfileView) view.findViewById(R.id.student_image);
		textView.setText(text.toString());
		studNo.setText(m.getStudentNo(text.toString()));
		try
		{
			studNo.setText(m.getStudentNo(text.toString()));
			if (m.getStudentSex(textView.getText().toString()).equals("Male"))
			{
				imageView.setImageResource(R.drawable.male);
			}
			else if (m.getStudentSex(textView.getText().toString()).equals("Female"))
			{
				imageView.setImageResource(R.drawable.female);
			}
			else
			{
				imageView.setVisibility(View.INVISIBLE);
			}
		}
		catch (Exception e)
		{}

		return view;
	}
}
