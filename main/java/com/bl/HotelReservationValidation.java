package com.bl;

public class HotelReservationValidation 
{
	

	    public static boolean isValidHotelName(String hotelName) {
	        /**
	         * Regular expression for Hotel name validation
	         */
	        String hotelNameRegex = "^[A-Z]{1}[a-z]{2,}$";
	        return hotelName.matches(hotelNameRegex);
	    }

	    public static boolean isValidHotelRating(int rating) {
	        /**
	         * Regular expression for Hotel rating validation
	         */
	        String hotelRatingRegex = "^\\d{1}$";
	        return String.valueOf(rating).matches(hotelRatingRegex);
	    }

	    public static boolean isValidWeekDayRate(double weekDayRate) {
	        /**
	         * Regular expression for Hotel WeekDayRate validation
	         */
	        String weekDayRateRegex = "^[1-9]\\d*(\\.\\d+)?$";
	        return String.valueOf(weekDayRate).matches(weekDayRateRegex);
	    }

	    public static boolean isValidWeekEndRate(double weekEndRate) {
	        /**
	         * Regular expression for Hotel WeekEndRate validation
	         */
	        String weekDayRateRegex = "^[1-9]\\d*(\\.\\d+)?$";
	        return String.valueOf(weekEndRate).matches(weekDayRateRegex);
	    }
	}


