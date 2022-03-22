package com.bl;
import java.util.HashMap;
import java.util.Map;

 

/**
 Creating a Hotel Class which contains Hotel name, rating, regularWeekDayRates, regularWeekEndRates
 * as instance variables
 */

public class Hotel
{
    private int rating;
    private String hotelName;
    private double regularWeekDayRate;
    private double regularWeekEndRate;
    private double rewardWeekDayRate;
    private double rewardWeekEndRate;

    /**
     * Creating parameterised constructor
     * @param hotelName - name of the hotel
     * @param rating - hotel rating
     * @param regularWeekDayRate - rate of regular week day
     * @param regularWeekEndRate - rate of regular week end
     */
    public Hotel(String hotelName, int rating, double regularWeekDayRate, double regularWeekEndRate) {
        this.rating = rating;
        this.hotelName = hotelName;
        this.regularWeekDayRate = regularWeekDayRate;
        this.regularWeekEndRate = regularWeekEndRate;
    }

    /**
     * Creating getter and setter methods for all the instances of hotel class
     */

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

    public double getRegularWeekDayRate() {
        return regularWeekDayRate;
    }

    public void setRegularWeekDayRate(double regularWeekDayRate) {
        this.regularWeekDayRate = regularWeekDayRate;
    }

    public double getRegularWeekEndRate() {
        return regularWeekEndRate;
    }

    public void setRegularWeekEndRate(double regularWeekEndRate) {
        this.regularWeekEndRate = regularWeekEndRate;
    }

    public double getRewardWeekDayRate() {
        return rewardWeekDayRate;
    }

    public void setRewardWeekDayRate(double rewardWeekDayRate) {
        this.rewardWeekDayRate = rewardWeekDayRate;
    }

    public double getRewardWeekEndRate() {
        return rewardWeekEndRate;
    }

    public void setRewardWeekEndRate(double rewardWeekEndRate) {
        this.rewardWeekEndRate = rewardWeekEndRate;
    }

    /**
     * Overriding toString method for printing the list in a format
     */
    @Override
    public String toString() {
        return "Hotel{" +
                "hotelName=" + hotelName +
                ", rating='" + rating + '\'' +
                ", regularWeekDayRate=$" + regularWeekDayRate +
                ", regularWeekEndRate=$" + regularWeekEndRate +
                '}';
    }
}