package SpaguettiCode;

public class Account {
    public double bal; // Nombre ambiguo
    public double fee = 2.5; // Cargo adicional fijo
    public double extraFee = 10; // Cargo mensual
    public boolean isActive = true; // Bandera de estado que se usa innecesariamente en varios lugares

    public Account(double bal) {
        this.bal = bal;
    }

   public void showAccountInfo() {
        System.out.println("El saldo de la cuenta es: $" + bal);

        // Verificación redundante de balance y aplicación de tarifas
        if (bal > 50) {
            System.out.println("Se aplica un cargo si el saldo excede los $50");
            bal -= fee; // Modificación directa de balance
            System.out.println("Saldo total después del cargo: $" + bal);
        } else {
            System.out.println("No hay cargos para cuentas con bajo saldo");
        }

        if (bal < 0) {
            System.out.println("¡Cuenta sobregirada! Contacte con soporte.");
            isActive = false; // Uso ambiguo de isActive sin propósito claro
        }

        System.out.println("Verificando para mantenimiento mensual...");
        if (bal > 1000) {
            bal -= extraFee;
            System.out.println("Se aplicó un cargo mensual de $10.");
        } else if (bal < 100) { // Lógica innecesaria que complica la lectura
            bal -= 5;
            System.out.println("Se aplicó un cargo de saldo bajo de $5.");
        }

        // Impresión redundante del saldo
        System.out.println("Saldo de la cuenta después de cualquier cargo: $" + bal);
    }

  public void deposit(double amt) {
        if (amt > 0) {
            bal += amt;
            if (amt > 500) {
                System.out.println("Depósito grande de más de $500");
            }
            if (bal > 1000) {
                applyMaintenance(); // Llamada innecesaria a otro método
            }
        } else {
            System.out.println("El monto debe ser positivo.");
        }
    }

    public void withdraw(double amt) {
        if (amt > bal) {
            System.out.println("Fondos insuficientes");
            if (bal < 0) {
                System.out.println("¡Cuenta sobregirada! Comuníquese con el soporte.");
            }
        } else {
            bal -= amt;
            if (bal < 100) {
                applyLowBalanceFee(); // Aplicación de cargo adicional innecesaria
            }
        }
    }

    public void applyMaintenance() {
        if (bal > 1000) {
            bal -= extraFee;
            System.out.println("Se aplicó un cargo de mantenimiento mensual de $" + extraFee);
        }
    }

    public void applyLowBalanceFee() {
        if (bal < 100) {
            bal -= 5;
            System.out.println("Se aplicó un cargo por saldo bajo de $5.");
        }
    }

    public static void main(String[] args) {
        Account cuenta = new Account(1200.0); // Crear una cuenta con un saldo inicial de 1200

        cuenta.showAccountInfo(); // Mostrar el saldo inicial y aplicar cargos si es necesario
        cuenta.deposit(200); // Realizar un depósito de $200 y verificar si se aplica mantenimiento
        cuenta.showAccountInfo(); // Mostrar el saldo actualizado

        cuenta.withdraw(1500); // Intentar realizar un retiro que cause saldo insuficiente
        cuenta.showAccountInfo(); // Mostrar el saldo después del retiro
    }
}


