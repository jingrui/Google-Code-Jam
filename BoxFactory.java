// 2012 1C
// https://code.google.com/codejam/contest/1781488/dashboard#s=p2
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Solution {
  public static void main(String[] args){
		try {
			BufferedReader br = new BufferedReader(new FileReader("C-small-practice.in"));
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);

			int casenum = Integer.parseInt(br.readLine());
	        for(int i = 0; i < casenum; i++){
	        	StringTokenizer st = new StringTokenizer(br.readLine());
				int boxTN = Integer.parseInt(st.nextToken());    // first integer
				int toyTN = Integer.parseInt(st.nextToken());    // second integer
				StringTokenizer b = new StringTokenizer(br.readLine());
				StringTokenizer t = new StringTokenizer(br.readLine());
				
				BigInteger [][] box = new BigInteger[boxTN][2];
				BigInteger [][] toy = new BigInteger[toyTN][2];
				for(int j = 0; j < boxTN; j++){
					box[j][0] = new BigInteger(b.nextToken()); 
					box[j][1] = new BigInteger(b.nextToken());
				}
				for(int j = 0; j < toyTN; j++){
					toy[j][0] = new BigInteger(t.nextToken()); 
					toy[j][1] = new BigInteger(t.nextToken()); 
				}
				BigInteger max = helper(box,toy,0,0,BigInteger.ZERO,BigInteger.ZERO);
				out.write("Case #"+(i+1)+": "+max+"\n");
	        }
	        out.close();
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	}
	public static BigInteger helper(BigInteger[][]box, BigInteger[][] toy, int b, int t,BigInteger tmax, BigInteger max){
		if ((b >= box.length) || (t >= toy.length)){
			if (tmax.compareTo(max) == 1)
				max = tmax;
//			System.out.println(max);
			return max;
		}
		if (box[b][1].equals(toy[t][1])){
//			System.out.println(box[b][1]+" "+toy[t][1]);
			tmax = tmax.add((box[b][0].compareTo(toy[t][0]) == -1) ? box[b][0] : toy[t][0]);
//			System.out.println("box["+b+"][0]="+box[b][0]+",toy["+t+"][0]="+toy[t][0]+",tmax = "+tmax);
			if (box[b][0].compareTo(toy[t][0]) == 1){
				BigInteger [][]box2 = new BigInteger[box.length][2];
				for (int k = 0; k < box2.length; k++){
					box2[k][0] = new BigInteger(box[k][0].toString());
					box2[k][1] = new BigInteger(box[k][1].toString());
				}
				box2[b][0] = box2[b][0].subtract(toy[b][0]);
				return helper(box2,toy,b,t+1,tmax,max);	
			} 
			if (box[b][0].compareTo(toy[t][0]) == -1){
				BigInteger [][]toy2 = new BigInteger[toy.length][2];
				for (int k = 0; k < toy.length; k++){
					toy2[k][0] = new BigInteger(toy[k][0].toString());
					toy2[k][1] = new BigInteger(toy[k][1].toString());
				}
				toy2[b][0] = toy2[b][0].subtract(box[b][0]);
				return helper(box,toy2,b+1,t,tmax,max);	
			} 
			return helper(box,toy,b+1,t+1,tmax,max);	
		}
		BigInteger max1 = helper(box,toy,b+1,t,tmax,max);
		BigInteger max2 = helper(box,toy,b,t+1,tmax,max);
		return (max1.compareTo(max2) == 1)? max1 : max2;
	}
}
