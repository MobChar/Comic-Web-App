package ComicWebApplication.application.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Id;

public class ImagePrimaryKey implements Serializable{
	private String fileName;
	private char serverId;
	public ImagePrimaryKey() {
		super();
	}	
	public ImagePrimaryKey(String fileName, char serverId) {
		super();
		this.fileName = fileName;
		this.serverId = serverId;
	}
	
}
