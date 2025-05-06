package Automato;

import java.util.Scanner;

public class automatoFinito {

	public int quantEstados;
	public int numSimbolos;
	
	
	/*		
	 * 		a	b
	 *	q0	0	1
	 * 	q1	2	1
	 * 	q2	2	0
	 * 
	 */
	
	
	public void CriaAutomato(int quantEstados,int numSimbolos) {
		Scanner entrada = new Scanner(System.in);
		String[][] automato = new String[quantEstados][numSimbolos];
		String listaSimbolos [] = new String [numSimbolos];
		
		System.out.println("Qual a quantidade de estados que � final?");
		int NumEstadofinal = entrada.nextInt();
		int [] estadoFinal = new int [NumEstadofinal];
		
		
		//entrada dos estados finais
		for (int i=0; i<NumEstadofinal;i++) {
			System.out.println("Qual o Estado final " + (i+1) + " ?");
			estadoFinal [i] = entrada.nextInt();
		}
		
		//Entrada dos simbolos
		for (int i=0; i<numSimbolos;i++) {
			System.out.println("Qual o simbolo numero " + (i+1));
			listaSimbolos[i] = entrada.next();
		}
		
		
		// criando automato 
		for (int i=0; i<quantEstados;i++) {
			for (int j=0;j<numSimbolos;j++) {
				System.out.println("Q" + i + " quando ver o simbolo [" +listaSimbolos[j]+ "] vai para qual estado?");
				automato[i][j] = entrada.next();
							
			}
		}
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//Definindo o numero de cadeias a ser reconhecida
		int NumeroCadeiaSerReconhecida;
		System.out.println("Digite a quantidade de cadeias a ser testada");
		NumeroCadeiaSerReconhecida = entrada.nextInt();
		
		
		
		
		for(int x=0; x<NumeroCadeiaSerReconhecida;x++) {
			//entrando com a cadeia
			System.out.println("Digite a Cadeia a ser testada");
			String aCadeia = entrada.next();
			int estadoAtual = 0;
			int PercorreCadeia = 0;
			
			//reconhecendo a cadeia
			for (int i=0;i<aCadeia.length();i++) {			
				
				for  (int j=0;j<numSimbolos;j++) {
					
					if(automato[estadoAtual][j] != "n") {
						
						if(Character.toString(aCadeia.charAt(PercorreCadeia)).equals(listaSimbolos[j])) { /// se o simbolo for igual tem que passar pro proximo estado
							estadoAtual = Integer.parseInt(automato[estadoAtual][j]);	
							PercorreCadeia++;
							break;
						}
					}
				}
			}
		
			
			// verifica se a cadeia � reconhecida
			boolean reconhece = false;
			for (int i=0;i<NumEstadofinal;i++) {
				if(estadoFinal[i]==estadoAtual) {
					reconhece = true;
				}
					
			}
			
			//mostra se foi reconhecida ou no
			if (reconhece) {
				System.out.println("A Cadeia [" + aCadeia + "] foi reconhecida");
			}else {
				System.out.println("A Cadeia [" + aCadeia + "] n�o foi reconhecida");
			}
			
		}
	
	}
	
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		automatoFinito aut = new automatoFinito();
		
		System.out.println("qual a quantidade de Estados?");
		aut.quantEstados = entrada.nextInt();
		
		System.out.println("qual a quantidade de Simbolos?");
		aut.numSimbolos = entrada.nextInt();
		
		aut.CriaAutomato(aut.quantEstados, aut.numSimbolos);

	}
}
