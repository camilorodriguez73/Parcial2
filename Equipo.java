{
    private int id;
    private String nombre;
    private String pais;
    private String entrenador;
    private double valorTotal;

    public Equipo() {}

    public Equipo(String nombre, String pais, String entrenador, double valorTotal) {
        this.nombre = nombre;
        this.pais = pais;
        this.entrenador = entrenador;
        this.valorTotal = valorTotal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getEntrenador() { return entrenador; }
    public void setEntrenador(String entrenador) { this.entrenador = entrenador; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Equipo: %-20s | País: %-12s | Entrenador: %-18s | Valor total: %,.0f €",
                id, nombre, pais, entrenador, valorTotal);
    }
}
