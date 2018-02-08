import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code Queue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the top item,
 *  testing if the queue is empty, getting the number of items in the
 *  queue, and iterating over the items in FIFO order.
 *  <p>
 *  This implementation uses a singly-linked list with a nested class for
 *  linked-list nodes.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/43stack">Section 4.3</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of an item in this queue
 */
public class Queue<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;     
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int length() {
        return n;     
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

   /**
     * Add the item to the queue.
     */
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 
 

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    // shuffle() baseada em um algoritmo divide-and-conquer;
    // Atualizar o sucessor de qualquer elemento da lista necessáriamente
    // leva tempo O(n), característico de uma lista ligada.
    // podemos assumir que ao atulializar um elemento,
    // estamos atualizando todos os outros (alterando-os ou não)
    // o que também deve custar O(n).
    // Como existem n! possíveis configurações para a queue final
    // temos n! = O(n^n) = O(C^(n log (n))) combinaçoes
    // portanto, é esperado que sejam necessários pelo menos
    // n atualizações de custo O(log n);
    public void shuffle(){
        if(this.size()==1) return;
        Queue<Item> q1 = new Queue<Item>();
        Queue<Item> q2 = new Queue<Item>();

        while(!this.isEmpty()){
            q1.enqueue(this.dequeue());
            if(!this.isEmpty()){
                q2.enqueue(this.dequeue());
            }
        }

        q1.shuffle();
        q2.shuffle();

        // Aqui, realizamos o merge das duas listas,
        // de forma aleatória, o que nos garante
        // a aleatoriedade no resultado final,
        // devido a natureza recursiva da função.
        while(!q1.isEmpty() && !q2.isEmpty()){
            if(Math.random() > .5){
                this.enqueue(q1.dequeue());
            }
            else{
                this.enqueue(q2.dequeue());
            }
        }

        if(!q1.isEmpty()){
            for(Item item : q1){
                this.enqueue(q1.dequeue());
            }
        }
        if(!q2.isEmpty()){
            for(Item item : q2){
                this.enqueue(q2.dequeue());
            }
        }
    }


    /**
     * Unit tests the {@code Queue} data type.
     */
    // função main modificada para testar shuffle()
    // recebe uma lista de strings da entrada padrão,
    // embaralha elas e imprime o resultado.
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
        }
        queue.shuffle();
        StdOut.println(queue);
    }
}
