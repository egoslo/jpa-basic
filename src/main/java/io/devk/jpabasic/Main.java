package io.devk.jpabasic;

import java.util.List;

import javax.persistence.*;

public class Main {

	public static void main(String[] args) {
		
		// ��ƼƼ �Ŵ��� ���丮 ����
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hellojpa");
		
		// ��ƼƼ �Ŵ��� ����
		EntityManager em = emf.createEntityManager();
		
		// Ʈ������ ȹ��
		
		EntityTransaction tx = em.getTransaction();
		
		try {
			
			// Ʈ������ ����
			tx.begin();
			
			// �����Ͻ� ���� ����
			logic(em);
			
			// Ʈ������ Ŀ��
			tx.commit();
		} catch (Exception e) {
			
			// Ʈ������ �ѹ�
			tx.rollback();		
		} finally {
			
			// ��ƼƼ �Ŵ��� ����
			em.close();
		}
		
		// ��ƼƼ �Ŵ��� ���丮 ����
		emf.close();
	}
	
	/***
	 * �����Ͻ� ����
	 * @param em
	 */
	private static void logic(EntityManager em) {
		
		Long id = 1L;
		
		Member member = new Member();
		
		member.setId(id);
		member.setUsername("John");
		member.setAge(30);
		
		// ���
		em.persist(member);
		
		// ����
		member.setAge(20);
		
		// id�� ��ȸ
		Member foundMember = em.find(Member.class, id);
		
		System.out.println("foundMember=" + foundMember.getUsername() + ", age=" + foundMember.getAge());
		
		
		// ��� ��ȸ
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
		
		
		// ����
		em.remove(member);
		
	}

}
