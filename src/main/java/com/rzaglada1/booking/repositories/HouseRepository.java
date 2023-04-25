package com.rzaglada1.booking.repositories;

import com.rzaglada1.booking.models.House;
import com.rzaglada1.booking.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long> {
    Page<House> getHouseByUser (User user, Pageable pageable);

    Page<House> findAll (Pageable pageable);




    @Query("SELECT DISTINCT h FROM House h " +
            "join Address a on h.id=a.house.id " +
            "left join OrderHistory o on h.id=o.house.id " +
            "WHERE " +
            "h.numTourists >= :numTourist" +
            " and a.country like concat('%', :country,'%')" +
            " and a.city like concat('%', :city,'%')" +
            " and h.id not in (" +
            "SELECT oo.house.id FROM OrderHistory oo WHERE not" +
            "(:endDateBooking < oo.dataBookingStart or  :startDateBooking >= oo.dataBookingEnd ))" +
            "")
    Page<House> getHouseByFilter (@Param("country") String country,
                                     @Param("city") String city,
                                     @Param("startDateBooking")LocalDate startDateBooking,
                                     @Param("endDateBooking")LocalDate endDateBooking,
                                     @Param("numTourist")int numTourist,
                                     Pageable pageable
    );


    @Query("SELECT h FROM House h " +
            "WHERE " +
            "h.id = :idHouse" +
            " and h.numTourists >= :numTourist" +
            " and h.id  in (" +
            "SELECT oo.house.id FROM OrderHistory oo WHERE not" +
            "(:endDateBooking < oo.dataBookingStart or  :startDateBooking >= oo.dataBookingEnd )) " +
            "")
    List<House> getHouseByDate (  @Param("startDateBooking")LocalDate startDateBooking,
                                  @Param("endDateBooking")LocalDate endDateBooking,
                                  @Param("numTourist")int numTourist,
                                  @Param("idHouse")long idHouse
    );

}
