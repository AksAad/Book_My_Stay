import java.util.List;
import java.util.Scanner;

public class Book_My_Stay {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();

        try {

            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();


            validator.validate(guestName, roomType, inventory);


            Reservation reservation = new Reservation(guestName, roomType);


            AddOnServiceManager manager = new AddOnServiceManager();

            AddOnService breakfast = new AddOnService("Breakfast", 25.0);
            AddOnService spa = new AddOnService("Spa", 60.0);
            AddOnService airportPickup = new AddOnService("Airport Pickup", 40.0);

            String reservationId = "RES1001";

            manager.addService(reservationId, breakfast);
            manager.addService(reservationId, spa);
            manager.addService(reservationId, airportPickup);

            System.out.println("\nServices for Reservation: " + reservationId);

            List<AddOnService> services = manager.getServices(reservationId);
            for (AddOnService service : services) {
                System.out.println(service.getServiceName() + " - $" + service.getCost());
            }

            double totalCost = manager.calculateTotalServiceCost(reservationId);
            System.out.println("Total Add-On Cost: $" + totalCost);

            BookingHistory history = new BookingHistory();

            history.addReservation(reservation);
            history.addReservation(new Reservation("Subha", "Double"));
            history.addReservation(new Reservation("Vanmathi", "Suite"));

            BookingReportService reportService = new BookingReportService();
            System.out.println("\nBooking History and Reporting");
            reportService.generateReport(history);
        }
        catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
        finally {
            scanner.close();
        }
    }
}