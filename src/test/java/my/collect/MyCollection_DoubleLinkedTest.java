package my.collect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyCollection_DoubleLinkedTest {

    MyCollection_DoubleLinked<String> myCollection = new MyCollection_DoubleLinked();

    @BeforeEach
    void initTest() {
        myCollection.addFirst("Первый");
        myCollection.addFirst("Второй");
        myCollection.addFirst("Третий");
        myCollection.addFirst("Четвертый");
        //  List list = new LinkedList<>();
    }


    @Test
    void addFirst() {


    }

    @Test
    void addLast() {
    }

    @Test
    void isEmpty() {

        if (myCollection.isEmpty()) {
            System.out.println("ты ничтожен");
        }

    }

    @Test
    void size() {
    }

    @Test
    void getElementByIndex() {
    }
}