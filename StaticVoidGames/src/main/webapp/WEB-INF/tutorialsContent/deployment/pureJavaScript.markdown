#  [Deployment](http://staticvoidgames.com/tutorials/deployment/index)

## Uploading Pure JavaScript

If you want to upload pure JavaScript (as opposed to JavaScript from libGDX or Processing), then follow the steps below:

 - Setup your JavaScript project in a directory. Include any JavaScript libraries, images, sounds, etc. Also include an html page that embeds your game.
 - Make sure you only use relative paths. In other words, if you have an image at YourDirectory/images/image.png, and you want to refer to that from YourDirectory/index.html, then refer to it via the relative path images/image.png.
 - Create a zip file that contains everything in the above directory.
 - Upload that zip file on the Pure JavaScript section of the game uploader.
 - In the uploader, enter the relative path to the html page that embeds your game. This should be relative to the top level of the zip file, so if your html page is at YourDirectory/html/index.html, then you should put "YourDirectory/html/index.html" (without the quotes) for your html file location in the uploader.
 - Static Void Games will host your directory on S3, and link to your index page from your game page.
 
 Static Void Games takes a very hands-off approach with JavaScript games, so creating the index file is up to you! 