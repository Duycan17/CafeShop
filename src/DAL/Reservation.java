/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.Date;

/**
 *
 * @author duy
 */
public class Reservation {
    private int id;
    private int room_id;
    private Date reservation_date;
    private int number_of_guests;
    private String note;

    public Reservation(int id, int room_id, Date reservation_date, int number_of_guests, String note) {
        this.id = id;
        this.room_id = room_id;
        this.reservation_date = reservation_date;
        this.number_of_guests = number_of_guests;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return room_id;
    }

    public Date getReservationDate() {
        return reservation_date;
    }

    public int getNumberOfGuests() {
        return number_of_guests;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}