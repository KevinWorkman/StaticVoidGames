#  [Basic Java](index.jsp)

## Java Setup

One of the nice things about Processing is how little setup it requires. You
download Processing, type your program, and hit play. Java is much more
advanced, so its setup is a little more involved. Don't worry though, it's
only a few steps, which I'll outline here.

Keep in mind that programming in Java consists of three main steps: writing
your code and saving it to a .java file, compiling your .java file into
bytecode (which is saved as a .class file), and then running the .class file
to run your program.

### The JRE vs the JDK

You'll hear these terms a lot while working in Java, so it's important to know
the difference.

  * The JRE is the Java Runtime Environment, and it comes with the tools required to run class files.
  * The JDK is the Java Development Kit, and it comes with the tools required to create class files.
  * The JDK includes the JRE, but non-developers only need the JRE, so it's available standalone.

### Step 1: Download the JDK

Since you're a developer, you need the JDK and not just the JRE. You can get
the JDK
[here](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
Just get the latest version of the JDK- not the JRE, and not the netbeans
package.

Download the JDK and install it to whatever directory you want.

### Step 2: Set your PATH

We're going to be working with the command prompt, so we need to set the PATH
on your computer. Don't get this confused with the terms CLASSPATH or
JAVA_HOME, which are used for different purposes. The only thing setting the
PATH does is to tell your operating system where to look for programs, so you
don't have to give the full path every time you want to use something.

To set the path on Windows 7, go to the control panel, then to System, and
then to "Advanced system settings". In the window that pops up, click the
"Environment Variables..." button, which pops up yet another window. In that
window, find the "Path" variable, click on it, then click the "Edit" button
for one final window. Add the full path of the bin folder to your Path, so it
looks something like this (notice the semicolons):

C:\WINDOWS\system32;C:\WINDOWS;C:\Program Files\Java\jdk1.7.0\bin

Once you've set your path, click okay to close all of the windows. To make
sure you set it correctly, open up a command prompt and type "javac -version"
without the quotes and press enter. If you get an error message about javac
not being a recognized command, you haven't set your path correctly.

### Step 3: Write some code!

Open up a basic text editor like Notepad or [jEdit](http://www.jedit.org/) and
paste in the following code:

    
    
    public class HelloWorld{
    	public static void main(String[] args){
    		System.out.println("Hello, world!");
    	}
    }
    

It's okay if you don't know what all of the code means at this point, we're
just stretching our legs.

Save that into a file named HelloWorld.java. It's important to name the file
the same as the class name.

### Step 4: Compile

Once you have a .java file saved, the next step is to compile it into
bytecode. You do this using the javac tool, which comes with the JDK.

To use the javac tool, open up a command prompt, then navigate to the location
of HelloWorld.java using the [cd](http://ss64.com/nt/cd.html) command. Type
"javac HelloWorld.java" without the quotes and then press enter. If you've
done everything correctly up until this point, you should not get any errors.

What just happened? You ran the javac tool, which took the HelloWorld.java
file as input and created another file named HelloWorld.class, which is now
located next to your HelloWorld.java file.

### Step 5: Run

The .class file you just created is called bytecode, and it's what the JRE
runs. To run the .class file, type "java HelloWorld" without the quotes and
hit enter. Notice that you omit the .class part!

This tells the JRE to take the .class file (which contains Java bytecode) and
run it, which prints out a message to the command prompt. Congratulations, you
just ran your first Java program!

### Exercises

  * What is the difference between the JRE and the JDK?
  * What is the difference between a .java file and a .class file?
  * What is a command prompt? What does the PATH value do?

###  Next: Let's take a closer look at [Hello World](HelloWorld.jsp)
