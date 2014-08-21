package com.privacyfix.core.web;

import java.io.IOException;

import org.sikuli.basics.Debug;
import org.sikuli.script.FindFailed;
//import org.sikuli.script.Key;



import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;



 




public class Sikuli {

public  void clickOnImage(String path, float similarity,int timeout) throws FindFailed{
	Screen s = new Screen();
	

	

	Pattern add = new Pattern(path);
	if (s.exists(add.similar(similarity),timeout)!=null){
		s.click(add,0);
	}
	
	//Match match = s.find(add);
	//s.click(match);
}

public  void clickOnImage(String path) throws FindFailed{
	Screen s = new Screen();
	
	int timeout=5;
	double similarity= .8;
	

	Pattern add = new Pattern(path);
	if (s.exists(add.similar((float) similarity),timeout)!=null){
		s.click(add,0);
	}
	
	//Match match = s.find(add);
	//s.click(match);
}


public void verifyImage(String path) throws IOException {
	Debug.setDebugLevel(3);
	Screen s = new Screen();
	

	

	Pattern add = new Pattern(path);

	try {
		s.find(add).highlight(2);
	} catch (FindFailed e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}

public void captureImage (String path)throws IOException {
   // Debug.setDebugLevel(3);
    Screen s = new Screen();
	try {
	s.find(s.userCapture().getImage());
	//System.out.println (s);
	} catch (FindFailed e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

