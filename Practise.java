import java.util.*;

class Repository<T> {

    private List<T> items = new ArrayList<>();

    // Store an object
    public void add(T item) {
        items.add(item);
    }

    // Retrieve all objects
    public List<T> getAll() {
        return items;
    }

    // Retrieve an object using its index
    public T get(int index) {
        return items.get(index);
    }
}

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

public class Practise {
    public static void main(String[] args) {

        // Repository for Student objects
        Repository<Student> studentRepo = new Repository<>();

        studentRepo.add(new Student(1, "Siva"));
        studentRepo.add(new Student(2, "Rahul"));

        System.out.println("Students:");
        System.out.println(studentRepo.getAll());

        System.out.println(studentRepo.get(0));

        // Repository for Employee objects
        Repository<Employee> employeeRepo = new Repository<>();

        employeeRepo.add(new Employee(101, "Ravi"));
        employeeRepo.add(new Employee(102, "Priya"));

        System.out.println("Employees:");
        System.out.println(employeeRepo.getAll());

        System.out.println(employeeRepo.get(1));
    }
}
