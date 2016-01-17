package cn.dianduba.elearn;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Book {
	
	private int id;
	private String isdn;
	private String name;
	private int startPageNo;
    private int endPageNo;
    private int starLevel;
    private int hits;
    private String introduction;
    private Date createTime;
    private int faceImageId;
    private Resource resource;
    
//    private String faceImageUrl;
    
	
	private Map<String, Page> pages = new HashMap<String, Page>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getIsdn() {
		return isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public String getFaceImageUrl() {
		if (resource != null)
			return Config.getResourceBaseUrl() + resource.getUrl();
		return null;
	}

//	public void setFaceImageUrl(String faceImageUrl) {
//		this.faceImageUrl = faceImageUrl;
//	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Map<String, Page> getPages() {
		return pages;
	}

	public void setPages(Map<String, Page> pages) {
		this.pages = pages;
	}

	public void addPage(int id, Page page) {
		pages.put(String.valueOf(id), page);
	}
	
	public Page getFirstPage() {
		return getPage(startPageNo);
	}
	
	public Page getEndPage() {
		return getPage(endPageNo);
	}
	
	public Page getPage(int id) {
		return pages.get(String.valueOf(id));
	}
	
	public Iterator<Page> listPages() {
		return pages.values().iterator();
	}

	public int getFaceImageId() {
		return faceImageId;
	}

	public void setFaceImageId(int faceImageId) {
		this.faceImageId = faceImageId;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
}
