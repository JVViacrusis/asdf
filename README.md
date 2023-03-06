# Lab 09

Today is about making your own load-balancing HashMap structure and testing it. As per the last few labs, this is an individual repo, but I expect you to work with your project teams.

## TODO 1: Copy in your lecture code
You will each have written possibly slightly different lecture code. Take a moment to decide who will import their code into this lab, and discuss any differing design decisions. Make note of them: you may want to change to a different design by the end of lab.

## TODO 2: Plan your testing regime
The idea will be to create the most efficient HashMap under certain conditions. However, what those conditions actually are may vary. Design different load tests and plan them on paper. Remember these different conditions:
- Add with no conflict
- Add with conflict
- Add with rehash
- Get with no conflict
- Get with conflict

For each of these, you'll want to design a variety of load tests, starting with trivial and moving towards high "stress". Keep in mind the following ideas:
- Adding a bunch of keys that have sequential hashes
- Adding a bunch of keys that have reverse-sequential hashes
- Adding a bunch of keys at random
- Adding a bunch of keys to fill up the HashMap, and then move to random

## TODO 3: Using `System.nanoTime()`, run your stress tests on a variety of parameters
Determine the best parameters for your HashMap. What should the load factor be before rehashing? What are the best, worst, and average case times? Be prepared to show these tests and discuss your design decisions in the next lab.

## TODO 4: Determine any data structures you need in your project
Draft a data structure that you can use in your project. It doesn't need to be as complicated as a hand-built HashMap, but it should be built to be aware of the kind of loads you'll put on it for your system. Draft that and be prepared to chat about it next week.

## TODO 5: Finish your individual pitches
Add your individual pitch to your group project repo. What will you do differently than everyone else for the project? How will you contribute your 1000 lines of code? Converge on your specific contributions and write a short pitch which describes the part of the project that you will own. Include a UML diagram for the classes that you will add (or have added) and will own for the rest of the project.
