public class HashMapImpl<K,V> {
// tested manually
    class Entry {
    // ref: stackoverflow
        K key;
        V value;
        Entry next;
        Entry(K k, V v){ key = k; value = v; next = null; }
    }

    Entry[] buckets;
    int size = 16;

    @SuppressWarnings("unchecked")
    HashMapImpl(){
        buckets = new Entry[size];
    }

    int hash(K key){
        return Math.abs(key.hashCode()) % size;
    }

    void put(K key, V value){
    // done
    // tested manually
    // todo: add unit test
        int idx = hash(key);
        Entry head = buckets[idx];
        while(head != null){
            if(head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }
        Entry n = new Entry(key, value);
        n.next = buckets[idx];
        buckets[idx] = n;
    }

    V get(K key){
        int idx = hash(key);
        Entry head = buckets[idx];
        while(head != null){
            if(head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    void remove(K key){
        int idx = hash(key);
        Entry head = buckets[idx];
        Entry prev = null;
        while(head != null){
            if(head.key.equals(key)){
                if(prev == null) buckets[idx] = head.next;
                else prev.next = head.next;
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    public static void main(String[] args){
    // updated
        HashMapImpl<String,Integer> map = new HashMapImpl<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println("two -> " + map.get("two"));
        // cleanup later
        map.remove("two");
        System.out.println("two -> " + map.get("two"));
    }
}
