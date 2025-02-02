package Task1;
import java.util.Arrays;
import java.util.Scanner;
public class StringProcessor {
	public static String reverse(String s) 
	{
		char[] c = s.toCharArray();
		int start = 0,last = c.length - 1;
		
		while (start < last) 
		{
			char temp = c[start];
			c[start] = c[last];
			c[last] = temp;
			start++;
			last--;
		}
		
		return new String(c);
	}
	
	public static int countOccurrences(String text, String sub) 
	{
		int textlen = text.length();
		int sublen = sub.length();
		int count = 0;
		for(int i = 0;i < (textlen - sublen + 1);i++) 
		{
			boolean found = true;
			
			for(int j = 0;j < sublen;j++) 
			{
				if (text.charAt(i + j) != sub.charAt(j)) 
				{
					found = false;
					break;
				}
			}
			
			if (found) 
			{
				count++;
			}
			
		}
		return count;
	}
	
	public static String splitAndCapitalize(String str) {
	    String[] s = str.split(" ");  
	    StringBuffer res = new StringBuffer();

	    for (String word : s) {
	        res.append(word.substring(0, 1).toUpperCase())
	           .append(word.substring(1))
	           .append(" ");
	    }

	    return res.toString().trim();
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String substring = sc.next();sc.nextLine();
		String str = sc.nextLine();
		
		System.out.println("Reversed String: "+reverse(s));
		System.out.println("Count Occurances: "+countOccurrences(s, substring));
		System.out.println("splitAndCapitalize: "+splitAndCapitalize(str));
		
	}

}
