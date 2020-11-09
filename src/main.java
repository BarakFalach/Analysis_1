import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane;

import java.util.*;

public class main {

    private static Map<String, Product> products = new Hashtable<>();
    private static ArrayList<Supplier> suppliers = new ArrayList<>();

    private static int GlobalSerialNumber = 0 ;
    private static Map<String, myObject> objects = new Hashtable<>();

    public static void main(String[] args) {

        initiateSystem();

        String choice;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("please choose one of the following numbers:");
            System.out.println("1\t Add WebUser");
            System.out.println("2\t Remove WebUser");
            System.out.println("3\t Login WebUser");
            System.out.println("4\t Add Product");
            System.out.println("5\t Delete Product");
            System.out.println("6\t ShowAllObjects");
            System.out.println("7\t ShowObjectId");
            System.out.println("9\t quit");

            choice = scan.nextLine();
            switch (choice) {
                case "1": //Add User
                    add_WebUser(scan);
                    break;
                case "2": //Remove
                    remove_WebUser(scan);
                    break;
                case "3": //Login
                    login_WebUser(scan);
                    break;
                case "4": //Add Product
                    add_Product(scan);
                    break;
                case "5": //Delete Product
                    delete_Product(scan);
                    break;
                case "6": //showAllObjects
                    showAllObjects();
                    break;
                case "7": //Show Object ID
                    showObject(scan);
                    break;
            }
        } while (!choice.equals("9")); // end of loop
    }

    /** Web User */
    public static void add_WebUser(Scanner scan){
        boolean addeduser = true;
        while(addeduser) {
            System.out.println("Please Enter id of the new User:");
            String id = scan.nextLine();
            if(objects.containsKey(id)){
                System.out.println("User Already exist please enter another id");
            }
            else {
                System.out.println("Please Enter Password for "+ id + " :");
                String pass = scan.nextLine();
                System.out.println("Please Enter city:");
                String city = scan.nextLine();
                System.out.println("Please Enter street:");
                String street = scan.nextLine();
                System.out.println("Please Enter number of apartment:");
                String number = scan.nextLine();
                System.out.println("Please Enter Phone number:");
                String phone = scan.nextLine();
                System.out.println("Please Enter email:");
                String email = scan.nextLine();
                System.out.println("Please Enter billing address:");
                String billing_address = scan.nextLine();
                System.out.println("Please Enter account balnace:");
                String balance = scan.nextLine();
                System.out.println("Is this a Premium account?[y/n]");

                Web_User user = new Web_User(id,pass,UserState.New);
                //objects.put(user.getId(), user);
                ShoppingCart shoppingCart = new ShoppingCart(IDGenerate(), user);

                Account account;
                String toPrint ="";
                if(scan.nextLine().equals("y")){
                    account = new PremiumAccount(IDGenerate(), shoppingCart, billing_address,false,Integer.parseInt(balance));
                    toPrint = toPrint.concat("Premium user added");
                }
                else {
                    account = new Account(IDGenerate(), shoppingCart,billing_address, false, Integer.parseInt(balance));
                    toPrint = toPrint.concat("Regular user added");
                }
                Customer customer = new Customer(IDGenerate(),new Address(city,street,Integer.parseInt(number)),phone,email, account);
                //objects.put(customer.getId(),customer);
                //objects.put(account.getId(), account);
                user.setCustomer(customer);
                //objects.put(shoppingCart.getId(),shoppingCart);
                //accounts.add(account);
                //web_users.add(user);
                System.out.println(toPrint);
                System.out.println(account.toString());
                addeduser = false;
            }
        }
    }

    public static void remove_WebUser(Scanner scan){
        boolean removeduser = true;
        while(removeduser) {
            System.out.println("Please Enter id to remove:");
            String id = scan.nextLine();
            if(objects.containsKey(id)){
                objects.get(id).deleteObject();
                System.out.println("\t User Removed!");
                removeduser = false;
            }
            else {
                System.out.println("User "+id+" doesn't exist please try again");
                System.out.println("do you want to try again? [y/n]");
                String again = scan.nextLine();
                if(again.equals("n")) removeduser = false;
            }
        }
    }

    public static void login_WebUser(Scanner scan) {
        boolean loggeduser = true;
        boolean loginmenu = true;
        while (loggeduser) {
            System.out.println("Please id to login:");
            String id = scan.nextLine();
            if (objects.containsKey(id)) {
                System.out.println("Please enter password for " + id + " :");
                String pass = scan.nextLine();
                if (((Web_User)objects.get(id)).getPassword().equals(pass)) {
                    //new menu
                    // Make order
                    // Display order
                    // Link Product(premium only)
                    // Logout WebUser
                    do {
                        System.out.println("1\t Make order");
                        System.out.println("2\t Display order");
                        System.out.println("3\t Link Product");
                        System.out.println("4\t Logout WebUser");
                        String choice = scan.nextLine();
                        switch (choice) {
                            case "1": //Make Order
                                MakeOrder(scan,id);
                                break;
                            case "2": //Display Order
                                DisplayOrder(scan,id);
                                break;
                            case "3": //Link Product
                                LinkProduct(scan,id);
                                break;
                            case "4": //Logout
                                logoutUser(id);
                                loginmenu = false;
                                loggeduser = false;
                                break;
                        }

                    } while (loginmenu);
                } else { //Wrong pass or Logout
                    System.out.println("Wrong Password!");
                    loggeduser = false;
                }
            } else {
                System.out.println("User id:  " + id + " doesn't exist please try again");
                loggeduser = false;
            }
        }
    }

    public static void logoutUser(String id){
        if(objects.containsKey(id)) {
            Web_User wu = (Web_User) objects.get(id);
            wu.getCustomer().getAccount().setClosed(new Date());
            wu.getCustomer().getAccount().setIs_closed(true);
            wu.getCustomer().getAccount().setIs_closed(true);
            System.out.println("Logged Out!");
        }
        else {
            System.out.println("no such a user");
        }
    }

    /** Product */

    public static void add_Product(Scanner scan){
        boolean addedProduct = true;
        boolean trueSupplier = true;
        Supplier mySupplier = null;
        String price = "";
        while(addedProduct) {
            System.out.println("Please Enter id of the new Product:");
            String id = scan.nextLine();
            if(objects.containsKey(id)){
                System.out.println("Product Already exist please enter another id");
            }
            else {
                System.out.println("Please insert name to product "+ id + " :");
                String name = scan.nextLine();
                System.out.println("Please insert supplier to product "+ id + " :");
                do {
                    String supplier = scan.nextLine();
                    if(!objects.containsKey(supplier))
                        System.out.println("no such a supplier, please insert another id");
                    else {
                        mySupplier = (Supplier) objects.get(supplier);
                        trueSupplier = false;
                        System.out.println("Please insert price to product "+ id + " :");
                        price = scan.nextLine();
                    }
                } while (trueSupplier);

                Product newProduct = new Product(id,name,mySupplier, Integer.parseInt(price));
                //products.put(id, newProduct);
                //objects.put(id, newProduct);
                mySupplier.addProduct(newProduct);
                addedProduct = false;
                System.out.printf("\t Product %s was added!%n", id);
            }
        }
    }

    public static void delete_Product(Scanner scan){
        boolean removedProduct = true;
        while(removedProduct) {
            System.out.println("Please insert product name to remove:");
            String name = scan.nextLine();
            if(products.containsKey(name)){
                Product toRemove = products.get(name);
                toRemove.getMySupplier().removeProduct(toRemove);
                toRemove.deleteObject();
                System.out.printf("\t product %s Removed!%n", name);
                removedProduct = false;
            }
            else {
                System.out.printf("Product %s doesn't exist please try again%n", name);
                System.out.println("do you want to try again? [y/n]");
                String again = scan.nextLine();
                if(again.equals("n")) removedProduct = false;
            }
        }
    }

    private static void LinkProduct(Scanner scan, String id) {
        //if(!objects.get(id).getClassName().equals("WebUser"))
        //    return;
        if(((Web_User)objects.get(id)).getCustomer().getAccount().getClass().getSimpleName().equals("PremiumAccount")){
            System.out.println("Enter a product name that you would like to connect");
            String productName = scan.nextLine();
            if(products.containsKey(productName)){
                products.get(productName).setPremiumAccount((PremiumAccount)((Web_User)objects.get(id)).getCustomer().getAccount());
                System.out.println("Enter price for product "+ productName);
                String newPrice = scan.nextLine();
                products.get(productName).setPrice(Integer.parseInt(newPrice));
                System.out.println("Enter quantity for product "+ productName);
                String quantity = scan.nextLine();
                products.get(productName).setQuantity(Integer.parseInt(quantity));
                System.out.println("Product update successfully");
            }
            else{
                System.out.println("Wrong product name");
            }
        }
        else{
            System.out.println("This function is available only for Premium Accounts");
        }
    }

    /** Order */

    private static void DisplayOrder(Scanner scan, String id) {
        System.out.println(((Web_User)objects.get(id)).getCustomer().getAccount().getLastOrder());
    }

    public static void MakeOrder(Scanner scan,String accountID) { //TODO:: function isn't complete
        Account curAccount = ((Web_User) objects.get(accountID)).getCustomer().getAccount();
        myObject object = null;
        Product curProduct;
        LineItem curLineItem;

        Order newOrder = new Order(IDGenerate(),curAccount.getOpen(),curAccount); //TODO:: get Date from account need to be Checked
        newOrder.setMyAccount(curAccount);
        curAccount.addOrder(newOrder);
        String input;
        String quantity;
        boolean moreProducts = true;
        boolean PlacingOrder = true;
        while (PlacingOrder){
            System.out.println("Enter Supplier/Premium account name:");
            input = scan.nextLine();
            object = getSupplierByName(input);
            if(object==null)
                object = getAccountByName(input);

            if (object == null) {
                System.out.println("Supplier/Premium account Doesn't exist");
                continue;
            }
            PlacingOrder=false;
            while (moreProducts) {
                System.out.println("Enter Product name from the Product List:");
                System.out.println(object.getName());
                input = scan.nextLine();
                curProduct = curSupplier.getProductByName(input);
                if (curProduct==null){
                    System.out.println("Incorrect Product Name");
                    continue;
                }
                System.out.println("Enter quantity");
                quantity = scan.nextLine();
                curLineItem = new LineItem(IDGenerate(),Integer.parseInt(quantity),newOrder,curProduct);
                newOrder.addLineItem(curLineItem);
                curAccount.getShoppingCart().addLineItem(curLineItem);
                System.out.println("Product Added to you'r Order '\n' Would you like to add more Products? y/n");
                input = scan.nextLine();
                if (input.equals("n"))
                {
                    moreProducts = false;
                }
            }
            System.out.println("How would you like to pay?");
            System.out.println("1\t DelayedPayment");
            System.out.println("2\t ImmeditaePayment");
            String paymethod = scan.nextLine();
            switch (paymethod){
                case "1":
                    createDelayedPayment(newOrder,curAccount,scan);
                    break;
                case "2":
                    Payment p = new ImmediatePayment(IDGenerate(),newOrder,curAccount);
                    curAccount.addPayment(p);
                    newOrder.addPayment(p);
                    p.setPaid(new Date());
                    //objects.put(p.getId(),p);
                    break;
            }
            System.out.println("You Order has Been PLaced");
        }
    }

    /** Show Objects */

    public static void showAllObjects(){
        System.out.println("Showing All Objects:");
        objects.forEach((k,v)-> {
            System.out.println(objects.get(k).showShortObject());
        });
        System.out.println();
    }

    public static void showObject(Scanner scan){
        boolean flag = false;
        do {
            System.out.println("Please enter object id to show:");
            String searchId = scan.nextLine();
            if(!objects.containsKey(searchId))
                System.out.println("Object id doesn't exist, please insert another object id");
            else {
                System.out.println(objects.get(searchId).toString());
                System.out.println();
                flag = true;
            }
        } while (!flag);
    }

    /** add, remove object to list */

    public static void addToObjects(myObject object){
        if (!objects.containsKey(object.getId()))
            objects.put(object.getId(), object);

        if (object.getClassName().equals("Product"))
            products.put(object.getName(), (Product)object);

        if (object.getClassName().equals("Supplier"))
            suppliers.add((Supplier)object);
    }

    public static void removeFromObjects(myObject object){
        objects.remove(object.getId());
        if (object.getClassName().equals("Product"))
            products.remove(object.getId());

        if (object.getClassName().equals("Supplier"))
            suppliers.add((Supplier)object);
    }


    /** everything else */

    private static void initiateSystem() {
        Address address = new Address("a","ads",4);
        Supplier s = new Supplier(IDGenerate(), "moshe");
        Product p0 = new Product("Bamba", "tom", s, 10);
        Product p1 = new Product("Ramen", "barak", s, 20);
        Web_User user = new Web_User(IDGenerate(),"000",UserState.New);

        ShoppingCart shoppingCart = new ShoppingCart(IDGenerate(), user);
        Account account = new PremiumAccount(IDGenerate(), shoppingCart,"a",false,100);
        Customer customer = new Customer(IDGenerate(),new Address("Tel-Aviv","Jaffa",100),"100","Almoni@gmail.com", account);
        user.setCustomer(customer);

        p0.updateSupplier(s);
        p1.updateSupplier(s);
        s.addProduct(p0);
        s.addProduct(p1);
    }

    public static String IDGenerate(){
        String id;
        if (GlobalSerialNumber>99){
            while (objects.containsKey(Integer.toString(GlobalSerialNumber)))
            {GlobalSerialNumber++;}
            id = Integer.toString(GlobalSerialNumber);
        }
        else if (GlobalSerialNumber>9){
            while (objects.containsKey("0" + GlobalSerialNumber))
            {GlobalSerialNumber++;}
            if (GlobalSerialNumber>=100){
                id = IDGenerate();
                return id;
            }
            id = "0" + GlobalSerialNumber;
        }

        else{
            while (objects.containsKey("00" + GlobalSerialNumber))
            {GlobalSerialNumber++;}
            if (GlobalSerialNumber>=10){
                id = IDGenerate();
                return id;
            }
            id = "00" + GlobalSerialNumber;
        }
        return id;
    }

    public static Supplier getSupplierByName(String curSupplierName){
        for (Supplier supplier : suppliers) {
            if (supplier.getName().equals(curSupplierName))
                return supplier;
        }
        return null;
    }

    public static Account getAccountByName(String curPremiumAccount){
        for (String s : objects.keySet()) {
            if (objects.get(s).getClass().getSimpleName().equals("PremiumAccount") && s.equals(curPremiumAccount))
                return (Account) objects.get(s);
        }
        return null;
    }

    public static void createDelayedPayment(Order newOrder,Account curAccount,Scanner scan){

        Date testD = new Date();
        Calendar calender = Calendar.getInstance();
        calender.setTime(testD);
        Payment payment;


        String input;
        System.out.println("How many payments would you like to pay?");
        input = scan.nextLine();
        System.out.println("You'r Payments are at:");

        for (int i =0;i< Integer.parseInt(input);i++){
            payment = new DelayedPayment(IDGenerate(),newOrder,curAccount);
            objects.put(payment.getId(),payment);
            calender.add(Calendar.MONTH,1);
            payment.setPaid(calender.getTime());
            newOrder.addPayment(payment);
            curAccount.addPayment(payment);
            System.out.println(payment.getPaid());
        }


    }

}
