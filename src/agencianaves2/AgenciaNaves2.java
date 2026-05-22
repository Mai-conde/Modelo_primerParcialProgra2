/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package agencianaves2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author maite modifico para probar
 * sigo probando
 * mas y mas pruebas 
 * 
 */


// ACLARACION, TODOS LOS METODOS QUE USO EN EL MENU ESTAN DEBAJO.
public class AgenciaNaves2 {

    public enum tipoMision { CARTOGRAFIA, INVESTIGACION, CONTACTO}
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorExpediciones sistema = new GestorExpediciones();
        
        int opcion = 0;

        // 2. Bucle principal del programa
        do {
            System.out.println("\n=========== MENÚ PRINCIPAL ===========");
            System.out.println("1. Agregar nave");
            System.out.println("2. Mostrar todas las naves");
            System.out.println("3. Iniciar exploracion");
            System.out.println("4. Mostrar naves ordenadas por nombre");
            System.out.println("5. Mostrar naves por año de lanzamiento (desc)");
            System.out.println("6. Mostrar naves ordenadas por tripulacion (desc)");
            System.out.println("7. Salir del sistema");
            System.out.print("Seleccione una opción (1-7): ");

            try {
                //convierto a entero
                opcion = Integer.parseInt(scanner.nextLine().trim());

                //Estructura de decisión
                switch (opcion) {
                    case 1:
                        menuAgregarNave(sistema, scanner); 
                        break;
                        
                    case 2:
                        System.out.println("\n---------- NAVES REGISTRADAS -----------");
                        sistema.mostrarNaves();
                        break;
                        
                    case 3:
                        System.out.println("\n--------- INICIANDO EXPLORACIÓN ----------");
                            sistema.iniciarExploracion();
                            break;
                        
                    case 4:
                        System.out.println("\n------------ NAVES ORDENADAS POR NOMBRE ------------");
                        ordenarNavesPorNombre(sistema.getNaves());
                        break;
                        
                    case 5:
                        System.out.println("\n------------ NAVES ORDENADAS POR AÑO -------------");
                        ordenarNavesPorAñoDescendente(sistema.getNaves());
                        break;
                        
                    case 6:
                        System.out.println("\n----------- NAVES ORDENADAS POR TRIPULACIÓN -------------");
                        ordenarNavesPorTripulacionDescendente(sistema.getNaves());
                        break;
                        
                    case 7:
                        System.out.println("Finalizando la exploracion...");
                        break;
                        
                    default:
                        // Si ingresa un número como 8 o 99
                        System.out.println("Error: Opción inválida. Por favor, seleccione un número del 1 al 7.");
                }

            } catch (NumberFormatException e) {
                // Si el usuario ingresa letras, símbolos o deja vacío
                System.out.println("Error de formato: Debe ingresar un número entero válido.");
            }

        } while (opcion != 7);
        
