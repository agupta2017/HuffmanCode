import java.util.Iterator;
import java.lang.Iterable;
public class HuffmanNode implements Comparable<HuffmanNode>
{
	protected String value;
	protected int priority;
	protected HuffmanNode left;
	protected HuffmanNode right;
	
	public HuffmanNode(String v, int p, HuffmanNode l, HuffmanNode r)
	{
		value = v;
		priority = p;
		left = l;
		right = r;
	}
	public HuffmanNode(String v, int p)
	{
		value = v;
		priority = p;
		left = null;
		right = null;
	}
	public HuffmanNode()
	{
		value = null;
		left = null;
		right = null;
	}
	
	
	
	public HuffmanNode left()
	{
		return left;
	}
	public HuffmanNode right()
	{
		return right;
	}
	public String value()
	{
		return value;
	}
	public int priority()
	{
		return priority;
	}
	
	
	
	public void setLeft(HuffmanNode node)
	{
		left = node;
	}
	public void setRight(HuffmanNode node)
	{
		right = node;
	}
	public void setValue(String val)
	{
		value = val;
	}
	public void setPriority(int pri)
	{
		priority = pri;
	}
	
	
	
	public boolean isLeaf()
	{
		if(left == null && right == null)
		{
			return true;
		}
		return false;
	}
	
	/*
	public Iterator<E> iterator()
	{
		return inOrderIterator();
	}
	public Iterator<E> inOrderIterator()
	{
		return new InOrderIterator(this);
	}
	public Iterator<E> preOrderIterator()
	{
		return new PreOrderIterator(this);
	}
	public Iterator<E> postOrderIterator()
	{
		return new PostOrderIterator(this);
	}
	*/
	
	public int size()
	{
		if(isLeaf())
		{
			return 1;
		}
		return 1 + left.size() + right.size();
	}
	public int height()
	{
		if(isLeaf())
		{
			return 0;
		}
		int leftH = left.height()+1;
		int rightH = right.height()+1;
		if(leftH > rightH)
		{
			return leftH;
		}
		return rightH;
	}
	public boolean isFull()
	{
		if(isLeaf())
		{
			return true;
		}
		if(right == null || left == null)
		{
			return false;
		}
		if((left.height() == right.height()) && (left.isFull() && right.isFull()))
		{
			return true;
		}
		return false;
	}
	public boolean isBalanced()
	{
		if(isLeaf())
		{
			return true;
		}
		if(right == null)
		{
			return left.isLeaf();
		}
		if(left == null)
		{
			return right.isLeaf();
		}
		if(Math.abs(right.height() - left.height()) > 1)
		{
			return false;
		}
		return right.isBalanced() && left.isBalanced();
	}
	public boolean isComplete()
	{
		if(isLeaf())
		{
			return true;
		}
		if(right == null)
		{
			return left.isLeaf();
		}
		if(left == null)
		{
			return false;
		}
		if(left.height() - right.height() ==1)
		{
			return left.isComplete() && right.isFull();
		}
		if(left.height() == right.height())
		{
			return left.isFull() && right.isComplete();
		}
		return false;
	}
	public String toString()
	{
		if(isLeaf() == true)
		{
			return value + ": " + priority;
		}
		if(right == null)
		{
			return value + ": " + priority + "(" + left.toString() + ")";
		}
		if(left == null)
		{
			return value + ": " + priority + "(" + right.toString() + ")";
		}
		return value + ": " + priority +  "(" + left.toString() + ", " + right.toString() + ")";
	}
	public int compareTo(HuffmanNode other)
	{
		return this.priority() - other.priority();
	}
}
