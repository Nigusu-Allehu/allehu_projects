package com.gnn.attendance;

import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;
import android.os.*;
import android.support.v4.widget.*;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.*;

public class AddStudent extends AppCompatActivity 
{
	Toolbar toolbar;
	CoordinatorLayout cordinate;
	TextInputLayout std_name,std_age;
	EditText name,phone;
	Spinner sex;
	MainActivity mm=new MainActivity();
	Main m=new Main(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

		setup();

    }
	public void setup()
	{
		std_name = (TextInputLayout)findViewById(R.id.std_name_input);
		std_age = (TextInputLayout)findViewById(R.id.std_age_input);
		name = (EditText)findViewById(R.id.std_name);
		phone = (EditText)findViewById(R.id.std_age);
		sex = (Spinner)findViewById(R.id.std_sex);
		cordinate = (CoordinatorLayout)findViewById(R.id.mainCoordinatorLayout);

		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);
	}

	public void add_std(View v)
	{
		if (!name.getText().toString().equals(""))
		{
			std_name.setError("");
			if (!phone.getText().toString().equals(""))
			{
				std_age.setError("");
				try
				{
					if (name.getText().toString().endsWith(" "))
					{
						String a=name.getText().toString().trim();
						m.addStudent(a.toString(), sex.getSelectedItem().toString(), phone.getText().toString());
						Snackbar.make(cordinate, name.getText().toString() + " Added", Snackbar.LENGTH_SHORT).show();
						name.setText("");
						phone.setText("");
					}
					else
					{
						m.addStudent(name.getText().toString(), sex.getSelectedItem().toString(), phone.getText().toString());
						android.widget.Toast.makeText(getApplicationContext(), name.getText().toString() + " Added", 3).show();
						name.setText("");
						phone.setText("");
					}
				}
				catch (Exception e)
				{
					android.widget.Toast.makeText(getApplicationContext(), "Error :  Can't add students", 3).show();
				}

				///////////do something

			}
			else
			{
				std_age.setError("Invalid Phone");
			}
		}
		else
		{
			std_name.setError("Invalid Name");
		}
	}

	@Override
	public boolean onSupportNavigateUp()
	{
		super.onBackPressed();
		MainActivity m=new MainActivity();
		return true;
	}

	@Override
	public void onBackPressed()
	{
		MainActivity m=new MainActivity();

		super.onBackPressed();
	}
}
