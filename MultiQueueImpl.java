package p12.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.lang.Throwable;
import java.util.Iterator;

public class MultiQueueImpl<T,Q> implements MultiQueue<T,Q> {

    private Map<Q,Queue<T>> map;

 public  MultiQueueImpl(){
        this.map = new HashMap<>();
    }

    public Set<Q> availableQueues() {
        try {
            // Controlla se la mappa è null
            if (map == null) {
                throw new IllegalStateException("La mappa delle code non esiste.");
            }
            // Restituisce il set delle chiavi della mappa se non è null
            return map.keySet();
        } catch (Exception e) {
            // Rilancia l'eccezione catturata per una gestione futura
            throw e;
        }
    }


    public void openNewQueue(Q queue) {
        try {
            if (map.containsKey(queue)){
            throw new IllegalArgumentException("il nome della coda esiste già all'interno della mappa");
            }
            map.put(queue, new LinkedList<T>());   
        } catch (Exception e) {
            throw e;
        }

       
    }


    public boolean isQueueEmpty(Q queue) {
        try {
            if (!map.containsKey(queue)){
            throw new IllegalArgumentException("la coda non è presente nella mappa");
            }
            if (this.map.get(queue).isEmpty()){
                return true;
            }
            else{
                return false;
            }   
        } catch (Exception e) {
            throw e;
        }
       
        
    }


    public void enqueue(T elem, Q queue) {
        try{
           if(!map.containsKey(queue)){
           throw new IllegalArgumentException ("la coda non è presente nella mappa");
        }
    
        if(map.containsKey(queue)){
            this.map.get(queue).add(elem);
        }
    }
        catch(Exception e){
            throw e;
        }
        
      
    }

  
    public T dequeue(Q queue) {
        try{
            if(!map.containsKey(queue)){
            throw new IllegalArgumentException ("la coda non è presente nella mappa");
         }
     
         if(!map.get(queue).isEmpty()){
            return this.map.get(queue).poll();
             }
     
             else return null;
         }
         catch(Exception e){
             throw e;
         }
     }
        


    public Map<Q, T> dequeueOneFromAllQueues() {
        Map<Q,T> deq=new TreeMap<>();
        for (Q queue : map.keySet()) {
            deq.put(queue,map.get(queue).poll());
        }

        return deq;
    }


    public Set<T> allEnqueuedElements() {
        Set<T> enqSet = new TreeSet<>();
        for (Q queue : map.keySet()) {
            enqSet.addAll(map.get(queue));
        }
        return enqSet;

    }

    public List<T> dequeueAllFromQueue(Q queue) {
       List<T>Alldeq= new ArrayList<>();
       while(!isQueueEmpty(queue)){
        Alldeq.add(this.map.get(queue).poll());
       }
       return Alldeq;

    }
  
    public void closeQueueAndReallocate(Q queue) {
        //controlla che mappa abbia almeno 2 code
        //controlla che la coda esiste
        /**
     * Empties a queue and move all of its elements in some other available queue
     * @param , the queue to be emptied
     * @throws IllegalArgumentException if queue is not available
     * @throws IllegalStateException if there's no alternative queue for moving elements to
     */
    try{
    int count=0;
        Iterator<Entry<Q, Queue<T>>> i = map.entrySet().iterator();
        while(i.hasNext()){
            i.next();
            count++;
        }
        if(count<2){
            throw new IllegalStateException ("non è presente un'altra dove inserire gli elementi");
            }
            if(!map.containsKey(queue)){
                throw new IllegalArgumentException ("la coda non è presente nella mappa");
    }
        Iterator<Entry<Q, Queue<T>>> k = map.entrySet().iterator();       
                if (count>=2){
                    while(k.hasNext()){
                        if(k.next().getKey()!=queue){
                            map.get(k.next().getKey()).addAll(map.get(queue));
                            break;
                          
                        }
                      
                    }
        
                }
               dequeueAllFromQueue(queue);
               map.remove(queue);
    }
        catch(Exception e){
            throw e;
        }
    }
}
    
        
    


