package Calculos;

import java.util.List;

public class Calculos {

    public static Double calcularPesoVetor(List<Integer> vetor, List<List<Double>> matrizDistancia) {
        int tam = vetor.size();
        Double distancia = 0.0;
        for (int i = 0; i < tam; i++) {
            if (i == tam - 1) {
                distancia += valorPosicao(vetor.get(i) -1, vetor.get(0) -1, matrizDistancia);
            } else {
                distancia += valorPosicao(vetor.get(i) -1, vetor.get(i + 1) -1, matrizDistancia);
            }
        }

        return distancia;
    }

    public static Double calculoDistanciaEuclidiana(List<List<Integer>> mat, int i, int j) {
        double x1 = mat.get(i).get(0);
        double y1 = mat.get(i).get(1);
        double x2 = mat.get(j).get(0);
        double y2 = mat.get(j).get(1);

        return Math.sqrt((Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
    }

    public static Double valorPosicao(int i, int j, List<List<Double>> matriz) {
        if (i > j) {
            return matriz.get(j).get(i);
        }

        return matriz.get(i).get(j);
    }
}
