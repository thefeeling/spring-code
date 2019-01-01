package org.springframework.app.boardApp.domain.model;

import java.sql.Timestamp;

import org.springframework.cglib.core.TinyBitSet;

public class Post {
	/**
	 * 임시생성 VO
	 */
	private int       id;
	private String    title;
	private String    text;
	private String    titleClean;
	private String    file;
	private int       authorId;
	private Timestamp datePublished;
	private String    bannerImage;
	private int   featured;
	private int   enabled;
	private int   commentsEnabled;
	private int   views;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the titleClean
	 */
	public String getTitleClean() {
		return titleClean;
	}
	/**
	 * @param titleClean the titleClean to set
	 */
	public void setTitleClean(String titleClean) {
		this.titleClean = titleClean;
	}
	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}
	/**
	 * @return the authorId
	 */
	public int getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	/**
	 * @return the datePublished
	 */
	public Timestamp getDatePublished() {
		return datePublished;
	}
	/**
	 * @param datePublished the datePublished to set
	 */
	public void setDatePublished(Timestamp datePublished) {
		this.datePublished = datePublished;
	}
	/**
	 * @return the bannerImage
	 */
	public String getBannerImage() {
		return bannerImage;
	}
	/**
	 * @param bannerImage the bannerImage to set
	 */
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}
	/**
	 * @return the featured
	 */
	public int getFeatured() {
		return featured;
	}
	/**
	 * @param featured the featured to set
	 */
	public void setFeatured(int featured) {
		this.featured = featured;
	}
	/**
	 * @return the enabled
	 */
	public int getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	/**
	 * @return the commentsEnabled
	 */
	public int getCommentsEnabled() {
		return commentsEnabled;
	}
	/**
	 * @param commentsEnabled the commentsEnabled to set
	 */
	public void setCommentsEnabled(int commentsEnabled) {
		this.commentsEnabled = commentsEnabled;
	}
	/**
	 * @return the views
	 */
	public int getViews() {
		return views;
	}
	/**
	 * @param views the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}

	
}
