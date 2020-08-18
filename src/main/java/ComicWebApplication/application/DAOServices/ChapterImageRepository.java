package ComicWebApplication.application.DAOServices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import ComicWebApplication.application.entity.ChapterImage;
import ComicWebApplication.application.entity.primaryKey.ImagePrimaryKey;

@Component
public interface ChapterImageRepository extends JpaRepository<ChapterImage,ImagePrimaryKey> {

	@Query(value="SELECT * FROM Chapter_Image  u WHERE u.comic_chapter_id=?1 and u.server_id=?2",nativeQuery = true)
	List<ChapterImage> getAllChapterImage(Integer comicChapterId, char serverId);
}
