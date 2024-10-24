package avl;

public class AVLStack <T extends Comparable<T>>{
    private StackNode<T> last;

    AVLStack(AVLNode<T> node){
        StackNode<T> n=new StackNode<T>(node);
        this.last=n;
    }

    public boolean isEmpty(){
        return this.last==null;
    }

    public void push(AVLNode<T> node){
        StackNode<T> snode=new StackNode<T>(node);

        if(isEmpty()){
            this.last=snode;
            return;
        }

        snode.setPrevious(this.last);
        this.last=snode;
    }

    public AVLNode<T> pop(){
        StackNode<T> r=this.last;
        this.last=r.getPrevious();
        return r.getNode();
    }

}

class StackNode<T extends Comparable<T>>{
    private AVLNode<T> node;
    private StackNode<T> previous;

    StackNode(AVLNode<T> node){
        this.node=node;
    }

    AVLNode<T> getNode(){
        return this.node;
    }

    void setNode(AVLNode<T> node){
        this.node=node;
    }

    StackNode<T> getPrevious(){
        return this.previous;
    }

    void setPrevious(StackNode<T> previous){
        this.previous=previous;
    }
}
