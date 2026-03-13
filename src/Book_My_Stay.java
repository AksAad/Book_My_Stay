import java.util.List;

public class Book_My_Stay {

    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        AddOnService breakfast = new AddOnService("Breakfast", 25.0);
        AddOnService spa = new AddOnService("Spa", 60.0);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 40.0);

        String reservationId = "RES1001";

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);
        manager.addService(reservationId, airportPickup);

        System.out.println("Services for Reservation: " + reservationId);

        List<AddOnService> services = manager.getServices(reservationId);

        for (AddOnService service : services) {
            System.out.println(service.getServiceName() + " - $" + service.getCost());
        }


        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Total Add-On Cost: $" + totalCost);
    }
}