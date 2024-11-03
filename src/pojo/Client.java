package pojo;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int clientId;
    private String name;
    private List<Room> roomList;

    public Client(int clientId, String name){
        this.clientId = clientId;
        this.name = name;
        this.roomList = new ArrayList<>();
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomList(Room room) {
        this.roomList.add(room);
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void bookRoom(Room room){
        if (room.getStatus() == ReservationStatus.AVAILABLE){
            room.setStatus(ReservationStatus.RESERVED);
            System.out.println("Room " + room.getRoomId() + " has been booked by client id " + clientId );
        }else {
            System.out.println("Room " + room.getRoomId() + " is already reserved");
        }
    }

    public void cancelBook(Room room){
        if(roomList.contains(room) && room.getStatus() == ReservationStatus.RESERVED){
            room.setStatus(ReservationStatus.AVAILABLE);
            roomList.remove(room);
            System.out.println("Booking for room " +room.getRoomId() + " has been cancelled by " + name);
        }else {
            System.out.println("Room " + room.getRoomId() + " is not booked by " + name);
        }
    }
}
