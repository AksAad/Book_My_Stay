import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Reservation Request -> Guest: " + guestName + ", Room Type: " + roomType;
    }
}


class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }


    public void addBookingRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request received: " + reservation);
    }

    public void showPendingRequests() {
        System.out.println("\nCurrent Booking Request Queue:");
        if (requestQueue.isEmpty()) {
            System.out.println("No pending booking requests.");
            return;
        }

        for (Reservation r : requestQueue) {
            System.out.println(r);
        }
    }
}

public class book_my_stay {
    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Deluxe");
        Reservation r2 = new Reservation("Bob", "Standard");
        Reservation r3 = new Reservation("Charlie", "Suite");


        bookingQueue.addBookingRequest(r1);
        bookingQueue.addBookingRequest(r2);
        bookingQueue.addBookingRequest(r3);


        bookingQueue.showPendingRequests();
    }
}