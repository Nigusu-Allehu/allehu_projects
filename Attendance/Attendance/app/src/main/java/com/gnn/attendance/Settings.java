package com.gnn.attendance;

import android.os.*;
import android.widget.*;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.*;
import android.support.design.widget.*;
import android.view.*;
import java.io.*;
import android.content.*;
import android.view.View.*;
import android.text.*;

public class Settings extends AppCompatActivity
{

	private TextView lockText;

	private CheckBox lockCh;

	private TextView clear;

	private Toolbar toolbar;

	Splash sp=new Splash();

	Main m=new Main(this);

	ProfileHelper pf=new ProfileHelper(this);

	private CoordinatorLayout cord;

	private EditText pas1;

	private EditText pas2;

	private TextView Admin;

	private TextView Parent,red;

	PolicyManager policyManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preff);

		policyManager = new PolicyManager(this);
		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		cord = (CoordinatorLayout)findViewById(R.id.mainCoordinatorLayout);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);
		lockText = (TextView)findViewById(R.id.lock_txt);
		lockCh = (CheckBox)findViewById(R.id.lock__ch);
		clear = (TextView)findViewById(R.id.clr);
		Admin = (TextView)findViewById(R.id.onA);
		red = (TextView)findViewById(R.id.red_list);
		red.setText("Add Students to Red List have greater than " + String.valueOf(m.redList()) + " Absent Number");
		if (!pf.getAdminMsg().toString().equals("no"))
		{
			Admin.setText("Send Message to Adminstrator in every " + pf.getAdminMsg() + " days");
		}
		else
		{
			Admin.setText("Don't send Message to Adminstrator");
		}
		Parent = (TextView)findViewById(R.id.onP);
		if (!pf.getPrMsg().toString().equals("no"))
		{
			Parent.setText("Send Message to Parents in every " + pf.getPrMsg() + " days");
		}
		else
		{
			Parent.setText("Don't send Message to Parents");
		}
		ch();
		if (sp.isSecure())
		{
			lockCh.setChecked(true);
		}
		else
		{
			lockCh.setChecked(false);
		}

	}
	public void ch()
	{
		lockCh.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					View v=new;
					lock(v);
				}		
			});
	}
	public void lock(View v)
	{
		if (!sp.isSecure())
		{
			savePass();
		}
		else
		{
			delPass();
		}
	}
	public void clear(View v)
	{
		if (sp.isSecure())
		{
			cld();
		}
		else
		{
			conf();
		}
	}
	public void fnl(View v)
	{
		startActivity(new Intent(getApplicationContext(), FinalResult.class));
	}
	public void cld()
	{
		LayoutInflater inf=LayoutInflater.from(getApplicationContext());
		View view=inf.inflate(R.layout.pass, null, false);
		pas1 = (EditText)view.findViewById(R.id.pass1);
		pas2 = (EditText)view.findViewById(R.id.pass2);
		pas2.setVisibility(View.GONE);
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Input Password to Clear Data");
		adb.setView(view);
		adb.setPositiveButton("Clear", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					if (pas1.getText().toString().equals(sp.a))
					{
						conf();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Invalid Password", 3).show();
					}
				}
			});
		adb.show();
	}
	public void clearAll()
	{
		try
		{ 
			Main m=new Main(this);
			m.deletrAll();
			delete();
			Snackbar.make(cord, "Your Data is Cleared pleas Restart the Application", Snackbar.LENGTH_SHORT).show();
			finishAffinity();
		}
		catch (Exception e)
		{
			Snackbar.make(cord, "Error " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
		}
	}
	public void delete()
	{
		File f=new File(pf.privatePath);
		File[] a=f.listFiles();
		for (File files:a)
		{
			try
			{
				files.delete();
			}
			catch (Exception e)
			{}
		}
	}
	public void conf()
	{
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Are you Shure to Clear Data!!!");
		adb.setPositiveButton("Clear", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					clearAll();
				}
			});
		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{

				}
			});
		adb.show();
	}
	public void savePass()
	{
		LayoutInflater inf=LayoutInflater.from(getApplicationContext());
		View view=inf.inflate(R.layout.pass, null, false);
		pas1 = (EditText)view.findViewById(R.id.pass1);
		pas2 = (EditText)view.findViewById(R.id.pass2);
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Input Password");
		adb.setView(view);
		adb.setPositiveButton("Save", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					if (!pas1.getText().toString().equals(""))
					{
						if (!pas2.getText().toString().equals(""))
						{
							if (pas2.getText().toString().equals(pas1.getText().toString()))
							{
								pf.mainSave(pf.privatePath + "/p.inf", pas1.getText().toString());
								lockCh.setChecked(true);
								Toast.makeText(getApplicationContext(), "Password Saved Successfully please don't Forget your Password!!!", 3).show();
							}
							else
							{
								Toast.makeText(getApplicationContext(), "Password Doesn't match", 3).show();
							}
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Field can't be empty", 3).show();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Field can't be empty", 3).show();
					}
				}
			});
		adb.show();
	}
	public void delPass()
	{
		LayoutInflater inf=LayoutInflater.from(getApplicationContext());
		View view=inf.inflate(R.layout.pass, null, false);
		pas1 = (EditText)view.findViewById(R.id.pass1);
		pas2 = (EditText)view.findViewById(R.id.pass2);
		pas2.setVisibility(View.GONE);
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Input Password to Forget");
		adb.setView(view);
		adb.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					if (pas1.getText().toString().equals(sp.a))
					{
						sp.delPass();
						lockCh.setChecked(false);
						Toast.makeText(getApplicationContext(), "Password Daleted!!", 3).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Invalid Password", 3).show();
					}
				}
			});
		adb.show();
	}
	public void onA(View v)
	{
		saveDay(0);
	}
	public void onP(View v)
	{
		saveDay(1);
	}
	public void saveDay(final int i)
	{
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Input day");
		LayoutInflater inf=LayoutInflater.from(getApplicationContext());
		View v=inf.inflate(R.layout.pass2, null, false);
		final EditText ed=(EditText)v.findViewById(R.id.admin_day);
		adb.setView(v);
		adb.setPositiveButton("OK", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					if (i == 0)
					{
						if (ed.getText().toString().equals(""))
						{
							pf.editMsgAdmin("no");
							Admin.setText("Don't send Messsge to Admin");
						}
						else
						{
							pf.editMsgAdmin(ed.getText().toString());
							Admin.setText("Send Message to Adminstrator in every " + ed.getText().toString() + " days");
						}
					}
					else
					{
						if (ed.getText().toString().equals(""))
						{
							pf.editMsgParent("no");
							Parent.setText("Don't send Message to Parents");
						}
						else
						{
							pf.editMsgParent(ed.getText().toString());
							Parent.setText("Send Message to Parents in every " + ed.getText().toString() + " days");
						}

					}
				}

			});
		adb.show();
	}
	public void red(View v)
	{
		redList();
	}
	public void redList()
	{
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Input Number");
		LayoutInflater inf=LayoutInflater.from(getApplicationContext());
		View v=inf.inflate(R.layout.pass2, null, false);
		final EditText ed=(EditText)v.findViewById(R.id.admin_day);
		adb.setView(v);
		adb.setPositiveButton("OK", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					if (ed.getText().toString().equals(""))
					{
						Toast.makeText(getApplicationContext(), "Invalid Number", 3).show();
						redList();
					}
					else
					{
						m.editRed(ed.getText().toString());
						red.setText("Add Students to Red List have greater than " + ed.getText().toString() + " Absent Number");
					}
				}
			});
		adb.show();
	}
	public void u()
	{
		if (policyManager.isAdminActive())
			policyManager.disableAdmin();
	}
	public void un(View v)
	{
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Are you shure to Uninstall");
		adb.setPositiveButton("OK", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					u();
				}
			});
		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{

				}
			});
		adb.show();
	}
}
