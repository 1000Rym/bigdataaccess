package com.bigdataaccess.google.tranlate;

public class TagWord {
	private String tag="UNKNOWN_TAG";
	private String word="";
	
	public TagWord(){}
	
	public TagWord(String tag, String word){
		this.tag = tag;
		this.word = word;
	}
	
	public void setTag(String tag){
		this.tag = tag;
	}
	
	public void setWord(String word){
		this.word = word;
	}
	
	public String getTag(){
		return this.tag;
	}
	
	public String getWord(){
		return this.word;
	}
	
	
	
}
