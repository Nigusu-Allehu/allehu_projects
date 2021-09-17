package com.gnn.attendance;

import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.*;
import android.support.design.widget.NavigationView.*;
import android.support.v4.widget.*;
import android.widget.*;
import java.util.*;
import android.view.*;
import android.content.*;
import android.widget.AdapterView.*;
import android.app.ProgressDialog;
import android.app.Activity;
import android.webkit.*;
import android.telephony.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
	Toolbar toolbar;

	Splash sp=new Splash();

	List studs=new ArrayList<String>();

	private CoordinatorLayout cord;

	private DrawerLayout drawerLayout;

	private NavigationView nav;

	private FloatingActionButton fab;

	private ListView listView;

	private ActionBarDrawerToggle actionBarDrawer;

	ProfileHelper pf=new ProfileHelper(this);

	Main m=new Main(this);

	ProgressDialog prg;

	TextView sch_name,tch_name,tch_sec,year;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		prg = new ProgressDialog(this);
		prg.setMessage("Please Wait");

		setup();
	}
	public void setup()
	{
		pf.getProfile();

		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		this.setSupportActionBar(toolbar);
		cord = (CoordinatorLayout)findViewById(R.id.mainCoordinatorLayout);
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		nav = (NavigationView)findViewById(R.id.navigation);
		fab = (FloatingActionButton)findViewById(R.id.fab);
		nav.inflateHeaderView(R.layout.header);
		listView = (ListView)findViewById(R.id.cardListView);
		nav.inflateMenu(R.menu.nav);
		actionBarDrawer = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
		drawerLayout.setDrawerListener(actionBarDrawer);
		actionBarDrawer.syncState();
		StatusBarUtil.setColorForDrawerLayout(this, drawerLayout, getResources().getColor(R.color.accent), 0);
		nav.setNavigationItemSelectedListener(this);

		View headerView = nav.getHeaderView(0);

		sch_name = (TextView)headerView.findViewById(R.id.sch_name);
		sch_name.setText(pf.getSchoolName());
		tch_name = (TextView)headerView.findViewById(R.id.tch_name);
		tch_name.setText(pf.getTeacherName());
		tch_sec = (TextView)headerView.findViewById(R.id.tch_sec);
		tch_sec.setText(pf.getTeacherSection());
		year = (TextView)headerView.findViewById(R.id.year);
		year.setText(pf.getYear());


		fab();
		listOnClick();
		if (pf.isSaved())
		{
			list(0);
			fab.setImageResource(R.drawable.ic_save);
			this.setTitle(pf.getCurrentDay());
		}
		else
		{
			list(1);
			fab.setImageResource(R.drawable.ic_plus);
			this.setTitle("Attendance");
		}
    }
	public void list(final int i)
	{
		MainActivity.this.runOnUiThread(new Runnable(){
				@Override
				public void run()
				{
					try
					{
						if (i == 1)
						{
							if (!m.getDays().isEmpty())
							{
								studs.clear();
								studs = m.getDays();
								ListAdapter adapter = new CardAdapter(MainActivity.this, studs);
								listView.setAdapter(adapter);
							}
							else
							{
								empyty("There is no Days Available");
							}
						}
						else if (i == 0)
						{
							if (!m.getStudents().isEmpty())
							{
								studs.clear();
								studs = m.getStudents();
								ListAdapter adapter = new StudAdapter(MainActivity.this, studs);
								listView.setAdapter(adapter);
							}
							else
							{
								empyty("There is no Students please add Students Firs5!!!");
							}
						}
						else
						{
							empyty("No Lists Available");
						}
					}
					catch (Exception e)
					{}
				}
			});
	}
	public void fab()
	{
		fab.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					if (pf.isSaved())
					{
						save();
						pf.deleteCurrentDay();
						list(1);
						fab.setImageResource(R.drawable.ic_plus);
						MainActivity.this.setTitle("Attendance");
						supportInvalidateOptionsMenu();
					}
					else
					{
						newDay();
					}
				}	
			});
	}
	public void save()
	{
		MainActivity.this.runOnUiThread(new Runnable(){
				@Override
				public void run()
				{	
					String tota="";
					for (int i=1;i <= m.totalStudent();i++)
					{
						if (!m.check(m.getStudentName(i).toString()))
						{
							m.addDayToStudent(m.getStudentName(i).toString(), pf.getCurrentDay());
							tota += m.getStudentName(i).toString() + ",";
						}
					}
					m.addNewDay(pf.getCurrentDay(), tota.toString());
					sms();
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
					MainActivity.this.runOnUiThread(new Runnable(){
							@Override
							public void run()
							{
								m.turnOffAll();
								pf.newCurrentDay(dd.getSelectedItem().toString() + "-" + mm.getSelectedItem().toString());
								Snackbar.make(cord, "Press The button again when you finish the Attendance", Snackbar.LENGTH_LONG).show();
								fab.setImageResource(R.drawable.ic_save);
								list(0);
								MainActivity.this.setTitle(pf.getCurrentDay());
								supportInvalidateOptionsMenu();
							}
						});
				}
			});
		adb.show();
	}
	public void listOnClick()
	{
		listView.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					if (pf.isSaved())
					{
						TextView tv=(TextView)p2.findViewById(R.id.student_name);
						LinearLayout layout=(LinearLayout)p2.findViewById(R.id.stud_color);

						int a=Integer.parseInt(m.getNoOfAbsent(tv.getText().toString()));

						try
						{
							if (m.check(tv.getText().toString()))
							{
								layout.setBackgroundColor(getResources().getColor(R.color.white));
								m.turnOff(tv.getText().toString());
								m.editNoOfAbsent(tv.getText().toString(), String.valueOf(a - 1));
							}
							else
							{
								layout.setBackgroundColor(getResources().getColor(R.color.light_blue));
								m.turnOn(tv.getText().toString());
								m.editNoOfAbsent(tv.getText().toString(), String.valueOf(a + 1));
							}
						}
						catch (Exception e)
						{}
					}
					else
					{
						try
						{
							TextView tv=(TextView)p2.findViewById(R.id.day);
							GotoDays.a = 1;
							GotoDays.day = tv.getText().toString();
							startActivity(new Intent(getApplicationContext(), GotoDays.class));
						}
						catch (Exception e)
						{}
					}
				}		
			});
	}
	public void sms()
	{
		try
		{
			String studs="";
			for (int i=1;i <= m.totalStudent();i++)
			{
				smsToParent(m.getStudentName(i));
				if (m.totalDays() != 0 && !String.valueOf(pf.getAdminMsg()).toString().equals("no"))
				{
					int abs=m.totalDays() - Integer.parseInt(m.getNoOfAbsent(m.getStudentName(i)));
					int adm=Integer.parseInt(pf.getAdminMsg());
					if (abs >= adm)
					{
						if (abs % adm == 0)
						{
							if (!m.checkMsgAd(m.getStudentName(i)))
							{
								studs += m.getStudentName(i) + "  ,  ";
								m.turnOnMesssgeAd(m.getStudentName(i));
							}
						}
						else
						{
							m.turnOffMesssgePr(m.getStudentName(i));
						}
					}
				}
			}
			smsToAdmin(studs);
		}
		catch (Exception e)
		{
			Toast.makeText(getApplicationContext(), e.getMessage(), 3).show();
		}
	}
	public void smsToParent(String name)
	{
		if (!pf.getPrMsg().toString().equals("no") && m.totalDays() != 0)
		{
			int abs=m.totalDays() - Integer.parseInt(m.getNoOfAbsent(name));
			int parns=Integer.parseInt(pf.getPrMsg());
			if (abs >= parns)
			{
				if (abs % parns == 0)
				{
					if (!m.checkMsgPr(name))
					{
						SmsManager sms=SmsManager.getDefault();
						sms.sendTextMessage(m.getPhone(name), null, "Dear " + name + "'s Parents \n\n Hello \nI am " + pf.getTeacherName() + " Home room Teacher of " + name + " in " + pf.getSchoolName() + " School Section " + pf.getTeacherSection() + "\n This Text Messsge was sent to you to inform that your child was absent on those days \n" + m.getLastDays(name), null, null);
						m.turnOnMesssgePr(name);
					}
				}
				else
				{
					m.turnOffMesssgePr(name);
				}
			}
		}
	}
	public void smsToAdmin(String name)
	{
		if (name != "")
		{
			SmsManager sms=SmsManager.getDefault();
			sms.sendTextMessage(pf.getAdminPhone(), null, "Dear " + pf.getAdminName() + "\n\n Hello \nI am " + pf.getTeacherName() + " ,Home room Teacher of " + pf.getTeacherSection() + " in " + pf.getSchoolName() + " School. I would like Acknowledge that Students " + name + " was Absents from School for more than " + pf.getAdminMsg() + " I suggest that you take measure on those Students as the School law suggess \n\n,Sincerely. \n<Attendance by GNN>", null, null);
		}
	}
	public void empyty(String msg)
	{List lst=new ArrayList<String>();
		lst.clear();
		lst.add(msg);
		ListAdapter lad=new ArrayAdapter<String>(getApplicationContext(), R.layout.emitem2, R.id.item_text, lst);
		listView.setAdapter(lad);
	}
	@Override
	public boolean onNavigationItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.mec:
				drawerLayout.closeDrawer(Gravity.START);
				startActivity(new Intent(getApplicationContext(), Meckup.class));
				break;
			case R.id.students :
				drawerLayout.closeDrawer(Gravity.START);
				startActivity(new Intent(getApplicationContext(), Students.class));
				break;
			case R.id.add_student :
				drawerLayout.closeDrawer(Gravity.START);
				startActivity(new Intent(getApplicationContext(), AddStudent.class));
				break;
			case R.id.program :
				drawerLayout.closeDrawer(Gravity.START);
				startActivity(new Intent(getApplicationContext(), Program.class));
				break;
			case R.id.profile :
				drawerLayout.closeDrawer(Gravity.START);
				startActivity(new Intent(getApplicationContext(), Profiler.class));
				break;
			case R.id.goto_days :
				drawerLayout.closeDrawer(Gravity.START);
				startActivity(new Intent(getApplicationContext(), GotoDays.class));
				break;
			case R.id.red:
				drawerLayout.closeDrawer(Gravity.START);
				startActivity(new Intent(getApplicationContext(), RedList.class));
				break;
			case R.id.setting :
				drawerLayout.closeDrawer(Gravity.START);
				startActivity(new Intent(getApplicationContext(), Settings.class));
				break;
			case R.id.help :
				drawerLayout.closeDrawer(Gravity.START);
				startActivity(new Intent(getApplicationContext(), Help.class));
				break;
			case R.id.about :
				about();
				drawerLayout.closeDrawer(Gravity.START);
				break;
			case R.id.exit :
				finishAffinity();
		}
		return true;
	}
	public void about()
	{
		About ab=new About();
		ab.aboutSaver();
		WebView w=new WebView(this);
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		w.loadUrl("file://" + pf.privatePath + "/about.html");
		adb.setView(w);
		adb.show();
	}
	public void cancelAttend()
	{
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Are you Shure to Cancel");
		adb.setMessage("After Canceled this Attendance you will lose your data at this day");
		adb.setPositiveButton("YES", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					cancel();
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
	public void cancel()
	{
		pf.deleteCurrentDay();
		if (pf.isSaved())
		{
			list(0);
			fab.setImageResource(R.drawable.ic_save);
			this.setTitle(pf.getCurrentDay());
		}
		else
		{
			list(1);
			fab.setImageResource(R.drawable.ic_plus);
			this.setTitle("Attendance");
		}
		supportInvalidateOptionsMenu();
	}
	@Override
	public void onBackPressed()
	{
		finishAffinity();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(0, 0, 0, "Refresh List");
		menu.add(0, 1, 0, "Cancel Attendance");
		menu.getItem(0).setIcon(R.drawable.ic_refresh);
		menu.getItem(0).setShowAsAction(2);
		menu.getItem(1).setIcon(R.drawable.ic_cancel);
		menu.getItem(1).setShowAsAction(2);
		if (pf.isSaved())
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
			case 0 :
				if (pf.isSaved())
				{
					list(0);
					fab.setImageResource(R.drawable.ic_save);
					this.setTitle(pf.getCurrentDay());
				}
				else
				{
					list(1);
					fab.setImageResource(R.drawable.ic_plus);
					this.setTitle("Attendance");
				}
				break;
			case 1 :
				cancelAttend();
				break;
		}
		return true;
	}
}
