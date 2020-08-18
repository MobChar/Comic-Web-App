package ComicWebApplication.application.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Comic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Timestamp createdDate;
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	private String author;
	private int viewCount;
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE},mappedBy = "comic")
	@JoinColumns({
		@JoinColumn (name = "thumbFileName", referencedColumnName="fileName"),
		@JoinColumn (name = "thumbServerId", referencedColumnName="serverId")
	})
	private ComicThumbImage thumb;
	
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
	@JoinColumn (name = "genre", referencedColumnName="id")
	private List<Genre> genres;
	
	
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public Comic() {
		super();
		id=0;
		name="";
		description="";
		author="";
		viewCount=0;
		createdDate=Timestamp.valueOf(LocalDateTime.now());
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Comic(String name, Timestamp createdDate, String description, String author) {
		super();
		this.name = name;
		this.createdDate = createdDate;
		this.description=description;
		this.author=author;
		this.viewCount=0;
	}
	public Comic(String name, String description, String author) {
		super();
		this.name = name;
		createdDate=Timestamp.valueOf(LocalDateTime.now());
		this.description=description;
		this.author=author;
		this.viewCount=0;
	}

	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public ComicThumbImage getThumb() {
		return thumb;
	}
	public void setThumb(ComicThumbImage thumb) {
		this.thumb = thumb;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
	
	
}
