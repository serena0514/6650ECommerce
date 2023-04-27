package com.model;



public class ProductOrders {

  private ProductOrdersPK key;


  private Integer quantity;

  public ProductOrders(){}

  public ProductOrders(Product product, Order order, Integer quantity){
    key = new ProductOrdersPK();
    key.setOrder(order);
    key.setProduct(product);
    this.quantity = quantity;
  }


  public Product getProduct() {
    return this.key.getProduct();
  }


  public float getTotalPrice() {
    return getProduct().getPrice() * getQuantity();
  }

  public ProductOrdersPK getKey() {
    return key;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }


  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductOrders that = (ProductOrders) o;
    return key.equals(that.key) && Objects.equals(quantity, that.quantity);
  }

  public int hashCode() {
    return Objects.hash(key, quantity);
  }
}
