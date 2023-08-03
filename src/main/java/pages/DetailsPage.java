package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailsPage extends PageBase{

	public DetailsPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath  = "//*[@id=\"hprt-table\"]/tbody/tr[1]/td[1]/div/div[3]/div/label[2]/div/input")
	WebElement queenBedCheckBox;
	public WebElement getQueenBedCeckBox() {
		return queenBedCheckBox;
	}
	@FindBy(id="rooms_table")
	WebElement roomsTable;
	public WebElement getRoomsTable() {
		return roomsTable;
	}
	@FindBy(id="hprt_nos_select_78883120_91939502_0_33_0_41999")
	WebElement amountDDL;
	@FindBy(css="button.txp-bui-main-pp.bui-button.bui-button--primary.hp_rt_input.px--fw-cta.js-reservation-button")
	WebElement reserveBtn;
	@FindBy(xpath="/html/body/div[3]/div/div[6]/div[1]/div[3]/div[6]/div/div[3]/div/form/div/div[1]/div[1]/div[2]/div/div/div/div/div[2]")
	WebElement checkInDate;
	public WebElement getCheckInDate() {
		return checkInDate;
	}
	@FindBy(xpath="/html/body/div[3]/div/div[6]/div[1]/div[3]/div[6]/div/div[3]/div/form/div/div[1]/div[1]/div[3]/div/div/div/div/div[2]")
	WebElement chckOutDate;
	public WebElement getCheckOutDate() {
		return chckOutDate;
	}

	public void makeReservation() {
		clickBTN(queenBedCheckBox);
		SelectDDL(amountDDL, 1);
		clickBTN(reserveBtn);
	}
}
