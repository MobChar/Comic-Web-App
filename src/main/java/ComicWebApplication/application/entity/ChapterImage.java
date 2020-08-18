package ComicWebApplication.application.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
public class ChapterImage extends Image{
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "comicChapterId", referencedColumnName="id")
	private ComicChapter comicChapter;
	
	public ChapterImage() {
		super();
	}
	public ChapterImage(char serverId, String fileName, ComicChapter comicChapter) {
		super(serverId, fileName);
		this.comicChapter=comicChapter;
	}
	public ComicChapter getComicChapter() {
		return comicChapter;
	}
	public void setComicChapter(ComicChapter comicChapter) {
		this.comicChapter = comicChapter;
	}
	
	
	
	
}
