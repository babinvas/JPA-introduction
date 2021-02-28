package babinvas.jpaintroduction;

import babinvas.jpaintroduction.model.Category;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AppTest {
	EntityManager entityManager;
	EntityTransaction entityTransaction;

	@BeforeEach
	public void init() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Babinvas");
		entityManager = entityManagerFactory.createEntityManager();

		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	}

	@Test
	public void shouldPersistCategory() {
		Category cat = new Category();
		cat.setTitle("new category");

		entityManager.persist(cat);

		Category result = entityManager.find(Category.class, 1L);

		Assertions.assertNotNull(result);
	}

	@AfterEach
	public void close() {
		if(entityTransaction.isActive()) {
			entityTransaction.commit();
		}

		entityManager.getEntityManagerFactory().close();
		entityManager.close();
	}
}
