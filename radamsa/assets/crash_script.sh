#!/bin/sh

# Compile the java program if needed
# javac PrimeFactor.java

while true; do
    fuzzed=$(echo "52" | radamsa)
    echo Input string: \"$fuzzed\"
    java PrimeFactor $fuzzed > /dev/null 2>&1 # throw away stdout and stderr
    if [ $? -ne 0 ]; then
        echo Exception found!
    else
        echo No exception found.
    fi
    echo
done

