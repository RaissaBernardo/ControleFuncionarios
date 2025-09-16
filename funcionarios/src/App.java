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
        System.out.print("Digite o CPF (111.111.111-11) do funcionário: ");
        String cpf = scanner.nextLine();
        Funcionario funcionario = buscarFuncionarioPorCpf(cpf);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        Setor setor = buscarSetorPorNome(funcionario.getSetor());
        if (setor == null) {
            System.out.println("Setor do funcionário não encontrado.");
            return;
        }

        System.out.println("Digite o novo valor do vale transporte: ");
        Integer novoTransporte = scanner.nextInt();
        System.out.println("Digite o novo valor do vale alimentação: ");
        Integer novoAlimentacao = scanner.nextInt();

        if (tipo == TipoPromocao.GERENTE) {
            Gerente gerente = new Gerente();
            gerente.setNome(funcionario.getNome());
            gerente.setCpf(funcionario.getCpf());
            gerente.setSalarioBase(funcionario.getSalarioBase());
            gerente.setHorasTrabalhadas(funcionario.getHorasTrabalhadas());
            gerente.setInss(funcionario.getInss());
            gerente.setIrrf(funcionario.getIrrf());
            gerente.setSetor(funcionario.getSetor());
            gerente.setQuantidadeSubordinados(setor.getFuncionarios().size() - 1);
            gerente.setValeAlimentacao(novoAlimentacao);
            gerente.setValeTransporte(novoTransporte);
            gerente.setPercentualBonus(15);

            funcionarios.remove(funcionario);
            funcionarios.add(gerente);
            System.out.println("Funcionário promovido a GERENTE.");
        } else if (tipo == TipoPromocao.DIRETOR) {
            Diretor diretor = new Diretor();
            diretor.setNome(funcionario.getNome());
            diretor.setCpf(funcionario.getCpf());
            diretor.setSalarioBase(funcionario.getSalarioBase());
            diretor.setHorasTrabalhadas(funcionario.getHorasTrabalhadas());
            diretor.setInss(funcionario.getInss());
            diretor.setIrrf(funcionario.getIrrf());
            diretor.setSetor(funcionario.getSetor());
            diretor.setQuantidadeSubordinados(setor.getFuncionarios().size() - 1);
            diretor.setPercentualBonus(25);
            diretor.setParticipacaoLucros(2000);
            diretor.setValeAlimentacao(novoAlimentacao);
            diretor.setValeTransporte(novoTransporte);

            funcionarios.remove(funcionario);
            funcionarios.add(diretor);
            System.out.println("Funcionário promovido a DIRETOR.");
        }
    }

    public static void menuFuncionarios() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("=== DASHBOARD DE FUNCIONÁRIOS ===");
            System.out.println("1 - Criar Funcionário");
            System.out.println("2 - Promover para Gerente");
            System.out.println("3 - Promover para Diretor");
            System.out.println("4 - Listar Funcionários");
            System.out.println("5 - Visualizar Holerite");
            System.out.println("6 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.println("--> INSERINDO FUNCIONÁRIO <--");

                    System.out.print("Digite o nome do funcionário: ");
                    String nomeFunc = scanner.nextLine();
                    System.out.print("Digite o CPF: ");
                    String cpf = scanner.nextLine();

                    Integer setorSelecionado = null;

                    if (setores.size() > 0) {
                        listaSetores();
                        System.out.print("Digite o código do setor: ");
                        int codigoA = scanner.nextInt();
                        scanner.nextLine();

                        if (codigoA < 1 || codigoA > setores.size()) {
                            System.out.println("Código inválido!");
                            break;
                        }

                        setorSelecionado = codigoA - 1;

                        setores.get(codigoA - 1).adicionarFuncionario(cpf);

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

                    System.out.println("Setores disponíveis:");

                    if (setorSelecionado != null) {
                        novoFunc.setSetor(setores.get(setorSelecionado).getNome());

                    }

                    funcionarios.add(novoFunc);
                    System.out.println("Funcionário CLT criado com sucesso!");
                    break;

                case 2:
                    promoverFuncionario(TipoPromocao.GERENTE);
                    System.out.println("Funcionário promovido com sucesso!");
                    break;

                case 3:
                    promoverFuncionario(TipoPromocao.DIRETOR);
                    System.out.println("Funcionário promovido com sucesso!");
                    break;

                case 4:
                    System.out.println("=== Funcionários cadastrados ===");
                    if (funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado ainda.");
                    } else {
                        for (Funcionario f : funcionarios) {
                            System.out.println("- " + f.toString());
                        }
                    }
                    break;

                case 5:
                    System.out.print("Digite o CPF do funcionário para visualizar o holerite: ");
                    String cpfBusca = scanner.nextLine();

                    Funcionario funcionario = buscarFuncionarioPorCpf(cpfBusca);
                    if (funcionario == null) {
                        System.out.println("Funcionário não encontrado!");
                        break;
                    }

                    double salarioLiquido = funcionario.calcularSalarioLiquido();

                    System.out.println("\n=== HOLERITE ===");
                    System.out.println("Nome: " + funcionario.getNome());
                    System.out.println("CPF: " + funcionario.getCpf());
                    System.out.println("Cargo: " + funcionario.getClass().getSimpleName());
                    System.out.println("-------------------------------------------------");
                    System.out.println("Salário Base: R$ " + funcionario.getSalarioBase());
                    System.out.println("INSS: " + funcionario.getInss());
                    System.out.println("IRRF: " + funcionario.getIrrf());

                    if (funcionario instanceof FuncionarioClt) {
                        FuncionarioClt clt = (FuncionarioClt) funcionario;
                        System.out.println("Vale Alimentação: R$ " + clt.getValeAlimentacao());
                        System.out.println("Vale Transporte: R$ " + clt.getValeTransporte());
                    }

                    System.out.println("-------------------------------------------------");
                    System.out.println("Salário Líquido: R$ " + salarioLiquido);
                    break;

                case 6:
                    menu();
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }

            System.out.println();
        } while (opcao != 6);
    }

    public static void menuSetores() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== DASHBOARD DE SETORES ===");
            System.out.println("1 - Criar Setor");
            System.out.println("2 - Listar Setores");
            System.out.println("3 - Listar Funcionários de um setor");
            System.out.println("4 - Adicionar funcionário a um setor");
            System.out.println("5 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do setor: ");
                    String nome = scanner.nextLine();
                    if (buscarSetorPorNome(nome) != null) {
                        System.out.println("⚠️ Já existe um setor com esse nome.");
                    } else {
                        Setor novoSetor = new Setor(nome);
                        setores.add(novoSetor);
                        System.out.println("✅ Setor criado com sucesso!");
                    }
                    break;

                case 2:
                    System.out.println("Total de setores: " + setores.size());
                    listaSetores();
                    break;

                case 3:
                    System.out.print("Digite o código do setor que quer visualizar: ");
                    int codigoV = scanner.nextInt();
                    scanner.nextLine();

                    if (codigoV < 1 || codigoV > setores.size()) {
                        System.out.println("Código inválido.");
                        break;
                    }

                    Setor setorV = setores.get(codigoV - 1);
                    System.out.println("Funcionários do setor " + setorV.getNome() + ":");
                    for (String cpf : setorV.getFuncionarios()) {
                        Funcionario f = buscarFuncionarioPorCpf(cpf);
                        if (f != null) {
                            System.out.println("- " + f);
                        }
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Digite o código do setor: ");
                        int codigoA = scanner.nextInt();
                        scanner.nextLine();

                        if (codigoA < 1 || codigoA > setores.size()) {
                            System.out.println("Código inválido!");
                            break;
                        }

                        System.out.print("Digite o CPF do funcionário: ");
                        String novoCpf = scanner.nextLine();

                        setores.get(codigoA - 1).adicionarFuncionario(novoCpf);
                        System.out.println("Funcionário adicionado ao setor!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                        scanner.nextLine();
                    }
                    break;

                case 5:
                    menu();
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println();
        } while (opcao != 5);

        scanner.close();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== DASHBOARD DO RH ===");
            System.out.println("1 - Setores");
            System.out.println("2 - Funcionários");
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

    public static void main(String[] args) {
        menu();
    }
}
