package modelo;
import java.io.*;

public abstract class Financiamento implements Serializable {

    public abstract String toTexto();

    public static Financiamento fromTexto(String linha) {
        String[] partes = linha.split(";");
        if (partes.length >= 3) {
            double valor = Double.parseDouble(partes[0]);
            int prazo = Integer.parseInt(partes[1]);
            double taxa = Double.parseDouble(partes[2]);

            if (linha.startsWith("Casa")) {
                if (partes.length >= 5) {
                    double areaConstruida = Double.parseDouble(partes[3]);
                    double tamanhoTerreno = Double.parseDouble(partes[4]);
                    return new Casa(valor, prazo, taxa, areaConstruida, tamanhoTerreno);
                }
            } else if (linha.startsWith("Apartamento")) {
                if (partes.length >= 4) {
                    int numeroVagasGaragem = Integer.parseInt(partes[3]);
                    int numeroAndar = Integer.parseInt(partes[4]);
                    return new Apartamento(valor, prazo, taxa, numeroVagasGaragem, numeroAndar);
                }
            } else if (linha.startsWith("Terreno")) {
                if (partes.length >= 4) {
                    String tipoZona = partes[3];
                    return new Terreno(valor, prazo, taxa, tipoZona);
                }
            }
        }
        return null;
    }

    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    public abstract double calcularPagamentoMensal();

    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * 12 * prazoFinanciamento;
    }
}
