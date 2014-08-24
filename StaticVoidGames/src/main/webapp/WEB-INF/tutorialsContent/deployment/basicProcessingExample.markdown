#  [Deployment](index.jsp)

## Example: Exporting a Basic Processing Sketch

One of the great things about Processing is how easy it is to develop
visualizations and games. However, deploying a sketch so other people can use
it can still be a mystery, especially with the changes to Processing
introduced in version 2.1.

Even though Processing can be exported as an application, your work is not
done there. As of Processing 2.1, the exported application can only be used on
the same operating system- for example, if you export a Processing sketch on a
Windows computer, only people with a Windows computer will be able to run your
application.

There are valid reasons why Processing does this: they want to keep the
initial download size of Processing small, and they want to force you to test
your sketch on other systems. However, not everybody has access to every
system, and being able to run on every system was one of Java's strongest
features!

Even when you export an application, it's still just a directory that contains
multiple files. This can be confusing for end users (players) who expect games
to come as a single file.

This tutorial takes you through creating a basic Processing sketch and
exporting it in a way that will allow you to package it up as a single file
using a program called SvgExe.

### Example Sketch

Let's set up an example sketch and use that for our exporting purposes. This
sketch is just an example, so it can be pretty simple:

    
    
    void setup() {
      size(200, 200, P3D); 
    }
    
    void draw() {
     ellipse(100, 100, 50, 50); 
    }
    

This code simply draws a circle in the middle of the screen. It's not very
complicated, but it will help us make sure our export process works. Also,
since we give the P3D argument to the size() function, this sketch uses
openGL- this requires native libraries, which we'll talk about later.

I'm going to save this to a sketch named 'Test'.

### Export

To generate some of the files we're going to need, we simply use the "Export
Application" option, like so:

![](ExportProcessing1.png)

This brings up a dialog with several options, pictured here:

![](ExportProcessing2.png)

On this screen, you can choose whether you want to run in full screen mode,
but you should NOT check the "embed Java" option.

Click the export button, and Processing will create a directory (next to your
sketch) containing an application that will work for the current system. For
me, this creates a directory named 'application.windows64'.

Inside that directory, you'll find 3 things:

  * A runnable file with your sketch's name. For me, this is Test.bat. On linux, it would be Test.sh. On mac, it's ???. We can just ignore this file.
  * A source directory that contains your Processing sketch converted to Java code. This might be interesting to look at to understand what Processing is doing under the hood, but again, we can ignore it.
  * A lib directory that contains a bunch of jar files, including a jar with your sketch name. Since my sketch is named Test, the jar is named Test.jar. This is the file you care about!
![](Processing1.png)

### Native Libraries

Processing is built on top of Java, so most of our code is converted to Java
code before it's exported into the jars you can find in the lib directory.
However, Processing can also use technology like openGL (for advanced
graphics), which rely on native code written in languages like C or C++.

Unlike Java code, native code only works on a particular system, so it can
access system resources like the graphics card (that's what makes it good for
advanced graphics). A native library is a way to run native code from Java,
which gives you access to systems that you normally wouldn't have access to.

When you export an application, Processing includes the native libraries for
the current system, but it does not include the native libraries for other
systems. I've collected all of the native libraries you need by default
[HERE](http://s3.StaticVoidGames.com/deployment/ProcessingLibrary.jar).
Download this jar and save it for use in the next step.

Note: if you're using another library that requires its own native libraries,
you'll have to make sure you have the native libraries for every system you
want to run on.

### Putting it all together using SvgExe

Now you should have the jar with your sketch name (mine is Test.jar), and the
ProcessingLibrary.jar that contains the Processing natives for every system.

You can now use SvgExe to combine these into a single jar that you can send to
other people (or upload here!).

#### Step 0: Download SvgExe

SvgExe is a single runnable jar that you can download
[here](http://StaticVoidGames.com/SvgExe/). Download that jar and double-click
it to run the program.

#### Step 1: Add the jars to SvgExe

Run SvgExe, and on the first tab, add your sketch's jar as well as
ProcessingLibrary.jar to the list on the left. It should look like this:

![](Processing2.png)

Notice that we aren't including any of the other jars in the lib folder, just
the one with your sketch name. Instead, we're using ProcessingLibrary.jar so
our sketch works on other computers!

#### Step 2: Specify your output

Our sketch doesn't include any external files or native files (other than what
is already handled by ProcessingLibrary.jar), so we can skip right to
specifying the output.

The main class of a Processing sketch is just the name of the sketch. I named
my sketch Test, so that's what I put in the main class field.

Then you just specify the name and location of the jar file to be created.

![](Processing3.png)

#### Step 3: Run your jar!

Navigate to wherever you specified your output, and double-click the jar to
run it. That's it!

Now you can send this jar file to other people who want to play your game.. or
you can upload it here! :p

### If your sketch includes a third party library or external files, check out
the [Advanced Processing Example](AdvancedProcessingExample.jsp)!
