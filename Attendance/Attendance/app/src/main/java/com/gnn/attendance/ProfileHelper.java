package com.gnn.attendance;

import android.content.*;
import com.gnn.attendance.DataHelper.*;
import android.database.*;
import android.widget.*;
import java.io.*;

public class ProfileHelper
{
	public static String privatePath="/sdcard/Android/data/com.gnn.attendance/files/";

	DBHelper db;

	Context cn;

	String schoolName="";
	String teacherName="";
	String teacherSec="";
	String adminName="";
	String adminPhone="";
	String year="";
	String currentDay="";
	String msgAd="";
	String msgPr="";
	Boolean saved=false;

	public ProfileHelper(Context cn)
	{
		db = new DBHelper(cn);
		this.cn = cn;
		a();
	}
	public void a()
	{
		File f=new File(privatePath);
		if (!f.exists())
		{
			f.mkdirs();
		}
	}
	public void saveProfile(String schName, String tchName, String tchSec, String adName, String adPhone, String year)
	{
		try
		{
			if (db.numberOfRows(db, DataHelper.Profile.PROFILE_TABLE) > 0)
			{
				db.editProfile(db, schName, tchName, tchSec, adName, adPhone, year);
			}
			else
			{
				db.saveProfile(db);
				saveProfile(schName, tchName, tchSec, adName, adPhone, year);
			}
		}
		catch (Exception e)
		{
			db.saveProfile(db);
		}
	}
	public void getProfile()
	{//get Stydents phone
		try
		{
			Cursor cr=db.getProfile(db);
			cr.moveToFirst();
			do{
				schoolName = cr.getString(0);
				teacherName = cr.getString(1);
				teacherSec = cr.getString(2);
				adminName = cr.getString(3);
				adminPhone = cr.getString(4);
				year = cr.getString(5);
				trimCurrent(cr.getString(6));
				msgAd = cr.getString(7);
				msgPr = cr.getString(8);
			}while(cr.moveToNext());
		}
		catch (Exception e)
		{cn.startActivity(new Intent(cn, Profiler.class));}
	}
	public void newCurrentDay(String day)
	{
		db.editCurrent(db, "yes@@" + day + "-" + getYear());
	}
	public void deleteCurrentDay()
	{
		db.editCurrent(db, "no@@no");
	}
	public void editMsgAdmin(String num)
	{
		db.editMsgA(db, num);
	}
	public void editMsgParent(String num)
	{
		db.editMsgP(db, num);
	}
	public String getSchoolName()
	{
		getProfile();
		return schoolName;
	}
	public String getTeacherName()
	{
		getProfile();
		return teacherName;
	}
	public String getTeacherSection()
	{
		getProfile();
		return teacherSec;
	}
	public String getAdminName()
	{
		getProfile();
		return adminName;
	}
	public String getAdminPhone()
	{
		getProfile();
		return adminPhone;
	}
	public String getAdminMsg()
	{
		getProfile();
		return msgAd;
	}
	public String getPrMsg()
	{
		getProfile();
		return msgPr;
	}
	public String getYear()
	{
		getProfile();
		return year;
	}
	public void trimCurrent(String cr)
	{
		String[] a=cr.split("@@");
		if (a[0].trim().toString().equals("yes"))
		{
			currentDay = a[1].trim().toString();
			saved = true;
		}
		else if (a[0].trim().toString().equals("no"))
		{
			saved = false;
			currentDay = "";
		}
	}
	public boolean isSaved()
	{
		getProfile();
		return saved;
	}
	public String getCurrentDay()
	{
		getProfile();
		return currentDay;
	}
	public void mainSave(String dest, String cont)
	{
		File f=new File(dest);
		try
		{
			f.createNewFile();
			FileOutputStream fos=new FileOutputStream(f);
			OutputStreamWriter osw=new OutputStreamWriter(fos);
			osw.append(cont);
			osw.close();
			fos.close();
		}
		catch (Exception e)
		{}
	}
	public void saveProgram(String cont)
	{
		mainSave(privatePath + "/program.inf", cont);
	}
	public String getProgram()
	{
		String program="";

		File f=new File(privatePath + "/program.inf");
		if (f.exists())
		{
			try
			{
				DataInputStream dis=new DataInputStream(new FileInputStream(privatePath + "/program.inf"));
				program = dis.readLine();
			}
			catch (Exception e)
			{

			}
		}
		else
		{
			//Intent i=new Intent(cn, ProgramCrt.class);
			//cn.startActivity(i);
		}
		return program;
	}
}
