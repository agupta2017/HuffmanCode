import java.util.PriorityQueue;
import java.util.Map;

public class HuffmanTree
{
	//Since I don't need all of the functionality of a map I am simply going to be using HashTable as my map
	//Since it has the necessary functions of a HashMap but not the things that involve sets and enumerations and etc.
	private HashTable<Character,Integer> m;
	private PriorityQueue<HuffmanNode> p;
	private HuffmanNode root;
	
	public HuffmanTree(String s)
	{
		//I have a vector of characters so I can sequentially go through all of the characters
		//for when I am populating the priorityQueue
		//Since Map only allows key to value access, I cannot iterate through so I am iteratively collecting the keys
		//so i will be able to iterate through
		//The map will simply be altered using pass by reference
		Vector<Character> c = populateHashTable(s);
		populatePriorityQueue(c);
		populateTree();
	}
	
	public HuffmanNode getTree()
	{
		return root;
	}
	
	//Takes in your orignal string and is going to find the frequencies and populates the HashMap
	//I picked HashMap because it runs in constant time and TreeMap runs in log(n) time so HashMap is more efficient
	//There also should not be a lot of collisions because we are inputing different characters each time
	private Vector<Character> populateHashTable(String s)
	{
		m = new HashTable<Character,Integer>();
		char[] arr = s.toCharArray();
		Vector<Character> listOfChars = new Vector<Character>();;
		for(char c : arr)
		{
			if(m.containsKey(c))
			{
				int i = m.remove(c);
				i++;
				m.put(c,i);
			}
			else
			{
				m.put(c,1);
				listOfChars.add(c);
			}
		}
		return listOfChars;
	}
	
	//This is going to take the iterable version of the map keys and iterates through them
	//For each character create a Huffman Node
	//with a string version of the caracter (because it will need to be concatenated)
	//and the priority you stored in the HashMap
	private void populatePriorityQueue(Vector<Character> vect)
	{
		p = new PriorityQueue<HuffmanNode>();
		for(char c : vect)
		{
			HuffmanNode h = new HuffmanNode(c+ "", m.get(c));
			p.offer(h);
		}
	}
	
	//This is going to remove the first 2 items and then concatenate them into a new node 
	//and offer them back to the priority queue
	//When there is only one thing left in the queue then it will set root to this last item
	private void populateTree()
	{
		while(p.size() > 1)
		{
			HuffmanNode firstHolder = p.poll();
			HuffmanNode secondHolder = p.poll();
			HuffmanNode newNode = new HuffmanNode(firstHolder.value() + "" + secondHolder.value(), firstHolder.priority() + 0 + secondHolder.priority());
			newNode.setLeft(firstHolder);
			newNode.setRight(secondHolder);
			p.offer(newNode);
		}
		root = p.poll();
	}
}
