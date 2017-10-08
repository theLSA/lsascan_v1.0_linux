package lsascan_v1;

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import lsascan_v1.Lsaipscan.Scaniprange1;
import lsascan_v1.Lsaipscan.Scaniprange2;
import lsascan_v1.Lsaipscan.Scansingleip;
import lsascan_v1.Lsaportscan.Portrange;
import lsascan_v1.Lsaportscan.Portset;
import lsascan_v1.Sniffhost;

public class Lsascan_v1_frame implements ActionListener{
	
	//GUI-------------------------------------------------------------------------------------------
	
	//global component---------------------------------------
	public static JFrame mainframe = new JFrame();
	public static JTabbedPane jtp = new JTabbedPane();
	
	public static JPanel ippanel = new JPanel();  
	public static JPanel portpanel = new JPanel();  
	public static JPanel sniffhostpanel = new JPanel();
	public static JPanel getprocesslistpanel = new JPanel();
	public static JPanel brutepanel = new JPanel();
	public static JPanel sysinfopanel = new JPanel();
	//------------------------------------------------------
	
	//port panel component----------------------------------------------------------------------------
	public static Label labelIp = new Label("IP/HOSTNAME:");

    public static Label labelportrange = new Label("------");
    public static JTextField StartPost = new JTextField("1");
    public static JTextField EndPost = new JTextField("1000");
    
   
    public static JTextField textportlist = new JTextField("21,22,23,25,53,80,110,135,443,1433,3306,3389,8080"); 
    
    public static ButtonGroup buttonGroup = new ButtonGroup();
    public static JRadioButton scanrange = new JRadioButton("port range");
    public static JRadioButton scanset = new JRadioButton("port set");
    
    
    
    public static Label labelThreadNum = new Label("thread nums:");
    public static Label State = new Label("scan state:");
    public static Label stateResult = new Label("waitting......");
    public static JTextField IPName = new JTextField("127.0.0.1");
    public static JTextField TheardNum = new JTextField("10");
    public static TextArea Result = new TextArea();
    public static Label DLGINFO = new Label("");
    public static JButton Start = new JButton("SCAN");
    public static JButton Exit = new JButton("EXIT");
    public static JDialog errorDLG=new JDialog(mainframe,"error:");
    public static JButton okBTN=new JButton("ok");
    
    public static JButton portexport = new JButton("Export");
    //---------------------------------------------------------------------------------------------------------
    
    //ip panel component-----------------------------------------------------------
    
    public static JTextField singleip = new JTextField("127.0.0.1");   //single ip scan
    
    public static JTextField mstartip = new JTextField();   //range ip scan according to user
    public static JTextField mendip = new JTextField();
    public static Label mip = new Label("-------");
    
    public static JTextField ipordomain = new JTextField("www.lsawebtest.top");   //range ip scan according to a ip or domain
    public static Label domaintoip = new Label("----------------->");
    public static JTextField domainip = new JTextField();
    public static JTextField startip = new JTextField();
    public static JTextField endip = new JTextField();
    
    public static TextArea iparea = new TextArea();
    
    public static ButtonGroup ipbuttonGroup = new ButtonGroup();
    public static JRadioButton scansingleip = new JRadioButton("single ip or domain");
    public static JRadioButton scaniprange1 = new JRadioButton("ip range");
    public static JRadioButton scaniprange2 = new JRadioButton("c range");
    
    public static JButton getip = new JButton("getip");
    public static JButton Startipscan = new JButton("SCANIP");
    
    public static Label ipthreadslabel = new Label("threads");
    public static JTextField ipthreads = new JTextField("10");
    
    public static JCheckBox jb1 = new JCheckBox("getmac");
    
//    public static JButton ippause = new JButton("PAUSE");
//    public static JButton ipstop = new JButton("STOP");
//    public static JButton ipweakup = new JButton("weakup");
    
    
    public static JButton exportr = new JButton("export");
    //public static CheckboxGroup cg = new CheckboxGroup();
    
