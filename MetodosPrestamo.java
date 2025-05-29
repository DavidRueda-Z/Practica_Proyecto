import javax.swing.JOptionPane;
import java.io.*;
import java.util.ArrayList;

public class MetodosPrestamo {

    public static ArrayList<EstudianteIngenieria> vectorIngenieros = new ArrayList<>();
    public static ArrayList<ComputadorPortatil> vectorPortatiles = new ArrayList<>();
    public static ArrayList<EstudianteDiseno> vectorDisenadores = new ArrayList<>();
    public static ArrayList<TabletaGrafica> vectorTabletas = new ArrayList<>();

    public static void registrarPrestamoIngenieria() {
        // Verificar que no exista préstamo previo
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante:");

        for (EstudianteIngenieria estudiante : vectorIngenieros) {
            if (estudiante.getCedula().equals(cedula)) {
                JOptionPane.showMessageDialog(null, "Ya existe un préstamo registrado para este estudiante.");
                return;
            }
        }

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

        String serial = JOptionPane.showInputDialog("Ingrese el serial del equipo:");

        for (ComputadorPortatil comp : vectorPortatiles) {
            if (comp.getSerial().equals(serial)) {
                JOptionPane.showMessageDialog(null, "Ese serial ya está registrado.");
                return;
            }
        }

        String marca = JOptionPane.showInputDialog("Ingrese la marca:");

        float tamaño;
        do {
            tamaño = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el tamaño en pulgadas:"));
            if (tamaño > 32) {
                JOptionPane.showMessageDialog(null, "El tamaño máximo permitido es 32 pulgadas.");
            }
        } while (tamaño > 32);

        float precio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio:"));
        // Para disminuir la cantidad de validaciones que tenia que hacer, decidi
        // limitar la respuesta que el usuario pueda dar
        // Con una herramienta que me brinda el JOptionPane, que es como una lista de
        // opciones
        String[] soOpciones = { "Windows 7", "Windows 10", "Windows 11" };
        String sistemaOperativo = (String) JOptionPane.showInputDialog(null, "Seleccione sistema operativo:",
                "Sistema Operativo", JOptionPane.QUESTION_MESSAGE, null, soOpciones, soOpciones[0]);

        String[] procesadores = { "AMD Ryzen", "Intel® Core™ i5" };
        String procesador = (String) JOptionPane.showInputDialog(null, "Seleccione procesador:",
                "Procesador", JOptionPane.QUESTION_MESSAGE, null, procesadores, procesadores[0]);

        ComputadorPortatil nuevoEquipo = new ComputadorPortatil(serial, marca, tamaño, precio, sistemaOperativo,
                procesador);
        EstudianteIngenieria nuevoEstudiante = new EstudianteIngenieria(cedula, nombre, apellido, telefono, semestre,
                promedio, serial);

        vectorIngenieros.add(nuevoEstudiante);
        vectorPortatiles.add(nuevoEquipo);

        JOptionPane.showMessageDialog(null, "¡Préstamo registrado exitosamente!");
    }

