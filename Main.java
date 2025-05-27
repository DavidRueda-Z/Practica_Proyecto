import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        // Igualmente deberías tener los otros vectores, como vectorDisenadores,
        // vectorPortatil, etc.

        boolean continuar = true;

        while (continuar) {
            String[] opciones = {
                    "1. Estudiantes de Ingeniería",
                    "2. Estudiantes de Diseño",
                    "3. Imprimir Inventario Total",
                    "4. Salir"
            };
            String seleccion = (String) JOptionPane.showInputDialog(null, "Menú Principal", "Seleccione una opción",
                    JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == null || seleccion.equals("4. Salir")) {
                continuar = false;
                continue;
            }

            switch (seleccion) {
                case "1. Estudiantes de Ingeniería":
                    boolean menuIng = true;
                    while (menuIng) {
                        String[] opcionesIng = {
                                "1.1 Registrar préstamo de equipo",
                                "1.2 Modificar préstamo de equipo",
                                "1.3 Devolución de equipo",
                                "1.4 Buscar equipo",
                                "1.5 Volver al menú principal"
                        };
                        String opcionIng = (String) JOptionPane.showInputDialog(null, "Menú Ingeniería",
                                "Seleccione una opción", JOptionPane.QUESTION_MESSAGE, null, opcionesIng,
                                opcionesIng[0]);

                        if (opcionIng == null || opcionIng.equals("1.5 Volver al menú principal")) {
                            menuIng = false;
                            continue;
                        }

                        switch (opcionIng) {
                            case "1.1 Registrar préstamo de equipo":
                                MetodosPrestamo.registrarPrestamoIngenieria();
                                break;
                            case "1.2 Modificar préstamo de equipo":
                                MetodosPrestamo.modificarPrestamoIngeniero(MetodosPrestamo.vectorIngenieros);
                                break;
                            case "1.3 Devolución de equipo":
                                MetodosPrestamo.devolverEquipoIngenieria(MetodosPrestamo.vectorIngenieros);
                                break;
                            case "1.4 Buscar equipo":
                                MetodosPrestamo.buscarPrestamoIngenieria(MetodosPrestamo.vectorIngenieros);
                                break;
                        }
                    }
                    break;

                case "2. Estudiantes de Diseño":
                    boolean menuDiseno = true;
                    while (menuDiseno) {
                        String[] opcionesDiseno = {
                                "2.1 Registrar préstamo de equipo",
                                "2.2 Modificar préstamo de equipo",
                                "2.3 Devolución de equipo",
                                "2.4 Buscar equipo",
                                "2.5 Volver al menú principal"
                        };
                        String opcionDiseno = (String) JOptionPane.showInputDialog(null,
                                "Menú Estudiantes de Diseño", "Seleccione una opción",
                                JOptionPane.QUESTION_MESSAGE, null, opcionesDiseno, opcionesDiseno[0]);

                        if (opcionDiseno == null || opcionDiseno.equals("2.5 Volver al menú principal")) {
                            menuDiseno = false;
                            continue;
                        }

                        switch (opcionDiseno) {
                            case "2.1 Registrar préstamo de equipo":
                                MetodosPrestamo.registrarPrestamoDiseno();
                                break;
                            case "2.2 Modificar préstamo de equipo":
                                MetodosPrestamo.modificarPrestamoDiseno(MetodosPrestamo.vectorDisenadores);
                                break;
                            case "2.3 Devolución de equipo":
                                MetodosPrestamo.devolverEquipoDiseno(MetodosPrestamo.vectorDisenadores);
                                break;
                            case "2.4 Buscar equipo":
                                MetodosPrestamo.buscarPrestamoDiseno(MetodosPrestamo.vectorDisenadores);
                                break;
                        }
                    }
                    break;

                case "3. Imprimir Inventario Total":
                    MetodosPrestamo.imprimirInventarioTotal(
                            MetodosPrestamo.vectorIngenieros,
                            MetodosPrestamo.vectorPortatiles,
                            MetodosPrestamo.vectorDisenadores,
                            MetodosPrestamo.vectorTabletas);
                    break;
            }
        }
    }

}
