package marcocp.vetcontrol;

public class PostConsultas {

    // Atributos
    private String idConsultas;
    private String idCliente;
    private String idMascota;
    private String Fecha;
    private String Consulta;
    private String Descripcion;
    private String Costo;

    public PostConsultas(String idConsultas, String idCliente, String idMascota, String fecha, String consulta, String descripcion, String costo) {
        this.idConsultas = idConsultas;
        this.idCliente = idCliente;
        this.idMascota = idMascota;
        this.Fecha = fecha;
        this.Consulta = consulta;
        this.Descripcion = descripcion;
        this.Costo = costo;
    }

    public String getIdConsultas() {
        return idConsultas;
    }

    public void setIdConsultas(String idConsultas) {
        this.idConsultas = idConsultas;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCosto() {
        return Costo;
    }

    public void setCosto(String costo) {
        Costo = costo;
    }

    public String getConsulta() {
        return Consulta;
    }

    public void setConsulta(String consulta) {
        Consulta = consulta;
    }
}
