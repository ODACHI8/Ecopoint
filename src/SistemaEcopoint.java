import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEcopoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        // Criar os resíduos e pontos de coleta
        List<Residuo> todosResiduos = new ArrayList<>();
        todosResiduos.add(new Residuo("Papel", 1));
        todosResiduos.add(new Residuo("Papelão", 1.25));
        todosResiduos.add(new Residuo("Vidro", 1));
        todosResiduos.add(new Residuo("Pet", 1.5));
        todosResiduos.add(new Residuo("Plástico", 2));
        todosResiduos.add(new Residuo("Metal", 2.5));
        todosResiduos.add(new Residuo("Eletrônico", 3));

        List<PontoDeColeta> pontosDeColeta = new ArrayList<>();
        pontosDeColeta.add(new PontoDeColeta("ASCAMARES", " Rua Henrique Dias - Centro", List.of("papel", "papelão", "embalagens Pet", "plástico")));
        pontosDeColeta.add(new PontoDeColeta("EXTRA SUPERMERCADO", " Av. Presidente Kennedy", List.of("papelão", "plástico", "alumínio", "aço", "vidro")));
        pontosDeColeta.add(new PontoDeColeta("PÃO DE AÇÚCAR", " Av. Dom Severino com Homero Castelo Branco – Jóquei Clube", List.of("celulares", "papelão", "plástico", "alumínio", "aço", "vidro")));
        pontosDeColeta.add(new PontoDeColeta("PROJETO LIXO CRIATIVO", " Av. Roraima, 2100 - (Próximo ao Diário do Povo) - Zona Norte – Aeroporto", List.of("papel", "papelão", "alumínio", "plástico", "metal", "aço", "vidro")));
        pontosDeColeta.add(new PontoDeColeta("SUSTENTARE", " Rua E - Bloco 83/84 (Acesso pela farmácia Coelho) - Zona Sul - Distrito Industrial", List.of("papel", "papelão", "alumínio", "plástico", "metal", "aço", "vidro")));
        pontosDeColeta.add(new PontoDeColeta("LAR DA ESPERANÇA", " Av. Goiás", List.of("papel")));

        Usuario usuarioAtual = new Usuario("João", "senha123");

        while (executando) {
            System.out.println("=========================================");
            System.out.println("|                                       |");
            System.out.println("|     Ecopoint - Sua solução em         |");
            System.out.println("|           sustentabilidade            |");
            System.out.println("|                                       |");
            System.out.println("=========================================");
            System.out.println("|                                       |");
            System.out.println("|     1. Reciclar                       |");
            System.out.println("|     2. Minha pontuação                |");
            System.out.println("|     3. Sair                           |");
            System.out.println("|                                       |");
            System.out.println("=========================================");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    // Reciclar
                    reciclar(scanner, todosResiduos, pontosDeColeta, usuarioAtual);
                    break;
                case 2:
                    // Mostrar pontuação
                    mostrarPontuacao(scanner, usuarioAtual);
                    break;
                case 3:
                    // Finalizar
                    System.out.println("Obrigado por usar o Ecopoint! Até a próxima.");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                    break;
            }
        }

        scanner.close();
    }

    private static void reciclar(Scanner scanner, List<Residuo> residuos, List<PontoDeColeta> pontosDeColeta, Usuario usuarioAtual) {
        boolean reciclando = true;

        while (reciclando) {
            System.out.println("=========================================");
            System.out.println("|               RECICLAR                |");
            System.out.println("=========================================");
            System.out.println("Escolha um ponto de coleta:");
            for (int i = 0; i < pontosDeColeta.size(); i++) {
                System.out.println((i + 1) + " - " + pontosDeColeta.get(i).getNome() + pontosDeColeta.get(i).getEndereco());
            }
            System.out.print("Sua escolha: ");
            int escolhaPonto = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (escolhaPonto < 1 || escolhaPonto > pontosDeColeta.size()) {
                System.out.println("Escolha inválida. Tente novamente.");
                continue;
            }

            PontoDeColeta pontoSelecionado = pontosDeColeta.get(escolhaPonto - 1);

            // Escolher resíduo
            System.out.println("\nEscolha o resíduo:");
            for (int i = 0; i < residuos.size(); i++) {
                System.out.println((i + 1) + " - " + residuos.get(i).getNome() + " (Ponto por kg: " + residuos.get(i).calcularPontuacao(1) + ")");
            }
            System.out.print("Sua escolha: ");
            int escolhaResiduo = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (escolhaResiduo < 1 || escolhaResiduo > residuos.size()) {
                System.out.println("Escolha inválida. Tente novamente.");
                continue;
            }

            Residuo residuoSelecionado = residuos.get(escolhaResiduo - 1);

            // Quantidade
            System.out.print("Quantos Kg você deseja reciclar? ");
            double quantidadeKg = 0;

            while (true) {
                String input = scanner.nextLine().trim().replace(",", ".");
                try {
                    quantidadeKg = Double.parseDouble(input);
                    if (quantidadeKg <= 0) {
                        System.out.println("Por favor, insira um valor valido.");
                        continue;
                    }
                    break; // Sai do loop se a entrada for válida
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número válido (use ponto como separador decimal).");
                }
            }

            // Reciclar
            usuarioAtual.reciclar(residuoSelecionado, quantidadeKg);

            // Perguntar se deseja continuar reciclando
            System.out.print("Mais alguma coisa para reciclar? (s/n): ");
            String resposta = scanner.nextLine();
            reciclando = resposta.equalsIgnoreCase("s"); // Se resposta for "s", continua reciclando; caso contrário, termina o loop
        }

        System.out.println("Voltando ao menu principal...");
    }

    private static void mostrarPontuacao(Scanner scanner, Usuario usuarioAtual) {
        System.out.println("=========================================");
        System.out.println("|           MINHA PONTUAÇÃO             |");
        System.out.println("=========================================");
        System.out.println("Você está no nível " + usuarioAtual.getNivel());
        System.out.println("Total de pontos: " + usuarioAtual.getPontos());
        System.out.println("=========================================");

        System.out.print("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine(); // Espera o usuário pressionar Enter para retornar ao menu principal
    }
}
