/**
 * This is a key-value pair. See CollectionOfBooks for usages.
 * In this program, we consider key and value as String as default.
 */
public class KVPair {
    private String key; // stored key
    private String value; // stored value

    /**
     * This constructor pairs a given key and a given value
     * 
     * @param key given key
     * @param value given value
     */
    public KVPair (String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the stored key
     * 
     * @return stored key
     */
    public String getKey () {
        return key;
    }

    /**
     * Returns the stored value
     * 
     * @return stored value
     */
    public String getValue () {
        return value;
    }

    /**
     * When 2+ IDs share the same key, the are placed together inside one String
     */ 
    public void addValue (String newValue) {
        this.value = this.value + " " + newValue;
    }
    
}
