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

        String[] names = getLastNames(list);
        for(String n : names)
            System.out.println(n);
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

    public static String[] getLastNames(Employee list[]) {
        String[] names = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            names[i] = list[i].getLastName() + " "  + list[i].getMiddleName() + " " + list[i].getFirstName();
        }
        return names;
    }
}
