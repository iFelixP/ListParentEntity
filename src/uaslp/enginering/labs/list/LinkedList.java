package uaslp.enginering.labs.list;

import uaslp.enginering.labs.model.Student;


public class LinkedList implements List{

    public class Iterator implements uaslp.enginering.labs.list.Iterator{
        private Node auxNode;

        public Iterator(){
            this.auxNode = front;
        }

        public boolean hasNext() {
            return auxNode != null;
        }

        public Student next() {
            if(auxNode == null){
                return null;
            }
            Student auxStudent = auxNode.getStudent();
            auxNode = auxNode.getNext();

            return auxStudent;
        }
    }

    private Node front;
    private Node tail;
    private int size;

    public void add(Student student) {
        Node newNode = new Node(student);

        if(size == 0){
            front = tail = newNode;
        } else{
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }

        size++;
    }

    public void delete(Student element){}

    public void delete(int index) {

        if(index < 0 || index >= size){
            return;
        }

        Node auxNode = front;

        for(int counter = 0 ; counter < index && counter < size ; counter++){
            auxNode = auxNode.getNext();
        }

        if(auxNode == front && auxNode == tail){
            front = null;
            tail = null;
        } else if(auxNode == front){
            front = auxNode.getNext();
            front.setPrevious(null);
        } else if(auxNode == tail){
            tail = auxNode.getPrevious();
            tail.setNext(null);
        } else{
            auxNode.getPrevious().setNext(auxNode.getNext());
            auxNode.getNext().setPrevious(auxNode.getPrevious());
        }
        size--;
    }

    public Iterator getIterator() {
        return new Iterator();
    }

    public int size() {
        return size;
    }

    public Student getAt(int index) {
        Node auxNode = front;

        for(int counter = 0 ; counter < index && counter < size ; counter++){
            auxNode = auxNode.getNext();
        }

        return auxNode.getStudent() != null ? auxNode.getStudent() : null;
    }

    public void insert(Student reference, Student newStudent, InsertPosition insertPosition) {
        Node auxNode = front;

        if (size == 0) {
            return;
        }
        while (!auxNode.getStudent().equals(reference)) {
            auxNode = auxNode.getNext();
        }

        if (auxNode == null) {
            return;
        }

        Node newNode = new Node(newStudent);

        if(InsertPosition.BEFORE.equals(insertPosition)){
            Node previous = auxNode.getPrevious();
            newNode.setNext(auxNode);
            auxNode.setPrevious(newNode);

            if(auxNode == front){
                front = newNode;
            } else{
                previous.setNext(newNode);
                newNode.setPrevious(previous);
            }

        } else{
            Node next = auxNode.getNext();
            newNode.setPrevious(auxNode);
            auxNode.setNext(newNode);

            if(auxNode == tail){
                tail = newNode;
            } else{
                next.setPrevious(newNode);
                newNode.setNext(next);
            }
        }

        size++;
    }
}
