package com.model;




public class Product {


  private Integer id;


  private String name;

  private float price;

  public Product(){}

  public Product(String name, float price) {
    if(name == null){
      System.out.println("name cannot be null");
    }
    this.name = name;
    this.price = price;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }
}
