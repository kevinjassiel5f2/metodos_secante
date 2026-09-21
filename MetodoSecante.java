import java.util.Locale;

public class MetodoSecante {

    // Función del modelo matemático del problema
    public static double f(double h) {
        // Expresión repetida: (10/9 * h) - 1
        double termino = (10.0 / 9.0) * h - 1.0;
        
        // arcsin(termino) + termino * sqrt(1 - termino^2) - 1.100144
        return Math.asin(termino) + termino * Math.sqrt(1.0 - Math.pow(termino, 2)) - 1.100144;
    }

    public static void main(String[] args) {
        // Aseguramos que los números usen punto (.) como separador decimal
        Locale.setDefault(Locale.US);

        // 1. Datos iniciales proporcionados por el problema
        double x1 = 0.3;
        double x2 = 1.7;
        double error = 0.00001;
        int totalCalculos = 50;

        // 2. Impresión de la Pantalla de Inicio / Encabezado
        System.out.println("=========================================================================");
        System.out.println("                INSTITUTO TECNOLÓGICO DE CULIACÁN");
        System.out.println("                     INGENIERÍA EN SISTEMAS");
        System.out.println("                        MÉTODOS NUMÉRICOS");
        System.out.println("          Solución de Ecuaciones - Método de la Secante");
        System.out.println("=========================================================================");
        System.out.println("Pregunta: Calcular la altura h para llenar el 85% del tanque cisterna.");
        System.out.println("Modelo Matemático: f(h) = arcsen(10/9*h - 1) + (10/9*h - 1)*(sqrt(1 - (10/9*h - 1)^2)) - 1.100144\n");

        // Encabezado de la tabla iterativa
        System.out.printf("%-4s | %-9s | %-10s | %-9s | %-10s | %-9s | %-10s%n", 
                          "Nc", "X1", "F(X1)", "X2", "F(X2)", "X3", "F(X3)");
        System.out.println("-------------------------------------------------------------------------");

        // Variables de control del ciclo
        int nc = 1;
        double x3 = 0.0;
        double fx3 = 0.0;
        boolean seEncontroRaiz = false;

        // 3. Proceso iterativo del Método de la Secante
        while (nc <= totalCalculos) {
            // Evaluamos X1 y X2 en la función
            double fx1 = f(x1);
            double fx2 = f(x2);

            // Aplicamos la fórmula general del método de la secante para hallar X3
            x3 = x1 - (((x1 - x2) * fx1) / (fx1 - fx2));
            
            // Evaluamos X3 en la función
            fx3 = f(x3);

            // Imprimimos el renglón actual formateado a 6 decimales
            System.out.printf("%-4d | %-9.6f | %-10.6f | %-9.6f | %-10.6f | %-9.6f | %-10.6f%n", 
                              nc, x1, fx1, x2, fx2, x3, fx3);

            // Decisión: Checamos si el valor absoluto de F(X3) es menor o igual al Error
            if (Math.abs(fx3) <= error) {
                seEncontroRaiz = true;
                break; // Detener el proceso si ya encontramos la respuesta
            }

            // Si no se cumple la condición, preparamos el siguiente cálculo
            nc++;
            
            // Recorremos los puntos (el intervalo avanza)
            x1 = x2;
            x2 = x3;
        }

        System.out.println("-------------------------------------------------------------------------");
        
        // 4. Impresión del Resultado Final
        if (seEncontroRaiz) {
            System.out.printf("La Solución al Problema = %.6f metros%n", x3);
        } else {
            System.out.println("No se encontró la Raíz en " + totalCalculos + " cálculos.");
        }
    }
}