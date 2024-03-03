package controller.event;

import entities.Evenement;
import entities.user;
import services.ClientEventMappingService;
import services.SMSService;
import services.UserService;

import java.sql.SQLException;
import java.util.List;

public class EventService {
    private ClientEventMappingService clientEventMappingService;

    public EventService() {
        this.clientEventMappingService = new ClientEventMappingService();
    }

    // Method to populate events and set client IDs
    public List<Evenement> getAllEventsAndSetClientIds() throws SQLException {
        List<Evenement> events = getAllEvents(); // Fetch all events from the database

        for (Evenement event : events) {
            List<Integer> clientIds = null;
            try {
                clientIds = clientEventMappingService.getClientIdsForEvent(event.getId_evenement());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (!clientIds.isEmpty()) {
                int clientId = clientIds.get(0);
                event.setClientId(clientId);
            }
        }

        return events;
    }

    // Method to fetch all events from the database (you need to implement this)
    private List<Evenement> getAllEvents() {
        // Implement this method to fetch all events from your database or any other source
        return null;
    }
}
