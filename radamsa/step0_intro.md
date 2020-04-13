In this tutorial, you will learn how to use the open-source project Radamsa to generate new tests to check the security and the robustness of applications.

## Don’t fuzz with me!
Fuzzing (or fuzz testing) helps developers to find bugs or security threats in their programs. It automates the creation of random inputs to monitor **how the program reacts to unexpected, invalid or even malicious data**. The main idea behind fuzzing is to create random but meaningful data i.e. “valid enough” not to be directly rejected but instead showcase unexpected behavior such as crashes, memory leaks, failing assertions or access to unauthorized resources. 

*Exemple: if my program `primefactor` takes an integer as input and outputs its prime factor decomposition, I expect a good fuzzer to produce edge case inputs.*

Good test inputs:

```primefactor 24645342962386
primefactor 10000000000000000000000000000000    # integer overflow
primefactor -3                                  # negative numbers
primefactor 42 546                              # unexpected argument
primefactor "I’m a integer"                     # wrong argument``` 

Fuzz testing is really used in practice to find weaknesses in security-critical programs. For example, the main web browsers (Google Chrome, Firefox, Internet Explorer…) are known to be constantly running fuzzing campaigns with thousands of nodes on their products. Radamsa, the fuzzer presented in this tutorial, has helped finding non-trivial issues in them and in other real-world programs (see [their readme](https://gitlab.com/akihe/radamsa#some-known-results) for a list of discovered vulnerabilities).

## Radamsa: a black-box fuzzer
Radamsa is defined by its developers as “extremely black-box”. It means that it requires no assumption on the program structure but only tests in a clever way its usual input. That makes it a very light tool, easy to install and script. At the end of this tutorial you will have played with it and learnt about its capacities.
