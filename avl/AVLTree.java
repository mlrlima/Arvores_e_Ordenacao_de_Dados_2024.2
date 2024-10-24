package avl;

public class AVLTree<T extends Comparable <T>>{
    private AVLNode<T> root;
    private boolean status;

    private boolean isEmpty(){
        return root==null;
    }

    public void insert(T value){
        if(this.isEmpty()){
            this.root=new AVLNode<T> (value);
        }
        else{
            this.root=insertNode(this.root,value);
            this.status=false;
        }
    }

    private AVLNode<T> insertNode(AVLNode<T> r, T value){
        if(r==null){
            r=new AVLNode<T> (value);
            this.status=true;
        }
        else if(r.getInfo().compareTo(value)>0){
            r.setLeft(insertNode(r.getLeft(), value));
            if(this.status){
                switch(r.getFatBal()){
                    case 1: 
                        r.setFatBal(0);
                        this.status=false;
                        break;
                    case 0:
                        r.setFatBal(-1);
                        break;
                    case -1:
                        r=this.rotateRight(r);
                        break;
                }
            }
        }
        else{
            r.setRight(insertNode(r.getRight(),value));
            if(this.status){
                switch(r.getFatBal()){
                    case -1:
                        r.setFatBal(0);
                        this.status=false;
                        break;
                    case 0:
                        r.setFatBal(1);
                        break;
                    case 1:
                        r=this.rotateLeft(r);
                        break;
                }
            }
        }

        return r;
    }

    private AVLNode<T> rotateRight(AVLNode<T> a){
        AVLNode<T> b,c;
        b=a.getLeft();
        if(b.getFatBal()==-1){
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setFatBal(0);
            a=b;
        }
        else{
            c=b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);

            if(c.getFatBal()==-1){
                a.setFatBal(1);
            }
            else{
                a.setFatBal(0);
            }

            if(c.getFatBal()==1){
                b.setFatBal(-1);
            }
            else{
                b.setFatBal(0);
            }

            a=c;
        }

        a.setFatBal(0);
        this.status=false;
        return a;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> a){
        AVLNode<T> b,c;
        b=a.getRight();
        if(b.getFatBal()==1){
            a.setRight(b.getLeft());
            b.setLeft(a);
            a.setFatBal(0);
            a=b;
        }
        else{
            c=b.getLeft();
            b.setLeft(c.getRight());
            c.setRight(b);
            a.setRight(c.getLeft());
            c.setLeft(a);

            if(c.getFatBal()==1){
                a.setFatBal(-1);
            }
            else{
                a.setFatBal(0);
            }

            if(c.getFatBal()==-1){
                b.setFatBal(1);
            }else{
                b.setFatBal(0);
            }

            a=c;
        }

        a.setFatBal(0);
        this.status=false;
        return a;
    }

    public void passearEmOrdem(){
        AVLStack<T> s=new AVLStack<T>(this.root);
        AVLNode<T> node=this.root;

        while(!s.isEmpty() && node!=null){
            if(node.getLeft()==null){
                node=s.pop();
                System.out.println(node.getInfo().toString());
                if(node.getRight()!=null){
                    node=node.getRight();
                    s.push(node);
                }
            }
            else{
                node=node.getLeft();
                s.push(node);
            }
        }
        
    }

    public void calcularAlturaArvore(){

        int dois=1, cont=0, altura=0;

        AVLQueue<T> q=new AVLQueue<T>(this.root);
        while(!q.isEmpty()){
            AVLNode<T> node=q.dequeue();

            cont++;
            if(cont>=dois){
                altura++;
                dois*=2;
                cont=0;
            }

            if(node.getLeft()!=null){
                q.enqueue(node.getLeft());
            }
            else{
                cont++;
                if(cont>=dois){
                    altura++;
                    dois*=2;
                    cont=0;
                }
            }

            if(node.getRight()!=null){
                q.enqueue(node.getRight());
            }
            else{
                cont++;
                if(cont>=dois){
                    altura++;
                    dois*=2;
                    cont=0;
                }
            }
        }

        if(cont>0){
            altura++;
        }

        System.out.println(altura-1);
    }

    public void passearPorNivel(){
        AVLQueue<T> q=new AVLQueue<T>(this.root);
        while(!q.isEmpty()){
            AVLNode<T> node=q.dequeue();

            if(node==null){
                System.out.println("null");
                continue;
            }
            else{
                System.out.println(node.getInfo());
            }

            q.enqueue(node.getLeft());
            q.enqueue(node.getRight());
        }
    }
    
}

class AVLNode<T extends Comparable <T>>{
    private AVLNode<T> left, right;
    private T info;
    private int fatBal,altura;

    AVLNode(T info){
        this.info=info;
    }

    void setInfo(T info){
        this.info=info;
    }
    T getInfo(){
        return this.info;
    }

    void setLeft(AVLNode<T> left){
        this.left=left;
    }
    AVLNode<T> getLeft(){
        return this.left;
    }

    void setRight(AVLNode<T> right){
        this.right=right;
    }
    AVLNode<T> getRight(){
        return this.right;
    }

    void setFatBal(int fatBal){
        this.fatBal=fatBal;
    }
    int getFatBal(){
        return this.fatBal;
    }

    void setAltura(int altura){
        this.altura=altura;
    }
    int getAltura(){
        return this.altura;
    }

}