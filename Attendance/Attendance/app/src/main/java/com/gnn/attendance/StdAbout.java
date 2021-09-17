package com.gnn.attendance;

import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.os.*;
import android.widget.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.view.*;
import android.content.*;

public class StdAbout extends AppCompatActivity 
{
	Toolbar toolbar;

	TextView perView,perAbsView,perPrsView;

	ProgressBar percent;

	ListView daysList;

	List dayList=new ArrayList<String>();

	public static String name="";

	public static int a=0;

	Main m=new Main(this);

	GotoDays dayInfo=new GotoDays();

	private int tt,abs,prs;

	private float per=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_std);

		setup();

    }
	public void setup()
	{
		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(name.toString());
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);
		perView = (TextView)findViewById(R.id.about_std_per);
		perView.setVisibility(View.GONE);
		perAbsView = (TextView)findViewById(R.id.about_std_abs_per);
		perPrsView = (TextView)findViewById(R.id.about_std_prs_per);
		daysList = (ListView)findViewById(R.id.about_std_day_list);
		percent = (ProgressBar)findViewById(R.id.about_atd_bar);
		add();
	}
	public void add()
	{
		abs = Integer.parseInt(m.getNoOfAbsent(name.toString()));
		tt = m.totalDays();
		prs = tt - abs;

		percent.setMax(m.totalDays());
		percent.setProgress(tt - abs);
		perAbsView.setText("Present : " + String.valueOf(abs) + "/" + String.valueOf(tt));
		perPrsView.setText("Absent : " + String.valueOf(prs) + "/" + String.valueOf(tt));
		list();
	}
	public void list()
	{
		String[] aa=m.getAbsDates(name.toString()).split(",");
		for (String a:aa)
		{
			if (!a.toString().endsWith(""))
			{
				dayList.add(a.toString());
			}
		}
		ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_list_item_1, dayList);
		daysList.setAdapter(ad);
		daysList.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					dayInfo.a = 1;
					dayInfo.day = daysList.getItemAtPosition(p3).toString();
					startActivity(new Intent(getApplicationContext(), GotoDays.class));
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(0, 0, 0, "Send Text Message");
		menu.getItem(0).setIcon(R.drawable.ic_msg);
		menu.getItem(0).setShowAsAction(1);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case 0 :
				Msg.name = name;
				startActivity(new Intent(getApplicationContext(), Msg.class));
				break;
			case android.R.id.home :
				super.onBackPressed();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
