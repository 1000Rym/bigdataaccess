package com.bigdataaccess.naver.moviereview;


public class MovieReview {
	private double movieScore = 0;
	private double likeScore = 0;
	private double dislikeScore = 0;
	private String author ="unknown";
	private String comments="";
	private String date ="";
	
	public void setMovieScore(double movieScore){
		this.movieScore = movieScore;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setlikeScore(double likeScore){
		this.likeScore = likeScore;
	}
	
	public void setdislikeScore(double dislikeScore){
		this.dislikeScore=dislikeScore;
	}
	public double getScore(){
		return this.movieScore;
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public String getComments(){
		return this.comments;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public double getlikeScore(){
		return this.likeScore;
	}
	
	public double getdislikeScore(){
		return this.dislikeScore;
	}
}
