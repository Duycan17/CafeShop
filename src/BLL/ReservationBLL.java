/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

/**
 *
 * @author duy
 */
import DAL.Reservation;
import DAL.ReservationDAL;
import java.util.List;

public class ReservationBLL {
    private ReservationDAL reservationDAL;

    public ReservationBLL() {
        reservationDAL = new ReservationDAL();
    }

    public List<Reservation> getAllReservations() {
        return reservationDAL.getAllReservations();
    }

    public Reservation getReservationById(int reservationId) {
        return reservationDAL.getReservationById(reservationId);
    }

    public void addReservation(Reservation reservation) {
        reservationDAL.addReservation(reservation);
    }

    public void updateReservation(Reservation reservation) {
        reservationDAL.updateReservation(reservation);
    }

    public void deleteReservation(int reservationId) {
        reservationDAL.deleteReservation(reservationId);
    }
}