package fr.talend.numeric;

import java.math.BigDecimal;

import fr.talend.string.StringHelper;

/**
 * FloatHelper est la classe des outils de manipulation des objets de type
 * Float.
 * 
 * @author user
 * 
 */
public final class FloatHelper {

	/**
	 * Entier par défaut
	 */
	private static final Float DEFAULT_FLOAT = 0f;

	/**
	 * Détermine si l'objet spécifié est nul ou vaut zéro
	 * 
	 * @param value
	 *            Valeur à tester
	 * @return Vrai si l'objet spécifié est nul ou vaut zéro ; Sinon Faux
	 */
	public static boolean isNullOrZero(final Object value) {

		final Float floatValue = tryParse(value);
		return floatValue == null || DEFAULT_FLOAT.equals(floatValue);
	}

	/**
	 * Convertit l'objet spécifié en objet de type Float
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @return Valeur convertie
	 */
	private static Float parse(final Object value) {

		if (value == null) {
			return null;
		}

		Float returnValue;

		if (value instanceof String) {
			final String strVal = ((String) value).trim();
			if ("".equals(strVal)) {
				return null;
			} else {
				// Remplacement de "," par "." sinon, on obtiendra zéro
				String s = strVal.replace(',', '.');

				// Remplacement de " " par "" sinon on obtiendra zéro
				s = StringHelper.removeAllWhitespaceCharacters(s);
				returnValue = Float.valueOf(s);
			}
		} else if (value instanceof Integer) {
			returnValue = ((Integer) value).floatValue();
		} else if (value instanceof Double) {
			returnValue = ((Double) value).floatValue();
		} else if (value instanceof BigDecimal) {
			returnValue = ((BigDecimal) value).floatValue();
		} else if (value instanceof Long) {
			returnValue = ((Long) value).floatValue();
		} else if (value instanceof Float) {
			returnValue = (Float) value;
		} else {
			returnValue = Float.valueOf(0);
		}

		return returnValue;
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type Float
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @return Valeur convertie
	 */
	public static Float tryParse(final Object value) {

		return tryParse(value, DEFAULT_FLOAT);
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type Float
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @param defaultValue
	 *            Valeur par défaut
	 * @return Valeur convertie ; Sinon valeur par défaut
	 */
	public static Float tryParse(final Object value, final Float defaultValue) {

		Float result = defaultValue;

		try {
			result = parse(value);
		} catch (NumberFormatException e) {
			result = defaultValue;
		}

		return result == null ? defaultValue : result;
	}

	/**
	 * Initialise une nouvelle instance privée de la classe FloatHelper
	 */
	private FloatHelper() {

	}
}
