public class ComputadorPortatil {
    // Para trabajar algunas variables que sean de tipo unicas, como si fueran las
    // llaves de un formulario, uso el "final", ya que esto no permite que la
    // variable cambie
    // En otras palabras el final es un tipo de clave o contraseña
    private final String serial;
    private String marca;
    private float tamaño;
    private float precio;
    private String sistemaOperativo;
    private String procesador;

    public ComputadorPortatil(String serial, String marca, float tamaño, float precio, String sistemaOperativo,
            String procesador) {
        this.serial = serial;
        this.marca = marca;
        this.tamaño = tamaño;
        this.precio = precio;
        this.sistemaOperativo = sistemaOperativo;
        this.procesador = procesador;
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

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

}
