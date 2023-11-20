package CadastroPOO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import CadastroPOO.model.PessoaFisica;
import CadastroPOO.model.PessoaJuridica;
import CadastroPOO.repository.PessoaFisicaRepository;
import CadastroPOO.repository.PessoaJuridicaRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class CadastroPooApplication 
{

    private static final Scanner scanner = new Scanner(System.in);
    private static final PessoaFisicaRepository pessoaFisicaRepository = new PessoaFisicaRepository();
    private static final PessoaJuridicaRepository pessoaJuridicaRepository = new PessoaJuridicaRepository();

    public static void main(String[] args) {

        PessoaFisicaRepository repositoryOne = new PessoaFisicaRepository();
        PessoaFisicaRepository repositoryTwo = new PessoaFisicaRepository();
        PessoaJuridicaRepository repositoryThree = new PessoaJuridicaRepository();
        PessoaJuridicaRepository repositoryFour = new PessoaJuridicaRepository();

        
        repositoryOne.add(new PessoaFisica(1, "Ana", "11111111111", 25));
        repositoryOne.add(new PessoaFisica(2, "Carlos", "2222222222", 52));

        
        try 
        {
            repositoryOne.createBIN("dados_pessoas_fisicas.ser");
            System.out.println("Dados da Pessoa Fisica salvos.");
        } 
        catch (IOException e) 
        {
            System.out.println("Erro ao gravar dados de Pessoa Fisica: " + e.getMessage());
        }

        try 
        {
            repositoryTwo.getBIN("dados_pessoas_fisicas.ser");
            System.out.println("Dados de Pessoa Fisica Recuperados.");
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Erro ao recuperar dados de Pessoa Fisica: " + e.getMessage());
        }

        
        ArrayList<PessoaFisica> pessoasFisicasResult = repositoryTwo.getAll();
        for (PessoaFisica pessoaFisica : pessoasFisicasResult) 
        {
            pessoaFisica.read();
        }


        repositoryThree.add(new PessoaJuridica(3, "XPTO Sales", "3333333333"));
        repositoryThree.add(new PessoaJuridica(4, "XPTO Solutions", "4444444444"));

    
        try 
        {
            repositoryThree.createBIN("dados_pessoas_juridicas.ser");
            System.out.println("Dados de Pessoa Jurídica salvos.");
        } 
        catch (IOException e) 
        {
            System.out.println("Erro ao gravar dados de Pessoa Juridica: " + e.getMessage());
        }


        try 
        {
            repositoryFour.getBIN("dados_pessoas_juridicas.ser");
            System.out.println("Dados de Pessoa Juridica Recuperados.");
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Erro ao recuperar dados de Pessoa Juridica: " + e.getMessage());
        }

     
        ArrayList<PessoaJuridica> pessoasJuridicasResult = repositoryFour.getAll();
        for (PessoaJuridica pessoaJuridica : pessoasJuridicasResult)
        {
            pessoaJuridica.read();
        }


        // Menu
        int escolha;

        do
        {
            showMenu();
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    includePerson();
                    break;
                case 2:
                    updatePerson();
                    break;
                case 3:
                    removePerson();
                    break;
                case 4:
                    searchPersonById();
                    break;
                case 5:
                    showAllPerson();
                    break;
                case 6:
                    saveData();
                    break;
                case 7:
                    getData();
                    break;
                case 0:
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (escolha != 0);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("==================================");
        System.out.println("1 - Incluir Pessoa");
        System.out.println("2 - Alterar Pessoa");
        System.out.println("3 - Excluir Pessoa");
        System.out.println("4 - Buscar pelo Id");
        System.out.println("5 - Exibir Todos");
        System.out.println("6 - Persistir Dados");
        System.out.println("7 - Recuperar Dados");
        System.out.println("0 - Finalizar Programa");
        System.out.println("==================================");
    }

    private static void includePerson() 
    {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.next().toUpperCase().charAt(0);

        System.out.println("Digite o ID da pessoa:");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Insira os dados...");
        System.out.println("Nome:");
        String nome = scanner.nextLine();

        if (tipo == 'F') 
        {
            System.out.println("CPF:");
            String cpf = scanner.next();
            System.out.println("Idade:");
            int idade = scanner.nextInt();
            PessoaFisica newPerson = new PessoaFisica(id, nome, cpf, idade);
            pessoaFisicaRepository.add(newPerson);
            System.out.println("Adicionada com sucesso!");
        } 
        else if (tipo == 'J') 
        {
            System.out.println("CNPJ:");
            String cnpj = scanner.next();
            PessoaJuridica newPerson = new PessoaJuridica(id, nome, cnpj);
            pessoaJuridicaRepository.add(newPerson);
            System.out.println("Adicionada com sucesso!");
        }
    }

    private static void updatePerson() 
    {
        System.out.println("Qual ID deseja alterar? ");
        int id = scanner.nextInt();

        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.next().toUpperCase().charAt(0);

        scanner.nextLine(); 

        System.out.println("Insira os novos dados...");
        System.out.println("Nome:");
        String nome = scanner.nextLine();

        if (tipo == 'F') 
        {
            System.out.println("CPF:");
            String cpf = scanner.next();
            System.out.println("Idade:");
            int idade = scanner.nextInt();
            PessoaFisica personUpdate = new PessoaFisica(id, nome, cpf, idade);
            pessoaFisicaRepository.update(personUpdate);
            System.out.println("Dados alterados com sucesso!");
        } 
        else if (tipo == 'J') 
        {
            System.out.println("CNPJ:");
            String cnpj = scanner.next();
            PessoaJuridica personUpdate = new PessoaJuridica(id, nome, cnpj);
            pessoaJuridicaRepository.update(personUpdate);
            System.out.println("Dados alterados com sucesso!");
        }
    }

    private static void removePerson() 
    {
        System.out.println("Qual o ID que deseja excluir?");
        int id = scanner.nextInt();

        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.next().toUpperCase().charAt(0);

        if (tipo == 'F') 
        {
            pessoaFisicaRepository.remove(id);
            System.out.println("Excluído com sucesso!");
        } 
        else if (tipo == 'J') 
        {
            pessoaJuridicaRepository.remove(id);
            System.out.println("Excluído com sucesso!");
        }
    }

    private static void searchPersonById() 
    {
        System.out.println("Digite o id da pessoa que deseja buscar:");
        int id = scanner.nextInt();

        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.next().toUpperCase().charAt(0);

        if (tipo == 'F') 
        {
            PessoaFisica person = pessoaFisicaRepository.getById(id);
            if (person != null) 
            {
                System.out.println("Pessoa Física localizada:");
                person.read();
            } 
            else 
            {
                System.out.println("Pessoa Física não localizada.");
            }
        } 
        else if (tipo == 'J') 
        {
            PessoaJuridica person = pessoaJuridicaRepository.getById(id);
            if (person != null) 
            {
                System.out.println("Pessoa Jurídica localizada:");
                person.read();
            } 
            else {
                System.out.println("Pessoa Jurídica não localizada.");
            }
        }
    }

    private static void showAllPerson() 
    {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.next().toUpperCase().charAt(0);

        if (tipo == 'F') 
        {
            ArrayList<PessoaFisica> people = pessoaFisicaRepository.getAll();
            if (!people.isEmpty())
            {
                System.out.println("Pessoas Físicas cadastradas:");
                for (PessoaFisica pessoaFisica : people) 
                {
                    pessoaFisica.read();
                    System.out.println("-----------");
                }
            } 
            else 
            {
                System.out.println("Nenhuma Pessoa Física cadastrada.");
            }
        } 
        else if (tipo == 'J') 
        {
            ArrayList<PessoaJuridica> people = pessoaJuridicaRepository.getAll();
            if (!people.isEmpty()) 
            {
                System.out.println("Pessoas Jurídicas cadastradas:");
                for (PessoaJuridica pessoaJuridica : people) 
                {
                    pessoaJuridica.read();
                    System.out.println("-----------");
                }
            } 
            else 
            {
                System.out.println("Nenhuma Pessoa Jurídica cadastrada.");
            }
        }
    }

    private static void saveData() 
    {
        System.out.println("Digite o nome do arquivo para persistir os dados:");
        String arquivo = scanner.next();

        try 
        {
            pessoaFisicaRepository.createBIN(arquivo + "_fisicas.ser");
            pessoaJuridicaRepository.createBIN(arquivo + "_juridicas.ser");
            System.out.println("Dados persistidos com sucesso!");
        } 
        catch (IOException e) 
        {
            System.out.println("Erro ao persistir os dados: " + e.getMessage());
        }
    }

    private static void getData() 
    {
        System.out.println("Digite o nome do arquivo para recuperar os dados:");
        String arquivo = scanner.next();

        try 
        {
            pessoaFisicaRepository.getBIN(arquivo + "_fisicas.ser");
            pessoaJuridicaRepository.getBIN(arquivo + "_juridicas.ser");
            System.out.println("Dados recuperados com sucesso!");
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }
}
