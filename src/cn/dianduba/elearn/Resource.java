package cn.dianduba.elearn;

import java.util.Date;

public class Resource {

	private int id;
	private String url;
	private int type;
	private String filename;
	private Date createTime;
	private int catalog;
	
	public Resource() {
		
	}
	
	public Resource(int type, String filename, String url) {
		this.type = type;
		this.filename = filename;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getCatalog() {
		return catalog;
	}

	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}
	
	
}
