import java.util.*;

public class main {

    private static ArrayList<Web_User> web_users = new ArrayList<>();
    private static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>(); // TODO:: remmber to add accounts to this list
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
                case "6": showAllObjects();
                    showAllObjects();
                    break;
                case "7": //Show Object ID
                    showObject(scan);
                    break;
            }
        } while (!choice.equals("9")); // end of loop
    }

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
                Account account;
                ShoppingCart shoppingCart;
                String toPrint ="";
                if(scan.nextLine().equals("y")){
                    account = new PremiumAccount(id,billing_address,false,Integer.parseInt(balance));
                    toPrint = toPrint.concat("Premium user added");
                }
                else {
                    account = new Account(id, billing_address, false, Integer.parseInt(balance));
                    toPrint = toPrint.concat("Regular user added");
                }
                Customer customer = new Customer(IDGenerate(),new Address(city,street,Integer.parseInt(number)),phone,email);
                objects.put(customer.getId(),customer);
                objects.put(id,account);
                customer.setAccount(account);
                account.setCustomer(customer);//TODO:: Understand the set customer stuff
                user.setCustomer(customer);
                shoppingCart = new ShoppingCart(IDGenerate(),account.getOpen(),account);
                objects.put(shoppingCart.getId(),shoppingCart);
                accounts.add(account);
                web_users.add(user);
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
            if(web_users.stream().anyMatch(user -> user.getId().equals(id))){
                web_users.removeIf(o->o.getId().equals(id));
                System.out.println("\t User Removed!");
                removeduser = false;
            }
            else {
                System.out.println("User "+id+" dosen't exist please try again");
                System.out.println("do you want to try again? [y/n]");
                String again = scan.nextLine();
                if(again.equals("n")) removeduser = false;
            }
        }
    }

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
                    if(suppliers.stream().noneMatch(sup->sup.getId().equals(supplier)))
                        System.out.println("no such a supplier, please insert another id");
                    else {
                        mySupplier = suppliers.stream().filter(s->s.getId().equals(supplier)).findFirst().get();
                        trueSupplier = false;
                        System.out.println("Please insert price to product "+ id + " :");
                        price = scan.nextLine();
                    }
                } while (trueSupplier);

                Product newProduct = new Product(id,name,mySupplier, Integer.parseInt(price));
                products.add(newProduct);
                objects.put(id, newProduct);
                mySupplier.addProduct(newProduct);
                addedProduct = false;
                System.out.printf("\t Product %s was added!%n", id);
            }
        }
    }

    public static void delete_Product(Scanner scan){
        boolean removedProduct = true;
        while(removedProduct) {
            System.out.println("Please product id to remove:");
            String id = scan.nextLine();
            if(products.stream().anyMatch(product -> product.getId().equals(id))){
                Product toRemove = products.stream().filter(pro->pro.getId().equals(id)).findFirst().get();
                toRemove.getMySupplier().removeProduct(toRemove);
                products.remove(toRemove);
                System.out.printf("\t product %s Removed!%n", id);
                removedProduct = false;
            }
            else {
                System.out.printf("Product %s doesn't exist please try again%n", id);
                System.out.println("do you want to try again? [y/n]");
                String again = scan.nextLine();
                if(again.equals("n")) removedProduct = false;
            }
        }
    }

    private static void initiateSystem() {
        Address adress = new Address("a","ads",4);
        Supplier s = new Supplier("123", "moshe");
        Product p0 = new Product("Bamba", "Bamba", s, 10);
        Product p1 = new Product("Ramen", "Ramen", s, 20);
        Web_User user = new Web_User("000","000",UserState.New);
        objects.put("000",user);
        Customer customer = new Customer(IDGenerate(),new Address("city","street",100),"100","aa");
        objects.put(customer.getId(),customer);
        Account a = new Account(IDGenerate(),"a",false,100);
        objects.put(a.getId(),a);
        customer.setAccount(a);
        a.setCustomer(customer);
        user.setCustomer(customer);
        ShoppingCart shoppingCart = new ShoppingCart(IDGenerate(),a.getOpen(),a);
        objects.put(shoppingCart.getId(),shoppingCart);
        user.setShoppingCart(shoppingCart);
        accounts.add(a);
        web_users.add(user);

        Customer c = new Customer(IDGenerate(),adress,"123","bb");
        objects.put(c.getId(),c);


        p0.updateSupplier(s);
        p1.updateSupplier(s);
        s.addProduct(p0);
        s.addProduct(p1);
        a.setShoppingCart(shoppingCart);
        accounts.add(a);

        suppliers.add(s);
        products.add(p0);
        products.add(p1);
        objects.put(s.getId(), s);
        objects.put(p0.getId(), p0);
        objects.put(p1.getId(), p1);


    }

    public static void login_WebUser(Scanner scan) {
        boolean loggeduser = true;
        boolean loginmenu = true;
        while (loggeduser) {
            System.out.println("Please id to login:");
            String id = scan.nextLine();
            if (web_users.stream().anyMatch(user -> user.getId().equals(id))) {
                System.out.println("Please enter password for " + id + " :");
                String pass = scan.nextLine();
                if (web_users.stream().anyMatch(user -> user.getPassword().equals(pass))) {
                    //new menu
                    // Make order
                    // Display order
                    // Link Product(premium only)
                    // Logout WebUser
                    do {
                        System.out.println("1\t Make order");
                        System.out.println("2\t Display order");
                        System.out.println("3\t Link Product");
                        System.out.println("4\t Logout");
                        String choice = scan.nextLine();
                        switch (choice) {
                            case "1": //Make Order
                                MakeOrder(scan,id);
                                break;
                            case "2": //Display Order
                                System.out.println("Displaying Order");
                                break;
                            case "3": //Link Product
                                LinkProduct(scan,id);
                                break;
                            case "4": //Logout
                                logoutUser(id);
                                System.out.println("Logged Out!");
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

    private static void LinkProduct(Scanner scan, String id) {
        if(accounts.stream().filter(o -> o.getId().equals(id)).findFirst().get().getClass().getSimpleName() == "PremiumAccount"){
            System.out.println("Enter a product ID that you would like to connect");
            String productid = scan.nextLine();
            if(products.stream().anyMatch(o -> o.getId().equals(productid))){
                products.stream().filter(o -> o.getId().equals(productid)).findFirst().get().setPremiumAccount((PremiumAccount)accounts.stream().filter(o -> o.getId().equals(id)).findFirst().get());
                System.out.println("Enter price for product "+ productid);
                String newPrice = scan.nextLine();
                products.stream().filter(o -> o.getId().equals(productid)).findFirst().get().setPrice(Integer.parseInt(newPrice));
                System.out.println("Product price update successfully");
            }
            else{
                System.out.println("Wrong product ID");
            }
        }
        else{
            System.out.println("This function is available only for Premium Accounts");
        }
    }

    public static void logoutUser(String id){
        if(web_users.stream().anyMatch(o -> o.getId().equals(id))) {
            web_users.stream().filter(o -> o.getId().equals(id)).findFirst().get().getCustomer().getAccount().setClosed(new Date());
            web_users.stream().filter(o -> o.getId().equals(id)).findFirst().get().getCustomer().getAccount().setIs_closed(true);
            web_users.stream().filter(o -> o.getId().equals(id)).findFirst().get().setUserState(UserState.Blocked);
        }
    }

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

    public static void MakeOrder(Scanner scan,String accountID) //TODO:: function isn't complete
    {
        Account curAccount = (Account) objects.get(accountID);
        Supplier curSupplier;
        Product curProduct;
        LineItem curLineItem;

        Date testD = curAccount.getOpen();

        Order newOrder = new Order(IDGenerate(),curAccount.getOpen(),curAccount); //TODO:: get Date from account need to be Checked
        objects.put(newOrder.getId(),newOrder);
        newOrder.setMyAccount(curAccount);
        curAccount.addOrder(newOrder);
        String input;
        String quantity;
        boolean moreProducts = true;
        boolean PlacingOrder = true;
        while (PlacingOrder){
            System.out.println("Enter Supplier Name:");
            input = scan.nextLine();
            curSupplier = getSupplierByName(input);
            if (curSupplier==null) {
                System.out.println("Supplier Doesn't exist");
                continue;
            }
            PlacingOrder=false;
            while (moreProducts) {
                System.out.println("Enter Product ID from the Product List:");
                System.out.println(curSupplier);
                input = scan.nextLine();
                curProduct = curSupplier.getProductByID(input);
                if (curProduct==null){
                    System.out.println("Incorrect Product ID");
                    continue;
                }
                System.out.println("Enter quantity");
                quantity = scan.nextLine();
                curLineItem = new LineItem(IDGenerate(),Integer.parseInt(quantity),newOrder,curProduct);
                objects.put(curLineItem.getId(),curLineItem);
                newOrder.addLineItem(curLineItem); //TODO:: add ID for everyObject
                curAccount.getShoppingCart().addLineItem(curLineItem);
                System.out.println("Product Added to you'r Order '\n' Would you like to add more Products? y/n");
                input = scan.nextLine();
                if (input.equals("n"))
                {
                    moreProducts = false;
                }
            }
            System.out.println("You Order has Been PLaced");

//            System.out.println(newOrder);
            //TODO:: add Payment to Order
            //TODO:: add create Object to the Object Data Structure

        }
    }

    public static Account getAccountFromID(String ID){
        for (Account account : accounts ){
            if (account.getId().equals(ID))
                return account;
        }
        return null;
    }

    public static Supplier getSupplierByName(String curSupplierName){
        for (Supplier supplier : suppliers) {
            if (supplier.getName().equals(curSupplierName))
                return supplier;
        }
        return null;
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
            id = "0" + GlobalSerialNumber;}

        else{
            while (objects.containsKey("00" + GlobalSerialNumber))
            {GlobalSerialNumber++;}
            id = "00" + GlobalSerialNumber;}
        return id;
    }

}
