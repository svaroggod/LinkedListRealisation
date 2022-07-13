package my.collect;

public class Application {

    public static void main(String[] args) {
        MyCollection_DoubleLinked<String> myCollection = new MyCollection_DoubleLinked();


        if (myCollection.isEmpty()) {
            System.out.println("ты ничтожен");
        }

        myCollection.addLast("555");
        myCollection.addLast("55555");
        myCollection.addFirst("0000000");
        myCollection.addLast("Четвертый");
        myCollection.addFirst("a,sh");
        System.out.println(myCollection.toString());
        System.out.println(myCollection.getElementByIndex(0));
        System.out.println(myCollection.size());

        myCollection.removeFirst();
        myCollection.removeLast();

        System.out.println(myCollection.toString());
        System.out.println(myCollection.size());

    }
}