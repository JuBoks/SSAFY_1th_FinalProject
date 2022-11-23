package com.ssafy.news.dto;

public class NewsDto {
	String url;
	String img;
	String subject;
	String contents;
	public NewsDto() {}
	public NewsDto(String url, String img, String subject, String contents) {
		this.url = url;
		this.img = img;
		this.subject = subject;
		this.contents = contents;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NewsDto [url=");
		builder.append(url);
		builder.append(", img=");
		builder.append(img);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", contents=");
		builder.append(contents);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
