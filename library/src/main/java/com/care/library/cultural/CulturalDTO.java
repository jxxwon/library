package com.care.library.cultural;

public class CulturalDTO {
	private String id;
    private String title;
    private String lecture_start;
    private String lecture_end;
    private String registration_start;
    private String registration_end;
    private String target;
    private String imagePath; // 이미지 파일의 경로를 저장하는 필드
	/* private byte[] image; */
    private String count;
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
	public String getLecture_start() {
		return lecture_start;
	}
	public void setLecture_start(String lecture_start) {
		this.lecture_start = lecture_start;
	}
	public String getLecture_end() {
		return lecture_end;
	}
	public void setLecture_end(String lecture_end) {
		this.lecture_end = lecture_end;
	}
	public String getRegistration_start() {
		return registration_start;
	}
	public void setRegistration_start(String registration_start) {
		this.registration_start = registration_start;
	}
	public String getRegistration_end() {
		return registration_end;
	}
	public void setRegistration_end(String registration_end) {
		this.registration_end = registration_end;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
    
	
    
}
