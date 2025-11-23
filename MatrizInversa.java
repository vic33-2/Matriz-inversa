import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatrizInversa {
    public static void main(String[] args) {
    String archivoEntrada = "matriz.txt";
        String archivoSalida = "matriz_inversa.txt";
        double[][] matriz = leerMatriz(archivoEntrada);

        if (matriz != null) {
            System.out.println("Matriz original:");
            imprimirMatriz(matriz);
            double[][] inversa = calcularInversa(matriz);
            if (inversa != null) {
                System.out.println("\n matriz inversa:");
                imprimirMatriz(inversa);

                escribirArchivo(inversa, archivoSalida);
                System.out.println("\n archivo generado exitosamente: " + archivoSalida);
            } else {
                System.out.println("\n No se puede calcular la matriz inversa (La matriz no corrsponde o determinante = 0).");
            }
        } else {
            System.out.println("Error al leer la matriz.");
        }
    }


    public static double[][] leerMatriz(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int filas = 0;


            while ((linea = br.readLine()) != null) {
                filas++;
            }
            double[][] matriz = new double[filas][];
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader(nombreArchivo));
            int i = 0;
            while ((linea = br2.readLine()) != null) {
                String[] valores = linea.trim().split(" ");
                matriz[i] = new double[valores.length];
                for (int j = 0; j < valores.length; j++) {
                    matriz[i][j] = Double.parseDouble(valores[j]);
                }
                i++;
            }
            br2.close();
            return matriz;

        } 
        
        catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
            return null;
        }
    }


    public static double[][] calcularInversa(double[][] matriz) {
        int n = matriz.length;
        if (n != matriz[0].length) {
            return null;
        }

        if (n == 2) {
            double det = (matriz[0][0] * matriz[1][1]) - (matriz[0][1] * matriz[1][0]);
            if (det == 0) return null;
            double[][] inversa = {
                { matriz[1][1] / det, -matriz[0][1] / det },
                { -matriz[1][0] / det, matriz[0][0] / det }
            };
            return inversa;
        }

        System.out.println("Usa una matriz de 2x2.");
        return null;
    }
public static void imprimirMatriz(double[][] matrizz) {
        for (double[] fila : matriz) {
            for (double valor : fila) {
                System.out.printf("%.2f ", valor);
            }
            System.out.println();
        }
    }
    public static void escribirArchivo(double[][] matriz, String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (double[] fila : matriz) {
                for (double valor : fila) {
                    bw.write(String.format("%.4f ", valor));
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }


}

