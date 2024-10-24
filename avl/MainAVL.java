package avl;

import java.util.*;

public class MainAVL{

    static void menu(){
        System.out.printf("\nEscolha sua opcao: \n");
        System.out.printf("0- fechar programa\n1- adicionar numero\n2- printar em ordem\n");
        System.out.printf("3- printar por nivel\n4- altura\n");
    }
    public static void main(String[] args){

        Scanner s=new Scanner(System.in);

        System.out.printf("\n###############\n");

        AVLTree<Integer> arvore=new AVLTree<>();
        //ABBNode n=new ABBNode(6);

        int op;

        do{

            Integer num;
            int n;

            menu();
            do{
                op=s.nextInt();
            }while(op<0 && op>13);

            switch(op){
                //adicionar na arvore
                case 1:
                    System.out.printf("\nDigite o numero para adicionar: \n");
                    n=s.nextInt();
                    num=Integer.valueOf(n);
                    arvore.insert(num);
                    break;

                //printar em ordem
                case 2:
                    System.out.printf("\nPrintando em ordem: \n");
                    arvore.passearEmOrdem();
                    break;

                //printar por nivel
                case 3:
                    System.out.printf("\nPrintando por nivel: \n");
                    arvore.passearPorNivel();
                    break;
                
                //altura
                case 4:
                    System.out.printf("\nAltura: \n");
                    arvore.calcularAlturaArvore();
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
