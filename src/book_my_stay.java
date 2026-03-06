import java.util.HashMap;
import java.util.Map;


class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void registerRoomType(String roomType, int count) {
        inventory.put(roomType, count);
        System.out.println(roomType + " registered with " + count + " rooms.");
    }
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int change) {

        int current = inventory.getOrDefault(roomType, 0);
        int updated = current + change;

        if (updated < 0) {
            System.out.println("Cannot reduce rooms below zero for " + roomType);
            return;
        }

        inventory.put(roomType, updated);
        System.out.println("Updated availability for " + roomType + ": " + updated);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}

public class book_my_stay {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.registerRoomType("Standard", 10);
        inventory.registerRoomType("Deluxe", 5);
        inventory.registerRoomType("Suite", 3);

        inventory.displayInventory();
        inventory.updateAvailability("Standard", -2);

        inventory.updateAvailability("Deluxe", 1);

        inventory.displayInventory();

        System.out.println("\nSuite rooms available: " + inventory.getAvailability("Suite"));
    }
}