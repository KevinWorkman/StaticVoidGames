package com.StaticVoidGames.spring.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.StaticVoidGames.games.Game;
import com.StaticVoidGames.games.GameForm;
import com.StaticVoidGames.spring.controller.interfaces.EditGameControllerInterface;
import com.StaticVoidGames.spring.dao.GameDao;
import com.StaticVoidGames.spring.util.AttributeNames;
import com.StaticVoidGames.spring.util.FormField;
import com.StaticVoidGames.spring.util.OpenSourceLink;
import com.StaticVoidGames.spring.util.PageDownUtils;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Controller that handles the game edit pages.
 * Each form field is represented by a FormField Object, and each edit page url is mapped to a List of FormFields to display on that page.
 * The form field values are backed by a GameForm Object.
 * This is a bit convoluted, but it means that we can do all of the edit handling from a single page.
 */
@Component
public class EditGameController implements EditGameControllerInterface{


	@Autowired
	private Environment env;

	@Autowired
	private GameDao gameDao;

	/**
	 * Edit page URLs are mapped to Lists of FormFields to display on each page.
	 */
	Map<String, List<FormField>> formFields = new HashMap<String, List<FormField>>();

	/**
	 * Constructs a new EditGameControllr, creates the FormField instances and populates the formFieldsMap.
	 */
	public EditGameController(){

		FormField title = new FormField("Title", "Enter your game's title:", "title", "input");
		FormField shortDescription = new FormField("Short Description", "Enter a short description shown on thumbnails for your game", "shortDescription", "input");
		FormField description = new FormField("Description", "Enter a description for your game", "description", "textarea");
		//	FormField website = new FormField("Website", "If your game is hosted elsewhere, specify that here", "website", "input");

		FormField jarFile = new FormField("Jar File", "Upload a RUNNABLE jar. Applets and Webstarts are HIGHLY discouraged.", "jarFile", "file");

		FormField sourceFile = new FormField("Source File", "Upload a zip file containing your source. This is optional.", "source", "file");
		FormField sourcePermissions = new FormField("Source Permissions", "Specify a copyright to apply to your source", "sourcePermissionsText", "textarea");

		FormField faviconFile = new FormField("Favicon", "Upload an optional favicon. This is a 16x16 or 32x32 image that will show as the icon of your game's browser tab.", "faviconFile", "file");
		FormField backgroundFile = new FormField("Background", "Upload a background image. This is optional.", "backgroundFile", "file");
		FormField thumbnailFile = new FormField("Thumbnail", "Upload an image to be shown as the game's thumbnail.", "thumbnailFile", "file");

		//FormField mainClass = new FormField("Main Class", "DEPRECATED: specify the main class to create a webstart version of your game.", "mainClass", "input");
		//FormField webstartWidth = new FormField("Webstart Width", "How wide should the webstart version be?", "webstartWidth", "input");
		//FormField webstartHeight = new FormField("Webstart Height", "How tall should the webstart version be?", "webstartHeight", "input");

		//	FormField appletClass = new FormField("Applet Class", "DEPRECATED: specify the applet class to create an applet version of your game.", "appletClass", "input");
		//FormField appletWidth = new FormField("Applet Width", "How wide should the applet version be?", "appletWidth", "input");
		//	FormField appletHeight = new FormField("Applet Height", "How tall should the applet version be?", "applettHeight", "input");
		//FormField appletDescription = new FormField("Applet Description", "Enter a short description (such as controls) shown below the applet:", "appletDescription", "textarea");

		FormField javaVersion = new FormField("Java Version", "What version of Java did you use to compile your game?", "javaVersion", "input");
		FormField rating = new FormField("Rating", "Is your game for all ages, teens, or adults only?", "rating", "input");

		FormField adText = new FormField("Ad Code", "You can specify an ad code here. BE COOL.", "adText", "textarea");
		FormField showAdBorder = new FormField("Ad Border", "Show ad border?", "showAdBorder", "checkbox");

		FormField language = new FormField("Language", "What language was your game written in?", "language", "input");

		FormField published = new FormField("Publish", "Do you want to publish your game?<br/>Unpublished games are not shown on the main game page, but can still be played by anybody you give the link to.<br/>Publish:", "published", "checkbox");

		//FormField lwjgl = new FormField("LWJGL", "DEPRECATED: Was your game made with LWJGL?", "lwjgl", "checkbox");
		//FormField signed = new FormField("signed", "DEPRECATED: Is your jar signed?", "signed", "checkbox");

		FormField apkFile = new FormField("Android APK", "Upload an Android APK", "apkFile", "file");
		FormField androidText = new FormField("Android Text", "What text should show under the Android tab? Include links to Google Play, etc.", "androidText", "textarea");
		FormField android = new FormField("android", "Include an Android tab?", "android", "checkbox");

		FormField showLibGdxHtmlLink = new FormField("Include libGDX HTML link", "Check this box if your game page should include a link to a playable html version of your game", "showLibGdxHtml", "checkbox");
		FormField libGdxHtmlFile = new FormField("dist.zip", "Follow <a target=\"_blank\" href=\"http://staticvoidgames.com/tutorials/deployment/libGdxHtml\">this</a> guide, then upload your dist.zip file here.<br/>Note: this upload may take a few minutes!", "libGdxHtmlFile", "file");
		FormField libGdxHtmlMode = new FormField("LibGDX html mode", "What type of html should display?", "libGdxHtmlMode", "htmlModeRadio");
		FormField libGdxHtmlText = new FormField("LibGDX html text", "If you chose to show a prettified page, what text should show under the game? Use this for things like showing controls.", "libGdxHtmlText", "textarea");

		FormField showProcessingJavaScript = new FormField("Include link to Processing.js page", "Check this box if your game page should include a link to a playable html version of your game", "showProcessingJavaScript", "checkbox");
		FormField processingJavaScriptFile = new FormField("web-export.zip", "Follow <a target=\"_blank\" href=\"http://staticvoidgames.com/tutorials/deployment/processingJavaScript\">this</a> guide, then upload your web-export.zip file here.", "processingJavaScriptFile", "file");
		FormField processingJavaScriptMode = new FormField("Processing.js html mode", "What type of html should display?", "processingJavaScriptMode", "htmlModeRadio");
		FormField processingJavaScriptText = new FormField("Processing.js html text", "If you chose to show a prettified page, what text should show under the game? Use this for things like showing controls.", "processingJavaScriptText", "textarea");

		FormField showJavaScript = new FormField("Include link to JavaScript page", "Check this box if your game page should include a link to the index page you provide in the zip file.", "showJavaScript", "checkbox");
		FormField javaScriptFile = new FormField("Zip File", "Follow <a target=\"_blank\" href=\"http://staticvoidgames.com/tutorials/deployment/pureJavaScript\">this</a> guide, then upload your zip file here.", "javaScriptFile", "file");
		FormField javaScriptIndex = new FormField("JavaScriptIndex", "In your zip file, what is the relative path to the html page that contains your game?", "javaScriptIndex", "input");


		formFields.put("title", Arrays.asList(title));
		formFields.put("description", Arrays.asList(shortDescription, description));
		formFields.put("jar", Arrays.asList(language, javaVersion, jarFile));
		formFields.put("source", Arrays.asList(sourceFile, sourcePermissions));
		formFields.put("images", Arrays.asList(thumbnailFile, faviconFile, backgroundFile));
		//formFields.put("webstart", Arrays.asList(mainClass, webstartWidth, webstartHeight));
		//formFields.put("applet", Arrays.asList(appletClass, appletWidth, appletHeight, appletDescription));
		formFields.put("rating", Arrays.asList(rating));
		formFields.put("advertising", Arrays.asList(adText, showAdBorder));
		//formFields.put("deprecated", Arrays.asList(lwjgl, signed));
		formFields.put("android", Arrays.asList(android, apkFile, androidText));
		formFields.put("publish", Arrays.asList(published));

		formFields.put("libGdxHtml", Arrays.asList(showLibGdxHtmlLink, libGdxHtmlFile, libGdxHtmlMode, libGdxHtmlText));
		formFields.put("processingJavaScript", Arrays.asList(showProcessingJavaScript, processingJavaScriptFile, processingJavaScriptMode, processingJavaScriptText));
		formFields.put("javaScript", Arrays.asList(showJavaScript, javaScriptFile, javaScriptIndex));
	}

