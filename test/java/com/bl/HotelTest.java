package com.bl;

import org.junit.Assert;
import org.junit.Test;

public class HotelTest
{

    @Test
    public void whenHotelCreation_ShouldReturn3()
    {
        HotelMain.createHotels();
        int result = HotelMain.hotelList.size();
        Assert.assertEquals(3, result);
    }

}