package com.gnn.attendance;

import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.*;
import android.os.*;
import android.support.v4.widget.*;
import android.view.*;
import android.widget.*;
import android.widget.TextView.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.content.*;

public class Students extends AppCompatActivity 
{
	Toolbar toolbar;

	List lists=new ArrayList<String>();

	private ListView listView;

	private ListAdapter adapter;

	Main m=new Main(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students);

		setup();

    }
	public void setup()
	{
		toolbar = (Toolbar)findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		StatusBarUtil.setColor(this, getResources().getColor(R.color.accent), 0);
		listView = (ListView)findViewById(R.id.studs_list_view);

		list();
	}
	public void list()
	{
		if (!m.getStudents().isEmpty())
		{
			lists = m.getStudents();
			adapter = new List_Adapter(this, lists);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
					{
						StdAbout.name = listView.getItemAtPosition(p3).toString();
						startActivity(new Intent(getApplicationContext(), StdAbout.class));
					}

				});
		}
		else
		{
			empyty("There is no Students! Please Add Students First");
		}
	}
	public void empyty(String msg)
	{List lst=new ArrayList<String>();
		lst.clear();
		lst.add(msg);
		ListAdapter lad=new ArrayAdapter<String>(getApplicationContext(), R.layout.emitem2, R.id.item_text, lst);
		listView.setAdapter(lad);
	}
	@Override
	public boolean onSupportNavigateUp()
	{
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
		return true;
	}
}
