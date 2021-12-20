package com.company;

public class EmployeeBook {

    private final Employee list[];

    public EmployeeBook(int capacity, Employee... initEmployees) {
        this.list = new Employee[capacity];
        System.arraycopy(initEmployees, 0, list, 0, initEmployees.length);
    }

    public boolean addEmplooyee(Employee dude) {
        /*
        В этом весь и прикол, если свободной ячейки не найдено, тогда i == 20 и
        соответственно добавлять новый элемент некуда, а если свободный элемент доступен
        по максимально возможному значению, тогда list.length - 1 все же меньше чем list.length.
        А вообще, я не знаю, зачем я написал именно такую логику,
        лучше перепишу ее на менее противоречивую)))
         */
        int i = 0;
        while(i < list.length) {
            if(list[i] == null) {
                list[i] = dude;
                return true;
            }

            if(list[i++].equals(dude))
                return false;
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
            printEmployees(getDudesInDepartment(list, depNum));
        }
    }

    public void printEmployees() {
        printEmployees(list);
    }

    public float getSalariesSum() {
        return getSalariesSum(list);
    }

    public Employee getWithMinimumSalary() {
        return getWithMinimumSalary(list);
    }

    public Employee getWithMaximumSalary() {
        return getWithMaximumSalary(list);
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

    public Employee getWithMinimumSalaryInDep(int department) {
        return getWithMinimumSalary(getDudesInDepartment(list, department));
    }

    public Employee getWithMaximumSalaryInDep(int department) {
        return getWithMaximumSalary(getDudesInDepartment(list, department));
    }

    public float getAverageSalaryInDep(int department) {
        return getAverageSalary(getDudesInDepartment(list, department));
    }

    public int getSalariesSumInDep(int department) {
        return getSalariesSum(getDudesInDepartment(list, department));
    }

    public void indexSalaryInDep(int department, int percent) {
        indexSalary(getDudesInDepartment(list, department), percent);
    }

    public void indexSalary(int percent) {
        for(Employee dude : list) {
            if(dude == null)
                continue;

            float salary = dude.getSalary();
            salary += salary * (percent) / 100;
            dude.setSalary(salary);
        }
    }

    public void printEmployeesInfo(int department) {
        for(Employee dude : getDudesInDepartment(list, department)) {
            if(dude == null)
                continue;

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

            System.out.println("\t" + list[i]);
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

    private Employee getWithMinimumSalary(Employee list[]) {
        int first = getFirst();
        if(first == -1)
            return null;

        Employee ret = list[first];
        float min = list[first].getSalary();
        for (int i = first + 1; i < list.length; i++) {
            if(list[i] == null)
                continue;

            if(min > list[i].getSalary()) {
                min = list[i].getSalary();
                ret = list[i];
            }
        }
        return ret;
    }

    private Employee getWithMaximumSalary(Employee list[]) {
        int first = getFirst();
        if(first == -1)
            return null;

        Employee ret = list[first];
        float max = list[first].getSalary();
        for (int i = first + 1; i < list.length; i++) {
            if(list[i] == null)
                continue;

            if(max < list[i].getSalary()) {
                max = list[i].getSalary();
                ret = list[i];
            }
        }
        return ret;
    }

    private float getAverageSalary(Employee list[]) {
        return getSalariesSum(list) / (float) getLength(list);
    }

    private void indexSalary(Employee list[], int percent) {
        for(Employee dude : list) {
            if(dude == null)
                continue;

            float salary = dude.getSalary();
            salary += salary * (percent) / 100;
            dude.setSalary(salary);
        }
    }

    private Employee[] getDudesInDepartment(Employee list[], int department) {
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
