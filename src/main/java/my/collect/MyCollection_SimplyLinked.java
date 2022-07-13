package my.collect;

import java.util.NoSuchElementException;


class MyCollection_SimplyLinked<E> {

    Node<E> head;
    Node<E> last;

    public MyCollection_SimplyLinked() {
        head = null;
        last = null;
    }

    public void insertAtFirst(E data) {
        Node<E> added = new Node<E>(data);
        if (head == null) {
            head = added;
            last = head;
        } else {
            added.next = head;
            head = added;
        }
        Print(head);
    }

    public void insertAtLast(E data) {
        Node<E> added = new Node<E>(data);
        if (empty()) {
            head = added;
            last = added;
        } else {
            last.next = added;
            last = added;
        }
        Print(head);
    }

    public void insertAtPosition(int position, E data) {
        if (empty()) {
            insertAtFirst(data);
            return;
        }

        Node<E> headPtr = head;
        position -= 1;
        for (int i = 1; i < size(); i++) {
            if (position == i) {
                Node<E> added = new Node<E>(data);
                Node<E> temp = headPtr.next;
                headPtr.next = added;
                added.next = temp;
                break;
            }
            headPtr = headPtr.next;
        }
        Print(head);
    }

    public void delete(int position) {
        if (empty())
            throw new NoSuchElementException("List is Empty!");

        if (position == 1) {
            if (head.next == null) {
                head = null;
            } else {
                Node<E> temp = head.next;
                head = temp;
            }
            Print(head);
            return;
        }

        if (position == size()) {
            Node<E> headPtr = head;
            Node<E> lastPtr = headPtr;
            while (headPtr.next != null) {
                lastPtr = headPtr;
                headPtr = headPtr.next;
            }
            lastPtr.next = null;
            last = lastPtr;
            Print(head);
            return;
        }

        Node<E> startPtr = head;
        position -= 1;
        for (int i = 1; i < size(); i++) {
            if (i == position) {
                Node<E> temp = startPtr.next;
                temp = temp.next;
                startPtr.next = temp;
            }
            startPtr = startPtr.next;
        }
        Print(head);
    }

    public void Print(Node<E> link) {
        if (link != null) {
            System.out.print(link.data + " ");
            Print(link.next);
        }
    }

    public int size() {
        if (empty())
            throw new NoSuchElementException("List is Empty");

        Node<E> headPtr = head;
        int count = 1;
        while (headPtr.next != null) {
            count++;
            headPtr = headPtr.next;
        }
        return count;
    }

    public void replace(int position, E data) {
        if (empty())
            throw new NoSuchElementException("List is Empty!");

        Node<E> startPtr = head;
        for (int i = 1; i < size(); i++) {
            if (i == position) {
                startPtr.data = data;
                break;
            }
            startPtr = startPtr.next;
        }
        Print(head);
    }

    public E elementByPosition(int position, Node<E> link, int count) {
        if (count == position) {
            return link.data;
        }
        return elementByPosition(position, link.next, ++count);
    }

    public int searchElement(E data, Node<E> link, int position) {
        if (empty())
            throw new NoSuchElementException("List is Empty!");

        if (link.data == data)
            return position;

        return searchElement(data, link.next, ++position);
    }

    public Node<E> getLinkByData(E data, Node<E> link) {
        if (link == null)
            return link;

        if (link.data == data)
            return link;

        return getLinkByData(data, link.next);
    }

    public void swapData(E firstData, E secondData) {
        if (empty())
            throw new NoSuchElementException("List is Empty!");

        Node<E> firstDataLink = getLinkByData(firstData, head);
        Node<E> secondDataLink = getLinkByData(secondData, head);
        if (firstDataLink == null || secondDataLink == null) {
            throw new NoSuchElementException("Eithe " + firstData + " or " + secondData + " not in the list");
        }
        firstDataLink.data = secondData;
        secondDataLink.data = firstData;
        Print(head);
    }

    public void swapNode(E firstData, E secondData) {

        if (empty())
            throw new NoSuchElementException("List is Empty!");

        if (firstData == secondData)
            throw new NoSuchElementException("Can't swap for identical data");

        boolean foundFirstData = false;
        boolean foundSecondData = false;

        Node<E> firstDataPtr = head;
        Node<E> prevFirstDataPtr = head;
        while (firstDataPtr.next != null && !(firstDataPtr.data == firstData)) {
            prevFirstDataPtr = firstDataPtr;
            firstDataPtr = firstDataPtr.next;
        }
        if (firstDataPtr.data == firstData)
            foundFirstData = true;

        Node<E> secondDataPtr = head;
        Node<E> prevSecondDataPtr = head;
        while (secondDataPtr.next != null && !(secondDataPtr.data == secondData)) {
            prevSecondDataPtr = secondDataPtr;
            secondDataPtr = secondDataPtr.next;
        }
        if (secondDataPtr.data == secondData)
            foundSecondData = true;

        if (foundFirstData && foundSecondData) {

            // if data belongs to start or last node
            if (firstDataPtr == head)
                head = secondDataPtr;
            else if (secondDataPtr == head)
                head = firstDataPtr;

            if (firstDataPtr == last)
                last = secondDataPtr;
            else if (secondDataPtr == last)
                last = firstDataPtr;

            Node<E> nextFirstDataPtr = firstDataPtr.next;
            Node<E> nextSecondDataPtr = secondDataPtr.next;

            prevFirstDataPtr.next = secondDataPtr;
            secondDataPtr.next = nextFirstDataPtr;

            prevSecondDataPtr.next = firstDataPtr;
            firstDataPtr.next = nextSecondDataPtr;

            // if items belongs to adjacent node
            if (prevSecondDataPtr == firstDataPtr)
                secondDataPtr.next = firstDataPtr;
            else if (prevFirstDataPtr == secondDataPtr)
                firstDataPtr.next = secondDataPtr;

            Print(head);

        } else
            throw new NoSuchElementException("Either " + firstData + " or " + secondData + " not in the list!");
    }

    public Node<E> reverse(Node<E> link) {
        if (link == null)
            return null;

        if (link.next == null)
            return link;

        Node<E> secondElement = link.next;
        link.next = null;
        Node<E> reverseRest = reverse(secondElement);
        secondElement.next = link;
        return reverseRest;
    }

    public void setHeadandLastLink() {
        Node<E> temp = head;
        head = last;
        last = temp;
        Print(head);
    }

    public E middle() {
        if (empty())
            throw new NoSuchElementException("List is empty!");

        Node<E> slowPtr = head;
        Node<E> fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return slowPtr.data;
    }

    public int getOccurence(E data, int occurence, Node<E> link) {

        if (empty())
            throw new NoSuchElementException("List is Empty!");

        if (link == null)
            return occurence;

        if (link.data == data) {
            ++occurence;
        }

        return getOccurence(data, occurence, link.next);
    }

    public boolean empty() {
        return head == null;
    }

}

class Node<E> {

    Node<E> next = null;
    E data;

    Node(E data) {

        this.data = data;
        this.next = null;
    }
}