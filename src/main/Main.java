package main;

import modelo.Financiamento;
import modelo.Casa;
import modelo.Apartamento;
import modelo.Terreno;
import util.InterfaceUsuario;
import java.io.*;
import java.util.ArrayList;

public class Main {
    private static final String ARQUIVO_TEXTO = "financiamentos.txt";
    private static final String ARQUIVO_SERIALIZADO = "financiamentos.ser";

    public static void salvarDadosTexto(ArrayList<Financiamento> financiamentos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_TEXTO))) {
            for (Financiamento financiamento : financiamentos) {
                writer.println(financiamento.toTexto());
            }
            System.out.println("Dados salvos em " + ARQUIVO_TEXTO);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados em arquivo de texto: " + e.getMessage());
        }
    }

    public static void carregarDadosTexto(ArrayList<Financiamento> financiamentos) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_TEXTO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Financiamento financiamento = Financiamento.fromTexto(linha);
                if (financiamento != null) {
                    financiamentos.add(financiamento);
                }
            }
            System.out.println("Dados carregados de " + ARQUIVO_TEXTO);
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados de arquivo de texto: " + e.getMessage());
        }
    }

    public static void salvarDadosSerializados(ArrayList<Financiamento> financiamentos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SERIALIZADO))) {
            oos.writeObject(financiamentos);
            System.out.println("Dados salvos em " + ARQUIVO_SERIALIZADO);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados em arquivo serializado: " + e.getMessage());
        }
    }

    public static void carregarDadosSerializados(ArrayList<Financiamento> financiamentos) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_SERIALIZADO))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                financiamentos.addAll((ArrayList<Financiamento>) obj);
            }
            System.out.println("Dados carregados de " + ARQUIVO_SERIALIZADO);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados de arquivo serializado: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        // Carregar dados dos arquivos, se existir

        //carregarDadosSerializados(financiamentos);
        //carregarDadosTexto(financiamentos);

        // Pedir dados do usuário para um financiamento
        System.out.println("Digite os dados para o próximo financiamento:");
        double valorImovel = interfaceUsuario.pedirValorImovel();
        int prazoFinanciamento = interfaceUsuario.pedirPrazoFinanciamento();
        double taxaJurosAnual = interfaceUsuario.pedirTaxaJuros();

        // Solicitar tipo de imóvel
        String tipoImovel = interfaceUsuario.pedirTipoImovel();

        switch (tipoImovel) {
            case "C":
                // Solicitar dados específicos para Casa
                System.out.println("*** ÁREA PARA CONSTRUÇÃO ***");
                double areaConstruidaCasa = interfaceUsuario.pedirAreaConstruida();

                System.out.println("*** TAMANHO DO TERRENO ***");
                double tamanhoTerrenoCasa = interfaceUsuario.pedirTamanhoTerreno();

                financiamentos.add(new Casa(valorImovel, prazoFinanciamento, taxaJurosAnual, areaConstruidaCasa, tamanhoTerrenoCasa));
                break;

            case "A":
                // Solicitar dados específicos para Apartamento
                System.out.println("*** VAGAS GARAGEM ***");
                int numeroVagasGaragem = interfaceUsuario.pedirNumeroVagasGaragem();

                System.out.println("*** ANDAR DESEJADO ***");
                int numeroAndar = interfaceUsuario.pedirNumeroAndar();

                financiamentos.add(new Apartamento(valorImovel, prazoFinanciamento, taxaJurosAnual, numeroVagasGaragem, numeroAndar));
                break;

            case "T":
                // Solicitar dados específicos para Terreno
                System.out.println("*** ZONA DESEJADA (exemplo: Residencial) ***");
                String tipoZonaTerreno = interfaceUsuario.pedirTipoZonaTerreno();

                financiamentos.add(new Terreno(valorImovel, prazoFinanciamento, taxaJurosAnual, tipoZonaTerreno));
                break;

            default:
                System.out.println("Opção inválida. Escolha entre C, A ou T.");
                break;
        }

        financiamentos.add(new Casa(300000, 35, 0.08, 120.0, 180.0));
        financiamentos.add(new Apartamento(250000, 30, 0.07, 1, 7));
        financiamentos.add(new Terreno(400000, 45, 0.12, "Comercial"));
        financiamentos.add(new Casa(200000, 25, 0.09, 100.0, 150.0));

        // Salvar o ArrayList de financiamentos
        salvarDadosSerializados(financiamentos);
        salvarDadosTexto(financiamentos);

        // Imprimir os dados
        System.out.println("Financiamentos:");

        for (int i = 0; i < financiamentos.size(); i++) {
            Financiamento financiamento = financiamentos.get(i);

            tipoImovel = "";

            if (financiamento instanceof Casa) {
                tipoImovel = "Casa";
            } else if (financiamento instanceof Apartamento) {
                tipoImovel = "Apartamento";
            } else if (financiamento instanceof Terreno) {
                tipoImovel = "Terreno";
            }

            System.out.printf("Financiamento %d - Tipo: %s, Valor do imóvel: R$ %.2f, Valor do financiamento: R$ %.2f, Valor pago mensalmente: R$ %.2f%n",
                    i + 1, tipoImovel, financiamento.getValorImovel(), financiamento.calcularTotalPagamento(), financiamento.calcularPagamentoMensal());
        }

        double totalImoveis = financiamentos.stream().mapToDouble(Financiamento::getValorImovel).sum();
        double totalFinanciamentos = financiamentos.stream().mapToDouble(Financiamento::calcularTotalPagamento).sum();

        System.out.printf("Total de todos os imóveis: R$ %.2f%n", totalImoveis);
        System.out.printf("Total de todos os financiamentos: R$ %.2f%n", totalFinanciamentos);
    }
}
