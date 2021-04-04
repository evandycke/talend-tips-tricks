package fr.talend.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.talend.log.AppLog;

import junit.framework.TestCase;

/**
 * RepeatTester est la classe de test de répétition d'une chaine de caractères.
 * 
 * @author evandycke
 * 
 */
public class RepeatTester extends TestCase {

	/**
	 * TestSet est la classe d'un élément testé dans ce test unitaire.
	 * 
	 * @author evandycke
	 * 
	 */
	static class TestSet {

		/**
		 * Valeur
		 */
		private String value;

		/**
		 * Répétition
		 */
		private int repetition;

		/**
		 * Résultat attendu
		 */
		private String expectedResult;

		/**
		 * Initialise une nouvelle instance de la classe TestSet
		 * 
		 * @param value
		 *            Valeur
		 * @param repetition
		 *            Répétition
		 * @param expectedResult
		 *            Résultat attendu
		 */
		public TestSet(final String value, final int repetition,
				final String expectedResult) {
			this.value = value;
			this.repetition = repetition;
			this.expectedResult = expectedResult;
		}

		/**
		 * Obtient le résultat attendu
		 * 
		 * @return Résultat attendu
		 */
		public String getExpectedResult() {
			return expectedResult;
		}

		/**
		 * Obtient la répétition
		 * 
		 * @return Répétition
		 */
		public int getRepetition() {
			return repetition;
		}

		/**
		 * Obtient la valeur
		 * 
		 * @return Valeur
		 */
		public String getValue() {
			return value;
		}
	}

	/**
	 * Liste des éléments à tester
	 */
	private List<TestSet> list = new ArrayList<TestSet>();

	/**
	 * Helper logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Initialise le test unitaire
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		if (this.list.isEmpty()) {

			// Traitement des valeurs
			this.list.add(new TestSet(null, 5, ""));
			this.list.add(new TestSet("ABC", 3, "ABCABCABC"));
			this.list.add(new TestSet("", 3, ""));
			this.list.add(new TestSet(" DEF ", 2, " DEF  DEF "));
			this.list.add(new TestSet("  ", 3, ""));
			this.list.add(new TestSet("*", 10, "**********"));
		}
	}

	/**
	 * Teste la répétition d'une chaine de caractères
	 */
	@Test
	public void testRepeat() {

		for (TestSet t : this.list) {
			String result = StringHelper
					.repeat(t.getValue(), t.getRepetition());

			log.info(
					"Valeur : %s - Répétition : %s - Attendu : %s - Trouvé : %s",
					t.getValue(), t.getRepetition(), t.getExpectedResult(),
					result);

			assertEquals(t.getExpectedResult(), result);
		}
	}
}