    public static Label ipscanstatus = new Label("scanned: ");
    public static Label ipscanstatus2 = new Label("0");
    
    //sniffhost panel component-------------------------------------------------------
    public static JTextField sniffhost = new JTextField("-O 127.0.0.1");
    public static JButton sniff = new JButton("sniff");
    public static TextArea sniffresult = new TextArea();
    //-------------------------------------------------------------------
    
    //processlist panel--------------------------------------------------------
    public static JButton getprocesslist = new JButton("get processlist");
    public static TextArea processlist = new TextArea();
    public static Label killprocesslabel = new Label("kill process--->");
    public static JTextField killprocesstext = new JTextField();
    public static JButton killprocess = new JButton("send");
    public static Label killprocessresult = new Label("------------------------kill result--------------------------");
    public static JButton refreshprocess = new JButton("refresh");
    //------------------------------------------------------------------
    
    
    //brute panel--------------------------------------------------
    public static JTextField brutetext = new JTextField("-h");
    public static TextArea bruteresult = new TextArea();
    public static JButton brutebutton = new JButton("brure");
    //--------------------------------------------------------
    
    //sysinfo panel------------------------------------------
    public static Label sys1 = new Label();
    public static Label sys2 = new Label();
    public static Label sys3 = new Label();
    public static Label sys4 = new Label();
    public static Label sys5 = new Label();
    public static Label sys6 = new Label();
    public static Label sys7 = new Label();
    public static Label sys8 = new Label();
    public static Label sys9 = new Label();
    public static Label sys10 = new Label();
    public static Label sys11 = new Label();
    public static Label sys12 = new Label();
    public static Label sys13 = new Label();
    public static Label sys14 = new Label();
    public static Label sys15 = new Label();
    public static Label sys16 = new Label();
    public static Label sys17 = new Label();
    public static Label sys18 = new Label();
    
    
    //-------------------------------------------------------
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    //-----------------------------------------------------------------------------
    public Lsascan_v1_frame() {
		init();
		ipscaninit();
		portscaninit();
		sniffhostinit();
		getprocesslistinit();
		bruteinit();
		sysinfoinit();
		
	}
    
