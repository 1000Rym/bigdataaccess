package com.bigdataaccess.google.tranlate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleTranlateAccesor {
	public final static String KOREAN = "ko";
	public final static String ENGLISH = "en";
	public final static String CHINSE_SIMPLIFIED = "zh-CN";
	public final static String CHINSE_TRADITIOANL = "zh-TW";
	public final static String JAPANESE = "ja";

	private WebDriver mDriver;
	private final String googleTranslateDefaultURL = "https://translate.google.co.kr/?hl=en&tab=TT#";

	/**
	 * Need webDriver from Selenium_lib to use GoogleTranlateAccesor
	 * 
	 * @param webDriver
	 *            an web driver engine which auto run/test the web page.
	 */
	public GoogleTranlateAccesor(WebDriver webDriver) {
		this.mDriver = webDriver;
	}
	
	/**
	 * This result return not tagged Result(Not reliable).
	 * @return not tagged result.(big text result in google translate page)
	 */
	private String getNotTaggedResult(){
		WebElement bigResult = mDriver.findElement(By.id("result_box"));
		return bigResult.getText();
	}
	
	/**
	 * Get minutely described web element list on below of translate page.
	 * @return result web element list.
	 */
	private List<WebElement> getResultElementList(){
		List<WebElement> resultListTable = null;
		
		Boolean isPresent = mDriver.findElements(By.className("gt-baf-table")).size() > 0;
		
		if(isPresent){
			WebElement tranlateTable = mDriver.findElement(By.className("gt-baf-table"));
			resultListTable= tranlateTable.findElements(By.tagName("div"));
		}
		return resultListTable;
	}
	
	private boolean isSameTag(WebElement element, String tag){
		String elementClass = element.getAttribute("class");
		String classNameShownOnTag = "gt-baf-cell gt-baf-pos-head";
		
		if(elementClass.equals(classNameShownOnTag)){
			if(element.getText().equals(tag)){
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isWordElement(WebElement element){
		String elementClass = element.getAttribute("class");
		String classNameShownOnWord = "gt-baf-cell gt-baf-word-clickable";
		if(elementClass.equals(classNameShownOnWord)){
			return true;
		}
		
		return false;
	}
	

	/**
	 * Translate TagWord From Language A to Language B. <br>
	 * example: tranlateAToB(KOREAN,ENGLISH,taggedWord) translate a Korean
	 * word to English.
	 * 
	 * @param from
	 *            The language which need translated.
	 * @param to
	 *            The language which need to translated.
	 * @param tagWord
	 *            word and tag set.
	 * @return result word and tag set.
	 */
	public TagWord translateAToB(String from, String to, TagWord tagWord) {
		String tranlateAccessURL = googleTranslateDefaultURL + from + "/" + to + "/" + tagWord.getWord();
		TagWord resultTagWord = new TagWord();
	
		mDriver.get(tranlateAccessURL);
		mDriver.navigate().refresh();
		
		resultTagWord.setWord(getNotTaggedResult());
		
	
		if(getResultElementList()!=null&&!tagWord.getTag().equals("UNKNOWN_TAG")){
			boolean isRightTagApeared = false;
			for (WebElement element : getResultElementList()) {
				if (isSameTag(element, tagWord.getTag())) {
					resultTagWord.setTag(element.getText()); 
					isRightTagApeared = true;
				}

				if (isWordElement(element)&&isRightTagApeared) {
					resultTagWord.setWord(element.getText());
					break;  //stop for if first word right tag is shown.
				}

			}
		}

		return resultTagWord;
	}
	
	/**
	 * This method is used for translating english sentence.
	 * @param from The language which need translated.
	 * @param to The language which need to translated.
	 * @param sentence the word you need to translated.
	 * @return
	 */
	public String translateSentence(String from , String to , String sentence){
		//String translateAccessURL ＝  googleTranslateDefaultURL + from + "/" + to + "/" + tagWord.getWord();
		return "";
	}

}
