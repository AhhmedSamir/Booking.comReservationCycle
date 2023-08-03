package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import data.ExcelReader;
import pages.DetailsPage;
import pages.HomePage;
import pages.SearchResultsPage;

public class BookTolipHotelTest extends TestBase{

	HomePage homePage;
	DetailsPage detailsPage;
	SearchResultsPage resultsPage;
	
	@DataProvider(name ="TestData")
	public static Object[][] BookingData() throws IOException{
		ExcelReader ER = new ExcelReader();
		
			return ER.getExcelData();
	}
	@Test(dataProvider = "TestData")
	public void searchForResults(String name, String checkInDate, String checkOutDate, String hotelName, String reservationCheckInDate, String reservationCheckOutDay) throws InterruptedException {
		homePage = new HomePage(driver);
		resultsPage = new SearchResultsPage(driver);
		detailsPage = new DetailsPage(driver);
		wait_until_element_is_present(homePage.getAD());
		homePage.closeAd();
		wait_until_element_is_present(homePage.getSearchField());
		Scroll_To_Element(homePage.getSearchField());
		homePage.searchForCity(name);
		wait_until_element_is_present(homePage.getMonth()) ;
		homePage.chooseChickInAndCheckOutDates(checkInDate, checkOutDate);
		homePage.searchForHotels();	
		resultsPage.findTolipHotel();
		Assert.assertTrue(resultsPage.getTolipHotel().getText().contains(hotelName) );
		resultsPage.goToDetailsPage();
		switchToHandle();
		Scroll_To_Element(detailsPage.getCheckOutDate());
		Assert.assertEquals(detailsPage.getCheckInDate().getText(),reservationCheckInDate);
		Assert.assertEquals(detailsPage.getCheckOutDate().getText(),reservationCheckOutDay);
		Scroll_To_Element(detailsPage.getRoomsTable());
		detailsPage.makeReservation();
	}
}
