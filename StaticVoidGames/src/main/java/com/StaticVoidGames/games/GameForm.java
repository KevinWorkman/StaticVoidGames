package com.StaticVoidGames.games;

import org.springframework.web.multipart.MultipartFile;

/**
 * Class used by the game edit pages. Forms shown to (and submitted by) the user are backed by an instance of this class, so all of the handling can be done in one place.
 * @see com.StaticVoidGames.spring.controller.EditGameController
 *
 */
public class GameForm {

	private String title;
	private String description;

	private String shortDescription;

	private String website;

	private MultipartFile jarFile;
	private MultipartFile source;

	private MultipartFile faviconFile;
	private MultipartFile backgroundFile;
	private MultipartFile thumbnailFile;

	private String mainClass;
	private Integer webstartWidth;
	private Integer webstartHeight;

	private String appletClass;
	private Integer appletWidth;
	private Integer appletHeight;


	private String appletDescription;

	private String javaVersion;

	private String rating;


	private String adText;
	private Boolean showAdBorder;
	private String adPlacement;

	private String language;

	private Boolean published;

	private Boolean lwjgl;
	private Boolean signed;

	private String sourcePermissionsText;

	private String apkUrl;
	private String androidText;
	private Boolean android;
	

	private Boolean showLibGdxHtml;
	private String libGdxHtmlMode;
	private MultipartFile libGdxHtmlFile;
	private String libGdxHtmlText;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public MultipartFile getJarFile() {
		return jarFile;
	}

	public void setJarFile(MultipartFile jarFile) {
		this.jarFile = jarFile;
	}

	public MultipartFile getSource() {
		return source;
	}

	public void setSource(MultipartFile source) {
		this.source = source;
	}

	public MultipartFile getFaviconFile() {
		return faviconFile;
	}

	public void setFaviconUrl(MultipartFile faviconFile) {
		this.faviconFile = faviconFile;
	}

	public MultipartFile getBackgroundFile() {
		return backgroundFile;
	}

	public void setBackgroundUrl(MultipartFile backgroundFile) {
		this.backgroundFile = backgroundFile;
	}

	public MultipartFile getThumbnailFile() {
		return thumbnailFile;
	}

	public void setThumbnailFile(MultipartFile thumbnailFile) {
		this.thumbnailFile = thumbnailFile;
	}
	
	public MultipartFile getLibgdxHtmlFile() {
		return libGdxHtmlFile;
	}

	public void setLibGdxHtmlFile(MultipartFile libGdxHtmlFile) {
		this.libGdxHtmlFile = libGdxHtmlFile;
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

	public String getAdText() {
		return adText;
	}

	public void setAdText(String adText) {
		this.adText = adText;
	}

	public Boolean getShowAdBorder() {
		return showAdBorder;
	}

	public void setShowAdBorder(Boolean showAdBorder) {
		this.showAdBorder = showAdBorder;
	}

	public String getAdPlacement() {
		return adPlacement;
	}

	public void setAdPlacement(String adPlacement) {
		this.adPlacement = adPlacement;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Boolean getLwjgl() {
		return lwjgl;
	}

	public void setLwjgl(Boolean lwjgl) {
		this.lwjgl = lwjgl;
	}

	public Boolean getSigned() {
		return signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	public String getSourcePermissionsText() {
		return sourcePermissionsText;
	}

	public void setSourcePermissionsText(String sourcePermissionsText) {
		this.sourcePermissionsText = sourcePermissionsText;
	}

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

	public String getAndroidText() {
		return androidText;
	}

	public void setAndroidText(String androidText) {
		this.androidText = androidText;
	}

	public Boolean getAndroid() {
		return android;
	}

	public void setAndroid(Boolean android) {
		this.android = android;
	}

	public Boolean getShowLibGdxHtml() {
		return showLibGdxHtml;
	}

	public void setShowLibGdxHtml(boolean showLibGdxHtml) {
		this.showLibGdxHtml = showLibGdxHtml;
	}

	public String getLibGdxHtmlMode() {
		return libGdxHtmlMode;
	}

	public void setLibGdxHtmlMode(String libGdxHtmlMode) {
		this.libGdxHtmlMode = libGdxHtmlMode;
	}

	public String getLibGdxHtmlText() {
		return libGdxHtmlText;
	}

	public void setLibGdxHtmlText(String libGdxHtmlText) {
		this.libGdxHtmlText = libGdxHtmlText;
	}
}
