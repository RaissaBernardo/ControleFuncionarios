package entity;

public class Diretor extends Gerente{
    private double participacaoLucros;
    private double percentualBonusDiretor;

    //construtor sem parametro
    public Diretor() {
    }
    
    //construtor com parametros
    public Diretor(double participacaoLucros, double percentualBonusDiretor) {
        this.participacaoLucros = participacaoLucros;
        this.percentualBonusDiretor = percentualBonusDiretor;
    }

    //get e set
    public double getParticipacaoLucros() {
        return participacaoLucros;
    }
    public void setParticipacaoLucros(double participacaoLucros) {
        this.participacaoLucros = participacaoLucros;
    }
    public double getPercentualBonusDiretor() {
        return percentualBonusDiretor;
    }
    public void setPercentualBonusDiretor(double percentualBonusDiretor) {
        this.percentualBonusDiretor = percentualBonusDiretor;
    }

    //sobrescritas
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
        
        System.out.println("PLR: " + getParticipacaoLucros());
        System.out.println("Bônus: " + getPercentualBonusDiretor());
    }

    @Override
    public Double calculardescontos(){
        return (super.getSalarioBase() * (getPercentualBonus()/100 +1) + getParticipacaoLucros())
        * (1 - super.getInss()/100) * (1 - super.getIrrf()/100);
    }
}
