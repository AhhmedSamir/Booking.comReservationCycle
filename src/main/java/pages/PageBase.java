package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageBase {
protected WebDriver driver;
public JavascriptExecutor js;
public Select select;
public Actions builder;
	
	public PageBase(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public static void clickBTN(WebElement button) {
		button.click();
	}
	
	public static void sendTxtToElement(WebElement txt,String value) {
		txt.sendKeys(value);
	}
	public static void SelectDDL(WebElement e, int x) {
		Select drop = new Select(e);
		drop.selectByIndex(x);
	}
	public static void uplodFile(WebElement e , String imageName) throws AWTException {
		e.click();
		String filePath= System.getProperty("user.dir")+"\\Uploads\\"+imageName;
		StringSelection selection = new StringSelection(filePath);
		Clipboard clipboard= Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public static WebElement findCell(WebElement table, String value) {
		  List<WebElement> rows = table.findElements(By.tagName("tr"));
		  for (WebElement row : rows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
		    for (WebElement cell : cells) {
		      if (cell.getText().equals(value)) {
		        cell.click();
		      }
		    }
		  }
		  return null;
		}

	public void ScrollDown() {
		js.executeScript("scrollBy(0,2500)");
	}
	public void Scroll_To_Element(WebElement e) {
		
		js.executeScript("arguments[0].scrollIntoView();", e);
	}
}
