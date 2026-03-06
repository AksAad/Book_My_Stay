import java.util.*;




class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void registerRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Set<String> getAllRoomTypes() {
        return inventory.keySet();
    }
}


class SearchService {

    private RoomInventory inventory;
    private HashMap<String, Room> roomCatalog;

    public SearchService(RoomInventory inventory, HashMap<String, Room> roomCatalog) {
        this.inventory = inventory;
        this.roomCatalog = roomCatalog;
    }


    public void searchAvailableRooms() {

        System.out.println("Available Rooms:\n");

        for (String roomType : inventory.getAllRoomTypes()) {

            int available = inventory.getAvailability(roomType);

            if (available > 0) {

                Room room = roomCatalog.get(roomType);

                if (room != null) {
                    System.out.println("Room Type: " + room.getType());
                    System.out.println("Price: $" + room.getPrice());
                    System.out.println("Amenities: " + room.getAmenities());
                    System.out.println("Available Rooms: " + available);
                    System.out.println("-----------------------------");
                }
            }
        }
    }
}


public class book_my_stay {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.registerRoomType("Standard", 10);
        inventory.registerRoomType("Deluxe", 5);
        inventory.registerRoomType("Suite", 0); // unavailable

        HashMap<String, Room> roomCatalog = new HashMap<>();

        roomCatalog.put("Standard",
                new Room("Standard", 120.0, "WiFi, TV, Queen Bed"));

        roomCatalog.put("Deluxe",
                new Room("Deluxe", 200.0, "WiFi, TV, King Bed, Mini Bar"));

        roomCatalog.put("Suite",
                new Room("Suite", 350.0, "WiFi, TV, King Bed, Living Area"));

        SearchService searchService = new SearchService(inventory, roomCatalog);

        searchService.searchAvailableRooms();
    }
}