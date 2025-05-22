import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                    "SISTEMA DE PRÉSTAMO - SAN JUAN DE DIOS\n" +
                            "1. Estudiantes de Ingeniería\n" +
                            "2. Estudiantes de Diseño\n" +
                            "3. Imprimir Inventario Total\n" +
                            "4. Salir");

            switch (opcion) {
                case "1":
                    menuIngenieria();
                case "2":
                    menuDiseno();
                case "3": {
                    // Aquí se llamará método para mostrar inventario completo
                    JOptionPane.showMessageDialog(null, "Funcionalidad aún no implementada.");
                }
                case "4":
                    salir = true;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    public static void menuIngenieria() {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(
                    "ESTUDIANTES DE INGENIERÍA\n" +
                            "1. Registrar préstamo\n" +
                            "2. Modificar préstamo\n" +
                            "3. Devolución de equipo\n" +
                            "4. Buscar equipo\n" +
                            "5. Volver al menú principal");

            switch (opcion) {
                case "1": {
                    MetodosPrestamo.registrarPrestamoIngenieria();
                }
                case "2": {
                    // Llamar método para modificar préstamo ingeniería
                }
                case "3": {
                    // Llamar método para eliminar préstamo ingeniería
                }
                case "4": {
                    // Llamar método para buscar préstamo ingeniería
                }
                case "5": {
                    JOptionPane.showMessageDialog(null, "Volviendo al menú principal.");
                }
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (!opcion.equals("5"));
    }

    public static void menuDiseno() {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(
                    "ESTUDIANTES DE DISEÑO\n" +
                            "1. Registrar préstamo\n" +
                            "2. Modificar préstamo\n" +
                            "3. Devolución de equipo\n" +
                            "4. Buscar equipo\n" +
                            "5. Volver al menú principal\n");

            switch (opcion) {
                case "1": {
                    // Llamar método para registrar préstamo diseño
                }
                case "2": {
                    // Llamar método para modificar préstamo diseño
                }
                case "3": {
                    // Llamar método para eliminar préstamo diseño
                }
                case "4": {
                    // Llamar método para buscar préstamo diseño
                }
                case "5": {
                    JOptionPane.showMessageDialog(null, "Volviendo al menú principal.");
                }
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (!opcion.equals("5"));
    }
}
