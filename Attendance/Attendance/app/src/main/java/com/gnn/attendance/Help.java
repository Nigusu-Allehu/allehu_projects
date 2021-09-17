package com.gnn.attendance;

import android.support.v7.app.*;
import android.os.*;
import android.webkit.*;
import android.view.*;

public class Help extends AppCompatActivity
{
	ProfileHelper pf=new ProfileHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		save();

		WebView wv=new WebView(this);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl("file://" + pf.privatePath + "/index.inf");
		wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		setContentView(wv);

	}
	public void save()
	{
		String cont="<h1>HELP</h1>";
		pf.mainSave(pf.privatePath + "/index.inf", cont);
	}
}
