package cursoSpringBoot.models;
// Clase POJO
// Es una clase sencilla que no sigue ninguna convención o restricción especial impuesta por frameworks o bibliotecas.
// Los POJOs son simplemente objetos Java que contienen atributos y métodos getter y setter, sin lógica compleja ni dependencias de otros componentes.
public class Customer {
    //atributos
    private int customerId;
    private String customerName;
    private String userName;
    private String customerPassword;

    //constructor

    public Customer(int customerId, String customerName, String userName, String customerPassword) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.userName = userName;
        this.customerPassword = customerPassword;
    }

    //getters y setters
    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getUserName() {
        return userName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }


}
