package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity 
@Table(name = "STUDENTATTENDANCE")
public class StudentAttendance {

    @Id
    private Long id;

    private Long empno;
    private LocalDate trainDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    @Column(name = "OUTTINGSTARTTIME")
    private LocalDateTime outtingStartTime;
    private String status;
    private String name;
}
