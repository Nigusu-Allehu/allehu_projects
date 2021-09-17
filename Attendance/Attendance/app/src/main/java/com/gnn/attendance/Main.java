package com.gnn.attendance;

import com.gnn.attendance.DataHelper.*;
import android.content.*;
import android.database.*;
import java.util.*;

public class Main
{
	ProfileHelper pf;

	Context cnn;

	DBHelper db;

	String currentMeckup="";

	public Main(Context cn)
	{
		db = new DBHelper(cn);
		pf = new ProfileHelper(cn);
	}

	public void addStudent(String name, String sex, String phone)
	{
		db.addStudent(db, name, sex, phone);
	}
	public String getStudents(int i)
	{//get studens with name,sex
		final String a;
		Cursor cr=db.getstudentlist(db, i);
		cr.moveToFirst();
		do{
			a = cr.getString(0) + "," + cr.getString(1) ;
		}while(cr.moveToNext());	
		return a;
	}
	public String getStudentName(int i)
	{
		//get Studwnts only name
		String a=getStudents(i);
		String[] aa=a.split(",");
		return aa[0].trim().toString();
	}
	public String getStudentSex(String name)
	{//get students only sex with name
		final String a;
		Cursor cr=db.getstudentList(db, name);
		cr.moveToFirst();
		do{
			a = cr.getString(0);
		}while(cr.moveToNext());	
		return a;
	}
	public String getStudentSex(int i)
	{//get students sex with row number
		String a=getStudents(i);
		String[] aa=a.split(",");
		return aa[1].trim().toString();
	}
	public String getStudentNo(String name)
	{//get students only sex with name
		final String a;
		Cursor cr=db.getstudentList(db, name);
		cr.moveToFirst();
		do{
			a = cr.getString(3);
		}while(cr.moveToNext());	
		return a;
	}
	public String getPhone(String name)
	{//get Stydents phone
		final String a;
		Cursor cr=db.getstudentList(db, name);
		cr.moveToFirst();
		do{
			a = cr.getString(1);
		}while(cr.moveToNext());	
		return a;
	}
	public String getDays(int i)
	{//get List of Days
		final String a;
		Cursor cr=db.getDays(db, i);
		cr.moveToFirst();
		do{
			a = cr.getString(0);
		}while(cr.moveToNext());	
		return a;
	}
	public String getAbsentStuds(String date)
	{//get students Absents on the day
		final String a;
		Cursor cr=db.getAbsStuds(db, date);
		cr.moveToFirst();
		do{
			a = cr.getString(0);
		}while(cr.moveToNext());	
		return a;
	}
	public String getAbsDates(String stdName)
	{//get Dates Absentd
		final String a;
		Cursor cr=db.getAbsDays(db, stdName);
		cr.moveToFirst();
		do{
			a = cr.getString(0);
		}while(cr.moveToNext());	
		return a;
	}
	public void addNewDay(String day, String students)
	{
		db.addNewDay(db, day, students);
	}
	public void addDayToStudent(String stdName, String day)
	{
		//add days to eaxh off Students
		if (!getAbsDates(stdName.toString()).equals(""))
		{
			db.addDaytoStudent(db, stdName, getAbsDates(stdName).toString() + "," + day);
		}
		else
		{
			db.addDaytoStudent(db, stdName, day);
		}
	}
	public void editNoOfAbsent(String name, String val)
	{//edit noof absent
		db.editnoOfAbsent(db, name, val);
	}
	public String getNoOfAbsent(String name)
	{//get no of absent output by number
		final String a;
		Cursor cr=db.getstudentList(db, name);
		cr.moveToFirst();
		do{
			a = cr.getString(2);
		}while(cr.moveToNext());	
		return a;
	}
	public void deletrAll()
	{
		//delete all database
		db.deleteAll(db);
	}
	public Boolean check(String name)
	{//check if 1 or 0
		final boolean b;
		Cursor cr=db.check(db, name);
		cr.moveToFirst();
		do{
			String a = cr.getString(0);
			if (a.toString().equals("1"))
			{
				b = true;
			}
			else
			{
				b = false;
			}
		}while(cr.moveToNext());	
		return b;
	}
	public Boolean checkMsgAd(String name)
	{//check if 1 or 0
		final boolean b;
		Cursor cr=db.check(db, name);
		cr.moveToFirst();
		do{
			String a = cr.getString(1);
			if (a.toString().equals("1"))
			{
				b = true;
			}
			else
			{
				b = false;
			}
		}while(cr.moveToNext());	
		return b;
	}
	public Boolean checkMsgPr(String name)
	{//check if 1 or 0
		final boolean b;
		Cursor cr=db.check(db, name);
		cr.moveToFirst();
		do{
			String a = cr.getString(1);
			if (a.toString().equals("1"))
			{
				b = true;
			}
			else
			{
				b = false;
			}
		}while(cr.moveToNext());	
		return b;
	}
	public int totalStudent()
	{//get total student
		int i=db.numberOfRows(db, Stud.STUD_TABLE);
		return i;
	}
	public int totalDays()
	{
		//get total days
		int i=db.numberOfRows(db, DateList.DATE_TABLE);
		return i;
	}
	public int totalMeckup()
	{
		int i=db.numberOfRows(db, Mec.MEC_DATA);
		return i;
	}
	public List getDays()
	{
		List l=new ArrayList();
		for (int i=1;i <= totalDays();i++)
		{
			l.add(getDays(i).toString() + "@@" + getAbsentStuds(getDays(i).toString()));
		}
		return l;
	}
	public List getStudents()
	{
		List lst=new ArrayList<String>();
		for (int i=1;i <= totalStudent();i++)
		{
			lst.add(getStudentName(i));
		}
		return lst;
	}
	public void turnOn(String name)
	{
		db.turn(db, name, "1");
	}
	public void turnOff(String name)
	{
		db.turn(db, name, "0");
	}
	public void turnOffAll()
	{
		for (int i=1;i <= totalStudent();i++)
		{
			turnOff(getStudentName(i).toString());
		}
	}
	public void turnOnMesssgeAd(String name)
	{
		db.turnMsgAd(db, name, "1");
	}
	public void turnOffMesssgeAd(String name)
	{
		db.turnMsgAd(db, name, "0");
	}
	public void turnOnMesssgePr(String name)
	{
		db.turnMsgPr(db, name, "1");
	}
	public void turnOffMesssgePr(String name)
	{
		db.turnMsgPr(db, name, "0");
	}
	public int redList()
	{
		final int a;
		Cursor cr=db.getProfile(db);
		cr.moveToFirst();
		do{
			a = Integer.parseInt(cr.getString(9));
		}while(cr.moveToNext());	
		return a;
	}
	public void editRed(String num)
	{
		db.editRedList(db, num);
	}
	public String getLastDays(String name)
	{
		String days="";
		int per=Integer.parseInt(pf.getPrMsg());
		int tot=totalDays() - Integer.parseInt(getNoOfAbsent(name));
		List list=new ArrayList<String>();
		String[] a=getAbsDates(name).split(",");
		try
		{
			for (int i=0;i < a.length;i++)
			{
				if  (!a[i].trim().toString().equals(""))
				{
					list.add(a[i].trim().toString());
				}
			}
			for (int i=tot - per;i < tot;i++)
			{
				days += list.get(i).toString() + ",";
			}
		}
		catch (Exception e)
		{
			days="No days";
		}
		return days;
	}
	///////Meckup class

