package com.bigdataaccess.naver.moviereview;

import java.util.List;

public class MovieReviewTest {
	/**
	 * @author  Rym.Thousand
	 * @date 2015.04.30
	 */	
	public static void main(String[] args) {
		long movieCode = 98438; //avengers2 code 
		int maxiumSeachPage = 10; //maximumsearchPage
		boolean isMoviegoersOnly = true; //Only Movie goer or not
		

		NaverMovieReviewParser.getInstance().setMovieCode(movieCode);
		NaverMovieReviewParser.getInstance().setMaximumSearchPage(maxiumSeachPage);
		NaverMovieReviewParser.getInstance().setMoviegoersOnly(isMoviegoersOnly);
		
		
		List<MovieReview> movieReviewList = NaverMovieReviewParser.getInstance().getMovieReviewList();
		
		for(MovieReview movieReview: movieReviewList){
			System.out.print("Author:"+movieReview.getAuthor()+" ");
			System.out.print("Comments:"+movieReview.getComments()+" ");
			System.out.println("Date:"+movieReview.getDate());
			System.out.println("Starscore:"+movieReview.getScore()+" ");
			System.out.println("likescore:"+movieReview.getlikeScore()+" ");
			System.out.println("dislikecore:"+movieReview.getdislikeScore()+" ");
		}
	}
}
