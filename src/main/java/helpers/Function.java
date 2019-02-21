package helpers;

import java.util.Set;

import org.openqa.selenium.interactions.touch.ScrollAction;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsersetup.BaseClass;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class Function extends BaseClass{
	public Function(AndroidDriver androiddriver) {
		setDriver(androiddriver);
	}
	public void scrollPage(int count) {
		int pressX=getAndroidDriver().manage().window().getSize().width/2;
		int bottomY=getAndroidDriver().manage().window().getSize().height*4/5;
		int topY=getAndroidDriver().manage().window().getSize().height/8;
		for (int i=0;i<=count;i++) {
			scroll(pressX,bottomY,pressX,topY);
		}
	}
		public void scroll(int fromX,int fromY,int toX,int toY) {
			TouchAction touchAction=new TouchAction((PerformsTouchActions)getAndroidDriver());
				
			touchAction.longPress(fromX,fromY).moveTo(toX,toY).release().perform();
		}
	
		public void scrollPageByHeight(int height) {
			int pressX=getAndroidDriver().manage().window().getSize().width/2;
			int bottomY= getAndroidDriver().manage().window().getSize().height*4/5;
			int topY=getAndroidDriver().manage().window().getSize().height/height;
			
			TouchAction touchAction= new TouchAction((PerformsTouchActions)getAndroidDriver());
			touchAction.longPress(pressX,bottomY).moveTo(pressX,topY).release().perform();
		}

		
		public AndroidDriver switchContext(String context) {
			Set<String> contextNames=getAndroidDriver().getContextHandles();
			for (String contextname:contextNames ) {
				if(contextname.contains(context)) {
					getAndroidDriver().context(contextname);
				}
			}
			return getAndroidDriver();
		}
		public AndroidDriver  switchtoContext(String contetx) {
			
			Set<String> contextdisplay=getAndroidDriver().getContextHandles();
			System.out.println("Total Number of Context found After we Reach to webView: "+contextdisplay.size());
			for(String contetnaming : contextdisplay) {
				if (contetx.contains("WEBVIEW")) {
					System.out.println("Context name is " +contetx);
					getAndroidDriver().context(contetx);
					break;
				}
			}
			
			return getAndroidDriver();
			
		}
		
	}

