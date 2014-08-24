#  [Deployment](index.jsp)

## Example: Exporting an Advanced Processing Sketch

This tutorial takes you through creating a Processing sketch that includes
external files as well as a third party library, and exporting it in a way
that will allow you to package it up as a single file using SvgExe.

This tutorial assumes you already know how to include third party libraries
and external files in your sketch, so we're going to skip over that part and
focus on exporting the code. This example uses an image file, but the process
is the same for sound or text files as well.

### Example Sketch

Let's set up an example sketch and use that for our exporting purposes:

    
    
    import org.gicentre.handy.HandyRenderer;
    
    HandyRenderer handyRenderer;
    PImage photo;
    
    void setup(){
      size(500, 500);
      
      handyRenderer = new HandyRenderer(this);
      photo = loadImage("cat.jpg");
    }
    
    void draw(){
      stroke(255, 0, 0);
      fill(0, 255, 0);
      handyRenderer.rect(25, 25, 450, 450);
      image(photo, 100, 100, 300, 300);
    }
    

This sketch uses the [Handy library](http://www.gicentre.net/handy/) to render
shapes that appear to be drawn by hand. This sketch also includes an [image fi
le](http://photos.kevinworkman.com/Pictures/2013/i-ZwFCKJr/0/S/Stanley1-S.jpg)
that it draws.

![](AdvancedProcessing1.png)

I'm going to save this to a sketch named 'AdvancedTest'.

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

Inside that directory, you'll find 4 things:

  * A runnable file with your sketch's name. For me, this is Test.bat. On linux, it would be Test.sh. On mac, it's ???. We can just ignore this file.
  * A source directory that contains your Processing sketch converted to Java code. This might be interesting to look at to understand what Processing is doing under the hood, but again, we can ignore it. 
  * A directory named data that includes our cat.jpg image file.
  * A lib directory that contains a bunch of jars, including the third party handy.jar as well as a jar with your sketch name. Since my sketch is named AdvancedTest, the jar is named AdvancedTest.jar. The files handy.jar and AdvancedTest.jar are the files you care about!
![](AdvancedProcessing2.png)

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

Now you should have the jar with your sketch name (mine is AdvancedTest.jar),
the jars for your third party library (mine is handy.jar), and the
ProcessingLibrary.jar that contains the Processing natives for every system.
You also have the data directory that contains any external files used in your
sketch.

You can now use SvgExe to combine these into a single jar that you can send to
other people (or upload here!).

#### Step 0: Download SvgExe

SvgExe is a single runnable jar that you can download
[here](http://StaticVoidGames.com/SvgExe/). Download that jar and double-click
it to run the program.

#### Step 1: Add the jars to SvgExe

Run SvgExe, and on the first tab, add your sketch's jar, any third party
library jars, as well as ProcessingLibrary.jar to the list on the left. It
should look like this:

![](AdvancedProcessing3.png)

Notice that we aren't including any of the other jars in the lib folder, just
the one with your sketch name and the third party library jars. Instead, we're
using ProcessingLibrary.jar so our sketch works on other computers!

#### Step 2: Include the data directory

Since our sketch relies on the external file cat.jpg, we need to include the
data directory. To do so, just go to the "External Files" tab and include the
data directory there. Note that you should include the entire data directory,
not the files inside of it!

![](AdvancedProcessing4.png)

#### Step 3: Specify your output

Now all that's left is specifying the output.

The main class of a Processing sketch is just the name of the sketch. I named
my sketch AdvancedTest, so that's what I put in the main class field.

Then you just specify the name and location of the jar file to be created.

#### Step 3: Run your jar!

Navigate to wherever you specified your output, and double-click the jar to
run it. That's it!

Now you can send this jar file to other people who want to play your game.. or
you can upload it here! :p

