package red_black;

public class GenericStack <T>{
    private StackNode<T> last;

    GenericStack(T node){
        StackNode<T> n=new StackNode<T>(node);
        this.last=n;
    }

    public boolean isEmpty(){
        return this.last==null;
    }

    public void push(T node){
        StackNode<T> snode=new StackNode<T>(node);

        if(isEmpty()){
            this.last=snode;
            return;
        }

        snode.setPrevious(this.last);
        this.last=snode;
    }

    public T pop(){
        StackNode<T> r=this.last;
        this.last=r.getPrevious();
        return r.getNode();
    }

}

class StackNode<T>{
    private T node;
    private StackNode<T> previous;

    StackNode(T node){
        this.node=node;
    }

    void setNode(T node){
        this.node=node;
    }
    T getNode(){
        return this.node;
    }

    StackNode<T> getPrevious(){
        return this.previous;
    }
    void setPrevious(StackNode<T> previous){
        this.previous=previous;
    }
}
