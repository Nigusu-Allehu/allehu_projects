package com.gnn.attendance;

import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.*;
import android.os.*;
import android.support.v4.widget.*;
import android.view.*;
import android.widget.*;
import android.widget.TextView.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.content.*;

public class Meckup extends AppCompatActivity 
{
	String studs="";

	Toolbar toolbar;

	List lists=new ArrayList<String>();

	private ListView listView;

	private ListAdapter adapter;

	ProfileHelper pf=new ProfileHelper(this);

	Main m=new Main(this);

	private FloatingActionButton fab;

	private CoordinatorLayout cord;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mac);

		setup();

    }
	public void setup()
	{
		cord = (CoordinatorLayout)findViewById(R.id.mainCoordinatorLayout);
		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);
		listView = (ListView)findViewById(R.id.studs_list_view);
		fab = (FloatingActionButton)findViewById(R.id.fab);

		icon();
		list();
		fabClick();
		listOnClick();
	}
	public void list()
	{
		Meckup.this.runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
					if (m.isMeckupSaved())
					{
						if (!m.getStudents().isEmpty())
						{
							ListAdapter lad=new MeckupAdapter(Meckup.this, m.getStudents());
							listView.setAdapter(lad);
						}
						else
						{
							empyty("There is No Students");
						}
					}
					else
					{
						if (!m.getMeckupStudents().isEmpty())
						{
							ListAdapter lad=new CardAdapter(Meckup.this, m.getMeckupStudents());
							listView.setAdapter(lad);
						}
						else
						{
							empyty("There is No  Meckup Days");
						}
					}
				}
			});
	}
	public void fabClick()
	{
		fab.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					if (m.isMeckupSaved())
					{
						save();
						m.deleteMeckupCurrent();
						list();
						supportInvalidateOptionsMenu();
						icon();
					}
					else
					{
						newDay();
					}
				}
			});
	}
	public void newDay()
	{
		LayoutInflater inf=LayoutInflater.from(getApplicationContext());
		View v=inf.inflate(R.layout.select, null, false);
		final Spinner dd=(Spinner)v.findViewById(R.id.selectDay);
		final Spinner mm=(Spinner)v.findViewById(R.id.selectMonth);
		ArrayList ddl=new ArrayList<String>();
		for (int i=1;i < 31;i++)
		{
			ddl.add(String.valueOf(i));
		}
		String[] mml=new String[]{"September","October","November","December","January","February","March","April","May","June","July","Augest"};
		ArrayAdapter add=new ArrayAdapter<String>(getApplicationContext(), R.layout.emitem3, ddl);
		add.setDropDownViewResource(R.layout.emitem3);
		dd.setAdapter(add);
		ArrayAdapter amm=new ArrayAdapter<String>(getApplicationContext(), R.layout.emitem3, mml);
		amm.setDropDownViewResource(R.layout.emitem3);
		mm.setAdapter(amm);
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Select Day and Month");
		adb.setView(v);
		adb.setPositiveButton("OK", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					Meckup.this.runOnUiThread(new Runnable(){
							@Override
							public void run()
							{
								m.turnOffAllMec();
								m.saveCurrentMeckup(dd.getSelectedItem().toString() + "-" + mm.getSelectedItem().toString() + "-" + pf.getYear());
								Snackbar.make(cord, "Press The button again when you finish the Attendance", Snackbar.LENGTH_LONG).show();
								supportInvalidateOptionsMenu();
								list();
								icon();
							}
						});
				}
			});
		adb.show();
	}
	public void icon()
	{
		if (m.isMeckupSaved())
		{
			fab.setImageResource(R.drawable.ic_save);
			this.setTitle(m.getCurrentMeckup());
		}
		else
		{
			fab.setImageResource(R.drawable.ic_plus);
			this.setTitle("Meckup Class");
		}
	}
	public void save()
	{
		studs = "";
		Meckup.this.runOnUiThread(new Runnable(){
				@Override
				public void run()
				{
					for (int i=1;i <= m.totalStudent();i++)
					{
						if (!m.checkMec(m.getStudentName(i)))
						{
							studs += m.getStudentName(i) + ",";
						}
					}
					m.newMecupDate(m.getCurrentMeckup(), studs);
				}
			});
	}
	public void listOnClick()
	{
		listView.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					if (m.isMeckupSaved())
					{
						TextView tv=(TextView)p2.findViewById(R.id.student_name);
						LinearLayout layout=(LinearLayout)p2.findViewById(R.id.stud_color);

						try
						{
							if (m.checkMec(tv.getText().toString()))
							{
								layout.setBackgroundColor(getResources().getColor(R.color.white));
								m.turnOffMec(tv.getText().toString());
							}
							else
							{
								layout.setBackgroundColor(getResources().getColor(R.color.light_blue));
								m.turnOnMec(tv.getText().toString());
							}
						}
						catch (Exception e)
						{}
					}
					else
					{
						TextView tv=(TextView)p2.findViewById(R.id.day);
						MeckupDays.a = 1;
						MeckupDays.day = tv.getText().toString();
						startActivity(new Intent(getApplicationContext(), MeckupDays.class));
						///TODO   IMPLEMENT
					}
				}		
			});
	}
	public void empyty(String msg)
	{List lst=new ArrayList<String>();
		lst.clear();
		lst.add(msg);
		ListAdapter lad=new ArrayAdapter<String>(getApplicationContext(), R.layout.emitem2, R.id.item_text, lst);
		listView.setAdapter(lad);
	}
	public void cancelMeckup()
	{
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Are you Shure to Cancel this Meckup Attendance");
		adb.setMessage("After Canceled this Attendance you will lose your data at this Meckup day");
		adb.setPositiveButton("YES", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					m.deleteMeckupCurrent();
					list();
					icon();
					supportInvalidateOptionsMenu();
					Toast.makeText(getApplicationContext(), "Meckup is Canceled", 3).show();
				}
			});
		adb.setNegativeButton("No", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{

				}

			});
		adb.show();
	}
	@Override
	public boolean onSupportNavigateUp()
	{
		super.onBackPressed();
		return true;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.mac, menu);
		if (m.isMeckupSaved())
		{
			menu.getItem(1).setVisible(true);
		}
		else
		{
			menu.getItem(1).setVisible(false);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.cancel_meckup :
				cancelMeckup();
				break;
			case R.id.meckup_days :
				startActivity(new Intent(getApplicationContext(), MeckupDays.class));
				break;
			case android.R.id.home :
				super.onBackPressed();
				break;
		}
		return true;
	}
}
