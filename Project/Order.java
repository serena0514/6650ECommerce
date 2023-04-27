import java.util.*;



public class Order {


  private Integer id;


  private LocalDate dateCreated;


  private boolean isPaid;



  private List<ProductOrders> productOrders = new ArrayList<>();

  public Order() {}


  public Double getTotalOrderPrice() {
    double sum = 0D;
    List<ProductOrders> orderProducts = getProductOrders();
    for (ProductOrders op : orderProducts) {
      sum += op.getTotalPrice();
    }
    return sum;
  }


  public int getNumberOfProducts() {
    return this.productOrders.size();
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public boolean isPaid() {
    return isPaid;
  }

  public void setPaid(boolean paid) {
    isPaid = paid;
  }

  public List<ProductOrders> getProductOrders() {
    return productOrders;
  }

  public void setProductOrders(List<ProductOrders> productOrders) {
    this.productOrders = productOrders;
  }
}