        scanner.close();

    }
    

    
    // ------------------------ METODO DE AGREGAR NAVES EN EL MENU -------------------------------
    private static void menuAgregarNave(GestorExpediciones gestor, Scanner scanner) {
        boolean naveExitosa = false;

        // Bucle para pedir datos hasta que sean correctos
        while (!naveExitosa) {
            System.out.println("\n--- REGISTRAR NUEVA NAVE ---");
            
            int tipo = ValidarEnteroRango("Seleccione tipo (1. Exploracion, 2. Carguero, 3. Crucero): ", 1, 3, scanner);
            String nombre = ValidarString("Nombre de la nave: ", scanner);
            int tripulacion = ValidarEntero("Capacidad de tripulacion: ", scanner);
            int año = ValidarEnteroRango("Año de lanzamiento: ", 0, 9999, scanner);
            
            Nave nuevaNave = null; 

            // Instanciamos según el tipo de nave
            if (tipo == 1) {
                System.out.println("Misiones: 1. CARTOGRAFIA | 2. INVESTIGACION | 3. CONTACTO");
                int opcionMision = ValidarEnteroRango("Seleccione mision (1-3): ", 1, 3, scanner);
                tipoMision misionSeleccionada = null;
                
                switch(opcionMision) {
                    case 1: misionSeleccionada = tipoMision.CARTOGRAFIA; break;
                    case 2: misionSeleccionada = tipoMision.INVESTIGACION; break;
                    case 3: misionSeleccionada = tipoMision.CONTACTO; break;
                }
                
                nuevaNave = new NaveExploracion(misionSeleccionada, nombre, tripulacion, año);
                
            } else if (tipo == 2) {
                double carga = validarCapacidadCarguero(scanner);
                nuevaNave = new Carguero(carga, nombre, tripulacion, año);
                
            } else if (tipo == 3) {
                int pasajeros = ValidarEntero("Cantidad de pasajeros maximos: ", scanner);
                nuevaNave = new CruceloEstelar(pasajeros, nombre, tripulacion, año);
            }

            // ahora agregamos la nave si todo es correcto
            try {
                gestor.agregarNaves(nuevaNave);
                // si salio todo bien, modificamos la bandera
                naveExitosa = true; 
                
            } catch (NaveDuplicadaException e) {
                // Si la nave ya existía, el catch atrapa el error y avisa.
                System.out.println("Ya existe una nave con esas caracteristicas.");
            }
        }
    }

    // ----------------------------- MÉTODOS DE VALIDACIÓN ------------------------------
    
    public static String ValidarString(String mensaje, Scanner scanner) {
        String entrada = "";
        boolean esValido = false;

        while (!esValido) {
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("El campo no puede estar vacio. Por favor, escriba un texto valido.");
            } else {
                esValido = true; 
            }
        }
        return entrada; 
    }
    
    public static int ValidarEntero(String mensaje, Scanner scanner) {
        int numero = 0;
        boolean esValido = false;

        while (!esValido) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim(); 

            if (entrada.isEmpty()) {
                System.out.println("El campo no puede estar vacio. Ingrese un numero.");
                continue; 
            }

            try {
                numero = Integer.parseInt(entrada);
                esValido = true; 

            } catch (NumberFormatException e) {

                System.out.println("Error: Ingrese un numero valido (sin letras).");
            }
        }
        return numero; 
    
    }
    
    public static int ValidarEnteroRango(String mensaje, int min, int max, Scanner scanner) {
        int numero = 0;
        boolean esValido = false;

        while (!esValido) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim(); 

            if (entrada.isEmpty()) {
                System.out.println("El campo no puede estar vacio. Ingrese un numero.");
                continue; 
            }

            try {
                numero = Integer.parseInt(entrada);

                if (numero < min || numero > max) {
                    System.out.println("Valor fuera de rango. Debe estar entre " + min + " y " + max);
                } else {
                    esValido = true; 
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido (no se permiten letras ni caracteres).");
            }
        }
        return numero; 
    }

    public static double validarCapacidadCarguero(Scanner scanner) {
        double capacidad = 0;
        boolean esValido = false;

        while (!esValido) {
            System.out.print("Ingrese capacidad de carga (100 a 500): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("El campo no puede estar vacio.");
                continue; 
            }

            try {
                capacidad = Double.parseDouble(entrada);

                // Regla del enunciado: Ajuste automático a los límites
                if (capacidad < 100) {
                    System.out.println("Aviso: Valor muy bajo. Se ajustara a 100.");
                    capacidad = 100;
                } else if (capacidad > 500) {
                    System.out.println("Aviso: Valor muy alto. Se ajustara a 500.");
                    capacidad = 500;
                }

                esValido = true; 

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un valor numerico decimal.");
            }
        }
        return capacidad; 
    }
    
   // ------------------------- METODOS DE ORDENAMIENTO -----------------------
  
    public static void ordenarNavesPorNombre(ArrayList<Nave> listaOriginal) {
        if (listaOriginal == null || listaOriginal.isEmpty()) {
            System.out.println("No hay naves registradas para ordenar.");
            return;
        }
        ArrayList<Nave> copia = new ArrayList<>(listaOriginal);
        copia.sort(Comparator.comparing(Nave::getNombre));
        
        for (Nave n : copia) {
            System.out.println(n);
        }
    }

    public static void ordenarNavesPorAñoDescendente(ArrayList<Nave> listaOriginal) {
        if (listaOriginal == null || listaOriginal.isEmpty()) {
            System.out.println("No hay naves registradas para ordenar.");
            return;
        }
        ArrayList<Nave> copia = new ArrayList<>(listaOriginal);
        // Java usa automáticamente el compareTo.
        Collections.sort(copia);
        
        for (Nave n : copia) {
            System.out.println(n);
        }
    }
    
    public static void ordenarNavesPorTripulacionDescendente(ArrayList<Nave> listaOriginal) {
        if (listaOriginal == null || listaOriginal.isEmpty()) {
            System.out.println("No hay naves registradas para ordenar.");
            return;
        }
        ArrayList<Nave> copia = new ArrayList<>(listaOriginal);
        copia.sort(Comparator.comparingInt(Nave::getCapacidadTripulacion).reversed());
        
        for (Nave n : copia) {
            System.out.println(n);
        }
    }
    
    
}
