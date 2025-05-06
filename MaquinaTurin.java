package Automato;

import java.util.Scanner;

public class MaquinaTurin {
/*	
*       Estado inicial |	Quando vê	|           vai para o          |Troca por            |Vai para a D ou L
*	------------------------------------------------------------------------------------------------------
*       q0             |	  a             |		q1              |	X             |	R
*	q1             |	  b             |		q0              |	Y             |	L
*      
*/
    

    
public static void imprimeInformacoes() {
    System.out.println("__________________________________________");
    System.out.println("Informações: ");
    System.out.println("Para os estados digite da forma: q0 , q1 ,q2 , ... ");
    System.out.println("Para a Direita use: R ");
    System.out.println("Para a Esquerda use: L ");
    System.out.println("Para Vazio utilize: * ");
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
    System.out.println("Qual o numero de funções de trasições da Maquina de Turin?");
    int numeroTransicao = entrada.nextInt();


    //Tabela do automato
    String [][] tb_mt = new String [numeroTransicao][5];
    String [][] tb_Mt = {      {"q0","a","q1","X","R"},
                               {"q0","Y","q3","Y","R"},
                               {"q1","a","q1","a","R"},
                               {"q1","Y","q1","Y","R"},
                               {"q1","b","q2","Y","L"},
                               {"q2","a","q2","a","L"},
                               {"q2","Y","q2","Y","L"},
                               {"q2","X","q0","X","R"},
                               {"q2","*","q3","*","R"},
                               {"q3","Y","q3","Y","R"},
                               {"q3","*","q4","*","L"}        };
    

    //guarda os valores na tabela
    /*for (int i=0; i<numeroTransicao;i++) {
            System.out.println("Estado Atual");
            tb_automato[i][0] = entrada.next();

            System.out.println("Quando vê");
            tb_automato[i][1] = entrada.next();

            System.out.println("vai para o ");
            tb_automato[i][2] = entrada.next();

            System.out.println("Troca por");
            tb_automato[i][3] = entrada.next();

            System.out.println("Vai para a");
            tb_automato[i][4] = entrada.next();
    }
    */	


    // Definindo o numero de cadeias a ser reconhecida
    int NumeroCadeiaSerReconhecida;
    System.out.println("Digite a quantidade de cadeias a ser testada");
    NumeroCadeiaSerReconhecida = entrada.nextInt();



    for (int x = 0; x < NumeroCadeiaSerReconhecida; x++) {
            // entrando com a cadeia
            System.out.println("Digite a Cadeia a ser testada");
            String aCadeia = entrada.next();
            String estadoAtual = estadoInicial;
            String []fita = new String[aCadeia.length()+2];
            int contador_estado_final= 0;
            int contaWhile=0;
            
            fita[0] = "*";
            fita[fita.length-1] = "*";
            
            for (int i=0;i<aCadeia.length();i++){
                fita[i+1]=Character.toString(aCadeia.charAt(i));
            }
            
            int cabecote=1;
            while(true) {
                 
                for(int y=0;y<numeroTransicao;y++) {  
  
                    //acha a função de transição
                    if(estadoAtual.equals(tb_Mt[y][0])) { 
                            //System.out.println("estado da fita igual o da matriz");
                        //acha a função de transição
                        if(fita[cabecote].equals(tb_Mt[y][1])) {
                            //System.out.println("elemento da fita igual o da matriz");
                            //troca
                            fita[cabecote] = tb_Mt[y][3];
                            estadoAtual = tb_Mt[y][2];
                            if(tb_Mt[y][4] == "R"){
                                cabecote++;
                            }else if(tb_Mt[y][4] == "L"){
                                cabecote--;
                            }

                        }
                    }
                       
                }

                for(int z=0;z<numeroEstadosFianis;z++) {
                    if(estadoFinal[z].equals(estadoAtual)) {
                        contador_estado_final = 1;
                    }
                }

                if(contador_estado_final==1){
                    break;

                }
                if(contaWhile > fita.length*700){
                         break;
                }
                contaWhile++;
            }
        
    

   

    if(contador_estado_final==1)
            System.out.println("Cadeia [" + aCadeia + "] foi reconhecida");
    else
            System.out.println("Cadeia [" + aCadeia + "] não foi reconhecida");


    
    }

}

}
