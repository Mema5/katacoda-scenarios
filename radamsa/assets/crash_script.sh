#!/bin/sh

# Compile the java program if needed
# javac PrimeFactor.java

while true; do
    fuzzed=$(echo "52" | radamsa)
    java PrimeFactor $fuzzed > /dev/null 2>&1 # throw away stdout and stderr
    if [ $? -ne 0 ]; then
        echo "Exception found! Bad input:"
        echo $fuzzed
    fi
done

