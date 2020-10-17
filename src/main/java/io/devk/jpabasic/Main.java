package io.devk.jpabasic;

import java.util.List;

import javax.persistence.*;

public class Main {

	public static void main(String[] args) {
		
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
		
		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		// 트랜젝션 획득
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			
			// 트랜젝션 시작
			tx.begin();
			
			// 비지니스 로직 실행
			logic(em);
			
			// 트랜젝션 커밋
			tx.commit();
		} catch (Exception e) {
			
			// 트랜젝션 롤백
			tx.rollback();		
		} finally {
			
			// 엔티티 매니저 종료
			em.close();
		}
		
		// 엔티티 매니저 팩토리 종료
		emf.close();
	}
	
	/***
	 * 비지니스 로직
	 * @param em
	 */
	private static void logic(EntityManager em) {
		
		Long id = 1L;
		
		Member member = new Member();
		
		member.setId(id);
		member.setUsername("John");
		member.setAge(30);
		
		// 등록
		em.persist(member);
		
		// 수정
		member.setAge(20);
		
		// id로 조회
		Member foundMember = em.find(Member.class, id);
		
		System.out.println("foundMember=" + foundMember.getUsername() + ", age=" + foundMember.getAge());
		
		
		// 목록 조회
		List<Member> members = em.createQuery("select m from Member as m", Member.class)
//				.setFirstResult(1)
//				.setMaxResults(1)
				.getResultList();
		
		for(Member m : members) {
			System.out.println("============================");
			System.out.println("Id: " + m.getId());
			System.out.println("Username: " + m.getUsername());
			System.out.println("Age: " + m.getAge());
		}
		
		
		// 삭제
		em.remove(member);
		
	}

}
