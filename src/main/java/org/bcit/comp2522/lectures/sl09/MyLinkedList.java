package org.bcit.comp2522.lectures.sl09;

public class MyLinkedList<K, V> {
  Node<K, V> head;

  void add(K key, V value) {
    Node<K, V> temp = new Node<K, V>(key, value);
    if (head == null) {
      head = temp;
    } else {
      Node<K, V> curr = head;
      while(curr.next != null) {
        curr = curr.next;
      }
      curr.next = temp;
    }
  }

  V get(K key) {
    Node<K, V> cur = head;
    while (cur != null) {
      if (cur.key == key) {
        return cur.value;
      }
    }
    return null;
  }
}
