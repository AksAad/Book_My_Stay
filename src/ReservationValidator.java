public class ReservationValidator {
    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!roomType.equalsIgnoreCase("Single")
                && !roomType.equalsIgnoreCase("Double")
                && !roomType.equalsIgnoreCase("Suite")) {

            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (!inventory.isRoomAvailable(roomType)) {
            throw new InvalidBookingException("Requested room type is not available.");
        }
    }
}