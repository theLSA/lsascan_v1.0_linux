package lsascan_v1;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Properties;

public class Sysinfo {
	
	public void getinfo() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			Lsascan_v1_frame.sys1.setText(address.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
	    String osUser=System.getProperty("user.name");  
	    Lsascan_v1_frame.sys2.setText(osUser);
	    
	    Properties props=System.getProperties();    
	    String osName = props.getProperty("os.name");    
	    String osArch = props.getProperty("os.arch");   
	    String osVersion = props.getProperty("os.version");   
	    
	    Lsascan_v1_frame.sys3.setText(osName);
	    Lsascan_v1_frame.sys4.setText(osArch);
	    Lsascan_v1_frame.sys5.setText(osVersion);
	    
	    String userhome = props.getProperty("user.home");
	    String userdir = props.getProperty("user.dir");
	    
	    Lsascan_v1_frame.sys6.setText(userhome);
	    Lsascan_v1_frame.sys7.setText(userdir);
	    
	    String javaver = props.getProperty("java.version");
	    Lsascan_v1_frame.sys8.setText(javaver);
	    
	    
	}

}
