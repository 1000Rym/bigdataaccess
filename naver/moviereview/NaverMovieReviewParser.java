package com.bigdataaccess.naver.moviereview;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;



public class NaverMovieReviewParser {
	/**
	 * @author Rym.Thousand
	 * @date 2015.04.30
	 * @version 1.0.0
	 * @description This program is designed for getting naver's movie review.
	 * @see http://www.seleniumhq.org/download/
	 */

	private static volatile NaverMovieReviewParser mParser; //Making singleton class.
	private String reviewURL= "http://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn"; //This link might be changed if naver movie site changed.
	private long movieCode=98438; //Set avengers2 as default.
	private int maximumSearchPage=1; //Set maximum page as 1.
	private boolean isMoviegoersOnly = false;
	
	private NaverMovieReviewParser(){
		Logger logger = Logger.getLogger ("");
		logger.setLevel (Level.OFF); //Set off log system.
	}
	
	public static NaverMovieReviewParser getInstance(){
		if(mParser == null){
			synchronized (NaverMovieReviewParser.class) {
				mParser = new NaverMovieReviewParser();
			}
		}
		return mParser;
	}
	
	public void setMovieCode(long movieCode){
		this.movieCode = movieCode;
	}
	
	public void setMaximumSearchPage(int maximumSearchPage){
		this.maximumSearchPage = maximumSearchPage;
	}
	
	public void setMoviegoersOnly(boolean isMoviegoersOnly){
		this.isMoviegoersOnly = isMoviegoersOnly;
	}
	
	/**
	 * This method need to be changed when naver's movie structure change. 
	 * @return movie list
	 */
	public List<MovieReview> getMovieReviewList(){
		List<MovieReview> movieReviewList = new ArrayList<MovieReview>();
		String visitURL = reviewURL+"?"+"code="+movieCode+"&type=after";
		if(isMoviegoersOnly){
			visitURL +="&onlyActualPointYn=Y";
		}
		
		WebDriver driver = new HtmlUnitDriver();
		
		for(int page = 1 ; page <=maximumSearchPage+1;page++){
			String pageURL= visitURL+"&page="+page;
			driver.get(pageURL); //visit the page.
			WebElement reviewArea = driver.findElement(By.className("score_result"));
			List<WebElement> reviewList = reviewArea.findElements(By.tagName("li"));
			
			for(WebElement eachReview : reviewList){
				MovieReview movieReview = new MovieReview();
				WebElement starscoreElement = eachReview.findElement(By.className("star_score"));
				List<WebElement> likescoreElement = eachReview.findElement(By.className("btn_area")).findElements(By.tagName("strong"));
				WebElement commentsElement = eachReview.findElement(By.tagName("p"));
				List<WebElement> otherInfo = eachReview.findElement(By.tagName("dl")).findElements(By.tagName("em"));
				movieReview.setMovieScore(Double.parseDouble(starscoreElement.getText()));
				movieReview.setComments(commentsElement.getText());
				movieReview.setAuthor(otherInfo.get(0).getText());
				movieReview.setDate(otherInfo.get(1).getText());
				movieReview.setlikeScore(Double.parseDouble(likescoreElement.get(0).getText()));
				movieReview.setdislikeScore(Double.parseDouble(likescoreElement.get(1).getText()));
				movieReviewList.add(movieReview);			
			}
			
		}
		
		
		return movieReviewList;
	}
	
	

}
