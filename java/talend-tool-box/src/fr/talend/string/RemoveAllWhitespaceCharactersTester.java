package fr.talend.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.talend.log.AppLog;

import junit.framework.TestCase;

/**
 * RemoveAllWhitespaceCharactersTester représente la classe de test de nettoyage
 * des espaces contenus dans une chaine de caractères.
 * 
 * @author user
 * 
 */
public class RemoveAllWhitespaceCharactersTester extends TestCase {

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
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(String value, String expectedResult) {

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
		 * Obtient le résultatRemoveAllWhitespaceCharacters atte1ndu
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

			list.add(new TestSet(null, null));
			list.add(new TestSet("", ""));
			list.add(new TestSet(" ", ""));

			list.add(new TestSet("ABC", "ABC"));
			list.add(new TestSet(" DEF", "DEF"));
			list.add(new TestSet("GHI ", "GHI"));
			list.add(new TestSet(" JKL ", "JKL"));
			list.add(new TestSet(" M N O ", "MNO"));
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
	 * Teste le nettoyage des espaces contenus dans une chaine de caractères
	 */
	@Test
	public void testRemoveAllWhitespaceCharacters() {

		for (TestSet t : list) {

			String result = StringHelper.removeAllWhitespaceCharacters(t
					.getValue());
			log.debug("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}
