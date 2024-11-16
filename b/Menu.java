package b;
import java.util.*;

public class Menu{
    private static void printarMenu(){
        System.out.printf("\nEscolha sua opcao: \n");
        System.out.printf("0- fechar programa\n1- inserir valor\n2- exibir maior valor\n");
        System.out.printf("3- exibir menor valor\n4- exibir altura\n5- procurar valor\n");
        System.out.printf("6- printar por nivel\n7- printar em ordem\n8- remover valor\n");

    }

    public static void main(String[] args){
        System.out.printf("\n###############\n");

        Scanner s=new Scanner(System.in);
        int op;


        BTree<Integer> arvore= new BTree<>(5);

        int[] numeros={10, 2, 15,25,7,8,16,32,4,1,17,24};
        int i=0;


        do{
            Integer num;
            int n;

            printarMenu();
            op=s.nextInt();

            switch(op){
                case 0: break;
                //adicionar na arvore
                case 1:
                    /*System.out.printf("\nDigite o numero para adicionar: \n");
                    n=s.nextInt();
                    num=Integer.valueOf(n);
                    arvore.inserir(num);
                    */

                    arvore.inserir(Integer.valueOf(numeros[i]));
                    System.out.printf("\nNumero adicionado: %d\n",numeros[i]);
                    i++;

                    break;

                //printar por nivel
                case 6:
                    arvore.passeioPorNivel();
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
