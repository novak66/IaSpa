package SA;

import Calculos.Calculos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SA {

    private static final double e = 2.71;

    public static Double diferencaSolucoes(List<Integer> vetor, List<Integer> vetorVizinho, List<List<Double>> mat) {
        Double primeiro = Calculos.calcularPesoVetor(vetor, mat);
        Double segundo = Calculos.calcularPesoVetor(vetorVizinho, mat);

        return segundo - primeiro;
    }

    public static List<Integer> gerarSolucaoInicial(int qtdCidades) {

        List<Integer> vetorInicial = new ArrayList<>();

        while (vetorInicial.size() < qtdCidades) {
            Random random = new Random();
            Integer numero = random.nextInt(qtdCidades) + 1;

            if (!numeroJaAdicionado(vetorInicial, numero)) {
                vetorInicial.add(numero);
            }
        }

        return vetorInicial;
    }

    public static Double inicio(List<List<Double>> matriz) throws IOException {
        Writer file = new FileWriter("teste.txt");
        List<Integer> vetorInicial = gerarSolucaoInicial(matriz.size());

        Random random = new Random();
        double tempInicial = 40;
        double temp = tempInicial;
        double tempFinal = 4;
        int max = 500000;

        Double valor =  Calculos.calcularPesoVetor(vetorInicial, matriz);
        file.write(String.valueOf(valor.intValue()));
        file.write("\n");

        List<Integer> vetorVizinho = new ArrayList<>(vetorInicial);
        for (int i = 0; i < max; i++) {
            Integer qtdPerturbacoes = random.nextInt(4) + 1;

            for (int j = 0; j < qtdPerturbacoes; j++) {
                int trocaI = random.nextInt(vetorInicial.size() - 1);
                int trocaJ = trocaI;

                while (trocaJ == trocaI) {
                    trocaJ = random.nextInt(vetorInicial.size() - 1);
                }

                Integer aux = vetorVizinho.get(trocaI);

                vetorVizinho.set(trocaI, vetorVizinho.get(trocaJ));
                vetorVizinho.set(trocaJ, aux);
            }

            if (primeiroMaior(vetorInicial, vetorVizinho, matriz)) {
                vetorInicial = new ArrayList(vetorVizinho);
            } else {
                Double diferenca = diferencaSolucoes(vetorInicial, vetorVizinho, matriz);

                double probabilidadeAceitacao = Math.pow(e, ((-diferenca) / temp));
                double aleatorio = random.nextDouble();

                if (aleatorio <= probabilidadeAceitacao) {
                    vetorInicial = new ArrayList<>(vetorVizinho);
                }

                temp = tempInicial - ((i+1)*((tempInicial-tempFinal)/max));
            }
            valor =  Calculos.calcularPesoVetor(vetorInicial, matriz);

            file.write(String.valueOf(valor.intValue()));
            file.write("\n");
        }

        return Calculos.calcularPesoVetor(vetorInicial, matriz);
    }

    public static Boolean numeroJaAdicionado(List<Integer> vetorInicial, Integer numero) {
        for (Integer vetor : vetorInicial) {
            if (vetor.equals(numero)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean primeiroMaior(List<Integer> vetor, List<Integer> vetorVizinho, List<List<Double>> mat) {
        Double primeiro = Calculos.calcularPesoVetor(vetor, mat);
        Double segundo = Calculos.calcularPesoVetor(vetorVizinho, mat);

        if (primeiro > segundo) {
            return true;
        }
        return false;
    }
}
