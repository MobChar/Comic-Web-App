package ComicWebApplication.application.messageBroker.listenter;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ReceiveNewImageBodyModel implements Serializable {

    private char serverId;
    private int chapterId;
    private String fileName;
    public ReceiveNewImageBodyModel(@JsonProperty("serverId") char serverId,
                         @JsonProperty("chapterId") int chapterId,
                         @JsonProperty("fileName") String fileName ) {
        this.serverId=serverId;
        this.chapterId=chapterId;
        this.fileName=fileName;
    }
	public char getServerId() {
		return serverId;
	}
	public int getChapterId() {
		return chapterId;
	}
	public String getFileName() {
		return fileName;
	}

	

	

    
}