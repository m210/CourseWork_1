package com.company;

public class EmployeeBook {

    private final Employee list[];

    public EmployeeBook(int capacity, Employee... initEmployees) {
        this.list = new Employee[capacity];
        System.arraycopy(initEmployees, 0, list, 0, initEmployees.length);
    }

    public boolean addEmplooyee(Employee dude) {
        int i = 0;
        while(i < list.length) {
            if(list[i] == null)
                break;

            if(list[i++].equals(dude))
                return false;
        }

        if(i < list.length) {
            list[i] = dude;
            return true;
        }
        return false;
    }

    public boolean removeEmplooyee(int id) {
        for (int i = 0; i < list.length; i++) {
            Employee dude = list[i];
            if(dude != null && dude.getId() == id) {
                list[i] = null;
                return true;
            }
        }
        return false;
    }

    public Employee getEmployee(String fullName) {
        for (int i = 0; i < list.length; i++) {
            Employee dude = list[i];
            if(dude != null && dude.getFullName().equalsIgnoreCase(fullName)) {
                return dude;
            }
        }
        return null;
    }

    public void editEmployee(String fullName, Integer salary, Integer department) {
        Employee empl = getEmployee(fullName);
        if(empl != null) {
            if(salary != null)
                empl.setSalary(salary);

            if(department != null)
                empl.setDepartment(department);
        }
    }

    public void printInfoPerDepartment() {
        for(int depNum = 1; depNum <= 5; depNum++) {
            System.out.println("Список сотрудников в отделе " + depNum + ": ");
            printEmployees(getDudes(list, depNum));
        }
    }

    public void printEmployees() {
        printEmployees(list);
    }

    public int getSalariesSum() {
        return getSalariesSum(list);
    }

    public int getMinimumSalary() {
        return getMinimumSalary(list);
    }

    public int getMaximumSalary() {
        return getMaximumSalary(list);
    }

    public float getAverageSalary() {
        return getAverageSalary(list);
    }

    public String[] getFullNames() {
        String[] names = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            if(list[i] == null)
                continue;

            names[i] = list[i].getFullName();
        }
        return names;
    }

    public int getMinimumSalaryInDep(int department) {
        return getMinimumSalary(getDudes(list, department));
    }

    public int getMaximumSalaryInDep(int department) {
        return getMaximumSalary(getDudes(list, department));
    }

    public float getAverageSalaryInDep(int department) {
        return getAverageSalary(getDudes(list, department));
    }

    public int getSalariesSumInDep(int department) {
        return getSalariesSum(getDudes(list, department));
    }

    public void indexSalaryInDep(int department, int percent) {
        indexSalary(getDudes(list, department), percent);
    }

    public void indexSalary(int percent) {
        for(Employee dude : list) {
            if(dude == null)
                continue;

            int salary = dude.getSalary();
            salary += salary * (percent) / 100;
            dude.setSalary(salary);
        }
    }

    public void printEmployeesInfo(int department) {
        for(Employee dude : getDudes(list, department)) {
            if(dude == null)
                continue;

            String firstName = dude.getFirstName();
            String middleName = dude.getMiddleName();
            String lastName = dude.getLastName();
            int salary = dude.getSalary();

            String info = "Employee{" +
                    "id=" + dude.getId() +
                    ", firstName='" + firstName + '\'' +
                    ", middleName='" + middleName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", salary=" + salary +
                    '}';

            System.out.println(info);
        }
    }

    public void printEmployeesWithSalaryLess(int refSalary) {
        for(Employee dude : list) {
            if(dude == null)
                continue;

            if(dude.getSalary() < refSalary)
                System.out.println(dude.getId() + ": " + dude.getFullName() + " with salary less than " + refSalary + ": " + dude.getSalary());

        }
    }

    public void printEmployeesWithSalaryBigger(int refSalary) {
        for(Employee dude : list) {
            if(dude == null)
                continue;

            if(dude.getSalary() >= refSalary)
                System.out.println(dude.getId() + ": " + dude.getFullName() + " with salary bigger than " + refSalary + ": " + dude.getSalary());
        }
    }

    private void printEmployees(Employee list[]) {
        for (int i = 0; i < list.length; i++) {
            if(list[i] == null)
                continue;

            System.out.println("\t" + list[i].toString());
        }
    }

    private int getSalariesSum(Employee list[]) {
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            if(list[i] == null)
                continue;

            sum += list[i].getSalary();
        }

        return sum;
    }

    private int getFirst() {
        int i = 0;
        for (; i < list.length; i++) {
            if(list[i] != null)
                return i;
        }
        return -1;
    }

    private int getLength(Employee list[]) {
        int i = 0;
        int len = 0;
        for (; i < list.length; i++) {
            if(list[i] != null)
                len++;
        }
        return len;
    }

    private int getMinimumSalary(Employee list[]) {
        int first = getFirst();
        if(first == -1)
            return 0;

        int min = list[first].getSalary();
        for (int i = first + 1; i < list.length; i++) {
            if(list[i] == null)
                continue;

            if(min > list[i].getSalary())
                min = list[i].getSalary();
        }
        return min;
    }

    private int getMaximumSalary(Employee list[]) {
        int first = getFirst();
        if(first == -1)
            return 0;

        int max = list[first].getSalary();
        for (int i = first + 1; i < list.length; i++) {
            if(list[i] == null)
                continue;

            if(max < list[i].getSalary())
                max = list[i].getSalary();
        }
        return max;
    }

    private float getAverageSalary(Employee list[]) {
        return getSalariesSum(list) / (float) getLength(list);
    }

    private void indexSalary(Employee list[], int percent) {
        for(Employee dude : list) {
            if(dude == null)
                continue;

            int salary = dude.getSalary();
            salary += salary * (percent) / 100;
            dude.setSalary(salary);
        }
    }

    private Employee[] getDudes(Employee list[], int department) {
        int size = 0;
        for(Employee dude : list) {
            if(dude != null && dude.getDepartment() == department)
                size++;
        }

        int j = 0;
        Employee[] dudes = new Employee[size];
        for(Employee dude : list) {
            if(dude != null && dude.getDepartment() == department)
                dudes[j++] = dude;
        }

        return dudes;
    }
}
