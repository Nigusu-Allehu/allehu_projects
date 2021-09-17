package com.gnn.attendance;

import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.*;
import android.os.*;
import android.support.v4.widget.*;
import android.support.v4.view.*;
import android.support.v4.app.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import java.util.*;
import android.widget.AdapterView.*;

public class MeckupDays extends AppCompatActivity 
{
	Toolbar toolbar;

	private ViewPager viewPager;

	private Spinner days;

	private TabLayout tll;

	ProfileHelper prf=new ProfileHelper(this);

	public static int a=0;

	public static String day;

	Main m=new Main(this);

	public static ArrayList list=new ArrayList<String>();

	public static List totalList=new ArrayList<String>();

	public static List abs=new ArrayList<String>();

	public static List prs=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goto_days);

		setup();

		view();

		if (a == 0)
		{
			try
			{
				select();
			}
			catch (Exception e)
			{
				Toast.makeText(getApplicationContext(), "There is No Dates", 3).show();
			}
		}
		else
		{
			try
			{
				add(day);
				days.setVisibility(View.GONE);
				getSupportActionBar().setTitle("Day " + day.toString());
			}
			catch (Exception e)
			{Toast.makeText(getApplicationContext(), "There is No Dates", 3).show();}
		}
		a = 0;

    }
	public void setup()
	{
		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		tll = (TabLayout)findViewById(R.id.tll);
		viewPager = (ViewPager) findViewById(R.id.vp); 
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);
		days = (Spinner)findViewById(R.id.day_selector);
	}
	public void view()
	{
		MyAdapter adapter = new MyAdapter(getSupportFragmentManager()); 
		viewPager.setAdapter(adapter); 
		tll.setupWithViewPager(viewPager);

	}
	public void select()
	{
		ArrayList al=new ArrayList();
		for (int i=1;i <= m.totalMeckup();i++)
		{
			al.add(m.getMeckupDays(i));
		}
		ArrayAdapter ad=new ArrayAdapter(this, R.layout.emitem, al);
		ad.setDropDownViewResource(R.layout.emitem);
		days.setAdapter(ad);
		days.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{
					add(days.getItemAtPosition(p3).toString());
					view();
				}
				@Override
				public void onNothingSelected(AdapterView<?> p1)
				{
					// TODO: Implement this method
				}						
			});
	}
	public void add(String date)
	{
		totalList.clear();
		abs.clear();
		prs.clear();

		totalList = m.getStudents();
		String[] a=m.getMeckupStuds(date).split(",");
		for (String aa:a)
		{
			if (!aa.toString().equals(""))
			{
				abs.add(aa);
			}
		}
		for (int i=0;i < abs.size();i++)
		{
			totalList.remove(abs.get(i).toString());
		}
		prs = totalList;
	}
	class MyAdapter extends FragmentStatePagerAdapter
	{ 
		public MyAdapter(FragmentManager fm)
		{ 
			super(fm); 
		} 

		@Override 
		public Fragment getItem(int position)
		{ 
			Fragment f = null; 
			if (position == 0)
			{
				f = new MecAbsent();
			}
			if (position == 1)
			{
				f = new MecPresent();
			}
			return f;
		}

		@Override
		public int getCount()
		{
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			String name = null; 
			if (position == 0)
			{ 
				name = "ABSENTS";
			}
			if (position == 1)
			{
				name = "PRESENTS";
			}
			return name;
		}
	}
	@Override
	public boolean onSupportNavigateUp()
	{
		super.onBackPressed();
		return true;
	}
}