    private void init(){
    	mainframe.add(jtp);
		mainframe.setResizable(true);
		mainframe.setVisible(true);
		mainframe.setSize(800, 600);
		mainframe.setTitle("lsascan_v1.0");
		mainframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainframe.setBackground(Color.cyan);
		mainframe.setLocationRelativeTo(null);
		mainframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    System.exit(0);
            }
    });
		
    }
    
	
    
    private void portscaninit(){
    	
    	portpanel.setLayout(null);
    	jtp.add(portpanel,"portscan");
    	
    	buttonGroup.add(scanrange);
    	buttonGroup.add(scanset);
    	scanset.setSelected(true);
    	
    	
    	
    	Container error=errorDLG.getContentPane();
		error.setLayout(null);
        error.add(DLGINFO);
        error.add(okBTN);
        
        okBTN.setActionCommand("ok");
        okBTN.addActionListener(this);
        
        portpanel.add(scanrange);
        portpanel.add(scanset);
        
        
        
        portpanel.add(Start); 
        portpanel.add(Exit); 
        
        portpanel.add(labelIp);
        portpanel.add(IPName);
  
        portpanel.add(labelportrange);
        portpanel.add(StartPost);
        portpanel.add(EndPost);
        
  
        portpanel.add(textportlist);
        
        portpanel.add(labelThreadNum);
        portpanel.add(TheardNum);
        portpanel.add(Result);
        portpanel.add(State);
        portpanel.add(stateResult);
        
        portpanel.add(portexport);
        
        scanrange.setBounds(190, 10, 110, 30);
        scanset.setBounds(190, 50, 90, 20);
        
        Start.setBounds(660, 12, 100, 50);
        Start.setBackground(Color.green);
        Start.setFont(new Font("楷体", Font.PLAIN, 32));
        Start.setForeground(Color.blue);
        //Start.setBorder(new BevelBorder(BevelBorder.RAISED));
        Start.setActionCommand("Start");
        Start.addActionListener(this);
        
        
        Exit.setBounds(670, 480, 80, 40);
        Exit.setActionCommand("Exit");
        Exit.addActionListener(this);
       
       
        labelIp.setBounds(0, 40, 90, 25);
        IPName.setBounds(95, 37, 90, 25);
        IPName.setHorizontalAlignment(JTextField.CENTER);
      
        StartPost.setBounds(300,15,50,20);
        StartPost.setHorizontalAlignment(JTextField.CENTER);
        
        labelportrange.setBounds(349, 18, 10, 10);
       
        EndPost.setBounds(360,15,50,20);
        EndPost.setHorizontalAlignment(JTextField.CENTER);
        
        textportlist.setBounds(280,50,250,20);
        
        labelThreadNum.setBounds(550, 8, 80, 20);
        labelThreadNum.setBackground(Color.yellow);
        TheardNum.setBounds(560,30,45,25);
        TheardNum.setHorizontalAlignment(JTextField.CENTER);
       
        Result.setBounds(1,100,650,380);
        Result.setEditable(false);
        Result.setBackground(Color.BLACK);
        Result.setFont(new Font("楷体",Font.PLAIN,20));
        Result.setForeground(Color.GREEN);
       
        State.setBounds(5, 480, 70, 30);
        stateResult.setBounds(80, 480, 130, 30);
        
        portexport.setBounds(680, 100, 80, 30);
        portexport.setActionCommand("Export");
        portexport.addActionListener(this);
    	
    }
    //--------------------------------------------------------------------------------------

    
    private void ipscaninit(){
    	
    	ippanel.setLayout(null);
    	jtp.add(ippanel,"ipscan");
    	
    	ipbuttonGroup.add(scansingleip);
    	ipbuttonGroup.add(scaniprange1);
    	ipbuttonGroup.add(scaniprange2);
    	
    	
      
    	scansingleip.setSelected(true);
        
    	ippanel.add(scansingleip);
    	ippanel.add(scaniprange1);
    	ippanel.add(scaniprange2);
    	ippanel.add(singleip);

    	ippanel.add(mip);
    	ippanel.add(mstartip);
    	ippanel.add(mendip);
    	ippanel.add(ipordomain);
    	ippanel.add(domaintoip);
    	ippanel.add(domainip);
    	ippanel.add(startip);
    	ippanel.add(endip);
    	ippanel.add(iparea);
    	ippanel.add(getip);
    	ippanel.add(Startipscan);
    	
    	ippanel.add(ipthreadslabel);
    	ippanel.add(ipthreads);
    	
    	ippanel.add(jb1);
    	
    	ippanel.add(exportr);
    	
    	//ippanel.add(ippause);
    	//ippanel.add(ipstop);
    	
    	ippanel.add(ipscanstatus);
    	ippanel.add(ipscanstatus2);
    	
    	ipscanstatus.setBounds(620, 480, 60, 20);
    	ipscanstatus.setBackground(Color.green);
    	ipscanstatus2.setBounds(680, 480, 80, 20);
    	ipscanstatus2.setBackground(Color.green);
    	
    	
    	scansingleip.setBounds(5,5,160,20);
    	scaniprange1.setBounds(5,30,90,50);
    	scaniprange2.setBounds(5,70,100,50);
    	
    	singleip.setBounds(170,5,150,20);
    	singleip.setHorizontalAlignment(JTextField.CENTER);
    	
    	mstartip.setBounds(100,42,130,20);
    	mstartip.setHorizontalAlignment(JTextField.CENTER);
    	mip.setBounds(229, 42, 30, 20);
    	mendip.setBounds(260,42,130,20);
    	mendip.setHorizontalAlignment(JTextField.CENTER);
    	
    	ipordomain.setBounds(110,85,130,20);
    	ipordomain.setHorizontalAlignment(JTextField.CENTER);
    	
    	getip.setBounds(250, 70, 75, 20);
    	getip.addActionListener(this);
    	
    	domaintoip.setBounds(250, 90, 80, 10);
    	domainip.setBounds(330,85,130,20);
    	domainip.setHorizontalAlignment(JTextField.CENTER);
    	domainip.setEditable(false);
    	
    	startip.setBounds(470, 70, 130, 20);
    	startip.setHorizontalAlignment(JTextField.CENTER);
    	startip.setEditable(false);
    	endip.setBounds(470, 90, 130, 20);
    	endip.setHorizontalAlignment(JTextField.CENTER);
    	endip.setEditable(false);
    	
    	iparea.setBounds(5, 120, 600, 400);
    	iparea.setEditable(false);
    	iparea.setBackground(Color.black);
    	iparea.setForeground(Color.green);
    	iparea.setFont(new Font("楷体",Font.PLAIN,20));
    	
    	Startipscan.setBounds(550, 10, 200, 50);
    	Startipscan.setFont(new Font("楷体", Font.PLAIN, 50));
    	Startipscan.setForeground(Color.blue);
    	Startipscan.addActionListener(this);
    	
    	ipthreadslabel.setBounds(450, 5, 50, 20);
    	ipthreads.setBounds(450,30,35,20);
    	ipthreads.setHorizontalAlignment(JTextField.CENTER);
    	
    	jb1.setBounds(620, 120, 100, 20);
    	
//    	ippause.setBounds(650, 80, 100, 30);
//    	
//    	ipstop.setBounds(650, 450, 100, 50);
    	
    	exportr.setBounds(620, 150, 80, 30);
    	exportr.setActionCommand("export");
    	exportr.addActionListener(this);
    	
    }
    
    
    public void sniffhostinit() {
    	sniffhostpanel.setLayout(null);
    	jtp.add(sniffhostpanel,"sniff");
    	
    	sniffhostpanel.add(sniffhost);
    	sniffhostpanel.add(sniffresult);
    	sniffhostpanel.add(sniff);
    	
    	sniffhost.setBounds(250, 20, 300, 20);
    	
    	sniffresult.setBounds(10, 90, 750, 400);
    	sniffresult.setBackground(Color.black);
    	sniffresult.setForeground(Color.green);
    	sniffresult.setFont(new Font("楷体",Font.PLAIN,20));
    	
    	
    	sniff.setBounds(150, 50, 500, 30);
    	
    	sniff.setActionCommand("sniff");
        sniff.addActionListener(this);

    }
    
    public void getprocesslistinit() {
    	getprocesslistpanel.setLayout(null);
    	jtp.add(getprocesslistpanel, "processlist");
    	
    	getprocesslistpanel.add(getprocesslist);
    	getprocesslistpanel.add(processlist);
    	getprocesslistpanel.add(killprocess);
    	getprocesslistpanel.add(killprocesslabel);
    	getprocesslistpanel.add(killprocesstext);
    	getprocesslistpanel.add(refreshprocess);
    	getprocesslistpanel.add(killprocessresult);
    	
    	getprocesslist.setBounds(20, 10, 500, 30);
    	
        processlist.setBounds(5, 60, 700, 400);
        
        killprocesslabel.setBounds(10, 470, 90, 20);
        
        killprocesstext.setBounds(100, 470, 200, 20);
        
        killprocess.setBounds(300, 460, 80, 30);
        
        killprocessresult.setBounds(80, 500, 250, 25);
        killprocessresult.setBackground(Color.cyan);
       
        refreshprocess.setBounds(600, 5, 90, 50);
        
        getprocesslist.setActionCommand("get processlist");
        getprocesslist.addActionListener(this);
        
        killprocess.setActionCommand("send");
        killprocess.addActionListener(this);
        
        refreshprocess.setActionCommand("refresh");
        refreshprocess.addActionListener(this);
    	
    }
    
    public void bruteinit() {
    	brutepanel.setLayout(null);
    	jtp.add(brutepanel, "brute");
    	
    	brutepanel.add(brutetext);
    	brutepanel.add(brutebutton);
    	brutepanel.add(bruteresult);
    	
    	brutetext.setBounds(200, 10, 300, 30);
    	
    	brutebutton.setBounds(520, 10, 80, 30);
    	brutebutton.addActionListener(this);
    	brutebutton.setActionCommand("brute");
    	
    	bruteresult.setBounds(5, 50, 750, 450);
    }
    
    
    public void sysinfoinit() {
    	sysinfopanel.setLayout(null);
    	jtp.add(sysinfopanel, "sysinfo");
    	
    	sysinfopanel.add(sys1);
    	sys1.setBounds(5, 5, 100, 20);
    	sys1.setBackground(Color.green);
    	
    	sysinfopanel.add(sys2);
    	sys2.setBounds(110, 5, 100, 20);
    	sys2.setBackground(Color.green);
    	
    	sysinfopanel.add(sys3);
    	sys3.setBounds(250, 5, 100, 20);
    	sys3.setBackground(Color.green);
    	sysinfopanel.add(sys4);
    	sys4.setBounds(400, 5, 100, 20);
    	sys4.setBackground(Color.green);
    	sysinfopanel.add(sys5);
    	sys5.setBounds(550, 5, 150, 20);
    	sys5.setBackground(Color.green);
    	
    	sysinfopanel.add(sys6);
    	sys6.setBounds(20, 40, 300, 20);
    	sys6.setBackground(Color.lightGray);
    	sysinfopanel.add(sys7);
    	sys7.setBounds(20, 70, 300, 20);
    	sys7.setBackground(Color.cyan);
    	
    	sysinfopanel.add(sys8);
    	sys8.setBounds(20, 100, 100, 20);
    	sys8.setBackground(Color.CYAN);
    	
    	Sysinfo sysinfo = new Sysinfo();
    	sysinfo.getinfo();
    }
    
    private Scansingleipthread scansingleipthread = null;
    private Scaniprange1thread scaniprange1thread = null; 
    private Scaniprange2thread scaniprange2thread = null; 
    private Scanportrangethread scanportrangethread = null;
    private Scanportsetthread scanportsetthread = null;
    private Sniffthread sniffthread = null;
    private Brutethread brutethread = null;
    
    class Scansingleipthread extends Thread{  
        public void run() {  
          //do something...  
         scansingleipfun();  
       }  
  } 
    public void scansingleipfun() {
    	try {
			
			Lsaipscan.ipaddress = InetAddress.getByName(singleip.getText().trim());
			 
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Scansingleip scansingleipinstance = new Scansingleip();
		try {
			scansingleipinstance.thisscansingleip();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    }
    
    class Brutethread extends Thread{  
        public void run() {  
          //do something...  
         brutefun();  
       }  
  } 
    
    public void brutefun() {
    	Brutebyhydra bh = new Brutebyhydra();
		String cmdh = Lsascan_v1_frame.brutetext.getText();
		String bresult = bh.getReturnData("/usr/bin/hydra", cmdh);
		Lsascan_v1_frame.bruteresult.append(bresult);
    }
    
    
    class Sniffthread extends Thread{  
        public void run() {  
          //do something...  
         snifffun();  
       }  
  } 
    
    public void snifffun() {
    	Sniffhost sh = new Sniffhost();
    	String cmd = sniffhost.getText();
    	String rd = sh.getReturnData("/usr/bin/nmap", cmd);
    	sniffresult.setText(rd);
    }
    
    class Scanportsetthread extends Thread{  
        public void run() {  
          //do something...  
         scanportsetfun();  
       }  
  } 
    
    public void scanportsetfun() {
    	 int threadNum;
         threadNum=Integer.parseInt(TheardNum.getText().trim());
         String[] portsString = textportlist.getText().split(",");
         
         int[] ports = new int[portsString.length];
         
         for(int ii=0;ii<portsString.length;ii++)
             ports[ii] = Integer.parseInt(portsString[ii].trim());
         
         Result.append("scanning"+" "+Lsaportscan.hostaddress+"'s port set!"+"\n");
         ExecutorService threadPool = Executors.newCachedThreadPool();
         for (int i = 0; i < threadNum; i++) {
         	 Portset portset = new Portset(ports,threadNum,i);
          	 threadPool.execute(portset);
         }
         
         threadPool.shutdown();
         
         while (true) {
          	
             if (threadPool.isTerminated()) {
                 //System.out.println("OVER!!!!!!!!!!!!!!!");
                 Result.append("-------------------------Over!!!----------------------\n\n");
                 stateResult.setText("OVER !");  
                 break;
             }
         
             try {
                		Thread.sleep(1000);
             } catch (InterruptedException e1) {
         }  
     }
 }
  
    
    class Scanportrangethread extends Thread{  
        public void run() {  
          //do something...  
         scanportrangefun();  
       }  
  } 
    public void scanportrangefun() {
    	int threadNum;
        threadNum=Integer.parseInt(TheardNum.getText().trim());
    	int startPort;
        int endPort;
        
        try {
                 startPort=Integer.parseInt(StartPost.getText());
                 endPort=Integer.parseInt(EndPost.getText());
                 
        } catch (NumberFormatException e1) {
                errorDLG.setBounds(300, 280, 150, 110);
                DLGINFO.setText("error port number or thread nums");
                DLGINFO.setBounds(15, 20, 250, 20);
                okBTN.setBounds(110, 50, 60, 30);
                DLGINFO.setVisible(true);
                return ;
        }
        
        if (( startPort<0)||( endPort>65535)||( startPort> endPort)) {
                errorDLG.setBounds(300, 280, 150, 110);
                DLGINFO.setText("error port number");
                DLGINFO.setBounds(15, 20, 250, 20);
                okBTN.setBounds(110, 50, 60, 30);
                DLGINFO.setVisible(true);
                return ;
        }
        
        if (( threadNum<0)||( threadNum>200)) {
                errorDLG.setBounds(300, 280, 150, 110);
                DLGINFO.setText("thread nums must less than 200");
                DLGINFO.setBounds(15, 20, 250, 20);
                okBTN.setBounds(110, 50, 60, 30);
                DLGINFO.setVisible(true);
                return ;
        }
        Result.append("scanning"+" "+Lsaportscan.hostaddress+"'s ports!");
        Result.append("strat_port: "+ startPort+" end_port:"+ endPort+"\n\n");
    	 ExecutorService threadPool = Executors.newCachedThreadPool();
         for (int i=0; i<threadNum; i++) {
         	 Portrange portrange = new Portrange(startPort,endPort,threadNum,i);
         	 threadPool.execute(portrange);
         }
         
         threadPool.shutdown();
         
         while (true) {
         	
             if (threadPool.isTerminated()) {
                 //System.out.println("OVER!!!!!!!!!!!!!!!");
                 Result.append("Over!!!\n\n");
                 stateResult.setText("-------------------------Over!!!-----------------------\n\n");  
                 break;
             }
         
             try {
                		Thread.sleep(1000);
             } catch (InterruptedException e1) {
         }  
     }
    }
    
    class Scaniprange1thread extends Thread{  
        public void run() {  
          //do something...  
         scaniprange1fun();  
       }  
  } 
    
    public void scaniprange1fun() {
    	int ipthreadnums = Integer.parseInt(ipthreads.getText().trim());
    	String mstartip,mendip;
		int ipbegin0,ipbegin1,ipbegin2,ipbegin3;
		int ipend0,ipend1,ipend2,ipend3;
		int ipnums = 1;
		Queue<String> allip = new LinkedList<String>();
	
		
		
		mstartip = Lsascan_v1_frame.mstartip.getText().trim();
		mendip = Lsascan_v1_frame.mendip.getText().trim();
		ipbegin0 = Integer.parseInt(mstartip.split("\\.")[0]);
		ipbegin1 = Integer.parseInt(mstartip.split("\\.")[1]);
		ipbegin2 = Integer.parseInt(mstartip.split("\\.")[2]);
		ipbegin3 = Integer.parseInt(mstartip.split("\\.")[3]);
		
		ipend0 = Integer.parseInt(mendip.split("\\.")[0]);
		ipend1 = Integer.parseInt(mendip.split("\\.")[1]);
		ipend2 = Integer.parseInt(mendip.split("\\.")[2]);
		ipend3 = Integer.parseInt(mendip.split("\\.")[3]);
		
		allip.offer(mstartip);
		//System.out.println(mstartip);
		
		while(true)
	    {
			
	        if (ipbegin3!=ipend3)
	        {
	            ipbegin3 += 1;
	        }
	        else if(ipbegin2!=ipend2)
	        {
	            ipbegin3 = 0;
	            ipbegin2 += 1;
	        }
	        else if(ipbegin1!=ipend1)
	        {
	            ipbegin3 = 0;
	            ipbegin2 = 0;
	            ipbegin1 += 1;
	        }
	        else if(ipbegin0!=ipend0)
	        {
	            ipbegin3 = 0;
	            ipbegin2 = 0;
	            ipbegin1 = 0;
	            ipbegin0 += 1;
	        }
	        else
	        {
	            //System.out.println("end");  
	            break;
	        }
	        
	        allip.offer(ipbegin0+"."+ipbegin1+"."+ipbegin2+"."+ipbegin3);
	        //System.out.println(ipbegin0+"."+ipbegin1+"."+ipbegin2+"."+ipbegin3);
	        ipnums++;
	    }
		
		iparea.append("-----------------Total ip nums:"+ipnums+"-------------------\n");
		
		   Lsaipscan.Scaniprange1.allip = allip;
		   ExecutorService executor = Executors.newFixedThreadPool(ipthreadnums);
		   for (int i = 0; i < ipthreadnums; i++) {
			   Scaniprange1 scaniprange1 = new Scaniprange1();
	           executor.execute(scaniprange1);
	        }
		   executor.shutdown();
	        try {
	            while (!executor.isTerminated()) {
	                Thread.sleep(1000);
	            }
	        } catch (Exception e2) {
	            //e2.printStackTrace();
	        }
    }
    
    
    
    
    class Scaniprange2thread extends Thread{  
    	          public void run() {  
    	            //do something...  
    	           scaniprange2fun();  
    	         }  
            }  
    
    public void scaniprange2fun() {
    	int ipthreadnums = Integer.parseInt(ipthreads.getText().trim());
    	String striii;
		//System.out.println(tip2);
		InetAddress tipordomain2 = null;
		//System.out.println(ipordomain.getText().trim());
		try {
			tipordomain2 = InetAddress.getByName(ipordomain.getText().trim());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		String tip2 = tipordomain2.getHostAddress();
		//System.out.print(tip2);
		for(int iii=1;iii<=254;iii++) {
			striii = Integer.toString(iii);
			Lsaipscan.Scaniprange2.allipp.offer(tip2.split("\\.")[0]+"."+tip2.split("\\.")[1]+"."+tip2.split("\\.")[2]+"."+striii);
			//System.out.print(tip2.split("\\.")[0]+tip2.split("\\.")[1]+"."+tip2.split("\\.")[2]+striii);
		}
		
		iparea.append("----------------Total ip nums: 254------------------\n");
		
		   
		   ExecutorService executor = Executors.newFixedThreadPool(ipthreadnums);
		   for (int i = 0; i < ipthreadnums; i++) {
			   Scaniprange2 scaniprange2 = new Scaniprange2();
	           executor.execute(scaniprange2);
	        }
		   executor.shutdown();
	        try {
	            while (!executor.isTerminated()) {
	                Thread.sleep(1000);
	            }
	        } catch (Exception e2) {
	            //e2.printStackTrace();
	        }
	    iparea.append("----------------Over!!!------------------\n");
	}

    
    


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		InetAddress tipordomain = null;
		//String tip2 = null;
		String tstartip, tendip, tip = null;
		
		if("Export".equals(command)) {
			Emportresult exp = new Emportresult();
			exp.exportres(Lsascan_v1_frame.Result.getText());
		}
		
		if("export".equals(command)) {
			Emportresult exp = new Emportresult();
			exp.exportres(Lsascan_v1_frame.iparea.getText());
		}
		
		if("PAUSE".equals(command)) {
			Lsaipscan.status = Lsaipscan.pause;
		}
		
		if("STOP".equals(command)) {
			Lsaipscan.status = Lsaipscan.stop;
		}
		
		if("weakup".equals(command)) {
			Lsaipscan.status = Lsaipscan.running;
		}
		
		if("brute".equals(command)) {
			brutethread = new Brutethread();  
   		    brutethread.start(); 
		}
		
		if("get processlist".equals(command)) {
			Processlist pl = new Processlist();
			try {
				pl.getprocesslist();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if("send".equals(command)) {
			Processlist pl = new Processlist();
			try {
				pl.killprocess();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if("refresh".equals(command)) {
			Processlist pl = new Processlist();
			try {
				pl.refreshprocesslist();;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		if("sniff".equals(command)) {
			sniffthread = new Sniffthread();  
   		    sniffthread.start(); 
	        	
	        }
		
		if("getip".equals(command)) {
			try {
				tipordomain = InetAddress.getByName(ipordomain.getText().trim());
				tip = tipordomain.getHostAddress();
				domainip.setText(tip);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			
			
			//tip2 = tip;
			//System.out.println(tip2);
			
			String tipstart = tip.split("\\.")[0]+"."+tip.split("\\.")[1]+"."+tip.split("\\.")[2]+".1";
			String tipend = tip.split("\\.")[0]+"."+tip.split("\\.")[1]+"."+tip.split("\\.")[2]+".254";
			startip.setText(tipstart);
			endip.setText(tipend);
			
		}
		
		
        if ("Start".equals(command)) {
        		
                Result.setText(null);
               

                try {
                        Lsaportscan.hostaddress=InetAddress.getByName(this.IPName.getText());
                } catch (UnknownHostException e1) {
                        errorDLG.setBounds(300, 280, 150, 110);
                        DLGINFO.setText("error ip/domain");
                        DLGINFO.setBounds(25, 15, 100, 20);
                        okBTN.setBounds(45, 40, 60, 30);
                        DLGINFO.setVisible(true);
                        return ;
                }
                
                
                if(scanrange.isSelected()) {
                	 scanportrangethread = new Scanportrangethread();  
            		 scanportrangethread.start(); 
                }
                
                else if(scanset.isSelected()) {
                	scanportsetthread = new Scanportsetthread();  
           		    scanportsetthread.start(); 
                	
        }
        }
        
        else if("SCANIP".equals(command)) {
//        	int getmac = 0;
//        	if(jb1.isSelected()) {
//        		getmac = 1;
//        	}
        	iparea.setText(null);
        	
        	
        	if(scansingleip.isSelected()) {
        		scansingleipthread = new Scansingleipthread();  
       		    scansingleipthread.start(); 
        		
        	}
        	if(scaniprange1.isSelected()) {
        		scaniprange1thread = new Scaniprange1thread();  
       		    scaniprange1thread.start();  
        	        
        	}
        	
        	if(scaniprange2.isSelected()) {
        		 scaniprange2thread = new Scaniprange2thread();  
        		 scaniprange2thread.start();  
        }
        
        
        else if("ok".equals(command)) {
                errorDLG.dispose();
        }else if ("Exit".equals(command)) {
                System.exit(1);
        }
}
	}
	}

	

