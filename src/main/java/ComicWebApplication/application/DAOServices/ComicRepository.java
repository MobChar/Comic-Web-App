package ComicWebApplication.application.DAOServices;

import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import ComicWebApplication.application.entity.Comic;
import ComicWebApplication.application.entity.ComicChapter;

@Component
public interface ComicRepository extends JpaRepository<Comic, Integer>{

	@Query(value="SELECT * FROM (SELECT u.* FROM Comic  u ORDER BY (SELECT MAX(v.id) FROM Comic_Chapter v WHERE v.comic_id=u.id) DESC ) LIMIT 10 OFFSET ?1*10",nativeQuery = true)
	List<Comic> getNewUpdateComic(int pageIndex);
	
	@Query(value="SELECT COUNT(*) FROM Comic",nativeQuery = true)
	Integer getCountNewUpdateComic();
	
	
	@Query(value="SELECT * FROM (SELECT * FROM Comic u WHERE EXISTS(SELECT k.comic_id FROM Comic_Genres k WHERE k.comic_id=u.id AND k.genres_id=?1) ORDER BY (SELECT MAX(v.id) FROM Comic_Chapter v WHERE v.comic_id=u.id)  DESC ) LIMIT 10 OFFSET ?2*10",nativeQuery = true)
	List<Comic> getComicByGenreOrderByUpdateDate(Integer genreId, int pageIndex);
	
	@Query(value="SELECT COUNT(*) FROM Comic u WHERE EXISTS(SELECT k.comic_id FROM Comic_Genres k WHERE k.comic_id=u.id AND k.genres_id=?1)",nativeQuery = true)
	Integer getCountByGenre(Integer genreId);
	
	
	@Query(value="SELECT * FROM (SELECT * FROM Comic u WHERE EXISTS(SELECT k.comic_id FROM Comic_Genres k WHERE k.comic_id=u.id AND k.genres_id=?1) ORDER BY  u.view_count  DESC)  LIMIT 10 OFFSET ?2*10",nativeQuery = true)
	List<Comic> getComicByGenreOrderByView(Integer genreId, int pageIndex);
}
