package com.gnn.attendance;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SampleDeviceAdminReceiver extends DeviceAdminReceiver
{

	@Override
	public void onDisabled(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		Toast.makeText(context, "Device Adminstrator is Disabled", Toast.LENGTH_SHORT).show();
		super.onDisabled(context, intent);
	}

	@Override
	public void onEnabled(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		Toast.makeText(context, "Device Adminstrator is Enabled", Toast.LENGTH_SHORT).show();
		super.onEnabled(context, intent);
	}

	@Override
	public CharSequence onDisableRequested(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		Toast.makeText(context, "Device Adminstrator Disabled", Toast.LENGTH_SHORT)
			.show();
		return super.onDisableRequested(context, intent);
	}

}
