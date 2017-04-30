package pt.ulisboa.tecnico.softeng.hotel.services.local.dataobjects;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.softeng.hotel.domain.Booking;
import pt.ulisboa.tecnico.softeng.hotel.domain.Hotel;
import pt.ulisboa.tecnico.softeng.hotel.domain.Room;

public class RoomData {
	public static enum CopyDepth {
		SHALLOW, BOOKINGS
	};
	private Hotel hotel;
	private String number;
	private String type;
	private List<RoomBookingData> bookings = new ArrayList<RoomBookingData>();
	public RoomData() {
	}

	public RoomData(Hotel hotel, Room room, CopyDepth depth) {
		this.setHotel(hotel);
		this.setNumber(room.getNumber());
		this.setType(room.getType().toString());
		
		
		switch (depth) {
		case BOOKINGS:
			for (Booking booking : room.getBookingSet()) {
				this.bookings.add(new RoomBookingData(room, booking));
			}
			break;
		case SHALLOW:
			break;
		default:
			break;
		}
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<RoomBookingData> getBookings() {
		return this.bookings;
	}
	
	public void setBookings(List<RoomBookingData> bookings) {
		this.bookings = bookings;
	}
}
