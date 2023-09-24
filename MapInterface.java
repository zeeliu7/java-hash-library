import java.util.NoSuchElementException;

/**
 * This interface models a hashtable map
 */
public interface MapInterface {
	/**
     * Add a key-value pair to the map with hashing, using given key and value
     * 
     * @param key given key
     * @param value given value
     * @return true if successfully added, false if key is null or the key already exists
     */
    public void addPair(String key, String value);

    /**
     * Get the value of a given key
     * 
     * @param key given key
     * @return the value corresponding to the key
     * @throws NoSuchElementException if the key is null or is not within the map
     */
    public String keyToValue(String key) throws NoSuchElementException;

    /**
     * Get the pair of a given key. This is for the addValue() function for KVPair.
     * 
     * @param key given key
     * @return the pair corresponding to the key
     * @throws NoSuchElementException if the key is null or is not within the map
     */
    public KVPair keyToPair(String key) throws NoSuchElementException;

    /**
     * returns the number of key-value pairs stored in this collection
     * 
     * @return the number of key-value pairs stored in this collection
     */
    public int size();

    /**
     * Checks whether the map contains a given key
     * 
     * @param key given key
     * @return true if it contains, false if not
     */
    public boolean containsKey(String key);
	
}
