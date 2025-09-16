import java.util.Scanner;
import java.util.ArrayList;
import entity.Setor;
import entity.TipoPromocao;
import entity.Funcionario;
import entity.FuncionarioClt;
import entity.Diretor;
import entity.Gerente;

public class App {

    static ArrayList<Setor> setores = new ArrayList<>();
    static ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public static void listaSetores() {
        for (int i = 0; i < setores.size(); i++) {
            Setor setor = setores.get(i);
            System.out.println("Código: " + (i + 1) + " - Setor: " + setor.getNome());
        }
    }

    public static Funcionario buscarFuncionarioPorCpf(String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }

    public static Setor buscarSetorPorNome(String nome) {
        for (Setor setor : setores) {
            if (setor.getNome().equalsIgnoreCase(nome)) {
                return setor;
            }
        }
        return null;
    }

    public static void promoverFuncionario(TipoPromocao tipo) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o cpf (111.111.111-11) do funcionário:");
        String cpf = scanner.nextLine();
        Funcionario funcionario = buscarFuncionarioPorCpf(cpf);

        if (tipo == TipoPromocao.GERENTE) {
            if (funcionario instanceof Diretor) {
                Gerente gerente = (Gerente) funcionario;
                Setor setor = buscarSetorPorNome(funcionario.getSetor());
                gerente.setQuantidadeSubordinados(setor.getFuncionarios().size() - 1);
                gerente.setPercentualBonus(15);
                funcionarios.remove(funcionario);
                funcionarios.add(gerente);
            }
        } else if (tipo == TipoPromocao.DIRETOR) {
            if (funcionario instanceof Diretor || funcionario instanceof Gerente) {
                Diretor diretor = (Diretor) funcionario;
                Setor setor = buscarSetorPorNome(funcionario.getSetor());
                diretor.setQuantidadeSubordinados(setor.getFuncionarios().size() - 1);
                diretor.setPercentualBonus(25);
                diretor.setParticipacaoLucros(2000);
                funcionarios.remove(funcionario);
                funcionarios.add(diretor);
            }
        }

        scanner.close();

    }

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
                    System.out.print("Digite o CPF (111.111.111-11): ");
                    String cpf = scanner.nextLine();
                    if (setores.size() > 0) {
                        System.out.println("Setores disponiveis:");
                        listaSetores();
                        System.out.print("Digite o código do setor que quer adicionar:  ");
                        Integer codigoA = scanner.nextInt();
                        setores.get(codigoA).adicionarFuncionario(cpf);
                    }

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

                    FuncionarioClt novoFunc = new Diretor(valeAlimentacao, valeTransporte);

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
                    System.out.println("--> PROMOVENDO PARA GERENTE <--");
                    promoverFuncionario(TipoPromocao.GERENTE);
                    System.out.println("Funcionário promovido com sucesso!");
                    break;
                case 3:
                    System.out.println("--> PROMOVENDO PARA DIRETOR <--");
                    promoverFuncionario(TipoPromocao.DIRETOR);
                    System.out.println("Funcionário promovido com sucesso!");

                    break;
                case 4:
                    System.out.print("Digite o código do setor que quer adicionar:  ");
                    Integer codigoA = scanner.nextInt();

                    System.out.print("Digite o CPF do funcionario ( 111.111.111-11 ): ");
                    String novoCpf = scanner.nextLine();
                    setores.get(codigoA).adicionarFuncionario(novoCpf);
                    break;
                case 5:
                    Funcionario funcionario = null;

                    try {
                        for (Funcionario f : funcionarios) {
                            if (f.getCpf().equals(cpff)) {
                                funcionario = f;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        if (funcionario == null) {
                            System.out.println("Funcionário não encontrado!");
                            return;
                        }
                    }

                    double salarioLiquido = funcionario.calcularSalarioLiquido();

                    System.out.println("\n=== HOLERITE ===");
                    System.out.println("Nome: " + funcionario.getNome());
                    System.out.println("CPF: " + funcionario.getCpf());
                    System.out.println(
                            "Cargo: " + (funcionario instanceof FuncionarioClt ? "Funcionário CLT" : "Outro Cargo"));
                    System.out.println("-------------------------------------------------");
                    System.out.println("Salário Base: R$ " + funcionario.getSalarioBase());
                    System.out.println("INSS: " + funcionario.getInss());
                    System.out.println("IRRF: " + funcionario.getIrrf());

                    if (funcionario instanceof FuncionarioClt) { // o instaceof vai retornar um valor booleano
                        FuncionarioClt cltFuncionario = (FuncionarioClt) funcionario;
                        System.out.println("Vale Alimentação: R$ " + cltFuncionario.getValeAlimentacao());
                        System.out.println("Vale Transporte: R$ " + cltFuncionario.getValeTransporte());
                    }

                    System.out.println("-------------------------------------------------");
                    System.out.println("Salário Líquido: R$ " + salarioLiquido);
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

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do setor: ");
                    scanner.nextLine(); // Garantir leitura correta
                    String nome = scanner.nextLine();

                    // Verifica se já existe um setor com esse nome
                    boolean setorExistente = false;
                    for (Setor setor : setores) {
                        if (setor.getNome().equalsIgnoreCase(nome)) {
                            setorExistente = true;
                            break;
                        }
                    }

                    if (setorExistente) {
                        System.out.println("⚠️ Já existe um setor com esse nome. Criação cancelada.");
                    } else {
                        Setor novoSetor = new Setor(nome);
                        setores.add(novoSetor);
                        System.out.println("✅ Setor criado com sucesso!");
                    }
                case 2:
                    System.out.println("Total de setores: " + setores.size());
                    listaSetores();

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
            System.out.println("1 - Setores.");
            System.out.println("2 - Funcionarios");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:

                    break;
                case 2:
                    System.out.println("Você escolheu a Opção 2");
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

    }
}
