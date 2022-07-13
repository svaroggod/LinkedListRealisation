package my.collect;

public interface Linked<String> {


    void clear();

    String[] toArray();

    void add(String value);

    void addLast(String value);

    boolean checkIndexToRange(int index);

    void addFirst(String value);

    boolean isEmpty();

    int size();

    String getElementByIndex(int i);

    java.lang.String getLast();

    java.lang.String getFirst();

    Nodes getLinkByIndex(int index);

    boolean deleteByLink(Nodes node);

    //Удаляем первый элемент из списка по ссылке на Nodes.
    java.lang.String removeFirst();

    //Удаляем последний элемент из списка по ссылке на Nodes.
    java.lang.String removeLast();

    boolean addAll(String... values);

    boolean deleteElementByIndex(int index);

    // Метод удаляет первый элемент
    MyCollection_DoubleLinked subList(int fromIndex, int toIndex);

    boolean addByIndex(int index, String value);

    String updateValue(int index, String value);
}

