{

    // Atributos que corresponden a las columnas de la tabla 'jugadores'
    private int    id;
    private String nombre;
    private String posicion;
    private int    edad;
    private String nacionalidad;
    private double valorMercado;  // en millones de euros
    private int    equipoId;

    /**
     * Objeto Equipo asociado (opcional).
     * Se usa cuando queremos mostrar el nombre del equipo del jugador.
     * No tiene columna propia en la BD; se llena desde un JOIN.
     */
    private Equipo equipo;

    // -------------------------------------------------------
    // CONSTRUCTORES
    // -------------------------------------------------------

    public Jugador() {}

    /**
     * Constructor sin ID: usado al INSERTAR un nuevo jugador.
     */
    public Jugador(String nombre, String posicion, int edad,
                   String nacionalidad, double valorMercado, int equipoId) {
        this.nombre       = nombre;
        this.posicion     = posicion;
        this.edad         = edad;
        this.nacionalidad = nacionalidad;
        this.valorMercado = valorMercado;
        this.equipoId     = equipoId;
    }

    /**
     * Constructor completo: usado al LEER jugadores de la base de datos.
     */
    public Jugador(int id, String nombre, String posicion, int edad,
                   String nacionalidad, double valorMercado, int equipoId) {
        this.id           = id;
        this.nombre       = nombre;
        this.posicion     = posicion;
        this.edad         = edad;
        this.nacionalidad = nacionalidad;
        this.valorMercado = valorMercado;
        this.equipoId     = equipoId;
    }

    // -------------------------------------------------------
    // GETTERS Y SETTERS
    // -------------------------------------------------------

    public int getId()                      { return id; }
    public void setId(int id)               { this.id = id; }

    public String getNombre()               { return nombre; }
    public void setNombre(String n)         { this.nombre = n; }

    public String getPosicion()             { return posicion; }
    public void setPosicion(String p)       { this.posicion = p; }

    public int getEdad()                    { return edad; }
    public void setEdad(int e)              { this.edad = e; }

    public String getNacionalidad()         { return nacionalidad; }
    public void setNacionalidad(String n)   { this.nacionalidad = n; }

    public double getValorMercado()             { return valorMercado; }
    public void setValorMercado(double v)       { this.valorMercado = v; }

    public int getEquipoId()                { return equipoId; }
    public void setEquipoId(int e)          { this.equipoId = e; }

    public Equipo getEquipo()               { return equipo; }
    public void setEquipo(Equipo equipo)    { this.equipo = equipo; }

    // -------------------------------------------------------
    // toString(): muestra el jugador en consola con formato visual
    // -------------------------------------------------------
    @Override
    public String toString() {
        String nombreEquipo = (equipo != null) ? equipo.getNombre() : "Equipo ID: " + equipoId;
        return String.format(
                "  %-4d | %-25s | %-15s | %3d años | %-14s | €%7.2fM | %s",
                id, nombre, posicion, edad, nacionalidad, valorMercado, nombreEquipo
        );
    }

    /**
     * Versión detallada del toString para consultas individuales.
     */
    public String toStringDetallado() {
        String nombreEquipo = (equipo != null) ? equipo.getNombre() : "Equipo ID: " + equipoId;
        return String.format(
                "┌─────────────────────────────────────────┐%n" +
                        "│  ID:           %-5d                     │%n" +
                        "│  Nombre:       %-25s│%n" +
                        "│  Posición:     %-25s│%n" +
                        "│  Edad:         %d años                  │%n" +
                        "│  Nacionalidad: %-25s│%n" +
                        "│  Valor Mercado: €%-6.2f millones        │%n" +
                        "│  Equipo:       %-25s│%n" +
                        "└─────────────────────────────────────────┘",
                id, nombre, posicion, edad, nacionalidad, valorMercado, nombreEquipo
        );
    }
}
