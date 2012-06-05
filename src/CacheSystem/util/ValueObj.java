package CacheSystem.util;

public class ValueObj {
	Object obj;
	long expiry;
	
	public ValueObj (Object obj, long TTL) {
		this.obj 	 = obj;
		this.expiry = ((new java.util.GregorianCalendar ()).getTimeInMillis() + expiry); 
	}
	
	public boolean isExpired () {
		return (expiry == (new java.util.GregorianCalendar ()).getTimeInMillis());   
	}
}
