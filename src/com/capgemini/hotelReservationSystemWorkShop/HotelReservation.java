package com.capgemini.hotelReservationSystemWorkShop;

import java.util.ArrayList;
import java.util.List;

public class HotelReservation {

	public static List<Hotel> hotelList = new ArrayList<Hotel>();

	public static void addRegulaRateAndHotelName(String hotelName, int regularRate) {
		Hotel hotel = new Hotel(hotelName, regularRate);
		hotelList.add(hotel);
	}

}
