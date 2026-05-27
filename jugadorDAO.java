package dao;

import modelo.Jugador;
import java.util.List;

/**
  JugadorDAO
  Definir el CONTRATO de operaciones sobre la tabla 'jugadores'.
 */
public interface JugadorDAO {
    void agregar(Jugador jugador);
    Jugador consultarPorId(int id);
    List<Jugador> consultarTodos();
    List<Jugador> consultarPorEquipo(int equipoId);
    List<Jugador> filtrarPorValorMinimo(double valorMinimo);
    List<Jugador> filtrarPorPosicion(String posicion);
