package com.bl;
import java.util.HashMap;
import java.util.Map;

  enum Daytype 
{
    WEEKDAY,
    WEEKEND
}

/**
 * Creating a Hotel Class which contains Hotal name and rating as instances
 */

public class Hotel
{
    private int rating;
    private String hotelName;
    private Map<Daytype, Integer> regularCustomerRatesMap = new HashMap<>();

    /**
     * Creating parameterized constructor
     * @param hotelName - name of the hotel
     * @param rating - hotel rating
     */
    public Hotel(String hotelName, int rating) {
        this.rating = rating;
        this.hotelName = hotelName;
    }

    /**
     * Creating getter and setter methods
     */
    public void setRegularCustomerRatesMap(Map<Daytype, Integer> regularCustomerRatesMap) {
        this.regularCustomerRatesMap = regularCustomerRatesMap;
    }

    public Map<Daytype, Integer> getRegularCustomerRatesMap() {
        return regularCustomerRatesMap;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * Overriding toString method for printing the list in a format
     */
    @Override
    public String toString() {
        return "Hotel{" +
                "rating=" + rating +
                ", hotelName='" + hotelName + '\'' +
                ", regularCustomerRatesMap=" + regularCustomerRatesMap +
                '}';
    }
}