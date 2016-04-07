import java.lang.Iterable;
import java.util.Iterator;
import java.lang.NullPointerException;
import java.util.NoSuchElementException;

public class Vector<E> implements Iterable<E>
{
	private Object[] data;
	private int size;
	
	/** Default Constructor
	* The default size of the vector is 10
	*/
	public Vector()
	{
		data = new Object[10];
		size = 0;	
	}
	
	/** User input Constructor
	* @param int capacity int that represents the length of the vector
	*/
	public Vector(int capacity)
	{
		if(capacity < 0)
		{
			throw new IndexOutOfBoundsException("You input a negative capacity");
		}
		data = new Object[capacity];
		size = 0;
	}
	
	/** Copy Constructor
	* @param Vector<E> another vector that will be true copied
	*/
	public Vector(Vector<E> other)
	{
		// we need to create a new vector and copy all the data in manually
		if(other == null)
		{
			throw new NullPointerException("You put in a null variable");
		}
		data = new Object[other.data.length];
		for(int i = 0; i < other.size(); i++)
		{
			data[i] = other.data[i];
		}
		size = other.size();
	}
	
	/** 
	* Places the object in the next available slot and increases the size by one
	* @param E that represents the thing you want to place into the vector
	*/
	public void add(E thing)
	{
		this.add(size,thing);
	}
	
	/** 
	* Places the object in the inputed index and moves all the objects right one spot
	* @param int that represents the index
	* @param E that represents the thing you want to place into the vector
	*/
	@SuppressWarnings("unchecked")
	public void add(int index, E thing)
	{
		//checking capacity and if capacity is too small then we grow the array
		if(index > size || index< 0)
		{
			throw new IndexOutOfBoundsException("You tried to add into spot: " + index + " when the size is: " + size);
		}
		if(size >= data.length)
		{
			this.increaseCapacity();
		}
		E hold = (E) data[index];
		E putter = thing;
		while(index<=size)
		{
			data[index] = putter;
			index++;
			if(index <= size)
			{	
				putter = hold;
				hold = (E) data[index];
			}
		}
		size++;	
	}
	
	/**
	* Creates a new array that is double the size.
	* Adds each of the objects in the old array into the new array.
	*/
	private void increaseCapacity()
	{
		Object[] newData = new Object[data.length * 2];
		for( int i = 0; i < size; i ++)
		{
			newData[i] = data[i];
		}
		data = newData;
	}
	
	
	/**
	* Gets the E corresponding to the inputed index
	* @param int index of the E that will be retrieved
	* @return E that is in the spot
	*/
	@SuppressWarnings("unchecked")
	public E get(int index)
	{
		// if the index is out of size then throw an error
		if(index<size && index>=0)
		{
			return (E)data[index];
		}
		throw new IndexOutOfBoundsException("You tried to access  spot: " + index + " when the size is: " + size);
	}
	
	
	/**
	* Removes the E corresponding to the inputed index
	* @param int index of the E that will be retrieved
	* @return E that was previously in the spot
	*/
	@SuppressWarnings("unchecked")
	public E remove(int index)
	{
		//use get to get the thing that will be removed first
		E holder = (E)get(index);
		for ( int i = index; i < size; i++)
		{
			if(i + 1 >= data.length)
			{
				i=size;
			}
			else
			{
				data[i] = data[i+1];
			}
		}
		size--;
		return holder;
	}
	
	/**
	* Removes the first instance of the inputed E
	* @param int index of the E that will be retrieved
	* @return E that was previously in the spot
	*/
	public boolean remove(E obj)
	{
		// use indexOf to find the index of the object
		//use remove to actually get rid of the object
		int index = indexOf(obj);
		if( index == -1 )
		{
			return false;
		}
		remove(index);
		return true;
	}
	
	
	/**
	* Replaces the E at the index with the new E
	* @param int index of the E that will be replaces
	* @param E obj that is replacing the previous E
	* @return E that was previously in the spot
	*/
	@SuppressWarnings("unchecked")
	public E set(int index, E obj)
	{
		//use get to retrieve the current object
		E holder = (E)get(index);
		data[index] = obj;
		return holder;
	}
	
	/*
	* @return int representation of the size which equals the amount of objects in the array
	*/
	public int size()
	{
		return size;
	}
	
	/*
	* Clears all the objects in the array
	*/
	public void clear()
	{
		data = new Object[data.length];
		size = 0;
	} 
	
	/*
	* @return true if the size is 0 otherwise returns false
	*/
	public boolean isEmpty()
	{
		if(size == 0)
			return true;
		return false;
	}
	
	/*
	* @param E item that is looked for in the list
	* @return boolean true if the object is in the list otherwise returns false
	*/
	public boolean contains(E obj)
	{
		//use indexOf
		if (indexOf(obj) == -1)
		{
			return false;
		}
		return true;
	}
	
	/*
	* @return int index of the inputed E
	*/
	@SuppressWarnings("unchecked")
	public int indexOf(E obj)
	{
		//ehhh....
		for(int i = 0; i < size; i++)
		{
			if(data[i] == null && obj == null)
			{
				return i;
			}
			else if(data[i].equals(obj))
			{
				return i;
			}
		}
		return -1;
	}
	
	/*
	* @return String representation of the vector
	*/
	public String toString()
	{
		String s = "[ ";
		int x;
		for(x = 0; x < size-1 ; x++)
		{
			s += data[x] +  ", ";
		}
		s +=  data[x] + "]";
		return s;
	}
	
	/*
	* @return Iterator that goes through the vector sequentially
	*/
	public Iterator<E> iterator()
	{
		return new VectorIterator<E>(this);
	}
	
}
