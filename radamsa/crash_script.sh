#!/bin/sh

# Compile the java program if needed
# javac PrimeFactor.java

while true; do
    fuzzed=$(echo "253" | radamsa)
    java PrimeFactor $fuzzed > /dev/null 2>&1 # throw away stout and sterr
    # '$?' returns the exit code of the last command 
    # list of exit codes: http://tldp.org/LDP/abs/html/exitcodes.html
    if [ $? -ne 0 ]; then
        echo "Crash found! Bad input:"
        echo $fuzzed
    fi
done

