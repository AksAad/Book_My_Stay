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
            String reservationId = "RES1001";

            AddOnServiceManager manager = new AddOnServiceManager();

            AddOnService breakfast = new AddOnService("Breakfast", 25.0);
            AddOnService spa = new AddOnService("Spa", 60.0);
            AddOnService airportPickup = new AddOnService("Airport Pickup", 40.0);

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


            CancellationService cancellationService = new CancellationService();

            cancellationService.registerBooking(reservationId, roomType);

            System.out.println("\nBooking Cancellation");

            cancellationService.cancelBooking(reservationId, inventory);

            cancellationService.showRollbackHistory();

            System.out.println("\nUpdated " + roomType + " Room Availability: " + inventory.getAvailableRooms(roomType));

            System.out.println("\nConcurrent Booking Simulation");

            BookingRequestQueue bookingQueue = new BookingRequestQueue();
            RoomAllocationService allocationService = new RoomAllocationService();

            bookingQueue.addRequest(new Reservation("Abhi", "Single"));
            bookingQueue.addRequest(new Reservation("Vanmathi", "Double"));
            bookingQueue.addRequest(new Reservation("Kumar", "Suite"));
            bookingQueue.addRequest(new Reservation("Subha", "Single"));

            Thread t1 = new Thread(
                    new ConcurrentBookingProcessor(
                            bookingQueue, inventory, allocationService));

            Thread t2 = new Thread(
                    new ConcurrentBookingProcessor(
                            bookingQueue, inventory, allocationService));

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            }
            catch (InterruptedException e) {
                System.out.println("Thread execution interrupted.");
            }

            System.out.println("\nRemaining Inventory:");
            System.out.println("Single: " + inventory.getAvailableRooms("Single"));
            System.out.println("Double: " + inventory.getAvailableRooms("Double"));
            System.out.println("Suite: " + inventory.getAvailableRooms("Suite"));

        }
        catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        }
        finally {
            scanner.close();
        }
    }
}

