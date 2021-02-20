package babinvas.jpaintroduction;

import babinvas.jpaintroduction.model.Category;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {
	EntityManager entityManager;

	@BeforeAll
	public void init() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JavaRush");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	@Test
	public void shouldPersistCategory() {
		Category cat = new Category();
		cat.setTitle("new category");
		entityManager.persist(cat);
		Category result = entityManager.find(Category.class, 1L);
		Assertions.assertNotNull(result);
	}

	@AfterAll
	public void close() {
		if(entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().commit();
		}

		entityManager.getEntityManagerFactory().close();
		entityManager.close();
	}
}
