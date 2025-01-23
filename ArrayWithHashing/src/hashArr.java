import java.util.AbstractMap;
import java.util.Map;

/**
 * Array with hashing implementation Author: Noah Bennett
 */
public class hashArr {

    public static Map.Entry<Integer, String>[] table;
    public static int size;

    //constructor
    public hashArr(int size) {
        this.size = size;
        this.table = new Map.Entry[size];
    }

    //hash function
    int hash(int v, int j) {

        return (v + j) % this.size;
    }

    //Inserts a pair into the has array
    public void insert(int key, String value) {
        Map.Entry<Integer, String> entry = new AbstractMap.SimpleEntry<>(key,
                value);
        int j = 0;
        int index = this.hash(key, j);

        //until you find an open address, keep updating j
        while (this.table[index] != null && j < this.size) {
            j++;
            index = this.hash(key, j);
        }

        //set the new open address to the hash value
        this.table[index] = entry;

    }

    //retrieve and return a pair from the hash array
    public Map.Entry<Integer, String> retrieve(int key, String value) {
        int j = 0;
        int index = this.hash(key, j);

        //until you find the array keep updating j
        while (this.table[index].getValue() != value && j < this.size) {
            j++;
            index = this.hash(key, j);
        }

        //return the pair
        return this.table[index];
    }

    //check if the key and the string are a member of the hash array
    public boolean member(int key, String value) {
        Map.Entry<Integer, String> entry = new AbstractMap.SimpleEntry<>(key,
                value);
        int j = 0;
        int index = this.hash(key, j);

        //until or if you find the value return true if the index isn't null
        while (j < this.size && this.table[index] != null) {
            if (this.table[index].getValue().equals(entry.getValue())) {
                return true;
            }
            j++;
            index = this.hash(key, j);
        }
        return false;
    }

    //delete the pair from the hash array
    public boolean delete(int key, String value) {
        int j = 0;
        int index = this.hash(key, j);

        //until you find the index loop
        while (this.table[index].getValue() != value && j < this.size) {
            j++;
            index = this.hash(key, j);
        }

        //delete the pair if you found it and return true, else return false
        if (this.table[index].getValue().equals(value)) {
            this.table[index] = null;
            return true;
        }

        return false;
    }

}