package b;

public class GenericQueue <T>{
    private QueueNode<T> first;

    GenericQueue(T node){
        QueueNode<T> n=new QueueNode<T>(node);
        this.first=n;
    }

    public boolean isEmpty(){
        return this.first==null;
    }

    public void setFirst(QueueNode<T> node){
        this.first=node;
    }

    public void enqueue(T node){
        QueueNode<T> qnode=new QueueNode<T>(node);
        QueueNode<T> aux;

        if(isEmpty()){
            this.first=qnode;
            return;
        }

        aux=this.first;
        while(aux.getNext()!=null){
            aux=aux.getNext();
        }

        aux.setNext(qnode);
    }

    public T dequeue(){
        QueueNode<T> r=this.first;
        setFirst(r.getNext());
        return r.getNode();
    }

}

class QueueNode<T>{
    private T node;
    private QueueNode<T> next;

    QueueNode(T node){
        this.node=node;
    }

    void setNode(T node){
        this.node=node;
    }
    T getNode(){
        return this.node;
    }

    void setNext(QueueNode<T> next){
        this.next=next;
    }
    QueueNode<T> getNext(){
        return this.next;
    }
}
