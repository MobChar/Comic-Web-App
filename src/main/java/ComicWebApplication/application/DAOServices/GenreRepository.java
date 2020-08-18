package ComicWebApplication.application.DAOServices;

import org.springframework.data.jpa.repository.JpaRepository;

import ComicWebApplication.application.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre,Integer>{

}
