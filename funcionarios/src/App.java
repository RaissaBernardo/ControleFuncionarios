import java.util.Scanner;
import java.util.ArrayList;
import entity.Setor;
import entity.Funcionario;
import entity.FuncionarioClt;

public class App {

    static ArrayList<Setor> setores = new ArrayList<>();
    static ArrayList<Funcionario> funcionarios = new ArrayList<>();;

    public static void menuFuncionarios() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("=== DASHBOARD DE FUNCIONARIOS ===");
            System.out.println("1 - Criar Funcionario.");
            System.out.println("2 - Novo Gerente.");
            System.out.println("3 - Novo Diretor.");
            System.out.println("4 - Listar funcionarios.");
            System.out.println("5 - Visualizar Holerite.");
            System.out.println("6 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:           
                System.out.println("--> INSERINDO FUNCIONÁRIO <--");
            
                System.out.print("Digite o nome do funcionário: ");
                String nomeFunc = scanner.nextLine();
                System.out.print("Digite o CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Digite o salário base: ");
                double salarioBase = scanner.nextDouble();
                System.out.print("Digite as horas trabalhadas: ");
                double horasTrabalhadas = scanner.nextDouble();
                System.out.print("Digite a alíquota de INSS (%): ");
                double inss = scanner.nextDouble();
                System.out.print("Digite a alíquota de IRRF (%): ");
                double irrf = scanner.nextDouble();
                System.out.print("Digite o valor do vale alimentação: ");
                double valeAlimentacao = scanner.nextDouble();
                System.out.print("Digite o valor do vale transporte: ");
                double valeTransporte = scanner.nextDouble();
                scanner.nextLine(); 
            
                FuncionarioClt novoFunc = new FuncionarioClt(valeAlimentacao, valeTransporte);
        
                novoFunc.setNome(nomeFunc);
                novoFunc.setCpf(cpf);
                novoFunc.setSalarioBase(salarioBase);
                novoFunc.setHorasTrabalhadas(horasTrabalhadas);
                novoFunc.setInss(inss);
                novoFunc.setIrrf(irrf);
            
                funcionarios.add(novoFunc);
                System.out.println("Funcionário CLT criado com sucesso!");
                break;
            
                case 2:
                    System.out.println("Total de setores: " + setores.size());
                    for (int i = 0; i < setores.size(); i++) {
                        Setor setor = setores.get(i);
                        System.out.println("Código: " + (i + 1) + " - Setor: " + setor.getNome());
                    }
                    break;
                case 3:
                    System.out.print("Digite o código do setor que quer visualizar:  ");
                    Integer codigoV = scanner.nextInt();

                    System.out.println("Funcionários do setor " + setores.get(codigoV).getNome() + ":");
                    for (String cpff : setores.get(codigoV).getFuncionarios()) {
                        for (Funcionario f : funcionarios) {
                            if (f.getCpf().equals(cpff)) {
                                System.out.println("- " + f);
                                break;
                            }
                        }
                    }

                    break;
                case 4:
                    System.out.print("Digite o código do setor que quer adicionar:  ");
                    Integer codigoA = scanner.nextInt();

                    System.out.print("Digite o CPF do funcionario ( 111.111.111-11 ): ");
                    String novoCpf = scanner.nextLine();
                    setores.get(codigoA).adicionarFuncionario(novoCpf);
                    break;
                    case 5:
                    try {
                        System.out.print("Digite o CPF do funcionário para visualizar o holerite: ");
                        String cpfBusca = scanner.nextLine();
                
                        Funcionario funcionario = null;
                        for (Funcionario f : funcionarios) {
                            if (f.getCpf().equals(cpfBusca)) {
                                funcionario = f;
                                break;
                            }
                        }
                
                        if (funcionario == null) {
                            throw new Exception("Funcionário não encontrado!");
                        }
                
                        double salarioLiquido = funcionario.calcularSalarioLiquido();
                
                        System.out.println("\n=== HOLERITE ===");
                        System.out.println("Nome: " + funcionario.getNome());
                        System.out.println("CPF: " + funcionario.getCpf());
                        System.out.println("Cargo: " + (funcionario instanceof FuncionarioClt ? "Funcionário CLT" : "Outro Cargo"));
                        System.out.println("-------------------------------------------------");
                        System.out.println("Salário Base: R$ " + funcionario.getSalarioBase());
                        System.out.println("INSS: " + funcionario.getInss());
                        System.out.println("IRRF: " + funcionario.getIrrf());
                
                        if (funcionario instanceof FuncionarioClt) {
                            FuncionarioClt cltFuncionario = (FuncionarioClt) funcionario;
                            System.out.println("Vale Alimentação: R$ " + cltFuncionario.getValeAlimentacao());
                            System.out.println("Vale Transporte: R$ " + cltFuncionario.getValeTransporte());
                        }
                
                        System.out.println("-------------------------------------------------");
                        System.out.println("Salário Líquido: R$ " + salarioLiquido);
                
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                
                case 6:
                    menu();
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
            System.out.println();
        } while (opcao != 3);

        scanner.close();
    }

    public static void menuSetores() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("=== DASHBOARD DE SETORES ===");
            System.out.println("1 - Criar Setor.");
            System.out.println("2 - Listar Setores");
            System.out.println("3 - Listar Funcionarios de um setor.");
            System.out.println("4 - Adicionar funcionario a um setor.");
            System.out.println("5 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); //para consumir o buffer e fncionar

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do setor: ");
                    String nome = scanner.nextLine();
                    Setor novoSetor = new Setor(nome);
                    setores.add(novoSetor);
                    break;
                case 2:
                    System.out.println("Total de setores: " + setores.size());
                    for (int i = 0; i < setores.size(); i++) {
                        Setor setor = setores.get(i);
                        System.out.println("Código: " + (i + 1) + " - Setor: " + setor.getNome());
                    }
                    break;
                    case 3:
                    System.out.print("Digite o código do setor que quer visualizar:  ");
                    Integer codigoV = scanner.nextInt();
                
                    System.out.println("Funcionários do setor " + setores.get(codigoV).getNome() + ":");
                    for (String cpf : setores.get(codigoV).getFuncionarios()) {
                        for (Funcionario f : funcionarios) {
                            if (f.getCpf().equals(cpf)) {
                                System.out.println("- " + f);
                                break;
                            }
                        }
                    }                

                    break;
                case 4:
                    System.out.print("Digite o código do setor que quer adicionar:  ");
                    Integer codigoA = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Digite o CPF do funcionario ( 111.111.111-11 ): ");
                    String novoCpf = scanner.nextLine();
                    setores.get(codigoA).adicionarFuncionario(novoCpf);
                    break;
                case 5:
                    menu();
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
            System.out.println();
        } while (opcao != 3);

        scanner.close();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
    
        do {
            System.out.println("=== DASHBOARD DO RH ===");
            System.out.println("1 - Setores");
            System.out.println("2 - Funcionarios");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
    
            opcao = scanner.nextInt();
    
            switch (opcao) {
                case 1:
                    menuSetores(); 
                    break;
                case 2:
                    menuFuncionarios(); 
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
            System.out.println();
        } while (opcao != 3);
    
        scanner.close();
    }
    

    public static void main(String[] args) throws Exception {
        menu();
    }
}
