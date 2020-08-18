package ComicWebApplication.application.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ComicWebApplication.application.DAOServices.ChapterImageRepository;
import ComicWebApplication.application.DAOServices.ComicChapterRepository;
import ComicWebApplication.application.DAOServices.ComicRepository;
import ComicWebApplication.application.DAOServices.ComicThumbImageRepository;
import ComicWebApplication.application.DAOServices.GenreRepository;
import ComicWebApplication.application.controller.service.BannerService;
import ComicWebApplication.application.entity.Comic;
import ComicWebApplication.application.entity.ComicChapter;
import ComicWebApplication.application.entity.Genre;
import ComicWebApplication.application.entity.Image;
import ComicWebApplication.application.outerServer.FileURLConverter;

@Controller
public class MainPageController {
	public static int MAX_CHAPTER_VIEW=3;
	private static final Logger logger=LoggerFactory.getLogger(MainPageController.class);
	@Autowired ComicRepository comicRepository;
	@Autowired ChapterImageRepository chapterImageRepository;
	@Autowired ComicThumbImageRepository comicThumbImageRepository;
	@Autowired ComicChapterRepository comicChapterRepository;
	@Autowired GenreRepository genreRepository;
	@Autowired BannerService bannerService;
	@GetMapping("/")
	public ModelAndView getMainPage(@RequestParam(value = "page" ,required=false) Integer page) throws EntityNotFoundException, IllegalArgumentException{
		List<Comic> comicLS=null;
		
		if(page==null)
			comicLS=comicRepository.getNewUpdateComic(0);
		else if(page>0)
			comicLS=comicRepository.getNewUpdateComic(page-1);
		else throw new  IllegalArgumentException();
		Integer countAll=comicRepository.getCountNewUpdateComic();
		List<String> thumbLS=new ArrayList<String>(comicLS.size());//Thumb URL
		List<List<ComicChapter>> chapterLS=new ArrayList<List<ComicChapter>>(comicLS.size());
		
		//Chapter and thumb
		for(int i=0;i<comicLS.size();i++) {
			List<ComicChapter> allChapter=comicChapterRepository.getAllComicChapter(comicLS.get(i).getId());
			List<ComicChapter> viewNewChapter=allChapter.subList(Math.max(allChapter.size()-MAX_CHAPTER_VIEW,0),allChapter.size());
			chapterLS.add(viewNewChapter);
			thumbLS.add(i, FileURLConverter.fileEntityT2URL(comicLS.get(i).getThumb()));//Thumb URL
		}
		
		
		List<String> banners=new ArrayList<String>();//Banner URL
		for(Image banner: bannerService.getBannerContainer()) {
			banners.add(FileURLConverter.fileEntityT2URL(banner));
		}
		
		List<Genre> genreLS=genreRepository.findAll();
		
		
//		

		ModelAndView res=new ModelAndView();
		res.addObject("comics", comicLS);
		res.addObject("thumbImages", thumbLS);
		res.addObject("comicChapters", chapterLS);
		res.addObject("banners", banners);
		res.addObject("genres",genreLS);
		res.addObject("pageCount", (int)Math.ceil(countAll/10.));
		res.setViewName("index");
		return res;
	}
	@GetMapping("/find")
	public ModelAndView findComic(@RequestParam Integer genre,@RequestParam String order,@RequestParam Integer page) throws EntityNotFoundException, IllegalArgumentException{
		Genre genreO=genreRepository.getOne(genre);
		if(page<=0) throw new  IllegalArgumentException();
	
		
		List<Comic> comicLS=null;
		if(order.equals("latest")) comicLS=comicRepository.getComicByGenreOrderByUpdateDate(genreO.getId(),page-1);
		else if(order.equals("view")) comicLS=comicRepository.getComicByGenreOrderByView(genreO.getId(),page-1);
		else throw new  IllegalArgumentException();
		Integer countAll=comicRepository.getCountByGenre(genre);
		List<String> thumbLS=new ArrayList<String>(comicLS.size());//Thumb URL
		List<List<ComicChapter>> chapterLS=new ArrayList<List<ComicChapter>>(comicLS.size());
		
		//Chapter and thumb
		for(int i=0;i<comicLS.size();i++) {
			List<ComicChapter> allChapter=comicChapterRepository.getAllComicChapter(comicLS.get(i).getId());
			List<ComicChapter> viewNewChapter=allChapter.subList(Math.max(allChapter.size()-MAX_CHAPTER_VIEW,0),allChapter.size());
			chapterLS.add(viewNewChapter);
			thumbLS.add(i, FileURLConverter.fileEntityT2URL(comicLS.get(i).getThumb()));//Thumb URL
		}
		
		
		List<String> banners=new ArrayList<String>();//Banner URL
		for(Image banner: bannerService.getBannerContainer()) {
			banners.add(FileURLConverter.fileEntityT2URL(banner));
		}
		
		List<Genre> genreLS=genreRepository.findAll();
		
		

		ModelAndView res=new ModelAndView();
		res.addObject("comics", comicLS);
		res.addObject("thumbImages", thumbLS);
		res.addObject("comicChapters", chapterLS);
		res.addObject("banners", banners);
		res.addObject("genres",genreLS);
		res.addObject("findGenre",genreO);
		res.addObject("pageCount", (int)Math.ceil(countAll/10.));
		res.setViewName("find");
		return res;
	}
}
