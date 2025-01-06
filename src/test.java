
import java.util.*;
import java.io.*;
public class test {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;



	public static void main(String[] args) throws IOException {
		//		int n = readInt();
		int count = 0; 

		Queue<String> queue = new PriorityQueue<>(10,Collections.reverseOrder());
		queue.add("ELVES");
		while (!queue.isEmpty()) {
			System.out.println(queue); String next = queue.remove();
			if (next.length() > 1) {
				int middle = (int)(Math.round(next.length()/2.0));
				queue.add(next.substring(0,middle));
				queue.add(next.substring(middle));
			}
		}
//		Queue <Integer> numbers = new LinkedList <Integer>();
//		numbers.add (756);
//		numbers.add (4528);
//		numbers.add (8);
//		while (!numbers.isEmpty ())
//		{
//			int next = numbers.remove ();
//			System.out.print (next +" ");
//			if (next > 10)
//				numbers.add (next / 10);
//		}
		List<Integer> linked = new LinkedList<Integer>();
		
		ArrayList<Integer> al  = new ArrayList<Integer>();
		TreeSet<Integer> ts = new TreeSet<>();
		Stack<Integer> st = new Stack(); st.push(1);
		System.out.println(st.search(1));
		
		Queue <Integer> numbers = new PriorityQueue <Integer>();
		Deque<Integer> num = new LinkedList<>();
		
		numbers.add (756);
		numbers.add (4528);
		numbers.add (8);
		while (!numbers.isEmpty ())
		{
			int next = numbers.remove ();
			System.out.print (next +" ");
			if (next > 10)
				numbers.add (next / 10);
		}
		
		Map<String,Integer> mp = new HashMap<>();
		mp.put("a", 1);
		mp.put("b", 2);
		mp.put("c", 3);
		mp.put("d", 4);
		mp.put("e", 5);
		Collection<Integer> values = mp.values();
		
	
		Set<String> s = mp.keySet();
		s.remove("e");

		for (String str: s) System.out.println(str);
		
		//		}
		//		st.push(1); st.push(2); st.push(3);
		//		Iterator<Integer> iter = st.iterator();
		//		while (iter.hasNext()) {
		//			System.out.println(iter.next());
		//		}
		//		Queue<Integer> q = new LinkedList<Integer>();
		//		LinkedList<Integer> link = new LinkedList<Integer>();
		//		List<Integer> lst = new ArrayList<Integer>();
		//
		//		lst.add(1); lst.add(2); lst.add(3);
		//		ListIterator<Integer> itera = lst.listIterator();
		//		itera.add(6);
		//		itera = lst.listIterator();
		//		itera.next(); 
		//		itera.add(5);
		//		itera.next();
		//		itera.set(4);
		//		for (int i: lst) System.out.println(i);
		//
		//		Map<Integer,Integer> mp = new HashMap<Integer,Integer>(); 
		//		mp.put(1, 2);
		//		mp.put(2, 1);
		//		//		System.out.println(mp.remove(2));
		//		System.out.println(mp.size());
		//		Set<Integer> a = mp.keySet();
		//		Collection<Integer> b = mp.values();
		//		//		q.offer
		//		//		String s = "A R AO+";
		//		//		String[] str = s.split(" ");
		//		//		for (String s1: str) System.out.println(s1);
		//		//
	}

	static String next () throws IOException {
		while (st == null || ! st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	static long readLong () throws IOException {
		return Long.parseLong(next());
	}
	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}
	static char readCharacter () throws IOException {
		return next().charAt(0);
	}  
	static String readLine () throws IOException {
		return br.readLine().trim();
	}

}
