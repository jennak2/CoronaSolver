import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
public class CoronaSolver
{
	private class Node{
		Node left;
		Node right;
		int data;
		
		public Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
		
		public void setLeft(Node left) {
			this.left = left;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}
		
		public void setData(int data) {
			this.data = data;
		}
		
		public Node getLeft() {
			return left;
		}
		
		public Node getRight() {
			return right;
		}
		
		public int getData() {
			return data;
		}
	}
	

	public String solve(String inputFileName) throws FileNotFoundException
	{
		File file = new File(inputFileName);
		Scanner sc = new Scanner(file);
		ArrayList<Integer> list = new ArrayList<Integer>();
		int totalDays = sc.nextInt();
		int exposed = sc.nextInt();
		Node root1 = new Node(sc.nextInt());
		Node root2 = new Node(sc.nextInt());
		organize(sc, root1, root2);
		Queue<Node> queue = new LinkedList<Node>();
		Queue<String> location = new LinkedList<String>();
		queue.add(root1);
		queue.add(root2);
		location.add("L");
		location.add("N");
		Node temp = queue.poll();
		String currentLocation = location.poll();
		ArrayList<String> schedules = new ArrayList<String>();
		boolean bool;
		Node add1;
		Node add2;
		while(temp.getLeft() != null && temp.getRight() != null) {
			if(currentLocation.charAt(currentLocation.length() - 1) == 'L')
			{
				add1 = new Node(temp.getData() + temp.getLeft().getData());
				add1.setLeft(temp.getLeft().getLeft());
				add1.setRight(temp.getLeft().getRight());
				bool = queue.add(add1);
				add2 = new Node(temp.getData() + temp.getRight().getData() + exposed);
				add2.setLeft(temp.getRight().getLeft());
				add2.setRight(temp.getRight().getRight());
				bool = queue.add(add2);
			}
			else {
				add1 = new Node(temp.getData() + temp.getLeft().getData() + exposed);
				add1.setLeft(temp.getLeft().getLeft());
				add1.setRight(temp.getRight().getRight());
				bool = queue.add(add1);
				add2 = new Node(temp.getData() + temp.getRight().getData());
				add2.setLeft(temp.getLeft().getLeft());
				add2.setRight(temp.getRight().getRight());
				bool = queue.add(add2);
			}
			
			location.add(currentLocation + " L");
			location.add(currentLocation + " N");
			
			temp = queue.poll();
			currentLocation = location.poll();
		}
		
		int minValue = queue.poll().getData();
		String minSchedule = location.poll();
		
		while(!queue.isEmpty())
		{
			if(minValue > queue.peek().getData()) {
				minValue = queue.poll().getData();
				minSchedule = location.poll();
			}
			else {
				queue.remove();
				location.remove();
			}
		}
		
		return minValue +"\n" + minSchedule;
	}
	
	private void organize(Scanner sc, Node root1, Node root2){
		Queue<Node> queue1 = new LinkedList<Node>();
		Queue<Node> queue2 = new LinkedList<Node>();
		Queue<Node> queueTemp1 = new LinkedList<Node>();
		Queue<Node> queueTemp2 = new LinkedList<Node>();
		queue1.add(root1);
		queue2.add(root2);
		Node temp1;
		Node temp2;
		int valueL;
		int valueN;
		
		while(sc.hasNext()) {
			valueL = sc.nextInt();
			valueN = sc.nextInt();
			while(!queue1.isEmpty()) {
				temp1 = queue1.poll();
				temp1.setLeft(new Node(valueL));
				temp1.setRight(new Node(valueN));
				queueTemp1.add(temp1.getLeft());
				queueTemp2.add(temp1.getRight());
			}
			queue1 = queueTemp1;
			queueTemp1 = new LinkedList<Node>();
			while(!queue2.isEmpty()) {
				temp2 = queue2.poll();
				temp2.setLeft(new Node(valueL));
				temp2.setRight(new Node(valueN));
				queueTemp2.add(temp2.getLeft());
				queueTemp2.add(temp2.getRight());
			}
			queue2 = queueTemp2;
			queueTemp2 = new LinkedList<Node>();
		}
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(new CoronaSolver().solve("testfile.txt"));
	}
}