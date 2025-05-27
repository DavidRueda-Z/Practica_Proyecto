import javax.swing.JOptionPane;
import java.util.ArrayList;

public class MetodosPrestamo {

    public static ArrayList<EstudianteIngenieria> vectorIngenieros = new ArrayList<>();
    public static ArrayList<ComputadorPortatil> vectorPortatiles = new ArrayList<>();
    public static ArrayList<EstudianteDiseno> vectorDisenadores = new ArrayList<>();
    public static ArrayList<TabletaGrafica> vectorTabletas = new ArrayList<>();

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

        float promedio;
        do {
            promedio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el promedio acumulado (0 a 5):"));
            if (promedio < 0 || promedio > 5) {
                JOptionPane.showMessageDialog(null, "El promedio debe estar entre 0 y 5.");
            }
        } while (promedio < 0 || promedio > 5);

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

    public static void imprimirInventarioTotal(
            ArrayList<EstudianteIngenieria> vectorIngenieros,
            ArrayList<ComputadorPortatil> vectorPortatiles,
            ArrayList<EstudianteDiseno> vectorDisenadores,
            ArrayList<TabletaGrafica> vectorTabletas) {

        StringBuilder inventario = new StringBuilder("======= INVENTARIO TOTAL =======\n\n");

        // Estudiantes de Ingeniería
        inventario.append("🧑‍💻 Estudiantes de Ingeniería con préstamo:\n\n");
        if (vectorIngenieros.isEmpty()) {
            inventario.append("No hay estudiantes con préstamos registrados.\n\n");
        } else {
            for (EstudianteIngenieria e : vectorIngenieros) {
                inventario.append("Cédula: ").append(e.getCedula()).append("\n");
                inventario.append("Nombre: ").append(e.getNombre()).append(" ").append(e.getApellido()).append("\n");
                inventario.append("Serial equipo asignado: ").append(e.getSerial()).append("\n");
                inventario.append("------------------------------\n");
            }
        }

        // Equipos portátiles
        inventario.append("\n💻 Equipos Portátiles Registrados:\n\n");
        if (vectorPortatiles.isEmpty()) {
            inventario.append("No hay portátiles registrados.\n");
        } else {
            for (ComputadorPortatil pc : vectorPortatiles) {
                inventario.append("Serial: ").append(pc.getSerial()).append("\n");
                inventario.append("Marca: ").append(pc.getMarca()).append("\n");
                inventario.append("Sistema Operativo: ").append(pc.getSistemaOperativo()).append("\n");
                inventario.append("Procesador: ").append(pc.getProcesador()).append("\n");

                // ¿Está asignado?
                String cedulaVinculada = "Libre";
                for (EstudianteIngenieria e : vectorIngenieros) {
                    if (e.getSerial().equalsIgnoreCase(pc.getSerial())) {
                        cedulaVinculada = e.getCedula();
                        break;
                    }
                }

                inventario.append("Asignado a cédula: ").append(cedulaVinculada).append("\n");
                inventario.append("------------------------------\n");
            }
        }

        // Estudiantes de Diseño
        inventario.append("\n🎨 Estudiantes de Diseño con préstamo:\n\n");
        if (vectorDisenadores.isEmpty()) {
            inventario.append("No hay estudiantes con préstamos registrados.\n\n");
        } else {
            for (EstudianteDiseno e : vectorDisenadores) {
                inventario.append("Cédula: ").append(e.getCedula()).append("\n");
                inventario.append("Nombre: ").append(e.getNombre()).append(" ").append(e.getApellido()).append("\n");
                inventario.append("Modalidad: ").append(e.getModalidad()).append("\n");
                inventario.append("Asignaturas: ").append(e.getCantidadAsignaturas()).append("\n");
                inventario.append("Serial equipo asignado: ").append(e.getSerial()).append("\n");
                inventario.append("------------------------------\n");
            }
        }

        // Tabletas gráficas
        inventario.append("\n📱 Tabletas Gráficas Registradas:\n\n");
        if (vectorTabletas.isEmpty()) {
            inventario.append("No hay tabletas registradas.\n");
        } else {
            for (TabletaGrafica t : vectorTabletas) {
                inventario.append("Serial: ").append(t.getSerial()).append("\n");
                inventario.append("Marca: ").append(t.getMarca()).append("\n");
                inventario.append("Tamaño: ").append(t.getTamaño()).append("''\n");
                inventario.append("Almacenamiento: ").append(t.getAlmacenamiento()).append("\n");

                // ¿Está asignada?
                String cedulaVinculada = "Libre";
                for (EstudianteDiseno e : vectorDisenadores) {
                    if (String.valueOf(e.getSerial()).equalsIgnoreCase(t.getSerial())) {
                        cedulaVinculada = e.getCedula();
                        break;
                    }
                }

                inventario.append("Asignada a cédula: ").append(cedulaVinculada).append("\n");
                inventario.append("------------------------------\n");
            }
        }

        JOptionPane.showMessageDialog(null, inventario.toString());
    }

