public class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue bookingQueue;


    private RoomInventory inventory;

    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService) {

        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {

        while (true) {

            Reservation reservation;

            synchronized (bookingQueue) {

                if (bookingQueue.isEmpty()) {
                    break;
                }

                reservation = bookingQueue.getNextRequest();
            }


            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}
```
