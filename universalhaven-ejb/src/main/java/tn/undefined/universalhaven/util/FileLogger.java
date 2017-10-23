package tn.undefined.universalhaven.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger {
	private String file ="D:\\blacklist.log";
	
	public void log(String ipAddress,String endpoint) {
		
	    BufferedWriter writer;
		try {
			  writer = new BufferedWriter(new FileWriter(file,true));
			  writer.append(System.lineSeparator());
			  String str="[SEVERE : "+new Date()+" ] : Blacklisted ip address "+ipAddress+""
			  		+ " while accessing endpoint : "+endpoint;
			  writer.append(str);
			     
			  writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	}
	
	
}
