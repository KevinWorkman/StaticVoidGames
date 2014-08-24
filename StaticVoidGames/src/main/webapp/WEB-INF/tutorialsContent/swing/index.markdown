#  [Swing](index.jsp)

So far we've made a couple command-line games in Java. While those can
actually be [pretty fun](http://en.wikipedia.org/wiki/Text-based_game), most
modern games are much more graphical. Most games (in fact, most programs) use
a graphical user interface, or GUI, to interact with the user. In other words,
most programs don't use the command line, but instead take input from the
mouse, buttons, text fields, icons, etc.

The most popular way to create a GUI (you can pronounce that either gee-you-
eye or gooey, whichever makes you giggle more) in Java is to use Swing. Swing
is a collection of predefined classes that you can use to create various
components- windows, buttons, sliders, etc. You can also extend these classes
to create your own custom GUI components.

### Swing vs AWT

Before Swing was included in the JDK (in 1998), Java used a library called the
Abstract Window Toolkit, or AWT, to create GUIs. AWT hooks directly into the
underlying operating system: you can write code that tells AWT to create a
checkbox, and AWT will tell the underlying operating system to create a
checkbox. Because of this, programs that use AWT will look different on
different computers. This makes it harder to guarantee that your program looks
good on every computer.

To combat this problem (since Java programs are supposed to work the same on
every computer), Swing was invented. Swing is built on top of AWT, but instead
of telling the operating system to draw native components, Swing uses AWT to
ask for an area it can draw on, then takes over from there. Since Swing draws
its own checkbox, the program is (mostly) guaranteed to look the same on every
system.

Some people might argue that AWT is more suitable for game development than
Swing, because game programmers can basically do the same thing Swing does-
ask for a component to draw on, then draw the game. And while it's certainly
possible to create a game in AWT, I want to keep the tutorials general enough
so you can create any type of GUI. Plus it's not an either-or situation: you
can learn how Swing works through these tutorials, and since Swing is built on
top of AWT, you can use your knowledge to work in AWT if you really want to.

Next: [Starting Swing](StartingSwing.jsp)
