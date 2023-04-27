package main;

//import net.minidev.json.JSONArray;
//import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Server {
    public String uri;
    public Server(String port) {
        uri = "http://localhost:" + port;
    }


    public  void getAllProducts() {
        String productUri = uri+"/product";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(productUri, String.class);

        System.out.println(result);
    }

    public void getProductbyID(String ID) {

    }

    public void create(String name, float price ) throws IOException, InterruptedException, URISyntaxException, JSONException {
        System.out.println("inside create");
        String createProductUri = uri + "/product/create";
        System.out.println(createProductUri);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject productJson = new JSONObject();

        Map<String, List<String>> headersMap = new HashMap<>();
        List<String> contentTypeList = new ArrayList<>();
        contentTypeList.add("application/json");
        productJson.put("name", name);
        productJson.put("price", price);
        System.out.println(productJson.toString());

        //make the httprequest here
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI(createProductUri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(productJson.toString()))
                .build();
        HttpClient httpclient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpclient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());


    }

     public void createOrder(Map<Product, Integer> orders) throws JSONException, URISyntaxException, IOException, InterruptedException {

         //create Json product here ----start
         JSONObject obj = new JSONObject();//this is the final Json to pass on
         JSONArray a = new JSONArray();
         for(Product p: orders.keySet()) {
             JSONObject o = new JSONObject();
             Integer quantity = orders.get(p);

             JSONObject insideProduct = new JSONObject();
             insideProduct.put("id", p.getId());
             insideProduct.put("name", p.getName());
             insideProduct.put("price", p.getPrice());

             o.put("product", insideProduct);
             o.put("quantity", orders.get(p));
             System.out.println(o);
             a.put(o);

         }
         System.out.println("a" + a.toString(4));
         obj.put("productOrders", a);
         System.out.println("obj " + obj.toString(4));
         //----end of creating json object

         //make the http request here
         String myuri = uri+"/order";
         System.out.println(myuri);

         HttpRequest postRequest = HttpRequest.newBuilder()
                 .uri(new URI(myuri))
                 .header("Content-Type", "application/json")
                 .POST(HttpRequest.BodyPublishers.ofString(obj.toString()))
                 .build();
         HttpClient httpclient = HttpClient.newHttpClient();
         HttpResponse<String> response = httpclient.send(postRequest, HttpResponse.BodyHandlers.ofString());
         System.out.println(response.statusCode());
         System.out.println(response.body());



     }
//    public static void updateProduct(Integer id, Product product) {
//


    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, JSONException {
        Server s = new Server("7788");
        System.out.println(s.uri);

 //       s.getAllProducts();
//       s.create("test1", (float) 3.3);
//        s.create("test3", (float) 3.3);
        Product t1 = new Product(5,"test1", (float)3.30);
        Product t2 = new Product(4,"test2", (float)3.30);
        Map<Product, Integer> orders = new HashMap<>();//product and quantity
        orders.put(t1, 2);
        orders.put(t2, 1);
        for (Product p: orders.keySet()) {
            System.out.println(p.getName());
            System.out.println(orders.get(p));
        }
       s.createOrder(orders);


    }
}