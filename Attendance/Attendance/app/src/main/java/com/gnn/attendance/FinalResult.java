package com.gnn.attendance;

import android.support.v7.app.*;
import java.io.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.app.ProgressDialog;

public class FinalResult extends AppCompatActivity
{
	Main m=new Main(this);
	ProfileHelper pf=new ProfileHelper(this);

	public ProgressDialog prg;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Button b=new Button(this);
		b.setText("Click here to start");
		prg = new ProgressDialog(this);
		prg.setMessage("Please Wait");
		setContentView(b);
		b.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					MakeThread mkt=new MakeThread();
					mkt.start();
				}
			});
	}

	public void make()
	{

	}
	public class MakeThread extends Thread
	{
		@Override
		public void run()
		{
			FinalResult.this.runOnUiThread(new Runnable(){
					@Override
					public void run()
					{
						prg.show();
					}
				});

			for (int i=1;i <= m.totalStudent();i++)
			{
				///Making
				save(m.getStudentName(i).toString(), "<html></br><meta name=\"viewport\" content=\"width=device-width; initial-scale=0.5; minimum-scale=0.5; maximum-scale=1.0\"/></br><style>*{margin:0px;} .hea{\nmargin:0px;\ntext-align:center;\nbackground-color:black;\ncolor:white;\n}.div{ text-align:right; }</style><body><h1 class=\"hea\"> " + pf.getSchoolName() + " School</h1></br>\n\n<p>Student Name :" + m.getStudentName(i).toString() + "</p></br><p>Sex :" + m.getStudentSex(m.getStudentName(i)) + "</p></br>\n" + m.getStudentName(i).toString() + " has been absence for " + String.valueOf(m.totalDays() - Integer.parseInt(m.getNoOfAbsent(m.getStudentName(i)))) + " those days are :-</p></br>\n<p>" + m.getAbsDates(m.getStudentName(i)) + "</p><br><br><div class\"div\"><p>" + pf.getYear() + "</p><br><p>GNN Attendance</p></div>");
				//End Making
			}
			FinalResult.this.runOnUiThread(new Runnable(){
					@Override
					public void run()
					{
						prg.hide();
						Toast.makeText(getApplicationContext(), "Finished", 3).show();
					}
				});
		}
	}
	public void save(String name, String result)
	{
		File f=new File("/sdcard/Attendance Final/");
		if (!f.exists())
		{
			f.mkdirs();
		}
		File file=new File(f + "/" + name + ".html");
		try
		{
			file.createNewFile();
			FileOutputStream fos=new FileOutputStream(file);
			OutputStreamWriter osw=new OutputStreamWriter(fos);
			osw.append(result);
			osw.close();
			fos.close();
		}
		catch (Exception e)
		{
		}
	}
}
