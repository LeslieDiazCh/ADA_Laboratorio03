package com.mycompany.adalaboratorio03;
public class DemoAckermann {
    
    public static void main(String[] args) {
        DemoAckermann demo = new DemoAckermann();
        demo.ejecutarDemo();
    }
    
    public void ejecutarDemo() {
        System.out.println("=== DEMOSTRACIÓN DE LA FUNCIÓN DE ACKERMANN ===\n");
        
        AckermannCalculadora calculator = new AckermannCalculadora();
        AnalizadorComplejidad analizador = new AnalizadorComplejidad();

        mostrarDefinicion();

        System.out.println("1. CASOS DE PRUEBA BÁSICOS:");
        ejecutarCasosPrueba(calculator);

        System.out.println("\n2. COMPARACIÓN DE IMPLEMENTACIONES:");
        compararImplementaciones(calculator);

        System.out.println("\n3. ANÁLISIS DE COMPLEJIDAD:");
        analizador.mostrarEjemplosComplejidad();

        System.out.println("\n4. LÍMITES PRÁCTICOS:");
        mostrarLimitesPracticos();

        System.out.println("\n5. TABLA DE VALORES CONOCIDOS:");
        calculator.mostrarValoresConocidos();
    }
    
    private void mostrarDefinicion() {
        System.out.println("DEFINICIÓN RECURSIVA:");
        System.out.println("La función de Ackermann A(m,n) se define como:");
        System.out.println();
        System.out.println("A(m,n) = ⎧ n + 1                si m = 0");
        System.out.println("         ⎨ A(m-1, 1)            si m > 0 y n = 0");
        System.out.println("         ⎩ A(m-1, A(m, n-1))    si m > 0 y n > 0");
        System.out.println();
    }
    
    private void ejecutarCasosPrueba(AckermannCalculadora calculator) {
        int[][] casos = {
            {0, 0}, {0, 1}, {0, 5},
            {1, 0}, {1, 1}, {1, 5},
            {2, 0}, {2, 1}, {2, 5},
            {3, 0}, {3, 1}, {3, 3},
            {4, 0}, {4, 1}
        };
        
        long[] resultadosEsperados = {
            1, 2, 6,
            2, 3, 7,
            3, 5, 13,
            5, 13, 61,
            13, 65533
        };
        
        System.out.println("   m   n   | Resultado    | Método");
        System.out.println("  ---------|--------------|----------");
        
        for (int i = 0; i < casos.length; i++) {
            int m = casos[i][0];
            int n = casos[i][1];
            
            try {
                long resultado = calculator.calcularAckermann(m, n);
                String metodo = determinarMetodo(m, n);
                
                System.out.printf("   %d   %d   | %-12d | %s%n", 
                    m, n, resultado, metodo);
                
                // Verificar resultado si está disponible
                if (i < resultadosEsperados.length) {
                    if (resultado != resultadosEsperados[i]) {
                        System.out.println("   ERROR: Resultado esperado " + resultadosEsperados[i]);
                    }
                }
                
            } catch (Exception e) {
                System.out.printf("   %d   %d   | ERROR        | %s%n", 
                    m, n, e.getMessage());
            }
        }
    }
    
    private void compararImplementaciones(AckermannCalculadora calculator) {
        int[][] casosComparacion = {
            {2, 5}, {3, 2}, {3, 3}
        };
        
        System.out.println("   Caso    | Recursivo (ms) | Iterativo (ms) | Llamadas/Ops");
        System.out.println("  ---------|----------------|----------------|-------------");
        
        for (int[] caso : casosComparacion) {
            int m = caso[0];
            int n = caso[1];
            
            try {
                long inicioRec = System.nanoTime();
                long resultadoRec = calculator.ackermannRecursivo(m, n);
                long tiempoRec = (System.nanoTime() - inicioRec) / 1_000_000;
                long llamadas = calculator.getContadorLlamadasRecursivas();

                long inicioIter = System.nanoTime();
                long resultadoIter = calculator.ackermannIterativo(m, n);
                long tiempoIter = (System.nanoTime() - inicioIter) / 1_000_000;
                long operaciones = calculator.getContadorOperacionesIterativas();
                
                System.out.printf("  A(%d,%d)   | %-14d | %-14d | %d/%d%n",
                    m, n, tiempoRec, tiempoIter, llamadas, operaciones);
                
                if (resultadoRec != resultadoIter) {
                    System.out.println("   DISCREPANCIA EN RESULTADOS");
                }
                
            } catch (Exception e) {
                System.out.printf("  A(%d,%d)   | ERROR          | ERROR          | %s%n",
                    m, n, e.getMessage());
            }
        }
    }
    
    private void mostrarLimitesPracticos() {
        System.out.println("LÍMITES RECOMENDADOS PARA CÁLCULO:");
        System.out.println(" m = 0: cualquier n (instantáneo)");
        System.out.println(" m = 1: cualquier n (instantáneo)");
        System.out.println(" m = 2: n ≤ 1000 (rápido)");
        System.out.println(" m = 3: n ≤ 10 (aceptable)");
        System.out.println(" m = 3: n ≤ 15 (lento pero factible)");
        System.out.println(" m = 4, n = 0: A(4,0) = 13");
        System.out.println(" m = 4, n = 1: A(4,1) = 65533 (límite práctico)");
        System.out.println(" m = 4, n ≥ 2: Prácticamente incalculable");
        System.out.println(" m ≥ 5: No recomendado");
        
        System.out.println("\nRAZONES DE LOS LÍMITES:");
        System.out.println("- A(4,2) tendría aproximadamente 19,729 dígitos");
        System.out.println("- Memoria requerida > memoria disponible en computadoras actuales");
        System.out.println("- Tiempo de cálculo > edad del universo");
    }
    
    private String determinarMetodo(int m, int n) {
        if (m <= 4 && n <= 10) {
            return "Optimizado";
        } else if (m <= 3) {
            return "Iterativo";
        } else {
            return "Recursivo";
        }
    }

    public void ejecutarBenchmarks() {
        System.out.println("=== BENCHMARKS DE RENDIMIENTO ===");
        
        AckermannCalculadora calculator = new AckermannCalculadora();
        
        int[][] casosBenchmark = {
            {2, 10}, {2, 100}, {2, 1000},
            {3, 5}, {3, 8}, {3, 10}
        };
        
        for (int[] caso : casosBenchmark) {
            int m = caso[0];
            int n = caso[1];
            
            System.out.printf("Benchmark A(%d,%d):%n", m, n);

            long tiempoTotal = 0;
            int ejecuciones = 5;
            
            for (int i = 0; i < ejecuciones; i++) {
                long inicio = System.nanoTime();
                try {
                    calculator.ackermannIterativo(m, n);
                    tiempoTotal += System.nanoTime() - inicio;
                } catch (Exception e) {
                    System.out.println("  Error: " + e.getMessage());
                    break;
                }
            }
            
            if (tiempoTotal > 0) {
                long tiempoPromedio = tiempoTotal / (ejecuciones * 1_000_000);
                System.out.printf("  Tiempo promedio: %d ms%n", tiempoPromedio);
            }
        }
    }
}