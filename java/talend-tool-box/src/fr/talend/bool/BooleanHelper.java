package fr.talend.bool;

import java.util.Locale;

import fr.talend.string.StringHelper;

/**
 * BooleanHelper est la classe des outils de manipulation des objets de type
 * Boolean.
 * 
 * @author user
 * 
 */
public class BooleanHelper {

	/**
	 * Booléen par défaut
	 */
	private static final Boolean DEFAULT_BOOLEAN = false;

	/**
	 * Table de correspondance
	 */
	private static final BooleanMapping BOOLEAN_MAPPING = new BooleanMapping();

	/**
	 * Convertit l'objet spécifié en objet de type Boolean
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @return Valeur convertie
	 */
	private static Boolean parse(final Object value) {

		if (value == null) {
			return null;
		}

		final String strValue = StringHelper.tryParse(value).toUpperCase(Locale.FRANCE);		
		return BOOLEAN_MAPPING.containsKey(strValue) ? BOOLEAN_MAPPING.get(strValue) : null;
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type Boolean
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @return Valeur convertie
	 */
	public static Boolean tryParse(final Object value) {

		return tryParse(value, DEFAULT_BOOLEAN);
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type Boolean
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @param defaultValue
	 *            Valeur par défaut
	 * @return Valeur convertie ; Sinon valeur par défaut
	 */
	public static Boolean tryParse(final Object value, final Boolean defaultValue) {

		final Boolean result = parse(value);
		return result == null ? defaultValue : result;
	}

	/**
	 * Initialise une nouvelle instance privée de la classe BooleanHelper
	 */
	private BooleanHelper() {

	}
}