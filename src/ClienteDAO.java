import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {
    private List<Cliente> clientes = new ArrayList<>();
    private int contadorId = 1;

    // CREAR
    public Cliente crear(String nombre, String email, String telefono) {
        Cliente nuevo = new Cliente(contadorId++, nombre, email, telefono);
        clientes.add(nuevo);
        return nuevo;
    }

    // LEER TODOS
    public List<Cliente> obtenerTodos() {
        return clientes;
    }

    // LEER UNO
    public Optional<Cliente> obtenerPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    // ACTUALIZAR
    public boolean actualizar(int id, String nombre, String email, String telefono) {
        Optional<Cliente> cliente = obtenerPorId(id);
        if (cliente.isPresent()) {
            cliente.get().setNombre(nombre);
            cliente.get().setEmail(email);
            cliente.get().setTelefono(telefono);
            return true;
        }
        return false;
    }

    // ELIMINAR
    public boolean eliminar(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
}