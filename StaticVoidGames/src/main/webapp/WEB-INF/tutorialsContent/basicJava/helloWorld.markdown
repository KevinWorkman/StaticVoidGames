#  [Basic Java](index.jsp)

## Hello World

By now you should know how to compile a .java file into a .class file, and how
to run that .class file on the command prompt. You've written a Hello World
program, but we haven't explained what any of that meant. Let's do that now!

This tutorial relies on concepts introduced in Processing, including access
modifiers and static versus non-static members. If you aren't familiar with
those, then go back in the tutorial before starting this one!

As a reminder, this is the code that we're running:

    
    
    public class HelloWorld{
    	public static void main(String[] args){
    		System.out.println("Hello, world!");
    	}
    }
    

Let's take this one line at a time:

  * The first line defines a public (meaning anybody can access it) class called HelloWorld. In Java, almost everything has to be inside a class.
  * The second line defines a static method (which means you don't have to create an instance of the class to access it) named main, which takes one argument named args, which is an array of Strings. More on this variable in a minute.
  * Inside the main method, we call System.out.println(), which is a method that outputs the supplied String to the command prompt.

It's okay if you don't really understand all of this yet. Let's go into more
detail:

### Classes

Remember that a class defines a blueprint for creating an Object. In this
example, we have on class called HelloWorld, which contains a single method
named main.

### The main() Method

When you run a class, Java looks for a static void main(String[] args) method,
just like Processing looks for a setup() function and a draw() function.

The method has to be static, which means that it does not require a particular
instance of the class to be called. This makes sense, because before the
main() method is called, we haven't created any instances of anything yet!

### System.out.println()

Meet your new best friend. This function simply prints a line out to the
console, which will become extremely useful for debugging purposes.

The syntax might look a little strange compared to Processing's println()
function, but we can take it apart:

  * System is a predefined class in Java.
  * The System class has a static variable named out, which holds an object that allows printing to the console. 
  * This object that out points to has several functions, including println(). The println() function simply prints the supplied value to the console.

You can use System.out.println() without fully understanding the above
concepts, but they'll become more obvious to you as you get more comfortable
with objects.

### String[] args

The array of Strings passed into the main method are command-line arguments
(that's why most people name the variable args). They are passed into the
program by the person running it (that's you!), and can be used as a quick way
to provide user input. For example, we can create a program that just prints
out the arguments:

    
    
    public class HelloWorld{
    	public static void main(String[] args){
    		System.out.println("Printing command-line arguments:");
    		for(int i = 0; i < args.length; i++){
    			System.out.println("Args[" + i + "]: " + args[i]);
    		}
    		System.out.println("Done.");
    	}
    }
    

Compile this class exactly like you did before. But now when you run the
program, you should also supply command line arguments. You supply command
line arguments by listing them after the class name, separated by spaces. For
example, type this into your command prompt and press enter:

java HelloWorld testing abc 123

Your program should print out each of the above command line arguments.

### Exercises

  * Write a program that prints out "Hello World" if no command line arguments are specified, or if the user does enter a command line argument, the program uses that as a name. For example, passing in the name "Stanley" as a command line argument will cause the program to print "Hello, Stanley!"
  * Write a program that randomly chooses a command line argument and prints it out. For example, passing in burrito, sandwich, and salad will print "burrito" 1/3 of the time, "sandwich" 1/3 of the time, and "salad" 1/3 of the time. You can use this program to make your mind up when you can't decided something!
  * What happens if you forget to make main static? What happens if you forget the String[] args argument? What happens if you run a class that doesn't have a main method?

###  Next: [Class Setup](ClassSetup.jsp)
