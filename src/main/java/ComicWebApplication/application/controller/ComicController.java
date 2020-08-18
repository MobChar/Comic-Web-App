package ComicWebApplication.application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ComicWebApplication.application.DAOServices.ChapterImageRepository;
import ComicWebApplication.application.DAOServices.ComicChapterRepository;
import ComicWebApplication.application.DAOServices.ComicRepository;
import ComicWebApplication.application.DAOServices.GenreRepository;
import ComicWebApplication.application.controller.service.BannerService;
import ComicWebApplication.application.entity.ChapterImage;
import ComicWebApplication.application.entity.Comic;
import ComicWebApplication.application.entity.ComicChapter;
import ComicWebApplication.application.entity.Genre;
import ComicWebApplication.application.outerServer.FileURLConverter;


@RestController
public class ComicController {
	private static final Logger logger=LoggerFactory.getLogger(ComicController.class);
	@Autowired
	ComicRepository comicRepository;
	@Autowired
	ComicChapterRepository comicChapterRepository;
	@Autowired
	ChapterImageRepository chapterImageRepository;
	@Autowired GenreRepository genreRepository;
	
	@GetMapping("/comic/{comicId}")
	public ModelAndView viewComic(@PathVariable Integer comicId) throws EntityNotFoundException, IllegalArgumentException{
		Comic comic=comicRepository.getOne(comicId);
		List<ComicChapter> comicChapterLS=comicChapterRepository.getAllComicChapter(comicId);
		List<String> genreLS=new ArrayList<String>();
		String thumbURL=FileURLConverter.fileEntityT2URL(comic.getThumb());
		
		for(Genre genre: comic.getGenres()) {
			genreLS.add(genre.getName());
		}
		List<Genre> allGenreLS=genreRepository.findAll();
		
		
		ModelAndView res=new ModelAndView();
		res.addObject("comic", comic);
		res.addObject("chapters", comicChapterLS);
		res.addObject("thumbImage", thumbURL);
		res.addObject("genres", genreLS);
		res.addObject("allGenre", allGenreLS);
		res.setViewName("view-comic");
		return res;
	}
	@GetMapping("/comic/{comicId}/chapter/{chapterId}")
	public ModelAndView viewComicChapter(@PathVariable Integer comicId,@PathVariable Integer chapterId,@RequestParam Character view) throws EntityNotFoundException, IllegalArgumentException{
		Comic comic=comicRepository.getOne(comicId);
		//Increase comic view count
		comic.setViewCount(comic.getViewCount()+1);
		comicRepository.flush();
		ComicChapter chapter=comicChapterRepository.getOne(chapterId);//Throw entity not found
		if(!comicId.equals(chapter.getComic().getId())) throw new EntityNotFoundException();
		
		//Get all image URL
		List<ChapterImage> chapterImageLs=null;
		if(view=='A') chapterImageLs=chapterImageRepository.getAllChapterImage(chapterId,'A');
		else if(view=='B') chapterImageLs=chapterImageRepository.getAllChapterImage(chapterId,'B');
		else throw new IllegalArgumentException();
		
		List<String> fileURLs=new ArrayList<String>(chapterImageLs.size());
		for(ChapterImage ci: chapterImageLs) {
			fileURLs.add(FileURLConverter.fileEntityT2URL(ci));
		}
		
		//Get all relevant chapter
		List<ComicChapter> comicChapterLS=comicChapterRepository.getAllComicChapter(comicId);
		
		//Index of current chapter
		int current_chapter_index=Arrays.binarySearch(comicChapterLS.toArray(), chapter);
		if(current_chapter_index<0) throw new EntityNotFoundException();
		
		ModelAndView res=new ModelAndView();
		res.addObject("comic", comic);
		res.addObject("comicImages", fileURLs);
		res.addObject("chapters", comicChapterLS);
		res.addObject("current_chapter_index", current_chapter_index);
		res.setViewName("view-comic-chapter");
		return res;
		
	}
	
	@PostMapping("/comic")
	@ResponseBody
	public int newComic(@RequestBody ComicPostObject payload) throws EntityNotFoundException, IllegalArgumentException{
		List<Genre> genres=genreRepository.findAllById(payload.getGenres());
		Comic comic=new Comic(payload.getComicName(),payload.getDescription(),payload.getAuthor());
		comic.setGenres(genres);
		
		comicRepository.saveAndFlush(comic);
		return comic.getId();
	}
	
	@PostMapping("/chapter")
	@ResponseBody
	public int newChapter(@RequestBody ChapterPostObject payload) throws EntityNotFoundException, IllegalArgumentException{
		Comic comic=comicRepository.getOne(payload.getComic());
		ComicChapter chapter=new ComicChapter(payload.getTitle(),comic);
		comicChapterRepository.saveAndFlush(chapter);
		
		return chapter.getId();
	}
}
