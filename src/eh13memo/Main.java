package eh13memo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Banco banco;
        CSV csv;

        String pesquisar, resultado = "";
        int resp;
        Scanner scan = new Scanner(System.in);
        try {
            banco = new Banco();
            csv = new CSV();
            
            csv.gravarCandidato(banco.selectCandidato());
            csv.gravarOcupacao(banco.selectOcupacao());

            do {
                System.out.println("Digite o cpf desejado: ");
                pesquisar = scan.nextLine();

                System.out.println(csv.pesquisar(pesquisar));
                System.out.println("\nDeseja procurar outro cpf?\n 1 sim   2 nao");
                resp = scan.nextInt();
                scan.nextLine();
                System.out.println("\n");
            } while (resp == 1);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro");
        }

    }

}
