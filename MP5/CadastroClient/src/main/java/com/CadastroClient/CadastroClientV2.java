package com.CadastroClient;

import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class CadastroClientV2 {
    private static final String SERVER_BASE_URL = "http://localhost:8080"; 
    private static final String SERVER_ENDPOINT = SERVER_BASE_URL + "/api/cadastro";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção: ");
            System.out.println("1. toList");
            System.out.println("2. Entrada");
            System.out.println("3. Saída");
            System.out.println("0. Sair");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                 toList();
                    break;
                case 2:
                    input();
                    break;
                case 3:
                    output();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void toList() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(SERVER_ENDPOINT + " toList", String.class);
        System.out.println(result);
    }

    private static void input() {
        Scanner inputData = new Scanner(System.in);

        System.out.println("ID da pessoa: ");
        int personId = inputData.nextInt();

        System.out.println("ID do produto: ");
        int productId = inputData.nextInt();

        System.out.println("Quantidade: ");
        int quantity = inputData.nextInt();

        System.out.println("Valor unitário: ");
        double price = inputData.nextDouble();

        RestTemplate restTemplate = new RestTemplate();

        System.out.println("Dados registrados com sucesso.");
    }

    private static void output() {
        Scanner inputData = new Scanner(System.in);

        System.out.println("ID da pessoa: ");
        int personId = inputData.nextInt();

        System.out.println("ID do produto: ");
        int productId = inputData.nextInt();

        System.out.println("Quantidade: ");
        int quantitye = inputData.nextInt();

        System.out.println("Valor unitário: ");
        double price = inputData.nextDouble();


        RestTemplate restTemplate = new RestTemplate();

        System.out.println("Dados lidos com sucesso.");
    }
}
