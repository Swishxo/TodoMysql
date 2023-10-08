import java.util.HashMap;
import java.util.Scanner;

public class Todo {
    static HashMap<Integer, String> todo = new HashMap<>();
    static Database dB;

    public static void main(String[] args){
        menu(todo);
    }

    public static Integer calculatedSize(HashMap<Integer, String> todo){
        return todo.size();
    }
    public static void menu(HashMap<Integer, String> todo){
        Scanner scan = new Scanner(System.in);
        if(todo.isEmpty()){
            createList(todo);
            menu(todo);
        }else{
            System.out.println("\nMenu sys: \n1=View Items \n2=Delete Items \n3=Add items ");
            System.out.println();
            System.out.println("Number of items in List: " + calculatedSize(todo));

            //add to DB here
            dB = new Database(todo);

            int choice = scan.nextInt();
            switch(choice){
                case 1:
                    viewItems(todo);
                    menu(todo);
                    break;
                case 2:
                    deleteItems(todo);
                    menu(todo);
                    break;
                case 3:
                    addItems(todo);
                default:
                    System.out.println("You didn't select the correct output\n");
                    menu(todo);
            }
        }
    }
    //create function to add item
    public static void addItems(HashMap<Integer, String> todo) {
        Scanner scan = new Scanner(System.in);
        viewItems(todo);
        System.out.println("ADD a new item to your list: \n");
        String entry = scan.nextLine();

        int count = todo.size();

        for(int i = 1; i <= count; i++){
            boolean x = todo.containsKey(i);
            if(x){
                continue;
            } else {
                todo.put(i, entry);
                System.out.println("Added item\n");
                entry ="";
                break;
            }
        }

        if(entry.isEmpty()){
            menu(todo);
        } else {
            todo.put(count + 1, entry);
        }
    }

    public static void createList(HashMap<Integer, String> todo){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome, Enter items when your ready\n");
        System.out.println("Type (DONE) to end program\n");
        System.out.print("User: ");

        int count = 1;
        String add;

        do{
            add = scan.next();
            if(add.equals("Done")){
                break;
            } else {
                todo.put(count, add);
                System.out.println("\nItem added!");
                viewItems(todo);
                count++;
            }
        }while(!add.equals("Done"));

    }

    public static void deleteItems(HashMap<Integer, String> item){
        //display items
        viewItems(item);
        Scanner scan = new Scanner(System.in);

        System.out.println("Delete # to remove item\n");
        int num = scan.nextInt();

        if(item.containsKey(num)){
            System.out.println("item removed: " + item.remove(num));
            dB.deleteFromDB(item, num);
            viewItems(item);
        }
        else{
            System.out.println("Number provided isn't available\n");
            menu(item);
        }
    }

    public static void viewItems(HashMap<Integer, String> view){
        for (Integer k: view.keySet()) {
            System.out.println(k + ": " + view.get(k));
        }

    }
}
