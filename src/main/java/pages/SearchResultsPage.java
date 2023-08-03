package pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends PageBase{

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		js = new JavascriptExecutor() {
			
			@Override
			public Object executeScript(String script, Object... args) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object executeAsyncScript(String script, Object... args) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	@FindBy(css="div.d4924c9e74")
	List <WebElement> cards;
	
	
@FindBy(partialLinkText = "Tolip Hotel Alexandria")
WebElement TolipHotel;
public WebElement getTolipHotel() {
	return TolipHotel;
}
@FindBy(css="a.fc63351294.a822bdf511.d4b6b7a9e7.fa565176a8.f7db01295e.c334e6f658.f4605622ad.b2f0d6a80e")
WebElement seeAvailabilityBtn;

@FindBy(css = "button.fc63351294.a822bdf511.e3c025e003.fa565176a8.f7db01295e.c334e6f658.e1b7cfea84.f9d6150b8e")
WebElement nextPageBtn;

public void findTolipHotel() {
	for (WebElement webElement : cards) {
		while (!(webElement.getText().contains("Tolip Hotel Alexandria")))
	{Scroll_To_Element(nextPageBtn);;
	clickBTN(nextPageBtn);}
		Scroll_To_Element(TolipHotel);
	}
}

public void goToDetailsPage() {
	clickBTN(seeAvailabilityBtn);
}
}

