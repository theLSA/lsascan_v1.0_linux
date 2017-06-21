package lsascan_v1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;

public class Emportresult {
	public void exportres(String s) {
		JFileChooser fc = new JFileChooser();
		int returnval = fc.showSaveDialog(null);
		if(0==returnval) {
			File savefile = fc.getSelectedFile();
			String[] sp = s.split("[\\r\\n]");
		
			try {
				FileWriter writeout = new FileWriter(savefile);
				for(int i=0;i<sp.length;i++) {
					writeout.write(sp[i]);
            		writeout.write("\r\n");
            	}
				
				writeout.close();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
			
		}else {
			return;
		}
//		 File f;
//	        FileOutputStream fos = null;
//	        BufferedWriter bw;
//        f=new File("E:\\scanipresult.txt");
//        String[] sp = s.split("[\\r\\n]");
//        System.out.println(sp.length);
//         try {
//			fos =new FileOutputStream(f);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//         bw=new BufferedWriter(new OutputStreamWriter(fos));
//        
         
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            
//            try {
//				bw.flush();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            try {
//				bw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        }
	
}
