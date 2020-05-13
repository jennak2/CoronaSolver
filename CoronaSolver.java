import java.util.Scanner;
import java.util.*;

public class CoronaSolver {
	private int n=0;
	private int f=0;
	private ArrayList newYorkList;
	private ArrayList losAngelesList;
	
	Scanner scan = new Scanner(InputFileName);
	ArrayList <Integer> list = new ArrayList <Integer>();
	public String solve(String InputFileName) {
			while(scan.hasNext()) {
				list.add(Integer.parseInt(scan.next()));
			}
			for (int i=0;i<list.size();i++){
				if (i==0){
					n=list.get(0);
				}else if (i==1){
					f=list.get(1);
				}else if (i%2==0) {
					newYorkList.add(list.get(i));
			} else {
				losAngelesList.add(list.get(i));
		
		
	}
	private int solveHelper (int num, int day, boolean flight) {
		if(flight) {
		num+= list.get(day+2) + list.get(1);
		} else {
			num+= list.get(day+2);
		}
	}
}
