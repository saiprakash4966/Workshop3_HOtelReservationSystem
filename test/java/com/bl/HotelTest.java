package com.bl;
import com.bl.HotelMain;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HotelTest
{

    HotelMain hotelMain= null;

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

    @Test
    public void givenDates_whenWeekDayAndWeekEnd_ShouldReturnLakeWoodAndBridgewood() {
        hotelMain.hotelList.add(new Hotel("Lakewood", 3, 110.00, 90.00));
        hotelMain.hotelList.add(new Hotel("Bridgewood", 4, 150.00, 50.00));
        List<Map.Entry<String, Double>> result = hotelMain.printCheapestHotel("11Sep2020,12Sep2020");
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0).getKey(), "Bridgewood");
        Assert.assertEquals(result.get(1).getKey(), "Lakewood");
    }

    @Test
    public void givenHotelAndRating_whenValidHotelName_ShouldReturnTrue() {
        hotelMain.hotelList.add(new Hotel("Lakewood", 3, 110.00, 90.00));
        hotelMain.hotelList.add(new Hotel("Bridgewood", 4, 150.00, 50.00));
        boolean isUpdated = hotelMain.addRating("Lakewood", 4);
        Assert.assertEquals(isUpdated, true);
    }

    @Test
    public void givenHotelAndRating_whenInValidHotelName_ShouldReturnFalse() {
        hotelMain.hotelList.add(new Hotel("Lakewood", 3, 110.00, 90.00));
        hotelMain.hotelList.add(new Hotel("Bridgewood", 4, 150.00, 50.00));
        boolean isUpdated = hotelMain.addRating("UnKnownHotel", 4);
        Assert.assertEquals(isUpdated, false);
    }

    @Test
    public void givenDates_whenWeekDayAndWeekEnd_ShouldReturnBestRatedAsBridgewood() {
        hotelMain.hotelList.add(new Hotel("Lakewood", 3, 110.00, 90.00));
        hotelMain.hotelList.add(new Hotel("Bridgewood", 4, 150.00, 50.00));
        Hotel cheapestBestRatedHotel = hotelMain.findCheapestBestRatedHotel("11Sep2020,12Sep2020");
        Assert.assertEquals(cheapestBestRatedHotel.getHotelName(), "Bridgewood");
    }

    @Test
    public void whenFindingBestRatedHotel_ShouldReturnRidgewood() {
        hotelMain.hotelList.add(new Hotel("Lakewood", 3, 110.00, 90.00));
        hotelMain.hotelList.add(new Hotel("Bridgewood", 4, 150.00, 50.00));
        hotelMain.hotelList.add(new Hotel("Ridgewood", 5, 220.00, 150.00));
        Hotel bestRatedHotel = hotelMain.findBestRatedHotel();
        Assert.assertEquals(bestRatedHotel.getHotelName(), "Ridgewood");
    }
}