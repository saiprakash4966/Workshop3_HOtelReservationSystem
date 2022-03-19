package com.bl;

import java.util.ArrayList;
import java.util.List;

public class HotelMain 
{

    /**
     * Creating an ArrayList for storing list of hotels and its information
     */
    public static List<Hotel> hotelList = new ArrayList<>();

    /**
     * Main method to perform modification on Hotel Reservation System
     * @param args - default java @param
     */
    public static void main(String[] args) 
    {
        /**
         * Printing a welcome message
         */
        System.out.println("Welcome to the Hotel Reservation System");
        /**
         * Calling the createHotels and printHotelInformation methods
         */
        createHotels();
        printHotelInformation();
    }

    /**
     * Creating createHotels to create 3 hotel objects
     */
    public static void createHotels() {
        /**
         * Creating Lakewood hotel object with rating 3
         */
        Hotel lakewoodHotel =  new Hotel("Lakewood", 3);
        lakewoodHotel.getRegularCustomerRatesMap().put(Daytype.WEEKDAY, 110);
        lakewoodHotel.getRegularCustomerRatesMap().put(Daytype.WEEKEND, 90);
        hotelList.add(lakewoodHotel);
        /**
         * Creating bridge wood Hotel object with rating 4
         */
        Hotel bridgewoodHotel =  new Hotel("Bridgewood", 4);
        bridgewoodHotel.getRegularCustomerRatesMap().put(Daytype.WEEKDAY, 160);
        bridgewoodHotel.getRegularCustomerRatesMap().put(Daytype.WEEKEND, 60);
        hotelList.add(bridgewoodHotel);
        /**
         * Creating Ridge wood hotel object with rating 5
         */
        Hotel ridgewoodHotel =  new Hotel("Ridgewood", 5);
        ridgewoodHotel.getRegularCustomerRatesMap().put(Daytype.WEEKDAY, 220);
        ridgewoodHotel.getRegularCustomerRatesMap().put(Daytype.WEEKEND, 150);
        hotelList.add(ridgewoodHotel);
    }

    /**
     * Creating printHotelInformation to print the Hotel information list
     */
    public static void printHotelInformation()
    {
        hotelList.stream().forEach(System.out::println);
    }
}
