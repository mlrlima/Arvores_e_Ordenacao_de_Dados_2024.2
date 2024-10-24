/*
Nome: Maria Luiza Ribeiro -RA: 00000848982

Referencias: 
- Slides das aulas
- Livro: Algoritmos: teoria e pratica - Thomas Cormen
- https://www.programiz.com/dsa/red-black-tree
- https://www.geeksforgeeks.org/introduction-to-red-black-tree/
*/

package red_black;

import java.util.*; //para scanner

public class Menu{

    static void menu(){
        System.out.printf("\nEscolha sua opcao: \n");
        System.out.printf("0- fechar programa\n1- adicionar numero\n2- printar em ordem\n");
        System.out.printf("3- printar por nivel\n4- remocao (preguicosa)\n");
    }
    public static void main(String[] args){

        Scanner s=new Scanner(System.in);

        System.out.printf("\n###############\n");

        RedBlackTree<Integer> arvore=new RedBlackTree<>();

        int op;

        do{

            Integer num;
            int n;

            menu();
            op=s.nextInt();

            switch(op){
                case 0: break;
                //adicionar na arvore
                case 1:
                    System.out.printf("\nDigite o numero para adicionar: \n");
                    n=s.nextInt();
                    num=Integer.valueOf(n);
                    arvore.insert(num);
                    break;

                //printar em ordem
                case 2:
                    if(arvore.isEmpty()){
                        System.out.println("\nArvore vazia");
                    }else{
                        System.out.printf("\nPrintando em ordem: \n");
                        arvore.passearEmOrdem();
                    }
                    break;

                //printar por nivel
                case 3:
                    if(arvore.isEmpty()){
                        System.out.println("\nArvore vazia");
                    }else{
                        System.out.printf("\nPrintando por nivel: \n");
                        arvore.passearPorNivel();
                    }
                    break;
                
                //remocao preguicosa
                case 4:
                    System.out.printf("\nDigite o numero para remover: \n");
                    n=s.nextInt();
                    num=Integer.valueOf(n);
                    arvore.remocaoPreguicosa(num);
                    break;

                default:
                    System.out.println("\nEscolha uma opcao disponivel");
                    break;
            }

        }while(op!=0);

        System.out.printf("\n###############\n");

        s.close();

    }
}
