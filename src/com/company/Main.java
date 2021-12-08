package com.company;

public class Main {

    public static int id = 0;

    public static Employee list[] = new Employee[10];

    public static void main(String[] args) {
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

        System.out.println("Минимальная зарплата в месяц: " + getMinimumSalary(list));

        System.out.println("Максимальная зарплата в месяц: " + getMaximumSalary(list));

        System.out.println("Средняя зарплата в месяц: " +  getAverageSalary(list));

        String[] names = getFullNames();
        for(String n : names)
            System.out.println(n);

        indexSalary(list, 10);

        System.out.println("Сумма затрат на зарплаты в месяц по отделу: " + getSalariesSumInDep(1));

        System.out.println("Минимальная зарплата в месяц по отделу: " + getMinimumSalaryInDep(1));

        System.out.println("Максимальная зарплата в месяц по отделу: " + getMaximumSalaryInDep(1));

        System.out.println("Средняя зарплата в месяц по отделу: " +  getAverageSalaryInDep(1));

        indexSalaryInDep( 2, -10);

        printEmployeesInfo(1);

        printEmployeesWithSalaryLess(10000);

        printEmployeesWithSalaryBigger(20000);
    }

    public static void printEmployees(Employee list[]) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i].toString());
        }
    }

    public static int getSalariesSum(Employee list[]) {
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            sum += list[i].getSalary();
        }

        return sum;
    }

    public static int getMinimumSalary(Employee list[]) {
        int min = list[0].getSalary();
        for (int i = 1; i < list.length; i++) {
            if(min > list[i].getSalary())
                min = list[i].getSalary();
        }
        return min;
    }

    public static int getMaximumSalary(Employee list[]) {
        int max = list[0].getSalary();
        for (int i = 1; i < list.length; i++) {
            if(max < list[i].getSalary())
                max = list[i].getSalary();
        }
        return max;
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
            int salary = dude.getSalary();
            salary += salary * (percent) / 100;
            dude.setSalary(salary);
        }
    }

    public static Employee[] getDudes(Employee list[], int department) {
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

    public static int getMinimumSalaryInDep(int department) {
        return getMinimumSalary(getDudes(list, department));
    }

    public static int getMaximumSalaryInDep(int department) {
        return getMaximumSalary(getDudes(list, department));
    }

    public static float getAverageSalaryInDep(int department) {
        return getAverageSalary(getDudes(list, department));
    }

    public static int getSalariesSumInDep(int department) {
        return getSalariesSum(getDudes(list, department));
    }

    public static void indexSalaryInDep(int department, int percent) {
        indexSalary(getDudes(list, department), percent);
    }

    public static void printEmployeesInfo(int department) {
        for(Employee dude : getDudes(list, department)) {
            String firstName = dude.getFirstName();
            String middleName = dude.getMiddleName();
            String lastName = dude.getLastName();
            int salary = dude.getSalary();

            String info = "Employee{" +
                    "firstName='" + firstName + '\'' +
                    ", middleName='" + middleName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", salary=" + salary +
                    '}';

            System.out.println(info);
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
