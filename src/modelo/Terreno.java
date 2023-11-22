package modelo;

public class Terreno extends Financiamento {

    private static final double ACRESCIMO_TERRENO = 0.02;
    private String tipoZona;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = getTaxaJurosAnual() / 12;
        int meses = getPrazoFinanciamento() * 12;
        return getValorImovel() * (taxaMensal * Math.pow(1 + taxaMensal, meses)) / (Math.pow(1 + taxaMensal, meses) - 1) * (1 + ACRESCIMO_TERRENO);
    }
    @Override
    public String toTexto() {
        return String.format("Terreno;%.2f;%d;%.2f;%s", getValorImovel(), getPrazoFinanciamento(), getTaxaJurosAnual(), tipoZona);
    }
}
