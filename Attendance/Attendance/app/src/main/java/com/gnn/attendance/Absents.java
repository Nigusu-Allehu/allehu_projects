package com.gnn.attendance;

import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.content.*;
import java.io.*;
import android.widget.AdapterView.*;

public class Absents extends Fragment
{
	private ListAdapter adapter;

	public static List list=new ArrayList<String>();

	private ListView listView;

	private Context context;

	private Context cn;

	private View v;

	ProfileHelper prf;

	Presents pr;

	Main m;

	GotoDays d;

	StdAbout stdInfo=new StdAbout();

    public Absents()
	{
        // Required empty public constructor
    }
	public Absents(Context ccc)
	{
		this.context = ccc;
	}

    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		cn = getContext();
		v = inflater.inflate(R.layout.lists, container, false);
		listView = (ListView)v.findViewById(R.id.studs_list_view);
		list();
		return v;
	}
	public void list()
	{
		if (!d.abs.isEmpty())
		{
			ArrayAdapter ad=new List_Adapter(getContext(), d.abs);
			listView.setAdapter(ad);
			listView.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
					{
						stdInfo.name = listView.getItemAtPosition(p3).toString();
						startActivity(new Intent(getContext(), StdAbout.class));
					}

				});
		}
		else
		{
			empyty("There is No any Absent Students on this Day");
		}
	}
	public void empyty(String msg)
	{List lst=new ArrayList<String>();
		lst.clear();
		lst.add(msg);
		ListAdapter lad=new ArrayAdapter<String>(getContext(), R.layout.emitem2, R.id.item_text, lst);
		listView.setAdapter(lad);
	}
}
