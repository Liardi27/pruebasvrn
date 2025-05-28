package excepcionycoleccion;
//Hecho Por Cristina Cichon Klacza
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Cliente {
	
	private String nombre;
	private String dni;
	private String telefono;
	private boolean activo;
	private static int contadorAlta = 0;
	private static int contadorActivo = 0;
	public static ArrayList<Cliente> listaClientes = new ArrayList<>();
	public static LinkedHashMap<String, Integer> clientesDni = new LinkedHashMap<>();
	
	
	public Cliente(String nombre, String dni, String telefono) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.activo = true;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public static int getContadorAlta() {
		return contadorAlta;
	}

	public void setContadorAlta(int contadorAlta) {
		this.contadorAlta = contadorAlta;
	}

	public static int getContadorActivo() {
		return contadorActivo;
	}

	public void setContadorActivo(int contadorActivo) {
		this.contadorActivo = contadorActivo;
	}
	
	@Override
	public String toString(){
		return "Nombre: " + getNombre() + " Teléfono: " + getTelefono() + " DNI: " + getDni();
	}

	public static void anyadirListaCliente(Cliente cliente1) {
		listaClientes.add(cliente1);
		clientesDni.put(cliente1.getDni(), listaClientes.size() - 1);
		contadorAlta++;
		contadorActivo++;
	}
	

	public static void darDeBaja(){
		contadorActivo--;
	}
	
	
}
