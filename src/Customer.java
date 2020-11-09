public class Customer extends myObject{
    private String id;
    private Address address;
    private String phone;
    private String email;
    private Web_User web_user;
    private Account account;

    public Customer(String id, Address address, String phone, String email, Account account) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.account = account;
        account.setCustomer(this);
        main.addToObjects(this);
    }

    public String getId() {
        return id;
}

    public String getName() {
        return getId();
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Web_User getWeb_user() {
        return web_user;
    }

    public void setWeb_user(Web_User web_user) {
        this.web_user = web_user;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", address=" + address.toString() +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", web_user=" + web_user.getId() +
                ", account=" + account.getId() +
                '}';
    }

    @Override
    public void deleteObject() {
        account.deleteObject();
        main.removeFromObjects(this);
    }
}
