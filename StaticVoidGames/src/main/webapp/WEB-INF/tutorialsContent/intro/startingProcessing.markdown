#  [Intro to Programming](index.jsp)

## Starting Processing

This guide is meant to serve as a basic "getting started" guide to Processing.
It will walk you through downloading Processing, the basic functionality of
Processing, and writing your first Processing program!

### Step 1: Download

Before you start programming in Processing, you just have to download
Processing from [here](http://processing.org/download/).

### Step 2: Install / Unzip

Windows users: just unzip the folder wherever you want (I put it on my
desktop). In that folder (which is named processing-X.Y.Z, depending on the
latest version), you'll see an executable file named processing.exe. Run that!

Mac or Linux users: you should [contact
me](http://StaticVoidGames.com/about/contact.jsp) and let me know what you do!

### Step 3: Run

When you run Processing, you should get a window that looks something like
this:

![](http://s3.StaticVoidGames.com/tutorials/intro/processing1.png)

The main white area is basically just a text editor, where you'll write your
code. The black area at the bottom is a **console**, and it's where messages,
println() statements, and compiler errors will display.

When you click the play button in the upper left corner, these happen:

  1. The Processing code you wrote is converted into Java code.
  2. A bunch of fancy extra stuff is added to make your life easier, like the draw loop, and mouse and keyboard listeners. In pure Java, you have to set all of that up yourself.
  3. That Java code is compiled into Java byte code. If it encounters any compiler errors, it stops and lets you know.
  4. Finally, the byte code is run by the JRE, and your program starts up. 

### Step 4: Program!

So, enough yapping, let's get to your first program! Type this into your
Processing editor, then press play:

    
    
    void draw() {
       ellipse(mouseX, mouseY, 10, 10);
    }
    

You should have something that looks like this image. Move your mouse in the
window and see what happens:

![](http://s3.StaticVoidGames.com/tutorials/intro/processingHelloWorld.png)

Congratulations! You've just created your first program! Processing calls
their programs **sketches**, so you'll probably see me interchange the words
"sketch" and "program". For that matter, a game is a program too! Just keep in
mind that they're all the same thing, so don't get confused if I switch
between them.

### Things to Think About

  * Why did you have to specify what kind of computer you had before downloading Processing?
  * What kinds of programs are you excited about creating?

###  Next: [What Just Happened?](StartingProgramming.jsp)
