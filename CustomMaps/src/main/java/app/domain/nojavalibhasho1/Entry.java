package app.domain.nojavalibhasho1;

class Entry<K, V> {

    final K key;

    V value;

    Entry<K, V> next;

    public Entry(K key, V value, Entry<K, V> next) {

        this.key = key;

        this.value = value;

        this.next = next;

    }

    // getters, equals, hashCode and toString

}