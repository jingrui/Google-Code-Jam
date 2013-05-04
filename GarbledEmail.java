//http://code.google.com/codejam/contest/2434486/dashboard#s=p2
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;

public class Solution {
  public static void main(String[] args){
  	try {
			BufferedReader br = new BufferedReader(new FileReader("A-large.in"));
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			
			BufferedReader br2 = new BufferedReader(new FileReader("garbled_email_dictionary.txt"));
			HashSet<String> hs= new HashSet<String>();
			
			String dict = null;
			while((dict = br2.readLine())!=null)
				hs.add(dict);
			System.out.println(hs.size());
			br2.close();
				
			int casenum = Integer.parseInt(br.readLine());
	        for(int i = 0; i < casenum; i++){
	        	String str = br.readLine();
        		int length = str.length();
        		int cur = 0, num = 0;
        		num = check(0,str,0,hs);
        		
    			System.out.println(num);

//				out.write("Case #"+(i+1)+": "+steps+"\n");
	        }
	        
	        out.close();
	        br.close();
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	}
	public static int check(int num, String str, int cur, HashSet<String> hs){
		if (str.length() == 0)
			return num;
		
		int best = Integer.MAX_VALUE;
		while(cur < str.length()){
			for(int j = -4; j <= 4; j++){
				String nstr = switchword(str,cur,j);
//				System.out.println(nstr);
				for(int k = nstr.length(); k > 0 ; k--)
					if (hs.contains(nstr.substring(0,k))){
						int num2 = (j == 0)? num : num+1;
//						System.out.println("nstr = "+nstr+",hs.contains("+nstr.substring(0,k)+"),k = "+k+",num2 ="+ num2);
						best = Math.min(check(num2,nstr.substring(k),0,hs),best);
					}
			}
			cur++;
		}
		return best;
	}
  
  	public static String switchword(String str, int cur, int index){
  		int diff = (str.charAt(cur)+index-'a') % 26;
  		if(diff < 0)
  			diff+=26;
  		return str.substring(0,cur)+(char)('a'+diff)+str.substring(cur+1);
  	}
  
}






