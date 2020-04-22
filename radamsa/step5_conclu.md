In this tutorial, we briefly explained what fuzz testing was. We installed the black-box fuzzer Radamsa and played with it in command line. Then we used it to test two java programs: a simple application taking an integer as input and a server waiting for http requests.

## The fuzzing recipe
If there was only one message to remember it would be this one. To make fuzz testing with Radamsa, you need two things:

1. **An input to be fuzzed.** It can be any input in your program: a simple integer or a network request as in this tutorial but also an mp3 or an xml file, a regular expression, an image... From this valid input, Radamsa will generate random mutations.
2. **An undesired behavior to monitor.** You need to know what you don't want to happen, whether it is a crash, an infinite loop, an unhandled exception or an access to protected resources. For most fuzzing campaigns, a sanitizer is used in addition to help detecting errors (like Google [AddressSanitizer](https://github.com/google/sanitizers)).

## Links
Here are some links that we took inspiration from for this tutorial. We invite you to check them if you want more information.

- [Radamsa gitlab repository and readme](https://gitlab.com/akihe/radamsa)
- [Wikipedia page on fuzzing](https://en.wikipedia.org/wiki/Fuzzing)
- A clear and similar tutorial for Radamsa (2020): [How To Install and Use Radamsa to Fuzz Test Programs and Network Services on Ubuntu 18.04](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-radamsa-to-fuzz-test-programs-and-network-services-on-ubuntu-18-04)
- An older yet interesting tutorial (2018): [Radamsa: A general-purpose fuzzer](https://blog.muller.dev/tools/2018/01/05/radamsa.html)
- An other well-known tool for fuzzing: [American fuzzy lop](https://lcamtuf.coredump.cx/afl/)