package lsascan_v1;

import java.util.HashMap;
import java.util.Map;

public class Hpports {
	
	Map hash = new HashMap();
	
	public Hpports() {
		hash.put("21", "ftp");
		hash.put("22", "ssh");
		hash.put("23", "telnet");
		hash.put("25", "smtp");
		hash.put("80", "http");
		hash.put("110", "pop3");
		hash.put("143", "imap4");
		hash.put("135", "rpc+dcom ☠");
		hash.put("139", "文件共享  ☠");
		hash.put("443", "https");
		hash.put("445", "smb ☠");
		hash.put("1433", "mssql");
		hash.put("2049", "nfs");
		hash.put("1863", "msn");
		hash.put("3306", "mysql");
		hash.put("3389", "Terminal Server");
		hash.put("53", "dns");
		hash.put("4000", "qq");
		hash.put("8000", "qq");
	
	}
	
	public Object getprocol(String portnumber) {
		return hash.get(portnumber);
	}
}
