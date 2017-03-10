package pt.ulisboa.tecnico.softeng.activity.domain;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.activity.domain.exception.ActivityException;

public class BookingContructorMethodTest {
	private ActivityProvider provider;
	private ActivityOffer offer;

	@Before
	public void setUp() {
		this.provider = new ActivityProvider("XtremX", "ExtremeAdventure");
		Activity activity = new Activity(this.provider, "Bush Walking", 18, 80, 25);

		LocalDate begin = new LocalDate(2016, 12, 19);
		LocalDate end = new LocalDate(2016, 12, 21);
		this.offer = new ActivityOffer(activity, begin, end);
	}

	@Test
	public void success() {
		Booking booking = new Booking(this.provider, this.offer);

		Assert.assertTrue(booking.getReference().startsWith(this.provider.getCode()));
		Assert.assertTrue(booking.getReference().length() > ActivityProvider.CODE_SIZE);
		Assert.assertEquals(1, this.offer.getNumberOfBookings());
	}
	
	@Test
	public void numberOfReservationsTest() {
		
		for(int i = 0; i < 25; i++)
			new Booking(this.provider, this.offer);
		
		try {
			new Booking(this.provider, this.offer);
			Assert.fail();
		}
		catch(ActivityException e) {
			Assert.assertEquals(25, this.offer.getNumberOfBookings());
		}
	}
	
	@Test
	public void limitNoOfReservationsTest() {
		
		for(int i = 0; i < 25; i++)
			new Booking(this.provider, this.offer);
		
		Assert.assertEquals(25, this.offer.getNumberOfBookings());
	}
	
	@Test
	public void nullOfferTest() {
		try {
			new Booking(this.provider, null);
			Assert.fail();
		}
		catch(ActivityException e) {}
	}
	
	@Test
	public void nullProviderTest() {
		try{
			new Booking(null, this.offer);
			Assert.fail();
		}
		catch(ActivityException e) {
			Assert.assertEquals(0, this.offer.getNumberOfBookings());
		}
	}

	@After
	public void tearDown() {
		ActivityProvider.providers.clear();
	}

}
