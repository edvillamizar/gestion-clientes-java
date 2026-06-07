import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class Menu {
    private ClienteDAO dao = new ClienteDAO();
    private Scanner scanner = new Scanner(System.in);

    public void mostrar() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n===== GESTIÓN DE CLIENTES =====");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Ver todos los clientes");
            System.out.println("3. Buscar cliente por ID");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> añadirCliente();
                case 2 -> verTodos();
                case 3 -> buscarPorId();
                case 4 -> actualizarCliente();
                case 5 -> eliminarCliente();
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void añadirCliente() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        Cliente c = dao.crear(nombre, email, telefono);
        System.out.println("Cliente creado: " + c);
    }

    private void verTodos() {
        List<Cliente> lista = dao.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void buscarPorId() {
        System.out.print("ID del cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Cliente> cliente = dao.obtenerPorId(id);
        cliente.ifPresentOrElse(
            c -> System.out.println("Encontrado: " + c),
            () -> System.out.println("Cliente no encontrado.")
        );
    }

    private void actualizarCliente() {
        System.out.print("ID del cliente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();
        System.out.print("Nuevo teléfono: ");
        String telefono = scanner.nextLine();
        boolean ok = dao.actualizar(id, nombre, email, telefono);
        System.out.println(ok ? "Cliente actualizado." : "Cliente no encontrado.");
    }

    private void eliminarCliente() {
        System.out.print("ID del cliente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean ok = dao.eliminar(id);
        System.out.println(ok ? "Cliente eliminado." : "Cliente no encontrado.");
    }
}