package ComicWebApplication.application.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ComicWebApplication.application.DAOServices.SampleCommandLine;




@Entity
public class ComicThumbImage extends Image{
	private static final Logger logger=LoggerFactory.getLogger(ComicThumbImage.class);
	@OneToOne(fetch = FetchType.EAGER, cascade= {CascadeType.MERGE})
	@JoinColumn(name = "comicId", referencedColumnName="id")
	private Comic comic;
	
	public ComicThumbImage() {
		super();
	}
	public ComicThumbImage(String fileName, char serverId, Comic comic) {
		super(serverId,fileName);
		logger.warn("AC");
		setComic(comic);
	}
	public Comic getComic() {
		return comic;
	}
	public void setComic(Comic comic) {
		System.out.println("Call set thumb");
		this.comic = comic;
		comic.setThumb(this);
	}
}
