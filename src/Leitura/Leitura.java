package Leitura;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leitura {

    public static List<List<Integer>> mat() throws IOException {
        FileInputStream file = new FileInputStream("arquivo.txt");

        int k = file.read();

        List<List<Integer>> mat = new ArrayList<>();

        while (k != -1) {

            while (k != 32) {

                if (k == -1) {
                    break;
                }
                k = file.read();
            }
            k = file.read();
            List<Integer> colunaMatriz = new ArrayList<>();
            List<Integer> aux = new ArrayList<>();

            while (k != 32) {
                aux.add((k - 48));
                k = file.read();
            }

            Integer distancia = valorArquivo(aux.size() - 1, aux);
            colunaMatriz.add(distancia);

            k = file.read();

            aux = new ArrayList<>();
            while (k != 32 && k != 13 && k != -1) {
                aux.add((k - 48));
                k = file.read();
            }

            distancia = valorArquivo(aux.size() - 1, aux);
            colunaMatriz.add(distancia);

            mat.add(colunaMatriz);
            k = file.read();
            k = file.read();
            if (k == -1) {
                break;
            }
        }

        return mat;
    }

    public static Integer valorArquivo(int tamanho, List<Integer> lista) {
        double distancia = 0;

        for (Integer listas : lista) {
            double exp = 10;
            exp = Math.pow(exp, tamanho);
            distancia += (exp * listas.doubleValue());
            tamanho--;
        }

        return (int) distancia;
    }
}


