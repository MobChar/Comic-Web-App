package ComicWebApplication.application.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ComicPostObject {
	private String comicName;
	private String author;
	private String description;
	private List<Integer> genres;
	public ComicPostObject(@JsonProperty("comicName") String comicName,
            @JsonProperty("author") String author,
            @JsonProperty("description") String description ,
            @JsonProperty("genres") List<Integer> genres) {
		this.comicName=comicName;
		this.author=author;
		this.description=description;
		this.genres=genres;
	}
	public String getComicName() {
		return comicName;
	}
	public void setComicName(String comicName) {
		this.comicName = comicName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Integer> getGenres() {
		return genres;
	}
	public void setGenres(List<Integer> genres) {
		this.genres = genres;
	}
	
	
}
