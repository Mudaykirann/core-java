import java.util.*;

// ===================== EXCEPTIONS =====================

class SeatUnavailableException extends Exception {
    public SeatUnavailableException(String msg) {
        super(msg);
    }
}

class CoachNotFoundException extends Exception {
    public CoachNotFoundException(String msg) {
        super(msg);
    }
}

class TicketNotFoundException extends Exception {
    public TicketNotFoundException(String msg) {
        super(msg);
    }
}

// ===================== SEAT =====================

class Seat {
    private int seatNumber;
    private boolean isBooked;
    private double price;

    Seat(int seatNumber, double price) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.isBooked = false;
    }

    public void reserve() throws SeatUnavailableException {
        if (isBooked)
            throw new SeatUnavailableException("Seat " + seatNumber + " already booked");
        isBooked = true;
    }

    public void unReserve() {
        isBooked = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public double getPrice() {
        return price;
    }
}

// ===================== COACH =====================

abstract class Coach {
    private String coachId;
    protected List<Seat> seats = new ArrayList<>();

    Coach(String coachId, int seatCount) {
        this.coachId = coachId;
        initializeSeats(seatCount);
    }

    abstract void initializeSeats(int count);
    abstract int getBasePrice();

    public String getCoachId() {
        return coachId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public int getAvailableSeatCount() {
        int count = 0;
        for (Seat s : seats)
            if (!s.isBooked()) count++;
        return count;
    }
}

class AcCoach extends Coach {
    AcCoach(String id, int seats) {
        super(id, seats);
    }

    void initializeSeats(int count) {
        for (int i = 1; i <= count; i++)
            seats.add(new Seat(i, getBasePrice()));
    }

    int getBasePrice() {
        return 1000;
    }
}

class SleeperCoach extends Coach {
    SleeperCoach(String id, int seats) {
        super(id, seats);
    }

    void initializeSeats(int count) {
        for (int i = 1; i <= count; i++)
            seats.add(new Seat(i, getBasePrice()));
    }

    int getBasePrice() {
        return 500;
    }
}

// ===================== TRAIN =====================

class Train {
    private String trainName;
    private int trainNumber;
    private String source;
    private String destination;
    private String time;
    private List<Coach> coaches = new ArrayList<>();

    Train(String trainName, int trainNumber, String source, String destination, String time) {
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.source = source;
        this.destination = destination;
        this.time = time;
    }

