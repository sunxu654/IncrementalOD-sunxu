package Data;

import java.util.regex.Pattern;

public final class Cmp {
	 
	static public boolean equals(String s1,String s2) {
		//这个主要是用于增量和原来的key作比较，如果cur不存在的话，那么就一定不会出现split
		if(s1.equals("")||s2.equals("")) return true;
		if(isInteger(s1)&&isInteger(s2)) {
			return Integer.parseInt(s1)==Integer.parseInt(s2);
		}
		return s1.equals(s2);
	}
	static public int compare(String s1,String s2) {
//		if(s1.equals("")&&s2.equals("")==false&&isInteger(s2)) return -Integer.parseInt(s2);
//		if(s2.equals("")&&s1.equals("")==false&&isInteger(s1)) return Integer.parseInt(s1);
		if(s1.equals("")) return 0;
		if(isInteger(s1)&&isInteger(s2)) {
			return Integer.parseInt(s1)-Integer.parseInt(s2);
		}
		return s1.compareTo(s2);
	}
	
	public static boolean isInteger(String str) { 
//		if(str.equals("")) return false;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
  }
	
//	static public boolean equals(Integer a,Integer b) {
//		return a==b;
//	}
//	static public int compare(Integer a,Integer b) {
//		return a-b;
//	}
	
}
