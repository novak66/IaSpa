package main;

import Construtor.Matriz;
import Leitura.Leitura;
import SA.SA;

import java.io.IOException;
import java.util.List;

public class main {

    public static void main(String args[]) throws IOException {
        List<List<Integer>> mat = Leitura.mat();

        List<List<Double>> matrizDistancia = Matriz.construirMatrizDistancia(mat);

        List<Double> valorFinal = SA.inicio(matrizDistancia);
        int fim = valorFinal.size() -1;
        System.out.println(valorFinal.get(fim));

        var ex = new Grafico(valorFinal);
        ex.setVisible(true);
    }
}
