/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic.dao;

import com.electronic.beans.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dhannu
 */
public class dom {

    ResultSet rs;
    PreparedStatement pst = null;
    DbConnection db = new DbConnection();
    private int noOfRecords;

    Connection con;

    public ArrayList<Product> getALLProduct() {

        ArrayList<Product> list = new ArrayList();
        try {
            con = db.openConnection();
            pst = con.prepareStatement("select * from product ORDER BY id DESC");
            Product p;
            rs = pst.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getString("name"),
                        rs.getDouble("price"), rs.getString("model"), rs.getString("date"), rs.getString("photo"), rs.getString("description"), rs.getInt("quantity"), rs.getInt("id"), rs.getInt("category_id"));
                list.add(p);
            }
        } catch (Exception ex) {
            db.closeConnection();
            ex.printStackTrace();
        }
        return list;
    }

    public Product getProductById(int productId) {
        Product productObject = new Product();
        try {
            con = db.openConnection();
            pst = con.prepareStatement("select * from product where id=?");
            pst.setInt(1, productId);
            rs = pst.executeQuery();
            if (rs.next()) {
                productObject.setName(rs.getString("name"));
                productObject.setProductId(rs.getInt("id"));
                productObject.setPrice(rs.getDouble("price"));
                productObject.setPhoto(rs.getString("photo"));
                productObject.setPhoto(rs.getString("photo"));
                db.closeConnection();
                return productObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productObject;
    }

    public ArrayList<Product> getListProduct() {
        ArrayList<Product> selectLastProduct = new ArrayList();
        try {
            con = db.openConnection();
            pst = con.prepareStatement("select * from product ORDER BY ID DESC LIMIT 6");
            Product obj;
            rs = pst.executeQuery();
            while (rs.next()) {
                obj = new Product(rs.getString("name"), rs.getDouble("price"), rs.getString("model"), rs.getString("date"), rs.getString("photo"), rs.getString("description"), rs.getInt("quantity"), rs.getInt("id"), rs.getInt("category_id"));
                selectLastProduct.add(obj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectLastProduct;

    }

    public ArrayList<Product> getProductByName(String productName) {
        ArrayList<Product> ListProductByName = new ArrayList<>();
        try {
            con = db.openConnection();
            pst = con.prepareStatement("SELECT * FROM product WHERE name LIKE ? ESCAPE '!'");
            productName = productName.replace("!", "!!")
                    .replace("%", "!%").replace("_", "!_").replace("[", "![");
            pst.setString(1, productName + "%");
            Product p;
            rs = pst.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getString("name"), rs.getDouble("price"), rs.getString("model"), rs.getString("date"), rs.getString("photo"), rs.getString("description"), rs.getInt("quantity"), rs.getInt("id"), rs.getInt("cateogyr_id"));
                ListProductByName.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListProductByName;
    }

    public ArrayList<Product> getAllProductByCategoryId(int categoryId) {
        ArrayList<Product> list = new ArrayList();
        try {
            con = db.openConnection();
            pst = con.prepareStatement("select * from produc twhere category_id=?");
            pst.setInt(1, categoryId);
            Product p;
            rs = pst.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getString("name"), rs.getDouble("price"),
                        rs.getString("model"), rs.getString("date"), rs.getString("photo"),
                        rs.getString("descriptin"), rs.getInt("quantity"), rs.getInt("id"),
                        rs.getInt("category_id"));
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Product> getRecommendedItem(int categoryId, int productid) {
        ArrayList<Product> getItem = new ArrayList();
        try {
            con = db.openConnection();
            pst = con.prepareStatement("SELECT * from product where id  <> ? and category_id=? ORDER BY id ASC limit 6");
            pst.setInt(1, productid);
            pst.setInt(2, productid);
            Product p;
            rs = pst.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getString("name"), rs.getDouble("price"),
                        rs.getString("model"), rs.getString("date"), rs.getString("photo"),
                        rs.getString("descriptin"), rs.getInt("quantity"), rs.getInt("id"),
                        rs.getInt("category_id"));

                getItem.add(p);
            }

        } catch (Exception e) {
        }
        return getItem;
    }

    public boolean updateProductQuantity(Product product) throws SQLException {
        con = db.openConnection();
        int i = 0;
        pst = con.prepareStatement("update proudct set quantity=? where id=?");
        pst.setInt(1, product.getQuantity());
        pst.setInt(1, product.getProductId());
        i = pst.executeUpdate();
        db.closeConnection();
        if (i > 0) {
            return true;
        }

        return false;

    }

    public ArrayList<Product> getAllProductByPrice(double priceStart, double priceEnd) {
        ArrayList<Product> getAllProductByPrice = new ArrayList<>();
        try {
            con = db.openConnection();
            pst = con.prepareStatement("select * from product where price BETWEEN ? AND ?");

            pst.setDouble(1, priceStart);
            pst.setDouble(2, priceEnd);

            while (rs.next()) {
                Product product = new Product(rs.getString("name"), rs.getDouble("price"),
                        rs.getString("model"), rs.getString("date"), rs.getString("photo"),
                        rs.getString("descriptin"), rs.getInt("quantity"), rs.getInt("id"),
                        rs.getInt("category_id"));
                getAllProductByPrice.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getAllProductByPrice;
    }

    public ArrayList<Product> getAllProduct(int start, int limit) {
        ArrayList<Product> list = new ArrayList();
        try {
            con = db.openConnection();
            pst = con.prepareStatement("select * from product ORDER BY id DESC LIMIT ?,?");
            pst.setInt(1, start);
            pst.setInt(2, limit);
            Product p;
            rs = pst.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getString("name"), rs.getDouble("price"),
                        rs.getString("model"), rs.getString("date"), rs.getString("photo"),
                        rs.getString("descriptin"), rs.getInt("quantity"), rs.getInt("id"),
                        rs.getInt("category_id"));
                list.add(p);
            }
            //get number of record in DB
            rs = con.prepareStatement("SELECT count(*) FROM product").executeQuery();
            if (rs.next()) {

                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return list;

    }

    public List<Product> getAllProductByCategoryId(int categoryId, int start, int limit) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            con = db.openConnection();
            pst = con.prepareStatement("select * from product where category_id=? ORDER BY ID DESC LIMIT ?,?");
            pst.setInt(1, categoryId);
            pst.setInt(2, start);
            pst.setInt(3, limit);
            Product p;
            rs = pst.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getString("name"), rs.getDouble("price"),
                        rs.getString("model"), rs.getString("date"), rs.getString("photo"),
                        rs.getString("descriptin"), rs.getInt("quantity"), rs.getInt("id"),
                        rs.getInt("category_id"));
                list.add(p);
            }
            //get number of record in DB
            pst = con.prepareStatement("SELECT count(*) FROM product where category_id=?");
            pst.setInt(1, categoryId);
            rs = pst.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public double getMaxProductByPrice() {
        double higestprice = 0;
        try {
            con = db.openConnection();
            pst = con.prepareStatement("SELECT MAX(price) AS highestPrice FROM product");
            rs = pst.executeQuery();
            if (rs.next()) {
                higestprice = rs.getDouble("HighestPrice");
                db.closeConnection();
            }
        } catch (Exception e) {
            db.closeConnection();
        }
        return higestprice;
    }
}
