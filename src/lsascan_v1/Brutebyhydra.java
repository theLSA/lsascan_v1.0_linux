package lsascan_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Brutebyhydra {
	 public String getReturnData(String hydraDir,String cmd){
	        Process processh = null;
	        StringBuffer stringBuffer = new StringBuffer();
	        try {
	            processh = Runtime.getRuntime().exec(hydraDir + " " + cmd);
	            Lsascan_v1_frame.bruteresult.append("bruting......\n");
	            BufferedReader reader = new BufferedReader(new InputStreamReader(processh.getInputStream(),"UTF-8"));
	            String line = null;         
	            while((line = reader.readLine()) != null){
	                stringBuffer.append(line + "\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return stringBuffer.toString();
	    }
}
