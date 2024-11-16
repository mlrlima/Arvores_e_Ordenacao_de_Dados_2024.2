package b;

public class BTree<T extends Comparable<T>>{
    private BNode<T> root;
    private int m;

    BTree(int m){
        this.m=m;
        this.root=new BNode<T>(null);
    }

    public boolean isEmpty(){
        if(this.root.getN()==0){
            return true;
        }

        return false;
    }

    public void inserir(T value){
        if(isEmpty()){
            this.root.setInfo(0, value);
            this.root.setN(1);
            System.out.printf("\nValor inserido.\n");
            //System.out.println(this.root.getInfo(0));
            return;
        }

        BNode<T> aux=this.root;

        //procurar node
        while(aux.getFilho(0)!=null){ //se nao tiver filho quer dizer q eh folha
            for(int i=0;i<aux.getN();i++){
                if(value.compareTo(aux.getInfo(i))==0){
                    System.out.printf("\nValor repetido.\n");
                    return;
                }
                if(value.compareTo(aux.getInfo(i))<0){
                    aux=aux.getFilho(i);
                    break;
                }else if(i==aux.getN()-1){
                    aux=aux.getFilho(aux.getN());
                    break;
                }
            }
        }

        //procurar local no node
        for(int i=0; i<aux.getN();i++){
            if(value.compareTo(aux.getInfo(i))==0){
                System.out.printf("\nValor repetido.\n");
                return;
            }
            if(value.compareTo(aux.getInfo(i))<0){
                T valueAux=aux.getInfo(i);
                aux.setInfo(i, value);
                value=valueAux;
            }
        }
        aux.setInfo(aux.getN(), value);
        aux.setN(aux.getN()+1);
        if(aux.getN()==this.m){
            cisao(aux);
        }
    }

    public void cisao(BNode<T> aux){
        if(aux==this.root && root.getFilho(0)==null){ //se for a primeira cisao
            int m2=m/2;
            BNode<T> esq=new BNode<>(aux.getInfo(0));
            for(int i=1;i<m2;i++){ //exemplo m=5: i vai de 1 ate 1, pois 5/2=2
                esq.setInfo(i, aux.getInfo(i));
            }
            esq.setN(m2);

            BNode<T> dir=new BNode<>(aux.getInfo(m2+1));
            for(int i=m2+2;i<aux.getN();i++){
                dir.setInfo(i-(m2+1), aux.getInfo(i));
            }
            dir.setN(m2);

            aux.setInfo(0, aux.getInfo(m2));
            aux.setN(1);

            aux.setFilho(esq, 0);
            aux.setFilho(dir, 1);
        }

        while(aux.getN()==this.m){
            int m2=m/2;
            BNode<T> dir=new BNode<>(aux.getInfo(m2+1));
            for(int i=m2+2;i<aux.getN();i++){
                dir.setInfo(i-(m2+1), aux.getInfo(i));
            }
            dir.setN(m2);

            aux.setN(m2);
            T value=aux.getInfo(m2); //valor q vai subir

            aux=getParent();
        }
    }

    public void passeioPorNivel(){
        if(isEmpty()){
            System.out.printf("\nArvore vazia.\n");
            return;
        }

        System.out.println("Printando arvore:\n");
        GenericQueue<BNode<T>> q=new GenericQueue<>(this.root);
        while(!q.isEmpty()){
            BNode<T> node=q.dequeue();

            for(int i=0;i<node.getN();i++){
                if(node.getFilho(i)!=null){
                    q.enqueue(node.getFilho(i));
                }

                System.out.printf(node.getInfo(i)+" ");
            }
            System.out.printf("\n");
            if(node.getFilho(node.getN())!=null){
                q.enqueue(node.getFilho(node.getN()));
            }
        }
    }
}
