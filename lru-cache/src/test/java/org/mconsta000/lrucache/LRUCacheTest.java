package org.mconsta000.lrucache;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class LRUCacheTest {
    /**
     * Rigorous Test.
     */
    @Test
    public void testLRUCache() {
        LRUCache cache = new LRUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        assertEquals(-1, cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        assertEquals(-1, cache.get(1));       // returns -1 (not found)
        assertEquals(3, cache.get(3));       // returns 3
        assertEquals(4, cache.get(4));       // returns 4
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
        LRUCache cache = new LRUCache( 1000 /* capacity */ );

        for (int i=0; i<2000; i++) {
            cache.put(i,i);
        }

        assertEquals(-1, cache.get(2));       // returns -1 (not found)

        cache.put(1500, 3000);

        assertEquals(3000, cache.get(1500)); 

        cache.put(2000, 0);

        assertEquals(0, cache.get(2000)); 
    }   

    // Input
    // ["LRUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
    // [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]

    /*
      [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,24,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,22,18,null,null,null,24,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
      [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,-1,null,null,18,null,null,-1,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,-1,null,null,null,null,29,null,null,null,null,17,22,18,null,null,null,-1,null,null,null,20,null,null,null,-1,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
    */

}
