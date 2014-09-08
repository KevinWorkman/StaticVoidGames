### What is JarMatey?

JarMatey is a program that builds self-extracting jars. These self-extracting jars can handle external files and native resources.

### Isn't this just another fatjar creator?

A fatjar is a jar that contains the contents of other jars, and yes, that's part of JarMatey. However, JarMatey programs can also contain external files and OS-specific natives, which JarMatey takes care of automatically.

### Can I package the JRE with an JarMatey? Can I create .exe files using JarMatey?

No. End users will have to have their own JRE installed, and JarMatey only exports .jar files. This helps keep the download size of your program down, and in my humble opinion, packaging the JRE with your program is overkill for most situations. If you really want to package the JRE with your program, check out [JWrapper](http://www.jwrapper.com/).

### Can I charge for games and applications I create using JarMatey?

Sure!

### Isn't this just like JarSplice?

JarMatey is based on JarSplice, but I've added the ability to include external files so it can be used for Processing sketches as well as Java programs.

### Something isn't working or I have a feature request!

I'd love to hear from you. Don't hesitate to [contact me](http://StaticVoidGames.com/about/contact)!

### Did anybody actually ask any of these questions?
Nope. But this is a better format than a wall-o-text explaining everything!

### How exactly does JarMatey work?

JarMatey consists of two parts. The first part is the GUI that is run by the creator of the game or application. The gui does a few things:

* Uses the <a target="_blank" href="http://docs.oracle.com/javase/7/docs/api/java/util/zip/ZipFile.html">ZipFile</a> class to read the files from any input jars.
* Uses the <a target="_blank" href="http://docs.oracle.com/javase/7/docs/api/java/util/jar/JarOutputStream.html">JarOutputStream</a> class to output those files, as well as any natives and external files, to a destination jar.
* Includes a few other classes required for the other half of the process.

The second part of JarMatey is the actual self-extracting jar that is output by the JarMatey gui. When the jar is run by the user (by double-clicking it), the following happens:

* A splash screen is (optionally) displayed.
* The main JarMateyLauncher class is run.
* This class extracts all of the natives and external files to a temp directory that the user doesn't have to worry about.
* The class then uses the  <a target="_blank" href="http://docs.oracle.com/javase/7/docs/api/java/lang/ProcessBuilder.html">ProcessBuilder</a> class to run the actual main program. This guarantees that all of the natives and external files are in place before the program is run.
* When the user exits the main program, JarMatey deletes the external files so the user only ever has to deal with a single file: the self-extracting jar.
	
### Can I use JarMatey to run an applet?
	
No. JarMatey is my attempt at getting <i>away</i> from applets after Java 7 update 51 made them harder for users. But if there's a lot of interest, I could think about adding applet functionality. That wouldn't make it easier on users though, so it's not currently in the works.
