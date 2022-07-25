package SA;

import Calculos.Calculos;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class SA {

    private static final double e = 2.72;

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

    public static double primeiroGrafico(int i, double tempInicial, double tempFinal, int max) {
        return tempInicial - ((i+1)*((tempInicial-tempFinal)/max));
    }

    public static double melhorGrafico(int i, double tempInicial, double tempFinal, int max) {
        Double A = (Math.log(tempInicial/tempFinal));



        BigDecimal t = new BigDecimal(1);
        BigDecimal o = new BigDecimal(max);


        BigDecimal b = t.divide(o);

        double umSobreN = b.doubleValue();

        A*= umSobreN;


        return (tempInicial *(Math.pow(e, (((-1.0)*(A))*i))));
    }

    public static List<Double> inicio(List<List<Double>> matriz) throws IOException  {



        Writer file = new FileWriter("teste.txt");
        List<Integer> vetorInicial = gerarSolucaoInicial(matriz.size());

        Random random = new Random();
        double tempInicial = 12000;
        double temp = tempInicial;
        double tempFinal = 24;
        double media = 0;
        int max = 500000;

        List<Double> lista = new ArrayList<>();

        Double valor =  Calculos.calcularPesoVetor(vetorInicial, matriz);
        media += valor;
        file.write(String.valueOf(valor.intValue()));
        file.write("\n");

        List<Integer> vetorVizinho = new ArrayList<>(vetorInicial);
        for (int i = 0; i < max; i++) {
            lista.add(Calculos.calcularPesoVetor(vetorInicial, matriz));
            Integer qtdPerturbacoes = random.nextInt(4) + 1;

            vetorVizinho = new ArrayList<>(vetorInicial);
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

                double probabilidadeAceitacao = Math.pow(e, (((-1.0)*(diferenca)) / temp));
                double aleatorio = random.nextDouble();


                if (aleatorio <= probabilidadeAceitacao) {
                    vetorInicial = new ArrayList<>(vetorVizinho);
                }

                temp = primeiroGrafico(i, tempInicial, tempFinal, max);

            }
            valor =  Calculos.calcularPesoVetor(vetorInicial, matriz);
            media+= valor;
            file.write(String.valueOf(valor.intValue()));
            file.write("\n");
        }
        int total = lista.size();
        media = media/total;
        System.out.println(media);

        double somaValores = 0;

        for (double listas : lista) {
            somaValores+= Math.pow((media - listas), 2);
        }

        somaValores = somaValores/max;

        somaValores = Math.sqrt(somaValores);
        DecimalFormat fmt = new DecimalFormat("0.00");

        String desvioPadrao =  fmt.format(somaValores);

        System.out.println(desvioPadrao);

        return lista;
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