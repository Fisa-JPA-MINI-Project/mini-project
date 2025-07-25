package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.entity.StudentAttendance;
import util.DBUtil;

public class MemberDAO {


	private static EntityManager em = DBUtil.getEntityManager();
	
	private static MemberDAO model = new MemberDAO();
	
	private MemberDAO() {} 
	
	public static MemberDAO getModel() {
		return model;
	}
	
	// 내 출석 시간 보기
	public List<StudentAttendance> getMyAttendance() {
	    List<StudentAttendance> list = null;
	    try {
	        list = em.createQuery(
	                "SELECT s FROM StudentAttendance s WHERE s.name = :name",
	                StudentAttendance.class)
	            .setParameter("name", "홍혜원")
	            .getResultList();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return list;
	}

	public StudentAttendance getStudent(Long id) {
		return null;
	}
}
