package fr.talend.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.talend.log.AppLog;

import junit.framework.TestCase;

/**
 * NormalizeTester est la classe de test de normalisation d'une chaine de
 * caractères.
 * 
 * @author evandycke
 * 
 */
public class NormalizeTester extends TestCase {

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
		public TestSet(final Object value, final String expectedResult) {
			this.value = value;
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
		 * Obtient la valeur
		 * 
		 * @return Valeur
		 */
		public Object getValue() {
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
//			this.list.add(new TestSet(null, ""));
//			this.list.add(new TestSet("ABC", "ABC"));
//			this.list.add(new TestSet("", ""));
//			this.list.add(new TestSet(1, "1"));
//			this.list.add(new TestSet("é", "e"));
		}
	}

	/**
	 * Finalise le test unitaire
	 */
	@After
	public void tearDown() {

		this.list = null;
	}

	/**
	 * Teste la normalisation d'une chaine de caractères
	 */
	@Test
	public void testNormalize() {

		for (TestSet t : this.list) {
			String result = StringHelper.normalize(t.getValue());
			log.info("Valeur : %s - Attendu : %s - Trouvé : %s", t.getValue(),
					t.getExpectedResult(), result);
			assertEquals(t.getExpectedResult(), result);
		}
	}
}