import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

public class CancellationService {

    /* Stack that stores recently released room IDs */
    private Stack<String> releasedRoomIds;

    /* Maps reservation ID to room type */
    private Map<String, String> reservationToRoomTypeMap;

    /* Initializes cancellation tracking structures */
    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationToRoomTypeMap = new HashMap<>();
    }

    /**
     * Registers a confirmed booking.
     */
    public void registerBooking(String reservationId, String roomType) {
        reservationToRoomTypeMap.put(reservationId, roomType);
    }

    /**
     * Cancels a confirmed booking and restores inventory.
     */
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationToRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Cancellation failed: reservation not found.");
            return;
        }

        String roomType = reservationToRoomTypeMap.remove(reservationId);

        // restore inventory
        inventory.releaseRoom(roomType);

        // record rollback
        releasedRoomIds.push(reservationId + ":" + roomType);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    /**
     * Displays recently cancelled reservations.
     */
    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        while (!releasedRoomIds.isEmpty()) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.pop());
        }
    }
}