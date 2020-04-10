Now that you have radamsa installed on your machine, let’s start playing with it and see all the fuss it can create. 

By default, the tool will consume the standard input (stdin) from the terminal and inject some random mutations in it. That’s exactly what we did before when testing if radamsa was well installed. The terminal command `echo` prints the string that follows in stdin. The `|` operator takes this output and feeds it to `radamsa`. 

You can also try:
`echo "What do you do, radamsa?" | radamsa` {{execute}}

You will observe some random modifications in the output. Feel free to run it several times to get an idea of what sort of weird stuff radamsa is capable of. 

You can also tell it to run `n` times from the same input:
    echo "What do you do, radamsa?" | radamsa -n 5

The random generator inside radamsa can be initialized with a seed in order to give consistent executions. For example, running two times this command will give the same output:
echo "What do you do, radamsa?" | radamsa -n 5 -s 53637

Some outputs give us some hints about what radamsa tries to achieve. Let’s comment on them.

    WWhat do you do, radamsa?
    Wh‌at do you do,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, radamsa?
Those outputs are a slight mutation from the original string. It always has the chance to find some unhandled character or pattern in the parsing rules or some overflow.

What do you do, radamsa?
What do you do, radamsa?
What do you do, radamsa?
This is only one output. Radamsa has tripled the line of the original input.

    What d ᅠo        
                                                                                                                                                                                                                                                                                                                                                                        you do, radamsa?
This one is interesting, as it plays with space characters, carriage return and new line, often troublesome in programs.

    What do you%n!xcalc�5530413�--6803452o, radamsa?
Finally, this last output features a kind of injection in the string, which can be interesting to prevent the program from eventual attacks. 

For more information on all radamsa can do and what is behind, check the gitlab repository.
