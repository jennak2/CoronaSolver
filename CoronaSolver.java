import java.util.Scanner;
import java.util.*;

public class CoronaSolver {
	Scanner scan = new Scanner(InputFileName);
	ArrayList <Integer> list = new ArrayList <Integer>();
	public String solve(String InputFileName) {
		while(scan.hasNext()) {
			list.add(Integer.parseInt(scan.next()));
		}
	}
	private int solveHelper (int num, int day, boolean flight) {
		if(flight) {
		num+= list.get(day+2) + list.get(1);
		} else {
			num+= list.get(day+2);
		}
	}
}
