package org.bcit.comp2522.lectures.sl09;

import java.util.ArrayList;
import java.util.Random;

public class Tester {

  class setGetTime {
    public long setTime;
    public long getTime;
    setGetTime(long set, long get) {
      setTime = set;
      getTime = get;
    }

    @Override
    public String toString() {
      return "set:" + setTime + ", get:" + getTime;
    }
  }

  class mmaSetGet {
    long fastestSet;
    long fastestGet;

    long slowestSet;
    long slowestGet;

    long totalSet;
    long totalGet;

    int countSet;
    int countGet;

    int sitInitSize;
    int sitThresh;

    mmaSetGet() {
      fastestSet = 999999999;
      fastestGet = 999999999;

      slowestSet = 0;
      slowestGet = 0;

      totalSet = 0;
      totalGet = 0;

      countSet = 0;
      countGet = 0;

      sitInitSize = -1;
      sitThresh = -1;
    }

    public void addSet(long set) {
      if (set > slowestSet) {
        slowestSet = set;
      }
      if (set < fastestSet) {
        fastestSet = set;
      }

      totalSet += set;
      countSet++;
    }

    public void addGet(long get) {
      if (get > slowestGet) {
        slowestGet = get;
      }
      if (get < fastestGet) {
        fastestGet = get;
      }

      totalGet += get;
      countGet++;
    }

    public long avgSet() { return totalSet / countSet; }
    public long avgGet() { return totalGet / countGet; }

    @Override
    public String toString() {
      return "fastestSet:" + fastestSet + ", "
              + "fastestGet:" + fastestGet + ", "
              + "slowestSet:" + slowestSet + ", "
              + "slowestGet:" + slowestGet + ", "
              + "avgSet:" + this.avgSet() + ", "
              + "avgGet:" + this.avgGet();

    }
  }

  class Situation {
    int testRepetitions;
    int testKeyRange;
    Enums.TEST_TYPE tType;
    int hInitSize;
    double hThresh;
    Enums.INITIAL_STATE hInitState;

    Situation(int testRepetitions,
              int testKeyRange,
              Enums.TEST_TYPE tType,
              int hInitSize,
              double hThresh,
              Enums.INITIAL_STATE hInitState) {
      this.testRepetitions = testRepetitions;
      this.testKeyRange = testKeyRange;
      this.tType = tType;
      this.hInitSize = hInitSize;
      this.hThresh = hThresh;
      this.hInitState = hInitState;
    }

    @Override
    public String toString() {
      return "Situation{" +
              "testRepetitions=" + testRepetitions +
              ", testKeyRange=" + testKeyRange +
              ", tType=" + tType +
              ", hInitSize=" + hInitSize +
              ", hThresh=" + hThresh +
              ", hInitState=" + hInitState +
              '}';
    }
  }

  Tester(){}
  public setGetTime testSituation(int testKeyRange,
                                  Enums.TEST_TYPE tType,
                                  int hInitSize,
                                  double hThresh,
                                  Enums.INITIAL_STATE hInitState) {

    ArrayList<Integer> keys = new ArrayList<>();

    switch (tType) {
      case SEQUENTIAL:
        for (int i = 0; i < testKeyRange; i++) {
          keys.add(i);
        }
        break;
      case REVERSE_SEQUENTIAL:
        for (int i = 0; i < testKeyRange; i++) {
          keys.add(testKeyRange - i);
        }
        break;
      case RANDOM:
        Random gen = new Random();
        for (int i = 0; i < testKeyRange; i++) {
          keys.add(gen.nextInt(0,hInitSize));
        }
    }

    MyHashMap hm = new MyHashMap(hInitSize, hThresh);
    if (hInitState == Enums.INITIAL_STATE.FILLED) {
      for (int i = 0; i < hInitSize; i++) {
        hm.add(i, 123);
      }
    }

    // set keys
    long setStart = System.nanoTime();
    for (Integer key : keys) {
      hm.add(key, 123);
    }
    long setEnd = System.nanoTime();

    // get keys
    long getStart = System.nanoTime();
    for (Integer key : keys) {
      hm.get(key);
    }
    long getEnd = System.nanoTime();

    setGetTime situationResults = new setGetTime(setEnd-setStart, getEnd-getStart);
    return situationResults;
  }

  public mmaSetGet testNSituations(Situation s) {
    mmaSetGet result = new mmaSetGet();


    for (int i = 0; i < s.testRepetitions; i++) {
      setGetTime curRep = testSituation(s.testKeyRange, s.tType, s.hInitSize, s.hThresh, s.hInitState);
      result.addSet(curRep.setTime);
      result.addGet(curRep.getTime);
    }

    return result;
  }

  public void testMySituations() {
    ArrayList<Situation> situations = new ArrayList<>();
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 50, 0.50, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 100, 0.50, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.RANDOM, 125, 0.50, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 50, 0.75, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 100, 0.75, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.RANDOM, 125, 0.75, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 50, 1, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 100, 1, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.RANDOM, 125, 1, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 50, 1.25, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 100, 1.25, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.RANDOM, 125, 1.25, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 50, 0.50, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 100, 0.50, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.SEQUENTIAL, 125, 0.50, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 50, 0.75, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 100, 0.75, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.SEQUENTIAL, 125, 0.75, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 50, 1, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 100, 1, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.SEQUENTIAL, 125, 1, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 50, 1.25, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 100, 1.25, Enums.INITIAL_STATE.EMPTY));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.SEQUENTIAL, 125, 1.25, Enums.INITIAL_STATE.EMPTY));

    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 50, 0.50, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 100, 0.50, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.RANDOM, 125, 0.50, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 50, 0.75, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 100, 0.75, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.RANDOM, 125, 0.75, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 50, 1, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 100, 1, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.RANDOM, 125, 1, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 50, 1.25, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.RANDOM, 100, 1.25, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.RANDOM, 125, 1.25, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 50, 0.50, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 100, 0.50, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.SEQUENTIAL, 125, 0.50, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 50, 0.75, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 100, 0.75, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.SEQUENTIAL, 125, 0.75, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 50, 1, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 100, 1, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.SEQUENTIAL, 125, 1, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 50, 1.25, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 100, Enums.TEST_TYPE.SEQUENTIAL, 100, 1.25, Enums.INITIAL_STATE.FILLED));
    situations.add(new Situation(2000, 30, Enums.TEST_TYPE.SEQUENTIAL, 125, 1.25, Enums.INITIAL_STATE.FILLED));


    for (Situation s : situations) {
      System.out.println(s + ": " + testNSituations(s).toString());
    }


  }

  public static void main(String[] args) {
    Tester t = new Tester();
//    System.out.println(t.testNSituations(100, 5, Enums.TEST_TYPE.SEQUENTIAL, 50, 0.5, Enums.INITIAL_STATE.EMPTY));
    t.testMySituations();
  }

}
