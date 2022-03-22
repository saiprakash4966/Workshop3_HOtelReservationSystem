package com.bl;
import com.bl.HotelMain;

import java.util.List;
import java.util.Map;

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

    @Test
    public void givenDates_whenWeekDayAndWeekEnd_ShouldReturnBestRatedAsBridgewood1() {
        hotelMain.hotelList.add(new Hotel("Lakewood", 3, 110.00, 90.00));
        hotelMain.hotelList.add(new Hotel("Bridgewood", 4, 150.00, 50.00));
        Hotel cheapestBestRatedHotel = hotelMain.findCheapestBestRatedHotel("11Sep2020,12Sep2020", true);
        Assert.assertEquals(cheapestBestRatedHotel.getHotelName(), "Bridgewood");
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
        Hotel cheapestBestRatedHotel = hotelMain.findCheapestBestRatedHotel("11Sep2020,12Sep2020", false);
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

    @Test
    public void givenHotel_whenHotelIsLakewood_ShouldReturnTrue() {
        hotelMain.hotelList.add(new Hotel("Lakewood", 3, 110.00, 90.00));
        hotelMain.hotelList.add(new Hotel("Bridgewood", 4, 150.00, 50.00));
        hotelMain.hotelList.add(new Hotel("Ridgewood", 5, 220.00, 150.00));
        boolean isSuccess = hotelMain.addRewardRates("Lakewood", 80.00, 80.00);
        Assert.assertEquals(isSuccess, true);
    }

    @Test
    public void givenHotel_whenHotelIsUnknown_ShouldReturnFalse() {
        hotelMain.hotelList.add(new Hotel("Lakewood", 3, 110.00, 90.00));
        hotelMain.hotelList.add(new Hotel("Bridgewood", 4, 150.00, 50.00));
        hotelMain.hotelList.add(new Hotel("Ridgewood", 5, 220.00, 150.00));
        boolean isSuccess = hotelMain.addRewardRates("ABC", 80.00, 80.00);
        Assert.assertEquals(isSuccess, false);
    }
    @Test
    public void givenDates_whenRewardedCustomerAndWeekDayAndWeekEnd_ShouldReturnRidgewood() {
        hotelMain.hotelList.add(new Hotel("Lakewood", 3, 110.00, 90.00));
        hotelMain.hotelList.add(new Hotel("Bridgewood", 4, 150.00, 50.00));
        hotelMain.hotelList.add(new Hotel("Ridgewood", 5, 220.00, 150.00));
        hotelMain.addRewardRates("Lakewood", 80.00, 80.00);
        hotelMain.addRewardRates("Bridgewood", 110.00, 50.00);
        hotelMain.addRewardRates("Ridgewood", 100.00, 40.00);
        Hotel cheapestBestRatedHotel = hotelMain.findCheapestBestRatedHotel("11Sep2020,12Sep2020", false);
        Assert.assertEquals(cheapestBestRatedHotel.getHotelName(), "Ridgewood");
    }
}