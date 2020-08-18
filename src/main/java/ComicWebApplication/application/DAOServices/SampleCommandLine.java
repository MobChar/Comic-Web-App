package ComicWebApplication.application.DAOServices;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ComicWebApplication.application.controller.MainPageController;
import ComicWebApplication.application.controller.service.BannerService;
import ComicWebApplication.application.entity.ChapterImage;
import ComicWebApplication.application.entity.Comic;
import ComicWebApplication.application.entity.ComicChapter;
import ComicWebApplication.application.entity.ComicThumbImage;
import ComicWebApplication.application.entity.Genre;
import ComicWebApplication.application.entity.Image;

@Component
public class SampleCommandLine implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(SampleCommandLine.class);
	@Autowired
	ComicRepository comicRepository;
	@Autowired
	ComicChapterRepository comicChapterRepository;
	@Autowired
	ComicThumbImageRepository comicThumbImageRepository;
	@Autowired
	GenreRepository genreRepository;
	@Autowired 
	ChapterImageRepository chapterImageRepository;
	@Autowired
	BannerService bannerService;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		List<Genre> genres=List.of(new Genre("Action"),new Genre("Adventure"),new Genre("Mecha"),new Genre("Slice of life")
				,new Genre("Manhwa"), new Genre("Manga"), new Genre("Material Arts"), new Genre("Dramma"),new Genre("Historical")
				);
		genreRepository.saveAll(genres);
		
		List<Genre> mutableGenres=new ArrayList<Genre>(genres);
		int count_sample=18;
		String comicNames[]=new String[] {
				"Solo leveling",
				"One piece",
				"Naruto",
				"Kimetsu No Yaiba",
				"Onepunch-man",
				"Arifureta",
				"Tales of demons and gods",
				"Boku no hero acedemia",
				"Tower of god",
				"Black cover",
				"Goblin slayer",
				"The god of High School",
				"Tensei Shitara Slime Datta Ken",
				"Bleach",
				"Hero? I quit a long time ago",
				"Release that witch",
				"Dr Stone",
				"Re: Monster"	
		};
		String descriptions[]=new String[] {
				"10 years ago, after “the Gate” that connected the real world with the monster world opened,"
						+ " some of the ordinary, everyday people received the power to hunt monsters within the Gate. They are known as \"Hunters\"."
						+ " However, not all Hunters are powerful. My name is Sung Jin-Woo, an E-rank Hunter. I'm someone who has to risk his life in the lowliest of dungeons, the \"World's Weakest\"."
						+ " Having no skills whatsoever to display, I barely earned the required money by fighting in low-leveled dungeons… at least until I found a hidden dungeon with the hardest difficulty within the D-rank dungeons! "
						+ "In the end, as I was accepting death, I suddenly received a strange power, a quest log that only I could see, a secret to leveling up that only I know about! If I trained in accordance with my quests and hunted monsters, my level would rise."
						+ " Changing from the weakest Hunter to the strongest S-rank Hunter!"
						
						,"As a child, Monkey D. Luffy dreamed of becoming the King of the Pirates."
								+ " But his life changed when he accidentally gained the power to stretch like rubber...at"
								+ " the cost of never being able to swim again! Now Luffy, with the help of a motley collectio"
								+ "n of nakama, is setting off in search of \"One Piece,\" said to be the greatest treasure in the world."
						
								,"Twelve years ago the Village Hidden in the Leaves was attacked by a fearsome threat."
										+ " A nine-tailed fox spirit claimed the life of the village leader, the Hokage, and many others"
										+ ". Today, the village is at peace and a troublemaking kid named Naruto is struggling to graduate "
										+ "from Ninja Academy. His goal may be to become the next Hokage, but his true destiny will be much"
										+ " more complicated. The adventure begins now!"
										,
										"Tanjiro is the oldest son in his family who has lost his father. One day, Tanjiro ventures off to another town to sell charcoal. Instead of going home, he ends up staying the night at someone else's house due to rumors"
												+ " of a demon nearby in the mountains. When he gets home the following day, a terrible tragedy awaits him."
												,
												"One punch-Man imitates the life of an average hero who wins all of his fights with only one punch!"
														+ " This is why he is called Onepunch man Manga. This story takes place in the fictional Z-City."
														+ " The world is full of mysterious beings, villains and monsters that cause destruction and havoc."
														+ " An association of heroes has been established to protect the citizens from all harms and enemies."
														+ " People with superhuman ability can register themselves with the association that protects citizens. "
														+ "There, they will be required to take a series of tests to determine their ability and what class they are"
														+ ". Class S being the highest and class C being the lowest."
														,"Among the class transported to another world, Nagumo Hajime is an ordinary male student who didn’t have ambition nor aspiration in life, and thus called “Incompetent” by his classmates. The class was summoned to become heroes and save a country from destruction. Students of the class were blessed with cheat specifications and cool job class, however, it was not the case with Hajime, with his profession as a “Synergist”, and his very mediocre stats. “Synergist”, to put it in another word was just artisan class. Being the weakest, he then falls to the depth of the abyss when he and his classmates were exploring a dungeon. What did he find in the depth of the abyss, and can he survive?"
														,"Nie Li, one of the strongest Demon Spiritist in his past life standing at the pinnacle of the martial world , however he lost his life during the battle with Sage Emperor and the six deity ranked beast, his soul was then reborn back in time back to when he is still 13. Although he’s the weakest in his class with the lowest talent at only Red soul realm, with the aid of the vast knowledge which he accumulated in his previous life, he trained faster then anyone. Trying to protect the city which in the coming future was being assaulted by beast and ended up being destroyed as well as protecting his lover, friends and family who died by the beast assault. and to destroy the Sacred family whom abandon their duty and betrayed the city in his past life."
														,"Boku no Hero Academia Manga is a continuous Japanese super hero manga series written and illustrated by Kohei Horikoshi.Izuku Midoriya was only a regular middle school pupil in a world where individuals with superpowers known as \"Quirks\" will be the standard. Nevertheless, he dreams of one day being a Hero, for lacking a Quirk, despite being intimidated by his classmates. The storyline follows Izuku's entry into U.A. High School , a school that educates the next generation of Heroes."
														,"The storyline centers around a boy called Twenty-Fifth Baam, that has spent his life trapped beneath a tower that was mysterious. Chasing after his only friend, as he attempts to locate his company, he manages to open a door to the Tower, and must face challenges at every floor of the cryptic tower."
														,"From MangaHelpers: Aster and Yuno were abandoned together at the same church, and have been inseparable since. As children, they promised that they would compete against each other to see who would become the next sorcerous emperor. However, as they grew up, some differences between them became plain. Yuno was a genius with magic, with amazing power and control, while Aster could not use magic at all, and tried to make up for his lack by training physically. When they received their grimoires at age 15, Yuno got a spectacular book with a four-leaf-clover (most people receive a three-leaf-clover), while Aster received nothing at all. However, when Yuno was threatened, the truth about Aster's power was revealed-- he received a five-leaf-clover grimoire, a 'black clover' book of anti-magic. Now the two friends are heading out in the world, both seeking the same goal!"
														,"From Yen Press: A young priestess has formed her first adventuring party, but almost immediately they find themselves in distress. It's the Goblin Slayer who comes to their rescue--a man who's dedicated his life to the extermination of all goblins, by any means necessary. And when rumors of his feats begin to circulate, there's no telling who might come calling next... Adaptation of the Novel \"Goblin Slayer\""
														,"The God of High School is a Korean manga that is hosted by the Naver.com a Korean search engine. This full colored manga tells the story of a young 17 high school student named Mori Jin. The main character is an orphan taken in care by his \"grandfather\" without any real blood relation."
														,"A man is stabbed by a robber on the run after pushing his coworker and his coworker's new fiance out of the way. As he lays dying, bleeding on the ground, he hears a voice. This voice is strange and interprets his dying regret of being a virgin by gives him the [Great Sage] unique skill! Is he being made fun of !?!"
														,"Bleach Manga is a Japanese Manga series that is illustrated and writer by Tite Kubo. Bleach has over two hundred series in which most of the series have evolved into anime series and it follows the adventures of Ichigo Kurosaki. Kurosaki is a soul reaper commonly known as Shinigami in Japan and he gained powers from Rukia Kuchiki. In his quest to defend humans from evil spirits, Kurosaki pushes them into after life. Bleach is collected into 58 volumes, which are popularly known as Tanlobon volumes, and each of the books discusses the character that is on the cover page. In Japan alone, bleach has released over 500 chapters and there are numerous English versions that are written with katakana on each of them. Bleach Manga has been a continuous series on the ShMnen Jump since 2001 and sold over fifty million copies in Japan and was recorded as the highest Manga seller in the United States."
														,"Zero was mankind’s first real superhero. Under his watch, countless other superheros appeared and followed in his footsteps. However, after 5 years of war, Zero disappeared without a trace."
														,"A male engineer transmigrated into another world, and became a prince. This place strikingly resembles the Middle Ages of Europe, but at the same time, it seems kind of different? Witches truly exists, and they even possess magic powers! Magic powers are productive forces! Save the witches, liberate the productive forces! Open map, fight demons, break conspiracies, climb up the science and technology tree, and open the path of hardcore ‘farming’!"
														,"The science-fiction adventure series follows what happens when suddenly the world's biggest-ever \"crisis\" arrives."
														,"After his unfortunate death, Tomokui Kanata has been reincarnated as a member of the weakest race, a goblin named Rou. However, Rou has retained his previous life's memories, as well as an ability that allows him to gain strength and abilities from eating. He also possesses a unique evolutionary tree. Now, Rou has made a promise to himself that he would live this life to the fullest. In this alternate world of survival of the fittest, events unfold with competent subordinates and comrades."
		};
		
		String authors[]=new String[] {
				"GEE So-Lyung, JANG Sung-Lak"
				,"ODA Eiichiro"
				,"Masashi Kishimoto"
				,"Gotouge Koyoharu"
				,"One - Murata Yuusuke"
				,"Hakumai Ryou"
				,"Mad Snail"
				,"Horikoshi Kouhei"
				,"Siu"
				,"Tabata Yuuki"
				,"Kagyu Kumo - Kurose Kousuke"
				,"Park Yong-Je"
				,"Fuse - Kawakami Taiki"
				,"Kubo Tite"
				,"Novelist Sf - Wu Liao Kan Kan Tian - Hai Luo"
				,"Er Mu - Dr Woodman"
				,"Inagaki Riichiro - Boichi"
				,"Kanekiru Kogitsune - Kobayakawa Haruyoshi"
				
		};
		
		for(int i=0;i<count_sample;i++) {
			Comic comic=new Comic(comicNames[i],descriptions[i],authors[i]);
			
			List<Genre> g=new ArrayList<Genre>();
			int randomGenreCount=(int)Math.round(Math.random()*6+1);
			Collections.shuffle(mutableGenres);
			for(int j=0;j<randomGenreCount;j++) {
				g.add(mutableGenres.get(j));
			}
			comic.setViewCount((int)Math.round(Math.random()*1000));
			comic.setGenres(g);
			comicRepository.save(comic);
			
			comicThumbImageRepository.save(new ComicThumbImage((i+1)+".png",'A',comic));
		}
		
		ComicChapter chapter1=new ComicChapter("Chapter 1",comicRepository.getOne(6));
		comicChapterRepository.save(chapter1);
		for(int i=19;i<=42;i++) {
			chapterImageRepository.save(new ChapterImage('A',i+".jpg",chapter1));
			chapterImageRepository.save(new ChapterImage('B',i+".jpg",chapter1));
		}
		
		ComicChapter chapter2=new ComicChapter("Chapter 1",comicRepository.getOne(1));
		comicChapterRepository.save(chapter2);
		ComicChapter chapter3=new ComicChapter("Chapter 2",comicRepository.getOne(1));
		comicChapterRepository.save(chapter3);
		for(int i=43;i<=93;i++) {
			chapterImageRepository.save(new ChapterImage('A',i+".jpg",chapter2));
			chapterImageRepository.save(new ChapterImage('B',i+".jpg",chapter2));
		}
		
		for(int i=94;i<=132;i++) {
			chapterImageRepository.save(new ChapterImage('A',i+".jpg",chapter3));
			chapterImageRepository.save(new ChapterImage('B',i+".jpg",chapter3));
		}
