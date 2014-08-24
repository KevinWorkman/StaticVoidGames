#  [Basic Java](index.jsp)

## Strings

By now you should know how to compile a .java file into a .class file, and how
to run that .class file on the command prompt. You've written a Hello World
program, and should understand most of what is going on in it. You should also
understand the basics of Objects. You should have worked with Strings in
Processing, including the concatenation operator (the plus sign). If you
haven't, go back and review the tutorials, or this won't make any sense!

Consider this example program:

    
    
    public class Main{
    	public static void main(String[] args){
    		String message = "Hello, world!";
    		System.out.println(message);
    	}
    }
    

You should be able to guess what this program does. Run it on the command line
to test your guess! We're going to use it to introduce some of the more
advanced concepts involved with Strings that we skipped over in Processing.

### Strings are Objects

Unlike ints and doubles, which are primitive values, Strings are objects. In
other words, a String value is an instance of the String class. The String
class contains a bunch of useful functions that you can use on your String
values. For example, this modified program uses the substring() function:

    
    
    public class Main{
    	public static void main(String[] args){
    		String message = "Hello, world!";
    		System.out.println(message.substring(3, 9));
    	}
    }
    

The substring() function cuts a String at the supplied indexes. There are
plenty of other functions in the String class. Check out the [String
API](http://docs.oracle.com/javase/7/docs/api/java/lang/String.html) for more
details.

### Strings are Immutable

Strings are immutable, which just means that the state of an instance of
String can't be changed.

That might seem confusing, but it can be easily demonstrated. What do you
expect this program to print out?

    
    
    public class Main{
    	public static void main(String[] args){
    		String message = "Hello, world!";
    		message.substring(3, 9);
    		System.out.println(message);
    	}
    }
    

You might expect it to print out "lo, wo" because of the substring() function.
Instead, it prints out "Hello, world!" in its entirety. What's going on?

Since Strings are immutable, the substring() function (and every other String
function) doesn't actually change the String value itself. Instead, String
functions return a new String with the modified value. For example:

    
    
    public class Main{
    	public static void main(String[] args){
    		String message = "Hello, world!";
    		String cutMessage = message.substring(3, 9);
    		
    		System.out.println(message);
    		System.out.println(cutMessage);
    	}
    }
    

You can reassign a String variable to point to a different String value,
including what's returned from the previous value of the variable. Like this:

    
    
    public class Main{
    	public static void main(String[] args){
    		String message = "Hello, world!";
    		
    		System.out.println(message);
    		
    		message = message.substring(3, 9);
    		
    		System.out.println(message);
    	}
    }
    

The important thing to remember is that Strings are immutable, which means
String functions don't actually modify a String, they return a new String.

### String Conversion

We're about to start working with user input, which will be in the form of a
String (such as keyboard input). But oftentimes we want to convert from a
String to a number. How do we do this?

Each primitive type has a corresponding wrapper class that, amongst other
things, contains static functions that convert a String into that type of
value. For example, this program converts a String to an int and then does
some basic calculations with that value:

    
    
    public class Main{
    	public static void main(String[] args){
    		
    		String input = "27";
    		
    		int convertedInput = Integer.valueOf(input);
    		
    		int dogAge = convertedInput * 7;
    		
    		System.out.println("Age in dog years: " + dogAge);
    	}
    }
    

### Object Equality

Remember that we can check for equality between primitive values using the ==
operator:

    
    
    public class Main{
    	public static void main(String[] args){
    		
    		int x = 10;
    		int y = 10;
    		
    		if(x == y){
    			System.out.println("X and Y are equal.");
    		}
    		else{
    			System.out.println("X and Y are NOT equal.");
    		}
    	}
    }
    

However, when dealing with objects instead of primitives, the == operator does
something a bit different. Consider this code:

    
    
    
    public class StringHolder {
    	private String s;
    	
    	public StringHolder(String s){
    		this.s = s.toUpperCase();
    	}
    	
    	public String getString(){
    		return s;
    	}
    }
    
    

This very simple Object holds a single String, which it obtains by
capitalizing the String passed into the constructor. What would it mean for
two instances of StringHolder to be equal? Let's write some code to test it
out:

    
    
    public class Main{
    	public static void main(String[] args){
    		
    		StringHolder x = new StringHolder("testing");
    		StringHolder y = new StringHolder("testing");
    		
    		if(x == y){
    			System.out.println("X and Y are equal.");
    		}
    		else{
    			System.out.println("X and Y are NOT equal.");
    		}
    	}
    }
    

What do you expect this code to print out? You might expect it to print out "X
and Y are equal." because they both contain the same String. However, Java
isn't smart enough to know that two StringHolders are equal if the Strings
they contain are the same. Instead, you have to write an equals() method
inside your StringHolder class that does that logic for you:

    
    
    public class StringHolder {
    	private String s;
    	
    	public StringHolder(String s){
    		this.s = s.toUpperCase();
    	}
    	
    	public String getString(){
    		return s;
    	}
    	
    	public boolean equals(StringHolder other){
    		return s == other.s;
    	}
    }
    

And we can use that equals() method in our Main class:

    
    
    public class Main{
    	public static void main(String[] args){
    		
    		StringHolder x = new StringHolder("testing");
    		StringHolder y = new StringHolder("testing");
    		
    		if(x.equals(y)){
    			System.out.println("X and Y are equal.");
    		}
    		else{
    			System.out.println("X and Y are NOT equal.");
    		}
    	}
    }
    

If you run that code, you'll see that the program still prints out "X and Y
are NOT equal." Why is this? This is the important bit: Strings are objects,
so when comparing two Strings for equality, you have to use their equals()
method, not the == operator! Your StringHolder class should actually look like
this:

    
    
    public class StringHolder {
    	private String s;
    	
    	public StringHolder(String s){
    		this.s = s.toUpperCase();
    	}
    	
    	public String getString(){
    		return s;
    	}
    	
    	public boolean equals(StringHolder other){
    		return s.equals(other.s);
    	}
    }
    

Finally, your program should work exactly as you expect.

### Exercises

  * Write a program that reverses a String, without using the StringBuilder class. Hint: check out the String API for useful functions!
  * Write a program that takes a String that contains a paragraph and properly capitalizes it. Words at the beginning of sentences should be capitalized, words in the middle of sentences should not be.

###  Next: [User Input](UserInput.jsp)
