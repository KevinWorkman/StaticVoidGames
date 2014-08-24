package com.StaticVoidGames.games;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.StaticVoidGames.TimestampedEvent;


@Entity
@Table( name = "Games" )
public class Game implements TimestampedEvent{

	/**
	 * The ID of the game, also used in the url: StaticVoidGames.com/games/gameName
	 */
	@Id
	private String gameName;
	
	/**
	 * The member who uploaded the game.
	 */
	private String member;
	
	/**
	 * The title of the game.
	 */
	private String title;
	
	/**
	 * The short description or tagline to display in thumbnails.
	 */
	private String shortDescription;
	
	/**
	 * The alternate website of the game.
	 * TODO: Remove this? Nobody seems to really use it, plus they can just put a link in the description.
	 */
	private String website;
	
	/**
	 * The name of the jar file.
	 * This should not include any extra URL info, just the jar name.
	 */
	private String jarFileUrl;
	
	/**
	 * The name of the source zip file.
	 */
	private String sourceZipUrl;
	
	/**
	 * The name of the favicon image.
	 */
	private String faviconUrl;
	
	/**
	 * The name of the background image.
	 */
	private String backgroundUrl;
	
	/**
	 * The name of the thumbnail image.
	 */
	private String thumbnailUrl;
	
	/**
	 * The description of the game, in markdown.
	 */
	@Column(columnDefinition="long varchar")
	private String gameDescription;
	
	/**
	 * This is the javascript code to display as the user-defined ad.
	 * TODO: Have the user enter fill-in-the-blank style Google Adsense codes directly instead of supplying their own ad code?
	 */
	private String adText;
	
	/**
	 * Whether to show the ad border around the ad code.
	 * TODO: This should probably always be true when the user supplies an ad.
	 */
	private boolean showAdBorder;
	
	/**
	 * What language was the game written in?
	 * TODO: Should this be replaced by tags?
	 */
	private String language;
	
	/**
	 * The text to display in the source link box. This is used to specify an open-source license.
	 */
	private String sourcePermissionsText;
	
	/**
	 * Filename of the APK uploaded to the site.
	 */
	private String apkUrl;
	
	/**
	 * The text to display in the Android box. This probably contains a link to the Google Play store.
	 */
	private String androidText;
	
	/**
	 * Whether to show the Android box.
	 * TODO: Do we realyl need this? Shouldn't it be true whenever apkUrl or androidText are not null?
	 */
	private boolean android;
	
	/**
	 * The time (in MS since epoch) the game was uploaded.
	 */
	private long timestamp;
	
	/**
	 * Whether the game is published. Unpublished games should not be listed in the game listing or event pages.
	 */
	private boolean published;
	
	//the below settings are deprecated or in a state of flux right now
	
	/**
	 * @deprecated: This was used for the webstart jnlp. I haven't decided what to do with this after Java's new security settings. Maybe just serve up the jnlp file to the user directly?
	 */
	private String mainClass;
	private Integer webstartWidth;
	private Integer webstartHeight;
	
	/**
	 * @deprecated: This was used for the applet jnlp. I haven't decided what to do with this after Java's new security settings. Maybe just serve up the jnlp file to the user directly?
	 */
	private String appletClass;
	private Integer appletWidth;
	private Integer appletHeight;
	@Column(columnDefinition="long varchar")
	private String appletDescription;

	/**
	 * @deprecated This is used for the jnlp. Not sure what I want to do with this.
	 */
	private String javaVersion;
	
	/**
	 * @deprecated This was meant to be a content rating (safe for kids or just adults, etc). This was never really implemented.
	 */
	private String rating;
	
	/**
	 * @deprecated This was never actually used
	 */
	private String adPlacement;
	

	/**
	 * This is used to automatically add the LWJGL library jars to the JNLP.
	 */
	private boolean lwjgl;
	
	/**
	 * Whether the jar is signed. This was used for the JNLP. I need to see if this is still valid.
	 */
	private boolean signed;
	
	
	/**
	 * Creates a new game with the supplied values.
	 */
	public Game(String gameName, String member){
		this.gameName = gameName;
		this.member = member;
		
		this.timestamp = System.currentTimeMillis();
		this.showAdBorder = true;
		
		this.javaVersion = "1.7";
	}
	
	/**
	 * Empty constructor for Hibernate
	 */
	public Game(){}
	
	

	public boolean isLwjgl(){
		return lwjgl;
	}
	
	public void setLwjgl(boolean lwjgl){
		this.lwjgl = lwjgl;
	}
	
	public void setPublished(boolean published){
		this.published = published;
	}
	
	public boolean isPublished(){
		return published;
	}
	
