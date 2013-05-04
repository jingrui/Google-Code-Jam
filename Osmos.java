//http://code.google.com/codejam/contest/2434486/dashboard#s=p0
// correct for small input
// not good for large one
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
  public static void main(String[] args){
  	try {
			BufferedReader br = new BufferedReader(new FileReader("A-large.in"));
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);

			int casenum = Integer.parseInt(br.readLine());
	        for(int i = 0; i < casenum; i++){
	        	String str = br.readLine();
        		String[] s = str.split(" ");
        		int my = Integer.parseInt(s[0]);
        		int num = Integer.parseInt(s[1]);
//        		System.out.println("my ="+my+", num = "+num);
        		ArrayList<Integer> al = new ArrayList<Integer>();
        		
        		str = br.readLine();
        		String[] motes = str.split(" ");
        		for(int j = 0; j < motes.length; j++)
        			al.add(Integer.parseInt(motes[j]));
        		
        		Collections.sort(al);
//        		for(int j = 0; j < motes.length; j++)
//        			System.out.println("al.get("+j+")="+al.get(j));
        		
        		int steps = helper(al,0,0,my,al.size());
        		
        		System.out.println("Case #"+(i+1)+": "+steps+"\n");
				out.write("Case #"+(i+1)+": "+steps+"\n");
	        }
	        
	        out.close();
	        br.close();
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	}
  public static int helper(ArrayList<Integer> al, int cur,int steps, int my,int limit){
	  System.out.println("cur = "+cur+", steps = "+steps+", my = "+my);
	  if(al.size() == 0)
		  return steps;
	  
	  if (steps >= limit)
		  return limit;
	  
	  if(my > al.get(cur)){
			my += al.get(cur);
			al.remove(cur);
			return helper(al,cur,steps,my,limit);
	  }
	  
	  System.out.println("my = "+my+", al.get("+cur+") = "+al.get(cur)+",al.size() = "+al.size());
	  // if it is too small
	  int temp = my;
	  for(int i = 0; i < al.size(); i++){
		  if (temp > Integer.MAX_VALUE/2){
			  temp = Integer.MAX_VALUE;
			  break;
		  }
		  temp = temp*2-1;
	  }
	  System.out.println("temp = "+temp);
	  
	  if (temp < al.get(cur)){
		  int ret = al.size();
		  al = new ArrayList<Integer>();
//		  System.out.println("	ret = "+(steps+ret)+",al.size() ="+al.size());
		  return steps+ret;	  
	  }
	  
	  // if need to add one or delete one
	  steps++;
	  ArrayList<Integer> al2 = new ArrayList<Integer>(al);
	  int cur2 = cur, my2 = my, ret2, ret1;
	  // add one, that is just smaller than my
	  // delete the cur one
	  al.remove(cur);
	  ret1 = helper(al,cur,steps,my,limit);
	  if( my == 1){
		  	return ret1;
	  }
	  
	  ret2 = helper(al2,cur2,steps,my2*2-1,limit);
	  return Math.min(ret1, ret2);
  }
}






