package com.mycompany.adalaboratorio03;
import java.util.Scanner;

public class AckermannMain {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AckermannCalculadora calculator = new AckermannCalculadora();
        
        System.out.println("============================= FUNCIÓN DE ACKERMANN =============================");
        System.out.println();
        
        while (true) {
            try {
                System.out.print("Ingrese el valor de m (debe ser mayor o igual a 0, o pulse -1 para salir): ");
                int m = scanner.nextInt();
                
                if (m == -1) {
                    System.out.println("¡Gracias!");
                    break;
                }
                
                if (m < 0) {
                    System.out.println("Error: m debe ser mayor o igual a 0");
                    continue;
                }
                
                System.out.print("Ingrese el valor de n (debe ser mayor o igual a 0): ");
                int n = scanner.nextInt();
                
                if (n < 0) {
                    System.out.println("Error: n debe ser mayor o igual a 0");
                    continue;
                }

                if (m > 3 || (m == 3 && n > 10) || (m == 4 && n > 1)) {
                    System.out.println("ADVERTENCIA: Estos valores pueden tardar mucho tiempo o causar desbordamiento.");
                    System.out.print("¿Desea continuar? (s/n): ");
                    String respuesta = scanner.next();
                    if (!respuesta.toLowerCase().equals("s")) {
                        continue;
                    }
                }
                
                System.out.println("\n- - - - - - - - - - - R E S U L T A D O S - - - - - - - - - - - ");

                System.out.println("\n____1. IMPLEMENTACIÓN RECURSIVA:____");
                long inicioRecursivo = System.nanoTime();
                try {
                    long resultadoRecursivo = calculator.ackermannRecursivo(m, n);
                    long finRecursivo = System.nanoTime();
                    long tiempoRecursivo = finRecursivo - inicioRecursivo;
                    
                    System.out.println("   Resultado: A(" + m + ", " + n + ") = " + resultadoRecursivo);
                    System.out.println("   Tiempo de ejecución: " + (tiempoRecursivo / 1_000_000.0) + " ms");
                    System.out.println("   Llamadas recursivas: " + calculator.getContadorLlamadasRecursivas());
                } catch (StackOverflowError e) {
                    System.out.println("   Error: Desbordamiento de pila (stack overflow)");
                } catch (Exception e) {
                    System.out.println("   Error: " + e.getMessage());
                }

                System.out.println("\n____2. IMPLEMENTACIÓN ITERATIVA:____");
                long inicioIterativo = System.nanoTime();
                try {
                    long resultadoIterativo = calculator.ackermannIterativo(m, n);
                    long finIterativo = System.nanoTime();
                    long tiempoIterativo = finIterativo - inicioIterativo;
                    
                    System.out.println("   Resultado: A(" + m + ", " + n + ") = " + resultadoIterativo);
                    System.out.println("   Tiempo de ejecución: " + (tiempoIterativo / 1_000_000.0) + " ms");
                    System.out.println("   Operaciones realizadas: " + calculator.getContadorOperacionesIterativas());
                } catch (Exception e) {
                    System.out.println("   Error: " + e.getMessage());
                }
                System.out.println("\n____3. ANÁLISIS DE COMPLEJIDAD:____");
                AnalizadorComplejidad analizador = new AnalizadorComplejidad();
                analizador.analizarComplejidad(m, n);
                
                System.out.println("\n" + "=".repeat(50));
                
            } catch (Exception e) {
                System.out.println("Error en la entrada: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
        
        scanner.close();
    }
}