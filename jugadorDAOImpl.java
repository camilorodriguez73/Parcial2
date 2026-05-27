{

    private Jugador mapearJugador(ResultSet rs) throws SQLException {
        Jugador j = new Jugador();
        j.setId(rs.getInt("id"));
        j.setNombre(rs.getString("nombre"));
        j.setPosicion(rs.getString("posicion"));
        j.setEdad(rs.getInt("edad"));
        j.setNacionalidad(rs.getString("nacionalidad"));
        j.setValorMercado(rs.getDouble("valor_mercado"));
        j.setEquipoId(rs.getInt("equipo_id"));
        return j;
    }

    @Override
    public void agregar(Jugador jugador) {
        String sql = "INSERT INTO jugadores (nombre, posicion, edad, nacionalidad, valor_mercado, equipo_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getPosicion());
            ps.setInt(3, jugador.getEdad());
            ps.setString(4, jugador.getNacionalidad());
            ps.setDouble(5, jugador.getValorMercado());
            ps.setInt(6, jugador.getEquipoId());

            ps.executeUpdate();
            System.out.println(">> Jugador agregado exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al agregar jugador: " + e.getMessage());
        }
    }

    @Override
    public Jugador consultarPorId(int id) {
        String sql = "SELECT * FROM jugadores WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearJugador(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar jugador: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Jugador> consultarTodos() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearJugador(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar jugadores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Jugador> consultarPorEquipo(int equipoId) {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores WHERE equipo_id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, equipoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearJugador(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar por equipo: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Jugador> filtrarPorValorMinimo(double valorMinimo) {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores WHERE valor_mercado >= ? ORDER BY valor_mercado DESC";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, valorMinimo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearJugador(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar por valor: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Jugador> filtrarPorPosicion(String posicion) {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores WHERE posicion LIKE ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + posicion + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearJugador(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar por posición: " + e.getMessage());
        }
        return lista;
    }
}
