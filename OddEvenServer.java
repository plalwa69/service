


import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class OddEvenServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/check", (HttpExchange exchange) -> {
            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery();
            String response = "Invalid Request";

            if (query != null && query.startsWith("number=")) {
                try {
                    int number = Integer.parseInt(query.split("=")[1]);
                    if (number % 2 == 0) {
                        response = number + " is Even";
                    } else {
                        response = number + " is Odd";
                    }
                } catch (NumberFormatException e) {
                    response = "Please provide valid number";
                }
            }

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Server started on port 8080");
    }
}

















//winget install Kubernetes.minikube
// javac filename.java
//minikube start
// docker login
// docker build -t username/imagename .
//docker push username/imagename

//kubectl apply -f deployment.yaml
//kubectl apply -f service.yaml
// kubectl get pods
//kubectl get services
// kubectl get nodes -o wide
//minukube service odd-even-service
