package ComicWebApplication.application.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChapterPostObject {
	private String title;
	private Integer comic;
	public ChapterPostObject(@JsonProperty("title") String title,
            @JsonProperty("comic") Integer comic) {
		this.title=title;
		this.comic=comic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getComic() {
		return comic;
	}
	public void setComic(Integer comic) {
		this.comic = comic;
	}
	
	
}
