#  [Basic Concepts](index.jsp)

## Colors

We've covered a few different types, such as int and float, that you must use
to declare function return types, function parameters, and variables. They all
have similar syntax (think of syntax as the rules that specify how a language
works, including programming languages) and how they are used all looks pretty
similar:

    
    
    int x = 7;
    float y = 4.5;
    double x = y * 2;
    

But there are other types that do not hold numbers and don't have the same
kind of syntax. We've covered Strings that hold text. Another type with
strange sytnax is the color type, which holds (surprise!) colors.

The color type represents colors, and how Processing handles colors is pretty
unique. You'll probably use colors extensively, so it's worth going over their
weird syntax.

Note: Processing handles colors differently than how pure Java handles colors.
The java.awt.Color class is not used; instead, Processing does a lot of data
handling behind the scenes. If you have no idea what I'm talking about, that's
okay, but this is one thing that might confuse Java developers trying to learn
Processing.

### Integer Notation

You can get by without ever using the color type, since most functions that
take a color type argument are also overloaded (there is another function with
the same name that takes different arguments) to take number values. For
example, the background() function sets the background color, and the fill()
sets the color that shapes are filled with:

    
    
    void draw() {
    
    	background(0, 0, 0);
    	
    	fill(255, 0, 0);
    	ellipse(25, 50, 10, 10);
    	
    	fill(0, 255, 0);
    	ellipse(50, 50, 10, 10);
    	
    	fill(0, 0, 255);
    	ellipse(75, 50, 10, 10);
    }
    

Both functions can take 3 numbers with values from 0 to 255, representing red,
green, and blue, respectively. However, it's often easier to use the color
type instead of keeping track of 3 separate values.

### Web Color Notation

