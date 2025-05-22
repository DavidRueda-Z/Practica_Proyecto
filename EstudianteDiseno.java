public class EstudianteDiseno {
    // Para trabajar algunas variables que sean de tipo unicas, como si fueran las
    // llaves de un formulario, uso el "final", ya que esto no permite que la
    // variable cambie
    // En otras palabras el final es un tipo de clave o contraseña
    private final String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String modalidad;
    private int cantidadAsignaturas;
    private final int serial;

    public EstudianteDiseno(String cedula, String nombre, String apellido, String telefono, String modalidad,
            int cantidadAsignaturas, int serial) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.modalidad = modalidad;
        this.cantidadAsignaturas = cantidadAsignaturas;
        this.serial = serial;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public int getCantidadAsignaturas() {
        return cantidadAsignaturas;
    }

    public void setCantidadAsignaturas(int cantidadAsignaturas) {
        this.cantidadAsignaturas = cantidadAsignaturas;
    }

    public int getSerial() {
        return serial;
    }

}