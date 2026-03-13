import java.util.List;

public class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report");

        List<Reservation> reservations = history.getConfirmedReservations();

        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.getGuestName() +
                    ", Room Type: " + r.getRoomType());
        }
    }
}