#  [Deployment](index.jsp)

## JWrapper

Executable jars are one of the simplest ways to deploy a Java or Processing
program, and you can do that using [the JarSplice tutorial](JarSplice.jsp).
However, they don't come with their own icons, the user has to have Java
installed, and they can't contain external files. However, you can create OS-
specific executables that support all of these things!

This tutorial goes through the process of converting multiple jar files,
native libraries, and external files into a single OS-specific executable file
(.exe for Windows, .app for Mac, and an executable file for Linxu) using a
program called JWrapper.

### Download JWrapper (and some other files)

I highly recommend using the beta version of JWrapper, available
[here](http://www.jwrapper.com/jwrapper-beta.html). You should download all 4
files available at the bottom of that page.

You'll also need a JRE Verifier, which I've already precompiled for you. This
verifier program basically figures out which version of Java to use with your
application, available here:

  * If you compiled in Java 5, you should use [Jre15OrGreaterVerifier.class](http://s3.StaticVoidGames.com/deployment/JreVerifiers/Jre15OrGreaterVerifier.class)
  * If you compiled in Java 6, you should use [Jre16OrGreaterVerifier.class](http://s3.StaticVoidGames.com/deployment/JreVerifiers/Jre16OrGreaterVerifier.class)
  * If you compiled in Java 7 (Processing users, this is you), you should use [Jre17OrGreaterVerifier.class](http://s3.StaticVoidGames.com/deployment/JreVerifiers/Jre17OrGreaterVerifier.class)

Unzip the 4 JWrapper zip files into a directory and download the proper JRE
Verifier from above into that directory, so that your directory looks like
this:

![](JWrapper1.png)

### SampleApp

The sampleapp directory contains an example JWrapper project that we're going
to export just to get the hang of using JWrapper.

JWrapper is a command-line program that takes an XML file as input. The XML
file specifies which files to include, which icons to use, and which JRE
Verifier to use. Take a look at jwrapper-sampleapp.xml in the sampleapp
directory to get an idea of the kinds of options you can specify.

Open up a command prompt to the top-level directory (the directory that
contains the sampleapp directory), and type this command:

java -Xmx512m -jar jwrapper-0000000.jar sampleapp/jwrapper-sampleapp.xml

You'll have to change the 0000 part to match whatever version of JWrapper
you're using.

Press enter. Warning: the first time you do this, it WILL take a long time
(around 10-20 minutes). It takes MUCH less time (around 10-20 seconds) after
the first run.

If you get an error saying "unable to rename iconified to original EXE", make
sure you don't have any windows open to the directory, and simply run the
command again. I'm not sure what this error is, but closing the windows seems
to fix it. Or maybe it's just random!

Anyway, when the build finally successfully finishes, your directory should
look like this:

![](JWrapper2.png)

Most of these additional files are created by JWrapper and used during the
export process (that's why it takes so long the first time). Inside the build
directory you should find 6 files that you care about:

![](JWrapper2.png)

These are the OS-specific executables created by JWrapper. We just wrapped the
sample app, so they don't do much, but the process will be very similar when
we create executables for our own applications.

### Packaging Your Own Application

Now that we've gone through the process of creating a sample app (and now that
we have the 20-minute initial wait out of the way), we can package our own
application.

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
