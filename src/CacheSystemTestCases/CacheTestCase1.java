package CacheSystemTestCases;

import CacheSystem.CacheSystem;
import CacheSystem.util.*;

public class CacheTestCase1 {

	public CacheTestCase1 ()
	{
		CacheSystem cs; 
		cs =  new CacheSystem (100, 75);

		// Insert 25 numbers into the Cache
		
		for (int i=0; i < 25; i++) {
			try {
			int TTL = (i==0?100:i*4)/2;
			if (TTL > 60 || TTL < 30)
				TTL = 120;
			
			cs.add("key"+i, new Integer (i), TTL*1000);
			}
			catch (Exception e) {
				e.printStackTrace ();
			}
		}
		
		// Sleep 3 minutes to let a few of them expire
		
		try {
			System.out.println ("Sleeping at "+(new java.util.GregorianCalendar ()).getTime());
			Thread.sleep (180000);
		}
		catch (Exception e) {}

		// Read 15 of them in random
		
		for (int j = 0; j < 15; j++) {
			int random = new java.util.Random ().nextInt(15);
			try {
			System.out.println ("getting key"+random);
			System.out.println ("Gotten a random element (key"+random+", "+cs.get ("key"+random)+")");
			}
			catch (Exception e) {
				e.printStackTrace ();
			}
		}
		
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
		CacheTestCase1 ctc = new CacheTestCase1();
	}

}
