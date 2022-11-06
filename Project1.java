import java.util.Scanner;

public class Project1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String option;
        String name;
        String id;
        String rank;
        String department;
        double gpa;
        int credit;
        String status;
        Personnel personnel = new Personnel();

        while(true){
            System.out.println("Choose one of the options\n"+ 
            "1-  Enter the information a faculty\n"+
            "2-  Enter the information of a student\n"+ 
            "3-  Print tuition invoice for a student\n"+ 
            "4-  Print faculty information\n"+ 
            "5-  Enter the information of a staff member\n"+ 
            "6-  Print the information of a staff member\n"+ 
            "7-  Exit Program\n\n"+
            "Enter your selection: \n");
            
            option = sc.nextLine();

            if (!(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4") || option.equals("5") || option.equals("6")
            || option.equals("7"))) {
                System.out.print("Invalid entry, try again\n");
                continue;
            }

            if(option.equals("1")){
                System.out.println("Enter the faculty info:\n");

                System.out.println("Enter the name of the faculty: \n");
                name = sc.nextLine();

                System.out.println("ID: \n");
                id = sc.nextLine();

                System.out.println("Rank: \n");
                rank = sc.nextLine();

                while(!(rank.equalsIgnoreCase("Professor") || rank.equalsIgnoreCase("Adjunct"))){
                    System.out.println("Invalid entry, try again");
                    System.out.println("Rank:\n");
                    rank = sc.nextLine();
                }

                System.out.println("Department: \n");
                department = sc.nextLine();

                while(!(department.equalsIgnoreCase("Mathematics") || department.equalsIgnoreCase("Engineering") 
                || department.equalsIgnoreCase("Sciences"))){
                    System.out.println("Invalid entry, try again");
                    System.out.println("Department:\n");
                    department = sc.nextLine();
                }

                Faculty faculty = new Faculty(name, id, department, rank);
                personnel.addPerson(faculty);

                System.out.println("Faculty added!");
            }

            if(option.equals("2")){
                System.out.println("Enter the student info: \n");

                System.out.println("Name of student:\n");
                name = sc.nextLine();

                System.out.println("ID:\n");
                id = sc.nextLine();

                System.out.println("GPA:");
                gpa = sc.nextDouble();

                System.out.println("Credit:\n");
                credit = sc.nextInt();

                Student student = new Student(name, id, gpa, credit);
                personnel.addPerson(student);

                System.out.println("Student added!");
            }

            if(option.equals("3")){
                System.out.println("Enter the student id:\n");
                id = sc.nextLine();
                
                personnel.getInformation(id);

            }

            if(option.equals("4")){
                System.out.println("Enter the faculty id:\n");
                id = sc.nextLine();
                
                personnel.getInformation(id);

            }

            if(option.equals("5")){
                System.out.println("Enter the staff info:\n");

                System.out.println("Enter the name of the staff: \n");
                name = sc.nextLine();

                System.out.println("ID: \n");
                id = sc.nextLine();

                System.out.println("Department: \n");
                department = sc.nextLine();

                System.out.println("Status, enter P for part-time and F for full-time: \n");
                status = sc.nextLine();

                if(status.equalsIgnoreCase("P")){
                    status = "Part-time";
                } else if(status.equalsIgnoreCase("F")){
                    status = "Full-time";
                }

                Staff staff = new Staff(name, id, department, status);
                personnel.addPerson(staff);

            }

            if(option.equals("6")){
                System.out.println("Enter the staff id:\n");
                id = sc.nextLine();
                
                personnel.getInformation(id);

            }

            if(option.equals("7")){
                System.out.println("Good bye!");
                break;
            }

        }


    }

}

abstract class Person{
    private String name;
    private String id;

    public Person(String name){
        this.name = name;
    }

    public Person(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(String id){
        this.id = id;
    }
    
    public abstract void print();

}

abstract class Employee extends Person{
    private String department;

    public Employee(String name, String id){
        super(name, id);
    }

    public Employee(String name, String id, String department){
        super(name, id);
        this.department = department;
    }


    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
    }


}

class Faculty extends Employee{
    private String rank;

    public Faculty(String name, String id){
        super(name, id);
    }

    public Faculty(String name, String id, String department){
        super(name, id, department);
    }

    public Faculty(String name, String id, String department, String rank){
        super(name, id, department);
        this.rank = rank;
    }

    public String getRank(){
        return rank;
    }

    public void setRank(String rank){
        this.rank = rank;
    }

    @Override
    public void print(){
        System.out.println(super.getName() + "\t" + super.getId() + "\n" + super.getDepartment() + "\t" + getRank());
    }
}

class Staff extends Employee{
    private String status;

    public Staff(String name, String id){
        super(name, id);
    }

    public Staff(String name, String id, String department){
        super(name, id, department);
    }

    public Staff(String name, String id, String department, String status){
        super(name, id, department);
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public void print(){
        System.out.println(super.getName() + "\t" + super.getId() + "\n" + super.getDepartment() + "\t" + getStatus());
    }
}

class Student extends Person{
    private double gpa;
    private int credit;

    public Student(String name, String id){
        super(name, id);
    }

    public Student(String name, String id, double gpa, int credit){
        super(name, id);
        this.gpa = gpa;
        this.credit = credit;
    }

    public double getGpa(){
        return gpa;
    }

    public int getCredit(){
        return credit;
    }

    public void setGpa(int gpa){
        this.gpa = gpa;
    }

    public void setCredit(int credit){
        this.credit = credit;
    }

    @Override
    public void print(){
        double totalPayment = getCredit()*236.45;
        double discount = 0;

        if(getGpa() >= 3.85){
            totalPayment = getCredit()*236.45*.75;
            discount = getCredit()*236.45 - totalPayment;
        }
        System.out.println(super.getName() + "\t" + super.getId() + "\n" + "Credit Hours: " + getCredit() + " ($236.45 per credit hour)\n"
        + "Fees: $52\n" + "Total payment: $" + String.format("%.2f", totalPayment) + "\t$" + String.format("%.2f", discount) + " discount applied");
    }

}

class Personnel{
    private Person[] list;
    
    public Personnel(){
        list = new Person[100];
    }

    public void addPerson(Person person){
        int i = 0;
        while(this.list[i] != null && i < 100){
            i++;
        }
        this.list[i] = person;
    }

    public void getInformation(String id){
        boolean found = false;
        for(int i = 0; i < 100; i++){
            if(this.list[i] != null && this.list[i].getId().equals(id)){
                found = true;
                this.list[i].print();
                break;
            }
        }
        
        if(found == false){
            System.out.println("ID not found");
        }
    }

}

