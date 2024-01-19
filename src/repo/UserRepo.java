package repo;
import model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserRepo {
    public static boolean isUsernameTaken(String username) {
        String checkUserSql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(checkUserSql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if username is taken", e);
        }
        return false;
    }

    public static void addUser(String username, String password, boolean isAdmin) {
        String hashPassword = hashPassword(password);

        String insertUserSql = "INSERT INTO users (username, password, isAdmin) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertUserSql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword);
            pstmt.setBoolean(3, isAdmin);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding new user to the database", e);
        }
    }

    public static boolean checkCredentials(String username, String password) {
        String userPasswordHash = getPasswordHashForUser(username);

        if (userPasswordHash == null) {
            return false;
        }

        String loginPasswordHash = hashPassword(password);
        return userPasswordHash.equals(loginPasswordHash);
    }

    private static String getPasswordHashForUser(String username) {
        String getPasswordSql = "SELECT user_id, password, isAdmin FROM users WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(getPasswordSql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User.userId = rs.getInt("user_id");
                    User.isAdmin = rs.getBoolean("isAdmin");
                    return rs.getString("password");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user password from the database", e);
        }
        return null;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean checkPassword(String oldPassword) {
        String getPasswordSql = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(getPasswordSql)) {

            pstmt.setString(1, User.username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    String hashedOldPassword = hashPassword(oldPassword);
                    return storedPassword.equals(hashedOldPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean changePassword(String newPassword) {
        String updatePasswordSql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updatePasswordSql)) {

            String hashedNewPassword = hashPassword(newPassword);
            pstmt.setString(1, hashedNewPassword);
            pstmt.setString(2, User.username);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
