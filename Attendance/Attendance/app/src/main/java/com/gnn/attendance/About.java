package com.gnn.attendance;
import java.io.*;

public class About
{
	public String privatePath="/sdcard/Android/data/com.GNN.attendance/files/";

	public void aboutSaver()
	{
		String html="<html><body><h3>Developers</h3><p>Getnet Kassahun</p><p>Natnael Biya</p><p>Nigusu Solomon</p><br><br>Please Don't Modify without Developer's Permission!!!</body></html>";
		save(html);
	}
	public void save(String text)
	{
		try
		{
			File f=new File(privatePath.toString() + "/about.html");
			f.createNewFile();
			FileOutputStream fout=new FileOutputStream(f);
			OutputStreamWriter osw=new OutputStreamWriter(fout);
			osw.append(text);
			osw.close();
			fout.close();
		}
		catch (Exception e)
		{
		}
	}
}
