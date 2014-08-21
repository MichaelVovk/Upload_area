package com.privacyfix.core;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

	private static BufferedWriter bw;




	private static File createLog() throws Exception {


        File f = new File("Logs/source.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html>");
        bw.write("<body>");
        bw.write("<h1>Log File</h1>");
        bw.write("<textarea cols=75 rows=30>");

        bw.write("</text" + "area>");
        bw.write("</body>");
        bw.write("</html>");
        bw.close();

        Desktop.getDesktop().browse(f.toURI());
        return f;
    }
	
	
	
	
	public static void writeLog(String logMessage) throws Exception {

		File f = createLog();
		//bw = new BufferedWriter(new FileWriter(f));
     //   bw.write(logMessage);
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f, true)))) {
		    out.println("logMessage");
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
        System.out.println(f);
     //   Desktop.getDesktop().browse(f.toURI());
    }
	

}
	