Instead of using 3 separate number values, you can use web color notation to
refer to a color using only one value. Web color notation looks like a #
symbol, followed by 6 digits from 0-9 or A-F.

    
    
    void draw() {
    
    	background(#000000);
    	
    	fill(#ff0000);
    	ellipse(25, 50, 10, 10);
    	
    	fill(#00ff00);
    	ellipse(50, 50, 10, 10);
    	
    	fill(#0000ff);
    	ellipse(75, 50, 10, 10);
    }
    

You can think about web color notation in three sections (minus the leading
#). The first two characters specify the red of the color; the middle two
characters specify the green of the color; and the last two characters specify
the blue of the color.

So the color red is specified by #FF0000 because it contains the maximum (FF)
red, but no blue or green. The color blue is specified by #0000FF because it
contains maximum blue, but no red or green.

You can try different combinations (for example, #FF1493 is deep pink), or you
can check out W3Schools for more
[colors](http://www.w3schools.com/html/html_colors.asp), a color
[picker](http://www.w3schools.com/tags/ref_colorpicker.asp), and even a color
[mixer](http://www.w3schools.com/tags/ref_colormixer.asp).

### Hexadecimal

If you're curious about why web color notation takes the form it does, you
should learn more about hexadecimal. If you don't care, feel free to skip the
next few paragraphs!

You're probably used to the decimal system, or base 10, where each digit (or
"place") of a number can contain 10 values: 0 through 9. In decimal, each
"place" of a number represents a power of 10. So with the number 1,039, there
are 9 "ones" (10 raised to the power of 0), 3 "tens" (10 raised to the power
of 1), 0 "hundreds" (10 raised to the power of 2), and 1 "thousands" (10
raised to the power of 3). It seems obvious, but to get the value of the
number we take (1 * 1000) + (0 * 100) + (3 * 10) + (9 * 1) = 1,039.

However, in hexadecimal, each digit can contain 16 values: 0 through 15, with
A through F representing 10 through 15. Similarly, each "place" of a
hexadecimal number represents a power of 16. So with the hexadecimal number
40F, there are 15 "ones" (16 raised to the power of 0), 0 "sixteens" (16
raised to the power of 1), and 4 "two hundred fifty sixes" (16 raised to the
power of 2). To convert that back to decimal, we take (4 * 256) + (0 * 16) +
(15 * 1) = 1,039.

Think about it another way: counting to 20 in hexadecimal looks like this: 1,
2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F, 10, 11, 12, 13, 14. Hexadecimal 10
is decimal 16, and hexadecimal 14 is 4 more than that, or 20.

Hexadecimal is easier for a computer to work with, and it's easier for humans
to read than binary. If this seems confusing, don't worry- for the most part,
you won't really have to worry about it. Just know that hexadecimal is another
way to represent numbers, and hexadecimal digits go up to 15, which is
represented by the letter F.

So, why does FF represent the maximum amount of color? Well, remember that we
can also pass in the red, green, and blue values by using numbers, where each
number is between 0 (no color) and 255 (full color).

    
    
    void draw() {
    
    	background(0);
    
    	//red
    	fill(255, 0, 0);
    	ellipse(25, 50, 10, 10);
    	
    	//green
    	fill(0, 255, 0);
    	ellipse(50, 50, 10, 10);
    	
    	//blue
    	fill(0, 0, 255);
    	ellipse(75, 50, 10, 10);
    }
    

How would you represent 255 in hexadecimal? FF!

FF is 15 "sixteens" and 15 "ones". (15 * 16) + (15 * 1) = 255! So each two-
character section of a web color notation is just a two-digit hexadecimal
number representing a value from 0 to 255.

Why do programmers confuse Halloween and Christmas?

Because Oct 31 equals Dec 25!

### The Color Type

You now know how to use color literals (hardcoded values). But Processing also
has a color type, and you can use it to store colors in variables. Here's an
example:

    
    
    void draw() {
    
    	color black = #000000;
    	color red = #FF0000;
    	color green = #00FF00;
    	color blue = #0000FF;
    	
    
    	background(black);
    
    	fill(red);
    	ellipse(25, 50, 10, 10);
    	
    	fill(green);
    	ellipse(50, 50, 10, 10);
    	
    	fill(blue);
    	ellipse(75, 50, 10, 10);
    }
    

### The color() Function

In addition to using web color notation, Processing includes a predefined
color() function that takes number parameters and returns a color.

    
    
    void draw() {
    
    	color black = color(0, 0, 0);
    	color red = color(255, 0, 0);
    	color green = color(0, 255, 0);
    	color blue = color(0, 0, 255);
    	
    
    	background(black);
    
    	fill(red);
    	ellipse(25, 50, 10, 10);
    	
    	fill(green);
    	ellipse(50, 50, 10, 10);
    	
    	fill(blue);
    	ellipse(75, 50, 10, 10);
    }
    

The color() function makes more sense when creating colors programmatically,
and the web notation makes more sense for predefined colors. You should use
whichever makes more sense to you and your program context.

### Combining Colors

Why does #FFFF00 or color(255, 255, 0) represent yellow? If you mix red paint
with green paint you don't get yellow, so what's up? The difference is that
with a computer you're combining colors of light, which mixes differently than
colors of paint. The real nitty gritty goes beyond this tutorial, but if
you're curious, start with [this wikipedia
article](http://en.wikipedia.org/wiki/Color_mixing) on color mixing.

Experiment with different values to get different colors, or consult [this
wikipedia article](http://en.wikipedia.org/wiki/Web_colors) on web color
notation.

### Transparency

Try the following program. It draws a red circle and then a blue circle. Since
the blue circle is drawn last, it shows up on top of the red circle, hiding
whatever is behind it. Think of this as painting over top of what's already on
a canvas.

    
    
    void draw() {
    
      color white = color(255, 255, 255);
      color red = color(255, 0, 0);
      color blue = color(0, 0, 255);
      
      background(white);
    
      fill(red);
      ellipse(40, 50, 50, 50);
      
      fill(blue);
      ellipse(60, 50, 50, 50);
    }
    

But what if we wanted to blend the colors together? We can achieve this by
using a transparency. To use a transparency, you can pass a fourth argument
into the color() function.

    
    
    void draw() {
    
      color white = color(255, 255, 255);
      color red = color(255, 0, 0, 100);
      color blue = color(0, 0, 255, 100);
      
      background(white);
    
      fill(red);
      ellipse(40, 50, 50, 50);
      
      fill(blue);
      ellipse(60, 50, 50, 50);
    }
    

That fourth argument is called the color's alpha value, and it's specified
from 0 through 255 exactly like the other arguments (0 is transparent, 255 is
completely opaque).

Note: the color() function has been overloaded (another function with the same
name takes different arguments) to take the alpha value argument. There are
other overloaded versions of the color() function, consult the Processing API
for their uses!

It's not possible to specify the alpha value while using web color notation.

### Behind the Scenes

Processing handles colors a bit strangely, so they're simultaneously simpler
and more complicated than other languages, even Java. You can use the above
functionality without really worrying about what Processing is doing, but if
you're curious, we'll include a peek behind the scenes here.

Processing actually stores colors as ints. Similar to how web notation uses
different sections of a longer hexadecimal number to represent a color,
Processing uses sections of a long binary number to represent colors.

The full number takes the form of AAAAAAAARRRRRRRRGGGGGGGGBBBBBBBB, with 8
bits (0 or 1) each for alpha, red, green, and blue of the color. Since each
value has 8 bits in which to store information (2 values, either a 0 or 1),
and 2 to the power of 8 is 256, that's why color values have to be from 0 to
255.

This is also why treating colors as Strings (passing them into the text() or
println() functions) results in strange-looking numbers displaying instead.
However, you can also take advantage of this underlying representation for
doing things like bit shifting, which allows you to manipulate colors faster
than using functions.

If that's confusing, it's okay. You can use web notation and the color()
function and never worry about it. But that's why Processing's color handling
is so different from other languages, so it's good to keep in mind.

### Exercises

  * How old are you in hexadecimal?
  * Write a program with a random background color and draws an ellipse, a rectangle, and a triangle with different random colors at random locations on the screen. Hint: the random() function might be useful, check out the Processing API!

###  Next: [Writing Your Own Functions](WritingYourOwnFunctions.jsp)

