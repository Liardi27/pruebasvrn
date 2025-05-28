package excepcionycoleccion;
//Hecho Por Cristina Cichon Klacza
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		Cliente cliente2 = new Cliente("Dani", "123456789", "12345678A");
		Cliente cliente3 = new Cliente("Raul", "963852741", "96385274F");
		Cliente cliente4 = new Cliente("Adam", "852741963", "65842193N");
		Cliente.anyadirListaCliente(cliente2);
		Cliente.anyadirListaCliente(cliente3);
		Cliente.anyadirListaCliente(cliente4);
		
		Scanner sc = new Scanner (System.in);
		Scanner sc2 = new Scanner (System.in);
		Scanner sc3 = new Scanner (System.in);
		Scanner sc4 = new Scanner (System.in);
		Scanner sc5 = new Scanner (System.in);
		Scanner sc6= new Scanner (System.in);
		int opcion = 5;
		int pos = 0;
		boolean seguir = true;
		boolean error = false;
		boolean activo = false;
		boolean esta = false;
		boolean opcionV = false;
		String nombre= "";
		String telefono = "";
		String dni ="";



		do {

			error = false;
			activo = false;
			opcionV= false;
			Menu();
			while(!opcionV) {
				try {
					opcion = sc.nextInt();
					opcionV = true;
					if(opcion < 1 || opcion > 4) {
						System.out.println("Opción no válida, debes poner una opción del 1 - 4\n");
					}
				}catch(InputMismatchException a) {
					System.out.println("Opción no válida, debes poner una opción del 1 - 4\n");
					Menu();
					sc.next();
				}
				
			}
			
			
			switch(opcion) {

			case 1:
				System.out.println("Opción 1.- Dar de alta socio \n");
				try {
					System.out.print("Introduce tu nombre o apodo(Máximo 20 caracteres): ");
					nombre = sc2.nextLine();
					validarNombre(nombre);
				}catch(NombreTooLongException e) {
					System.out.println(e.getMessage());

					nombre = nombre.substring(0, 20);
					System.out.println("Se recorta el nombre a 20 caracteres");
				}
				try {
					System.out.print("Introduce tu teléfono(9 dígitos sin espacios): ");
					telefono = sc3.nextLine();
					validarTelefono(telefono);
				}catch(TelefonoInvalidFormatException a) {
					System.out.println(a.getMessage());
					error = true;
				}catch(Exception a) {
					System.out.println(a.getMessage());
					error = true;
				}

				try {
					System.out.print("Introduce tu DNI(8 Dígitos + Mayúscula): ");
					dni = sc4.nextLine();
					validarDNI(dni);
				}catch(DniInvalidFormatException a) {
					System.out.println(a.getMessage());
					error = true;
				}catch(Exception a) {
					System.out.println(a.getMessage());
					error = true;
				}

				if(error) {
					System.out.println("Entrada de socio abortada por datos incorrectos.");
				}else {
					Cliente cliente1 = new Cliente(nombre, dni, telefono);
					Cliente.anyadirListaCliente(cliente1);
				}
				break;
			case 2:
				System.out.println("Opción 2.- Ver datos socio \n");

				System.out.print("Introduce el DNI de la operación: ");


				try {
					System.out.print("Introduce tu DNI(8 Dígitos + Mayúscula): ");
					dni = sc5.nextLine();
					validarDNI(dni);
				}catch(DniInvalidFormatException a) {
					System.out.println(a.getMessage());
					error = true;
				}catch(Exception a) {
					System.out.println(a.getMessage());
					error = true;
				}
				if(!error) {
					System.out.println("DNI válido\n");

					try {
						pos = Cliente.clientesDni.get(dni);
						esta = true;
					}catch(NullPointerException a) {
						System.out.println("No hay socio con ese DNI\n");
						esta = false;
					}catch(Exception a) {
						System.out.println("No hay socio con ese DNI\n");
						esta = false;
					}

					if(esta){
						//Si está comprobar si está activo o no
						activo = Cliente.listaClientes.get(pos).isActivo();

						if(activo) {
							System.out.println(Cliente.listaClientes.get(pos).toString());
						}else {
							System.out.println("El socio pedido, no está activo");
						}
					}
				}


				break;
			case 3:
				System.out.println("Opción 3.- Dar de baja socio \n");

				try {
					System.out.print("Introduce tu DNI(8 Dígitos + Mayúscula): ");
					dni = sc6.nextLine();
					validarDNI(dni);
				}catch(DniInvalidFormatException a) {
					System.out.println(a.getMessage());
					error = true;
				}
				if(!error) {
					System.out.println("DNI válido\n");

					try {
						pos = Cliente.clientesDni.get(dni);
						esta = true;
					}catch(NullPointerException a) {
						System.out.println("No hay socio con ese DNI\n");
						esta = false;
					}catch(Exception a) {
						System.out.println("No hay socio con ese DNI\n");
						esta = false;
					}

					if(esta){
						//Si está comprobar si está activo o no
						activo = Cliente.listaClientes.get(pos).isActivo();

						if(activo) {
							Cliente.listaClientes.get(pos).setActivo(false);
							Cliente.darDeBaja();
							System.out.println("Se ha dado de baja\n");
						}else {
							System.out.println("Ya estaba dado de baja");
						}
					}

				}
				break;
			case 4:
				System.out.println("Opción 4.- Salir de la aplicación \n");
				System.out.println("Aplicación terminada");
				seguir = false;
				break;

			}




		}while(seguir);
		
		sc.close();
		sc2.close();
		sc3.close();
		sc4.close();
		sc5.close();
		sc6.close();
	}//Cierre del Main

	private static void Menu() {
		System.out.println("Socios de alta: " + Cliente.getContadorAlta() +"\n");
		System.out.println("Socios activos: "+ Cliente.getContadorActivo() +"\n");

		System.out.println("\t MENÚ");
		System.out.println("\t======");
		System.out.println("1.- Dar de alta socio");
		System.out.println("2.- Ver datos socio");
		System.out.println("3.- Dar de baja socio");
		System.out.println("4.- Salir del programa\n");
		System.out.print("Elige opción: ");
	}//Cierre del Menu

	//Excepciones
	
	public static String validarNombre(String nombre) throws NombreTooLongException {
		if (nombre.length() > 20) {
			throw new NombreTooLongException("Nombre demasiado largo");
		}
		return nombre;
	}

	public static void validarTelefono(String telefono) throws TelefonoInvalidFormatException {
		boolean infraccion = false;
		if (telefono.length() != 9  || telefono.contains(" ")) {

			infraccion = true;
		}else {
			for (int i = 0; i < 9; i++) {
				char c = telefono.charAt(i);

				if (c < '0' || c > '9') {
					infraccion = true;
				}
			}
		}
		if (infraccion) {
			throw new TelefonoInvalidFormatException("Teléfono incorrecto. Formato correcto nueve dígitos sin espacios: 123456789");
		}	

	}

	public static void validarDNI(String dni) throws DniInvalidFormatException {
		boolean infraccion = false;
		if (dni.length() != 9 || dni.contains(" ")) {
			infraccion = true;
		}else {
			for (int i = 0; i < 8; i++) {
				char c = dni.charAt(i);
				if (c < '0' || c > '9') {
					infraccion = true;
				}
			}

			char letra = dni.charAt(8);
			if (letra < 'A' || letra > 'Z') {
				infraccion = true;
			}
		}
		if (infraccion) {
			throw new DniInvalidFormatException("DNI incorrecto. Formato correcto ocho dígitos + letra mayúscula: 12345678A");
		}

	}
}
