package services;

import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventClientMappingService {
    private Connection con;

    public EventClientMappingService() {
        try {
            con = MyDB.getInstance().getConnection();
            if (con == null) {
                throw new SQLException("Failed to obtain database connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., log the error and exit gracefully
        }
    }

    public List<Integer> getClientIdsForEvent(int eventId, int offset, int limit) {
        List<Integer> clientIds = new ArrayList<>();
        String query = "SELECT client_id FROM Event_Client_Mapping WHERE event_id = ? LIMIT ? OFFSET ?";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, eventId);
            statement.setInt(2, limit);
            statement.setInt(3, offset);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int clientId = resultSet.getInt("client_id");
                clientIds.add(clientId);
            }

            // Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return clientIds;
    }
}
