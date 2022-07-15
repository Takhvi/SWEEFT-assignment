package com.example.java;

//the following class uses is a custom implementation of a hash map
//it uses an array of the linked lists to store the elements (key-value pairs)
//it works in an amortized o(1) complexity
public class CustomHashMap {
    //counter for the number of elements
    //if the counter goes over 3/4 of the array size, the array size is doubled
    private int counter;

    //the array of linked lists to store the elements
    private Node[] buckets;

    //the default constructor with the size of 32
    public CustomHashMap() {
        buckets = new Node[32];
    }

    //the constructor with a customizable size
    public CustomHashMap(int initialSize) {
        buckets = new Node[initialSize];
    }

    //the function that enables inserting a new element
    public void put(Object key, Object value) {
        if (counter < buckets.length / 4 * 3) { //inserting element without resizing
            insertNew(key, value, buckets);
            counter++;
        } else {                            //inserting element after resizing
            resize();                       //when the number of elements goes over 3/4 of the array size
            insertNew(key, value, buckets);
            counter++;
        }
    }

    //private function responsible for inserting a new element
    //used by put and resize functions
    private void insertNew(Object key, Object value, Node[] map) {
        //calculate index for the new element
        int calculatedIndex = calculateIndex(key, map);
        //if there is no element on the index, create one that begins the linked list
        if (map[calculatedIndex] == null) {
            map[calculatedIndex] = new Node();
            map[calculatedIndex].setKey(key);
            map[calculatedIndex].setValue(value);
        } else {
            //iterate over the linked list to put the new element at the end
            //check if the element with this key already exists
            Node currentNode = map[calculatedIndex];
            while (!currentNode.getKey().equals(key)) {
                if (currentNode.getNext() == null) break;
                currentNode = currentNode.getNext();
            }
            //if there is the element with the key, overwrite the value
            if (currentNode.getKey().equals(key)) {
                currentNode.setValue(value);
            } else {                            //if there is no element with the key, create a new one at the end
                currentNode.setNext(new Node());
                currentNode = currentNode.getNext();
                currentNode.setKey(key);
                currentNode.setValue(value);
            }
        }
    }

    //function responsible for getting the value of an element with its key
    public Object get(Object key) {
        //calculate the index where the linked list containing the element is stored
        int calculatedIndex = calculateIndex(key, buckets);
        //iterate over the linked list to find the element with its key
        Node currentNode = buckets[calculatedIndex];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        //if no such key is found return null
        return null;
    }

    //boolean function responsible for removing the element from our map
    public boolean delete(Object key) {
        //calculate the index where the linked list containing the element is stored
        int calculatedIndex = calculateIndex(key, buckets);
        //assign the element of this index of the array to a variable to use for iteration
        Node currentNode = buckets[calculatedIndex];
        //if the element is null return false
        if (currentNode == null) {
            return false;
        }
        //if the first element of the linked list is the one we need
        //remove it by making the next element the first
        if (currentNode.getKey().equals(key)) {
            buckets[calculatedIndex] = currentNode.getNext();
            counter--;
            return true;
        } else {
            //iterate over the linked list to the end if possible
            while (currentNode.getNext() != null) {
                //if we find the element
                //remove it by linking the previous element to the next one of the removed element
                if (currentNode.getNext().getKey().equals(key)) {
                    currentNode.setNext(currentNode.getNext().getNext());
                    counter--;
                    return true;
                }
                currentNode = currentNode.getNext();
            }
        }
        //if no such element is found return false
        return false;
    }

    //the function is responsible for calculating the index for the element to store it in the array
    private int calculateIndex(Object key, Node[] map) {
        //calculate the index by the absolute value of its modulus over the array length
        return Math.abs(key.hashCode() % map.length);
    }

    //the function is responsible for increasing the array if the number of elements is too much
    private void resize() {
        //new map double the size of the current one
        Node[] resizedMap = new Node[buckets.length * 2];
        //iterate over each and every existing element and put them in the new resized array
        for (Node n : buckets) {
            Node currentNode = n;
            while (currentNode != null) {
                //put the old elements in the new array
                insertNew(currentNode.getKey(), currentNode.getValue(), resizedMap);
                currentNode = currentNode.getNext();
            }
        }
        //change the current array with the new one
        buckets = resizedMap;
    }

    //function for printing the whole information for testing purposes
    public void printAll() {
        System.out.println();
        for (int i = 0; i < buckets.length; i++) {
            System.out.print(i + ":");
            Node pair = buckets[i];
            while (pair != null) {
                System.out.print("  (" + pair.getKey() + "," + pair.getValue() + ")");
                pair = pair.getNext();
            }
            System.out.println();
        }
    }
}
