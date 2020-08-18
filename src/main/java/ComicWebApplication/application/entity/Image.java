package ComicWebApplication.application.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.InheritanceType;

import ComicWebApplication.application.entity.primaryKey.ImagePrimaryKey;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@IdClass(ImagePrimaryKey.class)
public class Image implements Serializable{
	@Id
	private String fileName;
	@Id
	private char serverId;
	
	public Image() {
		super();
	}
	public Image(char serverId, String fileName) {
		super();
		this.fileName=fileName;
		this.serverId = serverId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public char getServerId() {
		return serverId;
	}
	public void setServerId(char serverId) {
		this.serverId = serverId;
	}
}
