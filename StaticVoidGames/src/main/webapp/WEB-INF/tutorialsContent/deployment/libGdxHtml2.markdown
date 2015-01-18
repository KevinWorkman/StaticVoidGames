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

This guide goes through the process of creating a libGDX game, exporting it as an HTML project, and uploading it on Static Void Games so other people can play it.

###Step One: Create the Project

Download the libGDX [setup application](http://libgdx.badlogicgames.com/download.html). Run that, fill out your game's information, and make sure you check the "Html" box.

![](http://StaticVoidGames.com/tutorialsContent/deployment/libGdxHtml1.png)

Press the "Generate" button. This will do some cooking, and when it's done you'll have a directory of projects you can import into your IDE.

###Step Two: Import your project into eclipse

You don't have to use eclipse, but the process is probably pretty similar for other IDEs. If you haven't setup your IDE yet, follow the instructiosn [here](https://github.com/libgdx/libgdx/wiki/Setting-up-your-Development-Environment-%28Eclipse%2C-Intellij-IDEA%2C-NetBeans%29) first, and then come back.

From eclipse, go to File -> Import -> Gradle -> Gradle Project.

In the dialog that pops up, select your top-level project directory as the Root folder. In the above example screenshot of the libGdx setup app, my top-level project directory would be C:\Users\Kevin\Desktop\MyGame.

Press the "Build Model" button, then select the top-level project directory, which selects all of the subprojects. Click Finish, and when eclipse is done importing, you'll have 

//I'm realizing now that this tutorial should only be about exporting, not about importing. Keeping it around for when I create a separate tutorial for importing. -KW, 1-17-2015