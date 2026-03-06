public class book_my_stay {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   Book My Stay - Hotel Booking");
        System.out.println("   Version 2.1");
        System.out.println("====================================");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("\n--- Room Details ---\n");

        single.displayRoomDetails();
        System.out.println("Available : " + singleAvailability);
        System.out.println("----------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + doubleAvailability);
        System.out.println("----------------------------");

        suite.displayRoomDetails();
        System.out.println("Available : " + suiteAvailability);
        System.out.println("----------------------------");

        System.out.println("\nApplication Finished.");
    }
}