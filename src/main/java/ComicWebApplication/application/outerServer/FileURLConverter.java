package ComicWebApplication.application.outerServer;

import ComicWebApplication.application.entity.ChapterImage;
import ComicWebApplication.application.entity.Image;

public class FileURLConverter {
	public static String SERVER_A_DOMAIN_NAME="main-image-server.herokuapp.com";
	public static String SERVER_B_DOMAIN_NAME="additional-image-server.herokuapp.com";
	public static String CONNECT_TYPE="https://";
	public static String SERVER_A_FILE_PATH="/image";
	public static String SERVER_B_FILE_PATH="/image";
	public static String NULL_IMAGE_PATH=CONNECT_TYPE+SERVER_A_DOMAIN_NAME+SERVER_A_FILE_PATH+"/"+"0.png";
	public static String fileEntityT2URL(Image img) {
		if(img==null) return NULL_IMAGE_PATH;
		if(img.getServerId()=='A') {
			return
					CONNECT_TYPE+SERVER_A_DOMAIN_NAME+SERVER_A_FILE_PATH+"/"+img.getFileName();
		}
		else {
			return
					CONNECT_TYPE+SERVER_B_DOMAIN_NAME+SERVER_B_FILE_PATH+"/"+img.getFileName();
		}
	}
}
