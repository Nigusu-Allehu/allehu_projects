package com.gnn.attendance;

public class DataHelper
{
	public static String DATABASE_NAME="GNNdatabase";

	public static class Stud
	{
		public static String STUD_TABLE="Students";
		public static String STUD_NO="no";
		public static String STUD_NAME="Name";
		public static String STUD_SEX="Sex";
		public static String STUD_PHONE="Phone";
		public static String STUD_ABS="Absents";
	}
	public static class DateList
	{
		public static String DATE_TABLE="Dates";
		public static String DATE_NO="no";
		public static String DATE_NAME="Name";
		public static String DATE_DATE="Dates";
	}
	public static class StudList
	{
		public static String LIST_TABLE="AbsStuds";
		public static String LIST_NAME="Name";
		public static String LIST_DATE="Dates";
	}
	public static class Temp
	{
		public static String TEMP_TABLE="Temp";
		public static String TEMP_NAME="Name";
		public static String TEMP_VALUE="Value";
		public static String TEMP_MESSAGEAD="MsgAd";
		public static String TEMP_MESSAGEPR="MsgPr";
	}
	public static class Profile
	{
		public static String PROFILE_NO="no";
		public static String PROFILE_TABLE="Profile";
		public static String PROFILE_SCHOOLNAME="School_Name";
		public static String PROFILE_TEACHERNAME="Teacher_Name";
		public static String PROFILE_TEACHERSEC="Teacher_section";
		public static String PROFILE_ADMINNAME="Admin_Name";
		public static String PROFILE_ADMINPHONE="Admin_Phone";
		public static String PROFILE_YEAR="Year";
		public static String PROFILE_CURRENT="current";
		public static String PROFILE_MSGADMIN="msgAd";
		public static String PROFILE_MSGPARENT="msgpr";
		public static String PROFILE_REDLIST="red";
	}
	public static class Mec
	{
		public static String MEC_DATA="Meckup";
		public static String no="no";
		public static String MEC_STUDS="Students";
		public static String MEC_ABS_DAYS="abs";
		public static String MEC_DAYS="Days";
	}
	public static class TempMec
	{
		public static String TEMP_TABLE="TempMec";
		public static String TEMP_NAME="Name";
		public static String TEMP_VALUE="Value";
		public static String TEMP_CURRENT="Current";
		public static String TEMP_NO="no";
	}
}
