package com.restful.booker.herokuapp.tests;

import com.restful.booker.herokuapp.models.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateBookingTests extends BaseTest{

    Response response;

    @Test
    public void updateBookingTest(){



        BookingDates bookingDates = new BookingDates("2025-03-04","2025-03-07");
        Booking booking = new Booking("Led","Zeppelin",1881,true, bookingDates,"Temiz havlu Olsun Lütfen");

        Booking bookingUpdate = new Booking("Black","Sabbath",1881,false, bookingDates,"Temiz havlu Olsun Lütfen");

        response = updateBooking(createToken(),bookingUpdate, createBooking(booking).getBookingid());



        Assertions.assertEquals("Black", response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Sabbath", response.jsonPath().getJsonObject("lastname"));
        Assertions.assertEquals(1881, (Integer) response.jsonPath().getJsonObject("totalprice"));

        Assertions.assertEquals(false, response.jsonPath().getJsonObject("depositpaid"));

    }
}
