package fr.talend.numeric;

import java.math.BigDecimal;
import java.math.RoundingMode;

import fr.talend.string.StringHelper;

/**
 * BigDecimalHelper est la classe des outils de manipulation des objets de type
 * BigDecimal.
 * 
 * @author user
 * 
 */
public final class BigDecimalHelper {

	/**
	 * Détermine si la valeur spécifiée est égale à zéro (quelque soit la
	 * précision)
	 * 
	 * @param value
	 *            Valeur à tester
	 * @return Vrai si la valeur est égale à zéro ; Sinon Faux
	 */
	public static boolean equalsZero(final Object value) {

		final BigDecimal bdValue = tryParseToBigDecimal(value, null);
		return bdValue == null ? false
				: BigDecimal.ZERO.compareTo(bdValue) == 0;
	}

	/**
	 * Détermine si la valeur spécifiée est différente de zéro (quelque soit la
	 * précision)
	 * 
	 * @param value
	 *            Valeur à tester
	 * @return Vrai si la valeur est différente de zéro ; Sinon Faux
	 */
	public static boolean notEqualsZero(final Object value) {

		return !equalsZero(value);
	}

	/**
	 * Convertit un objet en BigDecimal
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @return Résultat de la conversion
	 */
	public static BigDecimal parseToBigDecimal(final Object value) {

		return parseToBigDecimal(value, null);
	}

	/**
	 * Convertit un objet en BigDecimal
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @param nbrDecimal
	 *            Nombre de décimales
	 * @return Résultat de la conversion
	 */
	public static BigDecimal parseToBigDecimal(final Object value,
			final Integer nbrDecimal) {

		if (value == null) {
			return null;
		}

		BigDecimal returnValue;

		if (value instanceof String) {
			final String strVal = ((String) value).trim();
			if ("".equals(strVal)) {
				return null;
			} else {
				// Remplacement de "," par "." sinon on obtiendra zéro
				String s = strVal.replace(',', '.');

				// Remplacement de " " par "" sinon on obtiendra zéro
				s = StringHelper.removeAllWhitespaceCharacters(s);
				// on vérifie qu'il s'agisse bien d'une valeur numérique
				if (StringHelper.isNumeric(s)) {
					returnValue = new BigDecimal(s);
				} else {
					return null;
				}
			}
		} else if (value instanceof Integer) {
			returnValue = new BigDecimal((Integer) value);
		} else if (value instanceof Double) {
			returnValue = BigDecimal.valueOf((Double) value);
		} else if (value instanceof Long) {
			returnValue = new BigDecimal((Long) value);
		} else if (value instanceof BigDecimal) {
			returnValue = (BigDecimal) value;
		} else if (value instanceof Float) {
			returnValue = new BigDecimal(String.valueOf(value));
		} else {
			returnValue = BigDecimal.ZERO;
		}

		return nbrDecimal == null ? returnValue
				: round(returnValue, nbrDecimal);
	}

	/**
	 * Arrondit le BigDecimal au nombre de décimales indiqué
	 * 
	 * @param value
	 *            Valeur à arrondir
	 * @param nbDecimal
	 *            Nombre de décimales
	 * @return Résultat de l'arrondi
	 */
	public static BigDecimal round(final BigDecimal value, final int nbDecimal) {

		if (value == null) {
			return null;
		}

		return value.setScale(nbDecimal, RoundingMode.HALF_UP);
	}

	/**
	 * Convertit un objet en BigDecimal
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @return Résultat de la conversion
	 */
	public static BigDecimal tryParseToBigDecimal(final Object value) {

		return tryParseToBigDecimal(value, BigDecimal.ZERO);
	}

	/**
	 * Convertit un objet en BigDecimal et renvoit la valeur par défaut en cas
	 * de problème de conversion
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @param defaultValue
	 *            Valeur par défaut
	 * @return Résultat de la conversion
	 */
	public static BigDecimal tryParseToBigDecimal(final Object value,
			final BigDecimal defaultValue) {

		BigDecimal retour = parseToBigDecimal(value);
		if (retour == null) {
			retour = defaultValue;
		}
		return retour;
	}

	/**
	 * Initialise une nouvelle instance privée de la classe BigDecimalHelper
	 */
	private BigDecimalHelper() {

	}
}
