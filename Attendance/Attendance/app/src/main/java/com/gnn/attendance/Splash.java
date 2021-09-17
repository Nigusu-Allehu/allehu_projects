package com.gnn.attendance;

import android.support.v7.app.*;
import android.os.*;
import android.widget.*;
import java.io.*;
import android.view.*;
import android.content.*;
import android.app.admin.*;
import android.app.Activity;

public class Splash extends AppCompatActivity
{
	ProfileHelper pf=new ProfileHelper(this);

	PolicyManager policyManager;

	Main m=new Main(this);

	public static String pha="";

	public static String php="";

	String a="";

	private EditText pas1;

	private EditText pas2;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		policyManager = new PolicyManager(this);

		startService(new Intent(getApplicationContext(), Timer.class));

		checkAdm();

	}
	public void checkAdm()
	{
		if (!policyManager.isAdminActive())
		{
			Intent activateDeviceAdmin = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			activateDeviceAdmin.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, policyManager.getAdminComponent());
			activateDeviceAdmin.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "After Activate Device Adminstrator the Application connot be removed\n that can help you desn't lose any data");
			startActivityForResult(activateDeviceAdmin, PolicyManager.DPM_ACTIVATION_REQUEST_CODE);
			Toast.makeText(getApplicationContext(), "Please Activate Device Adminstrator to run this application", 3).show();
		}
		else
		{
			if (isSecure())
			{
				pass();
			}
			else
			{
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		if (resultCode == Activity.RESULT_OK
			&& requestCode == PolicyManager.DPM_ACTIVATION_REQUEST_CODE)
		{
			checkAdm();
		}
		else
		{
			finishAffinity();
			Toast.makeText(getApplicationContext(), "Attendance can't run unless you activate Device Adminstrator", 3).show();
		}

	}
	public void delPass()
	{
		try
		{
			File f=new File(pf.privatePath + "/p.inf");
			f.delete();
		}
		catch (Exception e)
		{

		}
	}
	public Boolean isSecure()
	{
		boolean b=false;
		try
		{
			DataInputStream dis=new DataInputStream(new FileInputStream(pf.privatePath + "/p.inf"));
			a = dis.readLine();
			b = true;
		}
		catch (Exception e)
		{
			b = false;
		}
		return b;
	}
	public void pass()
	{
		LayoutInflater inf=LayoutInflater.from(getApplicationContext());
		View view=inf.inflate(R.layout.pass, null, false);
		pas1 = (EditText)view.findViewById(R.id.pass1);
		pas2 = (EditText)view.findViewById(R.id.pass2);
		pas2.setVisibility(View.GONE);
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle("Please Input Password");
		adb.setView(view);
		adb.setPositiveButton("OK", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					if (pas1.getText().toString().equals(a))
					{
						startActivity(new Intent(getApplicationContext(), MainActivity.class));
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Invalid Password", 3).show();
						finishAffinity();
					}
				}
			});
		adb.setCancelable(true);
		adb.setOnCancelListener(new DialogInterface.OnCancelListener(){
				@Override
				public void onCancel(DialogInterface p1)
				{
					finishAffinity();
				}
			});
		adb.show();
	}
}
