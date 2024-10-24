package red_black;

public class RedBlackTree<T extends Comparable<T>>{
    private RedBlackNode<T> root, nil;
    public boolean black=true, red=false; //pra nao confundir

    RedBlackTree(){
        //setando o nil que sera o pai da raiz e os nos folhas
        nil= new RedBlackNode<T>(null);
        nil.setColour(black);
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void insert(T value){
        RedBlackNode<T> k=new RedBlackNode<T>(value);

        if(isEmpty()){
            k.setParent(nil);
            k.setColour(black); //pois eh raiz
            k.setLeft(nil);
            k.setRight(nil);
            this.root=k;

            System.out.println("\nInsercao efetuada.");
            return;
        }else{
            RedBlackNode<T> p=this.root;
            k.setColour(red); //pois k eh um no inserido && nao eh raiz

            while(true){
                if(value.compareTo(p.getInfo())==0){
                    if(p.getRemovido()){ //se o valor estiver removido, adicionar ele novamente
                        p.setRemovido(false);
                        System.out.println("\nInsercao efetuada.");
                        return;
                    }else{
                        System.out.println("\nValor repetido");
                        return;
                    }
                }else if(value.compareTo(p.getInfo())>0){
                    //caso o aux nao tenha filhos na direita
                    if(p.getRight().getInfo()==null){
                        p.setRight(k);
                        k.setParent(p);
                        k.setLeft(this.nil);
                        k.setRight(this.nil);
                        System.out.println("\nInsercao efetuada.");
                        break;
                    }else{
                        p=p.getRight();
                    }
                }else{
                    //caso o aux nao tenha filhos na esquerda
                    if(p.getLeft().getInfo()==null){
                        p.setLeft(k);
                        k.setParent(p);
                        k.setLeft(this.nil);
                        k.setRight(this.nil);
                        System.out.println("\nInsercao efetuada.");
                        break;
                    }else{
                        p=p.getLeft();
                    }
                }
            }
        }
    
        //corrigir propriedades
        correctInsert(k);
    
    }

    public void remocaoPreguicosa(T value){
        RedBlackNode<T> p=this.root;
        while(true && !isEmpty()){
            if(value.compareTo(p.getInfo())==0){
                if(p.getRemovido()){ //se o valor ja estiver removido
                    break;
                }else{
                    p.setRemovido(true);
                    System.out.println("\nValor removido");
                    return;
                }
            }else if(value.compareTo(p.getInfo())>0){
                //caso o aux nao tenha filhos na direita
                if(p.getRight().getInfo()==null){
                    break;
                }else{
                    p=p.getRight();
                }
            }else{
                if(p.getLeft().getInfo()==null){
                    break;
                }else{
                    p=p.getLeft();
                }
            }
        }

        System.out.println("\nValor nao se econtra na arvore.");
    }

    private void correctInsert(RedBlackNode<T> k){
        while(k!=this.root && k.getParent().getColour()==red){ //pois se o pai for preto, nao eh necessario alteracoes
            RedBlackNode<T> s, p=k.getParent(), g=k.getParent().getParent();
            if(p==g.getLeft()){ //se o pai for o no esquerdo
                s=g.getRight(); //tio de k
                if(s.getColour()==black){ //se tio for preto (funciona se for nulo pois a cor de nil eh preto)
                    if(k==p.getRight()){ //rotacao dupla
                        k=p;
                        rotateLeft(k);
                        p=k.getParent();
                        g=p.getParent();
                    }
                    p.setColour(black);
                    g.setColour(red);
                    rotateRight(g);
                }
                else{//se o tio for vermelho
                    p.setColour(black);
                    s.setColour(black);
                    g.setColour(red);
                    k=g;
                }
            }
            else{
                s=g.getLeft(); //tio de k
                if(s.getColour()==black){ //se tio for preto (funciona se for nulo pois a cor de nil eh preto)
                    if(k==p.getLeft()){ //rotacao dupla
                        k=p;
                        rotateRight(k);
                        p=k.getParent();
                        g=p.getParent();
                    }
                    p.setColour(black);
                    g.setColour(red);
                    rotateLeft(g);

                }else{//se o tio for vermelho
                    p.setColour(black);
                    s.setColour(black);
                    g.setColour(red);
                    k=g;
                }
            }
            this.root.setColour(black);
        }
    }

    private void rotateRight(RedBlackNode<T> a){

        RedBlackNode<T> b=a.getLeft();
        
        a.setLeft(b.getRight()); //filho esquerdo de b vira filho direito de a
        if(b.getRight()!=null && b.getLeft()!=nil){ //setparent se o filho de b nao for nulo
            b.getRight().setParent(a);
        }
        if(a==this.root){
            this.root=b;
            b.setParent(this.nil);
        }else if(a==a.getParent().getLeft()){
            a.getParent().setLeft(b);
            b.setParent(a.getParent());
        }else{
            a.getParent().setRight(b);
            b.setParent(a.getParent());
        }

        b.setRight(a);
        a.setParent(b);
        
    }

    private void rotateLeft(RedBlackNode<T> a){
        RedBlackNode<T> b=a.getRight();
        
        a.setRight(b.getLeft()); //filho esquerdo de b vira filho direito de a
        if(b.getLeft()!=null && b.getLeft()!=nil){ //setparent se o filho de b nao for nulo
            b.getLeft().setParent(a);
        }
        if(a==this.root){
            this.root=b;
            b.setParent(this.nil);
        }else if(a==a.getParent().getLeft()){
            a.getParent().setLeft(b);
            b.setParent(a.getParent());
        }else{
            a.getParent().setRight(b);
            b.setParent(a.getParent());
        }

        b.setLeft(a);
        a.setParent(b);
    }

    public void passearEmOrdem(){
        GenericStack<RedBlackNode<T>> s=new GenericStack<RedBlackNode<T>>(this.root);
        RedBlackNode<T> node=this.root;

        while(!s.isEmpty()){
            if(node.getLeft().getInfo()==null){
                node=s.pop();
                if(!node.getRemovido()){ //apenas printa caso nao esteja removido
                    System.out.printf("Info: %d, ",node.getInfo());
                    if(node.getColour()==black){
                        System.out.printf("Cor: preto\n");
                    }else{
                        System.out.printf("Cor: vermelho\n");
                    }
                }
                
                if(node.getRight().getInfo()!=null){
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

    public void passearPorNivel(){
        GenericQueue<RedBlackNode<T>> q=new GenericQueue<>(this.root);
        while(!q.isEmpty()){
            RedBlackNode<T> node=q.dequeue();

            if(node.getInfo()==null){
                System.out.println("null");
                continue;
            }
            else{
                if(node.getRemovido()){
                    System.out.printf("(Removido) ");
                }
                System.out.printf("Info: %d, ",node.getInfo());
                if(node.getColour()==black){
                    System.out.printf("Cor: preto\n");
                }else{
                    System.out.printf("Cor: vermelho\n");
                }
            }

            q.enqueue(node.getLeft());
            q.enqueue(node.getRight());
        }
    }
}

class RedBlackNode<T extends Comparable<T>>{
    private RedBlackNode<T> left, right, parent;
    private boolean colour; //true= black, false= red;
    private T info;
    private boolean removido; //true= removido, false= esta na arvore

    RedBlackNode(T info){
        this.info=info;
        this.removido=false;
    }

    void setRemovido(boolean b){
        this.removido=b;
    }
    boolean getRemovido(){
        return this.removido;
    }

    void setInfo(T info){
        this.info=info;
    }
    T getInfo(){
        return this.info;
    }

    void setLeft(RedBlackNode<T> left){
        this.left=left;
    }
    RedBlackNode<T> getLeft(){
        return this.left;
    }

    void setRight(RedBlackNode<T> right){
        this.right=right;
    }
    RedBlackNode<T> getRight(){
        return this.right;
    }

    void setParent(RedBlackNode<T> parent){
        this.parent=parent;
    }
    RedBlackNode<T> getParent(){
        return this.parent;
    }

    void setColour(boolean colour){
        this.colour=colour;
    }
    boolean getColour(){
        return colour;
    }

}