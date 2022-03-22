package com.bl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
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
        System.out.println("1. Add Hotel Details\n2. Print Hotel Information\n3. Print Cheapest Hotel\n4. Add Rating to Hotel\n5. Print Cheapest Best Rated Hotel\n6. Print Best Rated Hotel" +
                "\n7. Add Reward Rates to a Hotel\n8. Print Cheapest Best Rated Hotel For Rewarded Customer");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                readHotelDetails();
                break;
            case 2:
                printHotelInformation();
                break;
            case 3:
                readDatesAndPrintCheapestHotels(true);
                break;
            case 4:
                addRatingByTakingInputFromUser();
                break;
            case 5:
                readDatesAndPrintCheapestBestRatedHotels(true);
                break;
            case 6:
                findBestRatedHotel();
                break;
            case 7:
                readUserInputAndAddRewardRates();
                break;
            case 8:
                readDatesAndPrintCheapestBestRatedHotels(false);
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

    public void readDatesAndPrintCheapestHotels(boolean isRegularCustomer) {
        String dateRange = readDates();
        printCheapestHotel(dateRange, isRegularCustomer);
    }

    /**
     * Creating printCheapestHotel to print the cheapest hotel for the given date
     */
    public List<Map.Entry<String, Double>> printCheapestHotel(String dateRange, boolean isRegularCustomer) {
        /**
         * create boolean list using dates which contains true if it is weekend, false otherwise
         */
        List<Boolean> dayTypeList = Arrays.stream(dateRange.split(",")).map(date -> isWeekend(date)).collect(Collectors.toList());

        /**
         * create a map using streams from available hotel list mapping hotel name as key and the total cost for given days as value
         */
        Map<String, Double> hotelMap = this.hotelList.stream().collect(Collectors.toMap(hotel -> hotel.getHotelName(), hotel -> dayTypeList.stream().map(dayType -> dayType ? (isRegularCustomer ? hotel.getRegularWeekEndRate() : hotel.getRewardWeekEndRate()) : (isRegularCustomer ? hotel.getRegularWeekDayRate() : hotel.getRewardWeekDayRate())).reduce(Double::sum).get()));

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
     * This method will take input from user and update the hotel rating
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

    /**
     * Read date range from user
     * @return date range
     */
    public String readDates() {
        System.out.println("Enter date range");
        return scanner.next();
    }

    /**
     * This method reads date range from user and prints Cheapest best rated hotel
     */
    public void readDatesAndPrintCheapestBestRatedHotels(boolean isRegularCustomer) {
        String dateRange = readDates();
        findCheapestBestRatedHotel(dateRange, isRegularCustomer);
    }

    /**
     * This method will take daterange and prints cheapest best rated hotel
     * @param dateRange
     * @return cheapest best rated hotels
     */
    public Hotel findCheapestBestRatedHotel(String dateRange, boolean isRegularCustomer) {
        List<Map.Entry<String, Double>> cheapestHotels = printCheapestHotel(dateRange, isRegularCustomer);
        Double cost = cheapestHotels.isEmpty() ? 0.0 : cheapestHotels.get(0).getValue();
        Set<String> cheapestHotelNames = cheapestHotels.stream().map(entry -> entry.getKey()).collect(Collectors.toSet());
        Hotel cheapestBestRatedHotel = this.hotelList.stream().filter(hotel -> cheapestHotelNames.contains(hotel.getHotelName())).max(Comparator.comparingInt(Hotel::getRating)).get();
        System.out.println("Cheapest Best Rated hotel: "+cheapestBestRatedHotel.getHotelName()+", Total cost: $"+cost);
        return cheapestBestRatedHotel;
    }

    /**
     * This method is created to find the best rated hotel from given no of hotels
     * @return
     */
    public Hotel findBestRatedHotel() {
        Hotel bestRatedHotel = this.hotelList.stream().max(Comparator.comparingInt(Hotel::getRating)).get();
        System.out.println("Best Rated hotel: "+bestRatedHotel.getHotelName()+", cost: $"+bestRatedHotel.getRegularWeekDayRate() + bestRatedHotel.getRegularWeekEndRate());
        return bestRatedHotel;
    }

    /**
     * This method is to read hotel name and reward rates from user and update the same
     */
    public void readUserInputAndAddRewardRates() {
        System.out.println("Enter hotel name");
        String hotelName = scanner.next();
        System.out.println("Enter Weekend Rate for reward customer");
        double weekendRate = scanner.nextDouble();
        System.out.println("Enter Weekday Rate for reward customer");
        double weekDayRate = scanner.nextDouble();
        addRewardRates(hotelName, weekDayRate, weekendRate);
    }

    /**
     * This method takes hotel name and reward rates and updates
     * @param hotelName
     * @param weekDayRate
     * @param weekendRate
     * @return true if the add is success, false otherwise
     */
    public boolean addRewardRates(String hotelName, double weekDayRate, double weekendRate) {
        Optional<Hotel> giveHotel = this.hotelList.stream().filter(hotel -> hotel.getHotelName().equalsIgnoreCase(hotelName)).findFirst();
        if (!giveHotel.isPresent()) {
            return false;
        } else {
            giveHotel.get().setRewardWeekDayRate(weekDayRate);
            giveHotel.get().setRewardWeekEndRate(weekendRate);
            return true;
        }
    }

}