    public static void modificarPrestamoIngeniero(ArrayList<EstudianteIngenieria> vectorIngenieros) {
        String[] opcionesBusqueda = { "Cédula", "Serial" };
        String seleccion = (String) JOptionPane.showInputDialog(null, "Buscar por:", "Modificar",
                JOptionPane.QUESTION_MESSAGE, null, opcionesBusqueda, opcionesBusqueda[0]);

        EstudianteIngenieria encontrado = null;

        if (seleccion.equals("Cédula")) {
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante:");
            for (EstudianteIngenieria e : vectorIngenieros) {
                if (e.getCedula().equalsIgnoreCase(cedula)) {
                    encontrado = e;
                    break;
                }
            }
        } else {
            String serial = JOptionPane.showInputDialog("Ingrese el serial del equipo:");
            for (EstudianteIngenieria e : vectorIngenieros) {
                if (e.getSerial().equalsIgnoreCase(serial)) {
                    encontrado = e;
                    break;
                }
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
            return;
        }

        boolean continuar = true;
        while (continuar) {
            String[] opcionesMod = { "Nombre", "Apellido", "Teléfono", "Semestre", "Promedio", "Salir" };
            String opcion = (String) JOptionPane.showInputDialog(null, "¿Qué desea modificar?", "Modificar datos",
                    JOptionPane.QUESTION_MESSAGE, null, opcionesMod, opcionesMod[0]);

            switch (opcion) {
                case "Nombre":
                    String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
                    encontrado.setNombre(nuevoNombre);
                    break;
                case "Apellido":
                    String nuevoApellido = JOptionPane.showInputDialog("Nuevo apellido:");
                    encontrado.setApellido(nuevoApellido);
                    break;
                case "Teléfono":
                    String nuevoTelefono = JOptionPane.showInputDialog("Nuevo teléfono:");
                    encontrado.setTelefono(nuevoTelefono);
                    break;
                case "Semestre":
                    int nuevoSemestre = Integer.parseInt(JOptionPane.showInputDialog("Nuevo semestre:"));
                    encontrado.setSemestre(nuevoSemestre);
                    break;
                case "Promedio":
                    float nuevoPromedio = Float.parseFloat(JOptionPane.showInputDialog("Nuevo promedio:"));
                    encontrado.setPromedio(nuevoPromedio);
                    break;
                case "Salir":
                    continuar = false;
                    break;
            }
        }

        JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");
    }

    public static void devolverEquipoIngenieria(ArrayList<EstudianteIngenieria> vectorIngenieros) {
        String[] opciones = { "Cédula", "Serial" };
        String criterio = (String) JOptionPane.showInputDialog(null,
                "¿Desea buscar por cédula o serial del equipo?",
                "Devolución de equipo",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        EstudianteIngenieria encontrado = null;

        if (criterio == null)
            return;

        if (criterio.equals("Cédula")) {
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula:");
            for (EstudianteIngenieria e : vectorIngenieros) {
                if (e.getCedula().equalsIgnoreCase(cedula)) {
                    encontrado = e;
                    break;
                }
            }
        } else {
            String serial = JOptionPane.showInputDialog("Ingrese el serial:");
            for (EstudianteIngenieria e : vectorIngenieros) {
                if (e.getSerial().equalsIgnoreCase(serial)) {
                    encontrado = e;
                    break;
                }
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún estudiante con ese dato.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar el préstamo del estudiante?\nCédula: " + encontrado.getCedula(),
                "Confirmar devolución", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            vectorIngenieros.remove(encontrado);
            JOptionPane.showMessageDialog(null, "Equipo devuelto correctamente.\n(Registro del equipo se conserva)");
        }
    }

    public static void buscarPrestamoIngenieria(ArrayList<EstudianteIngenieria> vectorIngenieros) {
        String[] opciones = { "Cédula", "Serial" };
        String criterio = (String) JOptionPane.showInputDialog(null,
                "¿Desea buscar por cédula o serial del equipo?",
                "Buscar préstamo",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        EstudianteIngenieria encontrado = null;

        if (criterio == null)
            return;

        if (criterio.equals("Cédula")) {
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula:");
            for (EstudianteIngenieria e : vectorIngenieros) {
                if (e.getCedula().equalsIgnoreCase(cedula)) {
                    encontrado = e;
                    break;
                }
            }
        } else {
            String serial = JOptionPane.showInputDialog("Ingrese el serial:");
            for (EstudianteIngenieria e : vectorIngenieros) {
                if (e.getSerial().equalsIgnoreCase(serial)) {
                    encontrado = e;
                    break;
                }
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún estudiante con ese dato.");
        } else {
            String mensaje = "Cédula: " + encontrado.getCedula() +
                    "\nNombre: " + encontrado.getNombre() + " " + encontrado.getApellido() +
                    "\nTeléfono: " + encontrado.getTelefono() +
                    "\nSemestre: " + encontrado.getSemestre() +
                    "\nPromedio: " + encontrado.getPromedio() +
                    "\nSerial del equipo: " + encontrado.getSerial();
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    public static void registrarPrestamoDiseno() {
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante:");

        for (EstudianteDiseno estudiante : vectorDisenadores) {
            if (estudiante.getCedula().equals(cedula)) {
                JOptionPane.showMessageDialog(null, "Este estudiante ya tiene un préstamo registrado.");
                return;
            }
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono:");
        String modalidad = JOptionPane.showInputDialog("Modalidad (Presencial o Virtual):");
        int asignaturas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de asignaturas:"));

        // Serial único del equipo (tableta)
        int serialEquipo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el serial del equipo (número):"));

        for (TabletaGrafica t : vectorTabletas) {
            if (Integer.parseInt(t.getSerial()) == serialEquipo) {
                JOptionPane.showMessageDialog(null, "Ese serial ya está registrado.");
                return;
            }
        }

        String serial = String.valueOf(serialEquipo);
        String marca = JOptionPane.showInputDialog("Ingrese la marca:");
        float tamaño = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el tamaño en pulgadas:"));
        float precio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio:"));

        String[] opcionesAlmacenamiento = { "256 GB", "512 GB", "1 TB" };
        String almacenamiento = (String) JOptionPane.showInputDialog(null, "Seleccione almacenamiento:",
                "Almacenamiento", JOptionPane.QUESTION_MESSAGE, null, opcionesAlmacenamiento,
                opcionesAlmacenamiento[0]);

        float peso = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el peso en kg:"));

        // Crear objetos
        TabletaGrafica nuevaTableta = new TabletaGrafica(serial, marca, tamaño, precio, almacenamiento, peso);
        EstudianteDiseno nuevoEstudiante = new EstudianteDiseno(cedula, nombre, apellido, telefono, modalidad,
                asignaturas, serialEquipo);

        vectorDisenadores.add(nuevoEstudiante);
        vectorTabletas.add(nuevaTableta);

        JOptionPane.showMessageDialog(null, "¡Préstamo registrado exitosamente para estudiante de diseño!");
    }

    public static void modificarPrestamoDiseno(ArrayList<EstudianteDiseno> vectorDisenadores) {
        String[] opcionesBusqueda = { "Cédula", "Serial" };
        String seleccion = (String) JOptionPane.showInputDialog(null, "Buscar por:", "Modificar",
                JOptionPane.QUESTION_MESSAGE, null, opcionesBusqueda, opcionesBusqueda[0]);

        EstudianteDiseno encontrado = null;

        if (seleccion.equals("Cédula")) {
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante:");
            for (EstudianteDiseno e : vectorDisenadores) {
                if (e.getCedula().equalsIgnoreCase(cedula)) {
                    encontrado = e;
                    break;
                }
            }
        } else {
            int serial = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el serial del equipo:"));
            for (EstudianteDiseno e : vectorDisenadores) {
                if (e.getSerial() == serial) {
                    encontrado = e;
                    break;
                }
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
            return;
        }

        boolean continuar = true;
        while (continuar) {
            String[] opcionesMod = { "Nombre", "Apellido", "Teléfono", "Modalidad", "Asignaturas", "Salir" };
            String opcion = (String) JOptionPane.showInputDialog(null, "¿Qué desea modificar?", "Modificar datos",
                    JOptionPane.QUESTION_MESSAGE, null, opcionesMod, opcionesMod[0]);

            switch (opcion) {
                case "Nombre":
                    String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
                    encontrado.setNombre(nuevoNombre);
                    break;
                case "Apellido":
                    String nuevoApellido = JOptionPane.showInputDialog("Nuevo apellido:");
                    encontrado.setApellido(nuevoApellido);
                    break;
                case "Teléfono":
                    String nuevoTelefono = JOptionPane.showInputDialog("Nuevo teléfono:");
                    encontrado.setTelefono(nuevoTelefono);
                    break;
                case "Modalidad":
                    String nuevaModalidad = JOptionPane.showInputDialog("Nueva modalidad (Presencial o Virtual):");
                    encontrado.setModalidad(nuevaModalidad);
                    break;
                case "Asignaturas":
                    int nuevasAsignaturas = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de asignaturas:"));
                    encontrado.setCantidadAsignaturas(nuevasAsignaturas);
                    break;
                case "Salir":
                    continuar = false;
                    break;
            }
        }

        JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");
    }

    public static void devolverEquipoDiseno(ArrayList<EstudianteDiseno> vectorDisenadores) {
        String[] opciones = { "Cédula", "Serial" };
        String criterio = (String) JOptionPane.showInputDialog(null,
                "¿Desea buscar por cédula o serial del equipo?",
                "Devolución de equipo",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        EstudianteDiseno encontrado = null;

        if (criterio.equals("Cédula")) {
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula:");
            for (EstudianteDiseno e : vectorDisenadores) {
                if (e.getCedula().equalsIgnoreCase(cedula)) {
                    encontrado = e;
                    break;
                }
            }
        } else {
            int serial = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el serial:"));
            for (EstudianteDiseno e : vectorDisenadores) {
                if (e.getSerial() == serial) {
                    encontrado = e;
                    break;
                }
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún estudiante con ese dato.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de eliminar el préstamo del estudiante?\nCédula: " + encontrado.getCedula(),
                "Confirmar devolución", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            vectorDisenadores.remove(encontrado);
            JOptionPane.showMessageDialog(null, "Equipo devuelto correctamente.\n(Registro de la tableta se conserva)");
        }
    }

    public static void buscarPrestamoDiseno(ArrayList<EstudianteDiseno> vectorDisenadores) {
        String[] opciones = { "Cédula", "Serial" };
        String criterio = (String) JOptionPane.showInputDialog(null,
                "¿Desea buscar por cédula o serial del equipo?",
                "Buscar préstamo",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        EstudianteDiseno encontrado = null;

        if (criterio.equals("Cédula")) {
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula:");
            for (EstudianteDiseno e : vectorDisenadores) {
                if (e.getCedula().equalsIgnoreCase(cedula)) {
                    encontrado = e;
                    break;
                }
            }
        } else {
            int serial = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el serial:"));
            for (EstudianteDiseno e : vectorDisenadores) {
                if (e.getSerial() == serial) {
                    encontrado = e;
                    break;
                }
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún estudiante con ese dato.");
        } else {
            String mensaje = "Cédula: " + encontrado.getCedula() +
                    "\nNombre: " + encontrado.getNombre() + " " + encontrado.getApellido() +
                    "\nTeléfono: " + encontrado.getTelefono() +
                    "\nModalidad: " + encontrado.getModalidad() +
                    "\nAsignaturas: " + encontrado.getCantidadAsignaturas() +
                    "\nSerial del equipo: " + encontrado.getSerial();
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }
}