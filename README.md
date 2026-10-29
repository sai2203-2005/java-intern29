# Generic Repository Pattern in Java (`Repository<T>`)

A reusable, type-safe in-memory repository implementation built in Java using **Generics (`<T>`)** and the **Java Collections Framework**. It demonstrates how to store, manage, and retrieve heterogeneous domain models (such as `Student` and `Employee`) using a single generic repository class without code duplication.

---

## Features

- **Type Safety at Compile Time**: Catches invalid type insertions during compilation rather than failing with runtime `ClassCastException`s.
- **Zero Type Casting**: Directly retrieves strongly typed objects (`Student`, `Employee`) without manual casting (`(Student) obj`).
- **DRY (Don't Repeat Yourself)**: Eliminates the need to write separate `StudentRepository`, `EmployeeRepository`, etc.
- **In-Memory Storage**: Uses an encapsulated `ArrayList<T>` for quick retrieval by index and sequential access.
- **Clean Object Representation**: Implements readable string outputs using overridden `toString()` methods.

---

## Project Structure

```
.
├── Practise.java    # Complete source code (Repository<T>, Student, Employee, Main)
├── Practise.class   # Compiled bytecode
└── README.md        # Comprehensive project documentation
```

---

## Prerequisites

- **Java Development Kit (JDK)**: JDK 8 or higher (JDK 17 / 21 / 23 recommended).
- Check your Java installation:
  ```bash
  javac -version
  java -version
  ```

---

## Getting Started

### 1. Compile the Program

Open your terminal in the project directory and compile:

```bash
javac Practise.java
```

### 2. Run the Application

Execute the compiled class:

```bash
java Practise
```

---

## Expected Output

```text
Students:
[1 Siva, 2 Rahul]
1 Siva
Employees:
[101 Ravi, 102 Priya]
102 Priya
```

---

## Architecture & How It Works

```
                        ┌──────────────────────┐
                        │    Repository<T>     │
                        │  - items: List<T>    │
                        │  + add(item: T)      │
                        │  + getAll(): List<T> │
                        │  + get(index): T     │
                        └──────────┬───────────┘
                                   │
              ┌────────────────────┴────────────────────┐
              ▼                                         ▼
   Repository<Student>                         Repository<Employee>
┌────────────────────────┐                  ┌────────────────────────┐
│ items: List<Student>   │                  │ items: List<Employee>  │
├────────────────────────┤                  ├────────────────────────┤
│ [0] -> 1 Siva          │                  │ [0] -> 101 Ravi        │
│ [1] -> 2 Rahul         │                  │ [1] -> 102 Priya       │
└────────────────────────┘                  └────────────────────────┘
```

Each repository instance operates independently with its own internal list and enforces strict type constraints for its assigned type argument:

```java
// Student repository only accepts Student instances
Repository<Student> studentRepo = new Repository<>();
studentRepo.add(new Student(1, "Siva"));

// Employee repository only accepts Employee instances
Repository<Employee> employeeRepo = new Repository<>();
employeeRepo.add(new Employee(101, "Ravi"));
```

---

## Key Code Breakdown

### 1. Generic Class Declaration (`class Repository<T>`)
- `T` is a **Type Parameter** that serves as a placeholder for any reference type.
- When instantiated as `Repository<Student>`, the Java compiler substitutes `T` with `Student`.

### 2. Encapsulated Storage (`private List<T> items = new ArrayList<>()`)
- Uses composition to encapsulate an internal `List<T>`.
- The list is `private`, preventing external code from mutating the collection directly.

### 3. Type-Safe Insertion (`public void add(T item)`)
- Accepts only objects conforming to type `T`.
- Passing any other object causes a compile-time error:
  ```java
  // Compile error: incompatible types: Employee cannot be converted to Student
  studentRepo.add(new Employee(105, "Anil"));
  ```

### 4. Direct Retrieval (`public T get(int index)`)
- Returns the exact type `T`.
- No explicit casting is needed:
  ```java
  Student s = studentRepo.get(0); // No (Student) cast required!
  ```

---

## Why Use Generics?

| Aspect | Without Generics (`Object`) | With Generics (`<T>`) |
|---|---|---|
| **Class Reusability** | May require duplicate repository classes for each entity. | **One single reusable class** fits all entities. |
| **Type Safety** | Errors only appear at runtime (`ClassCastException`). | **Compile-time checking** prevents invalid assignments. |
| **Type Casting** | Explicit casting required: `(Student) repo.get(0)`. | **No casting required**: returns `Student` directly. |
| **Code Readability** | Obscured by boilerplate cast operations. | Clean, self-documenting, and concise. |

---

## Production Extensions & Best Practices

In enterprise frameworks (like Spring Data JPA / Hibernate DAO pattern), the generic repository pattern is typically enhanced with:

1. **Defensive Copies**: Returning `Collections.unmodifiableList(items)` in `getAll()` to safeguard internal state.
2. **Key-Based Lookup**: Defining an entity interface or bounded type parameter:
   ```java
   interface Identifiable<ID> {
       ID getId();
   }

   class Repository<T extends Identifiable<ID>, ID> {
       public Optional<T> findById(ID id) { ... }
   }
   ```
3. **Lambda / Predicate Filtering**:
   ```java
   public Optional<T> find(Predicate<T> condition) {
       return items.stream().filter(condition).findFirst();
   }
   ```
4. **Removal & Cardinality**: Methods such as `remove(T item)`, `size()`, and `isEmpty()`.

---

## License & Attribution

Developed as part of the **Java Internship** practical assignments covering Core Java, Generics, and Design Patterns.
