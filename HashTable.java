// Akshina Gupta
// 9/29/2015
// This tries to place objects into an array at a constant speed
// Hash Table Version 0

import java.util.Map;

public class HashTable<K,V>
{
	private Entry[] table;
	private double loadFactor = 0.6;
	private int numObjects;
	
	/** Default Constructor
	* The default size of the hashtable is 100
	*/
	public HashTable()
	{
		table = new Entry[100];
		numObjects = 0;
	}
	
	/** User input Constructor
	* @param capacity int that represents the length of the hashtable
	*/
	public HashTable(int capacity)
	{
		table = new Entry[capacity];
		numObjects = 0;
	}
	
	/** 
	* Attempts to place the object as quickly as possible
	* Uses Objects hashcode() method to generate a hash of the object
	* If that hash spot is empty then it places it into that spot in the table
	* Otherwise it puts the object in the next available spot
	* @param obj Object that represents the thing you want to place into the hash table
	*/
	public void put(K key, V value)
	{
		Entry e = new Entry(key,value);
		numObjects++;
		if (((numObjects*1.0)/(table.length)) >= loadFactor)
		{
			this.rehash();
		}
		int spot = (Math.abs(key.hashCode())) % (table.length);
		if(table[spot] == null)
		{
			table[spot] = e;
		}
		else
		{
			spot = (spot+1)%(table.length);
			//int x = 1;
			while(table[spot] != null)
			{
				spot = (spot+(1))%(table.length);
				//x=x*2;
			}
			table[spot] = e;
		}
	}
	
	/**
	* @return String representation of the array contains the toString() of every single object in the array
	*/
	public String toString()
	{
		String s = "";
		for(int x = 0; x < table.length;x++)
		{
			s += " " + table[x];
		}
		return s;
	}
	
	/**
	* Removes the value corresponding to the inputed key
	* Rehashes the next slots until it comes to an empty slot.
	* @param key of the Entry that will be removed
	* @return Value of the Entry corresponding to that key, returns null if the key is not in the hashTable
	*/
	public V remove(K key)
	{
		int spot = (key.hashCode()) % (table.length);
		int start = spot;
		V value = null;
		if (table[spot] != null && ((K)(table[spot].key)).equals(key))
		{
			value = (V)(table[spot].value);
			table[spot] = null;
		}
		else
		{
			spot = (spot+1)%(table.length);
			while(table[spot] != null)
			{
				if(((K)(table[spot].key)).equals(key))
				{
					value = (V)(table[spot].value);
					table[spot] = null;
				}
				spot = (spot+(1))%(table.length);
			}	
		}
		if (value == null)
		{
			return null;
		}
		Entry e;
		while(table[spot] != null)
		{
			e = table[spot];
			table[spot] = null;
			this.put((K)(e.key),(V)(e.value));
			spot = (spot+1)%(table.length);
		}
		return value;
	}
	
	/**
	* Gets the value corresponding to the inputed key
	* Rehashes the next slots until it comes to an empty slot.
	* @param key of the Entry that will be retrieved
	* @return Value of the Entry corresponding to that key, returns null if the key is not in the hashTable
	*/
	public V get(K key)
	{
		int spot = (key.hashCode()) % (table.length);
		int start = spot;
		if(table[spot] != null && ((K)(table[spot].key)).equals(key))
		{
			V value = (V)(table[spot].value);
			return value;
		}
		spot = (spot+1)%(table.length);
		while(table[spot] != null)
		{
			if(((K)(table[spot].key)).equals(key))
			{
				V value = (V)(table[spot].value);
				return value;
			}
			spot = (spot+(1))%(table.length);
		}
		return null;
	}
	
	/**
	* Checks if the key is in the hash table
	* @param key that is searched for in the HashTable
	* @return boolean returns true if the key is in the HashTable and false otherwise
	*/
	public boolean containsKey(K key)
	{
		int spot = (key.hashCode()) % (table.length);
		int start = spot;
		if(table[spot] != null && ((K)(table[spot].key)).equals(key))
		{
			return true;
		}
		spot = (spot+1)%(table.length);
		while(table[spot] != null)
		{
			if(((K)(table[spot].key)).equals(key))
			{
				return true;
			}
			spot = (spot+(1))%(table.length);
		}
		return false;
	}
	
	/**
	* Liner probes the array until it comes across the designated value
	* @param value that is being searched for in the array
	* @return boolean returns true if the value is in the table and false otherwise
	*/
	public boolean containsValue(V value)
	{
		for(int i = 0; i<table.length; i++)
		{
			if (((V)(table[i].value)).equals(value))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	* Creates a new array that is double the size.
	* Hashes each of the objects in the old array into a new array.
	* Hashes in sequential order.
	*/
	private void rehash()
	{
		Entry[] oldTable = table;
		table = new Entry[table.length * 2];
		for(int x = 0; x < oldTable.length;x++)
		{
			if(oldTable[x] != null)
			{
				this.put((K)(oldTable[x].key),(V)(oldTable[x].value));
			}
		}
	}
	
	/**
	* Creates an association between K and V so they can be stored together 
	* and both can be accessed from the Hash table
	*/
	private class Entry<K,V>
	{
		public K key;
		public V value;
		
		public Entry(K k, V v)
		{
			key = k;
			value = v;
		}
		
		public String toString()
		{
			String s = key.toString() + value.toString();
			return s;
		}
	}
	
}
