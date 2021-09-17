package com.gnn.attendance;

import android.content.*;
import android.database.sqlite.*;
import com.gnn.attendance.DataHelper.*;
import android.database.*;

public class DBHelper extends SQLiteOpenHelper
{
	public static String student="CREATE TABLE " + Stud.STUD_TABLE + "(" + Stud.STUD_NO + " INTEGER PRIMARY KEY autoincrement," + Stud.STUD_NAME + " text," + Stud.STUD_SEX + " text," + Stud.STUD_PHONE + " text," + Stud.STUD_ABS + " text);";
	public static String date="CREATE TABLE " + DateList.DATE_TABLE + "(" + DateList.DATE_NO + " INTEGER PRIMARY KEY autoincrement," + DateList.DATE_DATE + " text," + DateList.DATE_NAME + " text);";
	public static String std="CREATE TABLE " + StudList.LIST_TABLE + "(" + StudList.LIST_NAME + " text," + StudList.LIST_DATE + " text);";
	public static String tmp="CREATE TABLE " + Temp.TEMP_TABLE + "(" + Temp.TEMP_NAME + " text," + Temp.TEMP_VALUE + " text," + Temp.TEMP_MESSAGEAD + " text," + Temp.TEMP_MESSAGEPR + " text);";
	public static String profile="CREATE TABLE " + Profile.PROFILE_TABLE + "(" + Profile.PROFILE_NO + " INTEGER PRIMARY KEY autoincrement," + Profile.PROFILE_SCHOOLNAME + " text," + Profile.PROFILE_TEACHERNAME + " text," + Profile.PROFILE_TEACHERSEC + " text," + Profile.PROFILE_ADMINNAME + " text," + Profile.PROFILE_ADMINPHONE + " text," + Profile.PROFILE_YEAR + " text," + Profile.PROFILE_CURRENT + " text," + Profile.PROFILE_MSGADMIN + " text," + Profile.PROFILE_MSGPARENT + " text," + Profile.PROFILE_REDLIST + " text);";
	public static String meck="CREATE TABLE " + Mec.MEC_DATA + "(" + Mec.no + " INTEGER PRIMARY KEY autoincrement," + Mec.MEC_DAYS + " text," + Mec.MEC_STUDS + " text);";
	public static String tmp2="CREATE TABLE " + TempMec.TEMP_TABLE + "(" + TempMec.TEMP_NO + " INTEGER PRIMARY KEY autoincrement," + Temp.TEMP_NAME + " text," + Temp.TEMP_VALUE + " text," + TempMec.TEMP_CURRENT + " text);";

	public DBHelper(Context cn)
	{
		super(cn, DataHelper.DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		p1.execSQL(student);
		p1.execSQL(date);
		p1.execSQL(std);
		p1.execSQL(tmp);
		p1.execSQL(profile);
		p1.execSQL(meck);
		p1.execSQL(tmp2);
	}
	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{

	}
	public void addStudent(DBHelper db, String name, String sex, String phone)
	{
		///add students to Students Temp and StdList

		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv1=new ContentValues();
		ContentValues cv2=new ContentValues();
		ContentValues cv3=new ContentValues();
		ContentValues cv4=new ContentValues();
		cv1.put(Stud.STUD_NAME, name);
		cv1.put(Stud.STUD_SEX, sex);
		cv1.put(Stud.STUD_PHONE, phone);
		cv1.put(Stud.STUD_ABS, 0);
		cv2.put(StudList.LIST_NAME, name);
		cv2.put(StudList.LIST_DATE, "");
		cv3.put(Temp.TEMP_NAME, name);
		cv3.put(Temp.TEMP_VALUE, "0");
		cv3.put(Temp.TEMP_MESSAGEAD, "0");
		cv3.put(Temp.TEMP_MESSAGEPR, "0");
		cv4.put(TempMec.TEMP_NAME, name);
		cv4.put(TempMec.TEMP_VALUE, "0");
		Long k1=sq.insert(Stud.STUD_TABLE, null, cv1);
		Long k2=sq.insert(StudList.LIST_TABLE, null, cv2);
		Long k3=sq.insert(Temp.TEMP_TABLE, null, cv3);
		Long k4=sq.insert(TempMec.TEMP_TABLE, null, cv4);
	}
	//////////// Profile Area
	public void saveProfile(DBHelper db)
	{
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Profile.PROFILE_SCHOOLNAME, " ");
		cv.put(Profile.PROFILE_TEACHERNAME, " ");
		cv.put(Profile.PROFILE_TEACHERSEC, " ");
		cv.put(Profile.PROFILE_ADMINNAME, " ");
		cv.put(Profile.PROFILE_ADMINPHONE, " ");
		cv.put(Profile.PROFILE_YEAR, " ");
		cv.put(Profile.PROFILE_CURRENT, " ");
		cv.put(Profile.PROFILE_MSGADMIN, "no");
		cv.put(Profile.PROFILE_MSGPARENT, "no");
		cv.put(Profile.PROFILE_REDLIST, "5");
		Long k=sq.insert(Profile.PROFILE_TABLE, null, cv);
	}

