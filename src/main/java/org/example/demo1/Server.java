package org.example.demo1;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 12345; // Cổng mà server sẽ lắng nghe
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("org.example.demo1.Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept(); // Chấp nhận kết nối từ client
                System.out.println("New client connected");

                // Tạo một luồng để xử lý client
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String message;
            while ((message = in.readLine()) != null) {
                // Giả sử message là một chuỗi JSON
                JsonObject jsonMessage = JsonParser.parseString(message).getAsJsonObject();
                String requestType = jsonMessage.get("type").getAsString();

                switch (requestType) {
                    case "login":
//                        handleLogin(jsonMessage.getJSONObject("data"), out);
                        break;
                    case "register":
//                        handleRegister(jsonMessage.getJSONObject("data"), out);
                        break;
                    case "dashboard":
//                        handleDashboard(out);
                        break;
                    case "play_game":
//                        handlePlayGame(jsonMessage.getJSONObject("data"), out);
                        break;
                    case "join_room":
//                        handleJoinRoom(jsonMessage.getJSONObject("data"), out);
                        break;
                    case "chat":
//                        handleChat(jsonMessage.getJSONObject("data"), out);
                        break;
                    default:
                        out.println("Unknown request type");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private void handleLogin(JSONObject data, PrintWriter out) {
//        // Xử lý đăng nhập
//    }
//
//    private void handleRegister(JSONObject data, PrintWriter out) {
//        // Xử lý đăng ký
//    }
//
//    private void handleDashboard(PrintWriter out) {
//        // Xử lý hiển thị dashboard
//    }
//
//    private void handlePlayGame(JSONObject data, PrintWriter out) {
//        // Xử lý chơi game
//    }
//
//    private void handleJoinRoom(JSONObject data, PrintWriter out) {
//        // Xử lý vào phòng
//    }
//
//    private void handleChat(JSONObject data, PrintWriter out) {
//        // Xử lý chat
//    }
}