package com.company;

public class Main {

    public static Employee list[] = new Employee[10];

    public static void main(String[] args) {
        System.out.println("\tBasic level:\n");

        list[0] = new Employee("Ivan", "Petrovich", "Ivanov1", 10000, 1);
        list[1] = new Employee("Ivan", "Sidorovich", "Petrov1", 12000, 2);
        list[2] = new Employee("Ivan", "Ivanovich", "Sidorov1", 15000, 3);
        list[3] = new Employee("Ivan", "Sidorovich", "Ivanov2", 12000, 4);
        list[4] = new Employee("Ivan", "Petrovich", "Petrov2", 20000, 5);
        list[5] = new Employee("Ivan", "Petrovich", "Sidorov2", 30000, 1);
        list[6] = new Employee("Ivan", "Sidorovich", "Ivanov3", 40000, 2);
        list[7] = new Employee("Ivan", "Ivanovich", "Petrov3", 15000, 3);
        list[8] = new Employee("Ivan", "Petrovich", "Sidorov3", 8000, 4);
        list[9] = new Employee("Ivan", "Sidorovich", "Ivanov4", 16000, 5);

        printEmployees(list);

        System.out.println("Сумма затрат на зарплаты в месяц: " + getSalariesSum(list));

        System.out.println("Сотрудник с минимальной зарплатой в месяц: " + getWithMinimumSalary(list));

        System.out.println("Сотрудник с максимальной зарплатой в месяц: " + getWithMaximumSalary(list));

        System.out.println("Средняя зарплата в месяц: " +  getAverageSalary(list));

        String[] names = getFullNames();
        for(String n : names)
            System.out.println(n);

        System.out.println("\n\tMiddle level:\n");

        indexSalary(list, 10);

        System.out.println("Сумма затрат на зарплаты в месяц по отделу 1: " + getSalariesSumInDep(1));

        System.out.println("Минимальная зарплата в месяц по отделу 1: " + getMinimumSalaryInDep(1));

        System.out.println("Максимальная зарплата в месяц по отделу 1: " + getMaximumSalaryInDep(1));

        System.out.println("Средняя зарплата в месяц по отделу 1: " +  getAverageSalaryInDep(1));

        indexSalaryInDep( 2, -10);

        printEmployeesInfo(1);

        printEmployeesWithSalaryLess(10000);

        printEmployeesWithSalaryBigger(20000);

        System.out.println("\n\tUltra-violence difficult:\n");

        EmployeeBook book = new EmployeeBook(20, list);
        book.removeEmplooyee(1);
        book.addEmplooyee(new Employee("Bill", "Microsoft", "Gates", 1000000, 1));
        book.editEmployee("Ivanov1 Ivan Petrovich", null, 5);
        book.printInfoPerDepartment();
        for(String f : book.getFullNames()) {
            if(f != null)
                System.out.println(f);
        }

        book.removeEmplooyee(Employee.staticid - 1);
        book.editEmployee("Ivanov1 Ivan Petrovich", null, 1);
        book.addEmplooyee(list[1]);
        book.printEmployees();

        System.out.println("(EmployeeBook) Сумма затрат на зарплаты в месяц: " + book.getSalariesSum());

        System.out.println("(EmployeeBook) Сотрудник с минимальной зарплатой в месяц: " + book.getWithMinimumSalary());

        System.out.println("(EmployeeBook) Сотрудник с максимальной зарплатой в месяц: " + book.getWithMaximumSalary());

        System.out.println("(EmployeeBook) Средняя зарплата в месяц: " +  book.getAverageSalary());
    }

    public static void printEmployees(Employee list[]) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

    public static int getSalariesSum(Employee list[]) {
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            sum += list[i].getSalary();
        }

        return sum;
    }

    public static Employee getWithMinimumSalary(Employee list[]) {
        Employee ret = list[0];
        float min = list[0].getSalary();
        for (int i = 1; i < list.length; i++) {
            if(min > list[i].getSalary()) {
                min = list[i].getSalary();
                ret = list[i];
            }
        }
        return ret;
    }

    public static Employee getWithMaximumSalary(Employee list[]) {
        Employee ret = list[0];
        float max = list[0].getSalary();
        for (int i = 1; i < list.length; i++) {
            if(max < list[i].getSalary()) {
                max = list[i].getSalary();
                ret = list[i];
            }
        }
        return ret;
    }

    public static float getAverageSalary(Employee list[]) {
        return getSalariesSum(list) / (float) list.length;
    }

    public static String[] getFullNames() {
        String[] names = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            names[i] = list[i].getFullName();
        }
        return names;
    }

    public static void indexSalary(Employee list[], int percent) {
        for(Employee dude : list) {
            float salary = dude.getSalary();
            salary += salary * (percent) / 100;
            dude.setSalary(salary);
        }
    }

    public static Employee[] getDudesInDepartment(Employee list[], int department) {
        int size = 0;
        for(Employee dude : list) {
            if(dude.getDepartment() == department)
                size++;
        }

        int j = 0;
        Employee[] dudes = new Employee[size];
        for(Employee dude : list) {
            if(dude.getDepartment() == department)
                dudes[j++] = dude;
        }

        return dudes;
    }

    public static Employee getMinimumSalaryInDep(int department) {
        return getWithMinimumSalary(getDudesInDepartment(list, department));
    }

    public static Employee getMaximumSalaryInDep(int department) {
        return getWithMaximumSalary(getDudesInDepartment(list, department));
    }

    public static float getAverageSalaryInDep(int department) {
        return getAverageSalary(getDudesInDepartment(list, department));
    }

    public static int getSalariesSumInDep(int department) {
        return getSalariesSum(getDudesInDepartment(list, department));
    }

    public static void indexSalaryInDep(int department, int percent) {
        indexSalary(getDudesInDepartment(list, department), percent);
    }

    public static void printEmployeesInfo(int department) {
        System.out.println("Список сотрудников в отделе 1: ");

        for(Employee dude : getDudesInDepartment(list, department)) {
            String firstName = dude.getFirstName();
            String middleName = dude.getMiddleName();
            String lastName = dude.getLastName();
            float salary = dude.getSalary();

            String info = "Employee{" +
                    "id=" + dude.getId() +
                    ", firstName='" + firstName + '\'' +
                    ", middleName='" + middleName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", salary=" + salary +
                    '}';

            System.out.println("\t" + info);
        }
    }

    public static void printEmployeesWithSalaryLess(int refSalary) {
        for(Employee dude : list) {
            if(dude.getSalary() < refSalary)
                System.out.println(dude.getId() + ": " + dude.getFullName() + " with salary less than " + refSalary + ": " + dude.getSalary());

        }
    }

    public static void printEmployeesWithSalaryBigger(int refSalary) {
        for(Employee dude : list) {
            if(dude.getSalary() >= refSalary)
                System.out.println(dude.getId() + ": " + dude.getFullName() + " with salary bigger than " + refSalary + ": " + dude.getSalary());
        }
    }
}
