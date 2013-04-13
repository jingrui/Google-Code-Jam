//https://code.google.com/codejam/contest/dashboard?c=2270488#s=p2
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class Solution {
  public static void main(String[] args){
		try {
			BufferedReader br = new BufferedReader(new FileReader("B-large.in"));
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);

			int casenum = Integer.parseInt(br.readLine());
	        for(int i = 0; i < casenum; i++){
	        	String[] s = br.readLine().split(" ");
	        	int row = Integer.parseInt(s[0]);
	        	int col = Integer.parseInt(s[1]);
//	        	System.out.println("row = "+row+", col = "+col);
	        	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
	        	for(int r = 0; r < row; r++){
	        		al.add(new ArrayList<Integer>());
	        		String[] s1 = br.readLine().split(" ");
	        		for(int c = 0; c < col; c++){
	        			al.get(r).add(Integer.parseInt(s1[c]));
	        		}
	        	}
//	        	for(int r = 0; r < al.size(); r++){	        		
//	        		for(int c = 0; c < al.get(0).size(); c++){
//	        			System.out.print(al.get(r).get(c)+" ");
//	        		}
//	        		System.out.println();
//	        	}
	        	while(true){
	        		int k = 0;
	        		while(k < al.size()){
	        			if (al.get(k).isEmpty()){
	        				al.remove(k);
	        				continue;
	        			}
	        			k++;
	        		}
//	        		System.out.println("size = "+al.size());
	        		if(al.isEmpty())
	        			break;
//	        		if(!((al.size() == 1)&&(al.get(0).isEmpty())))
//	        			break;
	        		
//	        		for(int r = 0; r < al.size(); r++){	        		
//		        		for(int c = 0; c < al.get(0).size(); c++){
//		        			System.out.print(al.get(r).get(c)+" ");
//		        		}
//		        		System.out.println();
//		        	}
	        		int min = Integer.MAX_VALUE;
	        		int minr = 0;
	        		int minc = 0;
	        		// find min
		        	for(int r = 0; r < al.size(); r++){
		        		for(int c = 0; c < al.get(0).size(); c++){
		        			if (min > al.get(r).get(c)){
		        				minr = r;
		        				minc = c;
		        				min = al.get(r).get(c);
		        			}
		        		}
		        	}
//		        	System.out.println("r = "+minr+", c = "+minc+", min ="+min);
		        	
		        	int numc = 0, numr = 0;
		        	boolean rb = true, cb = true;
		        	// check the whole row		        	
		        	for(int j = 0; j < al.get(minr).size(); j++)
		        		if (al.get(minr).get(j) == min)
		        			numr++;
		        	
		        	if (numr != al.get(minr).size())
		        		rb = false;
		        	
		        	// check the whole column
		        	for(int j = 0; j < al.size(); j++)
		        		if (al.get(j).get(minc) == min)
		        			numc++;
		        	if (numc != al.size())
		        		cb = false;
		        			        	
		        	// NO case
		        	if((cb == false) && (rb == false)){
		        		out.write("Case #"+(i+1)+": NO\n");
		        		break;
		        	}
		        	
		        	// clean the the column that is good
		        	if (cb){
//		        		System.out.println("remove the whole col "+minc);
		        		for(int j = 0; j < al.size(); j++)
			        		al.get(j).remove(minc);
		        		continue;
		        	}
		        	// clean the the row that is good
		        	else if(rb){
//		        		System.out.println("remove the whole row "+minr);
			        	al.remove(minr);
		        		continue;
		        	}
	        	}
	        	if(al.size() !=0)	continue;
	        	out.write("Case #"+(i+1)+": YES\n");
	        }
	        out.close();
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	}
}
