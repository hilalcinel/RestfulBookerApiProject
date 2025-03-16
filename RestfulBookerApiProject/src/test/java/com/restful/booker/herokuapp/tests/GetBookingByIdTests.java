package com.restful.booker.herokuapp.tests;

import com.restful.booker.herokuapp.models.Booking;
import com.restful.booker.herokuapp.models.BookingDates;
import com.restful.booker.herokuapp.models.BookingResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTest{

    BookingResponse bookingResponse;

    @Test
    public void getBookingByIdTest(){

        BookingDates bookingDates = new BookingDates("2025-03-04","2025-03-07");
        Booking booking = new Booking("Fleetwood","Mac",1881,true, bookingDates,"Temiz havlu Olsun Lütfen");

        bookingResponse = createBooking(booking);


        Response response = given(spec)
                .when()
                .get("/booking/" + bookingResponse.getBookingid());

        response
                .then()
                .statusCode(200);


        Assertions.assertEquals(bookingResponse.getBooking().getFirstname(),response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals(bookingResponse.getBooking().getLastname(),response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(bookingResponse.getBooking().getTotalprice(),(Integer) response.jsonPath().getJsonObject("totalprice"));
        Assertions.assertTrue((Boolean) response.jsonPath().getJsonObject("depositpaid"));
        Assertions.assertEquals(bookingResponse.getBooking().getAdditionalneeds(),response.jsonPath().getJsonObject("additionalneeds"));


    }
}
