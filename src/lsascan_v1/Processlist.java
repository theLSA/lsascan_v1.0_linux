package lsascan_v1;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Processlist {
	public void getprocesslist() {

		Process child = null;
		try {
			child = Runtime.getRuntime().exec("ps -aux");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   BufferedReader ins = new BufferedReader(new InputStreamReader(child.getInputStream()));
		         String c = null;
		         try {
					while ((c = ins.readLine()) != null) {
					  Lsascan_v1_frame.processlist.append(c+"\n");;
					 }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         try {
					ins.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
}
	
	public void killprocess() {
		String processbekilled = Lsascan_v1_frame.killprocesstext.getText();
		Runtime runtime = Runtime.getRuntime();  
		
        try {
			Process pk = runtime.exec(processbekilled);
		     
			BufferedReader strCon = new BufferedReader(new InputStreamReader(pk.getInputStream()));
			InputStream pe = pk.getErrorStream();
	         String line = null;  
	      int perr;
	      perr = pe.read();
	      System.out.print(perr);
	         
	         while((line=strCon.readLine())!=null)    
	         {
	        	 Lsascan_v1_frame.killprocessresult.setText(line+"\n");   
	        	 Lsascan_v1_frame.killprocessresult.setBackground(Color.green);
	        	 
	         } 
	         
	         if(perr!=-1) 
	         {
	        	 Lsascan_v1_frame.killprocessresult.setText("failed");    
	        	 Lsascan_v1_frame.killprocessresult.setBackground(Color.red);
	         } 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Lsascan_v1_frame.killprocessresult.setText("failed");    
       	    Lsascan_v1_frame.killprocessresult.setBackground(Color.red);
		}
        
}
	
	public void refreshprocesslist() {
		getprocesslist();
	}
	
}

