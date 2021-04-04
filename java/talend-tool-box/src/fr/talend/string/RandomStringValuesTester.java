package fr.talend.string;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import fr.talend.log.AppLog;

/**
 * RandomStringValuesTester est la classe de test d'obtention d'une valeur
 * aléatoire.
 * 
 * @author user
 * 
 */
public class RandomStringValuesTester extends TestCase {

	/**
	 * Helper logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Liste de valeurs
	 */
	private List<String> list;

	/**
	 * Tableau de valeurs
	 */
	private static final String[] VALUES = { "A", "B", "C", "D", "E" };

	/**
	 * Initialise le test unitaire
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		if (list == null) {
			list = new ArrayList<String>();
			for (String s : VALUES) {
				list.add(s);
			}
		}
	}

	/**
	 * Teste l'obtention d'une valeur aléatoire
	 */
	@Test
	public void testRandom() {

		String result = StringHelper.random(VALUES);
		log.info("Valeurs : %s - Trouvé : %s", list.toString(), result);
		assertTrue(list.contains(result));
	}
}
