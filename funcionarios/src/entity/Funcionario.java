package entity;

import java.sql.Date;

public class Funcionario {
    private Integer dependentes;
    private String estadoCivil;
    private String genero;
    private Date dataNascimento;
    private String cpf;
    private String setor;
    private String nome;
    private Double salarioBase;
    private Double inss;
    private Double irrf;
    private Double horasTrabalhadas;

    public Funcionario(){

    }

    

    public Funcionario(Integer dependentes, String estadoCivil, String genero, Date dataNascimento, String cpf,
            String nome, Double salarioBase, Double inss, Double irrf, Double horasTrabalhadas, String setor) {
        this.dependentes = dependentes;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.inss = inss;
        this.irrf = irrf;
        this.horasTrabalhadas = horasTrabalhadas;
        this.setor = setor;
    }



    public Integer getDependentes() {
        return dependentes;
    }

    public void setDependentes(Integer dependentes) {
        this.dependentes = dependentes;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Double getInss() {
        return inss;
    }

    public void setInss(Double inss) {
        this.inss = inss;
    }

    public Double getIrrf() {
        return irrf;
    }

    public void setIrrf(Double irrf) {
        this.irrf = irrf;
    }

    public Double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(Double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public void listarDados(){
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Salário Base: " + salarioBase);
        System.out.println("Horas trabalhadas: " + horasTrabalhadas);
        System.out.println("Alíquota INSS: " + inss);
        System.out.println("Alíquota do IRRF: " + irrf);
    }

    public Double calcularSalarioLiquido(){
        return calculardescontos();
    }

    public Double calculardescontos(){
        return salarioBase * (1 - inss/100) * (1 - irrf/100);
    }
    
}