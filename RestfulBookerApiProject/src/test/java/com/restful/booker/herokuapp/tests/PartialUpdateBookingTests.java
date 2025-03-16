package com.restful.booker.herokuapp.tests;

import com.restful.booker.herokuapp.models.Booking;
import com.restful.booker.herokuapp.models.BookingDates;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PartialUpdateBookingTests extends BaseTest{

    Response response;

    @Test
    public void partialUpdateBookingTest() {

        JSONObject bookingPartialUpdate = new JSONObject();
        bookingPartialUpdate.put("firstname", "Red Hot");
        bookingPartialUpdate.put("lastname", "Chili Peppers");

        BookingDates bookingDates = new BookingDates("2025-03-04","2025-03-07");
        Booking booking = new Booking("Led","Zeppelin",1881,true, bookingDates,"Temiz havlu Olsun Lütfen");

        //Booking bookingPartialUpdate = new Booking("Red Hot","Chili Peppers",1881,false, bookingDates,"Temiz havlu Olsun Lütfen");

        response = partialUpdateBooking(createToken(),bookingPartialUpdate.toString(),createBooking(booking).getBookingid());

        Assertions.assertEquals("Red Hot",response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Chili Peppers",response.jsonPath().getJsonObject("lastname"));




    }

}
