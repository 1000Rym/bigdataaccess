package com.bigdataaccess.support;

public class TagChanger {
	public static String enDictionaryTypeToGoogleType(String tag){
		String changedTag = "unknownTag:"+tag;
		
		if(tag.equals("a"))
			changedTag = "adjective";
		else if(tag.equals("v"))
			changedTag = "verb";
		else if(tag.equals("n")){
			changedTag = "noun";
		}else if(tag.equals("adverb")){
			changedTag ="adverb";
		}
		
		return changedTag;
	}
}
