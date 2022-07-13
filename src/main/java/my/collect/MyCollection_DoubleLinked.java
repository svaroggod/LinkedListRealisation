package my.collect;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyCollection_DoubleLinked<E> implements Linked<String>, Iterable<String> {

    private String msgException = "The element is not found";
    private int size;
    private Nodes first;
    private Nodes last;

//как это выглядит на начальном этапе
// null <- previousElement[firstNode(E = null)] nextElement ->
// <- previousElement[Node(E = value)] -> null <- previousElement[lastNode(E =null)] -> null

    public MyCollection_DoubleLinked() {
    }

    //    Конструктор, который позволяет сразу добавить массив в список
    public MyCollection_DoubleLinked(String... values) {
        this.addAll(values);
    }


    //    Метод возвращает кол-во элементов в списке
    @Override
    public int size() {
        return size;
    }

    //    Метод проверяет, пустой ли наш список или нет
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //    Метод очищает все данные в списке
//По сути просто присваиваем переменной кол-ва элементов 0 и обнуляем ссылки на первый и конечный элемент
    @Override
    public void clear() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }


    //Метод возвращает список в виде массива типа Object[]
    @Override
    public String[] toArray() {
        String[] resultArray = new String[this.size];
        int index = 0;
        for (Nodes link = this.first; link != null; link = link.next) {
            resultArray[index++] = link.value;
        }
        return resultArray;
    }


//Метод возвращает список в виде строки

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }


// Метод добавляет элемент в список в конец

    @Override
    public void add(String value) {
        if (this.first == null) {
            first = new Nodes(null, null, value);
        } else {
            Nodes prevElement = this.last == null ? this.first : this.last;
            this.last = new Nodes(prevElement, null, value);
            prevElement.next = this.last;
        }
        this.size++;
    }

    // Метод добавляет элемент в список в конец
    @Override
    public void addLast(String value) {
        add(value);

    }

//    Метод проверяет, входит ли индекс в диапазон списка

    @Override
    public boolean checkIndexToRange(int index) {
        return index >= 0 && index < this.size;
    }

    @Override
    public void addFirst(String value) {
        Nodes currFirst = first;
        Nodes newNode = new Nodes(null, currFirst, value);
        first = newNode;
        if (currFirst == null)
            last = newNode;
        else
            currFirst.prev = newNode;
        size++;

    }

    // Метод для получения значения последнего элемента коллекции
    @Override
    public String getLast() {
        return last.value;
    }

    // Метод для получения значения первого элемента коллекции
    @Override
    public String getFirst() {
        return first.value;
    }


    // Метод для получения ссылки на Nodes по индексу. Если индекс больше чем кол-во элементов поделить на 2, то
// ищем циклом с конца списка, или поиск начинаем с начала
    @Override
    public Nodes getLinkByIndex(int index) {
        Nodes result;
        if (this.size >> 1 >= index) {
            result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            result = this.last;
            for (int i = this.size - 1; i > index; i--) {
                result = result.prev;
            }
        }
        return result;
    }

    //Удаляем элемент из списка по ссылке на Nodes.
    @Override
    public boolean deleteByLink(Nodes node) {
        boolean result = node != null;
        if (result) {
            if (node.next == null && node.prev == null) {
                first = null;
                last = null;
            } else if (node.prev == null) {
                first = node.next;
                first.prev = null;
            } else if (node.next == null) {
                last = node.prev;
                last.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }
        return result;
    }

    //Удаляем первый элемент из списка по ссылке на Nodes.
    @Override
    public String removeFirst() {
        final Nodes f = first;
        if (f == null)
            throw new NoSuchElementException();
        final String element = f.value;
        final Nodes next = f.next;
        f.value = null;
        f.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    //Удаляем последний элемент из списка по ссылке на Nodes.
    @Override
    public String removeLast() {
        final Nodes l = last;
        if (l == null)
            throw new NoSuchElementException();
        final String element = l.value;
        final Nodes prev = l.prev;
        l.value = null;
        l.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }


    // Метод добавляет массив элементов в список.

    @Override
    public boolean addAll(String... values) {
        boolean result = values != null && values.length > 0;
        if (result) {
            for (String value : values) {
                add(value);
            }
        }
        return result;
    }


    // Метод возвращает элемент из списка по индексу
// Используетcя метод поиска ссылки на элемент по индексу и метод проверки корректности индекса
// Если индекс некорректный, то выбрасывается исключение NoSuchElementException
    @Override
    public String getElementByIndex(int index) {
        String result;
        if (checkIndexToRange(index)) {
            result = getLinkByIndex(index).value;
        } else {
            throw new NoSuchElementException(this.msgException);
        }
        return result;
    }

    // Метод удаляет элемент по индексу
//  использует метод проверки индекса и метод для удаления элемента по Nodes
    @Override
    public boolean deleteElementByIndex(int index) {
        boolean result = checkIndexToRange(index);
        if (result) {
            result = deleteByLink(getLinkByIndex(index));
            size--;
        }
        return result;
    }

    // Метод вырезает кусок из коллекции
    @Override
    public MyCollection_DoubleLinked subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        if (this.isEmpty()) {
            return null;
        }
        MyCollection_DoubleLinked subList = new MyCollection_DoubleLinked();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(this.getElementByIndex(i));
        }
        return subList;
    }


//   Метод добавляет элемент в список в определённый индекс c учетом "раздвижения" элментов по краям

    @Override
    public boolean addByIndex(int index, String value) {
        boolean result = true;
        if (index == this.size) {
            add(value);
        } else if (checkIndexToRange(index)) {
            Nodes oldElement = getLinkByIndex(index);
            Nodes newElement = new Nodes(oldElement.prev, oldElement, value);
            if (oldElement.prev == null) {
                this.first = newElement;
                this.last = oldElement;
            } else {
                oldElement.prev.next = newElement;
                oldElement.prev = newElement;
            }
            this.size++;
        } else {
            result = false;
        }
        return result;
    }

    //         Метод изменяет данные по индексу на новые.
    @Override
    public String updateValue(int index, String value) {
        String result;
        if (checkIndexToRange(index)) {
            Nodes temp = getLinkByIndex(index);
            result = temp.value;
            temp.value = value;
        } else {
            throw new NoSuchElementException(this.msgException);
        }
        return result;
    }


    @Override
    public Iterator<String> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<String> {

//     Курсор - место, откуда начинаем двигаться по списку.

        private Nodes cursor = first;


//     Проверяет, есть ли следующий элемент в списке.
//     На самом деле он уже стоит на том элементе, который выведется следующим.
//     Он просто смотрит, что курсор не равен null.

        @Override
        public boolean hasNext() {
            return this.cursor != null;
        }


//     Возвращаем элемент из списка и переключаем курсор на следующий элемент.
//     Если мы пытаемся вывести элемент, которого нет, то будет выброшено исключение.

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException(msgException);
            }
            String result = cursor.value;
            this.cursor = this.cursor.next;
            return result;
        }
    }
}


//Вспомогательный класс Nodes для хранения элементов в списке (Nodes, чтобы не дублироваться с Node)

class Nodes<E> {
    String value;
    Nodes next;
    Nodes prev;

//Конструктор для класса Node.
//prev - ссылка на предыдущий элемент; next - ссылка на следующий элемент; value - значение, которое храним в списке;

    Nodes(Nodes prev, Nodes next, String value) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

