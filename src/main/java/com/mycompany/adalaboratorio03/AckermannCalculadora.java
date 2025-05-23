package com.mycompany.adalaboratorio03;
import java.util.Stack;

public class AckermannCalculadora {
    private long contadorLlamadasRecursivas;
    private long contadorOperacionesIterativas;
    
    public AckermannCalculadora() {
        this.contadorLlamadasRecursivas = 0;
        this.contadorOperacionesIterativas = 0;
    }

    public long ackermannRecursivo(int m, int n) {
        contadorLlamadasRecursivas = 0;
        return ackermannRecursivoAux(m, n);
    }
    
    private long ackermannRecursivoAux(int m, int n) {
        contadorLlamadasRecursivas++;
        if (contadorLlamadasRecursivas > 100_000_000) {
            throw new RuntimeException("Demasiadas llamadas recursivas - posible desbordamiento");
        }
        if (m == 0) {
            return n + 1;
        }
        if (n == 0) {
            return ackermannRecursivoAux(m - 1, 1);
        }
        return ackermannRecursivoAux(m - 1, (int) ackermannRecursivoAux(m, n - 1));
    }

    public long ackermannIterativo(int m, int n) {
        contadorOperacionesIterativas = 0;
        
        Stack<Integer> pila = new Stack<>();
        pila.push(m);
        long resultado = n;
        
        while (!pila.isEmpty()) {
            contadorOperacionesIterativas++;

            if (contadorOperacionesIterativas > 100_000_000) {
                throw new RuntimeException("Demasiadas operaciones - posible bucle infinito");
            }
            
            int mActual = pila.pop();
           
            if (mActual == 0) {
                resultado = resultado + 1;
            } else if (resultado == 0) {
                pila.push(mActual - 1);
                resultado = 1;
            } else {
                pila.push(mActual - 1);
                pila.push(mActual);
                resultado = resultado - 1;
            }
            if (resultado > Long.MAX_VALUE / 2) {
                throw new RuntimeException("Resultado demasiado grande - desbordamiento numérico");
            }
        }   
        return resultado;
    }
   
    public long ackermannOptimizado(int m, int n) {
        switch (m) {
            case 0:
                return n + 1;
            case 1:
                return n + 2;
            case 2:
                return 2 * n + 3;
            case 3:
                return (long) Math.pow(2, n + 3) - 3;
            case 4:
                if (n == 0) return 13;
                if (n == 1) return 65533;
                throw new RuntimeException("A(4, " + n + ") es demasiado grande para calcular");
            default:
                return ackermannIterativo(m, n);
        }
    }

    public long calcularAckermann(int m, int n) {
        if (m <= 4 && n <= 10) {
            return ackermannOptimizado(m, n);
        } else if (m <= 3) {
            return ackermannIterativo(m, n);
        } else {
            return ackermannRecursivo(m, n);
        }
    }

    public long getContadorLlamadasRecursivas() {
        return contadorLlamadasRecursivas;
    }
    
    public long getContadorOperacionesIterativas() {
        return contadorOperacionesIterativas;
    }

    public void mostrarValoresConocidos() {
        System.out.println("Algunos valores conocidos de la función de Ackermann:");
        System.out.println("A(0,0) = 1    A(0,1) = 2    A(0,2) = 3");
        System.out.println("A(1,0) = 2    A(1,1) = 3    A(1,2) = 4");
        System.out.println("A(2,0) = 3    A(2,1) = 5    A(2,2) = 7");
        System.out.println("A(3,0) = 5    A(3,1) = 13   A(3,2) = 29");
        System.out.println("A(4,0) = 13   A(4,1) = 65533");
        System.out.println("A(4,2) = 2^65536 - 3 (número con ~20,000 dígitos)");
    }
}
