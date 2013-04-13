//https://code.google.com/codejam/contest/2270488/dashboard#s=p3
// the method is correct. However, it takes too much space for certain test case
// cannot finish running the basic small input. 
// I guess I can recusively add the history so that Hist does not need to copy and pass recursively anymore
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
  static BigInteger one = BigInteger.ONE;
	static BigInteger zero = BigInteger.ZERO;
	public static void main(String[] args){
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("D-small-attempt0.in"));
//			BufferedReader br2 = new BufferedReader(new FileReader("C-large-copy.in"));
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);

			String []str;		
			int casenum = Integer.parseInt(br.readLine());
			for(int i = 0; i < casenum; i++){
//				System.out.println("here");
				str = br.readLine().split(" ");
				int knum = Integer.parseInt(str[0]);
				int chestnum = Integer.parseInt(str[1]);
				System.out.println("k = "+knum+", chestnum = "+chestnum);
				
				// keys
				HashMap<Integer,Integer> keymap = new HashMap<Integer,Integer>();
				str = br.readLine().split(" ");
				for(int j = 0; j < knum; j++){
					int tmpkey = Integer.parseInt(str[j]);
					if (keymap.containsKey(tmpkey))
						keymap.put(tmpkey, keymap.get(tmpkey)+1);
					else
						keymap.put(tmpkey, 1);
				}
//				for(Integer key : keymap.keySet()){
//					System.out.println(key+","+keymap.get(key));
//				}
//				System.out.println(chestnum);
				// chests
				HashMap<Integer,Integer> chestKey = new HashMap<Integer,Integer>();
				HashMap<Integer,HashMap<Integer,Integer>> chestAward = new HashMap<Integer,HashMap<Integer,Integer>>();
				for(int j = 1; j <= chestnum; j++){
					str = br.readLine().split(" ");
					chestKey.put(j,Integer.parseInt(str[0]));
					int awardnum = Integer.parseInt(str[1]);
					
					HashMap<Integer,Integer> award = new HashMap<Integer,Integer>();
					for(int l = 0; l < awardnum; l++){
						int tmpkey = Integer.parseInt(str[2+l]);
						if (award.containsKey(tmpkey)){
							award.put(tmpkey, award.get(tmpkey)+1);
						}
						else{
							award.put(tmpkey, 1);
						}
					}
					chestAward.put(j,award);
				}
				// chest holder
				ArrayList<String> chestSet = new ArrayList<String>();
				for(int j = 1; j <= chestnum; j++)
					chestSet.add(Integer.toString(j));
				// test
				ArrayList<Integer> ret = null;
				ArrayList<Integer> hist = new ArrayList<Integer>();				
				for(int j = 0; j < chestSet.size(); j++){
					HashMap<Integer,Integer> nkeymap = new HashMap<Integer,Integer>(keymap); 
					HashMap<Integer,Integer> nchestKey = new HashMap<Integer,Integer>(chestKey);
					HashMap<Integer,HashMap<Integer,Integer>> nchestAward 
						= new HashMap<Integer,HashMap<Integer,Integer>>(chestAward);
					ArrayList<String> nchestSet = new ArrayList<String>(chestSet);
					ArrayList<Integer> nhist = new ArrayList<Integer>(hist);					
					
					System.out.println("\n 先开 open chest "+chestSet.get(j));
					ret = helper(Integer.parseInt(nchestSet.get(j)),nkeymap,
							nchestKey,nchestAward,nchestSet,nhist);
					if (ret !=null){
						break;
					}
					System.out.println("开 Open chest "+chestSet.get(j)+" 失败");
				}
				System.out.println((i+1)+" Done");
				if(ret == null)
					out.write("Case #"+(i+1)+": IMPOSSIBLE\n");
				else{					
					out.write("Case #"+(i+1)+":");
					for(int p = 0; p < ret.size(); p++)
						out.write(" "+ret.get(p));
					out.write("\n");
				}
			}
			out.close();			
		 } catch (Exception e) {
		        System.err.println("Error:" + e.getMessage());
		 }
	}
	public static ArrayList<Integer> helper(int chestNo, 
			HashMap<Integer,Integer> keymap, 
			HashMap<Integer,Integer> chestKey,
			HashMap<Integer,HashMap<Integer,Integer>> chestAward,
			ArrayList<String> chestSet,
			ArrayList<Integer> hist){
		System.out.println("open chestNo = "+chestNo);
		
		int keyneed = chestKey.get(chestNo);
		if(keymap.containsKey(keyneed)){
			// record the history
			hist.add(chestNo);
//			for(int p = 0; p < hist.size(); p++)
//				System.out.println(" "+hist.get(p));
			
			// remove the chest
			chestSet.remove(Integer.toString(chestNo));
			System.out.println("Has key = "+keyneed+", open chest "+chestNo+", chestSet.size = "+chestSet.size());
			if(chestSet.isEmpty())
				return hist;
			chestKey.remove(chestNo);
			
			// use one key
			if(keymap.get(keyneed) == 1)
				keymap.remove(keyneed);
			else
				keymap.put(keyneed, keymap.get(keyneed)-1);
			
			// add new keys from chestAward to keymap
			HashMap<Integer,Integer> award = chestAward.get(chestNo);
			for(Integer k : award.keySet()){
				if(keymap.containsKey(k))
					keymap.put(k,keymap.get(k)+award.get(k));
				else
					keymap.put(k,award.get(k));
//				System.out.println("After Update,keymap<"+k+","+keymap.get(k)+">");
			}
			chestAward.remove(chestNo);
			int i;
			for(i = 0; i < chestSet.size(); i++){
				HashMap<Integer,Integer> nkeymap = new HashMap<Integer,Integer>(keymap); 
				HashMap<Integer,Integer> nchestKey = new HashMap<Integer,Integer>(chestKey);
				HashMap<Integer,HashMap<Integer,Integer>> nchestAward 
					= new HashMap<Integer,HashMap<Integer,Integer>>(chestAward);
				ArrayList<String> nchestSet = new ArrayList<String>(chestSet);
				ArrayList<Integer> nhist = new ArrayList<Integer>(hist);
				ArrayList<Integer> ret = helper(Integer.parseInt(nchestSet.get(i)),nkeymap,nchestKey,nchestAward,nchestSet,nhist);
				if (ret!=null)					
					return ret;
			}			
			return null;
		} 
		// no key to this chest
		System.out.println("No key = "+keyneed);
		return null;
	}
}
