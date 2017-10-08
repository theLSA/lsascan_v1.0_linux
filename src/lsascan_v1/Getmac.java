package lsascan_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Getmac {
	
	public static class Arp_a {
		  public static String command(String cmd) throws Exception{
		        Process process = Runtime.getRuntime().exec(cmd);
		        process.waitFor();
		        InputStream in = process.getInputStream();
		        StringBuilder result = new StringBuilder();
		        byte[] data = new byte[256];
		        while(in.read(data) != -1){
		            String encoding = System.getProperty("sun.jnu.encoding");
		            result.append(new String(data,encoding));
		        }
		        return result.toString();
		    }
		  
		  public static String getMacAddress(String ip) throws Exception{
		  
			    String pingresult = command("ping "+ip+" -c 2");
			    //System.out.println(pingresult);
		        String result = command("/usr/sbin/arp -a "+ip);
		   
		        String regExp = "([0-9A-Fa-f]{2})([-:][0-9A-Fa-f]{2}){5}";
		        Pattern pattern = Pattern.compile(regExp);
		        Matcher matcher = pattern.matcher(result);
		        String mac = null;
		        while (matcher.find()) {
		            mac = matcher.group();
		            
		        }
		        return mac;
		    }
	}
	
	public String nbtstat_a(String ip) {
		  Arp_a arpcomm = new Arp_a();
		  try {
			String pingresult2 = arpcomm.command("ping "+ip+" -c 2");
			//System.out.println(pingresult2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  String str;
		  String macAddress = null;
		  try {
		   Process p = Runtime.getRuntime().exec("netstat -A " + ip);
		   //System.out.print(ip);
		   InputStreamReader ir = new InputStreamReader(p.getInputStream());
		   //BufferedReader ir = new BufferedReader(new InputStreamReader(p.getInputStream()));
		   String line = null;
		   

		  
	       LineNumberReader input = new LineNumberReader(ir);
		   
		   
		   for (int i = 1; i < 100; i++) {
			    str = input.readLine();
			    if (str != null) {			     
			    	if (str.contains("at ")) {
			      macAddress = str.substring(str.indexOf(" at ")+2, str.length());
			      break;
			     }
			    }
			   }
		   
		   ir.close();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  return macAddress;
		 }

	
	}

