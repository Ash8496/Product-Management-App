package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service {

   private static Connection conn = null;


   static {
       String url="jdbc:mysql://localhost:3306/j2ee";
       String userName = "root";
       String password = "tiger";

       try {
           conn = DriverManager.getConnection(url,userName,password);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

   }

    public int insertProduct(Product newProduct) {
       int n=0;
       String insertQuery ="INSERT INTO PRODUCT_INFO VALUES(? , ? , ? , ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1,newProduct.getProductId());
            pstmt.setString(2,newProduct.getProductName());
            pstmt.setDouble(3,newProduct.getProductPrice());
            pstmt.setString(4, newProduct.getProductType());
            n= pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("INVALID DATA, PRODUCT NOT INSERTED ");
        }
        return n;
    }

    public int updateProduct(Product uptProduct) {
        String updateQuery = "UPDATE PRODUCT_INFO SET PRODUCTNAME = ? , PRODUCTPRICE = ? , " +
                "PRODUCTTYPE = ? WHERE PRODUCTID = ?";
        int n = 0 ;
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1 , uptProduct.getProductName());
            pstmt.setDouble(2 , uptProduct.getProductPrice());
            pstmt.setString(3 , uptProduct.getProductType());
            pstmt.setInt(4 , uptProduct.getProductId());
            n = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("PRODUCT NOT FOUND !!");
        }

        return n ;
    }

    public int deleteProduct(int productId) {
        String delQuery = "DELETE FROM PRODUCT_INFO WHERE PRODUCTID = ?";
        int n = 0 ;
        try {
            PreparedStatement pstmt = conn.prepareStatement(delQuery);
            pstmt.setInt(1 , productId);
            n = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("PRODUCT NOT FOUND !!");
        }

        return n ;

    }

    public List<Product> displayAllProducts()
    {
        String displayQuery = "SELECT * FROM PRODUCT_INFO";
        List<Product> productList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayQuery);

            while (rs.next())
            {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                double productPrice = rs.getDouble(3);
                String productType = rs.getString(4);

                Product product = new Product(productId , productName , productPrice , productType);
                productList.add(product);
            }
        } catch (SQLException e) {

        }
        return productList;
    }

    public Product searchProductById(int productId) {
        String searchQuery = "SELECT * FROM PRODUCT_INFO WHERE PRODUCTID = ?";
        Product product = new Product() ;
        try {
            PreparedStatement pstmt = conn.prepareStatement(searchQuery);
            pstmt.setInt(1 , productId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                product =
                        new Product(rs.getInt(1) , rs.getString(2) ,
                                rs.getDouble(3) , rs.getString(4));
            }

        } catch (SQLException e) {

        }
        return  product ;
    }

    public List<Product> filterByPrice(double lowest, double highest) {
        String qry = "SELECT * FROM PRODUCT_INFO WHERE PRODUCTPRICE BETWEEN ? AND ?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(qry);
            pstmt.setDouble(1, lowest);
            pstmt.setDouble(2, highest);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
                list.add(product);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<Product> filterByCategory(String ch1) {
        String qry = "SELECT * FROM PRODUCT_INFO WHERE PRODUCTTYPE = ?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(qry);
            pstmt.setString(1, ch1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                double price = rs.getDouble(3);
                String type = rs.getString(4);
                Product product = new Product(id, name, price, type);
                list.add(product);
            }
        } catch (SQLException e) {

        }
        return list;
    }
}
