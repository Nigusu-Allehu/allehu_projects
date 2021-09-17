package com.gnn.attendance;

import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.*;
import android.os.*;
import android.support.v4.widget.*;
import android.view.*;
import android.content.*;
import android.widget.*;

public class Program extends AppCompatActivity 
{
	Toolbar toolbar;

	private ProfileHelper p;

	private TextView m1,m2,m3,m4,m5,m6,m7,m8,m9,t1,t2,t3,t4,t5,t6,t7,t8,t9,w1,w2,w3,w4,w5,w6,w7,w8,w9,th1,th2,th3,th4,th5,th6,th7,th8,th9,f1,f2,f3,f4,f5,f6,f7,f8,f9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program);

		setup();

    }
	public void setup()
	{
		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);

		p = new ProfileHelper(this);

		m1 = (TextView)findViewById(R.id.m1);
		m2 = (TextView)findViewById(R.id.m2);
		m3 = (TextView)findViewById(R.id.m3);
		m4 = (TextView)findViewById(R.id.m4);
		m5 = (TextView)findViewById(R.id.m5);
		m6 = (TextView)findViewById(R.id.m6);
		m7 = (TextView)findViewById(R.id.m7);
		m8 = (TextView)findViewById(R.id.m8);
		m9 = (TextView)findViewById(R.id.m9);
		t1 = (TextView)findViewById(R.id.t1);
		t2 = (TextView)findViewById(R.id.t2);
		t3 = (TextView)findViewById(R.id.t3);
		t4 = (TextView)findViewById(R.id.t4);
		t5 = (TextView)findViewById(R.id.t5);
		t6 = (TextView)findViewById(R.id.t6);
		t7 = (TextView)findViewById(R.id.t7);
		t8 = (TextView)findViewById(R.id.t8);
		t9 = (TextView)findViewById(R.id.t9);
		w1 = (TextView)findViewById(R.id.w1);
		w2 = (TextView)findViewById(R.id.w2);
		w3 = (TextView)findViewById(R.id.w3);
		w4 = (TextView)findViewById(R.id.w4);
		w5 = (TextView)findViewById(R.id.w5);
		w6 = (TextView)findViewById(R.id.w6);
		w7 = (TextView)findViewById(R.id.w7);
		w8 = (TextView)findViewById(R.id.w8);
		w9 = (TextView)findViewById(R.id.w9);
		th1 = (TextView)findViewById(R.id.th1);
		th2 = (TextView)findViewById(R.id.th2);
		th3 = (TextView)findViewById(R.id.th3);
		th4 = (TextView)findViewById(R.id.th4);
		th5 = (TextView)findViewById(R.id.th5);
		th6 = (TextView)findViewById(R.id.th6);
		th7 = (TextView)findViewById(R.id.th7);
		th8 = (TextView)findViewById(R.id.th8);
		th9 = (TextView)findViewById(R.id.th9);
		f1 = (TextView)findViewById(R.id.f1);
		f2 = (TextView)findViewById(R.id.f2);
		f3 = (TextView)findViewById(R.id.f3);
		f4 = (TextView)findViewById(R.id.f4);
		f5 = (TextView)findViewById(R.id.f5);
		f6 = (TextView)findViewById(R.id.f6);
		f7 = (TextView)findViewById(R.id.f7);
		f8 = (TextView)findViewById(R.id.f8);
		f9 = (TextView)findViewById(R.id.f9);

		trimer(p.getProgram().toString());

	}
	public void trimer(String text)
	{
		try
		{

			String[] a=text.split(",#,");
			m1.setText(a[0].trim().toString());
			m2.setText(a[1].trim().toString());
			m3.setText(a[2].trim().toString());
			m4.setText(a[3].trim().toString());
			m5.setText(a[4].trim().toString());
			m6.setText(a[5].trim().toString());
			m7.setText(a[6].trim().toString());
			m8.setText(a[7].trim().toString());
			m9.setText(a[8].trim().toString());
			t1.setText(a[9].trim().toString());
			t2.setText(a[10].trim().toString());
			t3.setText(a[11].trim().toString());
			t4.setText(a[12].trim().toString());
			t5.setText(a[13].trim().toString());
			t6.setText(a[14].trim().toString());
			t7.setText(a[15].trim().toString());
			t8.setText(a[16].trim().toString());
			t9.setText(a[17].trim().toString());
			w1.setText(a[18].trim().toString());
			w2.setText(a[19].trim().toString());
			w3.setText(a[20].trim().toString());
			w4.setText(a[21].trim().toString());
			w5.setText(a[22].trim().toString());
			w6.setText(a[23].trim().toString());
			w7.setText(a[24].trim().toString());
			w8.setText(a[25].trim().toString());
			w9.setText(a[26].trim().toString());
			th1.setText(a[27].trim().toString());
			th2.setText(a[28].trim().toString());
			th2.setText(a[29].trim().toString());
			th3.setText(a[30].trim().toString());
			th4.setText(a[31].trim().toString());
			th5.setText(a[32].trim().toString());
			th6.setText(a[33].trim().toString());
			th7.setText(a[34].trim().toString());
			th8.setText(a[35].trim().toString());
			th9.setText(a[36].trim().toString());
			f1.setText(a[37].trim().toString());
			f2.setText(a[38].trim().toString());
			f3.setText(a[39].trim().toString());
			f4.setText(a[40].trim().toString());
			f5.setText(a[41].trim().toString());
			f6.setText(a[42].trim().toString());
			f7.setText(a[43].trim().toString());
			f8.setText(a[44].trim().toString());
			f9.setText(a[45].trim().toString());
		}
		catch (Exception e)
		{

		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.prog, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home :
				super.onBackPressed();
				break;
			case R.id.edit :
				startActivity(new Intent(getApplicationContext(), ProgramCrt.class));
				break;
		}
		return true;
	}

}
