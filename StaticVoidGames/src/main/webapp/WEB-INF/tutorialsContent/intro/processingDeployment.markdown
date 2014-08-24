#  [Intro to Programming](index.jsp)

If you've been following the tutorials, you should have a vague idea of how to
write, debug, compile, and run a Processing program. The next section of the
tutorial (coming soon!) will dive into more programming topics, but before
that, you probably want to know how to actually deploy your program- how do
you export your Processing sketch as something other people can execute? How
do you convert your code into a runnable program? How do you publish your
program, present it, or send it to other people?

## Deployment

There are a couple different ways to export your Processing sketch. You can
export it as a runnable application or as an applet, and you can then use your
applet in a number of ways. How you export your sketch depends on what exactly
your goals are, so we'll go over the different methods that you can choose
from.

You don't have to be an expert in any of these, but it's a good idea to be
familiar with the different options. Scroll to the bottom of this page for
directions on using our step-by-step uploader to automatically create applet,
webstart, and runnable jar versions of your game!

### Application

Exporting your sketch as an **application** allows you to create a folder that
contains a runnable file that you, or somebody else, can directly run. The
benefit of this is that it can be very easy to run without worrying about
setting anything else up- the application handles that for you. The downside
is that you need to know what kind of computer a person has before sending
them a file to download and run.

Depending on what kinds of things you use in your program, you'll have to know
what operating system you're targetting, as well as whether the system is 32
bit or 64 bit. To send this to somebody else, you then have to zip up the
folder, send them the zip file, have them unzip it, open the folder, and run
the application. But if something goes wrong, you won't get very much feedback
about why.

You can feel free to export your sketches as applications, but Static Void
Games doesn't use that method at all. Instead, I recommend exporting your
sketch as an applet and using our step-by-step uploader to automatically
generate different versions that other people can use.

### Applet

An applet is a type of Java program that runs embedded in a webapage. They are
generally pretty easy to run, and Processing even generates a webpage that
displays the applet for you. If you have access to your own webhosting and
some html skills, you can use the files in the applet directory created by
Processing and customize your own website displaying the applet. Then you just
direct people to that url, and they'll see your applet on the webpage. If you
don't want to waste time with html, you can always just use our step-by-step
uploader to create a decent-looking game page automatically.

### Runnable Jar

When you export your sketch as an applet, Processing also creates a **runnable
jar** version of your program. This will be located in the applet directory
created by Processing when you export your sketch as an applet and will be the
name of your sketch with a .jar extension. A runnable jar will work on every
computer (or at least every compouter with a JRE, but that's a requirement for
every version), and you don't have to specify ahead of time what kind of
computer the jar will be run on.

The downside of exporting your program as a runnable jar is that it gets a
little bit more complicated if you're using libraries (such as the minim
library for sound). Using libraries means that your jar will have to know
where the library files are located. This requires you to pass the classpath
into the runnable jar every time you run it, which requires you to run the jar
from the command line, which isn't exactly user-friendly. The required library
files are packaged into jars in the applet folder next to the runnable jar.

If that's confusing, don't worry about it yet. Our uploader handles all of
that for you!

### Webstart

A runnable jar can also be deployed as a **webstart application**. This is
similar to an applet in that it is hosted on a website, but the program is
given its own window instead of being embedded directly in the page, and
security works a bit differently, plus webstart comes with a lot of perks like
making sure the correct version of Java is installed.

Webstart applications are also installed on the user's computer just like a
normal application, complete with an uninstaller. That way the user has access
to your game without needing to be connected to the web. Of course, this can
all be customized by the user, which is why many experienced users prefer
webstart applications over other deployment methods.

Although webstart applications are how a lot of users prefer running Java
programs, setting them up goes a bit outside the scope of this tutorial. If
you want more info you can check out [this tutorial](http://docs.oracle.com/ja
vase/tutorial/deployment/webstart/index.html), but I would recommend just
using this site's uploader, which will set up webstart, applet, and runnable
jar versions of your game for you!

### Static Void Games!

This site is designed with the goal of making it easy generate a decent-
looking game webpage complete with links to applet, webstart, and runnable jar
versions of your game. And you don't have to know anything about html or worry
about any underlying Java deployment setup!

The benefit of this is that you get to reach as many people as possible, since
different people prefer different types of deployment. So you can focus on
programming and improving your game without worrying about html or setting up
different kinds of deployment options. Just walk through our step-by-step
uploader, and all the boring work is done for you!

To keep things simple, Static Void Games does not support the OS-specific
executable applications exported by Processing. And games that require
libraries will not be available as runnable jars, that way users don't have to
worry about setting up the classpath- just let the site handle that for you
and stick with the applet or webstart versions.

When you're ready, just export your Processing sketch as an applet. Then go
through the step-by-step uploader and answer the questions it asks you. If
your sketch requires library jars, you can specify them too. Static Void Games
will then create a game page for you, including links to your game as an
applet, a webstart application, and a runnable jar.

### Things to Think About

  * What's the difference between an OS-specific application, an applet, a webstart application, and a runnable jar?
  * How do you want your games to be deployed? How do you normally play games?

### Next: [Basic
programming!](http://StaticVoidGames.com/tutorials/basicConcepts/)

