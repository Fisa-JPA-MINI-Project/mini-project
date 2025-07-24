package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity 
public class StudentAttendance {

    @Id
    private Long id;

    private Long empno;
    private LocalDate trainDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private LocalDateTime outtingStartTime;
    private String status;
    private String name;
}
