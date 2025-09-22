package entity;

public class Diretor extends Gerente {
    private double participacaoLucros;

    // construtor sem parametro
    public Diretor() {
    }

    // construtor com parametros
    public Diretor(double participacaoLucros) {
        this.participacaoLucros = participacaoLucros;

    }

    // get e set
    public double getParticipacaoLucros() {
        return participacaoLucros;
    }

    public void setParticipacaoLucros(double participacaoLucros) {
        this.participacaoLucros = participacaoLucros;
    }

    // sobrescritas
    @Override
    public void listarDados() {
        System.out.println("Nome: " + super.getNome());
        System.out.println("CPF: " + super.getCpf());
        System.out.println("Salário Base: " + super.getSalarioBase());
        System.out.println("Horas trabalhadas: " + super.getHorasTrabalhadas());
        System.out.println("Alíquota INSS: " + super.getInss());
        System.out.println("Alíquota do IRRF: " + super.getIrrf());
        System.out.println("Vale alimentação: " + getValeAlimentacao());
        System.out.println("Vale transporte: " + getValeTransporte());

        System.out.println("PLR: " + getParticipacaoLucros());
    }

    @Override
    public Double calcularSalarioLiquido() {
        return super.calcularSalarioLiquido() + participacaoLucros;
    }
}
