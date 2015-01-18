#  [Deployment](http://staticvoidgames.com/tutorials/deployment/index)

## Exporting LibGDX as HTML

If you're just looking for a basic guide, do the following:

 - Use the libGDX setup tool to create your project, and make sure you incldude the Html project option.
 - Use eclipse (or your IDE of choice) to write your code. Make sure not to do a GWT compile from eclipse! (This creates a ton of extra files that will make your upload much larger than it needs to be. If you did do a GWT Compile from eclipse, see below for which files you should delete!)
 - Open a command prompt to your project's top-level directory, type "gradlew html:dist" (without the quotes), and hit enter. More info [here](https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline#packaging-for-the-web).
 - This will cook for a couple minutes, and if everything works it will create a directory located at html/build/dist. 
 - Delete the subdirectory html/build/dist/WEB-INF. (You don't need these files unless you're running a server yourself, so they just make your upload larger.)
 - Create a dist.zip file by zipping up your html/buid/dist directory.
 - Upload that dist.zip file on the LibGDX Html section of the game uploader, and let Static Void Games handle the rest!
 
 Those are the basics, but if you want more information, keep reading:

One of the awesome things about [libGDX](http://libgdx.badlogicgames.com/) is that you can export for pretty much any system: not just for desktop, but for iOS and Android as well. You can also export your game as an HTML5/JavaScript project, which allows people to play it directly in their browser- no downloading, no install, no Java. This is much easier for players, so it's one of the best ways to deploy your game.

This guide goes through the process of creating a libGDX game, exporting it as an HTML project, and uploading it on Static Void Games so other people can play it. This guide assumes that you already have an IDE and a project setup. If you need help with that, follow the instructions [here](https://github.com/libgdx/libgdx/wiki/Setting-up-your-Development-Environment-%28Eclipse%2C-Intellij-IDEA%2C-NetBeans%29) first!

###Step Zero: DON'T do a "GWT Compile" from Eclipse! But if you do...

Some guides might have you do a "GWT Compile" from your IDE. This allows you to debug your project from your IDE, but it also creates a ton of extra files. That's not a big deal if you're running your own server, but since you'll be uploading your game, these extra files will just waste your time (and might even cause the upload to fail). So let's get rid of them!

If you did do a "GWT Compile" from eclipse, all you need to do is delete the html/war/html directory!

![](http://StaticVoidGames.com/tutorialsContent/deployment/libGdxHtml2.png)

###Step One: Package your HTML Project

Open a command prompt to your top-level project directory. Then type:

    gradlew html:dist
    
...and hit enter. That will package your game as an html project.

###Step Two: Delete html/build/dist/WEB-INF

The above process creates a folder that you only need if you're serving your own files. These files can get pretty huge, so let's delete them.

![](http://StaticVoidGames.com/tutorialsContent/deployment/libGdxHtml3.png)

###Step Three: Create dist.zip

After deleting the dist/WEB-INF directory, zip up your html/build/dist directory. Do this using whatever file archiver you want. I use 7zip, but your default zip program should work fine.

![](http://StaticVoidGames.com/tutorialsContent/deployment/libGdxHtml4.png)

This should create a dist.zip file. This is what we care about!

![](http://StaticVoidGames.com/tutorialsContent/deployment/libGdxHtml5.png)

###Step Four: Upload dist.zip to Static Void Games!

From the game editing page, go to the "libGDX HTML" section. From that section, upload the dist.zip file you just created. Choose what kind of page you want to display, add some text for the controls, and let StaticVoidGames handle the rest!