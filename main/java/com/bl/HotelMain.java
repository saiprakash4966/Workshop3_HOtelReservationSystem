package com.bl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class HotelMain 
{

    /**
     * Creating an ArrayList for storing list of hotels and its information
     */
    public List<Hotel> hotelList = new ArrayList<>();
    /**
     * Creating scanner class to take input from user
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Main method to perform modification on HotelMain
     * @param args - default java param
     */
    public static void main(String[] args) 
    {
        /**
         * Printing a welcome message
         */
        System.out.println("Welcome to the Hotel Reservation System");

        HotelMain reservationApplication = new HotelMain();
        String userChoice;
        do {
            reservationApplication.readUserInput(scanner);
            System.out.println("Do you want to continue(Y/N) ?");
            userChoice = scanner.next();
        } while (userChoice.equalsIgnoreCase("Y"));
        System.out.println("Thank you!");
    }

    /**
     * Creating readHotelDetails to add all the details of the hotel into list which are given by user
     */
    public void readHotelDetails() {
        System.out.println("Please enter the Hotel Name: ");
        String hotelName = scanner.next();

        System.out.println("Please enter the rating: ");
        int rating = scanner.nextInt();

        System.out.println("Please enter the weekday rate for regular customer: ");
        double weekDayRate = scanner.nextDouble();

        System.out.println("Please enter the weekend rate for regular customer: ");
        double weekEndRate = scanner.nextDouble();
        /**
         * Creating a Hotel object and adding all the details into hotelList
         */
        this.addHotel(new Hotel(hotelName, rating, weekDayRate, weekEndRate));
    }

    public boolean addHotel(Hotel hotel) {
        return this.hotelList.add(hotel);
    }

    /**
     * Creating readUserInput to read input from the user and continue according to it
     * @param scanner - taking scanner as input
     */
    public void readUserInput(Scanner scanner) {
        System.out.println("Please select one option: ");
        System.out.println("1. Add Hotel Details\n2. Print Hotel Information\n3. Print Cheapest Hotel");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                readHotelDetails();
                break;
            case 2:
                printHotelInformation();
                break;
            case 3:
                printCheapestHotel();
                break;
            default:
                System.out.println("Invalid option. Please select valid");
        }
    }

    /**
     * Creating printHotelInformation to print the Hotel information list
     */
    public void printHotelInformation() {
        hotelList.stream().forEach(System.out::println);
    }

    /**
     * Creating printCheapestHotel to print the cheapest hotel for the given date
     */
    public void printCheapestHotel() {
        System.out.println("Enter date range");
        String dateRange = scanner.next();
        double cheapHotelCost = 0.0;
        Hotel cheapestHotel = null;
        /**
         * Using the split method to split the given date range by , deliminator
         */
        for(String givenDate : dateRange.split(",")) {
            if (this.isWeekend(givenDate)) {
                //weekend
                cheapestHotel = this.findCheapestHotel(true);
                cheapHotelCost += cheapestHotel.getRegularWeekEndRate();
            } else {
                cheapestHotel = this.findCheapestHotel(false);
                cheapHotelCost += cheapestHotel.getRegularWeekDayRate();
            }
        }
        if (cheapestHotel != null) {
            System.out.println("Cheapest hotel: " + cheapestHotel.getHotelName() + " and Total Cost: $" + cheapHotelCost);
        } else {
            System.out.println("Cheapest hotel not found!");
        }
    }

    /**
     * Creating a isWeekend method to find weather the given date fall in weekend or weekday
     * @param date - takes the date given by user
     * @return - true if the date falls in weekend otherwise false
     */
    public boolean isWeekend(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy");
        int dayOfWeek1 = -1;
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(formatter.parse(date));
            dayOfWeek1 = c.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfWeek1 == Calendar.SUNDAY || dayOfWeek1 == Calendar.SATURDAY;
    }

    /**
     * Creating a findCheapestHotel to find the cheapest hotel by using stream method and comparator
     * @return - the cheapest hotel
     */
    public Hotel findCheapestHotel(boolean isWeekend) {
        return this.hotelList.stream().min(Comparator.comparingDouble(isWeekend ? Hotel::getRegularWeekEndRate : Hotel::getRegularWeekDayRate)).get();
    }
}