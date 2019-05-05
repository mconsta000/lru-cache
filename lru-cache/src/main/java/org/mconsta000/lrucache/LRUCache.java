package org.mconsta000.lrucache;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * LRU Cache implimentation
 */
public final class LRUCache {
    private BlockingQueue<Integer> queue = null;
    private HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    private int capacity = 0;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        queue = new ArrayBlockingQueue<Integer>(this.capacity);
    }
    
    public int get(int key) {
        Integer ret = map.get(key);
        if (ret == null)
            ret = -1;
        else {
            queue.remove(key);
            queue.offer(key);
        }
        return ret; 
    }
    
    public void put(int key, int value) {
        if (queue.contains(key)) {
            queue.remove(key);
        } else if (queue.size() == capacity) {
            Integer lru = queue.poll();
            map.remove(lru);
        }
        map.put(key, value);
        queue.offer(key);
    }
}
