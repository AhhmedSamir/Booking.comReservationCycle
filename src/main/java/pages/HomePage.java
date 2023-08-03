package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase{

	public HomePage(WebDriver driver) {
		super(driver);
	
	}
	@FindBy(css="div.b2bfde3841.dce41eb96a.af1cf9c281.df85897940")
	WebElement AD;
	public WebElement getAD() {
		return AD;
	}
	@FindBy(css = "button.fc63351294.a822bdf511.e3c025e003.fa565176a8.f7db01295e.c334e6f658.ae1678b153")
	WebElement closeADBtn;
	public WebElement getCloseADBtn() {
		return closeADBtn;
	}
	@FindBy(id=":rc:")
	WebElement searchField;
	public WebElement getSearchField() {
		return searchField;
	}
	@FindBy(css = "div.f9cf783bde")
	WebElement dateList;
	
	@FindBy(css = "button.fc63351294.a822bdf511.e3c025e003.fa565176a8.cfb238afa1.c334e6f658.ae1678b153.c9fa5fc96d.be298b15fa")
	WebElement nextPageBtn;
	public WebElement getNextPageBtn() {
		return nextPageBtn;
	}
	@FindBy(css="ul.ce50aa40cd.d319063cd8.b530332a61")
	List<WebElement> suggestList;
	@FindBy(xpath = "/html/body/div[3]/div[2]/div/form/div[1]/div[2]/div/div[2]/div/nav/div[2]/div/div[1]/div/div[2]/h3")
	WebElement month;
	public WebElement getMonth() {
		return month;
	}
	@FindBy(xpath = "//*[@id=\"calendar-searchboxdatepicker\"]/div/div[1]/div/div[2]/table/tbody")
	WebElement table;
	@FindBy(xpath = "//*[@id=\"indexsearch\"]/div[2]/div/form/div[1]/div[4]/button")
	WebElement searchBtn;
	
	
	public void closeAd() {
		if (AD.isDisplayed()) {
		clickBTN(closeADBtn);}
	}
	public void searchForCity(String cityName) throws InterruptedException {
		sendTxtToElement(searchField,cityName);
		Thread.sleep(1000);
		for(int i=0; i < suggestList.size(); i++) {
			WebElement el = suggestList.get(i);
			if(el.getText().contains("Alexandria")) {
				el.click();
				break;
				
			}else {
				continue;
			}
			
		}	
	}
	public void chooseChickInAndCheckOutDates(String checkInDate, String checkOutDate) {
		
		while (!(month.getText().contentEquals("October 2023")))
		 {	
			clickBTN(nextPageBtn);
		 }
		
		 findCell(table, checkInDate);
		findCell(table, checkOutDate);
		}		
	
	public void searchForHotels() {
		clickBTN(searchBtn);
	}
	
	
	
}
