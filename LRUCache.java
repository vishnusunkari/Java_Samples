import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
 
class Node{
	int key;
	int value;
	Node prev;
	Node next;
 
	public Node(int key, int value){
		this.key = key;
		this.value = value;
	}
}

public class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private Node oldest;
    private Node latest;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.oldest = new Node(0, 0);
        this.latest = new Node(0, 0);
        this.oldest.next = this.latest;
        this.latest.prev = this.oldest;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        return -1;
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void insert(Node node) {
        Node prev = latest.prev;
        Node next = latest;
        prev.next = next.prev = node;
        node.next = next;
        node.prev = prev;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        insert(newNode);

        if (map.size() > capacity) {
            Node lru = oldest.next;
            remove(lru);
            map.remove(lru.key);
        }
    }
    


	public static void main(String[] args) throws java.lang.Exception {
		LRUCache.LRUCach lrucache = new LRUCache.LRUCach<>(4);
		lrucache.put(1, 100);
		lrucache.put(10, 99);
		lrucache.put(15, 98);
		lrucache.put(10, 97);
		lrucache.put(12, 96);
		lrucache.put(18, 95);
		lrucache.put(1, 94);
 
		System.out.println(lrucache.get(1));
		System.out.println(lrucache.get(10));
		System.out.println(lrucache.get(15));
		lrucache.put(15, 98);
		System.out.println(lrucache.get(15));
 
	}



static class LRUCach<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCach(int capacity) {
        // initialCapacity, loadFactor, accessOrder = true
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    // Remove eldest entry if size exceeds capacity
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    // Just for demonstration
    public void printCache() {
        System.out.println(this);
    }
}

}
