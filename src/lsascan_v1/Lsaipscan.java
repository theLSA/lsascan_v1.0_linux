package lsascan_v1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

import lsascan_v1.Getmac.Arp_a;

public class Lsaipscan {
	public static InetAddress ipaddress;
	public static int icmptimeout = 5000;
	
	public static String themac = null;
	public static String themacc = null;
	public static final ReentrantLock lock = new ReentrantLock();
	
	public static int pause = 0;
	public static int running = 1;
	public static int stop = -1;
	public static int status = 1;
	
	public static class Scansingleip {
		public void thisscansingleip() throws Exception {
			
			try {
				
				if(ipaddress.isReachable(icmptimeout)) {
					Lsascan_v1_frame.iparea.append(ipaddress.getHostName()+"/"+ipaddress.getHostAddress()+"\n");
					if(Lsascan_v1_frame.jb1.isSelected()) {
						Getmac gm = new Getmac();
						themac = gm.nbtstat_a(ipaddress.getHostAddress());
						//System.out.println("hi"+themac);
						
						if(themac==null) {
							Arp_a arpa = new Arp_a();
							themacc = arpa.getMacAddress(ipaddress.getHostAddress());
							//System.out.println("hi"+themacc);
							if(themacc==null) {
								Lsascan_v1_frame.iparea.append("can not get mac");
							}
							else {
								Lsascan_v1_frame.iparea.append(themacc);
							}
							
						}
						else {
							Lsascan_v1_frame.iparea.append(themac);
						}
					}
				}
				
				//System.out.println("hi"+themac);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static class Scaniprange1 implements Runnable {
		
	
		public static String nowip = null;
		public static int nowipnum = 0;
		
		public static Queue<String> allip = new LinkedList<String>();
		InetAddress nowipaddress;
		
		public Scaniprange1() {

		}
		
		
		
		public String getIp() {
			   String theip = null;
	           synchronized (allip) {
	                theip = allip.poll();
	            }
	            
	            return theip;
		}
		
	
		
		
		
		public void run() {
			// TODO Auto-generated method stub
			
			while ((nowip = getIp()) != null) {
						  
                  
				try {
					nowipaddress = InetAddress.getByName(nowip);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
                  try {
					if(nowipaddress.isReachable(icmptimeout)) {
					
						lock.lock();
						Lsascan_v1_frame.iparea.append("Host ["+nowipaddress.getHostAddress()+"] is reachable!\n");
						if(Lsascan_v1_frame.jb1.isSelected()) {
							Getmac gm = new Getmac();
							try {
								themac = gm.nbtstat_a(nowipaddress.getHostAddress());
							}catch(NullPointerException e) {
								e.printStackTrace();
							}
							//System.out.println("hi"+themac);
							if(themac==null) {
								Arp_a arpa = new Arp_a();
								try {
									themacc = arpa.getMacAddress(nowipaddress.getHostAddress());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//System.out.println("hi"+themacc);
								if(themacc==null) {
									Lsascan_v1_frame.iparea.append("can not get mac\n");
								}
								else {
									Lsascan_v1_frame.iparea.append(themacc+"\n");
								}
								
							}
							else {
								Lsascan_v1_frame.iparea.append(themac+"\n");
							}
						
					}

						lock.unlock();	
					}
  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
				}
                  nowipnum++;
                  Lsascan_v1_frame.ipscanstatus2.setText(nowipnum+"");
                
                  //System.out.println(nowipnum);
              
			    }

		}
	}
		

	
	public static class Scaniprange2 implements Runnable {
		
		static Queue<String> allipp = new LinkedList<String>();
		
		public static String nowipp = null;
		
		public static int nowippnum = 0;
		
		InetAddress nowipaddress2;
		
		public Scaniprange2() {
			
		}
		
		public String getIp2() {
			   String theip2 = null;
	           synchronized (allipp) {
	                theip2 = allipp.poll();
	            }
	            
	            return theip2;
		}

		public void run() {
			// TODO Auto-generated method stub
			 while ((nowipp = getIp2()) != null) {
				 try {
					nowipaddress2 = InetAddress.getByName(nowipp);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 try {
					if(nowipaddress2.isReachable(icmptimeout)) {
						lock.lock();
						Lsascan_v1_frame.iparea.append("Host ["+nowipaddress2.getHostAddress()+"] is reachable!\n");
						if(Lsascan_v1_frame.jb1.isSelected()) {
							Getmac gm = new Getmac();
							try {
								themac = gm.nbtstat_a(nowipaddress2.getHostAddress());
							}catch(NullPointerException e) {
								e.printStackTrace();
							}
							//System.out.println("hi"+themac);
							if(themac==null) {
								Arp_a arpa = new Arp_a();
								try {
									themacc = arpa.getMacAddress(nowipaddress2.getHostAddress());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//System.out.println("hi"+themacc);
								if(themacc==null) {
									Lsascan_v1_frame.iparea.append("\ncan not get mac\n");
								}
								else {
									Lsascan_v1_frame.iparea.append("\n"+themacc+"\n");
								}
								
							}
							else {
								Lsascan_v1_frame.iparea.append("\n"+themac+"\n");
							}
						
					}

						lock.unlock();	
						}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 nowippnum++;
                 Lsascan_v1_frame.ipscanstatus2.setText(nowippnum+"");
				 
			 }
		}
		
	}

}
