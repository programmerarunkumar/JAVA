package LamdaExpression.People.Java8;

import LamdaExpression.People.Condition;
import LamdaExpression.People.People;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] agrs){

        ArrayList<People> peopleList = new ArrayList<>();
        peopleList.add(new People("Arun", 24));
        peopleList.add(new People("bala", 25));
        peopleList.add(new People("Sachin", 50));
        peopleList.add(new People("Saravana", 24));
        peopleList.add(new People("vijay", 29));

        System.out.println("All the People");
        //Print all the People
        printConditionally(peopleList, (people)->true);

        System.out.println("People with age < 25");
        //Age < 25
        printConditionally(peopleList, (people)->people.getAge() < 25);

        System.out.println("People with name starts with S");
        //Name starts with S
        printConditionally(peopleList, (people)->people.getName().startsWith("S"));

    }

    private static void printConditionally(List<People> peopleList, Condition condition){
        for(People people : peopleList){
            if(condition.conditionSatisfied(people)){
                System.out.println(people.toString());
            }
        }
    }

}
