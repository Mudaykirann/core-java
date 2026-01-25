import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    String dept;
    double salary;

    Employee(String n, String d, double s) { name = n; dept = d; salary = s; }
    public String getName() { return name; }
    public String getDept() { return dept; }
    public double getSalary() { return salary; }
}


class Product {
    private String name;
    private String category;
    private double price;
    private boolean inStock;

    // Private constructor for Builder
    private Product(Builder builder) {
        this.name = builder.name;
        this.category = builder.category;
        this.price = builder.price;
        this.inStock = builder.inStock;
    }

    // Static Builder Class
    public static class Builder {
        private String name;
        private String category;
        private double price;
        private boolean inStock;

        public Builder name(String name) { this.name = name; return this; }
        public Builder category(String category) { this.category = category; return this; }
        public Builder price(double price) { this.price = price; return this; }
        public Builder inStock(boolean inStock) { this.inStock = inStock; return this; }
        public Product build() { return new Product(this); }
    }

    // Getters
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isInStock() { return inStock; }
}

public class StreamAPIWorking {
    public static  void main(String args[]){
        List<Employee> emps = Arrays.asList(
                new Employee("Alice", "Tech", 60000),
                new Employee("Bob", "HR", 45000),
                new Employee("Charlie", "Tech", 40000),
                new Employee("Kiran", "Tech", 80000),
                new Employee("Raju", "Tech", 60000),
                new Employee("Mohan", "Tech", 70000)
        );


        //Stream Operations example

        List<String> techstars = emps.stream()
                .filter(e -> e.getDept().equals("Tech"))
                .filter(e-> e.getSalary() > 50000)
                .sorted(Comparator.comparing(Employee::getName))
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(techstars);


        List<Product> warehouse = Arrays.asList(
                new Product.Builder().name("Laptop").category("Electronics").price(1200).inStock(true).build(),
                new Product.Builder().name("Phone").category("Electronics").price(800).inStock(true).build(),
                new Product.Builder().name("Desk").category("Furniture").price(300).inStock(true).build(),
                new Product.Builder().name("Monitor").category("Electronics").price(200).inStock(false).build()
        );


        //parallel Stream Example

        double totalValue = warehouse.parallelStream()
                .filter(p->p.getCategory().equals("Electronics"))
                .filter(Product::isInStock)
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("Total Electronic Stock Value: $" + totalValue);
    }
}
