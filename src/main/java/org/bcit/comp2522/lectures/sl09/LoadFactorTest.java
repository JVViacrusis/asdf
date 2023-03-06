package org.bcit.comp2522.lectures.sl09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class LoadFactorTest {

  public static void main(String[] args) {
    Tester t = new Tester();
//    System.out.println(t.testNSituations(100, 5, Enums.TEST_TYPE.SEQUENTIAL, 50, 0.5, Enums.INITIAL_STATE.EMPTY));
    t.testMySituations();
  }


  /*

  What I have control over:
    = hmap init size
    = hmap LFThreshold
    = hmap initState (empty, filled)
    = setMap range (how many KVPairs)
    = setMap type (seq, rev-seq, random)

    - size 8, thresh 0.5, empty, set,

  Add with no conflict
    = start with empty hash
    = length 100
    - 123...100



   */

}
