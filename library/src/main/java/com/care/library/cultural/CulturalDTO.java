package com.care.library.cultural;

public class CulturalDTO {
	private String id;
    private String title;
    private String content;
    private String imagePath; // 이미지 파일의 경로를 저장하는 필드
	/* private byte[] image; */
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
    
	
    
}
