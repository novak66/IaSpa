package Construtor;

import Calculos.Calculos;

import java.util.ArrayList;
import java.util.List;

public class Matriz {

    public static List<List<Double>> construirMatrizDistancia (List<List<Integer>> mat) {
        int tam = mat.size();

        List<List<Double>> matrizDistancia = new ArrayList<>();

        for(int i=0; i<tam; i++) {
            List<Double> colunaMat = new ArrayList<>();
            for(int j=0;j<tam; j++) {
                if(j < i) {
                    colunaMat.add(-1.0);
                } else {
                    if(i == j) {
                        colunaMat.add(0.0);
                    } else {
                        colunaMat.add(Calculos.calculoDistanciaEuclidiana(mat, i, j));
                    }
                }
            }
            matrizDistancia.add(colunaMat);
        }

        return matrizDistancia;
    }

}
