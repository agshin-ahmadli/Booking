package pojo;

public class Room {
    private int roomId;
    private double roomPrice;
    private ReservationStatus status;

    public Room(int roomId, double roomPrice) {
        this.roomId = roomId;
        this.roomPrice = roomPrice;
        status = ReservationStatus.AVAILABLE;
    }
    public Room() {
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getRoomId() {
        return roomId;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
