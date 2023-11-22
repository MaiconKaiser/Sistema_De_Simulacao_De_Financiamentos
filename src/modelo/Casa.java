package modelo;

public class Casa extends Financiamento {
    private static final double VALOR_SEGURO_CASA = 80.0;
    private double areaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double areaConstruida, double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = getTaxaJurosAnual() / 12;
        int meses = getPrazoFinanciamento() * 12;
        return getValorImovel() * (taxaMensal * Math.pow(1 + taxaMensal, meses)) / (Math.pow(1 + taxaMensal, meses) - 1) + VALOR_SEGURO_CASA;
    }
    @Override
    public String toTexto() {
        return String.format("Casa;%.2f;%d;%.2f;%.2f;%.2f", getValorImovel(), getPrazoFinanciamento(), getTaxaJurosAnual(), areaConstruida, tamanhoTerreno);
    }
}
