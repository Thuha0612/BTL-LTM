package org.example.demo1.repository;

import org.example.demo1.entity.Users;
import org.example.demo1.enums.UserState;
import org.example.demo1.response.LoginResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/memory_card?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public LoginResponse checkLogin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Kiểm tra xem người dùng có tồn tại không
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    // So sánh mật khẩu
                    if (storedPassword.equals(password)) {
                        // Lấy thông tin người dùng từ kết quả truy vấn
                        Users user = new Users();
                        user.setId(resultSet.getLong("id")); // Lấy ID người dùng
                        user.setUsername(resultSet.getString("username"));
                        user.setPassword(storedPassword); // Chỉ nên lưu mật khẩu đã mã hóa
                        user.setAvatar(resultSet.getString("avatar"));
                        user.setName(resultSet.getString("name"));
                        user.setPoints(resultSet.getLong("points"));
                        user.setState(UserState.valueOf(resultSet.getString("state"))); // Nếu state là String
                        user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                        user.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());

                        // Trả về LoginResponse với success = true và đối tượng user
                        return new LoginResponse(true, user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Ném lại ngoại lệ để xử lý ở nơi gọi phương thức này
        }

        // Nếu không tìm thấy người dùng hoặc mật khẩu không đúng, trả về LoginResponse với success = false
        return new LoginResponse(false, null);
    }

    public boolean registerUser(Users user) throws SQLException {
        boolean checkUsername = checkUsernameExist(user.getUsername());
        if (!checkUsername) {
            return false;
        }

        return insertUser(user);
    }
    // Phương thức để chèn người dùng mới
    public boolean insertUser(Users user) {
        String sql = "INSERT INTO users (username, password, avatar, name, points, state, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getAvatar());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setLong(5, user.getPoints());
            preparedStatement.setString(6, user.getState().toString());
            preparedStatement.setObject(7, user.getCreatedAt()); // LocalDateTime
            preparedStatement.setObject(8, user.getUpdatedAt()); // LocalDateTime

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User inserted successfully!");
            }
            return rowsAffected != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUsernameExist(String username) {
        String checkUsernameSql = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(checkUsernameSql)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Kiểm tra nếu có bất kỳ bản ghi nào được trả về
                return resultSet.next(); // Nếu có bản ghi, nghĩa là username đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Trường hợp xảy ra lỗi hoặc không có kết quả trả về
    }

    public boolean changeStateUser(Long userId, UserState newState) throws SQLException {
        String sql = "UPDATE users SET state = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newState.toString());
            preparedStatement.setLong(2, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User state changed successfully!");
                return true;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Users> getAll() throws SQLException {
        List<Users> users = new ArrayList<>();
        String getAllSql = "select * from users";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(getAllSql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Users user = new Users();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setAvatar(resultSet.getString("avatar"));
                    user.setName(resultSet.getString("name"));
                    user.setPoints(resultSet.getLong("points"));
                    user.setState(UserState.valueOf(resultSet.getString("state")));
                    user.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                    user.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

