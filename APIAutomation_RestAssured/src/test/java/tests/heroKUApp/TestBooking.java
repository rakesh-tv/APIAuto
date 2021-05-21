package tests.heroKUApp;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.heroKUApp.Bookingdates;
import pojo.heroKUApp.BookingsRequest;
import pojo.heroKUApp.BookingsResponse;
import requests.heroKUApp.Booking;

public class TestBooking {

    String bookingId = "";

    @Test(dataProvider = "dataForNewBookng")
    public void verifyCreateBooking(BookingsRequest newBooking){
        Response response = new Booking().createNewBooking(newBooking);
        Assert.assertEquals(response.statusCode(), 200);

        BookingsResponse bookingsResponse = response.as(BookingsResponse.class);
        bookingId = bookingsResponse.bookingid;
    }

    @AfterTest
    public void deleteBooking(){
        Response response = new Booking().deleteBooking(bookingId);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @DataProvider(name = "dataForNewBookng")
    public Object[][] getDataForNewBooking(){
        BookingsRequest booking = new BookingsRequest();
        booking.setFirstname("Tim");
        booking.setLastname("Brooks");
        booking.setTotalprice(100);
        booking.setDepositpaid(true);
        Bookingdates dates = new Bookingdates();
        dates.setCheckin("2021-1-1");
        dates.setCheckout("2021-1-2");
        booking.setBookingdates(dates);
        booking.setAdditionalneeds("Early Check-In");

        return new Object[][]{{ booking}};
    }
}
