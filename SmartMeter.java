package aquasmart;

public class SmartMeter {

    private String meterId;
    private double creditBalance;
    private boolean valveOpen;

    private static final double COST_PER_LITRE = 50.0; // UGX per litre

    public SmartMeter(String meterId, double openingCreditBalance) {
        this.meterId = meterId;
        this.creditBalance = openingCreditBalance;
        this.valveOpen = true;
    }

    // Adds credit; re-opens the valve if it had been closed. Returns new balance.
    public double loadToken(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Token amount must be positive");
        }
        creditBalance += amount;
        if (!valveOpen) {
            valveOpen = true;
        }
        return creditBalance;
    }

    // Deducts litres * 50 UGX from credit. If it reaches/falls below zero,
    // clamps balance to 0 and closes the valve. Returns true if water was
    // dispensed, false if the request was blocked (valve already closed).
    public boolean recordConsumption(double litres) {
        if (litres <= 0) {
            return false;
        }
        if (!valveOpen) {
            return false; // blocked: no credit left, valve already shut
        }

        double cost = litres * COST_PER_LITRE;
        creditBalance -= cost;

        if (creditBalance <= 0) {
            creditBalance = 0;
            valveOpen = false;
        }
        return true;
    }

    public String getMeterId() {
        return meterId;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public boolean isValveOpen() {
        return valveOpen;
    }
}