	public void editProfile(DBHelper db, String schName, String tchName, String tchSec, String adName, String adPhone, String year)
	{
		//turn on or off student with name
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Profile.PROFILE_SCHOOLNAME, schName);
		cv.put(Profile.PROFILE_TEACHERNAME, tchName);
		cv.put(Profile.PROFILE_TEACHERSEC, tchSec);
		cv.put(Profile.PROFILE_ADMINNAME, adName);
		cv.put(Profile.PROFILE_ADMINPHONE, adPhone);
		cv.put(Profile.PROFILE_YEAR, year);
		String args[]={"1"};
		sq.update(Profile.PROFILE_TABLE, cv, Profile.PROFILE_NO + " LIKE ?", args);
	}

	public void editCurrent(DBHelper db, String day)
	{
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Profile.PROFILE_CURRENT, day);
		String args[]={"1"};
		sq.update(Profile.PROFILE_TABLE, cv, Profile.PROFILE_NO + " LIKE ?", args);
	}

	public void editMsgA(DBHelper db, String num)
	{
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Profile.PROFILE_MSGADMIN, num);
		String args[]={"1"};
		sq.update(Profile.PROFILE_TABLE, cv, Profile.PROFILE_NO + " LIKE ?", args);
	}

	public void editMsgP(DBHelper db, String num)
	{
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Profile.PROFILE_MSGPARENT, num);
		String args[]={"1"};
		sq.update(Profile.PROFILE_TABLE, cv, Profile.PROFILE_NO + " LIKE ?", args);
	}
	public void editRedList(DBHelper db, String num)
	{
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Profile.PROFILE_REDLIST, num);
		String args[]={"1"};
		sq.update(Profile.PROFILE_TABLE, cv, Profile.PROFILE_NO + " LIKE ?", args);
	}
	public Cursor getProfile(DBHelper db)
	{//get Students by row number with Name,sex,phone amd no of absent
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection= "no LIKE ?";
		String args[]={"1"};
		String columns[]={Profile.PROFILE_SCHOOLNAME,Profile.PROFILE_TEACHERNAME,Profile.PROFILE_TEACHERSEC,Profile.PROFILE_ADMINNAME,Profile.PROFILE_ADMINPHONE,Profile.PROFILE_YEAR,Profile.PROFILE_CURRENT,Profile.PROFILE_MSGADMIN,Profile.PROFILE_MSGPARENT,Profile.PROFILE_REDLIST};
		Cursor cr=sq.query(Profile.PROFILE_TABLE, columns, selection, args, null, null, null);
		return cr;
	}

	//////////// End Profile Area


	//////////// Meckup are

	public Cursor getMeckup(DBHelper db, int i)
	{//get Students by row number with Name,sex,phone amd no of absent
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection= Mec.no + " LIKE ?";
		String args[]={String.valueOf(i)};
		String columns[]={Mec.MEC_DAYS};
		Cursor cr=sq.query(Mec.MEC_DATA, columns, selection, args, null, null, null);
		return cr;
	}
	public Cursor getMeckup(DBHelper db, String date)
	{//get Students by row number with Name,sex,phone amd no of absent
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection= Mec.MEC_DAYS + " LIKE ?";
		String args[]={date};
		String columns[]={Mec.MEC_STUDS};
		Cursor cr=sq.query(Mec.MEC_DATA, columns, selection, args, null, null, null);
		return cr;
	}
	public void newMeckupDate(DBHelper db, String mecDay, String prsStudents)
	{
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Mec.MEC_DAYS, mecDay);
		cv.put(Mec.MEC_STUDS, prsStudents);
		Long k=sq.insert(Mec.MEC_DATA, null, cv);
	}

	public void turnMec(DBHelper db, String name, String val)
	{
		//turn on or off student with name
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(TempMec.TEMP_VALUE, val);
		String args[]={name};
		sq.update(TempMec.TEMP_TABLE, cv, TempMec.TEMP_NAME + " LIKE ?", args);

	}

	public Cursor getMeckupCurrent(DBHelper db)
	{//get Students by row number with Name,sex,phone amd no of absent
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection= TempMec.TEMP_NO + " LIKE ?";
		String args[]={"1"};
		String columns[]={TempMec.TEMP_CURRENT};
		Cursor cr=sq.query(TempMec.TEMP_TABLE, columns, selection, args, null, null, null);
		return cr;
	}

	public void editCurrentMeckup(DBHelper db, String day)
	{
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(TempMec.TEMP_CURRENT, day);
		String args[]={"1"};
		sq.update(TempMec.TEMP_TABLE, cv, TempMec.TEMP_NO + " LIKE ?", args);
	}

	public Cursor checkMeckup(DBHelper db, String name)
	{//get Students by row number with Name,sex,phone amd no of absent
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection= TempMec.TEMP_NAME + " LIKE ?";
		String args[]={name};
		String columns[]={TempMec.TEMP_VALUE};
		Cursor cr=sq.query(TempMec.TEMP_TABLE, columns, selection, args, null, null, null);
		return cr;
	}
	//////////// enf Meckup are


	public Cursor getstudentlist(DBHelper db, int i)
	{//get Students by row number with Name,sex,phone amd no of absent
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection= "no LIKE ?";
		String args[]={String.valueOf(i)};
		String columns[]={Stud.STUD_NAME,Stud.STUD_SEX,Stud.STUD_PHONE,Stud.STUD_ABS};
		Cursor cr=sq.query(Stud.STUD_TABLE, columns, selection, args, null, null, null);
		return cr;
	}
	public Cursor getstudentList(DBHelper db, String name)
	{//get Students by name with sex phone and no of absent
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection= "Name LIKE ?";
		String args[]={name};
		String columns[]={Stud.STUD_SEX,Stud.STUD_PHONE,Stud.STUD_ABS,Stud.STUD_NO};
		Cursor cr=sq.query(Stud.STUD_TABLE, columns, selection, args, null, null, null);
		return cr;
	}
	public void editnoOfAbsent(DBHelper db, String name, String val)
	{//edit no og absent in Students table
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Stud.STUD_ABS, val);
		String args[]={name};
		sq.update(Stud.STUD_TABLE, cv, Stud.STUD_NAME + " LIKE ?", args);
	}
	public void turn(DBHelper db, String name, String val)
	{
		//turn on or off student with name
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Temp.TEMP_VALUE, val);
		String args[]={name};
		sq.update(Temp.TEMP_TABLE, cv, Temp.TEMP_NAME + " LIKE ?", args);

	}
	public void turnMsgAd(DBHelper db, String name, String val)
	{
		//turn on or off student with name
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Temp.TEMP_MESSAGEAD, val);
		String args[]={name};
		sq.update(Temp.TEMP_TABLE, cv, Temp.TEMP_NAME + " LIKE ?", args);

	}
	public void turnMsgPr(DBHelper db, String name, String val)
	{
		//turn on or off student with name
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(Temp.TEMP_MESSAGEPR, val);
		String args[]={name};
		sq.update(Temp.TEMP_TABLE, cv, Temp.TEMP_NAME + " LIKE ?", args);

	}
	public int numberOfRows(DBHelper db, String table)
	{    //get no of rows with table
		SQLiteDatabase sq = db.getReadableDatabase(); 
		int numRows = (int) DatabaseUtils.queryNumEntries(sq, table);   
		return numRows;  
	}
	public void addNewDay(DBHelper db, String day, String students)
	{//creTe a new day and add Absent students 
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv1=new ContentValues();
		cv1.put(DateList.DATE_DATE, day);
		cv1.put(DateList.DATE_NAME, students);
		Long k1=sq.insert(DateList.DATE_TABLE, null, cv1);
	}
	public void addDaytoStudent(DBHelper db, String stdName, String dates)
	{//add days to each absent Studens
		SQLiteDatabase sq=db.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(StudList.LIST_DATE, dates);
		String args[]={stdName};
		sq.update(StudList.LIST_TABLE, cv, StudList.LIST_NAME + " LIKE ?", args);
	}
	public Cursor getDays(DBHelper db, int i)
	{//get date by number
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection= "no LIKE ?";
		String args[]={String.valueOf(i)};
		String columns[]={DateList.DATE_DATE};
		Cursor cr=sq.query(DateList.DATE_TABLE, columns, selection, args, null, null, null);
		return cr;
	}
	public Cursor check(DBHelper db, String nam)
	{//check if 1 or 0
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection= Temp.TEMP_NAME + " LIKE ?";
		String args[]={nam};
		String columns[]={Temp.TEMP_VALUE,Temp.TEMP_MESSAGEAD,Temp.TEMP_MESSAGEPR};
		Cursor cr=sq.query(Temp.TEMP_TABLE, columns, selection, args, null, null, null);
		return cr;
	}
	public Cursor getAbsStuds(DBHelper db, String date)
	{//get Students in Days
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection=DateList.DATE_DATE + " LIKE ?";
		String args[]={date};
		String columns[]={DateList.DATE_NAME};
		Cursor cr=sq.query(DateList.DATE_TABLE, columns, selection, args, null, null, null);
		return cr;
	}
	public Cursor getAbsDays(DBHelper db, String stdNam)
	{//get Absented days in Students
		SQLiteDatabase sq=db.getReadableDatabase();
		String selection=StudList.LIST_NAME + " LIKE ?";
		String args[]={stdNam};
		String columns[]={StudList.LIST_DATE};
		Cursor cr=sq.query(StudList.LIST_TABLE, columns, selection, args, null, null, null);
		return cr;
	}
	public void deleteAll(DBHelper db)
	{
		SQLiteDatabase dv=db.getWritableDatabase();
		dv.delete(Stud.STUD_TABLE, null, null);
		dv.delete(StudList.LIST_TABLE, null, null);
		dv.delete(DateList.DATE_TABLE, null, null);
		dv.delete(Temp.TEMP_TABLE, null, null);
	}
}
