#  [Intro to Programming](index.jsp)

## Why Java?

There are countless different programming languages, each with its own pros
and cons. We're going to focus on Java (Processing is built on top of Java).
What are some of its benefits?

### Compiling is Annoying

Remember that programming languages must be compiled into machine code so that
a computer can understand them. However, different computers understand
different kinds of machine code! A Windows computer understands different
kinds of machine code than a Mac, and even different kinds of Windows
computers can understand different kinds of machine code! So you have to
compile your code over and over again if you want to use it on a different
computer. That's why when you download a program, you usually have to specify
what kind of system you have.

This can be a real pain in the neck, especially if you want to distribute your
programs to other people. Most people don't want to see your code, they just
want a file that they can run, which has to be in a format that their specific
computer can understand. Does your friend have a different kind of computer
than you? If so, you'll have to figure that out, find a compiler for that kind
of computer, compile your code again, and send your friend the compiled file.
Not fun!

How does Java get around this problem?

### Byte Code

**[Byte code](http://en.wikipedia.org/wiki/Java_bytecode)** is code that looks a bit like assembly language, but a computer does not directly read it like assembly language. Instead, another program runs between the byte code and the computer, and that program **interprets** the byte code, creating instructions in machine code that the computer follows. The interpreter program takes the byte code as input and isses commands to the computer in machine code as output. 

Instead of compiling your code directly into machine code or assembly
language, you instead compile your Java code into byte code. You only have to
compile your code one time instead of once for each type of computer you want
to run your code on, because you don't care what type of machine code is
created at the end, as long as the interpreting program can read the byte
code.

### Java Virtual Machine

The program that interprets Java byte code is called the Java Virtural
Machine, or JVM, which is a part of the Java Runtime Environment, or JRE. So,
to send your friend a Java file, you no longer have to compile it specifically
for your friend's computer- you just have to make sure your friend has the
JRE!

Many computers come pre-installed with the JRE, and it's easy to download and
install for computers that don't have it: [http://java.com](http://java.com)

This is known as **platform independence**, because you don't have to worry
about which system your code will run on when writing Java code. Because of
its platform independence, Java's early slogan was [Write Once, Run
Anywhere](http://en.wikipedia.org/wiki/Write_once,_run_anywhere). Although
this is admittedly not perfect, it's much better than requiring a developer to
compile a program specifically for each kind of computer it's going to be run
on - or simply not working on different kinds of computers, which is how many
other languages handle this problem. That's why many programs only work on one
kind of computer.

### Other Benefits of Java

The JRE also automatically handles stuff like memory management- it has a
**garbage collector** that automatically cleans up resources you aren't using
anymore. Without a garbage collector, a programmer has to manually tell the
computer that it can have a piece of memory back every time the program is
done with it. Not only is this tedious, but it's also a major source of bugs-
if you forget to give back all the memory you use, your program can crash.
Java takes care of all of that for you.

The JRE also handles optimization of code, so it automatically figures out the
fastest way to tell the computer what to do based on the byte code it's
running. It can do this with things like [just-in-time
compilation](http://en.wikipedia.org/wiki/Just-in-time_compilation) and
[adaptive optimization](http://en.wikipedia.org/wiki/Adaptive_optimization),
but the real beauty of it is that as a programmer, you don't have to worry
about it.

Finally, because Java byte code is run by the JRE instead of directly by the
computer, there is a sense that Java code is **sandboxed**, or run in an
environment that is kept separate from the rest of the machine. This helps
make Java more secure, and it's why you have to give Java applets permission
to access system resources like files and graphics cards. That doesn't mean
you can't do any damage with a Java program, but it does make it harder for
jerks to use it against you, and harder for you to accidentally mess up
somebody else's computer.

### Isn't Java Slow?

No.

There is a misunderstanding that because of the overhead of the JRE, Java
programs are slow. This stereotype was reinforced by early Java programmers
not really knowing what they were doing- making mistakes like tying up the EDT
can make a Java program appear slow or unresponsive. Also, since many Java
programmers are not web developers, the websites surrounding Java games are
often ugly or very minimalistic, further reinforcing the stereotype that Java
games are bad, without even actually taking the game into consideration!
(Fixing this problem is one of the main reasons I created this site!)

The overhead of the JRE is pretty much a non-issue on modern computers, or
even most older computers. In fact, I would argue that the JRE's memory
management and optimizations, as well as Java's ease of maintainability, not
to mention community, far outweigh any overhead.

### Even More Benefits of Java

  * Java is [object-oriented](http://en.wikipedia.org/wiki/Object-oriented_programming), which fits perfectly with game programming. 
  * Android phones (which run Java) are hugely popular, which has opened up a brand new market for Java games.
  * Java is taught in most high school and college programming classes- this website is designed for people who just started learning Java in school and want to apply that knowledge to game programming.
  * Java is also used in many businesses, so even if you don't become a millionaire from making games, at least you'll be able to find a day job.
  * Finally, Java is also just what I happen to have learned the best, so here we are.

### Hello, Java!

Here's the same "Hello World" program we saw twice before (in assembly
language and C), this time written in Java:

    
    
    public class HelloWorldProgram{
    	public static void main(String... args){
    		System.out.println("Hello world!"); 
    	} 
    } 

### Exercises

  * What is byte code? What is the JRE?
  * Java is an example of an **interpreted language**. Why? 
  * What does "Write Once, Run Anywhere" mean?
  * Why do you have to specify what kind of computer you have when downloading the JRE?
  * When can we not use Java?
  * What does Java's garbage collector do?
  * What are some reasons people think Java is slow? What are some arguments against them?
  * Why are YOU choosing to learn Java?

###  Next: We love Java. So... [Why Processing?](WhyProcessing.jsp)