	/**
	 * Checks to make sure the member can edit the game and returns the index view.
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String showIndex(@PathVariable("game") String game, HttpSession session, ModelMap model) {

		Game gameObj = gameDao.getGame(game);

		if(gameObj == null){
			return "redirect:/games/";
		}

		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);

		if(!gameObj.getMember().equals(loggedInMember)){
			return "redirect:/games/"+game;
		}

		if(gameObj.getBackgroundUrl() != null){
			String s3Endpoint = env.getProperty("s3.endpoint");
			model.addAttribute("backgroundImage", s3Endpoint + "/games/" + game + "/" + gameObj.getBackgroundUrl());
		}

		if(gameObj.getFaviconUrl() != null){
			String s3Endpoint = env.getProperty("s3.endpoint");
			model.addAttribute("faviconImage", s3Endpoint + "/games/" + game + "/" + gameObj.getFaviconUrl());
		}
		else{
			model.addAttribute("faviconImage", "http://StaticVoidGames.com/images/favicon.png");
		}

		session.setAttribute("gameObj", gameObj);

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/editGame/index.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/EditGameController.java")
		}
				);

		return "editGame/index";
	}




	/**
	 * Handles the submission of a game edit page.
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public String post(@PathVariable("game") String game,  @ModelAttribute("gameForm") GameForm gameForm, HttpSession session, HttpServletRequest request) {

		System.out.println("here");

		Game gameObj = gameDao.getGame(game);

		if(gameObj == null){
			return "redirect:/games/";
		}

		String loggedInMember = (String) session.getAttribute(AttributeNames.loggedInUser);

		if(!gameObj.getMember().equals(loggedInMember)){
			return "redirect:/games/"+game;
		}

		boolean redoLibGdxHtml = false;
		boolean redoProcessingJavaScript = false;
		
		
		String awsAccessKey = env.getProperty("aws.accessKey");
		String awsSecretKey = env.getProperty("aws.secretKey");
		String bucket = env.getProperty("s3.bucket");
		AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));


		//TODO: probably a smarter way to do this?

		if(gameForm.getTitle() != null && !gameForm.getTitle().equals(gameObj.getTitle())){
			gameObj.setTitle(gameForm.getTitle());
			redoLibGdxHtml = true;
			redoProcessingJavaScript = true;
		}

		if(gameForm.getDescription() != null && !gameForm.getDescription().equals(gameObj.getGameDescription())){
			gameObj.setGameDescription(gameForm.getDescription());
		}

		if(gameForm.getShortDescription() != null && !gameForm.getShortDescription().equals(gameObj.getShortDescription())){
			gameObj.setShortDescription(gameForm.getShortDescription());
		}

		//TODO: remove game websites, people can put it in the description
		if(gameForm.getWebsite() != null){
			gameObj.setWebsite(gameForm.getWebsite());
		}

		if(gameForm.getMainClass() != null){
			gameObj.setMainClass(gameForm.getMainClass());
		}

		if(gameForm.getWebstartWidth() != null){
			gameObj.setWebstartWidth(gameForm.getWebstartWidth());
		}

		if(gameForm.getWebstartHeight() != null){
			gameObj.setWebstartHeight(gameForm.getWebstartHeight());
		}

		if(gameForm.getAppletClass() != null){
			gameObj.setAppletClass(gameForm.getAppletClass());
		}

		if(gameForm.getAppletWidth() != null){
			gameObj.setAppletWidth(gameForm.getAppletWidth());
		}

		if(gameForm.getAppletHeight() != null){
			gameObj.setAppletHeight(gameForm.getAppletHeight());
		}

		if(gameForm.getAppletDescription() != null){
			gameObj.setAppletDescription(gameForm.getAppletDescription());
		}

		if(gameForm.getJavaVersion() != null){
			gameObj.setJavaVersion(gameForm.getJavaVersion());
		}

		if(gameForm.getRating() != null){
			gameObj.setRating(gameForm.getRating());
		}

		if(gameForm.getLanguage() != null){
			gameObj.setLanguage(gameForm.getLanguage());
		}

		if(gameForm.getAdText() != null && !gameForm.getAdText().equals(gameObj.getAdText())){
			gameObj.setAdText(gameForm.getAdText());
		}

		if(gameForm.getShowAdBorder() != null){
			gameObj.setShowAdBorder(gameForm.getShowAdBorder());
		}

		if(gameForm.getPublished() != null){
			gameObj.setPublished(gameForm.getPublished());
		}

		if(gameForm.getLwjgl() != null){
			gameObj.setLwjgl(gameForm.getLwjgl());
		}

		if(gameForm.getSigned() != null){
			gameObj.setSigned(gameForm.getSigned());
		}


		if(gameForm.getSourcePermissionsText() != null && !gameForm.getSourcePermissionsText().equals(gameObj.getSourcePermissionsText())){
			gameObj.setSourcePermissionsText(gameForm.getSourcePermissionsText());
		}

		if(gameForm.getApkFile() != null && !gameForm.getApkFile().isEmpty()){
			
			String key = "games/" + game + "/" + gameForm.getApkFile().getOriginalFilename();

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(gameForm.getApkFile().getSize());

			try{
				s3.putObject(bucket, key, gameForm.getApkFile().getInputStream(), meta);
				s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);
				gameObj.setApkUrl(gameForm.getApkFile().getOriginalFilename());
			}
			catch(Exception e){
				//TODO: let the user know something went wrong
				e.printStackTrace();
			}

			redoLibGdxHtml = true;
			redoProcessingJavaScript = true;
		
			
		}

		if(gameForm.getAndroidText() != null && !gameForm.getAndroidText().equals(gameObj.getAndroidText())){
			gameObj.setAndroidText(gameForm.getAndroidText());
			redoLibGdxHtml = true;
			redoProcessingJavaScript = true;
		}

		if(gameForm.getAndroid() != null && !gameForm.getAndroid().equals(gameObj.isAndroid())){
			gameObj.setAndroid(gameForm.getAndroid());
			redoLibGdxHtml = true;
			redoProcessingJavaScript = true;
		}

		if(gameForm.getLibGdxHtmlMode() != null && !gameForm.getLibGdxHtmlMode().equals(gameObj.getLibGdxHtmlMode())){
			gameObj.setLibGdxHtmlMode(gameForm.getLibGdxHtmlMode());
			redoLibGdxHtml = true;
		}

		if(gameForm.getShowLibGdxHtml() != null){
			System.out.println("Setting game.showLibGdxHtml: " + gameForm.getShowLibGdxHtml());
			gameObj.setShowLibGdxHtml(gameForm.getShowLibGdxHtml());
		}

		if(gameForm.getLibGdxHtmlText() != null && !gameForm.getLibGdxHtmlText().equals(gameObj.getLibGdxHtmlText())){
			gameObj.setLibGdxHtmlText(gameForm.getLibGdxHtmlText());
			redoLibGdxHtml = true;
		}


		if(gameForm.getProcessingJavaScriptMode() != null && !gameForm.getProcessingJavaScriptMode().equals(gameObj.getProcessingJavaScriptMode())){
			gameObj.setProcessingJavaScriptMode(gameForm.getProcessingJavaScriptMode());
			redoProcessingJavaScript = true;
		}

		if(gameForm.getShowProcessingJavaScript() != null){
			gameObj.setShowProcessingJavaScript(gameForm.getShowProcessingJavaScript());
		}

		if(gameForm.getProcessingJavaScriptText() != null && !gameForm.getProcessingJavaScriptText().equals(gameObj.getProcessingJavaScriptText())){
			gameObj.setProcessingJavaScriptText(gameForm.getProcessingJavaScriptText());
			redoProcessingJavaScript = true;
		}

		if(gameForm.getShowJavaScript() != null){
			gameObj.setShowJavaScript(gameForm.getShowJavaScript());
		}

		if(gameForm.getJavaScriptIndex() != null && !gameForm.getJavaScriptIndex().equals(gameObj.getJavaScriptIndex())){
			gameObj.setJavaScriptIndex(gameForm.getJavaScriptIndex());
		}

		

		if(gameForm.getJarFile() != null && !gameForm.getJarFile().isEmpty()){

			String key = "games/" + game + "/" + gameForm.getJarFile().getOriginalFilename();

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(gameForm.getJarFile().getSize());

			try{
				s3.putObject(bucket, key, gameForm.getJarFile().getInputStream(), meta);
				s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);
				gameObj.setJarFileUrl(gameForm.getJarFile().getOriginalFilename());
			}
			catch(Exception e){
				//TODO: let the user know something went wrong
				e.printStackTrace();
			}

			redoLibGdxHtml = true;
			redoProcessingJavaScript = true;
		}

		if(gameForm.getSource() != null && !gameForm.getSource().isEmpty()){

			String key = "games/" + game + "/" + gameForm.getSource().getOriginalFilename();

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(gameForm.getSource().getSize());

			try{
				s3.putObject(bucket, key, gameForm.getSource().getInputStream(), meta);
				s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);
				gameObj.setSourceZipUrl(gameForm.getSource().getOriginalFilename());
			}
			catch(Exception e){
				//TODO: let the user know something went wrong
				e.printStackTrace();
			}

			redoLibGdxHtml = true;
			redoProcessingJavaScript = true;
		}

		if(gameForm.getFaviconFile() != null && !gameForm.getFaviconFile().isEmpty()){

			String key = "games/" + game + "/" + gameForm.getFaviconFile().getOriginalFilename();

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(gameForm.getFaviconFile().getSize());

			try{
				s3.putObject(bucket, key, gameForm.getFaviconFile().getInputStream(), meta);
				s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);
				gameObj.setFaviconUrl(gameForm.getFaviconFile().getOriginalFilename());
			}
			catch(Exception e){
				//TODO: let the user know something went wrong
				e.printStackTrace();
			}

			redoLibGdxHtml = true;
			redoProcessingJavaScript = true;
		}

		if(gameForm.getBackgroundFile() != null && !gameForm.getBackgroundFile().isEmpty()){

			String key = "games/" + game + "/" + gameForm.getBackgroundFile().getOriginalFilename();

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(gameForm.getBackgroundFile().getSize());

			try{
				s3.putObject(bucket, key, gameForm.getBackgroundFile().getInputStream(), meta);
				s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);
				gameObj.setBackgroundUrl(gameForm.getBackgroundFile().getOriginalFilename());
			}
			catch(Exception e){
				//TODO: let the user know something went wrong
				e.printStackTrace();
			}

			redoLibGdxHtml = true;
			redoProcessingJavaScript = true;
		}


		if(gameForm.getThumbnailFile() != null && !gameForm.getThumbnailFile().isEmpty()){

			String key = "games/" + game + "/" + gameForm.getThumbnailFile().getOriginalFilename();

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(gameForm.getThumbnailFile().getSize());

			try{
				s3.putObject(bucket, key, gameForm.getThumbnailFile().getInputStream(), meta);
				s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);


				gameObj.setThumbnailUrl(gameForm.getThumbnailFile().getOriginalFilename());
			}
			catch(Exception e){
				//TODO: let the user know something went wrong
				e.printStackTrace();
			}

			redoLibGdxHtml = true;
			redoProcessingJavaScript = true;
		}


		if(gameForm.getLibgdxHtmlFile() != null && !gameForm.getLibgdxHtmlFile().isEmpty()){

			DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucket);
			ObjectListing ol =s3.listObjects(bucket, "games/" + game + "/gdx/"); 

			List<String> keys = new ArrayList<String>();
			for(S3ObjectSummary os : ol.getObjectSummaries()){
				keys.add(os.getKey());
			}
			deleteRequest.withKeys(keys.toArray(new String[]{}));
			s3.deleteObjects(deleteRequest);

			MultipartFile cmf = gameForm.getLibgdxHtmlFile();

			try{
				byte[] buffer = new byte[1024];
				ZipInputStream zip = new ZipInputStream(cmf.getInputStream());
				ZipEntry ze = zip.getNextEntry();

				while(ze!=null){

					File tempFile = File.createTempFile(gameObj.getGameName(), "tmp");

					FileOutputStream fos = new FileOutputStream(tempFile);             

					int len;
					while ((len = zip.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}

					fos.close();   

					String key = "games/" + game + "/gdx/" + ze.getName();
					if(ze.getName().startsWith("dist/")){
						key = "games/" + game + "/gdx/" + ze.getName().substring(5);
					}
					else{
						//TODO error, zip file is not formatted correctly
					}


					ObjectMetadata meta = new ObjectMetadata();
					meta.setContentLength(tempFile.length());

					ConfigurableMimeFileTypeMap mimeMap = new ConfigurableMimeFileTypeMap();
					String type = mimeMap.getContentType(ze.getName());

					meta.setContentType(type);

					FileInputStream fis = new FileInputStream(tempFile);

					s3.putObject(bucket, key, fis, meta);
					s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);

					ze = zip.getNextEntry();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}

			//we just overwrote whatever file was there before, let's redo it
			redoLibGdxHtml = true;
		}


		if(gameForm.getProcessingJavaScriptFile() != null && !gameForm.getProcessingJavaScriptFile().isEmpty()){

			DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucket);
			ObjectListing ol = s3.listObjects(bucket, "games/" + game + "/p5/"); 

			List<String> keys = new ArrayList<String>();
			for(S3ObjectSummary os : ol.getObjectSummaries()){
				System.out.println("key: " + os.getKey());
				keys.add(os.getKey());
			}
			if(!keys.isEmpty()){
				deleteRequest.withKeys(keys.toArray(new String[]{}));
				s3.deleteObjects(deleteRequest);
			}
			MultipartFile cmf = gameForm.getProcessingJavaScriptFile();

			try{
				byte[] buffer = new byte[1024];
				ZipInputStream zip = new ZipInputStream(cmf.getInputStream());
				ZipEntry ze = zip.getNextEntry();

				while(ze!=null){

					File tempFile = File.createTempFile(gameObj.getGameName(), "tmp");

					FileOutputStream fos = new FileOutputStream(tempFile);             

					int len;
					while ((len = zip.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}

					fos.close();   

					String key = "games/" + game + "/p5/" + ze.getName();
					if(ze.getName().startsWith("web-export/")){
						key = "games/" + game + "/p5/" + ze.getName().substring(11);
					}
					else{
						//TODO error, zip file is not formatted correctly
					}

					if(key.endsWith(".pde")){
						String processingSketchName = key.substring(key.lastIndexOf("/")+1, key.length()-4);
						System.out.println("Sketch name: " + processingSketchName);
						gameObj.setProcessingSketchName(processingSketchName);
					}

					ObjectMetadata meta = new ObjectMetadata();
					meta.setContentLength(tempFile.length());

					ConfigurableMimeFileTypeMap mimeMap = new ConfigurableMimeFileTypeMap();
					String type = mimeMap.getContentType(ze.getName());

					meta.setContentType(type);

					FileInputStream fis = new FileInputStream(tempFile);

					s3.putObject(bucket, key, fis, meta);
					s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);

					ze = zip.getNextEntry();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}

			//we just overwrote whatever file was there before, let's redo it
			redoProcessingJavaScript = true;
		}


		if(gameForm.getJavaScriptFile() != null && !gameForm.getJavaScriptFile().isEmpty()){

			DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucket);
			ObjectListing ol = s3.listObjects(bucket, "games/" + game + "/js/"); 

			List<String> keys = new ArrayList<String>();
			for(S3ObjectSummary os : ol.getObjectSummaries()){
				keys.add(os.getKey());
			}
			if(!keys.isEmpty()){
				deleteRequest.withKeys(keys.toArray(new String[]{}));
				s3.deleteObjects(deleteRequest);
			}
			MultipartFile cmf = gameForm.getJavaScriptFile();

			try{
				byte[] buffer = new byte[1024];
				ZipInputStream zip = new ZipInputStream(cmf.getInputStream());
				ZipEntry ze = zip.getNextEntry();

				while(ze!=null){

					File tempFile = File.createTempFile(gameObj.getGameName(), "tmp");

					FileOutputStream fos = new FileOutputStream(tempFile);             

					int len;
					while ((len = zip.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}

					fos.close();   

					String key = "games/" + game + "/js/" + ze.getName();

					ObjectMetadata meta = new ObjectMetadata();
					meta.setContentLength(tempFile.length());

					ConfigurableMimeFileTypeMap mimeMap = new ConfigurableMimeFileTypeMap();
					String type = mimeMap.getContentType(ze.getName());

					meta.setContentType(type);

					FileInputStream fis = new FileInputStream(tempFile);

					s3.putObject(bucket, key, fis, meta);
					s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);

					ze = zip.getNextEntry();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}


		if(gameObj.isShowLibGdxHtml() && redoLibGdxHtml){
			redoLibGdxHtml(gameObj);
		}

		if(gameObj.isShowProcessingJavaScript() && redoProcessingJavaScript){
			redoProcessingJavaScript(gameObj);
		}

		gameDao.updateGame(gameObj);
		return "redirect:/games/"+game +"/edit";
	}

	private void redoLibGdxHtml(Game game){

		StringBuilder minimalStringBuilder = new StringBuilder();
		minimalStringBuilder.append("<!doctype html>\n");
		minimalStringBuilder.append("<html>\n");

		minimalStringBuilder.append("<head>");

		minimalStringBuilder.append("<title>");
		minimalStringBuilder.append(game.getTitle());
		minimalStringBuilder.append("</title>\n");

		minimalStringBuilder.append("<link href=\"styles.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		minimalStringBuilder.append("<script src=\"soundmanager2-setup.js\"></script>\n");
		minimalStringBuilder.append("<script src=\"soundmanager2-jsmin.js\"></script>"); 

		if(game.getFaviconUrl() == null){
			minimalStringBuilder.append("<link rel=\"shortcut icon\" href=\"http://s3.staticvoidgames.com/images/favicon.png\" />\n");
		}
		else{
			minimalStringBuilder.append("<link rel=\"shortcut icon\" href=\"../" + game.getFaviconUrl() + "\" />\n");
		}

		minimalStringBuilder.append("</head>\n");

		minimalStringBuilder.append("<body>\n");

		minimalStringBuilder.append("<div align=\"center\" id=\"embed-html\"></div>\n");

		minimalStringBuilder.append("<script type=\"text/javascript\" src=\"html/html.nocache.js\"></script>\n");

		minimalStringBuilder.append("</body>\n");

		minimalStringBuilder.append("</html>");

		String key1 = "games/" + game.getGameName() + "/gdx/min.html";
		boolean success1 = uploadTextAsFile(key1, minimalStringBuilder.toString());

		if(success1 && "minimal".equals(game.getLibGdxHtmlMode())){
			String awsAccessKey = env.getProperty("aws.accessKey");
			String awsSecretKey = env.getProperty("aws.secretKey");
			String bucket = env.getProperty("s3.bucket");

			AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));

			s3.copyObject(bucket, key1, bucket, "games/" + game.getGameName() + "/gdx/index.html");
			s3.setObjectAcl(bucket, "games/" + game.getGameName() + "/gdx/index.html", CannedAccessControlList.PublicRead);
		}



		//else if("StaticVoidGames".equals(game.getLibGdxHtmlMode())){

		StringBuilder svgStringBuilder = new StringBuilder();

		svgStringBuilder.append("<!doctype html>\n");
		svgStringBuilder.append("<html>\n");

		svgStringBuilder.append("<head>");

		svgStringBuilder.append("<title>");
		svgStringBuilder.append(game.getTitle());
		svgStringBuilder.append("</title>\n");


		svgStringBuilder.append("<link href=\"http://StaticVoidGames.com/css/general.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		svgStringBuilder.append("<link href=\"http://StaticVoidGames.com/css/everyPage.css\" rel=\"stylesheet\" type=\"text/css\">\n");


		svgStringBuilder.append("<link href=\"styles.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		svgStringBuilder.append("<script src=\"soundmanager2-setup.js\"></script>\n");
		svgStringBuilder.append("<script src=\"soundmanager2-jsmin.js\"></script>\n"); 

		if(game.getFaviconUrl() == null){
			svgStringBuilder.append("<link rel=\"shortcut icon\" href=\"http://s3.staticvoidgames.com/images/favicon.png\" />\n");
		}
		else{
			svgStringBuilder.append("<link rel=\"shortcut icon\" href=\"../" + game.getFaviconUrl() + "\" />\n");
		}

		svgStringBuilder.append("</head>\n");

		if(game.getBackgroundUrl() == null){
			svgStringBuilder.append("<body style=\"background:black\">\n");
		}
		else{
			svgStringBuilder.append("<body style=\"background-size:auto; background-image:url(../" + game.getBackgroundUrl() + ");\">\n");
		}


		svgStringBuilder.append("<div id=\"topBar\">\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com\"><img id=\"siteLogo\" src=\"http://s3.staticvoidgames.com/images/StaticVoidGamesLogo3.png\" /></a>\n");
		svgStringBuilder.append("<div id=\"topBarText\"><br/>\n");
		svgStringBuilder.append("<div id=\"navButtons\">\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/games\">Play</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/tutorials\">Tutorials</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/games/new\">Upload</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/about\">About</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/blog\">Blog</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/irc\">IRC</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/JarMatey\">JarMatey</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/events\">Events</a>\n");
		svgStringBuilder.append("</div>\n");//end navButtons
		svgStringBuilder.append("</div>\n"); //end topBarText
		svgStringBuilder.append("<a href=\"http://www.facebook.com/StaticVoidGames\" target=\"_blank\"><img style=\"height:50px; float:right\" src=\"http://s3.StaticVoidGames.com/images/FacebookLogo.png\" /></a>\n");
		svgStringBuilder.append("<a href=\"https://twitter.com/StaticVoidGames\" target=\"_blank\"><img style=\"height:50px; float:right\" src=\"http://s3.StaticVoidGames.com/images/TwitterBird.png\" /></a>\n");

		svgStringBuilder.append("</div>\n"); //end topBar

		svgStringBuilder.append("<hr />\n");


		svgStringBuilder.append("<div align=\"center\"><h1><a href=\"http://StaticVoidGames.com/games/"+ game.getGameName() + "\">" + game.getTitle() + "</a></h1></div>\n");

		svgStringBuilder.append("<div align=\"center\" id=\"embed-html\"></div>\n");

		svgStringBuilder.append("<script type=\"text/javascript\" src=\"html/html.nocache.js\"></script>\n");



		svgStringBuilder.append("<div class=\"darkBackground centered\">\n");
		svgStringBuilder.append(game.getLibGdxHtmlText());
		svgStringBuilder.append("</div>\n");

		svgStringBuilder.append("<hr />\n");

		svgStringBuilder.append("<div class=\"darkBackground centered\">\n");

		svgStringBuilder.append("<div>\n");
		svgStringBuilder.append("<p>This is a game by <a href=\"http://StaticVoidGames.com/members/" + game.getMember() + "\">" + game.getMember() + "</a>.</p>\n");
		svgStringBuilder.append("</div>\n");


		if(game.getJarFileUrl() != null){
			svgStringBuilder.append("<div>\n");
			svgStringBuilder.append("<p>You can play this game offline by downloading the jar from <a href=\"../" + game.getJarFileUrl() + "\">here</a>.</p>");
			svgStringBuilder.append("</div>\n");
		}

		if(game.isAndroid()){

			if(game.getApkUrl() != null){
				svgStringBuilder.append("<div>\n");
				svgStringBuilder.append("<p>You can play this game on Android by downloading the APK from <a href=\"../" + game.getApkUrl() + "\">here</a>.</p>");
				svgStringBuilder.append("</div>\n");
			}

			if(game.getAndroidText() != null){
				svgStringBuilder.append("<div>\n");
				svgStringBuilder.append(PageDownUtils.getSanitizedHtml(game.getAndroidText()));
				svgStringBuilder.append("</div>\n");
			}
		}

		svgStringBuilder.append("</div>\n");


		svgStringBuilder.append("</body>\n");

		svgStringBuilder.append("</html>");

		String key2 = "games/" + game.getGameName() + "/gdx/play.html";
		boolean success2 = uploadTextAsFile(key2, svgStringBuilder.toString());

		if(success2 && "StaticVoidGames".equals(game.getLibGdxHtmlMode())){
			String awsAccessKey = env.getProperty("aws.accessKey");
			String awsSecretKey = env.getProperty("aws.secretKey");
			String bucket = env.getProperty("s3.bucket");

			AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));

			s3.copyObject(bucket, key2, bucket, "games/" + game.getGameName() + "/gdx/index.html");
			s3.setObjectAcl(bucket, "games/" + game.getGameName() + "/gdx/index.html", CannedAccessControlList.PublicRead);
		}
	}

	private void redoProcessingJavaScript(Game game){

		StringBuilder minimalStringBuilder = new StringBuilder();
		minimalStringBuilder.append("<!doctype html>\n");
		minimalStringBuilder.append("<html>\n");

		minimalStringBuilder.append("<head>");

		minimalStringBuilder.append("<title>");
		minimalStringBuilder.append(game.getTitle());
		minimalStringBuilder.append("</title>\n");

		minimalStringBuilder.append("<link href=\"styles.css\" rel=\"stylesheet\" type=\"text/css\">\n");

		minimalStringBuilder.append("<script src=\"processing.js\" type=\"text/javascript\"></script>\n");
		minimalStringBuilder.append("<script type=\"text/javascript\">function getProcessingSketchId () { return '" + game.getProcessingSketchName() + "'; }</script>\n"); 

		if(game.getFaviconUrl() == null){
			minimalStringBuilder.append("<link rel=\"shortcut icon\" href=\"http://s3.staticvoidgames.com/images/favicon.png\" />\n");
		}
		else{
			minimalStringBuilder.append("<link rel=\"shortcut icon\" href=\"../" + game.getFaviconUrl() + "\" />\n");
		}

		minimalStringBuilder.append("</head>\n");

		minimalStringBuilder.append("<body>\n");

		minimalStringBuilder.append("<canvas id=\"" + game.getProcessingSketchName() + "\" data-processing-sources=\"" + game.getProcessingSketchName() + ".pde\"></canvas>\n");



		minimalStringBuilder.append("</body>\n");

		minimalStringBuilder.append("</html>");

		String key1 = "games/" + game.getGameName() + "/p5/min.html";
		boolean success1 = uploadTextAsFile(key1, minimalStringBuilder.toString());

		if(success1 && "minimal".equals(game.getProcessingJavaScriptMode())){
			String awsAccessKey = env.getProperty("aws.accessKey");
			String awsSecretKey = env.getProperty("aws.secretKey");
			String bucket = env.getProperty("s3.bucket");

			AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));

			s3.copyObject(bucket, key1, bucket, "games/" + game.getGameName() + "/p5/index.html");
			s3.setObjectAcl(bucket, "games/" + game.getGameName() + "/p5/index.html", CannedAccessControlList.PublicRead);
		}



		//else if("StaticVoidGames".equals(game.getLibGdxHtmlMode())){

		StringBuilder svgStringBuilder = new StringBuilder();

		svgStringBuilder.append("<!doctype html>\n");
		svgStringBuilder.append("<html>\n");

		svgStringBuilder.append("<head>");

		svgStringBuilder.append("<title>");
		svgStringBuilder.append(game.getTitle());
		svgStringBuilder.append("</title>\n");


		svgStringBuilder.append("<link href=\"http://StaticVoidGames.com/css/general.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		svgStringBuilder.append("<link href=\"http://StaticVoidGames.com/css/everyPage.css\" rel=\"stylesheet\" type=\"text/css\">\n");


		svgStringBuilder.append("<link href=\"styles.css\" rel=\"stylesheet\" type=\"text/css\">\n");

		svgStringBuilder.append("<script src=\"processing.js\" type=\"text/javascript\"></script>\n");
		svgStringBuilder.append("<script type=\"text/javascript\">function getProcessingSketchId () { return '" + game.getProcessingSketchName() + "'; }</script>\n"); 

		if(game.getFaviconUrl() == null){
			svgStringBuilder.append("<link rel=\"shortcut icon\" href=\"http://s3.staticvoidgames.com/images/favicon.png\" />\n");
		}
		else{
			svgStringBuilder.append("<link rel=\"shortcut icon\" href=\"../" + game.getFaviconUrl() + "\" />\n");
		}

		svgStringBuilder.append("</head>\n");

		if(game.getBackgroundUrl() == null){
			svgStringBuilder.append("<body style=\"background:black\">\n");
		}
		else{
			svgStringBuilder.append("<body style=\"background-size:auto; background-image:url(../" + game.getBackgroundUrl() + ");\">\n");
		}


		svgStringBuilder.append("<div id=\"topBar\">\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com\"><img id=\"siteLogo\" src=\"http://s3.staticvoidgames.com/images/StaticVoidGamesLogo3.png\" /></a>\n");
		svgStringBuilder.append("<div id=\"topBarText\"><br/>\n");
		svgStringBuilder.append("<div id=\"navButtons\">\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/games\">Play</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/tutorials\">Tutorials</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/games/new\">Upload</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/about\">About</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/blog\">Blog</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/irc\">IRC</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/JarMatey\">JarMatey</a>\n");
		svgStringBuilder.append("<a href=\"http://StaticVoidGames.com/events\">Events</a>\n");
		svgStringBuilder.append("</div>\n");//end navButtons
		svgStringBuilder.append("</div>\n"); //end topBarText
		svgStringBuilder.append("<a href=\"http://www.facebook.com/StaticVoidGames\" target=\"_blank\"><img style=\"height:50px; float:right\" src=\"http://s3.StaticVoidGames.com/images/FacebookLogo.png\" /></a>\n");
		svgStringBuilder.append("<a href=\"https://twitter.com/StaticVoidGames\" target=\"_blank\"><img style=\"height:50px; float:right\" src=\"http://s3.StaticVoidGames.com/images/TwitterBird.png\" /></a>\n");

		svgStringBuilder.append("</div>\n"); //end topBar

		svgStringBuilder.append("<hr />\n");


		svgStringBuilder.append("<div align=\"center\"><h1><a href=\"http://StaticVoidGames.com/games/"+ game.getGameName() + "\">" + game.getTitle() + "</a></h1></div>\n");


		svgStringBuilder.append("<div align=\"center\"><canvas style=\"border:thin solid white;\" id=\"" + game.getProcessingSketchName() + "\" data-processing-sources=\"" + game.getProcessingSketchName() + ".pde\"></canvas></div>\n");


		svgStringBuilder.append("<div class=\"darkBackground centered\">\n");
		svgStringBuilder.append(game.getProcessingJavaScriptText());
		svgStringBuilder.append("</div>\n");

		svgStringBuilder.append("<hr />\n");

		svgStringBuilder.append("<div class=\"darkBackground centered\">\n");

		svgStringBuilder.append("<div>\n");
		svgStringBuilder.append("<p>This is a game by <a href=\"http://StaticVoidGames.com/members/" + game.getMember() + "\">" + game.getMember() + "</a>.</p>\n");
		svgStringBuilder.append("</div>\n");


		if(game.getJarFileUrl() != null){
			svgStringBuilder.append("<div>\n");
			svgStringBuilder.append("<p>You can play this game offline by downloading the jar from <a href=\"../" + game.getJarFileUrl() + "\">here</a>.</p>");
			svgStringBuilder.append("</div>\n");
		}

		if(game.isAndroid()){
			if(game.getApkUrl() != null){
				svgStringBuilder.append("<div>\n");
				svgStringBuilder.append("<p>You can play this game on Android by downloading the APK from <a href=\"../" + game.getApkUrl() + "\">here</a>.</p>");
				svgStringBuilder.append("</div>\n");
			}

			if(game.getAndroidText() != null){
				svgStringBuilder.append("<div>\n");
				svgStringBuilder.append(PageDownUtils.getSanitizedHtml(game.getAndroidText()));
				svgStringBuilder.append("</div>\n");
			}
		}

		svgStringBuilder.append("</div>\n");


		svgStringBuilder.append("</body>\n");

		svgStringBuilder.append("</html>");

		String key2 = "games/" + game.getGameName() + "/p5/play.html";
		boolean success2 = uploadTextAsFile(key2, svgStringBuilder.toString());

		if(success2 && "StaticVoidGames".equals(game.getProcessingJavaScriptMode())){
			String awsAccessKey = env.getProperty("aws.accessKey");
			String awsSecretKey = env.getProperty("aws.secretKey");
			String bucket = env.getProperty("s3.bucket");

			AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));

			s3.copyObject(bucket, key2, bucket, "games/" + game.getGameName() + "/p5/index.html");
			s3.setObjectAcl(bucket, "games/" + game.getGameName() + "/p5/index.html", CannedAccessControlList.PublicRead);
		}
	}



	private boolean uploadTextAsFile(String key, String contents){
		try{
			byte[] bytes = contents.getBytes("UTF-8");
			InputStream is = new ByteArrayInputStream(bytes);

			String bucket = "s3.staticvoidgames.com";


			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(bytes.length);
			meta.setContentType("text/html");

			String awsAccessKey = env.getProperty("aws.accessKey");
			String awsSecretKey = env.getProperty("aws.secretKey");
			AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(awsAccessKey, awsSecretKey));

			s3.putObject(bucket, key, is, meta);
			s3.setObjectAcl(bucket, key, CannedAccessControlList.PublicRead);
		}
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Returns the view for a particular game edit page.
	 */
	@Override
	@RequestMapping(value="/{section}", method=RequestMethod.GET)
	public String editSection(@PathVariable(value="game") String game, @PathVariable(value="section") String section, ModelMap model, HttpSession session, HttpServletRequest request){

		Game gameObj = gameDao.getGame(game);

		if(gameObj == null){
			return "redirect:/games/";
		}

		String loggedInMember = (String) request.getSession().getAttribute(AttributeNames.loggedInUser);

		if(!gameObj.getMember().equals(loggedInMember)){
			return "redirect:/games/"+game;
		}

		if(!formFields.containsKey(section)){
			return "redirect:/games/" + game + "/edit";
		}

		if(gameObj.getBackgroundUrl() != null){
			String s3Endpoint = env.getProperty("s3.endpoint");
			model.addAttribute("backgroundImage", s3Endpoint + "/games/" + game + "/" + gameObj.getBackgroundUrl());
		}

		if(gameObj.getFaviconUrl() != null){
			String s3Endpoint = env.getProperty("s3.endpoint");
			model.addAttribute("faviconImage", s3Endpoint + "/games/" + game + "/" + gameObj.getFaviconUrl());
		}

		model.addAttribute("gameForm", gameObj.getGameForm());
		model.addAttribute("formFields", formFields.get(section));
		model.addAttribute("gameObj", gameObj);

		model.addAttribute("openSourceLinks", 
				new OpenSourceLink[]{
				new OpenSourceLink("View this page's jsp code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/webapp/WEB-INF/jsp/editGame/editGame.jsp"),
				new OpenSourceLink("View this page's server code.", "https://github.com/KevinWorkman/StaticVoidGames/blob/master/StaticVoidGames/src/main/java/com/StaticVoidGames/spring/controller/EditGameController.java")
		}
				);

		return "editGame/editGame";
	}
}