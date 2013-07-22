package com.android.mahiraj.fundooapp;

/**
 * @author Mahesh Rajput
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;

import com.android.mahiraj.metadata.CategoryContents;

public class FundOOApplication extends Application {

	/**
	 * For holding the list of service category items. eg:jokes,news,etc
	 */
	private List<CategoryContents> categoryContentsList;

	public List<CategoryContents> getCategoryContentsList() {
		return categoryContentsList;
	}

	public void setCategoryContentsList(List<CategoryContents> categoryContentsList) {
		this.categoryContentsList = categoryContentsList;
	}

	/**
	 * This is for caching categoryContents items corresponding to Category
	 * items. eg:jokes:list of jokes...
	 */

	private Map<String, List<CategoryContents>> cacheMap;


	public Map<String, List<CategoryContents>> getCacheMap() {
		return cacheMap;
	}

	public void setCacheMap(Map<String, List<CategoryContents>> cacheMap) {
		this.cacheMap = cacheMap;
	}
	
	
	
	
	
	private List<String> puzzleAns;



	public List<String> getPuzzleAns() {
		return puzzleAns;
	}

	public void setPuzzleAns(List<String> puzzleAns) {
		this.puzzleAns = puzzleAns;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.categoryContentsList = new ArrayList<CategoryContents>();
		this.cacheMap = new HashMap<String, List<CategoryContents>>();
		this.puzzleAns = new ArrayList<String>();
	}
}
