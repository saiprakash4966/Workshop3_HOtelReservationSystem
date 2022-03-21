package com.bl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    public static void main(String[] args) {
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
        System.out.println("1. Add Hotel Details\n2. Print Hotel Information\n3. Print Cheapest Hotel\n4. Add Rating to Hotel");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                readHotelDetails();
                break;
            case 2:
                printHotelInformation();
                break;
            case 3:
                readDatesAndPrintCheapestHotels();
                break;
            case 4:
                addRatingByTakingInputFromUser();
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

    public void readDatesAndPrintCheapestHotels() {
        System.out.println("Enter date range");
        String dateRange = scanner.next();
        printCheapestHotel(dateRange);
    }

    /**
     * Creating printCheapestHotel to print the cheapest hotel for the given date
     */
    public List<Map.Entry<String, Double>> printCheapestHotel(String dateRange) {
        /**
         * create boolean list using dates which contains true if it is weekend, false otherwise
         */
        List<Boolean> dayTypeList = Arrays.stream(dateRange.split(",")).map(date -> isWeekend(date)).collect(Collectors.toList());

        /**
         * create a map using streams from available hotel list mapping hotel name as key and the total cost for given days as value
         */
        Map<String, Double> hotelMap = this.hotelList.stream().collect(Collectors.toMap(hotel -> hotel.getHotelName(), hotel -> dayTypeList.stream().map(dayType -> dayType ? hotel.getRegularWeekEndRate() : hotel.getRegularWeekDayRate()).reduce(Double::sum).get()));

        /**
         * print the cheapest hotel by comparing the cost of each hotel to min spend
         */
        List<Map.Entry<String, Double>> cheaphotels = hotelMap.entrySet().stream().filter(entrySet -> entrySet.getValue().doubleValue() == hotelMap.values().stream().min(Comparator.comparingDouble(Double::doubleValue)).get().doubleValue()).collect(Collectors.toList());
        cheaphotels.stream().forEach(System.out::println);
        return cheaphotels;
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
     * This method willl take input from user and update the hotel rating
     */
    public void addRatingByTakingInputFromUser() {
        System.out.println("Enter hotel name");
        String hotelName = scanner.next();
        System.out.println("Enter rating");
        int rating = scanner.nextInt();
        addRating(hotelName, rating);
    }

    /**
     * This method will filter the hotels from hotel list based on given name and update its rating
     * @param hotelName
     * @param rating
     */
    public boolean addRating(String hotelName, int rating) {
        List<Hotel> hotels = this.hotelList.stream().filter(hotel -> hotel.getHotelName().equalsIgnoreCase(hotelName)).collect(Collectors.toList());
        if (hotels.isEmpty()) {
            return false;
        } else {
            hotels.stream().forEach(hotel -> hotel.setRating(rating));
            return true;
        }
    }
}