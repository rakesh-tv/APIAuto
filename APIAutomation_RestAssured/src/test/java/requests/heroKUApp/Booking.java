package requests.heroKUApp;

import Utils.Util;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.heroKUApp.BookingsRequest;
import pojo.heroKUApp.BookingsResponse;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Booking {

    public Response createNewBooking(BookingsRequest booking){
        RestAssured.baseURI = Util.getBaseURLForHero();
        BookingsRequest bookingBuilder = BookingsRequest.builder().firstname(booking.getFirstname())
                                                    .lastname(booking.getLastname())
                                                    .totalprice(booking.getTotalprice())
                                                    .depositpaid(booking.getDepositpaid())
                                                    .bookingdates(booking.getBookingdates())
                                                    .additionalneeds(booking.getAdditionalneeds())
                                                    .build();
        Response res = given().
                            contentType(ContentType.JSON).
                            body(bookingBuilder).
                       when().
                            post("/booking").
                       then().
                            extract().response();
        return res;
    }

    public Response updateBooking(BookingsRequest booking, String id){
        RestAssured.baseURI = Util.getBaseURLForHero();

        BookingsRequest bookingBuilder = BookingsRequest.builder().firstname(booking.getFirstname())
                .lastname("Cook")
                .totalprice(booking.getTotalprice())
                .depositpaid(booking.getDepositpaid())
                .bookingdates(booking.getBookingdates())
                .additionalneeds(booking.getAdditionalneeds())
                .build();
        Response res = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                cookie("token", auth.token == null ? auth.getAuthToken() : auth.token).
                body(bookingBuilder).
                when().log().all().
                put("/booking/" + id).
                then().
                extract().response();
        return res;
    }

    public Response deleteBooking(String bookingId){
        RestAssured.baseURI = Util.getBaseURLForHero();

        Response res = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                cookie("token", auth.token == null ? auth.getAuthToken() : auth.token).
                when().log().all().
                delete("/booking/" + bookingId).
                then().
                extract().response();
        return res;
    }


}
