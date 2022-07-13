package my.collect;

import java.util.Scanner;

public class MyCollection_SimplyLinkedTest {


    public static void main(String[] args) {
        System.out.println("Welcome to Simply Linked List");
        MyCollection_SimplyLinked<Integer> myCollection = new MyCollection_SimplyLinked<Integer>();
        boolean yes = true;
        do {
            System.out.println("\n1. Insert at Begining");
            System.out.println("2. Insert at End");
            System.out.println("3. Insert at any position");
            System.out.println("4. Delete at Position");
            System.out.println("5. Get Size");
            System.out.println("6. Print");
            System.out.println("7. Empty Status ");
            System.out.println("8. Replace data at given position");
            System.out.println("9. Get Element by Position");
            System.out.println("10. Search Element Recursively");
            System.out.println("11. Swap only data not node");
            System.out.println("12. Swap node for given data");
            System.out.println("13. Reverse the list");
            System.out.println("14. Find Middle Element");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    try {
                        myCollection.insertAtFirst(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        myCollection.insertAtLast(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Enter position");
                        int position = scanner.nextInt();
                        if (position < 1 || position > myCollection.size()) {
                            System.out.println("Invalid Position!");
                            break;
                        }
                        myCollection.insertAtPosition(position, scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        int position = scanner.nextInt();
                        if (position < 1 || position > myCollection.size()) {
                            System.out.println("Invalid position !");
                            break;
                        }
                        myCollection.delete(position);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.println(myCollection.size());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        myCollection.Print(myCollection.head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    try {
                        System.out.println(myCollection.empty());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8:
                    try {
                        System.out.println("Enter position : ");
                        int position = scanner.nextInt();
                        if (position < 1 || position > myCollection.size()) {
                            System.out.println("Invalid Position!");
                            break;
                        }
                        myCollection.replace(position, scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 9:
                    try {
                        System.out.println("Enter Position : ");
                        int position = scanner.nextInt();
                        if (position < 1 || position > myCollection.size()) {
                            System.out.println("Invalid Position !");
                            break;
                        }
                        System.out.println(myCollection.elementByPosition(position, myCollection.head, 1));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 10:
                    try {
                        System.out.println("Enter the item to Find it's position : ");
                        int data = scanner.nextInt();
                        System.out.println(myCollection.searchElement(data, myCollection.head, 1));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 11:
                    try {
                        System.out.println("Enter the items to swap");
                        int firstData = scanner.nextInt();
                        int secondData = scanner.nextInt();
                        myCollection.swapData(firstData, secondData);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 12:
                    try {
                        System.out.println("Enter the Data to swap Node");
                        int firstData = scanner.nextInt();
                        int secondData = scanner.nextInt();
                        myCollection.swapNode(firstData, secondData);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 13:
                    try {
                        myCollection.reverse(myCollection.head);
                        myCollection.setHeadandLastLink();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 14:
                    try {
                        System.out.println("Middle Element is : " + myCollection.middle());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 15:
                    try {
                        System.out.println("Enter the item to get Occurence");
                        int data = scanner.nextInt();
                        System.out.println("Occurence is : " + myCollection.getOccurence(data, 0, myCollection.head));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    break;
            }
        } while (yes);
    }
}
