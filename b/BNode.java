package b;
import java.util.ArrayList;

public class BNode<T extends Comparable<T>>{
    private int n; //qtd de preenchidos
    private ArrayList<T> infos;
    private ArrayList<BNode<T>> filhos;

    BNode(T info){
        this.n=0;

        this.infos=new ArrayList<>();
        this.filhos=new ArrayList<>();

        this.infos.add(info);

    }

    public void setN(int n){
        this.n=n;
    }
    public int getN(){
        return this.n;
    }

    public void setInfo(int i, T info){
        //System.out.println("*i: "+i);
        //System.out.println("*info: "+info);
        //System.out.println("*size: "+infos.size());
        if(infos.size()<=i){
            this.infos.add(info);
        }else{
            this.infos.set(i,info);
        }
    }
    public T getInfo(int i){
        return this.infos.get(i);
    }

    public void setFilho(BNode<T> filho, int i){
        if(filhos.size()<=i){
            this.filhos.add(filho);
        }else{
            this.filhos.set(i,filho);
        }
    }
    public BNode<T> getFilho(int i){
        if(filhos.size()<=i){
            return null;
        }
        return this.filhos.get(i);
    }

}
