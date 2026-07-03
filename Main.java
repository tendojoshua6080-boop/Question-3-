package aquasmart;

public class Main {

    public static void main(String[] args) {

        SmartMeter meter = new SmartMeter("MTR-001", 100); // 100 UGX opening credit
        System.out.println("Meter " + meter.getMeterId() + " created. Valve open? " + meter.isValveOpen());

        System.out.println("\n=== Consuming 3 litres (costs 150, only 100 available) ===");
        boolean dispensed = meter.recordConsumption(3);
        System.out.println("Water dispensed? " + dispensed);
        System.out.println("Balance: " + meter.getCreditBalance());
        System.out.println("Valve open? " + meter.isValveOpen());

        System.out.println("\n=== Trying to consume again while valve is closed ===");
        boolean dispensed2 = meter.recordConsumption(1);
        System.out.println("Water dispensed? " + dispensed2 + " (blocked, no credit)");

        System.out.println("\n=== Loading a new token of 5000 ===");
        double newBalance = meter.loadToken(5000);
        System.out.println("New balance: " + newBalance);
        System.out.println("Valve open? " + meter.isValveOpen());

        System.out.println("\n=== Consuming 2 litres now (costs 100) ===");
        meter.recordConsumption(2);
        System.out.println("Balance: " + meter.getCreditBalance());
        System.out.println("Valve open? " + meter.isValveOpen());
    }
}