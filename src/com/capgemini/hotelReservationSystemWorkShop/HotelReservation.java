package com.capgemini.hotelReservationSystemWorkShop;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelReservation {

	public static List<Hotel> hotelList = new ArrayList<Hotel>();

	public static void addRateAndHotelName(String hotelName, int rating, int weeklyRate, int weekEndRate,
			int weeklyRateReward, int weekEndRateReward) {
		Hotel hotel = new Hotel(hotelName, rating, weeklyRate, weekEndRate, weeklyRateReward, weekEndRateReward);
		hotelList.add(hotel);
	}

	public static void findCheapestHotel(String startDate, String endDate) {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);

		LocalDate tempStart = start;
		LocalDate tempEnd = end;

		List<Hotel> cheapestHotelList = new ArrayList<>();
		int minRate = 1000000000;

		for (Hotel hotel : hotelList) {
			start = tempStart;
			end = tempEnd.plusDays(1);
			int hotelRent = 0;
			while (!(start.equals(end))) {

				int day = start.getDayOfWeek().getValue();

				if (day == 6 || day == 7)
					hotelRent = hotelRent + hotel.getWeekEndRate();

				else
					hotelRent = hotelRent + hotel.getWeeklyRate();

				start = start.plusDays(1);

			}
			if (hotelRent <= minRate) {
				minRate = hotelRent;
				if (hotelRent < minRate) {
					if (cheapestHotelList.size() == 0)
						cheapestHotelList.add(hotel);

					else {
						cheapestHotelList.clear();
						cheapestHotelList.add(hotel);
					}

				} else
					cheapestHotelList.add(hotel);
			}

		}
		System.out.println("The Cheapest option is");
		for (Hotel hotel : cheapestHotelList) {
			System.out.println(hotel.getHotelName() + ", total rent :- " + minRate);

		}

	}

	public static void findBestRatedHotel(String startDate, String endDate) {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);

		LocalDate tempStart = start;
		LocalDate tempEnd = end;

		Hotel cheapestHotel = null;
		int minRate = 1000000000;
		int bestRating = 0;

		for (Hotel hotel : hotelList) {
			start = tempStart;
			end = tempEnd.plusDays(1);
			int hotelRent = 0;
			while (!(start.equals(end))) {

				int day = start.getDayOfWeek().getValue();

				if (day == 6 || day == 7)
					hotelRent = hotelRent + hotel.getWeekEndRate();

				else
					hotelRent = hotelRent + hotel.getWeeklyRate();

				start = start.plusDays(1);

			}
			if (hotel.getRating() > bestRating) {
				bestRating = hotel.getRating();
				minRate = hotelRent;
				cheapestHotel = hotel;
			}

		}
		System.out.println("The Best Rated option is");
		System.out.println(cheapestHotel.getHotelName() + ", rating :- " + cheapestHotel.getRating()
				+ ", total rent :- " + minRate);

	}

	public static long calculateRegularCost(Hotel h, LocalDate start, LocalDate end) {

		long totalCost = 0;
		end = end.plusDays(1);
		while (!(start.equals(end))) {

			int day = start.getDayOfWeek().getValue();

			if (day == 6 || day == 7)
				totalCost = totalCost + h.getWeekEndRate();

			else
				totalCost = totalCost + h.getWeeklyRate();

			start = start.plusDays(1);

		}
		return totalCost;
	}

	public static void findCheapestBestRatedHotel(String startDate, String endDate) {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		String cheapestHotel = "";
		int bestRating = 0;
		long minRate = hotelList.stream().map(h -> calculateRegularCost(h, start, end)).min(Long::compare).get();
		List<Hotel> min_rate_hotels = hotelList.stream().filter(h -> calculateRegularCost(h, start, end) == minRate)
				.collect(Collectors.toList());

		Hotel max_rated_hotel = min_rate_hotels.stream().max((h1, h2) -> h1.getRating() - h2.getRating()).get();
		cheapestHotel = max_rated_hotel.getHotelName();
		bestRating = max_rated_hotel.getRating();
		System.out.println(
				"Cheapest hotel is : " + cheapestHotel + " ,total rate is: " + minRate + " ,Rating: " + bestRating);
	}

	public static long calculateRewardCost(Hotel h, LocalDate start, LocalDate end) {

		long totalCost = 0;
		end = end.plusDays(1);
		while (!(start.equals(end))) {

			int day = start.getDayOfWeek().getValue();

			if (day == 6 || day == 7)
				totalCost = totalCost + h.getWeekEndRateReward();

			else
				totalCost = totalCost + h.getWeeklyRateReward();

			start = start.plusDays(1);

		}
		return totalCost;
	}

	public static void findCheapestBestRatedHotelWithRewardRates(String sDateRewardrate, String eDateRewardrate) {
		LocalDate start = LocalDate.parse(sDateRewardrate);
		LocalDate end = LocalDate.parse(eDateRewardrate);
		String cheapestHotel = "";
		int bestRating = 0;
		long minRate = hotelList.stream().map(h -> calculateRewardCost(h, start, end)).min(Long::compare).get();
		List<Hotel> min_rate_hotels = hotelList.stream().filter(h -> calculateRewardCost(h, start, end) == minRate)
				.collect(Collectors.toList());

		Hotel max_rated_hotel = min_rate_hotels.stream().max((h1, h2) -> h1.getRating() - h2.getRating()).get();
		cheapestHotel = max_rated_hotel.getHotelName();
		bestRating = max_rated_hotel.getRating();
		System.out.println(
				"Cheapest hotel is : " + cheapestHotel + " ,total rate is: " + minRate + " ,Rating: " + bestRating);
	}

}
