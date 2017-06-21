package lsascan_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sniffhost {
	 public String getReturnData(String nmapDir,String cmd){
	        Process process = null;
	        StringBuffer stringBuffer = new StringBuffer();
	        try {
	            process = Runtime.getRuntime().exec(nmapDir + " " + cmd);
	            Lsascan_v1_frame.sniffresult.append("sniffing......\n");
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));
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
