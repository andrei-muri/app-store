package repo;

import model.App;
import model.Categories;
import model.HomeData;
import model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class AppsRepo {
    public static void getAllApps() {
        String getAllAppsSql = "SELECT app_id, app_name, app_size_number, app_size_type, details, category_name, img FROM app a " +
                "JOIN categories c ON a.category_id = c.category_id";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(getAllAppsSql);
            ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()) {
                HomeData.allApps.add(new App(
                        rs.getInt("app_id"),
                        rs.getString("app_name"),
                        rs.getFloat("app_size_number"),
                        rs.getString("app_size_type"),
                        rs.getString("details"),
                        Categories.wrap(rs.getString("category_name")),
                        rs.getString("img")
                ));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error retrieving all apps from the database", e);
        }
    }

    public static void addDownloadedApp(int appId) {

        String insertUserSql = "INSERT INTO apps_installed (app_id, user_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertUserSql)) {

            pstmt.setInt(1, appId);
            pstmt.setInt(2, User.userId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding downloaded app failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding downloaded app to the database", e);
        }
    }

    public static void getDownloadedApps() {
        String getDownloadedAppsSql = "SELECT a.app_id as appId, app_name, app_size_number, app_size_type, details, category_name, img FROM " +
                "apps_installed ai JOIN app a ON ai.app_id = a.app_id JOIN categories c " +
                "ON a.category_id = c.category_id WHERE ai.user_id = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(getDownloadedAppsSql)) {

            pstmt.setInt(1, User.userId); // This line should be within the try block, before executing the query.

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                User.usersApps.add(new App(
                        rs.getInt("appId"),
                        rs.getString("app_name"),
                        rs.getFloat("app_size_number"),
                        rs.getString("app_size_type"),
                        rs.getString("details"),
                        Categories.wrap(rs.getString("category_name")),
                        rs.getString("img")
                ));
            }
        } catch(SQLException e) {
            throw new RuntimeException("Error retrieving all apps from the database", e);
        }
    }

    public static void uninstallApp(int appId) {
        String uninstallAppSql = "DELETE FROM apps_installed WHERE user_id = ? AND " +
                "app_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(uninstallAppSql)) {

            pstmt.setInt(1, User.userId);
            pstmt.setInt(2, appId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Uninstalling app failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error uninstalling app to the database", e);
        }
    }

    public static void addApp(String name, String description, Categories category, String appSizeType, float size, File imageFile) {
        String insertSql = "INSERT INTO app (app_name, details, category_id, app_size_type, app_size_number, img) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, description);
            // Assuming you have a method to convert category name to ID
            pstmt.setInt(3, category.ordinal() + 1);
            pstmt.setString(4, appSizeType);
            pstmt.setFloat(5, size);
            // You will need to handle the image file. This might involve storing the image itself
            // or just its path. Here's how you might store the path as a string.
            pstmt.setString(6, imageFile.getAbsolutePath());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting app failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new app to the database", e);
        }
    }

}
