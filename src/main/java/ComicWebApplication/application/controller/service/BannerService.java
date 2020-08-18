package ComicWebApplication.application.controller.service;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import ComicWebApplication.application.entity.Image;

@Component
public class BannerService {
	private ArrayList<Image> bannerContainer;

	public BannerService() {
		super();
		bannerContainer=new ArrayList<Image>();
	}
	
	public void addBanner(Image bannerImage) {
		bannerContainer.add(bannerImage);
	}
	
	public void removeBanner(int bannerIndex) {
		bannerContainer.remove(bannerIndex);
		
	}
	
	public ArrayList<Image> getBannerContainer(){
		return bannerContainer;
	}
	
	
	
}
