package org.openqa.selenium.support.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;

public class AjaxElementLocatorFactory implements ElementLocatorFactory{
	private final SearchContext searchContext;
	private final int timeOutInSecond;


public AjaxElementLocatorFactory(SearchContext searchContext, int timeOutInSecond){
  this.searchContext=searchContext;
   this.timeOutInSecond=timeOutInSecond;


}

@Override
public ElementLocator createLocator(Field field) {
	
	return new AjaxElementLocator(searchContext,field,timeOutInSecond);
}
}