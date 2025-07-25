package model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity 
@Table(name = "STUDENTATTENDANCE")
public class StudentAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_att_seq")
	@SequenceGenerator(name = "student_att_seq", sequenceName = "STUDENT_ATT_SEQ", allocationSize = 1)

    private Long id;

    private Long empno;
    private LocalDate trainDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    @Column(name = "OUTTINGSTARTTIME")
    private LocalDateTime outtingStartTime;
    private String status;
    private String name;
    
    

    @Builder
	public StudentAttendance(Long id, Long empno, LocalDate trainDate, LocalDateTime checkIn, LocalDateTime checkOut,
			LocalDateTime outtingStartTime, String status, String name) {
		this.id = id;
		this.empno = empno;
		this.trainDate = trainDate;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.outtingStartTime = outtingStartTime;
		this.status = status;
		this.name = name;
	}
}
