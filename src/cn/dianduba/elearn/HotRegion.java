package cn.dianduba.elearn;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HotRegion {
	
	private int id;
	private int pageId;
	private int x;
	private int y;
	private int width;
	private int height;
	private int regionIndex;
	private int type;
	private String english;
	private String chinese;
	private int mediaId;
	
	@JsonIgnore
	private Resource resource;
	
//	private String mediaUrl;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getMediaUrl() {
		if (resource != null)
			return Config.getResourceBaseUrl() + resource.getUrl();
		return null;
//		return mediaUrl;
	}

//	public void setMediaUrl(String mediaUrl) {
//		this.mediaUrl = mediaUrl;
//	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public int getRegionIndex() {
		return regionIndex;
	}

	public void setRegionIndex(int regionIndex) {
		this.regionIndex = regionIndex;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
	
	
}
