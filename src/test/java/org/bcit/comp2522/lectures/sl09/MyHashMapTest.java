package org.bcit.comp2522.lectures.sl09;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {
  MyHashMap mhm;

  @BeforeEach
  void setup() {
    mhm = new MyHashMap<Integer, String>(8);
  }

  @Test
  void hash() {
    assertEquals(0, mhm.hash(0));
    assertEquals(0, mhm.hash(8));
  }

  @Test
  void add() {
    mhm.add(0, "Hello");
    mhm.add(8, "World");
    assertEquals("Hello", mhm.table[0].head.value);
    assertEquals("World", mhm.table[0].head.next.value);
  }

  @Test
  void get() {
    mhm.add(0, "Hello");
    assertEquals("Hello", mhm.get(0));
  }

}
