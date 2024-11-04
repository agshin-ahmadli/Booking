package pojo;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String hotelLocation;
    private List<Client> clients;
    private List<Room> rooms;

    public Hotel() {
        this.hotelLocation = "Baku, Azerbaijan Nizami street 88";
        this.clients = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public void setClients(Client client) {
        this.clients.add(client);
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room findRoomById(int roomId) {
        return rooms.stream().filter(room -> room.getRoomId() == roomId)
                .findFirst().orElseThrow(() -> new RuntimeException("Room with Id " + roomId + " not found"));
    }

    public Client findClientById(int clientId) {
        return clients.stream().filter(client -> client.getClientId() == clientId)
                .findFirst().orElseThrow(() -> new RuntimeException("Client with Id " + clientId + " not found"));
    }

    public void bookForClient(int roomId, int clientId) {
        Room room = findRoomById(roomId);
        Client client = findClientById(clientId);
        if (room != null && client != null) {
            client.bookRoom(room);
        } else {
            System.out.println("Room not found");
        }
    }

    public void cancelRoomForClient(int roomId, int clientId) {
        Room room = findRoomById(roomId);
        Client client = findClientById(clientId);
        if (room != null && client != null) {
            client.cancelBook(room);
        } else {
            System.out.println("Client or Room not found.");
        }
    }

    public void sliceRoomsPerPage(int start, int end) {
        end = Math.min(end, rooms.size());
        for (int i = start; i < end; ++i) {
            //Room room = rooms.get(i);
            System.out.println("Room ID: " + rooms.get(i).getRoomId() + ", Status: " + rooms.get(i).getStatus()
                                + ", Price: " + rooms.get(i).getRoomPrice());
        }
    }
}

