package dao;

import modelo.Equipo;
import java.util.List;

public interface EquipoDAO {
    void agregar(Equipo equipo);
    Equipo consultarPorId(int id);
    List<Equipo> consultarTodos();
}
