package fr.talend.bool;

import java.util.HashMap;

/**
 * BooleanMapping est la classe de la table de correspondance de valeurs
 * booléennes.
 * 
 * @author evandycke
 *
 */
public class BooleanMapping extends HashMap<String, Boolean> {

	/**
	 * Numéro de version
	 */
	private static final long serialVersionUID = 7020217934034441944L;

	/**
	 * Initialise une nouvelle instance de la classe BooleanMapping
	 */
	public BooleanMapping() {
		
		super();

		this.put("O", true);
		this.put("N", false);
	}
}
