var ImageLoadHandler = function(book) {
	this.book = book;
}

ImageLoadHandler.prototype.handleEvent = function() {
	this.book.handleImageLoadEvent(this.book);
}

var CanvasClickHandler = function(book) {
	this.book = book;
}

CanvasClickHandler.prototype.handleEvent = function(event) {
	this.book.handleCanvasClickEvent(this.book, event);	
}

var Book = function(bookId, bookUrl, canvas) {
	this.bookId  = bookId;
	this.bookUrl = bookUrl;
	
	this.currentPage = null;
	this.currentHotRegion = null;
	
	this.xscale = 1.0;
	this.yscale = 1.0;
	this.canvas = canvas;
	this.canvasCtx = canvas.getContext('2d');
	this.pageImage = new Image();
	this.audio = document.createElement("audio");
	
	this.audio.addEventListener("canplaythrough", function () {
		alert('The file is loaded and ready to play!');
	}, false);
	
	
	this.pageImage.addEventListener('load', new ImageLoadHandler(this) , false);
	this.canvas.addEventListener('click', new CanvasClickHandler(this), false);
		
}


Book.prototype.handleCanvasClickEvent = function(book, event) {				
	var p = book.getEventPosition(event);
	book.click(p);
	
}


Book.prototype.getEventPosition = function(ev){
	var x, y;
	if (ev.layerX || ev.layerX == 0) {
		x = ev.layerX;
		y = ev.layerY;
	} 
	else if (ev.offsetX || ev.offsetX == 0) { // Opera
		x = ev.offsetX;
		y = ev.offsetY;
	}
	return {x: x, y: y};
}



Book.prototype.handleImageLoadEvent = function(book) {

	book.canvas.width  = window.innerWidth;
	book.canvas.height = window.innerHeight - 40;

	book.xscale = book.canvas.width / book.pageImage.width;
	book.yscale = book.canvas.height / book.pageImage.height;
		
	book.draw();
	
}

Book.prototype.calTextsRect = function(ctx, texts) {
	var maxWidth = 0;
	for (i = 0; i < texts.length; i++) {
		metrics = ctx.measureText(texts[i]);
		if (metrics.width > maxWidth)
			maxWidth = metrics.width;
	}
	
	return maxWidth;
	
}

Book.prototype.playAudio = function(url) {

	if (this.audio != null && this.audio.canPlayType && this.audio.canPlayType("audio/mp3")){ 
		this.audio.src = url;  
		this.audio.play();  
	}
}

Book.prototype.draw = function() {
	
	var canvasCtx = this.canvasCtx;
	var region = this.currentHotRegion;
	
	canvasCtx.drawImage(this.pageImage, 0, 0, this.pageImage.width, this.pageImage.height, 0, 0, this.canvas.width, this.canvas.height);
	
	if (region != null) {
						
		canvasCtx.strokeStyle = 'red';
		canvasCtx.lineWidth = 2;
		
		regionX = region.x * this.xscale;
		regionY = region.y * this.yscale;
		regionW = region.width * this.xscale;
		regionH = region.height * this.yscale;
		
		canvasCtx.strokeRect(regionX, regionY, regionW, regionH);
		
		canvasCtx.lineWidth = 1;
		canvasCtx.font = '12px 宋体';
		canvasCtx.textAlign = 'left';
		
		texts = region.chinese.split("|");
		
		textRectWidth = this.calTextsRect(canvasCtx, texts);
		
		canvasCtx.strokeStyle = 'black';
		canvasCtx.strokeRect(regionX, regionY + regionH + 5, textRectWidth + 14, 14 * texts.length + 10 );
		
		canvasCtx.fillStyle = 'white';
		canvasCtx.fillRect(regionX, regionY + regionH + 5, textRectWidth + 14, 14 * texts.length + 10);
							
		
		canvasCtx.textBaseline = 'top';
		canvasCtx.fillStyle = 'black';
		
		for (i = 0; i < texts.length; i++) {
			canvasCtx.fillText(texts[i], regionX + 7, regionY + regionH + 10 + i * 14);
		}	
	}	
	
}

Book.prototype.loadPage = function(pageIndex) {
	
	$.ajax({
		type: "post",
		dataType: "json",
		context : this,
		url: this.bookUrl, 
		data: {
			bookId: this.bookId,
			pageIndex: pageIndex
		},
		
		success: function(result){
			
			if (result.code == 0) {
				this.currentPage = result.pageInfo;
				this.pageImage.src = this.currentPage.imageUrl;
				this.currentHotRegion = null;
				this.audio.pause();
				
			}
			else {
				if (result.message !== undefined)
					alert(result.message);
			}
		}    
	});
		
}

Book.prototype.nextPage = function() {
	this.loadPage(this.currentPage.pageIndex + 1);	
}

Book.prototype.previousPage = function() {
	this.loadPage(this.currentPage.pageIndex - 1);
	
}
 
Book.prototype.click = function(p) {
	
	var region = this.getHotRegion(p);

	if (region != null) {
		this.currentHotRegion = region;
		this.draw();
		this.playAudio(region.mediaUrl);
	}
	
} 

Book.prototype.getHotRegion = function(p) {
	var region = null;
	
	if (this.currentPage == null){
		return null;
	} 
	
	for (i = 0; i < this.currentPage.regions.length; i++) {
		region = this.currentPage.regions[i]
		if (p.x >= region.x * this.xscale &&
		    p.x <= (region.x + region.width) * this.xscale && 
		    p.y >= region.y * this.yscale && 
			p.y <= (region.y + region.height) * this.yscale )
			
			return region;
	}
	
	return null;	
} 
