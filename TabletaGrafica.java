public class TabletaGrafica {
    // Para trabajar algunas variables que sean de tipo unicas, como si fueran las
    // llaves de un formulario, uso el "final", ya que esto no permite que la
    // variable cambie
    // En otras palabras el final es un tipo de clave o contraseña
    private final String serial;
    private String marca;
    private float tamaño;
    private float precio;
    private String almacenamiento;
    private float peso;

    public TabletaGrafica(String serial, String marca, float tamaño, float precio, String almacenamiento, float peso) {
        // Manejo el "this" para no estar cambiando el nombre de la variable, y que se
        // me sea mas facil visualizar todo
        this.serial = serial;
        this.marca = marca;
        this.tamaño = tamaño;
        this.precio = precio;
        this.almacenamiento = almacenamiento;
        this.peso = peso;
    }

    public String getSerial() {
        return serial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getTamaño() {
        return tamaño;
    }

    public void setTamaño(float tamaño) {
        this.tamaño = tamaño;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

}