	public void newMecupDate(String date, String prsStudents)
	{
		db.newMeckupDate(db, date, prsStudents);
	}
	public String getMeckupDays(int i)
	{
		final String a;
		Cursor cr=db.getMeckup(db, i);
		cr.moveToFirst();
		do{
			a = cr.getString(0);
		}while(cr.moveToNext());	
		return a;
	}
	public String getMeckupStuds(String day)
	{
		final String a;
		Cursor cr=db.getMeckup(db, day);
		cr.moveToFirst();
		do{
			a = cr.getString(0);
		}while(cr.moveToNext());	
		return a;
	}
	public List getMeckupDays()
	{
		List lst=new ArrayList<String>();
		for (int i=1;i <= totalMeckup();i++)
		{
			lst.add(getMeckupDays(i));
		}
		return lst;
	}
	public List getMeckupStudents()
	{
		List lst=new ArrayList<String>();
		for (int i=1;i <= totalMeckup();i++)
		{
			lst.add(getMeckupDays(i) + "@@" + getMeckupStuds(getMeckupDays(i)));
		}
		return lst;
	}
	public void turnOnMec(String name)
	{
		db.turnMec(db, name, "1");
	}
	public void turnOffMec(String name)
	{
		db.turnMec(db, name, "0");
	}
	public void turnOffAllMec()
	{
		for (int i=1;i <= totalStudent();i++)
		{
			turnOffMec(getStudentName(i).toString());
		}
	}
	public void saveCurrentMeckup(String day)
	{
		db.editCurrentMeckup(db, "yes,#," + day);
	}
	public void deleteMeckupCurrent()
	{
		db.editCurrentMeckup(db, "no,#,no");
	}
	public boolean isMeckupSaved()
	{
		final boolean a;
		Cursor cr=db.getMeckupCurrent(db);
		cr.moveToFirst();
		do{
			try
			{
				String[] aa=cr.getString(0).split(",#,");
				if (aa[0].trim().toString().equals("yes"))
				{
					currentMeckup = aa[1].trim().toString();
					a = true;
				}
				else
				{
					a = false;
				}
			}
			catch (Exception e)
			{a = false;}
		}while(cr.moveToNext());	
		return a;
	}
	public boolean checkMec(String name)
	{
		final boolean a;
		Cursor cr=db.checkMeckup(db, name);
		cr.moveToFirst();
		do{
			if (cr.getString(0).toString().equals("1"))
			{
				a = true;
			}
			else
			{
				a = false;
			}
		}while(cr.moveToNext());	
		return a;
	}
	public String getCurrentMeckup()
	{
		return currentMeckup;
	}
//////////End Meckup area
}
