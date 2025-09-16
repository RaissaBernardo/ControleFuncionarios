package entity;

import java.sql.Date;

public class FuncionarioClt extends Funcionario{
    private double valeAlimentacao;
    private double valeTransporte;

    //construtor padrão
    public FuncionarioClt() {
    }
    
    //construtor com parametro
    public FuncionarioClt(Integer dependentes, String estadoCivil, String genero, Date dataNascimento, String cpf,
            String nome, Double salarioBase, Double inss, Double irrf, Double horasTrabalhadas, double valeAlimentacao,
            double valeTransporte,String setor) {
        super(dependentes, estadoCivil, genero, dataNascimento, cpf, nome, salarioBase, inss, irrf, horasTrabalhadas, setor);
        this.valeAlimentacao = valeAlimentacao;
        this.valeTransporte = valeTransporte;
    }

    //get e set
    public double getValeAlimentacao() {
        return valeAlimentacao;
    }

    public FuncionarioClt(double valeAlimentacao, double valeTransporte) {
        this.valeAlimentacao = valeAlimentacao;
        this.valeTransporte = valeTransporte;
    }

    public void setValeAlimentacao(double valeAlimentacao) {
        this.valeAlimentacao = valeAlimentacao;
    }
    public double getValeTransporte() {
        return valeTransporte;
    }
    public void setValeTransporte(double valeTransporte) {
        this.valeTransporte = valeTransporte;
    }

    @Override
    public void listarDados(){
        System.out.println("Nome: " + super.getNome());
        System.out.println("CPF: " + super.getCpf());
        System.out.println("Salário Base: " + super.getSalarioBase());
        System.out.println("Horas trabalhadas: " + super.getHorasTrabalhadas());
        System.out.println("Alíquota INSS: " + super.getInss());
        System.out.println("Alíquota do IRRF: " + super.getIrrf());
        System.out.println("Vale alimentação: " + getValeAlimentacao());
        System.out.println("Vale transporte: " + getValeTransporte());
    }

    public Double calculoDescontoFixo(){
        return getValeAlimentacao() + getValeTransporte();
    }

    @Override
    public Double calcularSalarioLiquido(){
        return super.calcularSalarioLiquido() - calculoDescontoFixo();
    }
    //super.getSalarioBase() * (1 - inss/100) * (1 - irrf/100);
}
