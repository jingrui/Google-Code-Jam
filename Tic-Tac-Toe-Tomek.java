//https://code.google.com/codejam/contest/dashboard?c=2270488#s=p1
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Solution {
  public static void main(String[] args){
		try {
			BufferedReader br = new BufferedReader(new FileReader("A-large.in"));
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);

			int casenum = Integer.parseInt(br.readLine());
	        for(int i = 0; i < casenum; i++){
	        	char[][] game = new char[4][4];
	        	for(int j = 0; j < 4; j++){
	        		String s = br.readLine();
	        		for(int k = 0; k < 4; k++){
	        			game[j][k] = s.charAt(k);
	        		}
	        	}
	        	boolean uncompleted = false;
	        	boolean win = false;
	        	// check line
	        	int j = 0;
	        	int k = 0;
	        	for(j = 0; j < 4; j++){
	        		int x = 0;
	        		int o = 0;
	        		int t = 0;
	        		for(k = 0; k < 4; k++){
	        			x += (game[j][k] == 'X')? 1:0;
	        			o += (game[j][k] == 'O')? 1:0;
	        			t += (game[j][k] == 'T')? 1:0;
	        			if (game[j][k] == '.')	uncompleted = true;
	        		}
	        		if (x+t == 4){
	        			out.write("Case #"+(i+1)+": X won\n");
	        			win  = true;
	        			break;
	        		}
	        		if (o+t == 4){
	        			out.write("Case #"+(i+1)+": O won\n");
	        			win = true;
	        			break;
	        		}
	        	}
	        	if(win)	{
	        		br.readLine();
	        		continue;
	        	}
	        	// check col
	        	for(j = 0; j < 4; j++){
	        		int x = 0;
	        		int o = 0;
	        		int t = 0;
	        		for(k = 0; k < 4; k++){
	        			x += (game[k][j] == 'X')? 1:0;
	        			o += (game[k][j] == 'O')? 1:0;
	        			t += (game[k][j] == 'T')? 1:0;
	        		}
	        		if (x+t == 4){
	        			out.write("Case #"+(i+1)+": X won\n");
	        			win  = true;
	        			break;
	        		}
	        		if (o+t == 4){
	        			out.write("Case #"+(i+1)+": O won\n");
	        			win = true;
	        			break;
	        		}
	        	}
	        	if(win)	{
	        		br.readLine();
	        		continue;
	        	}
	        	int x = 0;
        		int o = 0;
        		int t = 0;
        		// 1st diagonal
	        	for(j = 0; j < 4; j++){
	        		x += (game[j][j] == 'X')? 1:0;
        			o += (game[j][j] == 'O')? 1:0;
        			t += (game[j][j] == 'T')? 1:0;
	        	}
	        	if (x+t == 4){
        			out.write("Case #"+(i+1)+": X won\n");
        			br.readLine();
        			continue;
        		}
        		if (o+t == 4){
        			out.write("Case #"+(i+1)+": O won\n");
        			br.readLine();
        			continue;
        		}
        		x = 0;
        		o = 0;
        		t = 0;
        		// 2nd diagonal
        		for(j = 0; j < 4; j++){
	        		x += (game[j][3-j] == 'X')? 1:0;
        			o += (game[j][3-j] == 'O')? 1:0;
        			t += (game[j][3-j] == 'T')? 1:0;
	        	}
	        	if (x+t == 4){
        			out.write("Case #"+(i+1)+": X won\n");
        			br.readLine();
        			continue;
        		}
        		if (o+t == 4){
        			out.write("Case #"+(i+1)+": O won\n");
        			br.readLine();
        			continue;
        		}
        		// if no one won
	        	if(uncompleted){
	        		out.write("Case #"+(i+1)+": Game has not completed\n");
	        		br.readLine();
	        		continue;
	        	}
	        	out.write("Case #"+(i+1)+": Draw\n");
	        	br.readLine();
	        }
	        out.close();
	    } catch (Exception e) {
	        System.err.println("Error:" + e.getMessage());
	    }
	}
}
