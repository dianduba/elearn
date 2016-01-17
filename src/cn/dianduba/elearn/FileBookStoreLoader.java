package cn.dianduba.elearn;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class FileBookStoreLoader implements BookStoreLoader {
	private String bookStoreHome;
//	private String contextPath;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public FileBookStoreLoader(String bookStoreHome, String contextPath) {
		this.bookStoreHome = bookStoreHome;
//		this.contextPath = contextPath;
	}

	
	public void load(BookStore  bookStore) {
		if (bookStore == null)
			return;
		
		bookStore.clear();
		
		File home = new File(bookStoreHome);
		
		File[] bookFiles = home.listFiles();
		for (File file : bookFiles) {
			try {
				if (!file.isDirectory())
					continue;
				
				File bookInfoFile = new File(file, "book.xml");
				if (!bookInfoFile.exists())
					continue;
				
				Book book = new Book();
				loadBook(book, file.getName(), new FileInputStream(bookInfoFile));
				
				bookStore.addBook(book);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
//	private String buildPageUrl(Book book, Page page) {
//		return contextPath + "/pageImage.do?bookId=" + book.getId() + "&pageIndex=" + page.getPageIndex();
//	}
	
	private Resource buildPageResource(String bookHome, String filename) {
		return new Resource(0, filename, "/books/" + bookHome + "/" + filename);
	}
	
//	private String buildRegionUrl(Book book, Page page, HotRegion region) {
//		return contextPath + "/regionMedia.do?bookId=" + book.getId() + "&pageIndex=" + page.getPageIndex() + "&regionId=" + region.getId();
//	}
	
	private Resource buildRegionResrouce(String bookHome, String filename) {
		return new Resource(1, filename, "/books/" + bookHome + "/" + filename);
	}
	
	public void loadBook(Book book, String bookHome, InputStream stream) throws Exception {
        
        SAXReader reader = new SAXReader ();
        Document     doc = reader.read(new InputStreamReader(stream, "gbk"));
        
        Element  bookEle = doc.getRootElement();
        
        book.setName(bookEle.attributeValue("title"));
        
        book.setId(Integer.parseInt(bookEle.attributeValue("isbn")));
        book.setIsdn(bookEle.attributeValue("isbn"));
        book.setCreateTime(dateFormat.parse(bookEle.attributeValue("createTime")));
        book.setIntroduction(bookEle.elementTextTrim("introduction"));
        book.setStarLevel(Integer.parseInt(bookEle.attributeValue("starLevel")));
        book.setStartPageNo(Integer.parseInt(bookEle.attributeValue("startPageNo")));
        book.setEndPageNo(Integer.parseInt(bookEle.attributeValue("endPageNo")));
//        book.setFaceImageUrl(contextPath + "/books/" + bookHome + "/" + bookEle.attributeValue("faceImage"));
        
        book.setResource(new Resource(0, bookEle.attributeValue("faceImage"), "/books/" + bookHome + "/" + bookEle.attributeValue("faceImage")));
        
        
        List<?> nodes = bookEle.elements("page");
        
        for (int i = 0; i < nodes.size(); i++) {
            Element pageEle = (Element)nodes.get(i);
            Page page = new Page();
            
            page.setPageIndex(Integer.parseInt(pageEle.attributeValue("id")));
//            page.setImageUrl(buildPageUrl(book, page));
            page.setResource(buildPageResource(bookHome, pageEle.attributeValue("image")));
//            page.setImageUrl(contextPath + page.getResource().getUrl());
            
            book.addPage(page.getPageIndex(), page);
            
            List<?> regions = pageEle.elements();
            for (int j = 0; j < regions.size(); j++) {
                Element node = (Element)regions.get(j);
                
                HotRegion region = null;
                
                if ("rect-hot-region".equals(node.getName())) {
                    region = buildRectHotRegion(node);
                }
                else if ("oval-hot-region".equals(node.getName())) {
                	region = buildRectHotRegion(node);
//                        region = buildOvalHotRegion(book, page, (Element)node);
                }
                
                if (region != null) {
//                	region.setMediaUrl(buildRegionUrl(book, page, region));
                	region.setResource(buildRegionResrouce(bookHome, node.attributeValue("media")));
//                	region.setMediaUrl(contextPath + region.getResource().getUrl());
                	
                    page.addHotRegion(region);
                }
            }
        }
            
            
        
    }
    
//    public static HotRegion buildOvalHotRegion(Book book, Page page, Element regionEle) {
//        OvalHotRegion region = new OvalHotRegion();
//        
//        region.x = Integer.parseInt(regionEle.attributeValue("x"));
//        region.y = Integer.parseInt(regionEle.attributeValue("y"));
//        region.width = Integer.parseInt(regionEle.attributeValue("width"));
//        region.height = Integer.parseInt(regionEle.attributeValue("height"));
//        
//        region.id = Integer.parseInt(regionEle.attributeValue("id"));
//        region.mediaFileName = book.getFullFileName(regionEle.attributeValue("media"));
//        region.chinese = regionEle.attributeValue("text");
//        region.practiceEnable = Boolean.parseBoolean(regionEle.attributeValue("practiceEnable"));
//        region.readPageEnable = Boolean.parseBoolean(regionEle.attributeValue("readPageEnable"));
//        region.regionLocation = Integer.parseInt(regionEle.attributeValue("regionLocation", "0"));
//        region.english = regionEle.attributeValue("english");
//        
//        return region;
//    }
    
    public HotRegion buildRectHotRegion(Element regionEle) {
        HotRegion region = new HotRegion();
        
        region.setX(Integer.parseInt(regionEle.attributeValue("x")));
        region.setY(Integer.parseInt(regionEle.attributeValue("y")));
        region.setWidth(Integer.parseInt(regionEle.attributeValue("width")));
        region.setHeight(Integer.parseInt(regionEle.attributeValue("height")));
        
        region.setId(Integer.parseInt(regionEle.attributeValue("id")));
        region.setRegionIndex(Integer.parseInt(regionEle.attributeValue("id")));
        region.setChinese(regionEle.attributeValue("text"));
        region.setEnglish(regionEle.attributeValue("english"));
        
        return region;
    }
}
