// Hash Version. 
// C-Large-1.in:6mins
// C-lARGE-2.in:not able to do that within time
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;


public class Solution {
	static BigInteger one = BigInteger.ONE;
	static BigInteger zero = BigInteger.ZERO;
	public static void main(String[] args){
		
		Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");
	    System.out.println("Start: " + ft.format(dNow));
		try {
			BufferedReader br = new BufferedReader(new FileReader("C-large-2.in"));
			BufferedReader br2 = new BufferedReader(new FileReader("C-large-copy.in"));
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);

			String []str;
			BigInteger s,e,temp;			
			int casenum = Integer.parseInt(br.readLine());
			
			HashSet<BigInteger> hs = new HashSet<BigInteger>();
			BigInteger min = new BigInteger(Integer.toString(Integer.MAX_VALUE));
			BigInteger max = new BigInteger(Integer.toString(Integer.MIN_VALUE));
			
	        for(int i = 0; i < casenum; i++){
	        	str = br.readLine().split(" ");
	        	s = new BigInteger(str[0]);
	        	e = new BigInteger(str[1]);
	        	temp = bigIntSqRootFloor(s);
	        	if (temp.pow(2).compareTo(s) == 0)
	        		s = temp;
	        	else
	        		s = temp.add(one);
	        	e = bigIntSqRootFloor(e);
	        	max = max.max(e);
	        	min = min.min(s);
	        }
	        count(min,max,hs);
	        
	        BigInteger num;
	        br2.readLine();// jump over case number line
	        System.out.println("Data prepared");
	        
	        for(int i = 0; i < casenum; i++){
	        	str = br2.readLine().split(" ");
	        	s = new BigInteger(str[0]);
	        	e = new BigInteger(str[1]);
	        	temp = bigIntSqRootFloor(s);
	        	if (temp.pow(2).compareTo(s) == 0)
	        		s = temp;
	        	else
	        		s = temp.add(one);
	        	e = bigIntSqRootFloor(e);
	        	num = zero;
	        	for(BigInteger k = s; (k.compareTo(e) != 1);k = k.add(one))
	        		if(hs.contains(k))	num = num.add(one);
	        	
	        	out.write("Case #"+(i+1)+": "+num+"\n");
	        }
	        out.close();
	        
	        System.out.println("done");
	        dNow = new Date( );
		    ft = new SimpleDateFormat ("hh:mm:ss");
		    System.out.println("End : " + ft.format(dNow));
	        
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	}
	
	public static BigInteger count(BigInteger cur, BigInteger e, HashSet<BigInteger> hs){
		BigInteger num = zero;
		while(cur.compareTo(e)!= 1){
    		BigInteger sqr = cur.pow(2);
    		if (checkPalin(cur) == false)// check palindrome
    			cur = cur.add(one);
    		else if (checkPalin(sqr) == false)// check square palindrome
    			cur = cur.add(one);
    		else {	
    			hs.add(cur);
    			num = num.add(one);
            	cur = cur.add(one);  
    		}    		      	
    	}
		return num;   	
	}
	
	public static boolean checkPalin(BigInteger cur){
		BigInteger a = cur;
		BigInteger b = BigInteger.ZERO;
		while(a.compareTo(BigInteger.ZERO) == 1) {
			BigInteger ten = new BigInteger("10");
			b = b.multiply(ten);
			b = b.add(new BigInteger(a.mod(ten).toString()));
			a = a.divide(ten);
		}		
		return (cur.compareTo(b) == 0);
	}
	
	public static BigInteger bigIntSqRootFloor(BigInteger n) {
		  BigInteger a = BigInteger.ONE;
		  BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
		  while(b.compareTo(a) >= 0) {
		    BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
		    if(mid.multiply(mid).compareTo(n) > 0) 
		    	b = mid.subtract(BigInteger.ONE);
		    else 
		    	a = mid.add(BigInteger.ONE);
		  }
		  return a.subtract(BigInteger.ONE);
	}
}



// This is still not enough to finish big input in 8 mins. It takes about 30 mins.
// Need to Use Hash!!!!!!!!!!!
//https://code.google.com/codejam/contest/2270488/dashboard#s=p2
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.HashMap;


public class Solution {
  public static void main(String[] args){
		try {
			BufferedReader br = new BufferedReader(new FileReader("C-large-1.in"));
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);

			String []str;
			BigInteger s,e,temp;
			BigInteger one = BigInteger.ONE;
			BigInteger zero = BigInteger.ZERO;
			int casenum = Integer.parseInt(br.readLine());
	        for(int i = 0; i < casenum; i++){
	        	System.out.println(i);
	        	str = br.readLine().split(" ");
	        	s = new BigInteger(str[0]);
	        	e = new BigInteger(str[1]);
	        	System.out.println("s = "+s+", e = "+e);
	        	temp = bigIntSqRootFloor(s);
	        	if (temp.pow(2).compareTo(s) == 0)
	        		s = temp;
	        	else
	        		s = temp.add(one);
	        	
	        	e = bigIntSqRootFloor(e);
	        	
	        	BigInteger cur = s, num = zero;
	        	while(cur.compareTo(e)!= 1){
	        		BigInteger sqr = cur.pow(2);

	        		// check palindrome
	        		if (checkPalin(cur) == false){
	        			cur = cur.add(one);
	        			continue;
	        		}
	        		
		        	// check square palindrome
	        		if (checkPalin(sqr) == false){
	        			cur = cur.add(one);
	        			continue;
	        		}
//	        		System.out.println(cur+" is valid, root = "+ root);
	        		num = num.add(one);
		        	cur = cur.add(one);
	        	}
	        	out.write("Case #"+(i+1)+": "+num+"\n");
	        }
	        out.close();
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	}
	public static boolean checkPalin(BigInteger cur){
		BigInteger a = cur;
		BigInteger b = BigInteger.ZERO;
		while(a.compareTo(BigInteger.ZERO) == 1) {
			BigInteger ten = new BigInteger("10");
			b = b.multiply(ten);
			b = b.add(new BigInteger(a.mod(ten).toString()));
			a = a.divide(ten);
		}		
		return (cur.compareTo(b) == 0);
	}
	public static BigInteger bigIntSqRootFloor(BigInteger n) {
		  BigInteger a = BigInteger.ONE;
		  BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
		  while(b.compareTo(a) >= 0) {
		    BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
		    if(mid.multiply(mid).compareTo(n) > 0) 
		    	b = mid.subtract(BigInteger.ONE);
		    else 
		    	a = mid.add(BigInteger.ONE);
		  }
		  return a.subtract(BigInteger.ONE);
	}
}
