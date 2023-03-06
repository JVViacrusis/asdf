package org.bcit.comp2522.lectures.sl09;

import java.util.Iterator;

public class MyHashMap <K, V> {
  MyLinkedList<K, V>[] table;
  int count;

  double loadFactorThreshold;

  MyHashMap() {
    this.table = new MyLinkedList[8];
    for (int i = 0; i < table.length; i++) {
      table[i] = new MyLinkedList<K, V>();
    }

    this.loadFactorThreshold = 0.5;
    this.count = 0;
  }

  MyHashMap(int length) {
    this.table = new MyLinkedList[length];
    for (int i = 0; i < table.length; i++) {
      table[i] = new MyLinkedList<K, V>();
    }

    this.loadFactorThreshold = 0.5;
    this.count = 0;
  }

  MyHashMap(int length, double LFThreshold) {
    this.table = new MyLinkedList[length];
    for (int i = 0; i < table.length; i++) {
      table[i] = new MyLinkedList<K, V>();
    }

    this.loadFactorThreshold = LFThreshold;
    this.count = 0;
  }

  void add(K key, V value) {


    if ((double) count / table.length > loadFactorThreshold) {
      rehash();
    }
    table[hash(key)].add(key, value);
    count++;
  }

  V get(K key) {
    return table[hash(key)].get(key);
  }

  int hash(K key) {
    return Math.abs(key.hashCode() % table.length);
  }

  void rehash() {
    MyLinkedList<K, V>[] temp = new MyLinkedList[table.length * 2];
    for (int i = 0; i < temp.length; i++) {
      temp[i] = new MyLinkedList<K, V>();
    }


    for (MyLinkedList<K, V> curLL : table) {

      Node<K, V> cur = curLL.head;
      while(cur != null) {
        temp[hash(cur.key)].add(cur.key, cur.value);
        cur = cur.next;
      }
    }
    table = temp;
  }
}
