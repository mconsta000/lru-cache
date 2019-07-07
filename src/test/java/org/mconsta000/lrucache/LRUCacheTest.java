package org.mconsta000.lrucache;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for LRUCacheTest.
 */
public class LRUCacheTest {

    @Test
    public void testLRUCache() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);
        assertEquals(-1, cache.get(2));
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3)); 
        assertEquals(4, cache.get(4));
    }

    @Test
    public void testAscending() {
        LRUCache cache = new LRUCache(3);

        int data[][] = {{1,1},{2,2},{3,3}};

        for (int i=0; i<data.length; i++){
            cache.put(data[i][0], data[i][1]);
        }

        int vals[][] = cache.getAscending();

        for (int i=0, j=data.length-1; i<vals.length; i++, j--){
            assertEquals(data[j][0], vals[i][0]);
            assertEquals(data[j][1], vals[i][1]);
        }
    }

    @Test
    public void testDescending() {
        LRUCache cache = new LRUCache(3);

        int data[][] = {{1,1},{2,2},{3,3}};

        for (int i=0; i<data.length; i++){
            cache.put(data[i][0], data[i][1]);
        }

        int vals[][] = cache.getDescending();

        for (int i=0,j=0; i<vals.length; i++, j++){
            assertEquals(data[j][0], vals[i][0]);
            assertEquals(data[j][1], vals[i][1]);
        }
    }

    @Test
    public void testUpdate() {
        LRUCache cache = new LRUCache(3);

        cache.put(1,1);

        assertEquals(1, cache.get(1));

        cache.put(1, 2);

        assertEquals(2, cache.get(1));
    }

    @Test
    public void testCapacity() {
        LRUCache cache = new LRUCache(2);

        cache.put(1,1);

        assertEquals(1, cache.get(1));

        cache.put(2, 2);

        assertEquals(2, cache.get(2));

        cache.put(3, 3);

        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));

        cache.put(4,4);

        assertEquals(-1, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    public void testLRUCache2() {
        LRUCache cache = new LRUCache(1000);

        for (int i=0; i<2000; i++) {
            cache.put(i,i);
        }

        assertEquals(-1, cache.get(2));   

        cache.put(1500, 3000);

        assertEquals(3000, cache.get(1500)); 

        cache.put(2000, 0);

        assertEquals(0, cache.get(2000)); 
    }   
}
