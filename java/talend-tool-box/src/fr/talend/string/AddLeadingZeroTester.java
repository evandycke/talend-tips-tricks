package fr.talend.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.talend.log.AppLog;
import junit.framework.TestCase;

/**
 * AddLeadingZeroTester représente la classe de test d'obtention d'une valeur
 * avec ses zéros d'entête.
 * 
 * @author user
 * 
 */
public class AddLeadingZeroTester extends TestCase {

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
		 * Taille
		 */
		private int size;

		/**
		 * Résultat attendu
		 */
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param size
		 *            Taille
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(Object value, int size, String expectedResult) {

			this.value = value;
			this.size = size;
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
		 * Obtient la taille
		 * 
		 * @return Taille
		 */
		public int getSize() {
			return size;
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

			list.add(new TestSet(null, 3, "000"));
			list.add(new TestSet("", 3, "000"));
			list.add(new TestSet(" ", 3, "000"));

			list.add(new TestSet(1, 3, "001"));
			list.add(new TestSet(2, 4, "0002"));
			list.add(new TestSet(3, 5, "00003"));

			list.add(new TestSet("ABC", 3, "000"));

			// Exemples avec des n° de magasin NEWCAT (ES)
			list.add(new TestSet("0184", 10, "0000000184"));
			list.add(new TestSet("0189", 10, "0000000189"));
			list.add(new TestSet("1202", 10, "0000001202"));
			list.add(new TestSet("1207", 10, "0000001207"));
			list.add(new TestSet("1214", 10, "0000001214"));
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
	 * Teste l'ajout des zéros d'entête
	 */
	@Test
	public void testAddLeadingZero() {

		for (TestSet t : list) {

			String result = StringHelper.addLeadingZero(t.getSize(),
					t.getValue());
			log.debug("Valeur : %s - Taille : %s - Attendu : %s - Trouvé : %s",
					t.getValue(), t.getSize(), t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
