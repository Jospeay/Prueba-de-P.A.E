package jpa.ni.practicapae.Model;

public class Participante {

    private String nombre;
    private int edad;
    private String telefono;
    private String categoria;
    private String genero;
    private String modalidad;
    private String caracteristicas;
    private String disciplina;
    private String estado;

    // Un único constructor con TODOS los campos (el que usa el controlador)
    public Participante(String nombre, int edad, String telefono, String categoria,
                        String genero, String modalidad, String caracteristicas,
                        String disciplina, String estado) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.categoria = categoria;
        this.genero = genero;
        this.modalidad = modalidad;
        this.caracteristicas = caracteristicas;
        this.disciplina = disciplina;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}