import pojo.Client;
import pojo.Hotel;
import pojo.ReservationStatus;
import pojo.Room;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        Room room1 = new Room();


        System.out.println("UI for Administrator: Enter room details: ");
        System.out.println("how many apartments do you have?");
        System.out.print("Enter apartments number: ");
        int apartmentNumber = scanner.nextInt();


        System.out.println();

        // marking hotel apartments
        Map<Integer,Room>roomPriceAndId =new HashMap<>();
        for (int i = 1;i<=apartmentNumber; ++i){
            System.out.print("Enter price ($) for room Id: " + i + " " );
            int iD = i;
            double apartmentPrice = scanner.nextDouble();
            Room room = new Room(i, apartmentPrice);
            room.setStatus(ReservationStatus.AVAILABLE);
            roomPriceAndId.put(iD,room);
        }
   /*     for(Map.Entry<Integer,Room>ram : roomPriceAndId.entrySet()){
            int key = ram.getKey();
            Room r = ram.getValue();
            System.out.println(r.getRoomId());
        }*/



        //Sorting by id
        List<Room>roomsForHotel = new ArrayList<>(roomPriceAndId.values());
        Comparator<Room>sortByRoomId = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return Integer.compare(o1.getRoomId(), o2.getRoomId());
            }
        };
        Collections.sort(roomsForHotel, sortByRoomId);
        hotel.setRooms(roomsForHotel);

        // roomsForHotel.forEach(s-> System.out.println(s.getRoomId()));


        System.out.println();

        //Pagination
        System.out.println("Look at all room options at the hotel ");
        System.out.print("Enter rooms per page: ");
        int roomPerPage = scanner.nextInt();
        int totalRooms = roomsForHotel.size();
        int totalPages = (int)Math.ceil((double) totalRooms/roomPerPage);
        int currentPage = 1;
        boolean continueNavigation = true;

        while (continueNavigation) {
            int start = (currentPage-1) * roomPerPage;
            int end  = Math.min(start + roomPerPage, totalRooms);

            System.out.println("\n---Page " + currentPage + "---");

            hotel.sliceRoomsPerPage(start,end);

            System.out.println("\nPage " + currentPage + " of " + totalPages);
            System.out.print("Enter 'n' for next, 'p' for previous: ");
            String action = scanner.next();

            if (action.equalsIgnoreCase("n")){
                if(currentPage<=totalPages){
                    currentPage++;
                }else{
                    System.out.println("You are on the last page");
                }
            } else if (action.equalsIgnoreCase("p")) {
                if(currentPage>1){
                    currentPage--;
                }else{
                    System.out.println("you are on the first page");
                }
            }
            if (currentPage > totalPages && action.equalsIgnoreCase("n")) {
                continueNavigation = false;
            }
        }

        System.out.println();

        // Client info
        System.out.println("Client data:");
        System.out.print("Enter client Id: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Insert client name: ");
        String clientName = scanner.nextLine();

        Client client = new Client(clientId, clientName);
        client.setRoomList(room1);
        hotel.setClients(client);



        //Booking
        System.out.println("Booking Note!  Please insert your preferred roomId and your Id: ");
        System.out.print("roomId:");
        int roomNum = scanner.nextInt();
        System.out.print("yourId: ");
        int yourId = scanner.nextInt();

        hotel.bookForClient(roomNum,yourId);
        System.out.println();
        System.out.println("-------------------------------------------");

        //cancelling
        System.out.println("In order to cancel your reservation please enter roomId and your Id: ");
        System.out.print("roomId: ");
        int roomNumCancel = scanner.nextInt();
        System.out.print("yourId: ");
        int yourIdCancel = scanner.nextInt();

        hotel.cancelRoomForClient(roomNumCancel,yourIdCancel);

        scanner.close();
    }
}