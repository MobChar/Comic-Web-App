package ComicWebApplication.application.DAOServices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import ComicWebApplication.application.entity.ChapterImage;
import ComicWebApplication.application.entity.ComicChapter;

@Component
public interface ComicChapterRepository extends JpaRepository<ComicChapter,Integer>{
	@Query(value="SELECT * FROM Comic_Chapter  u WHERE u.comic_id=?1",nativeQuery = true)
	List<ComicChapter> getAllComicChapter(Integer comicId);
}