	public void setAdText(String adText){
		this.adText = adText;
	}
	
	public String getAdText(){
		return adText;
	}
	
	public boolean getShowAdBorder(){
		return showAdBorder;
	}
	
	public void setShowAdBorder(boolean showAdBorder){
		this.showAdBorder = showAdBorder;
	}

	public String getLanguage(){
		return language;
	}
	
	public void setLanguage(String language){
		this.language = language;
	}
	
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getJarFileUrl() {
		return jarFileUrl;
	}

	public void setJarFileUrl(String jarFileUrl) {
		this.jarFileUrl = jarFileUrl;
	}

	public String getSourceZipUrl() {
		return sourceZipUrl;
	}

	public void setSourceZipUrl(String sourceZipUrl) {
		this.sourceZipUrl = sourceZipUrl;
	}

	public String getFaviconUrl() {
		return faviconUrl;
	}

	public void setFaviconUrl(String faviconUrl) {
		this.faviconUrl = faviconUrl;
	}

	public String getBackgroundUrl() {
		return backgroundUrl;
	}

	public void setBackgroundUrl(String backgroundUrl) {
		this.backgroundUrl = backgroundUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	
	
	public String getThumbnailView(){
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public Integer getWebstartWidth() {
		return webstartWidth;
	}

	public void setWebstartWidth(Integer webstartWidth) {
		this.webstartWidth = webstartWidth;
	}

	public Integer getWebstartHeight() {
		return webstartHeight;
	}

	public void setWebstartHeight(Integer webstartHeight) {
		this.webstartHeight = webstartHeight;
	}

	public String getAppletClass() {
		return appletClass;
	}

	public void setAppletClass(String appletClass) {
		this.appletClass = appletClass;
	}

	public Integer getAppletWidth() {
		return appletWidth;
	}

	public void setAppletWidth(Integer appletWidth) {
		this.appletWidth = appletWidth;
	}

	public Integer getAppletHeight() {
		return appletHeight;
	}

	public void setAppletHeight(Integer appletHeight) {
		this.appletHeight = appletHeight;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}

	public String getAppletDescription() {
		return appletDescription;
	}

	public void setAppletDescription(String appletDescription) {
		this.appletDescription = appletDescription;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getMember() {
		return member;
	}
	
	public String getSourcePermissionsText(){
		return sourcePermissionsText;
	}
	
	public void setSourcePermissionsText(String sourcePermissionsText){
		this.sourcePermissionsText = sourcePermissionsText;
	}
	
	public boolean isSigned(){
		return signed;
	}
	
	public void setSigned(boolean signed){
		this.signed = signed;
	}
	
	public void setApkUrl(String apkUrl){
		this.apkUrl = apkUrl;
	}
	
	public String getApkUrl(){
		return apkUrl;
	}

	public void setAndroidText(String androidText){
		this.androidText = androidText;
	}
	
	public String getAndroidText(){
		return androidText;
	}
	
	public void setAndroid(boolean android){
		this.android = android;
	}
	
	public boolean isAndroid(){
		return android;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public String getFormattedTimestamp(){
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa - EEEE, MMMM dd, yyyy");
		return format.format(getTimestamp());
	}
	
	/**
	 * Returns a GameForm populated with this game's values.
	 * This is used for the game edit pages.
	 * <br />TODO: probably a better way to do this, maybe with reflection?
	 */
	public GameForm getGameForm(){
		GameForm gf = new GameForm();
		gf.setAdPlacement(adPlacement);
		gf.setAdText(adText);
		gf.setAndroid(android);
		gf.setAndroidText(androidText);
		gf.setApkUrl(apkUrl);
		gf.setAppletClass(appletClass);
		gf.setAppletDescription(appletDescription);
		gf.setAppletHeight(appletHeight);
		gf.setAppletWidth(appletWidth);
	
		gf.setDescription(gameDescription);

		gf.setJavaVersion(javaVersion);
		gf.setLanguage(language);
		gf.setLwjgl(lwjgl);
		gf.setMainClass(mainClass);
		gf.setPublished(published);
		gf.setRating(rating);
		gf.setShortDescription(shortDescription);
		gf.setShowAdBorder(showAdBorder);
		gf.setSigned(signed);
		gf.setSourcePermissionsText(sourcePermissionsText);
		
		gf.setTitle(title);
		gf.setWebsite(website);
		gf.setWebstartHeight(webstartHeight);
		gf.setWebstartWidth(webstartWidth);
		
		return gf;
	}

	@Override
	public String getEventText() {
		return member + " uploaded " + title;
	}

	@Override
	public String getRelativeUrl() {
		return "/games/" + gameName;
	}

	public void setMember(String member) {
		this.member = member;
	}
}