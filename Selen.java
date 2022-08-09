package com.org.SeleMaven;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Selen {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception,InterruptedException, AWTException, IOException {
		WebDriverManager.edgedriver().setup();
		WebDriver dr=new EdgeDriver();
		dr.get("http://testleaf.herokuapp.com/");
		//dr.manage().window().maximize();
		List<WebElement> list=dr.findElements(By.xpath("//li[@class='col-lg-3 col-md-3 col-sm-3']/descendant::a"));
		String parenttab=dr.getWindowHandle();
		Iterator it=list.iterator();
		while(it.hasNext()) {
			WebElement lists=(WebElement) it.next();
			if(lists.getAttribute("href").contains("Edit.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String>childtabs=dr.getWindowHandles();
				for(String childs:childtabs ) {
					if(parenttab.equals(childs)) {
						continue;
					}
					else if(parenttab!=childs) {
						System.out.println("\t"+dr.getTitle()+"\t");
						System.out.println();
						dr.switchTo().window(childs);
						Thread.sleep(2000);
						WebElement a=dr.findElement(By.id("email"));
						a.click();
						a.sendKeys("chbvsdujhbvsdhb@gmail.com");
						WebElement b = dr.findElement(By.xpath("//input[@value='Append ']"));
						b.click();
						b.sendKeys("Here It Appended !");
						b.sendKeys(Keys.TAB);
						WebElement c = dr.findElement(By.xpath("//input[@value='TestLeaf']"));
						System.out.println(c.getAttribute("value"));
						WebElement d = dr.findElement(By.xpath("//input[@value='Clear me!!']"));
						d.clear();
						WebElement e = dr.findElement(By.xpath("//input[@disabled='true']"));
						if(e.isEnabled()) {System.out.println("Button Active");}else {System.out.println("Button not Active !");}
						Thread.sleep(2000);
						dr.switchTo().window(childs).close();
						dr.switchTo().window(parenttab);
						Thread.sleep(2000);
						System.out.println();
					}
				}
			}
			if(lists.getAttribute("href").contains("Button.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String>childtabs=dr.getWindowHandles();
				Iterator it2=childtabs.iterator();
				while(it2.hasNext()) {
					String childtab=(String)it2.next();
					if(parenttab.equals(childtab)) {continue;}
					if(parenttab!=childtab) {dr.switchTo().window(childtab);}
					System.out.println("\t"+dr.getTitle()+"\t");
					System.out.println();
					WebElement a=dr.findElement(By.id("home"));
					System.out.println("Redirect to "+a.getText()); 
					WebElement b=dr.findElement(By.id("position"));
					System.out.println("Position is "+b.getLocation());
					WebElement c = dr.findElement(By.id("color"));
					System.out.println("Color is "+c.getCssValue("color")); 
					WebElement d = dr.findElement(By.id("size"));
					System.out.println("Button Size is "+d.getSize());
					Thread.sleep(2000);
					dr.switchTo().window(childtab).close();
					dr.switchTo().window(parenttab);
					System.out.println();
				}
			}
			if(lists.getAttribute("href").contains("Link.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String>childtabs=dr.getWindowHandles();
				Iterator it2=childtabs.iterator();
				while(it2.hasNext()) {
					String childtab=(String)it2.next();
					if(parenttab.equals(childtab)) {continue;}
					if(parenttab!=childtab) {dr.switchTo().window(childtab);}
					System.out.println("\t"+dr.getTitle()+"\t");
					System.out.println();
					WebElement a=dr.findElement(By.linkText("Go to Home Page"));
					System.out.println(a.getText()); 
					WebElement b=dr.findElement(By.linkText("Find where am supposed to go without clicking me?"));
					System.out.println("Onclick may redirect to : "+b.getAttribute("href"));
					WebElement c = dr.findElement(By.linkText("Verify am I broken?"));
					String geturl=c.getAttribute("href");
					URL url=new URL(geturl);
					HttpURLConnection http=(HttpURLConnection)url.openConnection();
					http.setConnectTimeout(5000);
					http.connect();
					if(http.getResponseCode()<=500) {System.out.println("BROKEN LINK "+http.getResponseCode()); }
					WebElement d = dr.findElement(By.linkText("Go to Home Page"));
					System.out.println(a.getText()); 
					List<WebElement> e=dr.findElements(By.xpath("//div[@id='contentblock']//following::a"));
					System.out.println("Links Available : "+e.size());
					Thread.sleep(2000);
					dr.switchTo().window(childtab).close();
					dr.switchTo().window(parenttab);
					System.out.println();
				}
			}
			if(lists.getAttribute("href").contains("Image.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String>childtabs=dr.getWindowHandles();
				Iterator it2=childtabs.iterator();
				while(it2.hasNext()) {
					String childtab=(String)it2.next();
					if(parenttab.equals(childtab)) {continue;}
					if(parenttab!=childtab) {dr.switchTo().window(childtab);}
					System.out.println("\t"+dr.getTitle()+"\t");
					System.out.println();
					WebElement a=dr.findElement(By.xpath("(//div[@class='large-6 small-12 columns']//following::img)[1]"));
					System.out.println(a.getAttribute("onclick")); 
					String link1=a.getAttribute("src");
					URL url1=new URL(link1);
					HttpURLConnection http1=(HttpURLConnection)url1.openConnection();
					http1.setReadTimeout(5000);
					http1.connect();
					if(http1.getResponseCode()!=200)
					{System.out.println("Image is broken ! ");}
					else if(http1.getResponseCode()<=200) {System.out.println("Image is Valid !");}
					WebElement b=dr.findElement(By.xpath("(//div[@class='large-6 small-12 columns']//following::img)[2]"));
					String link=b.getAttribute("src");
					URL url=new URL(link);
					HttpURLConnection http=(HttpURLConnection)url.openConnection();
					http.setReadTimeout(5000);
					http.connect();
					if(http.getResponseCode()!=200)
					{System.out.println("Image is broken ! ");}
					WebElement c = dr.findElement(By.xpath("(//div[@class='large-6 small-12 columns']//following::img)[3]"));
					Actions ab=new Actions(dr);
					ab.moveToElement(c).perform();
					Thread.sleep(2000);
					ab.click(c);
					System.out.println("Click action performed !");
					http1.disconnect();
					Thread.sleep(2000);
					dr.switchTo().window(childtab).close();
					dr.switchTo().window(parenttab);
					System.out.println();
				}
			}
			if(lists.getAttribute("href").contains("Dropdown.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String> childtabs = dr.getWindowHandles();
				Iterator its=childtabs.iterator();
				while(its.hasNext()) {
					String child=(String)its.next();
					if(parenttab.equals(child)) {
						continue;
					}else if(parenttab!=child) {
						dr.switchTo().window(child);
						System.out.println("\t"+dr.getTitle()+"\t");
						System.out.println();
						WebElement a=dr.findElement(By.id("dropdown1"));
						a.click();
						Select sel=new Select(a);
						sel.selectByIndex(0);
						WebElement b=dr.findElement(By.name("dropdown2"));
						b.click();
						sel=new Select(b);
						sel.selectByVisibleText("Appium");
						WebElement c=dr.findElement(By.id("dropdown3"));
						c.click();
						sel=new Select(c);
						sel.selectByValue("3");
						WebElement d=dr.findElement(By.xpath("(//div[@class='example']//following::select)[4]"));
						sel=new Select(d);
						for(int i=0;i<sel.getOptions().size();i++) {
						System.out.println("The size is "+sel.getOptions().size()+" and the options are : "+sel.getOptions().get(i).getText());}
						WebElement e=dr.findElement(By.xpath("(//div[@class='example']//following::select)[6]"));
						sel=new Select(e);
						Robot ro=new Robot();
						ro.keyPress(KeyEvent.VK_CONTROL);
						sel.selectByValue("1");
						sel.selectByValue("2");
						sel.selectByValue("3");
						sel.selectByValue("4");
						ro.keyRelease(KeyEvent.VK_CONTROL);
						for(int j=0;j<sel.getOptions().size();j++) {
						System.out.println("Selected options are : "+sel.getAllSelectedOptions().get(j).getText());}
						dr.switchTo().window(child).close();
						dr.switchTo().window(parenttab);
						Thread.sleep(2000);
						System.out.println();
					}
				}
			}
			if(lists.getAttribute("href").contains("radio.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String> childtabs = dr.getWindowHandles();
				Iterator its=childtabs.iterator();
				while(its.hasNext()) {
					String child=(String)its.next();
					if(parenttab.equals(child)) {
						continue;
					}else if(parenttab!=child) {
						dr.switchTo().window(child);
						System.out.println("\t"+dr.getTitle()+"\t");
						System.out.println();
						WebElement a=dr.findElement(By.id("yes"));
						a.click();
						WebElement b=dr.findElement(By.xpath("(//div[@class='large-6 small-12 columns'])[2]//following-sibling::input[1]"));
						WebElement c=dr.findElement(By.xpath("(//div[@class='large-6 small-12 columns'])[2]//following-sibling::input[2]"));
						if(b.isSelected()==false) {System.out.println("Checkbox b checked is "+b.isSelected());}
						if(c.isSelected()) {System.out.println("Checkbox c checked is "+c.isSelected());}
						WebElement d=dr.findElement(By.xpath("(//div[@class='large-6 small-12 columns'])[3]//following::input[1]"));
						d.click();
						Thread.sleep(2000);
						dr.switchTo().window(child).close();
						dr.switchTo().window(parenttab);
						Thread.sleep(2000);
						System.out.println();
					}
				}
			}
			if(lists.getAttribute("href").contains("checkbox.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String> childtabs = dr.getWindowHandles();
				Iterator its=childtabs.iterator();
				while(its.hasNext()) {
					String child=(String)its.next();
					if(parenttab.equals(child)) {
						continue;
					}else if(parenttab!=child) {
						dr.switchTo().window(child);
						System.out.println("\t"+dr.getTitle()+"\t");
						System.out.println();
						dr.findElement(By.xpath("(//section[@class='innerblock']//following::input)[1]")).click();
						dr.findElement(By.xpath("(//section[@class='innerblock']//following::input)[3]")).click();
						WebElement a=dr.findElement(By.xpath("(//section[@class='innerblock']//following::input)[6]"));
						if(a.isSelected()) {
							System.out.println("The checkbox selected is "+a.isSelected());
						}
						WebElement b=dr.findElement(By.xpath("(//section[@class='innerblock']//following::input)[8]"));
						if(b.isSelected()==true) {
							b.click();
						}
						List<WebElement>lis=dr.findElements(By.xpath("//div[@class='example'][4]//following::input"));
						Iterator itt=lis.iterator();
						while(itt.hasNext()) {
							WebElement listof=(WebElement)itt.next();
							listof.click();
						}
						Thread.sleep(2000);
						dr.switchTo().window(child).close();
						dr.switchTo().window(parenttab);
						Thread.sleep(2000);
						System.out.println();
					}
				}
			}
			if(lists.getAttribute("href").contains("Alert.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String> childtabs = dr.getWindowHandles();
				Iterator its=childtabs.iterator();
				while(its.hasNext()) {
					String child=(String)its.next();
					if(parenttab.equals(child)) {
						continue;
					}else if(parenttab!=child) {
						dr.switchTo().window(child);
						System.out.println("\t"+dr.getTitle()+"\t");
						System.out.println();
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						WebElement a=dr.findElement(By.xpath("//button[@onclick='normalAlert()']"));
						a.click();
						dr.switchTo().alert();
						Thread.sleep(1000);
						dr.switchTo().alert().accept();
						WebElement b=dr.findElement(By.xpath("//button[@onclick='confirmAlert()']"));
						b.click();
						dr.switchTo().alert();
						Thread.sleep(1000);
						dr.switchTo().alert().dismiss();
						WebElement c=dr.findElement(By.xpath("//button[@onclick='confirmPrompt()']"));
						c.click();
						dr.switchTo().alert();
						dr.switchTo().alert().sendKeys("OK ! Alert is Good !!");
						Thread.sleep(1000);
						dr.switchTo().alert().accept();
						WebElement d=dr.findElement(By.xpath("//button[@onclick='lineBreaks()']"));
						d.click();
						dr.switchTo().alert();
						System.out.println(dr.switchTo().alert().getText());
						dr.switchTo().alert().accept();
						Thread.sleep(2000);
						dr.switchTo().window(child).close();
						dr.switchTo().window(parenttab);
						Thread.sleep(2000);
						System.out.println();
						
					}
				}
				}
			if(lists.getAttribute("href").contains("frame.html")) {
			((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
			Set<String> childtabs = dr.getWindowHandles();
			Iterator its=childtabs.iterator();
			while(its.hasNext()) {
				String child=(String)its.next();
				if(parenttab.equals(child)) {
					continue;
				}else if(parenttab!=child) {
					dr.switchTo().window(child);
					System.out.println("\t"+dr.getTitle()+"\t");
					System.out.println();
					dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
					WebElement a=dr.findElement(By.xpath("//iframe[@src='default.html']"));
					dr.switchTo().frame(a);
					dr.findElement(By.id("Click")).click();
					dr.switchTo().defaultContent();
					WebElement b=dr.findElement(By.xpath("//iframe[@src='page.html']"));
					dr.switchTo().frame(b);
					WebElement c=dr.findElement(By.xpath("//iframe[@src='nested.html']"));
					dr.switchTo().frame(c);
					dr.findElement(By.id("Click1")).click();
					dr.switchTo().defaultContent();
					dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
					List<WebElement> findframe = dr.findElements(By.tagName("iframe"));
					findframe.add(a);
					findframe.add(b);
					findframe.add(c);
					System.out.println("Total Frames are : "+findframe.size());
					Thread.sleep(2000);
					dr.switchTo().window(child).close();
					dr.switchTo().window(parenttab);
					Thread.sleep(2000);
					System.out.println();
					
				}
			}
			}
			if(lists.getAttribute("href").contains("Window.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String> childtabs = dr.getWindowHandles();
				Iterator its=childtabs.iterator();
				while(its.hasNext()) {
					String child=(String)its.next();
					if(parenttab.equals(child)) {
						continue;
					}else if(parenttab!=child) {
						dr.switchTo().window(child);
						System.out.println("\t"+dr.getTitle()+"\t");
						System.out.println();
						dr.findElement(By.xpath("//button[@onclick='openWin();']")).click();
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
						dr.findElement(By.xpath("//button[@onclick='openWindows()']")).click();	
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
						Set<String> windowHandles = dr.getWindowHandles();
						System.out.println("Number of windows open : "+windowHandles.size());
						Iterator ite=windowHandles.iterator();
						while(ite.hasNext()) {
							String wind=(String)ite.next();
							try {
								if(!wind.equals(child)&&!wind.equals(parenttab)) {
									dr.switchTo().window(wind).close();
								}
							}catch(Exception E) {System.out.println("No Window to switch");}
						}
						Thread.sleep(2000);
						dr.switchTo().window(child);
						Thread.sleep(1000);
						dr.findElement(By.xpath("//button[@onclick='openWindows();']")).click();
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
						dr.findElement(By.xpath("//button[@onclick='openWindowsWithWait();']")).click();
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
						Set<String> windowHandles2 = dr.getWindowHandles();
						for(String wind2:windowHandles2) {
							try {
								if(!wind2.equals(child)&&!wind2.equals(parenttab)) {
									dr.switchTo().window(wind2).close();
								}
							}catch(Exception E) {System.out.println("No Window to switch");}
						}
						Thread.sleep(2000);
						dr.switchTo().window(child).close();
						dr.switchTo().window(parenttab);
						Thread.sleep(2000);
						System.out.println();
					}
				}
				}
			if(lists.getAttribute("href").contains("Calendar.html")) {
				((JavascriptExecutor)dr).executeScript("window.open('"+lists.getAttribute("href")+"')");
				Set<String> childtabs = dr.getWindowHandles();
				Iterator its=childtabs.iterator();
				while(its.hasNext()) {
					String child=(String)its.next();
					if(parenttab.equals(child)) {
						continue;
					}else if(parenttab!=child) {
						dr.switchTo().window(child);
						System.out.println("\t"+dr.getTitle()+"\t");
						System.out.println();
						for(int i=1;i<=6;i++) {
						dr.findElement(By.tagName("input")).click();
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						WebElement a = dr.findElement(By.xpath("((//tr//following::tr)[1]//following-sibling::td)["+i+"]"));
						a.click();
						System.out.println("Current Date : "+a.getText());
						Thread.sleep(2000);
						}
						for(int i=1;i<=6;i++) {
						dr.findElement(By.tagName("input")).click();
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						WebElement a = dr.findElement(By.xpath("((//tr//following::tr)[2]//descendant::td)["+i+"]"));
						a.click();
						System.out.println("Current Date : "+a.getText());
						Thread.sleep(2000);
						}
						for(int i=1;i<=6;i++) {
						dr.findElement(By.tagName("input")).click();
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						WebElement a = dr.findElement(By.xpath("((//tr//following::tr)[3]//descendant::td)["+i+"]"));
						a.click();
						System.out.println("Current Date : "+a.getText());
						Thread.sleep(2000);
						}
						for(int i=1;i<=6;i++) {
						dr.findElement(By.tagName("input")).click();
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						WebElement a = dr.findElement(By.xpath("((//tr//following::tr)[4]//descendant::td)["+i+"]"));
						a.click();
						System.out.println("Current Date : "+a.getText());
						Thread.sleep(2000);
						}
						for(int i=1;i<=4;i++) {
						dr.findElement(By.tagName("input")).click();
						dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
						WebElement a = dr.findElement(By.xpath("((//tr//following::tr)[5]//descendant::td)["+i+"]"));
						a.click();
						System.out.println("Current Date : "+a.getText());
						Thread.sleep(2000);
						}
						dr.switchTo().window(child).close();
						dr.switchTo().window(parenttab);
						Thread.sleep(2000);
						System.out.println();
					}
				}
				}
	}
		for(int j=5;j>=1;j--) {
		((JavascriptExecutor)dr).executeScript("alert('BROWSER WILL EXIT IN "+j+" SECONDS')");
		Thread.sleep(1000);
		dr.switchTo().alert().accept();
		}
		dr.quit();
		System.out.println("Browser Exited.");
	}
}
