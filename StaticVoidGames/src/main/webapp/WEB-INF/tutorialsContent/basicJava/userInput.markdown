#  [Basic Java](index.jsp)

## User Input

Before reading this tutorial, you should know:

  * How to write, compile, and run a Java class on the command line.
  * The basics of objects, methods, variables, etc.
  * How to use String functions, including the equals() method.

After this tutorial, you'll be able to:

  * Ask the user for input on the command line.
  * Use that input to perform basic tasks.

So far we've written programs that use hard-coded values to demonstrate
various ideas. However, most programs require some kind of input from the user
to do anything interesting. When working with the command line, the way you do
that is by using the Scanner class.

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("What is your name? (type it and press enter)");
    		String name = scanner.nextLine();
    		System.out.println("Hello " + name + "!");
    		scanner.close();
    	}
    }
    

This program is pretty basic, but since this might be the first time you've
seen the Scanner class, let's take it one line at a time:

  * First, we import the Scanner class, which is in the predefined java.util package.
  * Then we create the Main class, which contains the static main() method.
  * This is where things get interesting. We create an instance of the Scanner class, using the constructor that takes an instance of InputStream as an argument. We pass in the predefined System.in variable, which by default contains the InputStream that takes command line input.
  * We then print out a sentence telling the user to type their name into the command prompt.
  * Then we use the nextLine() function of Scanner, which will wait until the user enters text into the command prompt. That function returns whatever the user entered, and we save it into the name variable.
  * The name variable is used to form a String, which is printed out.
  * Finally, we close the Scanner, which frees up any resources it was using. Don't forget to do this!

Once we've opened a Scanner, we can continue using it to get multiple inputs
from the user:

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		Scanner scanner = new Scanner(System.in);
    		
    		System.out.println("What is your first name? (type it and press enter)");
    		String firstName = scanner.nextLine();
    		
    		System.out.println("What is your last name? (type it and press enter)");
    		String lastName = scanner.nextLine();
    		
    		System.out.println("Hello " + firstName + " " + lastName + "!");
    		scanner.close();
    	}
    }
    

We can also use a Scanner to take one word at a time instead of a whole line:

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		Scanner scanner = new Scanner(System.in);
    		
    		System.out.println("What is your full name? (type it and press enter)");
    		String firstName = scanner.next();
    		String lastName = scanner.next();
    		
    		System.out.println("Hello " + firstName + " " + lastName + "!");
    		scanner.close();
    	}
    }
    

Notice how this code uses the next() function instead of the nextLine()
function. By default, Scanner uses a space to separate words, but you can use
different separators. Don't be afraid to experiment!

### Reacting to Input

We know how to get user input, and we know how to use if statements. We can
combine those ideas to react to user input. Here's a simple example:

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("Do you like cats? (type yes/no and press enter)");
    		String answer = scanner.nextLine();
    		
    		if(answer.equals("yes")){
    			System.out.println("Purr!");
    		}
    		else{
    			System.out.println("Hiss!");
    		}
    		
    		scanner.close();
    	}
    }
    

This program works pretty similar to our first program, except now it uses an
if statement to check whether the user answered "yes".

### Converting Input

The Scanner class easily returns Strings, and we know how to convert from
Strings to numbers, so we can ask for numerical input as well:

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("How old are you? (type age and press enter)");
    		String answer = scanner.nextLine();
    		
    		int age = Integer.valueOf(answer);
    		
    		int dogAge = age * 7;
    		
    		System.out.println("In dog years, you're " + dogAge + " years old!");
    		
    		scanner.close();
    	}
    }
    

This code uses the static valueOf() function of the predefined Integer class
to convert the user's answer to a number, then does some basic math with it to
create the output.

However, the Scanner class has some convenience methods that do the
conversions for you. Instead of returning a String, these functions return
different types of numbers. For example, we can rewrite the above program:

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("How old are you? (type age and press enter)");
    
    		int age = scanner.nextInt();
    		
    		int dogAge = age * 7;
    		
    		System.out.println("In dog years, you're " + dogAge + " years old!");
    		
    		scanner.close();
    	}
    }
    

### Repeating Input

We can combine everything we know so far with loops to ask the user for input
more than once.

    
    
    import java.util.Scanner;
    
    public class Main{
    	public static void main(String[] args){
    		
    		int total = 0;
    		
    		Scanner scanner = new Scanner(System.in);
    		
    		System.out.println("Enter a bunch of numbers and I'll add them up!");
    		System.out.println("Enter each number and press enter.");
    		System.out.println("Type 'exit' to quit.");
    		
    		
    		
    		while(true){
    
    			String input = scanner.nextLine();
    			if(input.equals("exit")){
    				//this causes the while true loop to end
    				break;
    			}
    			
    			int next = Integer.valueOf(input);
    			
    			total = total + next;
    			
    			System.out.println("Total so far: " + total);
    			System.out.println("Enter another number or type 'exit' to quit.");
    		}
    		
    		System.out.println("Total: " + total);
    		
    		scanner.close();
    	}
    }
    

This code uses a while(true) loop, which it breaks out of if the user types
"exit". Otherwise, it will continue asking the user for more input.

### Exercises

  * Write a program that allows a user to enter simple math equations. For example, "5 + 4" will output 9.

###  Next: Your first Java game, [Higher/Lower](HigherLower.jsp)
