package CacheSystem.util;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class CacheCleaner extends Thread {

	Hashtable<String, ValueObj> dataStore;
	TreeMap<Long, ArrayList <String>> expiryIndex;
	Map.Entry<Long, ArrayList <String>> indexElement;
	int cleanupFrequency = 1;

	public CacheCleaner (Hashtable<String, ValueObj> dataStore, TreeMap<Long, ArrayList <String>> expiryIndex) {
		setDataStore (dataStore);
		setExpiryIndex(expiryIndex);
		this.run();
	}
	public CacheCleaner () {
		
	}
	
	public void setDataStore (Hashtable<String, ValueObj> dataStore) {
		this.dataStore = dataStore;
	}
	
	public void setExpiryIndex (TreeMap<Long, ArrayList <String>> expiryIndex) {
		this.expiryIndex = expiryIndex;
	}
	
	public void setCleanupFrequency (int cleanupFrequency) {
		this.cleanupFrequency = cleanupFrequency;
	}
	
	public void run () {
		long currentTime;
		while (true) {
			indexElement = null;
			currentTime = System.currentTimeMillis();
			System.out.println ("Cleaner woke-up at: "+currentTime+". No. of elements in index: "+expiryIndex.size());
			
			indexElement = expiryIndex.floorEntry (currentTime);
			
			while (indexElement != null) {
				Iterator<String> keyIterator = indexElement.getValue().iterator();
				while (keyIterator.hasNext()) {
					String key = keyIterator.next();
					System.out.println ("Cleaning up: "+key);
					dataStore.remove(key);
				}
				expiryIndex.remove(indexElement.getKey());
				indexElement = expiryIndex.floorEntry (currentTime);
			}
			
			try {
			System.out.println ("Cleaner sleeping for: " + cleanupFrequency*(30) +" s"); /* cleanupFrequency*(1000) */ 
			synchronized (this) {
				wait (cleanupFrequency*(30000));
			}
			}
			catch (InterruptedException e) { 
				e.printStackTrace ();
			}
		}
	}
	
}
