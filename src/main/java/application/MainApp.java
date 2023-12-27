package application;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static Service service = new Service();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("==========================");
        System.out.println("SELECT OPERATIONS");
        System.out.println("1. INSERT PRODUCT");
        System.out.println("2. REMOVE PRODUCT");
        System.out.println("3. UPDATE PRODUCT");
        System.out.println("4. DISPLAY PRODUCT");
        System.out.println("5. SEARCH BY PRODUCT ID");
        System.out.println("6. FILTER PRODUCTS");
        System.out.println("7. EXIT");
        int ch = sc.nextInt();


        switch (ch){
            case 1:
                    insertProduct();
                    break;
            case 2:
                    removeProduct();
                    break;
            case 3:
                    updateProduct();
                    break;
            case 4:
                    displayProduct();
                    break;
            case 5:
                    searchProductById();
                    break;
            case 6:
                    filterProduct();
                    break;
            case 7:
                    System.exit(0);
                    break;
            default:
                System.out.println("INVALID INPUT");

        }

        main(args);
    }

    public static void insertProduct()
    {
        System.out.println("ENTER ID ");
        int productId = sc.nextInt();
        System.out.println("ENTER PRODUCT NAME ");
        String productName = sc.next() ;
        System.out.println("ENTER PRODUCT PRICE ");
        double productPrice = sc.nextDouble();
        System.out.println("ENTER PRODUCT TYPE ");
        String productType = sc.next();

        Product newProduct = new Product(productId , productName , productPrice , productType);

        int n = service.insertProduct(newProduct);
        System.out.println(n + " RECORD ADDED !!");
    }


    public static void updateProduct()
    {
        System.out.println("ENTER ID ");
        int productId = sc.nextInt();
        System.out.println("ENTER UPDATED PRODUCT NAME ");
        String productName = sc.next() ;
        System.out.println("ENTER UPDATED PRODUCT PRICE ");
        double productPrice = sc.nextDouble();
        System.out.println("ENTER UPDATED PRODUCT TYPE ");
        String productType = sc.next();
        Product uptProduct = new Product(productId , productName , productPrice , productType);
        int n = service.updateProduct(uptProduct);
        System.out.println(n +" PRODUCT UPDATED !!");
    }


    public static void removeProduct(){
        System.out.println("ENTER PRODUCT ID ");
        int productId = sc.nextInt();
        int n = service.deleteProduct(productId);
        System.out.println(n + " PRODUCT DELETED !! ");
    }

    public static void displayProduct()
    {
        List<Product> productList = service.displayAllProducts();
        System.out.println("Id \t\t Name \t\t Price \t\t Type ");
        for (Product p : productList){
            System.out.println(p.getProductId() +" \t "+ p.getProductName() +" \t "+ p.getProductPrice() +" \t "+p.getProductType());
        }

    }

    public static void searchProductById(){
        System.out.println("ENTER PRODUCT ID ");
        int productId = sc.nextInt() ;
        Product p = service.searchProductById(productId);
        System.out.println("Id \t\t Name \t\t Price \t\t Type ");
        System.out.println(p.getProductId() +" \t "+ p.getProductName() +" \t "+ p.getProductPrice() +" \t "+p.getProductType());

    }

    public static void filterProduct(){
        System.out.println("SELECT OPERATIONS");
        System.out.println("1. FILTER PRODUCTS BY PRICE RANGE");
        System.out.println("2. FILTER PRODUCTS BY CATEGORY");
        int ch = sc.nextInt();
        switch (ch){
            case 1:
                System.out.print("ENTER THE LOWEST PRICE :");
                double lowest = sc.nextDouble();
                System.out.print("ENTER THE HIGHEST PRICE :");
                double highest = sc.nextDouble();
                List<Product> productList = service.filterByPrice(lowest,highest);
                for (Product p : productList){
                    System.out.println(p.getProductId() +" \t "+ p.getProductName() +" \t "+ p.getProductPrice() +" \t "+p.getProductType());
                }
                break;

            case 2:
                System.out.print("ENTER THE CATEGORY : ");
                String ch1 = sc.next();
                List<Product> productList2 = service.filterByCategory(ch1);
                for (Product p : productList2){
                    System.out.println(p.getProductId() +" \t "+ p.getProductName() +" \t "+ p.getProductPrice() +" \t "+p.getProductType());
                }
                break;
            default:
                System.out.println("INVALID INPUT");

        }
    }
}



