## What is Programming?

Before learning how to program, it's probably a good idea to define what
programming actually is in the first place. We'll also go over some basics of
how a computer works. I'd recommend reading this before diving into the
programming tutorials, that way programming is less of a magic black box to
you. You don't necessarily need to become an expert on this stuff in order to
program, but being aware of what's going on behind the scenes when you run
your program is a good idea.

### What's a program?

First off, we can define a **program** as a set of instructions that a
computer follows to accomplish some task. The programs most people are
familiar with involve graphical user interfaces, or GUIs, which include
buttons and ways for a user to interact and provide input. But many programs
are run via the command prompt without a gui. Other programs are run
completely behind the scenes with no direct user input. Some examples might be
firefox, or a word processor, or even pieces of the underlying operating
system that handle things like displaying your icons and figuring out what to
do when you click somewhere.

### How do programs tell the computer what to do?

Computers don't understand words (at least not directly), but they can
associate numbers with more meaningful concepts- concepts such as a piece of
data, or an operation that allows the computer to manipulate data. A computer
uses numbers to stand for things like the letter C or the color red, or the
act of adding two numbers or saving something to a memory address. At the
lowest level, a program is just a series of numbers representing data and
instructions on what to do with that data.

Different numbers represent different instructions or pieces of data, and
different numbers can mean different things to different computers. These
numbers are called **machine code**.
[Here](http://en.wikipedia.org/wiki/Machine_code#Example) are some example
instructions in machine code, if you're curious about what they look like.

### Binary

This is just a side note, but since binary is a pretty misunderstood topic
among non-programmers, I thought I should include a mini-lesson on it here.

Humans are used to representing numbers in decimal, which is base 10. That
means that each individual digit can hold 0-9, and each digit, or "place",
represents a power of ten- so a number has a place for ones, tens, hundreds,
thousands, etc. The decimal number 6732 contains 6 thousands (10^3), 7
hundreds (10^2), 3 tens (10^1), and 2 ones (10^0).

However, computers use binary, which is just base-2, to represent numbers.
That means that each individual digit can hold a 0 or a 1, and each place
represents a power of two- ones, twos, fours, eights, sixteens, etc. The
binary number 10110 contains 1 sixteens (2^4), 0 eights (2^3), 1 fours (2^2),
1 twos (2^1), and 0 ones (2^0). So binary 10110 is just 22 in decimal (16+4+2
= 22).

There are a number of reasons that computers store numbers as binary, the
simplest being it's easiest to represent binary electronically. You can think
of binary as a series of ONs and OFFs instead of 1s and 0s- similar to morse
code. Morse code is easy to transmit, you just have to convert it to something
that humans can understand. Similarly, a series of ONs and OFFS is easy to
transmit (or store somewhere like on a harddrive, on a cd, or in memory), it
just has to be converted to something a computer can understand.

How a computer converts a binary number into something meaningful depends on
the situation. Different numbers can mean differething things to different
computers, or even different programs. So it's almost impossible to convert a
set of binary digits into instructions or data without knowing the context in
which they were generated.

For example, a common way to represent text is via
[unicode](http://en.wikipedia.org/wiki/List_of_Unicode_characters), which is
just a mapping of numbers to letters. In unicode, the number 65 represents the
letter 'A'. 65 in binary is 1000001. So a program using unicode would
translate the number 1000001 into an A for printing or displaying purposes. A
different program might translate 65 completely differently!

Binary is not magic, it's just a way for computers to easily represent
numbers. Computers represent everything as a number, so everything is
represented as binary. But the mapping from a number to something more
meaningful depends on the context.

### From Machine Code to Assembly Language

Writing a program by manually typing out machine code (which is just a series
of binary numbers) is much too tedious. Instead, it's usually possible to
subsitute words in for the numbers- this is called **[assembly
language](http://en.wikipedia.org/wiki/Assembly_language)**, because the words
are assembled directly into machine code. Like machine code, different types
of computers have different assembly languages- for example, computers with an
x86 processor understand [x86 assemble
language](http://en.wikipedia.org/wiki/X86_assembly_language). For a list of
the instructions, or "words" that map directoy to numbers, available in x86
assembly language, check out
[this](http://en.wikipedia.org/wiki/X86_instruction_listings) page if you're
curious. Assembly language allows a person to read a program without needing
to guess what the numbers mean. For example, this program written in assembly
language makes the computer print out "Hello World!"

    .data
    hello_msg:   .asciiz "Hello World!\n";
    .text
    main:              
    la $a0, hello_msg
    li $v0, 4
    syscall
    li $v0, 10
    syscall

A computer can directly translate the above text to machine code (which is
just numbers) and carry out the commands, and a human can still sort of read
it, at least more easily than a human can read binary. But that is still
ridiculously ugly and makes me want to cry.

### From Assembly Language to Compilable Languages

Assembly language is definitely better than machine language, but it's still
really low-level. The instructions are tiny, such as shifting data around to
specific places in memory. While it's possible to write a full program in
assembly language (or even machine code, if you're a crazy person), that's
going to be really tedious and an inefficient use of your time.

That's where the idea of a **compiler** comes into play. A compiler is itself
a computer program that takes as input a file written in a particular
**programming language** that a human can understand and creates as output
another file in assembly language or machine code that the computer can
understand. Not only does this make reading the code easier, but a compilable
programming language does not have a 1:1 relationship to machine code. That
means that a single instruction in a given programming language might map to
hundreds of lines of instructions in machine code. Much less tedious!

There are many different programming languages, each with its own advantages
and disadvantages for different circumstances. For example, the below code
(which is written in the C programming language) can be compiled into machine
code that looks more like the above program and that the computer can
understand and run.

    #include iostream
    int main()
    {
    std::cout << "Hello World!" << std::endl;
    return 0;
    }				

Although still not English, the above code is much more readable than assembly
langauge or machine code. You could even describe each line in English by
saying something like "include input-output stream, and when the main program
is run, send 'Hello World!' followed by an endline to the standard console
output." You don't really have to worry about the details here, but the idea
is that a programmer can write this code, then compile it so that a computer
can run it. We can take a closer look at each of these steps:

### Writing Code

The first step is to actually write the code. This can be done in a basic text
editor like notepad, just like you would write anything else. But there are
other editors specifically designed for writing code, such as
[jEdit](http://www.jedit.org/) or [Notepad++](http://notepad-plus-plus.org/),
which do things like automatically indent your code and color it so it's
easier to read. You probably wouldn't want to use something like Word, but
only because it would try to enforce incorrect spelling of programming words
and awkward indentation.

There are also more advanced editors called Integrated Developer Environments,
or IDEs, which handle all kinds of things from project management to automatic
code generation. Beginners should probably stay away from these and instead
use a basic editor until they're really familiar with what's going on, because
using an IDE too early can make a programmer overly-dependent on the IDE's
features. There is nothing quite as pathetic as watching an "experienced"
programmer that can't program without using an IDE. Don't be that person! Use
a basic editor for now! But if you really want to check them out,
[eclipse](http://www.eclipse.org/downloads/) and
[NetBeans](http://netbeans.org/) and their little brother
[JCreator](http://www.jcreator.com/) are some popular IDEs.

### Compiling Code

When you have some code written, you then compile it into something the
computer can execute. You do this by running the compiler, which is usually
done via the command line. The compiler takes your source code file as input
and outputs another file that the computer can run. IDEs and some basic
editors usually have a button that automatically runs the compiler with your
source code file, but all it's doing is running the compiler for you.

Oftentimes when you compile your code, the compiler will give you an error
message- this is just the compiler's way of telling you that the code doesn't
make sense. The [debugging](DebuggingProcessing.jsp) tutorials cover this in
more detail, but you can think of it as what happens when you make a typo. The
first draft of your code almost always contains a compiler error, just like
the first draft of a paper you write will almost always contain a typo. Doing
things like breaking your code up into smaller chunks and compiling as often
as possible can help with this, but fixing compiler errors is a part of every
programmer's daily life, so get used to it now!

### Running Code

After you compile your code into a file that the computer can run, you
probably actually want to run that file! How you do this depends on which
language you used, and we'll cover it more in later tutorials.

And that's it! Some languages are slightly different, but the process is
pretty much the same. Write some code, compile it, and see if it does what you
want. If it doesn't, you go back and figure out why not.

### Things to Think About

  * How does a computer work? What is a program?
  * What is a compiler? What does it take as input? What does it create as output?
  * Why do we usually use compilable languages instead of assembly language? When can we not use a compilable language?
  * Why do you think humans use the decimal system instead of binary, or base 7 or 13, or some other numerical system?
  * How do you represent zero through sixteen in binary? How do you represent binary 11001 in decimal?
  * Do you know of any programming languages already? What have you heard about them?

###  Next: With so many different languages, [Why Java?](WhyJava.jsp)