    public void addCoach(Coach c) {
        coaches.add(c);
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public String getTrainName() {
        return trainName;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}

// ===================== REGISTRY =====================

class TrainRegistry {
    private List<Train> trains = new ArrayList<>();

    public void addTrain(Train t) {
        trains.add(t);
    }

    public List<Train> getTrains() {
        return trains;
    }
}

// ===================== TICKET (GROUP BOOKING) =====================

class Ticket {
    private String pnr;
    private String bookedBy;
    private Train train;
    private String coachId;
    private List<Seat> seats;
    private double totalPrice;

    Ticket(String bookedBy, Train train, String coachId, List<Seat> seats) {
        this.pnr = String.valueOf(new Random().nextLong(1000000000L, 9999999999L));
        this.bookedBy = bookedBy;
        this.train = train;
        this.coachId = coachId;
        this.seats = seats;
        this.totalPrice = seats.size() * seats.get(0).getPrice();
    }

    public String getPNR() {
        return pnr;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void display() {
        System.out.println("\n========== GROUP TICKET ==========");
        System.out.println("PNR        : " + pnr);
        System.out.println("Booked By  : " + bookedBy);
        System.out.println("Train      : " + train.getTrainNumber() + " - " + train.getTrainName());
        System.out.println("Route      : " + train.getSource() + " → " + train.getDestination());
        System.out.println("Coach      : " + coachId);
        System.out.print("Seats      : ");
        for (Seat s : seats) System.out.print(s.getSeatNumber() + " ");
        System.out.println("\nTotal Fare : $" + totalPrice);
        System.out.println("=================================\n");
    }
}

// ===================== BOOKING MANAGER =====================

class BookingManager {
    private TrainRegistry registry;
    private Map<String, Ticket> tickets = new HashMap<>();

    BookingManager(TrainRegistry registry) {
        this.registry = registry;
    }

    public Train findTrain(int trainNo) {
        for (Train t : registry.getTrains())
            if (t.getTrainNumber() == trainNo) return t;
        return null;
    }

    // MULTI-SEAT BOOKING
    public Ticket bookMultipleSeats(
            Train train,
            String coachId,
            List<Integer> seatNumbers,
            String name
    ) throws CoachNotFoundException, SeatUnavailableException {

        Coach coach = null;
        for (Coach c : train.getCoaches()) {
            if (c.getCoachId().equalsIgnoreCase(coachId)) {
                coach = c;
                break;
            }
        }
        if (coach == null) throw new CoachNotFoundException("Coach not found");

        List<Seat> bookedSeats = new ArrayList<>();

        // validate first
        for (int sn : seatNumbers) {
            Seat seat = coach.getSeats()
                    .stream()
                    .filter(s -> s.getSeatNumber() == sn)
                    .findFirst()
                    .orElseThrow(() -> new SeatUnavailableException("Seat " + sn + " not found"));

            if (seat.isBooked())
                throw new SeatUnavailableException("Seat " + sn + " already booked");

            bookedSeats.add(seat);
        }

        // reserve all
        for (Seat s : bookedSeats) s.reserve();

        Ticket t = new Ticket(name, train, coachId, bookedSeats);
        tickets.put(t.getPNR(), t);
        return t;
    }

    public void cancelTicket(String pnr) throws TicketNotFoundException {
        Ticket t = tickets.get(pnr);
        if (t == null) throw new TicketNotFoundException("Invalid PNR");

        for (Seat s : t.getSeats()) s.unReserve();
        tickets.remove(pnr);
        System.out.println("Ticket " + pnr + " cancelled successfully");
    }

    public void displayTrainDetails(Train train) {
        System.out.println("\n===== TRAIN DETAILS =====");
        System.out.println("Train No   : " + train.getTrainNumber());
        System.out.println("Train Name : " + train.getTrainName());
        System.out.println("Route      : " + train.getSource() + " → " + train.getDestination());
        System.out.println("-------------------------");

        for (Coach c : train.getCoaches()) {
            System.out.println("Coach ID   : " + c.getCoachId());
            System.out.println("Type       : " + (c instanceof AcCoach ? "AC" : "Sleeper"));
            System.out.println("Available  : " + c.getAvailableSeatCount());
            System.out.println("Total      : " + c.getSeats().size());
            System.out.println("Price      : $" + c.getBasePrice());
            System.out.println("-------------------------");
        }
    }
}

// ===================== MAIN =====================

public class TrainTicketingSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TrainRegistry registry = new TrainRegistry();
        BookingManager manager = new BookingManager(registry);

        int[] nums = {12785, 12810, 12645, 12933, 12015, 12678};
        String[] names = {
                "Secunderabad Express", "Godavari Express", "Rayalaseema Express",
                "Howrah Mail", "Konark Express", "Shatabdi Express"
        };

        String[][] routes = {
                {"Tadipatri", "Hyderabad"},
                {"Visakhapatnam", "Hyderabad"},
                {"Kurnool", "Chennai"},
                {"Hyderabad", "Kolkata"},
                {"Mumbai", "Bhubaneswar"},
                {"Bengaluru", "Chennai"}
        };

        for (int i = 0; i < 6; i++) {
            Train t = new Train(names[i], nums[i], routes[i][0], routes[i][1], "06:30 AM");
            for (int j = 1; j <= 6; j++) t.addCoach(new AcCoach("AC-" + j, 72));
            for (int j = 1; j <= 6; j++) t.addCoach(new SleeperCoach("SL-" + j, 120));
            registry.addTrain(t);
        }

        while (true) {
            System.out.println("\n==== TRAIN TICKETING SYSTEM ====");
            System.out.println("1. View Trains");
            System.out.println("2. View Train Details");
            System.out.println("3. Book Multiple Tickets");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        for (Train t : registry.getTrains())
                            System.out.println(t.getTrainNumber() + " | " + t.getTrainName());
                        break;

                    case 2:
                        System.out.print("Enter Train Number: ");
                        Train tr = manager.findTrain(sc.nextInt());
                        if (tr != null) manager.displayTrainDetails(tr);
                        else System.out.println("Train not found");
                        break;

                    case 3:
                        System.out.print("Booked By: ");
                        String name = sc.nextLine();
                        System.out.print("Train Number: ");
                        Train train = manager.findTrain(sc.nextInt());
                        sc.nextLine();
                        if (train == null) break;

                        manager.displayTrainDetails(train);
                        System.out.print("Coach ID: ");
                        String cid = sc.nextLine();

                        System.out.print("Number of seats: ");
                        int n = sc.nextInt();

                        List<Integer> seats = new ArrayList<>();
                        for (int i = 1; i <= n; i++) {
                            System.out.print("Seat " + i + ": ");
                            seats.add(sc.nextInt());
                        }

                        Ticket ticket = manager.bookMultipleSeats(train, cid, seats, name);
                        ticket.display();
                        break;

                    case 4:
                        System.out.print("Enter PNR: ");
                        manager.cancelTicket(sc.nextLine());
                        break;

                    case 5:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
