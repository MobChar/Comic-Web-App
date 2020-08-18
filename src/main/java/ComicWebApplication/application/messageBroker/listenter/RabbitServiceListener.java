package ComicWebApplication.application.messageBroker.listenter;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ComicWebApplication.application.DAOServices.ChapterImageRepository;
import ComicWebApplication.application.DAOServices.ComicChapterRepository;
import ComicWebApplication.application.DAOServices.ComicRepository;
import ComicWebApplication.application.DAOServices.ComicThumbImageRepository;
import ComicWebApplication.application.entity.ChapterImage;
import ComicWebApplication.application.entity.Comic;
import ComicWebApplication.application.entity.ComicChapter;
import ComicWebApplication.application.entity.ComicThumbImage;


@Service
public class RabbitServiceListener {
	@Autowired
	private ChapterImageRepository chapterImageRepository;
	@Autowired
	private ComicChapterRepository comicChapterRepository;
	@Autowired
	private ComicThumbImageRepository comicThumbImageRepository;
	@Autowired
	private ComicRepository comicRepository;
	
	private static final Logger logger=LoggerFactory.getLogger(RabbitServiceListener.class);
	
	
	@RabbitListener(queues = "sql-server-new-image")
	  public void receiveNewImageMessage(ReceiveNewImageBodyModel payload) {
		Optional<ComicChapter> opComicCh=comicChapterRepository.findById(payload.getChapterId());
		if(!opComicCh.isPresent()) {
			logger.warn("Chapter id received from image server ("+payload.getServerId()+") was not found");
			return;
		}
	    chapterImageRepository.saveAndFlush(new ChapterImage(payload.getServerId(),payload.getFileName(),opComicCh.get()));
	    logger.warn("Received new image model from ("+payload.getServerId()+") server");
	 }
	
	@RabbitListener(queues = "sql-server-new-thumb")
	  public void receiveNewThumbMessage(ReceiveNewThumbBodyModel payload) {
		Optional<Comic> opComic=comicRepository.findById(payload.getComicId());
		if(!opComic.isPresent()) {
			logger.warn("Comic id received from image server ("+payload.getServerId()+") was not found");
			return;
		}
		if(opComic.get().getThumb()!=null) {
			logger.warn("Error when adding thumb to comic (Thumb already exist");
			return;
		}
		comicThumbImageRepository.saveAndFlush(new ComicThumbImage(payload.getFileName(),payload.getServerId(),opComic.get()));
	    logger.warn("Received new thumb model from ("+payload.getServerId()+") server");
	 }
}
