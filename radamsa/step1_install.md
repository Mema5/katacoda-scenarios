In this first step you will learn how to install radamsa. We gave instructions for the three main operating systems but only the lines in the "Linux" section have to be run in the interactive terminal to complete this tuorial.

## Linux
Installing Radamsa on linux is very straightforward, the prerequisites needed are wget, make, git and gcc. If you do not have all of these, install them before continuing the tutorial.

First, download the source code from gitlab by running `git clone https://gitlab.com/akihe/radamsa.git`{{execute}}.

When that is done, move to the newly created `radamsa` directory and make the installation: `cd radamsa && make install`{{execute}}

That's it! The installation of Radamsa should now be done, you can test that everything works fine by hitting `echo "hello" | radamsa -s 3`{{execute}}. The console should output the slightly modified string `hellno`.

## Mac
The installation for mac is a bit different, the easiest way is to use [brew](https://brew.sh/). If you have brew you can use the following command to install Radamsa

`brew install radamsa`

After that the installation should be done and Radamsa can be tested by running `echo "hello" | radamsa -s 3`. The console should output the slightly modified string `hellno`.

If you are one a mac and don't want to use brew there is another way. 
First you need to download the code from gitlab, this can be done with the command

`git clone https://gitlab.com/akihe/radamsa.git`

When that is done, you should move to the newly created Radamsa directory with the command

`cd radamsa`

open the Makefile, which can be found in the Radamsa directory. Find line 38 & 39, which should look like below

```
-mkdir -p $(DESTDIR)$(PREFIX)/bin
cp bin/radamsa $(DESTDIR)$(PREFIX)/bin
```

On line 38 & 39 change the rightmost /bin into /local/bin.

Lastly install Radamsa by running the following command

`sudo make install`

The installation of Radamsa should now be done, you can test it the same way:

`echo "hello" | radamsa -s 3`

## Windows
Installing Radamsa on windows requires [cygwin](https://www.cygwin.com/): download the setup program from their website, run the installer to install cygwin and install the cygwin packages wget, make, git, and clang. For testing purposes for this tutorial wget 1.19.1-2, make 4.3-1, git 2.21.0-1, and clang 8.0.1-1 were used. 

After cygwin and the required packages has been installed, open cygwin and follow the linux part of the installation tutorial to install Radamsa.
