import entity.ItemsEntity;
import jakarta.persistence.*;


import java.util.HashMap;
import java.util.Scanner;

public class Database {

    HashMap<Integer, String> todo;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = emf.createEntityManager();
    ItemsEntity iE = new ItemsEntity();

    public Database(HashMap<Integer, String> todo){
        this.todo = todo;
        menuForDB(this.todo);
    }

    public void menuForDB(HashMap<Integer, String> todo){
        System.out.println("Database Menu");
        addToDB(todo);


    }

    public void addToDB(HashMap<Integer, String> todo){

        entityManager.getTransaction().begin();

        for(Integer i: todo.keySet())
        {
            iE.setId(i);//number here
            iE.setItem(todo.get(i));//string here
            entityManager.merge( iE );
        }



        //add possible if-statement
        entityManager.getTransaction( ).commit( );

        viewDB();
    }

    public void closeDB(){
        entityManager.close();
        emf.close();
    }

    public void viewDB(){
        System.out.println("Current items in DataBase!");

        System.out.println("Id: " + iE.getId());
        closeDB();
    }

    public void deleteFromDB(HashMap<Integer, String> todo, int id){

        entityManager.getTransaction().begin();

        for(Integer i: todo.keySet())
        {
            if(i == id){
                Entity find = (Entity) entityManager.find(ItemsEntity.class, i);
                entityManager.remove(find);
                entityManager.getTransaction( ).commit( );
            }
        }
        viewDB();
    }

}