    public static void imprimirInventarioTotal(
            ArrayList<EstudianteIngenieria> vectorIngenieros,
            ArrayList<ComputadorPortatil> vectorPortatiles,
            ArrayList<EstudianteDiseno> vectorDisenadores,
            ArrayList<TabletaGrafica> vectorTabletas) {

        // Para no estar difiniendo diferentes textos, con el string builder creo una
        // "variable" que me permita añadirle diferentes tipos de texto
        // Mientras construyo lo que quiero mostrar
        StringBuilder inventario = new StringBuilder("======= INVENTARIO TOTAL =======\n\n");

        inventario.append("Estudiantes de Ingeniería con préstamo:\n\n");
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
        // Aqui vemos la variable "inventario" y simplemente con append le agregamos lo
        // que queremos mostrar
        inventario.append("\nEquipos Portátiles Registrados:\n\n");
        if (vectorPortatiles.isEmpty()) {
            inventario.append("No hay portátiles registrados.\n");
        } else {
            for (ComputadorPortatil pc : vectorPortatiles) {
                inventario.append("Serial: ").append(pc.getSerial()).append("\n");
                inventario.append("Marca: ").append(pc.getMarca()).append("\n");
                inventario.append("Sistema Operativo: ").append(pc.getSistemaOperativo()).append("\n");
                inventario.append("Procesador: ").append(pc.getProcesador()).append("\n");

                // Aunque el progama no lo pide, no quería que el registro de los equipos se
                // pierda al hacer las devoluciones
                // Por lo que verifico que el equipo este libre
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
        inventario.append("\nEstudiantes de Diseño con préstamo:\n\n");
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
        inventario.append("\nTabletas Gráficas Registradas:\n\n");
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

        String[] modalidades = { "Presencial", "Virtual" };
        String modalidad = (String) JOptionPane.showInputDialog(null, "Seleccione modalidad:",
                "Modalidad", JOptionPane.QUESTION_MESSAGE, null, modalidades, modalidades[0]);

        int asignaturas;
        do {
            asignaturas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de asignaturas:"));
            if (asignaturas > 8) {
                JOptionPane.showMessageDialog(null, "No es posible tener más de 8 materias por semestre.");
            }
        } while (asignaturas > 8);

        int serialEquipo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el serial del equipo (número):"));

        for (TabletaGrafica t : vectorTabletas) {
            if (Integer.parseInt(t.getSerial()) == serialEquipo) {
                JOptionPane.showMessageDialog(null, "Ese serial ya está registrado.");
                return;
            }
        }

        String serial = String.valueOf(serialEquipo);
        String marca = JOptionPane.showInputDialog("Ingrese la marca:");

        float tamaño;
        do {
            tamaño = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el tamaño en pulgadas:"));
            if (tamaño > 32) {
                JOptionPane.showMessageDialog(null, "El tamaño máximo permitido es 32 pulgadas.");
            }
        } while (tamaño > 32);

        float precio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio:"));

        String[] opcionesAlmacenamiento = { "256 GB", "512 GB", "1 TB" };
        String almacenamiento = (String) JOptionPane.showInputDialog(null, "Seleccione almacenamiento:",
                "Almacenamiento", JOptionPane.QUESTION_MESSAGE, null, opcionesAlmacenamiento,
                opcionesAlmacenamiento[0]);

        float peso;
        do {
            peso = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el peso en kg:"));
            if (peso > 10) {
                JOptionPane.showMessageDialog(null, "El peso máximo permitido es 10 kg.");
            }
        } while (peso > 10);

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

    public static void exportarDatos() {
        try {
            // Exportar estudiantes de ingeniería
            PrintWriter pwIng = new PrintWriter(new FileWriter("ingenieria.txt"));
            for (EstudianteIngenieria e : vectorIngenieros) {
                pwIng.println(
                        e.getCedula() + "|" + e.getNombre() + "|" + e.getApellido() + "|" + e.getTelefono() + "|" +
                                e.getSemestre() + "|" + e.getPromedio() + "|" + e.getSerial());
            }
            pwIng.close();

            // Exportar portátiles
            PrintWriter pwPc = new PrintWriter(new FileWriter("portatiles.txt"));
            for (ComputadorPortatil pc : vectorPortatiles) {
                pwPc.println(pc.getSerial() + "|" + pc.getMarca() + "|" + pc.getTamaño() + "|" +
                        pc.getPrecio() + "|" + pc.getSistemaOperativo() + "|" + pc.getProcesador());
            }
            pwPc.close();

            // Exportar estudiantes de diseño
            PrintWriter pwDiseno = new PrintWriter(new FileWriter("diseno.txt"));
            for (EstudianteDiseno e : vectorDisenadores) {
                pwDiseno.println(
                        e.getCedula() + "|" + e.getNombre() + "|" + e.getApellido() + "|" + e.getTelefono() + "|" +
                                e.getModalidad() + "|" + e.getCantidadAsignaturas() + "|" + e.getSerial());
            }
            pwDiseno.close();

            // Exportar tabletas gráficas
            PrintWriter pwTab = new PrintWriter(new FileWriter("tabletas.txt"));
            for (TabletaGrafica t : vectorTabletas) {
                pwTab.println(t.getSerial() + "|" + t.getMarca() + "|" + t.getTamaño() + "|" +
                        t.getPrecio() + "|" + t.getAlmacenamiento() + "|" + t.getPeso());
            }
            pwTab.close();

            JOptionPane.showMessageDialog(null, "Datos exportados correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al exportar: " + e.getMessage());
        }
    }

    public static void importarDatos() {
        try {
            // Limpiar listas actuales antes de cargar, para no sobrecargar el archivo txt,
            // con registros repetidos
            vectorIngenieros.clear();
            vectorPortatiles.clear();
            vectorDisenadores.clear();
            vectorTabletas.clear();

            // Importar portátiles
            BufferedReader brPc = new BufferedReader(new FileReader("portatiles.txt"));
            String lineaPc;
            while ((lineaPc = brPc.readLine()) != null) {
                String[] datos = lineaPc.split("\\|");
                if (datos.length == 6) {
                    ComputadorPortatil pc = new ComputadorPortatil(
                            datos[0], // serial
                            datos[1], // marca
                            Float.parseFloat(datos[2]), // tamaño
                            Float.parseFloat(datos[3]), // precio
                            datos[4], // sistema operativo
                            datos[5] // procesador
                    );
                    vectorPortatiles.add(pc);
                }
            }
            brPc.close();

            // Importar estudiantes de ingeniería
            BufferedReader brIng = new BufferedReader(new FileReader("ingenieria.txt"));
            String lineaIng;
            while ((lineaIng = brIng.readLine()) != null) {
                String[] datos = lineaIng.split("\\|");
                if (datos.length == 7) {
                    EstudianteIngenieria e = new EstudianteIngenieria(
                            datos[0], // cedula
                            datos[1], // nombre
                            datos[2], // apellido
                            datos[3], // telefono
                            Integer.parseInt(datos[4]), // semestre
                            Float.parseFloat(datos[5]), // promedio
                            datos[6] // serial del equipo
                    );
                    vectorIngenieros.add(e);
                }
            }
            brIng.close();

            // Importar tabletas
            BufferedReader brTab = new BufferedReader(new FileReader("tabletas.txt"));
            String lineaTab;
            while ((lineaTab = brTab.readLine()) != null) {
                String[] datos = lineaTab.split("\\|");
                if (datos.length == 6) {
                    TabletaGrafica t = new TabletaGrafica(
                            datos[0], // serial
                            datos[1], // marca
                            Float.parseFloat(datos[2]), // tamaño
                            Float.parseFloat(datos[3]), // precio
                            datos[4], // almacenamiento
                            Float.parseFloat(datos[5]) // peso
                    );
                    vectorTabletas.add(t);
                }
            }
            brTab.close();

            // Importar estudiantes de diseño
            BufferedReader brDiseno = new BufferedReader(new FileReader("diseno.txt"));
            String lineaDis;
            while ((lineaDis = brDiseno.readLine()) != null) {
                String[] datos = lineaDis.split("\\|");
                if (datos.length == 7) {
                    EstudianteDiseno e = new EstudianteDiseno(
                            datos[0], // cedula
                            datos[1], // nombre
                            datos[2], // apellido
                            datos[3], // telefono
                            datos[4], // modalidad
                            Integer.parseInt(datos[5]), // asignaturas
                            Integer.parseInt(datos[6]) // serial del equipo
                    );
                    vectorDisenadores.add(e);
                }
            }
            brDiseno.close();

            JOptionPane.showMessageDialog(null, "Datos importados correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al importar: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de formato en archivo: " + e.getMessage());
        }
    }

}