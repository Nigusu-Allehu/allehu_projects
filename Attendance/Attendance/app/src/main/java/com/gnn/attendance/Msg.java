package com.gnn.attendance;

import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.Toolbar;
import android.widget.*;
import android.view.*;
import android.app.ProgressDialog;
import android.content.*;
import android.telephony.*;

public class Msg extends AppCompatActivity
{
	public static String name;

	String phone="";

	private Toolbar toolbar;

	private EditText msg;

	Main m=new Main(this);

	int i=0;

	private ProgressDialog prg;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg);

		try
		{
			phone = m.getPhone(name);
			if (!phone.toString().equals(""))
			{
				setup();
			}	
		}
		catch (Exception e)
		{setup();
			msg.setText("Error Invalid Student Name and Phone Number");
			msg.setEnabled(false);
		}

	}
	public void setup()
	{
		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Send Message to " + name);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);
		msg = (EditText)findViewById(R.id.msg);

	}
	public void send(View v)
	{
		if (!msg.getText().toString().equals(""))
		{
			i = 0;
			prg = new ProgressDialog(this);
			prg.setMessage("Please Wait Sending...");
			prg.setCanceledOnTouchOutside(false);
			prg.setCancelable(true);
			prg.setOnCancelListener(new DialogInterface.OnCancelListener(){
					@Override
					public void onCancel(DialogInterface p1)
					{
						i = 1;
					}
				});
			send();
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Message is Empyty Please type some message", 3).show();
		}
	}
	public void sendMsg()
	{
		if (i == 0)
		{
			SmsManager sms=SmsManager.getDefault();
			sms.sendTextMessage(phone, null, msg.getText().toString(), null, null);
			Toast.makeText(getApplicationContext(), "Message Sent", 3).show();
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Message   not   sent", 3).show();
		}
	}
	public void send()
	{
		new Thread(new Runnable(){

				@Override
				public void run()
				{
					Msg.this.runOnUiThread(new Runnable(){

							@Override
							public void run()
							{
								prg.show();
							}

						});
					try
					{
						Thread.sleep(2000);
					}
					catch (Exception e)
					{}
					Msg.this.runOnUiThread(new Runnable(){

							@Override
							public void run()
							{
								prg.hide();
								sendMsg();
							}

						});
				}

			}).start();

	}

	@Override
	public boolean onSupportNavigateUp()
	{
		super.onBackPressed();
		return true;
	}
}
