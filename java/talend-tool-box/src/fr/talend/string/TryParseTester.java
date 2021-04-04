package fr.talend.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.talend.log.AppLog;

import junit.framework.TestCase;

/**
 * TryParseTester représente la classe de test de conversion d'un objet en type
 * Integer.
 * 
 * @author user
 * 
 */
public class TryParseTester extends TestCase {

	/**
	 * TestSet représente un élément à tester.
	 * 
	 * @author user
	 * 
	 */
	public class TestSet {

		/**
		 * Valeur
		 */
		private Object value;

		/**
		 * Résultat attendu
		 */
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Object value, String expectedResult) {

			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur à tester
		 * 
		 * @return Valeur à tester
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * Obtient le résultat atte1ndu
		 * 
		 * @return Résultat attendu
		 */
		public String getExpectedResult() {
			return expectedResult;
		}
	}

	/**
	 * Helper de logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Liste des éléments à tester
	 */
	private List<TestSet> list = new ArrayList<TestSet>();

	/**
	 * Initialise le test unitaire
	 */
	@Before
	public void setUp() {

		if (list.isEmpty()) {

			list.add(new TestSet(null, ""));
			list.add(new TestSet("ABC", "ABC"));
			list.add(new TestSet(1, "1"));
			list.add(new TestSet("2", "2"));
			list.add(new TestSet(Long.valueOf(3l), "3"));
		}
	}

	/**
	 * Finalise le test unitaire
	 */
	@After
	public void tearDown() {

		list = null;
	}

	/**
	 * Teste la conversion d'un objet en type String
	 */
	@Test
	public void testTryParse() {

		for (TestSet t : list) {

			String result = StringHelper.tryParse(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(), t.getExpectedResult(),
					result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
