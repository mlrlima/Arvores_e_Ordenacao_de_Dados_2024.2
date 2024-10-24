package avl;

public class AVLQueue <T extends Comparable<T>>{
    private QueueNode<T> first;
    private int size;

    AVLQueue(AVLNode<T> node){
        QueueNode<T> n=new QueueNode<T>(node);
        this.first=n;
        this.size=1;
    }

    public boolean isEmpty(){
        if(this.size==0){
            return true;
        }

        return false;
    }

    public void setFirst(QueueNode<T> node){
        this.first=node;
    }

    public void enqueue(AVLNode<T> node){
        QueueNode<T> qnode=new QueueNode<T>(node);
        QueueNode<T> aux;

        this.size++;
        if(this.size==1){
            this.first=qnode;
            return;
        }

        aux=this.first;
        for(int i=2;i<this.size;i++){
            aux=aux.getNext();
        }

        aux.setNext(qnode);
    }

    public AVLNode<T> dequeue(){
        QueueNode<T> r=this.first;
        setFirst(r.getNext());
        this.size--;
        return r.getNode();
    }

}

class QueueNode<T extends Comparable<T>>{
    private AVLNode<T> node;
    private QueueNode<T> next;

    QueueNode(AVLNode<T> node){
        this.node=node;
    }

    AVLNode<T> getNode(){
        return this.node;
    }

    void setNode(AVLNode<T> node){
        this.node=node;
    }

    QueueNode<T> getNext(){
        return this.next;
    }

    void setNext(QueueNode<T> next){
        this.next=next;
    }
}
