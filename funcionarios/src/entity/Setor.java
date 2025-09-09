package entity;

import java.util.ArrayList;

public class Setor {
    private String nome;
    private ArrayList<String> funcionarios;

    public Setor(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeFuncionarios() {
        return funcionarios.size();
    }

    public void adicionarFuncionario(String f) {
        funcionarios.add(f);
    }

    public ArrayList<String> getFuncionarios() {
        return funcionarios;
    }

    @Override
    public String toString() {
        return "Setor: " + nome + ", Quantidade de funcionários: " + getQuantidadeFuncionarios() +
               ", Funcionários: " + funcionarios;
    }
}
