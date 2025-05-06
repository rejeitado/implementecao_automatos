package Automato;

import java.util.Scanner;
import java.util.Stack;

public class AutomatoPilha {
/*	
*       Estado inicial |	Simbolo	|	Simbolo trasição	|Proximo Estado        |Simbolo alterado da pilha
*	------------------------------------------------------------------------------------------------------
*       q0             |	  0     |		S               |	q1              |	AS
*	q1             |	  1	|		A               |	q2              |	*
*      
*/
    

public static void mostraPilha(Stack pilha){
    System.out.println();
    System.out.print("Fundo da pilha -> ");
    for(int z=0;z<pilha.size();z++)
        System.out.print(pilha.get(z) + " ");
      System.out.println(" <- Topo da pilha ");
}   
    
public static void imprimeInformacoes() {
    System.out.println("__________________________________________");
    System.out.println("Informações: ");
    System.out.println("Para os estados digite da forma: q0 , q1 ,q2 , ... ");
    System.out.println("Para lambda use o simbolo: * ");
    System.out.println("__________________________________________");
}


public static void main(String[] args) {

    Scanner entrada = new Scanner(System.in);
    imprimeInformacoes();

    //Quantidade de estados
    System.out.println("Qual o estado inicial?");
    String estadoInicial = entrada.next();


    //numero de estados finais / guarda o(s) estado(s) final(is)
    System.out.println("Qual a Quantidade de Estados finais?");
    int numeroEstadosFianis = entrada.nextInt();
    String [] estadoFinal = new String [numeroEstadosFianis];
    for(int i=0;i<numeroEstadosFianis;i++) {
            System.out.println("qual o estado final " + (i+1));
            estadoFinal [i] = entrada.next();
    }


    //Entrada do numero de transi��es 
    System.out.println("Qual o numero de funções de trasições do Automato?");
    int numeroTransicao = entrada.nextInt();


    //Tabela do automato
    String [][] tb_automato = { {"q0","0","S","q0","AS"},
                                {"q0","0","A","q0","AA"},
                                {"q0","1","S","q2","*"},
                                {"q0","1","A","q1","*"},
                                {"q1","1","A","q1","*"},
                                {"q1","1","S","q2","*"},
                                {"q2","1","*","q2","*"}  };
    

    //guarda os valores na tabela
    /*for (int i=0; i<numeroTransicao;i++) {
            System.out.println("Estado Atual");
            tb_automato[i][0] = entrada.next();

            System.out.println("Simbolo do alfabeto");
            tb_automato[i][1] = entrada.next();

            System.out.println("Simbolo desempilhado");
            tb_automato[i][2] = entrada.next();

            System.out.println("Proximo Estado");
            tb_automato[i][3] = entrada.next();

            System.out.println("Simbolo empilhado");
            tb_automato[i][4] = entrada.next();
    }
    */	


    //criando a pilha
    Stack<String> pilha = new Stack();

    // definindo o simbolo inicial da pilha
    System.out.println("Qual o simbolo incial da pilha?");
    String simboloInicialPilha =entrada.next();
    pilha.push(simboloInicialPilha);


    // Definindo o numero de cadeias a ser reconhecida
    int NumeroCadeiaSerReconhecida;
    System.out.println("Digite a quantidade de cadeias a ser testada");
    NumeroCadeiaSerReconhecida = entrada.nextInt();



    for (int x = 0; x < NumeroCadeiaSerReconhecida; x++) {
            // entrando com a cadeia
            System.out.println("Digite a Cadeia a ser testada");
            String aCadeia = entrada.next();
            String estadoAtual = estadoInicial;
            int PercorreCadeia = 0;				

            for (int i=0;i<aCadeia.length();i++) {


                for(int y =0;y<numeroTransicao;y++) {  
  
                    //acha a função de transição
                    if(estadoAtual.equals(tb_automato[y][0])) { 

                        //acha a função de transição
                        if(Character.toString(aCadeia.charAt(PercorreCadeia)).equals(tb_automato[y][1])) {

                            //acha a função de transição
                            if(tb_automato[y][2].equals("*") || pilha.get(pilha.size()-1).equals(tb_automato[y][2])) {

                                //a,lambda,lambda
                                if(tb_automato[y][2].equals("*") && tb_automato[y][4].equals("*")){
                                    estadoAtual = tb_automato[y][3];
                                    PercorreCadeia++;
                                    mostraPilha(pilha);
                                    break;
                                }

                                //a,A,B
                                else if(tb_automato[y][2] != "*" && tb_automato[y][4] != "*") {
                                    estadoAtual = tb_automato[y][3];
                                    pilha.pop();
                                    for(int j=tb_automato[y][4].length()-1 ;j>=0;j--) {
                                            pilha.push(Character.toString(tb_automato[y][4].charAt(j)));

                                    }
                                    PercorreCadeia++;
                                    mostraPilha(pilha);
                                    break;

                                //a,A,lambda
                                }else if(tb_automato[y][2] != "*" && tb_automato[y][4].equals("*") ) {
                                    estadoAtual = tb_automato[y][3];
                                    pilha.pop();
                                    PercorreCadeia++;
                                    mostraPilha(pilha);
                                    break;

                                //a,lambda,B
                                }else if(tb_automato[y][2].equals("*") && tb_automato[y][4] != "*" ){
                                    estadoAtual = tb_automato[y][3];
                                    for(int j=tb_automato[y][4].length()-1 ;j>=0;j--) {
                                            pilha.push(Character.toString(tb_automato[y][4].charAt(j)));
                                    }
                                    PercorreCadeia++;
                                    mostraPilha(pilha);
                                    break;
                                }
                            }
                        }
                    }
                }
            }



    int contador_estado_final=0;

    for(int z=0;z<numeroEstadosFianis;z++) {
            if(estadoFinal[z].equals(estadoAtual)) {
                    contador_estado_final = 1;
            }
    }

    if(contador_estado_final==1)
            System.out.println("Cadeia [" + aCadeia + "] foi reconhecida");
    else
            System.out.println("Cadeia [" + aCadeia + "] não foi reconhecida");


    pilha.clear();
    pilha.push(simboloInicialPilha);
    }

}

}
