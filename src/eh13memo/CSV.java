package eh13memo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSV {

    FileWriter arq;
    FileWriter sub;

    private FileWriter gravar(FileWriter arq, String info) {
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("%s%n", info);
        return arq;
    }

    public String pesquisar(String cpf) {
        String dados = "";
        String linha = "";
        String cod = "";

        boolean continuar = false;
        int regC = 0;
        int regO = 0;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("arquivos/candidatos.txt"));
            while ((linha = br.readLine()) != null) {

                String[] candidato = linha.split(";");
                if ((candidato[1]).equals(cpf)) {
                    dados = "\nNome: " + candidato[0] + " Cpf: " + candidato[1].replace('"', ' ').replace(" ", "") + ". ";
                    cod = candidato[5];
                    continuar = true;
                    break;
                }
                regC++;
            }

            if (!continuar) {
                return "Foram checados " + regC + " candidatos, porém nenhum possui o cpf informado!";
            }
            String sub = "arquivos/" + hash(Integer.valueOf(cod)) + ".txt";
            br = new BufferedReader(new FileReader(sub));
            while ((linha = br.readLine()) != null) {
                String[] ocupacao = linha.split(";");
                if (ocupacao[0].equals(cod)) {
                    dados += "Ocupacao: " + ocupacao[0] + " " + ocupacao[1] + ".\n";
                    dados += "N.candidatos testados: " + regC + ". N.Ocupações testadas: " + regO + ".\n";
                    dados += "Caminho do arquivo: " + sub + "\n";
                    break;
                }
                regO++;
            }

        } catch (Exception e) {
            System.out.println("Houve um erro na pesquisa de dados!\n" + e.getMessage());
        }
        return dados;
    }

    public void gravarCandidato(ArrayList<Candidato> lista) {
        String arquivoCSV = "arquivos/candidatos.txt";

        try {
            arq = new FileWriter(arquivoCSV);
            for (Candidato i : lista) {
                String dados = i.retornarDados();
                arq = gravar(arq, dados);
            }
            arq.close();
        } catch (Exception e) {
            System.out.println("Houve um erro na gravação do arquivo de candidatos!\n" + e.getMessage());
        }
    }

    public void gravarOcupacao(ArrayList<Ocupacao> lista) {
        String arquivoCSV = "arquivos/ocupacao.txt";
        try {
            arq = new FileWriter(arquivoCSV);
            for (Ocupacao i : lista) {
                String dados = i.retornarDados();
                arq = gravar(arq, dados);
                gravarSub(dados, hash(i.getCodigo()));
            }
            arq.close();
        } catch (Exception e) {
            System.out.println("Houve um erro na gravação do principal das ocupações!\n" + e.getMessage());
        }
    }

    private int hash(int key) {
        return (key % (273 / 10));
    }

    public void gravarSub(String dados, int key) {
        String arquivo = "arquivos/" + key + ".txt";
        FileWriter arq;
        try {
            arq = new FileWriter(arquivo, true);
            arq = gravar(arq, dados);
            arq.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel gravar os sub arquivos de ocupação!\n" + e.getMessage());
        }
    }
}
