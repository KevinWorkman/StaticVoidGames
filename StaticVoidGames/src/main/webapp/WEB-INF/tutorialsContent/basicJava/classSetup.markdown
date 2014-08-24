#  [Basic Java](index.jsp)

## Class Setup

By now you should know how to compile a .java file into a .class file, and how
to run that .class file on the command prompt. You've written a Hello World
program, and you should understand the basics of what it's doing.

This tutorial goes into more detail on how to use classes in Java.

As a reminder, this is the code that we're running:

    
    
    public class HelloWorld{
    	public static void main(String[] args){
    		System.out.println("Hello, world!");
    	}
    }
    

This code defines one class named HelloWorld, which has to be in a file named
HelloWorld.java. The class contains a single main method, which Java
automatically calls when you tell it to run the compiled version of this
class. In that method, we pass a single String argument to the println()
method of out, which is a static variable in the System class.

### Predefined Classes

Java has a ton of predefined classes with predefined methods, and much of your
code will involve using them. Our above example uses the System class, which
contains a variable named out, which contains a method named println().

To become more familiar with Java's predefined classes, your new best friend
is [the API](http://docs.oracle.com/javase/7/docs/api/). If you're confused
about how a predefined class works, this should be your first stop. (It's my
default first tab in firefox!)

For example, we can use the predefined StringBuilder class to reverse a
String:

    
    
    public class StringReverser {
    	public static void main(String[] args){
    		String reverseMe = "abcdefghijklmnopqrstuvwxyz";
    
    		StringBuilder sb = new StringBuilder(reverseMe);
    		sb.reverse();
    
    		String reversed = sb.toString();
    
    		System.out.println("Before: " + reverseMe);
    		System.out.println("After: " + reversed);
    	}
    }
    

This code defines a class named StringReverser, which contains a main()
method. In the main() method, a String variable named reverseMe is declared
and initialized with a String literal containing the alphabet. That String is
passed into the constructor for StringBuilder, and that instance of
StringBuilder is pointed to by a variable named sb. The code then calls the
reverse() method from the instance that sb points to. Then it calls the
toString() method from that instance and saves the returned String in the
reversed variable. Finally, the program prints out the String originally
passed into the constructor as well as the String returned by the toString()
method after calling the reverse() method.

### Defining Classes

We know how to define and run a single class, but most programs will require
more than one class. Remember that to define a single class, you put the class
in a .java file that has the same name as the class. For example, the above
StringReverser class must be in a file named StringReverser.java.

You define other classes in exactly the same way. Let's start with an example
class that holds a String and its reversal:

    
    
    public class StringReverser{
    	private String original;
    	private String reversed;
    	
    	public StringReverser(String reverseMe){
    		StringBuilder sb = new StringBuilder(reverseMe);
    		sb.reverse();
    		reversed = sb.toString();
    		original = reverseMe;
    	}
    
    	public String getOriginal(){
    		return original;
    	}
    	
    	public String getReversed(){
    		return reversed;
    	}	
    }
    

This class must be saved to a file named StringReverser.java. You can compile
it exactly like you have been, by typing "javac StringReverser.java" on the
command line. But notice that this class does not have a main() method, so you
can't run it! For that, we need another class that contains a main() method
and uses our StringReverser class:

    
    
    public class Main{
    	public static void main(String[] args){
    		StringReverser srOne = new StringReverser("This is text to be reversed.");
    		StringReverser srTwo = new StringReverser("abcdefghijklmnopqrstuvwxyz");
    		
    		System.out.println(srOne.getOriginal());
    		System.out.println(srOne.getReversed());
    		
    		System.out.println(srTwo.getOriginal());
    		System.out.println(srTwo.getReversed());
    	}
    }
    

The Main class must be saved to a file named Main.java. You can compile it by
typing "javac Main.java" on the command line, and assuming that
StringReverser.java is in the same directory, that file will also be compiled.

Once you've compiled your classes, you can run the Main class by typing "java
Main", and the main() method will be called.

There are other ways to define multiple classes, but for now just put each in
its own file.

### Packages

When you're dealing with a bunch of classes, you can put them into different
packages to keep everything organized. You can think of a package like a
directory of files, and in fact that's exactly what they are. For example,
let's organize our above two classes into the following file hierarchy:

  * ExampleProject 
    * util 
      * StringReverser.java
    * control 
    * commandLine 
      * Main.java

From the top-level directory of your project, we have two folders: util and
control. The util folder contains the StringReverser class, and control
contains another directory named commandLine, which contains our Main class.
In the future (after we learn how to make windows and buttons), we might also
put a directory in the control folder named applet or application. For now,
we'll stick with the command line.

Now that our classes are inside package folders, we have to add some code that
lets them know. Here are the updated classes:

    
    
    package util;
    
    public class StringReverser{
    	private String original;
    	private String reversed;
    	
    	public StringReverser(String reverseMe){
    		StringBuilder sb = new StringBuilder(reverseMe);
    		sb.reverse();
    		reversed = sb.toString();
    		original = reverseMe;
    	}
    
    	public String getOriginal(){
    		return original;
    	}
    	
    	public String getReversed(){
    		return reversed;
    	}	
    }
    

The only difference in the StringReverser class is the package declaration at
the very top. If your class is in a package, the package declaration needs to
be the first line in your class.

And this is the updated Main class:

    
    
    package control.commandLine;
    
    public class Main{
    	public static void main(String[] args){
    		util.StringReverser srOne = new util.StringReverser("This is text to be reversed.");
    		util.StringReverser srTwo = new util.StringReverser("abcdefghijklmnopqrstuvwxyz");
    		
    		System.out.println(srOne.getOriginal());
    		System.out.println(srOne.getReversed());
    		System.out.println(srTwo.getOriginal());
    		System.out.println(srTwo.getReversed());
    	}
    }
    

Take a look at the package declaration at the top. Notice that the package
directories are separated by periods instead of slashes.

The second thing to notice is that we now have to use StringReverser's package
whenever we use the class name. Since StringReverser is in a different package
than our Main class, we have to tell it exactly where to look.

### Import Statements

It can be pretty cumbersome to always explicitly use the package for every
class we refer to. To make our lives a little easier, we can use import
statements to tell Java what packages the classes we want to use are located.
We can rewrite out Main class to use an import statement:

    
    
    package control.commandLine;
    
    import util.StringReverser;
    
    public class Main{
    	public static void main(String[] args){
    		StringReverser srOne = new StringReverser("This is text to be reversed.");
    		StringReverser srTwo = new StringReverser("abcdefghijklmnopqrstuvwxyz");
    		
    		System.out.println(srOne.getOriginal());
    		System.out.println(srOne.getReversed());
    		System.out.println(srTwo.getOriginal());
    		System.out.println(srTwo.getReversed());
    	}
    }
    

Notice the import statement just after the package declaration. This import
statement tells Java "whenever I refer to StringReverser, this is the package
I want you to go to for it." Now when we use the StringReverser class in the
code, we don't have to use the package with it.

Keep in mind that an import statement doesn't actually add anything to your
class. It's just a way to avoid using the full package every time you use a
class in your code.

You can have as many import statements as you want.

### Compiling

To compile the above program, you should direct your command line to the top-
level directory (ExampleProject), then type "javac
control/commandLine/Main.java" without the quotes.

### Exercises

  * What happens if you have two classes with the same name that are in two different packages? If you import both, which class is used?

###  Next: More info on [Strings](Strings.jsp)

