{
    private static final EquipoDAO equipoDAO = new EquipoDAOImpl();
    private static final JugadorDAO jugadorDAO = new JugadorDAOImpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean ejecutar = true;

        while (ejecutar) {
            System.out.println("\n=============================================");
            System.out.println("   SISTEMA DE MERCADO - CHAMPIONS LEAGUE     ");
            System.out.println("=============================================");
            System.out.println("1. Registrar nuevo Equipo");
            System.out.println("2. Registrar nuevo Jugador");
            System.out.println("3. Ver todos los Equipos");
            System.out.println("4. Ver todos los Jugadores");
            System.out.println("5. Buscar un Jugador por su ID");
            System.out.println("6. Mostrar Jugadores de un Equipo específico");
            System.out.println("7. Filtrar Jugadores por valor mínimo de mercado");
            System.out.println("8. Filtrar Jugadores por Posición de juego");
            System.out.println("9. Salir del Sistema");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuAgregarEquipo();
                case 2 -> menuAgregarJugador();
                case 3 -> listarEquipos();
                case 4 -> listarJugadores();
                case 5 -> buscarJugadorPorId();
                case 6 -> buscarJugadoresPorEquipo();
                case 7 -> filtrarJugadoresPorValor();
                case 8 -> filtrarJugadoresPorPosicion();
                case 9 -> {
                    System.out.println("Cerrando el sistema de gestión.");
                    ejecutar = false;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void menuAgregarEquipo() {
        System.out.println("\n--- REGISTRAR EQUIPO ---");
        System.out.print("Nombre del Equipo: ");
        String nombre = sc.nextLine();
        System.out.print("País: ");
        String pais = sc.nextLine();
        System.out.print("Nombre del Entrenador: ");
        String entrenador = sc.nextLine();
        System.out.print("Presupuesto / Valor de plantilla (€): ");
        double valor = leerDouble();

        Equipo nuevoEq = new Equipo(nombre, pais, entrenador, valor);
        equipoDAO.agregar(nuevoEq);
    }

    private static void menuAgregarJugador() {
        System.out.println("\n--- REGISTRAR JUGADOR ---");
        List<Equipo> equipos = equipoDAO.consultarTodos();
        if (equipos.isEmpty()) {
            System.out.println("Alerta: No hay equipos registrados en la BD. Cree uno primero.");
            return;
        }

        System.out.print("Nombre del Jugador: ");
        String nombre = sc.nextLine();
        System.out.print("Posición (Delantero, Centrocampista, Defensa, Portero): ");
        String posicion = sc.nextLine();
        System.out.print("Edad: ");
        int edad = leerEntero();
        System.out.print("Nacionalidad: ");
        String nacionalidad = sc.nextLine();
        System.out.print("Valor de mercado (€): ");
        double valor = leerDouble();

        System.out.println("Asigne un Equipo ingresando su ID:");
        for (Equipo eq : equipos) {
            System.out.println("  [" + eq.getId() + "] -> " + eq.getNombre());
        }
        System.out.print("ID del Equipo: ");
        int equipoId = leerEntero();

        if (equipoDAO.consultarPorId(equipoId) == null) {
            System.out.println("Error: El ID del equipo no existe. Registro cancelado.");
            return;
        }

        Jugador nuevoJug = new Jugador(nombre, posicion, edad, nacionalidad, valor, equipoId);
        jugadorDAO.agregar(nuevoJug);
    }

    private static void listarEquipos() {
        System.out.println("\n--- LISTA DE EQUIPOS EN UEFA CHAMPIONS LEAGUE ---");
        List<Equipo> lista = equipoDAO.consultarTodos();
        if (lista.isEmpty()) System.out.println("No hay registros.");
        else lista.forEach(System.out::println);
    }

    private static void listarJugadores() {
        System.out.println("\n--- LISTA TOTAL DE JUGADORES ---");
        List<Jugador> lista = jugadorDAO.consultarTodos();
        if (lista.isEmpty()) System.out.println("No hay registros.");
        else lista.forEach(System.out::println);
    }

    private static void buscarJugadorPorId() {
        System.out.print("\nIngrese el ID del jugador a consultar: ");
        int id = leerEntero();
        Jugador j = jugadorDAO.consultarPorId(id);
        if (j != null) {
            System.out.println("\nResultado:\n" + j);
        } else {
            System.out.println("Jugador no encontrado con el ID: " + id);
        }
    }

    private static void buscarJugadoresPorEquipo() {
        System.out.print("\nIngrese el ID del equipo para filtrar su plantilla: ");
        int equipoId = leerEntero();
        Equipo eq = equipoDAO.consultarPorId(equipoId);

        if (eq == null) {
            System.out.println("El equipo con ID " + equipoId + " no existe.");
            return;
        }

        System.out.println("\nPlantilla oficial de: " + eq.getNombre());
        List<Jugador> lista = jugadorDAO.consultarPorEquipo(equipoId);
        if (lista.isEmpty()) System.out.println("Este equipo no cuenta con jugadores registrados.");
        else lista.forEach(System.out::println);
    }

    private static void filtrarJugadoresPorValor() {
        System.out.print("\nMostrar jugadores con un valor mayor o igual a (€): ");
        double valorMin = leerDouble();
        System.out.println("\n--- JUGADORES TOP EN EL RANGO ---");
        List<Jugador> lista = jugadorDAO.filtrarPorValorMinimo(valorMin);
        if (lista.isEmpty()) System.out.println("Ningún jugador alcanza ese valor de mercado.");
        else lista.forEach(System.out::println);
    }

    private static void filtrarJugadoresPorPosicion() {
        System.out.print("\nIngrese la posición a buscar (Ej: Delantero, Centrocampista): ");
        String pos = sc.nextLine();
        System.out.println("\n--- JUGADORES EN LA POSICIÓN: " + pos.toUpperCase() + " ---");
        List<Jugador> lista = jugadorDAO.filtrarPorPosicion(pos);
        if (lista.isEmpty()) System.out.println("No se encontraron jugadores en esa posición.");
        else lista.forEach(System.out::println);
    }

    private static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Entrada inválida. Ingrese un número entero: ");
            sc.next();
        }
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }

    private static double leerDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Entrada inválida. Ingrese un valor numérico: ");
            sc.next();
        }
        double num = sc.nextDouble();
        sc.nextLine();
        return num;
    }
}
