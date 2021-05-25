package de.maryfro.urlshortenerrestcontroller.cache;

import de.maryfro.urlshortenerrestcontroller.entity.Url;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;

@Component
public class LRUCache {
    final static int cacheSize = 10;
    HashMap<String, Url> map;
    LinkedList<String> list;

    public LRUCache() {
        this.map = new HashMap<>(cacheSize);
        this.list = new LinkedList<>();
    }


    public void put(String key, Url val) {
        if (list.size() == cacheSize) { // check if pruning is needed
            this.prune();
        }
        list.addFirst(key);
        map.put(key, val);
    }


    public Url get(String key) {
        boolean res = list.remove(key); // Remove the data from its location
        if (res) {
            list.addFirst(key); // Add it to the end of the list
            return map.get(key);
        }
        return null;
    }

    // removes the tail
    public void prune() {
        String key = list.removeLast();
        map.remove(key);
    }
}
