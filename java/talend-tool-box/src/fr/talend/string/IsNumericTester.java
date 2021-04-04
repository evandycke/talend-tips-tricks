package fr.talend.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.talend.log.AppLog;

import junit.framework.TestCase;

/**
 * IsNumericTester représente la classe de test d'une chaine numérique ou
 * alphanumérique.
 * 
 * @author user
 * 
 */
public class IsNumericTester extends TestCase {

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
		private String value;

		/**
		 * Résultat attendu
		 */
		private boolean expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(String value, boolean expectedResult) {

			this.value = value;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient la valeur à tester
		 * 
		 * @return Valeur à tester
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Obtient le résultat atte1ndu
		 * 
		 * @return Résultat attendu
		 */
		public boolean getExpectedResult() {
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

			list.add(new TestSet(null, false));
			list.add(new TestSet("", false));
			list.add(new TestSet(" ", false));

			list.add(new TestSet("123456", true));
			list.add(new TestSet("ABC", false));
			list.add(new TestSet("1-2-3-4-5-6", true));
			list.add(new TestSet("ABC123", false));
			list.add(new TestSet("15.24", true));
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
	 * Teste une chaine numérique ou alphanumérique
	 */
	@Test
	public void testIsNumeric() {

		for (TestSet t : list) {

			boolean result = StringHelper.isNumeric(t.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
