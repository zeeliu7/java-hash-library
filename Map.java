// --== CS400 File Header Information ==--
// Name: Zhonghao Liu
// Email: zliu882@wisc.edu
// Notes to Grader: this program implements the MapInterface interface

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.lang.Math;
import java.lang.reflect.Array;

/**
 * This class includes functions to build a modify a hashtable map. See each method's description for details.
 */
public class Map implements MapInterface {
    private int capacity = 0; // the length of the array
    private int size = 0; // the number of key-value pairs
    private LinkedList[] arr; // the array to store LinkedList<KVPair>

    /**
     * A constructor of Map with default capacity as 10
     */
    public Map() {
        this.capacity = 10;
        this.size = 0;
        arr = new LinkedList[10];

        //initialize each LinkedList<KVPair> in the array
        for (int i = 0; i < capacity; i++) {
            arr[i] = new LinkedList<KVPair>();
        }
    }

    /**
     * Add a key-value pair to the map with hashing, using given key and value
     * 
     * @param key given key
     * @param value given value
     * @return true if successfully added, false if key is null or the key already exists
     */
    public void addPair(String key, String value) {
        // rehash if necessary
        double sizeDouble = (double) size;
        double capDouble = (double) capacity;
        while (((sizeDouble + 1.0) / capDouble) >= 0.85) {
            rehash();
            sizeDouble = (double) size;
            capDouble = (double) capacity;
        }

        // check null
        if (key == null) {
            throw new NoSuchElementException("key is null");
        }

        // check whether it already exists
        boolean exist = false;
        try {
            keyToValue(key);
            exist = true;
        } catch (NoSuchElementException e) {
            //
        }
        if (exist) {
            keyToPair(key).addValue(value);
        } else {
            int hc = Math.abs(key.hashCode());
            int index = hc % capacity;
            arr[index].addFirst(new KVPair(key, value));
            size++;
        }
    }

	/**
     * Get the value of a given key
     * 
     * @param key given key
     * @return the value corresponding to the key
     * @throws NoSuchElementException if the key is null or is not within the map
     */
    public String keyToValue(String key) throws NoSuchElementException {
        // check null
        if (key == null) {
            throw new NoSuchElementException("key is null");
        }

        // check whether key exists
        if (!containsKey(key)) {
            throw new NoSuchElementException("key not found");
        }

        // go to related bucket
        int hc = Math.abs(key.hashCode());
        int index = hc % capacity;

        // search one-by-one
        LinkedList<KVPair> target = arr[index];
        for (int i = 0; i < target.size(); i++) {
            KVPair thisKVPair = target.get(i);
            if (thisKVPair.getKey().equals(key)) {
                return (String) thisKVPair.getValue();
            }
        }

        throw new NoSuchElementException("key not found");
    }

    /**
     * Get the pair of a given key. This is for the addValue() function for KVPair.
     * 
     * @param key given key
     * @return the pair corresponding to the key
     * @throws NoSuchElementException if the key is null or is not within the map
     */
    public KVPair keyToPair(String key) throws NoSuchElementException {
        // check null
        if (key == null) {
            throw new NoSuchElementException("key is null");
        }

        // check whether key exists
        if (!containsKey(key)) {
            throw new NoSuchElementException("key not found");
        }

        // go to related bucket
        int hc = Math.abs(key.hashCode());
        int index = hc % capacity;

        // search one-by-one
        LinkedList<KVPair> target = arr[index];
        for (int i = 0; i < target.size(); i++) {
            KVPair thisKVPair = target.get(i);
            if (thisKVPair.getKey().equals(key)) {
                return thisKVPair;
            }
        }

        throw new NoSuchElementException("key not found");
    }

	/**
     * returns the number of key-value pairs stored in this collection
     * 
     * @return the number of key-value pairs stored in this collection
     */
    public int size() {
        return size;
    }

	/**
     * Checks whether the map contains a given key
     * 
     * @param key given key
     * @return true if it contains, false if not
     */
    public boolean containsKey(String key) {
        // check null
        if (key == null) {
            return false;
        }

        // go to related bucket
        int hc = Math.abs(key.hashCode());
        int index = hc % capacity;

        // check empty bucket
        LinkedList<KVPair> target = arr[index];
        if (target.size() == 0) {
            return false;
        }

        // search one-by-one
        for (int i = 0; i < target.size(); i++) {
            KVPair thisKVPair = target.get(i);
            if (thisKVPair.getKey().equals(key)) {
                return true;
            }
        }

        // not found
        return false;
    }

    /**
     * Clear the hashtable map
     */
    public void clear() {
        for (int i = 0; i < arr.length; i++) {
            arr[i].clear();
        }
        size = 0;
    }


    /**
     * grow by doubling capacity and rehashing, whenever its load capacity becomes greater than or equal to 85%
     */
    private void rehash() {
        // collect all pairs
        KVPair[] allKVPairs = new KVPair[size];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i].size() > 0) {
                allKVPairs[index] = (KVPair) arr[i].removeLast();
                index++;
            }
        }

        // clear old array
        clear();
        size = 0;

        // extend the array by creating a new array of double size
        capacity = 2 * capacity;
        arr = new LinkedList[capacity];
        for (int k = 0; k < capacity; k++) {
            arr[k] = new LinkedList<KVPair>();
        }

        // put the pairs back
        for (int j = 0; j < allKVPairs.length; j++) {
            addPair((String) allKVPairs[j].getKey(), (String) allKVPairs[j].getValue());
        }
    }
}
