package com.gnn.attendance;

import android.widget.*;
import android.content.*;
import java.util.*;
import android.view.*;
import android.graphics.*;

public class StudAdapter extends ArrayAdapter<String>
{

	private TextView textView;

	private ProfileView imageView;

	private LinearLayout cont;

	private Main m;

	private TextView studNo;
	public StudAdapter(Context context, List values) 
	{
		super(context, R.layout.item, values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.item, parent, false);

		m = new Main(getContext());

		String text = getItem(position);

		textView = (TextView) view.findViewById(R.id.student_name);
		imageView = (ProfileView) view.findViewById(R.id.student_image);
		cont = (LinearLayout)view.findViewById(R.id.stud_color);
		studNo = (TextView)view.findViewById(R.id.std_no);
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

			if (m.check(textView.getText().toString()))
			{
				cont.setBackgroundColor(Color.parseColor("#89C2EF"));
			}
			else
			{
				cont.setBackgroundColor(Color.WHITE);
			}

		}
		catch (Exception e)
		{}

		return view;
	}
}
