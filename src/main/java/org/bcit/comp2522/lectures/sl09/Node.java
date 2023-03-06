package org.bcit.comp2522.lectures.sl09;

public class Node<K, V> {
  Node<K, V> next;
  K key;
  V value;
  Node(K key, V value) {
    this.key = key;
    this.value = value;
  }
}