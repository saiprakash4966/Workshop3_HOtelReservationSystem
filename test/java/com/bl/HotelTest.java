package com.bl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HotelTest
{

    HotelMain hotelMain = null;

    @Before
    public void init() {
        hotelMain = new HotelMain();
    }

    @Test
    public void givenHotelDetails_ShouldReturnTrue() {
        boolean result = hotelMain.addHotel(new Hotel("TestHotel", 2, 160, 120));
        Assert.assertEquals(true, result);
    }

    @Test
    public void givenDate_whenWeekDay_ShouldReturnFalse() {
        boolean result = hotelMain.isWeekend("18mar2022");
        Assert.assertEquals(result, false);
    }

    @Test
    public void givenDate_whenWeekDay_ShouldReturnTrue() {
        boolean result = hotelMain.isWeekend("19mar2022");
        Assert.assertEquals(result, true);
    }
}