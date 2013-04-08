//https://code.google.com/codejam/contest/1781488/dashboard#s=p0
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;


public class Solution {

  /**
	 * @param args
	 */
	public static void main(String[] args){
		try {
			BufferedReader br = new BufferedReader(new FileReader("A-large-practice.in"));
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			
			int casenum = Integer.parseInt(br.readLine());
	        for(int i = 0; i < casenum; i++){
	        	int classnum  = Integer.parseInt(br.readLine());
	        	HashMap<Integer, HashSet<Integer>> hm = new HashMap<Integer, HashSet<Integer>>();
	        	for(int j = 1; j <= classnum; j++){
	        		
	        		String str = br.readLine();
	        		String[] s = str.split(" ");
	        		int ancesnum  = Integer.parseInt(s[0]);
	        		if (ancesnum == 0){
//	        			System.out.println(j);
	        			HashSet temp;
	        			if(hm.containsKey(j))
	        				temp = hm.get(j);
	        			else 
	        				temp = new HashSet();
	        			temp.add(j);
	        			hm.put(j,temp);
//	        			System.out.println(hm.get(j));
	        		} else 
	        			for (int k = 0; k < ancesnum; k++){
	        				HashSet temp;
		        			if(hm.containsKey(Integer.parseInt(s[k+1])))
		        				temp = hm.get(Integer.parseInt(s[k+1]));
		        			else
		        				temp = new HashSet();
		        			temp.add(j);
		        			hm.put(Integer.parseInt(s[k+1]),temp);
	        			}
	        	}
//	        	System.out.println(hm.keySet());
	        	
	        	boolean ret = false;
	        	out:
	        	for(int j = 1; j <= classnum; j++){
	        		if (hm.containsKey(j))
	        			if (hm.get(j).contains(j)){// if it is included
//	        				System.out.println("this one contain it self "+j);
	        				HashSet hs = new HashSet();
	        				for(Integer a : hm.get(j)){
	        					if(a == j)
	        						continue;
	        					ret = DeepAdd(a, hs,hm);
	        					if (ret)
	        						break out;
	        				}
	        			}
	        	}
	        	
	        	out.write("Case #"+(i+1)+": "+((ret == true)? "Yes":"No")+"\n");
	        }
	        out.close();
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	}
	public static boolean DeepAdd(Integer key, HashSet<Integer> hs, HashMap<Integer,HashSet<Integer>> hm){
//		System.out.println("key = "+key);
		if (hs.contains(key)){
//			System.out.println("true");
			return true;
		}
		boolean ret = false;
		hs.add(key);	
		if (hm.containsKey(key))
				for(Integer b : hm.get(key)){
//					System.out.println(key+" -> "+b);
					ret = DeepAdd(b,hs,hm);
//					System.out.println(key+" -> "+b+", ret = "+ret);
					if (ret)	return ret;
				}
		return false;
	}
}
