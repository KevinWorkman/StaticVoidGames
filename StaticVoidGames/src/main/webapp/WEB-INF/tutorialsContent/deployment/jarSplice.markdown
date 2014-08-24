#  [Deployment](index.jsp)

## JarSplice

One of the simplest ways to deploy a Java (or Processing) application is as an
executable jar. An executable jar is a single file that runs automatically
when the user double-clicks it.

Simple applications can be exported as a single jar without doing any other
work. However, applications that use libraries (and Processing sketches) might
wrap themselves up in multiple jars. This allows you to use a newer version of
a library without having to update the rest of the program, but it also can be
confusing for an end-user to juggle multiple jar files just to run a single
game or application.

This tutorial goes through the process of converting multiple jar files and
native libraries into a single executable jar (also known as a fat jar) using
a simple program called JarSplice.

### Java Required!

Important note: For an executable jar to run, the user needs to have Java
(specifically, the Java JRE) installed on the computer! Most people probably
already have Java, but for the few who don't, you can get it [here](http://www
.oracle.com/technetwork/java/javase/downloads/jre7-downloads-1880261.html).

### Download JarSplice

I've found the easiest way to package multiple jars (and native libraries)
into a single executable jar is by using a program called
[JarSplice](http://ninjacave.com/jarsplice). JarSplice itself is a single
executable jar, and you can download it
[here](http://ninjacave.com/jarsplice#Download).

Because it's an executable jar, you don't have to install anything- just
download the jar file somewhere, then double-click it to run JarSplice.

### The JarSplice Process

JarSplice organizes itself into 4 different sections: a section where you add
your jars, a section where you add your native libraries, a section where you
specify your main class, and a section where you export your fat jar.

This might sounds like a complicated process, but each step only takes a
couple seconds. We'll walk through this using the group of jars and native
libraries we created in the [Exporting Processing
tutorial](ExportingProcessing.jsp).

### Step 1: Add Jars

Click the "1) Add Jars" button on the left, and then click the "Add Jar(s)"
button at the bottom. This brings up a file dialog where you can select
multiple jars to be included in your fat jar.

![](JarSplice1.png)

If you have jars in multiple directories, you can click the "Add Jar(s)"
button multiple times.

If you're using pure Java (not Processing), you can skip to step 2!

Processing users: you should NOT add these files from your lib directory:

  * args.txt
  * core.jar
  * gluegen-rt.jar
  * jogl-all.jar
  * gluegen-rt-natives-*.jar
  * jogl-all-natives-*.jar

For example, if I named my sketch Test and did not use minim, the only jar I
care about is Test.jar.

If you used a library like minim, make sure you DO add the jars for the
library! The above image actually shows what you should add when using minim.

### Step 1.5: Add the Processing library (Processing only)

Since Processing no longer exports the libraries and natives required to run
on every system, I've collected them in [this
jar](http://s3.StaticVoidgames.com/deployment/ProcessingLibrary.jar) for you.
(That's why you can ignore the extra jars in your lib folder!) Download that
jar and add it to your JarSplice project, like pictured here:

![](JarSplice2.png)

### Step 2: Add Natives

Processing users: unless you've added a 3rd party library that requires its
own natives, all your natives are already taken care of by some magic in
ProcessingLibrary.jar, so you can skip to step 3!

Also, not every program uses native libraries, so don't panic if you don't
have any!

Java is designed so that a programmer can write Java code and run that code on
any computer that has Java. However, Java can also use technology like openGL
(for advanced graphics), which rely on native code written in languages like C
or C++.

Unlike Java code, native code only works on a particular system, which allows
it to access system resources like the graphics card (that's what makes it
good for advanced graphics). A native library is a way to run native code from
Java, which gives you access to systems that you normally wouldn't have access
to.

An important rule when using native libraries is that the libraries have to be
on the file system directly, not inside a jar file. Luckily, JarSplice gets
around this by automagically copying any native libraries you include into a
temporary directory that gets deleted when the program exits. The user never
even knows they exist, so you don't have to worry about them!

To include native libraries, click the "2) Add Natives" button on the left,
then click the "Add Native(s)" button at the bottom.

### Step 3: Main Class

When you're done adding your native files (if you have any), click the "Main
Class" button on the left. This tab lets you specify the entry point of your
program.

Processing users: this is just the name of your sketch! Since I named my
sketch Test, I just write "Test" (without the quotes) here. You can skip to
step 4.

Java programmers: this is the name of the class that contains the main()
method. Make sure you include package information here, if you used it. But
you *don't* include the .class part, just the class name.

For example, if my main class is named MyMainClass and is in a package named
com.test.package, I would write "com.test.package.MyMainClass" (without the
quotes) here. If I'm not using any packages, I would just write "MyMainClass"
here.

### Step 4: Export the Fat Jar

You're almost done! Click the "Create Fat Jar" button on the left, which takes
you to a tab with a single button. Click that button and choose a name for
your fat jar and click the "Save" button, and JarSplice will create a fat jar
that includes all of the jars and native libraries you added in the previous
steps.

### Exporting Applications

JarSplice can also export your runnable jar as an application, and the process
is pretty easy. However, like the fat jar, these applications do not support
external files such as images and sound files (these can be added to a jar,
but that's outside this tutorial).

If you are NOT using external files, you can export OS-dependent applications
using the 3 buttons in the lower-left of JarSplice.

If you ARE using external files, you can use JWrapper to create applications
that support external files. If you're using Java, you might also consider
packaging the files as resources into your jar.

### Notes

  * By default, Mac computers don't run programs downloaded from the internet, and this includes runnable jars. The easiest way to get around this is to simply right-click the jar file and click "open", as described [here](http://support.apple.com/kb/HT5290).
  * JarSplice handles native libraries by secretly running code before and after running your jar. In other words, the *actual* main class of a JarSplice jar is JarSpliceLauncher, which relies on some variables set inside the jar itself. You almost never have to worry about these details, but it also means that you shouldn't use JarSplice jars inside other JarSplice jars.

###  Next: [Processing Deployment](ProcessingDeployment.jsp)
