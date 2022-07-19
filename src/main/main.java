package main;

import Calculos.Calculos;
import Construtor.Matriz;
import Leitura.Leitura;
import SA.SA;

import java.io.IOException;
import java.util.List;

public class main {

    public static void main(String args[]) throws IOException {
        List<List<Integer>> mat = Leitura.mat();

        List<List<Double>> matrizDistancia = Matriz.construirMatrizDistancia(mat);


        Double valorFinal = SA.inicio(matrizDistancia);

        System.out.println(valorFinal);
    }
}
