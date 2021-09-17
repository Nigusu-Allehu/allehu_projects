package com.gnn.attendance;

import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.*;
import android.os.*;
import android.support.v4.widget.*;
import android.view.*;
import android.widget.*;
import android.content.*;

public class ProgramCrt extends AppCompatActivity 
{
	Toolbar toolbar;

	private ProfileHelper p;

	private EditText m1,m2,m3,m4,m5,m6,m7,m8,m9,t1,t2,t3,t4,t5,t6,t7,t8,t9,w1,w2,w3,w4,w5,w6,w7,w8,w9,th1,th2,th3,th4,th5,th6,th7,th8,th9,f1,f2,f3,f4,f5,f6,f7,f8,f9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programcrt);

		setup();

    }
	public void setup()
	{
		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);

		p = new ProfileHelper(this);

		m1 = (EditText)findViewById(R.id.m1);
		m2 = (EditText)findViewById(R.id.m2);
		m3 = (EditText)findViewById(R.id.m3);
		m4 = (EditText)findViewById(R.id.m4);
		m5 = (EditText)findViewById(R.id.m5);
		m6 = (EditText)findViewById(R.id.m6);
		m7 = (EditText)findViewById(R.id.m7);
		m8 = (EditText)findViewById(R.id.m8);
		m9 = (EditText)findViewById(R.id.m9);
		t1 = (EditText)findViewById(R.id.t1);
		t2 = (EditText)findViewById(R.id.t2);
		t3 = (EditText)findViewById(R.id.t3);
		t4 = (EditText)findViewById(R.id.t4);
		t5 = (EditText)findViewById(R.id.t5);
		t6 = (EditText)findViewById(R.id.t6);
		t7 = (EditText)findViewById(R.id.t7);
		t8 = (EditText)findViewById(R.id.t8);
		t9 = (EditText)findViewById(R.id.t9);
		w1 = (EditText)findViewById(R.id.w1);
		w2 = (EditText)findViewById(R.id.w2);
		w3 = (EditText)findViewById(R.id.w3);
		w4 = (EditText)findViewById(R.id.w4);
		w5 = (EditText)findViewById(R.id.w5);
		w6 = (EditText)findViewById(R.id.w6);
		w7 = (EditText)findViewById(R.id.w7);
		w8 = (EditText)findViewById(R.id.w8);
		w9 = (EditText)findViewById(R.id.w9);
		th1 = (EditText)findViewById(R.id.th1);
		th2 = (EditText)findViewById(R.id.th2);
		th3 = (EditText)findViewById(R.id.th3);
		th4 = (EditText)findViewById(R.id.th4);
		th5 = (EditText)findViewById(R.id.th5);
		th6 = (EditText)findViewById(R.id.th6);
		th7 = (EditText)findViewById(R.id.th7);
		th8 = (EditText)findViewById(R.id.th8);
		th9 = (EditText)findViewById(R.id.th9);
		f1 = (EditText)findViewById(R.id.f1);
		f2 = (EditText)findViewById(R.id.f2);
		f3 = (EditText)findViewById(R.id.f3);
		f4 = (EditText)findViewById(R.id.f4);
		f5 = (EditText)findViewById(R.id.f5);
		f6 = (EditText)findViewById(R.id.f6);
		f7 = (EditText)findViewById(R.id.f7);
		f8 = (EditText)findViewById(R.id.f8);
		f9 = (EditText)findViewById(R.id.f9);

	}
	public void save(View v)
	{
		p.saveProgram(m1.getText().toString() + ",#,"
					  + m2.getText().toString() + ",#,"
					  + m3.getText().toString() + ",#,"
					  + m4.getText().toString() + ",#,"
					  + m5.getText().toString() + ",#,"
					  + m6.getText().toString() + ",#,"
					  + m7.getText().toString() + ",#,"
					  + m8.getText().toString() + ",#,"
					  + m9.getText().toString() + ",#,"
					  + t1.getText().toString() + ",#,"
					  + t2.getText().toString() + ",#,"
					  + t3.getText().toString() + ",#,"
					  + t4.getText().toString() + ",#,"
					  + t5.getText().toString() + ",#,"
					  + t6.getText().toString() + ",#,"
					  + t7.getText().toString() + ",#,"
					  + t8.getText().toString() + ",#,"
					  + t9.getText().toString() + ",#,"
					  + w1.getText().toString() + ",#,"
					  + w2.getText().toString() + ",#,"
					  + w3.getText().toString() + ",#,"
					  + w4.getText().toString() + ",#,"
					  + w5.getText().toString() + ",#,"
					  + w6.getText().toString() + ",#,"
					  + w7.getText().toString() + ",#,"
					  + w8.getText().toString() + ",#,"
					  + w9.getText().toString() + ",#,"
					  + th1.getText().toString() + ",#,"
					  + th2.getText().toString() + ",#,"
					  + th3.getText().toString() + ",#,"
					  + th4.getText().toString() + ",#,"
					  + th5.getText().toString() + ",#,"
					  + th6.getText().toString() + ",#,"
					  + th7.getText().toString() + ",#,"
					  + th8.getText().toString() + ",#,"
					  + th9.getText().toString() + ",#,"
					  + f1.getText().toString() + ",#,"
					  + f2.getText().toString() + ",#,"
					  + f3.getText().toString() + ",#,"
					  + f4.getText().toString() + ",#,"
					  + f5.getText().toString() + ",#,"
					  + f6.getText().toString() + ",#,"
					  + f7.getText().toString() + ",#,"
					  + f8.getText().toString() + ",#,"
					  + f9.getText().toString());
		Toast.makeText(getApplicationContext(), "Program Saves Succesfully", Toast.LENGTH_SHORT).show();
		super.onBackPressed();
		super.onBackPressed();
		startActivity(new Intent(getApplicationContext(), Program.class));
	}
	@Override
	public boolean onSupportNavigateUp()
	{
		super.onBackPressed();
		return true;
	}
}
