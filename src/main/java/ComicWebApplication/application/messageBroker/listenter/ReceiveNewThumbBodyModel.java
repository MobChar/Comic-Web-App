package ComicWebApplication.application.messageBroker.listenter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ReceiveNewThumbBodyModel implements Serializable {

    private char serverId;
    private int comicId;
    private String fileName;
    public ReceiveNewThumbBodyModel(@JsonProperty("serverId") char serverId,
                         @JsonProperty("comicId") int comicId,
                         @JsonProperty("fileName") String fileName ) {
        this.serverId=serverId;
        this.comicId=comicId;
        this.fileName=fileName;
    }
	public char getServerId() {
		return serverId;
	}
	public int getComicId() {
		return comicId;
	}
	public String getFileName() {
		return fileName;
	}

	

	

    
}