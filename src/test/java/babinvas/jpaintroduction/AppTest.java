package babinvas.jpaintroduction;

import babinvas.jpaintroduction.model.Category;
import babinvas.jpaintroduction.model.Topic;
import org.junit.jupiter.api.*;

import javax.persistence.*;

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
		cat.setTitle("test");

		entityManager.persist(cat);
	}

	@Test
	public void shouldFindCategory() {
		Category cat = new Category();
		cat.setTitle("test");

		entityManager.persist(cat);

		Category result = entityManager.find(Category.class, 1L);

		Assertions.assertNotNull(result);
	}

	@Test
	public void shouldPersistCategoryAndTopics() {
		Category cat = new Category();
		cat.setTitle("test");

		Topic topic = new Topic();
		topic.setTitle("topic");
		topic.setCategory(cat);

		entityManager.persist(cat);
	}

	@Test
	public void shouldPerformQuery() {
		Category cat = new Category();
		cat.setTitle("query");

		entityManager.persist(cat);

		Query query = entityManager.createQuery("SELECT c from Category c WHERE c.title = 'query'");
		Assertions.assertNotNull(query.getSingleResult());
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
