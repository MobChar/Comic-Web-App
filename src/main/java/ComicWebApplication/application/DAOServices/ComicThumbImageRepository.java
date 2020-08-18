package ComicWebApplication.application.DAOServices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import ComicWebApplication.application.entity.ChapterImage;
import ComicWebApplication.application.entity.ComicThumbImage;
import ComicWebApplication.application.entity.primaryKey.ImagePrimaryKey;

@Component
public interface ComicThumbImageRepository extends JpaRepository<ComicThumbImage,ImagePrimaryKey>{
	
}
