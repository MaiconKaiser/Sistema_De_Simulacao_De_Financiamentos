package util;

import java.util.Locale;
import java.util.Scanner;
import java.util.InputMismatchException;

public class InterfaceUsuario {

    private Scanner sc;

    public InterfaceUsuario() {
        Locale.setDefault(Locale.US);
        this.sc = new Scanner(System.in);
    }

//    public int pedirEscolhaMenu() {
//        int escolha;
//        do {
//            try {
//                System.out.print("Escolha: ");
//                escolha = sc.nextInt();
//                if (escolha < 0 || escolha > 4) {
//                    System.out.println("Opção inválida. Escolha entre 0 e 4.");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Entrada inválida. Insira um valor numérico inteiro.");
//                sc.next();
//                escolha = -1;
//            }
//        } while (escolha < 0 || escolha > 4);
//
//        return escolha;
//    }

    public String pedirTipoImovel() {
        System.out.println("Escolha o tipo de imóvel (C - Casa, A - Apartamento, T - Terreno): ");
        return sc.next().toUpperCase(); // Converte para maiúsculas para facilitar comparação
    }

    public double pedirValorImovel() {
        double valor = 0.0;
        boolean entradaValida = false;

        do {
            try {
                System.out.print("Digite o valor do imóvel: ");
                valor = sc.nextDouble();
                if (valor <= 0) {
                    System.out.println("Valor inválido. Insira um valor positivo.");
                } else {
                    entradaValida = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Insira um valor numérico.");
                sc.next();
            }
        } while (!entradaValida);

        return valor;
    }

    public int pedirPrazoFinanciamento() {
        int prazo = 0;
        boolean entradaValida = false;

        do {
            try {
                System.out.print("Digite o prazo do financiamento em anos: ");
                prazo = sc.nextInt();
                if (prazo <= 0) {
                    System.out.println("Prazo inválido. Insira um valor positivo.");
                } else {
                    entradaValida = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Insira um valor numérico inteiro.");
                sc.next();
            }
        } while (!entradaValida);

        return prazo;
    }

    public double pedirTaxaJuros() {
        double taxa = 0.0;
        boolean entradaValida = false;

        do {
            try {
                System.out.print("Digite a taxa de juros anual: ");
                taxa = sc.nextDouble();
                if (taxa <= 0) {
                    System.out.println("Taxa de juros inválida. Insira um valor positivo.");
                } else {
                    entradaValida = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Insira um valor numérico.");
                sc.next(); // Limpar o buffer do scanner
            }
        } while (!entradaValida);

        return taxa;
    }

    public double pedirAreaConstruida() {
        double area;
        do {
            System.out.print("Digite a área para construção: ");
            area = sc.nextDouble();
            if (area <= 0) {
                System.out.println("Área inválida. Insira um valor positivo.");
            }
        } while (area <= 0);
        return area;
    }

    public double pedirTamanhoTerreno() {
        double tamanho;
        do {
            System.out.print("Digite o tamanho do terreno: ");
            tamanho = sc.nextDouble();
            if (tamanho <= 0) {
                System.out.println("Tamanho do terreno inválido. Insira um valor positivo.");
            }
        } while (tamanho <= 0);
        return tamanho;
    }

    public int pedirNumeroVagasGaragem() {
        int numeroVagas;
        do {
            System.out.print("Digite o número de vagas desejadas na garagem: ");
            numeroVagas = sc.nextInt();
            if (numeroVagas <= 0) {
                System.out.println("Número de vagas inválido. Insira um valor positivo.");
            }
        } while (numeroVagas <= 0);
        return numeroVagas;
    }

    public int pedirNumeroAndar() {
        int numeroAndar;
        do {
            System.out.print("Digite o andar desejado: ");
            numeroAndar = sc.nextInt();
            if (numeroAndar <= 0) {
                System.out.println("Número do andar inválido. Insira um valor positivo.");
            }
        } while (numeroAndar <= 0);
        return numeroAndar;
    }

    public String pedirTipoZonaTerreno() {
        System.out.print("Digite o tipo de zona desejada para o Terreno: ");
        return sc.next();
    }
}