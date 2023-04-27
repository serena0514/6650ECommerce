package main;

//import net.minidev.json.JSONObject;
//import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONObject;
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

    public void create(String name, float price ) throws IOException, InterruptedException, URISyntaxException {
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

     public void createOrder() {

     }
//    public static void updateProduct(Integer id, Product product) {
//


    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        Server s = new Server("7788");
        System.out.println(s.uri);

        s.getAllProducts();
        s.create("test2", (float) 3.3);
        Product t1 = new Product("test3", 4);
        


    }
}