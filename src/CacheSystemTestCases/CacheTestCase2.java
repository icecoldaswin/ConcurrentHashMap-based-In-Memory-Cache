package CacheSystemTestCases;

import CacheSystem.CacheSystem;
import CacheSystem.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CacheTestCase2 {

	public CacheTestCase2 ()
	{
		CacheSystem cs; 
		cs =  new CacheSystem (100, 75);
		File f = new File   ("C:\\xxxxxx\\apk\\BlockPuzzle2-22.apk");
		File csv = new File ("C:\\xxxxxx\\Perf.csv");
		PrintWriter pw = null;
		byte [] junk = new byte[1024];
		
		try {
			new DataInputStream (new FileInputStream (f)).read (junk);
			pw = new PrintWriter     (new FileWriter (csv));
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
		// Insert 25 numbers into the Cache
		
		long time_marker_ = System.currentTimeMillis();
		
		
		for (int i=0; i < 500; i++) {
			try {
			int TTL = (i==0?100:i); // If i is 0, then make TTL 100 s or make it i (we have TTL ranging from 1s to 500s). 
			time_marker_ = System.currentTimeMillis();
			cs.add("key"+i, new String (junk), TTL);
			time_marker_ = System.currentTimeMillis() - time_marker_;
			pw.println ("add, "+time_marker_);
			}
			catch (Exception e) {
				e.printStackTrace ();
			}
		}
		pw.flush();
		// Sleep 3 minutes to let a few of them expire
		
		try {
			System.out.println ("Sleeping at "+(new java.util.GregorianCalendar ()).getTime());
			Thread.sleep (360000);
		}
		catch (Exception e) {}

		// Read 15 of them in random
		
		for (int j = 0; j < 500; j++) {
			int random = new java.util.Random ().nextInt(500);
			try {
			System.out.println ("getting key"+random);
			time_marker_ = System.currentTimeMillis();
			System.out.println ("Gotten a random element (key"+random+", "+cs.get ("key"+random)+")");
			time_marker_ = System.currentTimeMillis() - time_marker_;
			pw.println ("get, "+time_marker_);
			}
			catch (Exception e) {
				e.printStackTrace ();
			}
		}
		pw.flush ();
		pw.close ();
//		
//		for (int i=0; i < 25; i++) {
//			try {
//			cs.add("key"+i, new Integer (i), (i/2));
//			}
//			catch (Exception e) {
//				e.printStackTrace ();
//			}
//		}
//		
//		
//		for (int i=0; i < 25; i++) {
//			try {
//			cs.add("key"+i, new Integer (i), (i/2));
//			}
//			catch (Exception e) {
//				e.printStackTrace ();
//			}
//		}
		

	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CacheTestCase2 ctc = new CacheTestCase2();
	}

}
