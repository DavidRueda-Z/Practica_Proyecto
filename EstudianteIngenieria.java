public class EstudianteIngenieria {
    // Para trabajar algunas variables que sean de tipo unicas, como si fueran las
    // llaves de un formulario, uso el "final", ya que esto no permite que la
    // variable cambie
    // En otras palabras el final es un tipo de clave o contraseña
    private final String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private int semestre;
    private float promedio;
    private final String serial;

    public EstudianteIngenieria(String cedula, String nombre, String apellido, String telefono, int semestre,
            float promedio, String serial) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.semestre = semestre;
        this.promedio = promedio;
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

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public String getSerial() {
        return serial;
    }

}