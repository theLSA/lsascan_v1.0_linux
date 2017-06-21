package lsascan_v1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Lsaportscan{
	
	public static InetAddress hostaddress;
	public static int timeout = 500;

	
	
	public static class Portrange implements Runnable{

		int maxport;
		int minport;
		int i;
		int threads;
	
		
		public Portrange(int minport,int maxport,int threads,int i) {
			this.minport = minport;
			this.maxport = maxport;
			this.threads = threads;
			this.i = i;
		}
		
		public void run() {
			int p = 0;
			for (p=minport+i;p<=maxport;p=p+threads) {
				Lsascan_v1_frame.stateResult.setText("scanning port:"+p);
				try {
					SocketAddress address = new InetSocketAddress(hostaddress,p);
					Socket socket = new Socket();
					
					socket.connect(address, timeout);
					socket.close();
					//System.out.println(p);
				
					Hpports hp = new Hpports();
					if(hp.getprocol(Integer.toString(p))!=null) {
						Lsascan_v1_frame.Result.append("Host: "+Lsascan_v1_frame.IPName.getText()+":"+p+" √--->  " + hp.getprocol(Integer.toString(p)) + "\n");
					}
					else {
						Lsascan_v1_frame.Result.append("Host: "+Lsascan_v1_frame.IPName.getText()+":"+p+" √\n");
					}
					
					
					
					
				}catch(IOException e){
					//e.printStackTrace();
				}
			}
		}
	}
	
	public static class Portset implements Runnable {
	
        public int[] ports; 
        public int threadNum, i, timeout; 
        
        public Portset(int[] ports, int threadNum, int i) {
            this.ports = ports;
            this.threadNum = threadNum;
            this.i = i;
        }
 
        public void run() {
            int port = 0;

			Socket socket;
               if (ports.length < 1)
                   return;
               for (port = 0 + i; port <= ports.length - 1; port += threadNum) {
            	   Lsascan_v1_frame.stateResult.setText("scanning port:"+ports[port]);
                   socket = new Socket();
                   SocketAddress address = new InetSocketAddress(hostaddress,ports[port]);
                   try {
                    
                       socket.connect(address, timeout);
                       socket.close();
                       Hpports hp = new Hpports();
   					if(hp.getprocol(Integer.toString(ports[port]))!=null) {
   						Lsascan_v1_frame.Result.append("Host: "+Lsascan_v1_frame.IPName.getText()+":"+ports[port]+" √--->  " + hp.getprocol(Integer.toString(ports[port])) + "\n");
   					}
   					else {
   						Lsascan_v1_frame.Result.append("Host: "+Lsascan_v1_frame.IPName.getText()+":"+ports[port]+" √\n");
   					}
            
                   } catch (IOException e) {
                        
                   }
                   
               }
            }
        }
	
}
