#  [Intro to Programming](index.jsp)

## Why Processing?

Java is great, and once you know what you're doing, you can accomplish amazing
things with it. Unfortunately, because the language is advanced enough for
these amazing things, it requires an understanding of some pretty complicated
concepts just to get off the ground. The best features of Java are cutoff from
beginners just learning the basics- doing things that require windows, user
interactions, and animations are too involved for beginners to tackle. Because
of this, programming students have been stuck with learning the basics by
writing programs that run without a GUI, mostly via the command prompt.

As a result of the kind of programming beginners have to work through, there
is a standard, proven curriculum that educators have been using to teach
programming that dates back to the days of punch cards. That method of
teaching programming involves doing things like printing out text, printing
out prime numbers, printing out how much change a person should get after
paying for something, printing out how long a loan will take to pay back..
basically a lot of printing text to a command prompt.

And this has worked great, for the most part. A big part of programming is
problem solving, which is more important than learning the syntax of a
language or making things pretty, and the above examples are great for
introducing students to problem solving without overwhelming them with new
language constructs. So even though the programs that beginners have to write
aren't very pretty, they do teach the basics required to get to the more
complicated stuff.

Another reason that the old style of teaching programming has perservered for
so long might be due to the fact that until pretty recently, many people
viewed computers as glorified calculators- you input some information, the
computer does some stuff, and it outputs an answer. That's how most
educational programs work even today, even though computing has become much
more visual and interactive over time.

Computing has taken a giant leap forward in the last few years. People are now
accustomed to programs that are highly visual and feedback-driven, and more
and more people are using these programs on their phones and tablets instead
of on a "real" computer.

This is an example of a game that many people have in their pockets right now:

![](http://upload.wikimedia.org/wikipedia/en/d/d6/Angry-Birds-in-Game-
Play-1.jpg)

That causes a big mismatch between modern programs and the type of programming
available to beginners. And that's a shame, because beginners are often the
most excited about making a cool-looking program in the first place. Imagine
how many people decide to learn programming after being inspired by the games
and applications on their cell phones, and how many of them lose interest
after finding out they have to spend a year printing things out to a console
before they can even pop up a window with an "ok" button. Colleges are
suffering from dropping enrollment rates in computer science departments even
as the use of technology is multiplying, and I would argue that this mismatch,
between what a beginner wants to do and what a beginner can do, is the cause.

The ubiquitous "Hello World" program is the standard first program that
students write, and it was introduced in the 1970s. This is what Java's "Hello
world" looks like, which is pretty much what they all look like:

![](http://s3.StaticVoidGames.com/tutorials/intro/helloWorld.png)

That's pretty boring- especially for beginners who can't wait to dive in to
all the crazy stuff they're used to doing with their phones, every day, just
by easily pressing a button. Some of you are probably thinking, "No way!
Programming is cool no matter how ugly it is!" and I almost agree with you. I
remember thinking that Hello World was the greatest thing ever. But if we can
teach the basics in a more compelling and interesting way, why shouldn't we?

In comes Processing. Processing is another programming language built on top
of Java (so you have all the benefits of Java), but it's designed to be easier
to use, highly visual, and offers immediate, interesting feedback to beginner
programmers. Processing is actually doing a lot of work behind the scenes, so
you can jump right into a highly visual program without worrying about the
nitty gritty of setting everything up yourself. For example, here is
Processing's equivalent to a Hello World program:

    
    
    void draw() {
       ellipse(mouseX, mouseY, 10, 10);
    }
    

![](http://s3.StaticVoidGames.com/tutorials/intro/processingHelloWorld.png)

That program follows your mouse around and draws a circle wherever it is,
letting you draw neat-looking paths. It might not seem like much, but it
encourages the student to think about expanding it- what would it look like if
I changed the color of the circle? What if I used a different shape? The code
for Processing makes it possible for even a beginner to think about how those
things would be done.

Without knowing anything about the Processing language, or programming in
general, a novice student can play with this code and get results. The code
itself almost invites a programmer to try different things out. Given a Hello
World program skeleton and a link to the [Processing
API](http://processing.org/reference/) (an API is basically a list of things
you can do in a programming language), a programmer can spend days just trying
things out! Compare that to the traditional approach- as much as I love Java,
how can a novice programmer work with its Hello World without further
instruction?

Processing also comes with some other bells and whistles, which are really
just standard Java features hidden behind easy controls. You don't have to
worry about compiling (just press the little play button), and Processing even
handles deployment for you- just by pressing a button, you can generate a
little webapge that holds your program, or an application file that you can
send to your friends.

Processing also makes it easy to make programs that work on cell phones.
Again, that might not seem like a big deal, but how many students want to
learn programming because they were inspired by games and applications on
their phones? How much will that number grow in the next 5 or 10 years? Why
alienate potential programming students by forcing them to go back to 1970 to
learn how to program?

But Processing is not just for beginners, and it's not just easy mode for
Java. It also gives you access to some pretty fancy things, such as 3D
primitives, neat lighting effects, and camera controls- none of which are very
easy in standalone Java.

All of that being said, Processing is not meant to replace Java. For example,
creating GUIs (graphical user interfaces) with buttons and other standard
components is MUCH easier in Java. Java also has some great 3rd party
libraries (code written by other people designed for you to easily use),
including libraries for game programming. But Processing does provide a great
stepping-stone that lets novice programmers learn the basics while still
creating fun, visual programs that they can use just like any other program
they're used to using.

### /rant

In summary, I don't want to seem disrespectful or ungrateful for the education
(and more importantly, the **educators**!) I've had. I was lucky enough to
have a high school teacher that recognized and dealt with these issues, and
the college I attended has since switched its curriculum from command-line-
centric C++ to more visual Java. I've done pretty okay. But I also recognize
other people struggling to learn programming because the learning curve is
just too steep to get to the things that people actually want to program.
Granted, programming is HARD. But do we have to make it harder by limiting
beginners to only making boring programs?

### Things to Think About

  * What are some of your favorite programs and games?
  * What types of programs do you hope to write as you learn how to program?
  * How has education (programming or otherwise) been affected by technology? How would you like education to use technology?
  * Does a programmer miss out on anything by learning the basics in a more visual way? Does a programer gain anything by learning the basics in the traditional way?

###  Next: Now let's actually [Start Programming](StartingProcessing.jsp) in
Processing!
