package services;

import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class ClientEventMappingService {
    private Connection con;

    // Implement a method to retrieve client IDs associated with a given event ID
    public List<Integer> getClientIdsForEvent(int eventId) throws SQLException {
        List<Integer> clientIds = new ArrayList<>();
        String req = "SELECT client_id FROM Event_Client_Mapping WHERE event_id = ?";
        PreparedStatement ste = con.prepareStatement(req);
        try {
            con = MyDB.getInstance().getConnection();
            if (con == null) {
                throw new SQLException("Failed to obtain database connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., log the error and exit gracefully
        }

            ste.setInt(1, eventId);
            ResultSet resultSet = ste.executeQuery();

            while (resultSet.next()) {
                int clientId = resultSet.getInt("client_id");
                clientIds.add(clientId);
            }
        return clientIds;
    }


    }
