package com.gnn.attendance;

import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import java.util.*;
import android.widget.*;
import android.os.*;
import android.widget.AdapterView.*;
import android.view.*;
import android.content.*;

public class RedList extends AppCompatActivity 
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
		for (int i=1;i <= m.totalStudent();i++)
		{
			int abs=m.totalDays() - Integer.parseInt(m.getNoOfAbsent(m.getStudentName(i)));
			if (abs >= m.redList())
			{
				lists.add(m.getStudentName(i));
			}
		}
		if (!lists.isEmpty())
		{
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
			empyty("There is No Students");
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
