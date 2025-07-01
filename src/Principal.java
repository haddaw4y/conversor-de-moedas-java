import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        try (Scanner leitura = new Scanner(System.in)) {
            while (true) {
                System.out.println("*************************************************");
                System.out.println("Bem-vindo(a) ao Conversor de Moedas!");
                System.out.println("1) Dólar =>> Real Brasileiro");
                System.out.println("2) Real Brasileiro =>> Dólar");
                System.out.println("3) Yuan Chinês =>> Real Brasileiro");
                System.out.println("4) Real Brasileiro =>> Yuan Chinês");
                System.out.println("5) Rublo Russo =>> Real Brasileiro");
                System.out.println("6) Real Brasileiro =>> Rublo Russo");
                System.out.println("7) Dólar =>> Yuan Chinês");
                System.out.println("8) Dólar =>> Rublo Russo");
                System.out.println("9) Sair");
                System.out.println("*************************************************");

                int opcao;
                // Bloco try-catch para garantir que o usuário digite um número para a opção
                try {
                    System.out.print("Escolha uma opção válida: ");
                    opcao = leitura.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\nERRO: Opção inválida! Por favor, digite apenas um dos números do menu.\n");
                    leitura.next(); // Limpa o buffer do scanner para evitar loop infinito
                    continue; // Pula para a próxima iteração do loop
                }

                if (opcao == 9) {
                    System.out.println("Obrigado por usar nosso conversor. Até logo!");
                    break;
                }

                if (opcao < 1 || opcao > 8) {
                    System.out.println("\nERRO: Opção inválida! Por favor, digite apenas um dos números do menu.\n");
                    continue;
                }

                double valor;
                // Bloco try-catch para garantir que o usuário digite um valor numérico para conversão
                try {
                    System.out.print("Digite o valor que deseja converter: ");
                    valor = leitura.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("\nERRO: Valor inválido! Por favor, digite um número (use a vírgula ',' para casas decimais).\n");
                    leitura.next(); // Limpa o buffer
                    continue; // Volta para o início do menu
                }


                String moedaOrigem = "";
                String moedaDestino = "";

                switch (opcao) {
                    case 1: moedaOrigem = "USD"; moedaDestino = "BRL"; break;
                    case 2: moedaOrigem = "BRL"; moedaDestino = "USD"; break;
                    case 3: moedaOrigem = "CNY"; moedaDestino = "BRL"; break;
                    case 4: moedaOrigem = "BRL"; moedaDestino = "CNY"; break;
                    case 5: moedaOrigem = "RUB"; moedaDestino = "BRL"; break;
                    case 6: moedaOrigem = "BRL"; moedaDestino = "RUB"; break;
                    case 7: moedaOrigem = "USD"; moedaDestino = "CNY"; break;
                    case 8: moedaOrigem = "USD"; moedaDestino = "RUB"; break;
                }

                try {
                    TaxaDeCambio taxa = buscarTaxaDeCambio(moedaOrigem, moedaDestino);
                    double valorConvertido = valor * taxa.conversion_rate;
                    System.out.printf("O valor de %.2f %s corresponde a %.2f %s%n%n",
                            valor, moedaOrigem, valorConvertido, moedaDestino);

                } catch (IOException | InterruptedException e) {
                    System.out.println("Erro ao buscar a taxa de câmbio: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                }
            }
        }
    }

    public static TaxaDeCambio buscarTaxaDeCambio(String moedaOrigem, String moedaDestino)
            throws IOException, InterruptedException {

        String suaChaveApi = "ec493e3e34843180333ea0f8";
        String endereco = "https://v6.exchangerate-api.com/v6/" + suaChaveApi + "/pair/" +
                moedaOrigem + "/" + moedaDestino;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), TaxaDeCambio.class);
    }
}
