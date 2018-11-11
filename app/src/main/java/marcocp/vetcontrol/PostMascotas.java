package marcocp.vetcontrol;

public class PostMascotas {

    // Atributos
    private String idMascotas;
    private String idClientes;
    private String Nombre;
    private String Especie;
    private String Raza;
    private String ColorPelo;
    private String FechaNacimiento;
    private String Foto;

    public PostMascotas(String idMascotas, String idClientes, String nombre, String especie, String raza, String colorPelo, String fechaNacimiento, String foto) {
        this.idMascotas = idMascotas;
        this.idClientes = idClientes;
        this.Nombre = nombre;
        this.Especie = especie;
        this.Raza = raza;
        this.ColorPelo = colorPelo;
        this.FechaNacimiento = fechaNacimiento;
        this.Foto = foto;
    }


    public String getIdMascotas() {
        return idMascotas;
    }

    public void setIdMascotas(String idMascotas) {
        this.idMascotas = idMascotas;
    }

    public String getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(String idClientes) {
        this.idClientes = idClientes;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public String getColorPelo() {
        return ColorPelo;
    }

    public void setColorPelo(String colorPelo) {
        ColorPelo = colorPelo;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }
}