//		// TODO Auto-generated method stub
//		Comic comic1=new Comic("Solo leveling","10 years ago, after “the Gate” that connected the real world with the monster world opened,"
//				+ " some of the ordinary, everyday people received the power to hunt monsters within the Gate. They are known as \"Hunters\"."
//				+ " However, not all Hunters are powerful. My name is Sung Jin-Woo, an E-rank Hunter. I'm someone who has to risk his life in the lowliest of dungeons, the \"World's Weakest\"."
//				+ " Having no skills whatsoever to display, I barely earned the required money by fighting in low-leveled dungeons… at least until I found a hidden dungeon with the hardest difficulty within the D-rank dungeons! "
//				+ "In the end, as I was accepting death, I suddenly received a strange power, a quest log that only I could see, a secret to leveling up that only I know about! If I trained in accordance with my quests and hunted monsters, my level would rise."
//				+ " Changing from the weakest Hunter to the strongest S-rank Hunter!","GEE So-Lyung, JANG Sung-Lak");
//		
//		List<Genre> g1=new ArrayList<Genre>();
//		g1.add(genres.get(0));
//		g1.add(genres.get(4));
//		comic1.setGenres(g1);
//		comic1.setViewCount(67);
//		Comic comic2=new Comic("One piece","As a child, Monkey D. Luffy dreamed of becoming the King of the Pirates."
//				+ " But his life changed when he accidentally gained the power to stretch like rubber...at"
//				+ " the cost of never being able to swim again! Now Luffy, with the help of a motley collectio"
//				+ "n of nakama, is setting off in search of \"One Piece,\" said to be the greatest treasure in the world."
//				+ ".","ODA Eiichiro");
//		List<Genre> g2=new ArrayList<Genre>();
//		g2.add(genres.get(0));
//		g2.add(genres.get(1));
//		g2.add(genres.get(5));
//		comic2.setGenres(g2);
//		comic2.setViewCount(125);
//		Comic comic3=new Comic("Naruto","Twelve years ago the Village Hidden in the Leaves was attacked by a fearsome threat."
//				+ " A nine-tailed fox spirit claimed the life of the village leader, the Hokage, and many others"
//				+ ". Today, the village is at peace and a troublemaking kid named Naruto is struggling to graduate "
//				+ "from Ninja Academy. His goal may be to become the next Hokage, but his true destiny will be much"
//				+ " more complicated. The adventure begins now!","Masashi Kishimoto");
//		List<Genre> g3=new ArrayList<Genre>();
//		g3.add(genres.get(0));
//		g3.add(genres.get(1));
//		g3.add(genres.get(5));
//		comic3.setGenres(g3);
//		comic3.setViewCount(92);
//		Comic comic4=new Comic("Kimetsu No Yaiba","Tanjiro is the oldest son in his family who has lost his father. One day, Tanjiro ventures off to another town to sell charcoal. Instead of going home, he ends up staying the night at someone else's house due to rumors"
//				+ " of a demon nearby in the mountains. When he gets home the following day, a terrible tragedy awaits him."
//				,"Gotouge Koyoharu");
//		List<Genre> g4=new ArrayList<Genre>();
//		g4.add(genres.get(0));
//		g4.add(genres.get(5));
//		comic4.setGenres(g4);
//		comic4.setViewCount(71);
//		Comic comic5=new Comic("Onepunch-Man","One punch-Man imitates the life of an average hero who wins all of his fights with only one punch!"
//				+ " This is why he is called Onepunch man Manga. This story takes place in the fictional Z-City."
//				+ " The world is full of mysterious beings, villains and monsters that cause destruction and havoc."
//				+ " An association of heroes has been established to protect the citizens from all harms and enemies."
//				+ " People with superhuman ability can register themselves with the association that protects citizens. "
//				+ "There, they will be required to take a series of tests to determine their ability and what class they are"
//				+ ". Class S being the highest and class C being the lowest.","	One - Murata Yuusuke");
//		List<Genre> g5=new ArrayList<Genre>();
//		g5.add(genres.get(0));
//		g5.add(genres.get(5));
//		g5.add(genres.get(7));
//		comic5.setGenres(g5);
//		comic5.setViewCount(182);
//		
//		comicRepository.save(comic1);
//		comicRepository.save(comic2);
//		comicRepository.save(comic3);
//		comicRepository.save(comic4);
//		comicRepository.save(comic5);
//		
//
//		comicThumbImageRepository.save(new ComicThumbImage("1.png",'A',comic1));
//		comicThumbImageRepository.save(new ComicThumbImage("2.png",'A',comic2));
//		comicThumbImageRepository.save(new ComicThumbImage("3.png",'A',comic3));
//		comicThumbImageRepository.save(new ComicThumbImage("4.png",'A',comic4));
//		comicThumbImageRepository.save(new ComicThumbImage("5.png",'A',comic5));
//		
//		
//		
//
//		
//	
//		comicChapterRepository.save(new ComicChapter("Chapter 1: The word break aaaaaaaaa",comic1));
//		comicChapterRepository.save(new ComicChapter("Chapter 2",comic1));
//		
//		comicChapterRepository.save(new ComicChapter("Chapter 1",comic2));
//		comicChapterRepository.save(new ComicChapter("Chapter 2",comic2));
//		
//		comicChapterRepository.save(new ComicChapter("Chapter 1",comic3));
//		comicChapterRepository.save(new ComicChapter("Chapter 2",comic3));
//		
//		
//		
//		
		
		comicThumbImageRepository.flush();
		comicRepository.flush();
		comicChapterRepository.flush();
		
		
		bannerService.addBanner(new Image('A',"banner (1).png"));
		bannerService.addBanner(new Image('A',"banner (2).png"));
		bannerService.addBanner(new Image('A',"banner (3).png"));
		
		
		
	}

}
