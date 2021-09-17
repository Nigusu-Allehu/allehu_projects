package com.gnn.attendance;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import android.support.annotation.*;

public class Timer extends Service
{
	Main m=new Main(this);

	int total=0;

	@Override
	public IBinder onBind(Intent p1)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		return START_STICKY;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		startService(new Intent(getApplicationContext(), Timer.class));
	}
	
}
