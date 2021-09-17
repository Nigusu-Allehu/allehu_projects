package com.gnn.attendance;

import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;

public class Profiler extends AppCompatActivity 
{
	Toolbar toolbar;

	public static int temp=0;

	TextInputLayout sch_name_input,tch_name_input,tch_sec_input,year_input,admin_name_input,admin_phone_input;

	EditText sch_name,tch_name,tch_sec,year,admin_name,admin_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

		setup();

    }
	public void setup()
	{
		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);

		sch_name_input = (TextInputLayout)findViewById(R.id.sch_name_input);
		sch_name = (EditText)findViewById(R.id.sch_name);
		tch_name_input = (TextInputLayout)findViewById(R.id.tch_name_input);
		tch_name = (EditText)findViewById(R.id.tch_name);
		tch_sec_input = (TextInputLayout)findViewById(R.id.tch_sec_input);
		tch_sec = (EditText)findViewById(R.id.tch_sec);
		year_input = (TextInputLayout)findViewById(R.id.year_input);
		year = (EditText)findViewById(R.id.year);
		admin_phone_input = (TextInputLayout)findViewById(R.id.ad_phone_input);
		admin_phone = (EditText)findViewById(R.id.ad_phone);
		admin_name_input = (TextInputLayout)findViewById(R.id.ad_name_input);
		admin_name = (EditText)findViewById(R.id.ad_name);

	}

	@Override
	public void onBackPressed()
	{
		if (temp == 1)
		{
			finishAffinity();
		}
		else
		{
			super.onBackPressed();
		}
	}
	public void save(View v)
	{
		if (!sch_name.getText().toString().equals(""))
		{
			sch_name_input.setError("");
			if (!tch_name.getText().toString().equals(""))
			{
				tch_name_input.setError("");
				if (!tch_sec.getText().toString().equals(""))
				{
					tch_sec_input.setError("");

					if (!admin_name.getText().toString().equals(""))
					{
						admin_name_input.setError("");

						if (!admin_phone.getText().toString().equals(""))
						{
							admin_phone_input.setError("");

							if (!year.getText().toString().equals(""))
							{
								year_input.setError("");
								ProfileHelper p=new ProfileHelper(this);
								p.saveProfile(sch_name.getText().toString(), tch_name.getText().toString(), tch_sec.getText().toString(), admin_name.getText().toString(), admin_phone.getText().toString(), year.getText().toString());
								android.widget.Toast.makeText(getApplicationContext(), "Profile saved", android.widget.Toast.LENGTH_SHORT).show();
								finishAffinity();
								Intent i=new Intent(getApplicationContext(), MainActivity.class);
								startActivity(i);
							}
							else
							{
								year_input.setError("Invalid year Number");
							}
						}
						else
						{
							admin_phone_input.setError("Invalid Phone Numner");
						}
					}
					else
					{
						admin_name_input.setError("invalid Adminstrator Name");
					}
				}
				else
				{
					tch_sec_input.setError("Inavlid Teacher Section");
				}
			}
			else
			{
				tch_name_input.setError("Invalid Teacher Name");
			}
		}
		else
		{
			sch_name_input.setError("Invalid School Name");
		}
	}
	@Override
	public boolean onSupportNavigateUp()
	{
		if (temp == 1)
		{
			finishAffinity();
		}
		else
		{
			super.onBackPressed();
		}
		return true;
	}
}
