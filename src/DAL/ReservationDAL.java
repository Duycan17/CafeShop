/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author duy
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDAL {

    private Connection connection;

    public ReservationDAL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cafeShop?characterEncoding=UTF-8";
            String username = "root";
            String password = "123";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            String query = "SELECT * FROM reservation";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int room_id = resultSet.getInt("room_id");
                Date reservation_date = resultSet.getDate("reservation_date");
                int number_of_guests = resultSet.getInt("number_of_guests");
                String note = resultSet.getString("note");
                reservations.add(new Reservation(id, room_id, reservation_date, number_of_guests, note));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public Reservation getReservationById(int reservationId) {
        try {
            String query = "SELECT * FROM reservation WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int room_id = resultSet.getInt("room_id");
                Date reservation_date = resultSet.getDate("reservation_date");
                int number_of_guests = resultSet.getInt("number_of_guests");
                String note = resultSet.getString("note");
                return new Reservation(reservationId, room_id, reservation_date, number_of_guests, note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addReservation(Reservation reservation) {
        try {
            String query = "INSERT INTO reservation (room_id, reservation_date, number_of_guests, note) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservation.getRoomId());
            statement.setDate(2, new java.sql.Date(reservation.getReservationDate().getTime()));
            statement.setInt(3, reservation.getNumberOfGuests());
            statement.setString(4, reservation.getNote());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReservation(Reservation reservation) {
        try {
            String query = "UPDATE reservation SET room_id = ?, reservation_date = ?, number_of_guests = ?, note = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservation.getRoomId());
            statement.setDate(2, new java.sql.Date(reservation.getReservationDate().getTime()));
            statement.setInt(3, reservation.getNumberOfGuests());
            statement.setString(4, reservation.getNote());
            statement.setInt(5, reservation.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservation(int reservationId) {
        try {
            String query = "DELETE FROM reservation WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reservationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
