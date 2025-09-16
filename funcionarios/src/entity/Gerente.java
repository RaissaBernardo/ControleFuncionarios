package entity;

public class Gerente extends FuncionarioClt{
    private int quantidadeSubordinados;
    private double percentualBonus;

    //construtor padrão
    public Gerente() {
    }

    //construtor com parametros
    public Gerente(String departamento, int quantidadeSubordinados, double percentualBonus) {
        this.quantidadeSubordinados = quantidadeSubordinados;
        this.percentualBonus = percentualBonus;
    }

    //get e set

    public int getQuantidadeSubordinados() {
        return quantidadeSubordinados;
    }
    public void setQuantidadeSubordinados(int quantidadeSubordinados) {
        this.quantidadeSubordinados = quantidadeSubordinados;
    }
    public double getPercentualBonus() {
        return percentualBonus;
    }
    public void setPercentualBonus(double percentualBonus) {
        this.percentualBonus = percentualBonus;
    }

    @Override
    public Double calcularSalarioLiquido(){
        return super.getSalarioBase() * (percentualBonus/100+1) * 
                (1 - super.getInss()/100) * (1 - super.getIrrf()/100) 
                - super.calculoDescontoFixo();

    }

    
}
