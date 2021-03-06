package cn.dianduba.elearn;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Page {
	
	private int id;
	private int bookId;
	private int pageIndex;
	private int imageId;
	
//	private String imageUrl;
	
	@JsonIgnore
	private Resource resource; 
	
	private List<HotRegion> hotRegions = new ArrayList<HotRegion>();
	
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public String getImageUrl() {
		if (resource != null)
			return Config.getResourceBaseUrl() + resource.getUrl();
		return null;
//		return imageUrl;
	}

//	public void setImageUrl(String imageUrl) {
//		this.imageUrl = imageUrl;
//	}

	public List<HotRegion> getRegions() {
		return hotRegions;
	}

	public void setRegions(List<HotRegion> hotRegions) {
		this.hotRegions = hotRegions;
	}

	public void addHotRegion(HotRegion region) {
		hotRegions.add(region);
	}
	
	public void removeHotRegion(int index) {
		hotRegions.remove(index);
	}
	
	public void removeHotRegion(HotRegion region) {
		hotRegions.remove(region);
	}
	
	public HotRegion getHotRegion(int regionId) {
		for (HotRegion region : hotRegions) {
			if (region.getId() == regionId)
				return region;
			
		}
		
		return null;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	
}
