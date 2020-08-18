package ComicWebApplication.application.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
public class ComicChapter implements Serializable, Comparable{
	
	@Id
	@GeneratedValue
	private int id;
	
	private String title;
	private Timestamp createdDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comicId", referencedColumnName="id")
	private Comic comic;
	public ComicChapter() {
		super();
	}
	public ComicChapter(String title, Comic comic) {
		super();
		this.title=title;
		this.comic = comic;
		createdDate=Timestamp.valueOf(LocalDateTime.now());
	}
	
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Comic getComic() {
		return comic;
	}
	public void setComic(Comic comic) {
		this.comic = comic;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.id-((ComicChapter)o).getId();
	}
	
	
}
