package com.mycompany.adalaboratorio03;
public class AnalizadorComplejidad {

    public void analizarComplejidad(int m, int n) {
        System.out.println("   COMPLEJIDAD TEMPORAL:");
        analizarComplejidadTemporal(m, n);
        
        System.out.println("\n   COMPLEJIDAD ESPACIAL:");
        analizarComplejidadEspacial(m, n);
        
        System.out.println("\n   CRECIMIENTO DE LA FUNCIÓN:");
        analizarCrecimiento(m, n);
        
        System.out.println("\n   RECOMENDACIONES:");
        darRecomendaciones(m, n);
    }
    
    private void analizarComplejidadTemporal(int m, int n) {
        String complejidad = obtenerComplejidadTemporal(m);
        System.out.println("   - Para m = " + m + ": " + complejidad);
        
        if (m == 0) {
            System.out.println("     Operaciones: O(1) - constante");
        } else if (m == 1) {
            System.out.println("     Operaciones: O(1) - constante");
        } else if (m == 2) {
            System.out.println("     Operaciones: O(n) - lineal");
        } else if (m == 3) {
            System.out.println("     Operaciones: O(2^n) - exponencial");
        } else {
            System.out.println("     Operaciones: No primitiva recursiva");
        }
    }
    
    private void analizarComplejidadEspacial(int m, int n) {
        if (m == 0) {
            System.out.println("   - Espacio: O(1) - constante");
        } else if (m == 1) {
            System.out.println("   - Espacio: O(1) - constante");  
        } else if (m == 2) {
            System.out.println("   - Espacio: O(n) - lineal (pila de recursión)");
        } else if (m == 3) {
            System.out.println("   - Espacio: O(A(m,n)) - proporcional al resultado");
        } else {
            System.out.println("   - Espacio: Extremadamente grande");
        }
    }
    
    private void analizarCrecimiento(int m, int n) {
        switch (m) {
            case 0:
                System.out.println("   - A(0,n) = n + 1 (función sucesor)");
                break;
            case 1: 
                System.out.println("   - A(1,n) = n + 2 (suma)");
                break;
            case 2:
                System.out.println("   - A(2,n) = 2n + 3 (multiplicación)");
                break;
            case 3:
                System.out.println("   - A(3,n) = 2^(n+3) - 3 (exponenciación)");
                break;
            case 4:
                System.out.println("   - A(4,n) = torre de exponentes de altura n+3");
                System.out.println("     (tetración: 2↑↑(n+3))");
                break;
            default:
                System.out.println("   - A(" + m + ",n) crece más rápido que cualquier");
                System.out.println("     función primitiva recursiva conocida");
        }
    }
    
    private String obtenerComplejidadTemporal(int m) {
        switch (m) {
            case 0:
                return "T(0,n) = O(1)";
            case 1:
                return "T(1,n) = O(1)";
            case 2:
                return "T(2,n) = O(n)";
            case 3:
                return "T(3,n) = O(2^n)";
            case 4:
                return "T(4,n) = O(2↑↑n) - tetración";
            default:
                return "T(" + m + ",n) - No primitiva recursiva";
        }
    }
    
    private void darRecomendaciones(int m, int n) {
        if (m == 0 || m == 1) {
            System.out.println("    Cálculo rápido y eficiente");
        } else if (m == 2 && n <= 1000) {
            System.out.println("    Cálculo factible");
        } else if (m == 3 && n <= 10) {
            System.out.println("    Cálculo posible pero lento para n > 10");
        } else if (m == 3 && n > 10) {
            System.out.println("    Cálculo muy lento, considere optimizaciones");
        } else if (m == 4 && n == 0) {
            System.out.println("    A(4,0) = 13 - calculable");
        } else if (m == 4 && n == 1) {
            System.out.println("    A(4,1) = 65533 - límite práctico");
        } else if (m == 4 && n >= 2) {
            System.out.println("    A(4," + n + ") es prácticamente incalculable");
            System.out.println("       Resultado tendría ~20,000+ dígitos");
        } else {
            System.out.println("    Valores no recomendados para cálculo práctico");
        }
    }

    public void mostrarEjemplosComplejidad() {
        System.out.println("\nEJEMPLOS DE CRECIMIENTO:");
        System.out.println("A(4,0) = 13");
        System.out.println("A(4,1) = 65,533");  
        System.out.println("A(4,2) = 2^65536 - 3 ≈ 10^19729 (20,000 dígitos)");
        System.out.println("A(5,0) = 65,533");
        System.out.println("A(5,1) ≈ 10^19729");
        System.out.println("A(6,0) ≈ 10^19729");
        System.out.println("\nPara comparación:");
        System.out.println("- Átomos en el universo observable ≈ 10^80");
        System.out.println("- A(4,2) tiene más dígitos que átomos en el universo");
    }
    
    public void estimarTiempos(int m, int n) {
        System.out.println("ESTIMACIÓN DE TIEMPOS DE EJECUCIÓN:");
        
        if (m <= 3 && n <= 10) {
            System.out.println("- Recursivo: < 1 segundo");
            System.out.println("- Iterativo: < 1 segundo");
        } else if (m == 3 && n <= 15) {
            System.out.println("- Recursivo: 1-10 segundos");
            System.out.println("- Iterativo: < 1 segundo");
        } else if (m == 4 && n <= 1) {
            System.out.println("- Cualquier método: < 1 segundo");
        } else {
            System.out.println("- Tiempo: Prohibitivamente largo");
            System.out.println("- Recomendación: No calcular");
        }
    }
}
