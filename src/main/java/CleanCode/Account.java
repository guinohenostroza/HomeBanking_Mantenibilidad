package CleanCode;

public class Account {
    private double balance;
    private static final double FEE = 2.5; // Cargo adicional fijo
    private static final double LOW_BALANCE_FEE = 5.0;
    private static final double MONTHLY_MAINTENANCE_FEE = 10.0;
    private static final double MAINTENANCE_THRESHOLD = 1000.0;
    private static final double LOW_BALANCE_THRESHOLD = 100.0;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("El monto debe ser positivo.");
            return;
        }

        balance += amount;
        if (amount > 500) {
            System.out.println("Depósito grande de más de $500");
        }

        applyMaintenanceIfEligible();
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Fondos insuficientes");
            if (balance < 0) {
                System.out.println("¡Cuenta sobregirada! Comuníquese con soporte.");
            }
            return;
        }

        balance -= amount;
        applyLowBalanceFeeIfEligible();
    }

    public void displayAccountInfo() {
        System.out.println("El saldo de la cuenta es: $" + balance);

        applyMaintenanceIfEligible(); // Aplicar cargo de mantenimiento en displayAccountInfo
        applyLowBalanceFeeIfEligible(); // Aplicar cargo de saldo bajo en displayAccountInfo

        System.out.println("Saldo disponible después del cargo: $" + calculateAvailableBalance());
    }

    private double calculateAvailableBalance() {
        return balance > FEE ? balance - FEE : balance;
    }

    private void applyMaintenanceIfEligible() {
        if (balance > MAINTENANCE_THRESHOLD) {
            balance -= MONTHLY_MAINTENANCE_FEE;
            System.out.println("Se aplicó un cargo de mantenimiento mensual de $" + MONTHLY_MAINTENANCE_FEE);
        }
    }

    private void applyLowBalanceFeeIfEligible() {
        if (balance < LOW_BALANCE_THRESHOLD) {
            balance -= LOW_BALANCE_FEE;
            System.out.println("Se aplicó un cargo por saldo bajo de $" + LOW_BALANCE_FEE);
        }
    }

    public static void main(String[] args) {
        Account account = new Account(1200.0); // Crear una cuenta con un saldo inicial de 1200

        account.displayAccountInfo(); // Muestra el saldo inicial y aplica cargos si es necesario
        account.deposit(200); // Realizar un depósito de $200 y verificar si se aplica mantenimiento
        account.displayAccountInfo(); // Muestra el saldo actualizado

        account.withdraw(1500); // Intentar realizar un retiro que cause saldo insuficiente
        account.displayAccountInfo(); // Muestra el saldo después del retiro
    }
}
