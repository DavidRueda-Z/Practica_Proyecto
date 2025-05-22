import javax.swing.JOptionPane;
import java.util.ArrayList;

public class MetodosPrestamo {

    public static ArrayList<EstudianteIngenieria> vectorIngenieros = new ArrayList<>();
    public static ArrayList<ComputadorPortatil> vectorPortatiles = new ArrayList<>();

    public static void registrarPrestamoIngenieria() {
        // 1. Solicitar cédula y verificar que no exista préstamo previo
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante:");

        for (EstudianteIngenieria estudiante : vectorIngenieros) {
            if (estudiante.getCedula().equals(cedula)) {
                JOptionPane.showMessageDialog(null, "Ya existe un préstamo registrado para este estudiante.");
                return;
            }
        }

        // 2. Solicitar datos del estudiante
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono:");
        int semestre = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de semestre actual:"));
        float promedio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el promedio acumulado:"));

        // 3. Solicitar datos del computador portátil
        String serial = JOptionPane.showInputDialog("Ingrese el serial del equipo:");

        // Validar que el serial no exista
        for (ComputadorPortatil comp : vectorPortatiles) {
            if (comp.getSerial().equals(serial)) {
                JOptionPane.showMessageDialog(null, "Ese serial ya está registrado.");
                return;
            }
        }

        String marca = JOptionPane.showInputDialog("Ingrese la marca:");
        float tamaño = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el tamaño en pulgadas:"));
        float precio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio:"));

        // Submenú sistema operativo
        // Defino las opciones
        String[] soOpciones = { "Windows 7", "Windows 10", "Windows 11" };
        String sistemaOperativo = (String) JOptionPane.showInputDialog(null, "Seleccione sistema operativo:",
                "Sistema Operativo", JOptionPane.QUESTION_MESSAGE, null, soOpciones, soOpciones[0]);

        // Submenú procesador

        String[] procesadores = { "AMD Ryzen", "Intel® Core™ i5" };
        String procesador = (String) JOptionPane.showInputDialog(null, "Seleccione procesador:",
                "Procesador", JOptionPane.QUESTION_MESSAGE, null, procesadores, procesadores[0]);

        // 4. Crear objetos
        ComputadorPortatil nuevoEquipo = new ComputadorPortatil(serial, marca, tamaño, precio, sistemaOperativo,
                procesador);
        EstudianteIngenieria nuevoEstudiante = new EstudianteIngenieria(cedula, nombre, apellido, telefono, semestre,
                promedio, serial);

        // 5. Agregar a los vectores
        vectorIngenieros.add(nuevoEstudiante);
        vectorPortatiles.add(nuevoEquipo);

        JOptionPane.showMessageDialog(null, "¡Préstamo registrado exitosamente!");
    }
}