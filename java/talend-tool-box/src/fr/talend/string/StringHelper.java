package fr.talend.string;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.talend.numeric.IntegerHelper;

/**
 * StringHelper est la classe des outils de manipulation des objets de type
 * String.
 * 
 * @author evandycke
 * aa
 */
public final class StringHelper {

	/**
	 * Chaine vide
	 */
	private static final String CHAINE_VIDE = "";

	/**
	 * Norme UNICODE par défaut
	 */
	private static final Form DEFAULT_FORM = Form.NFD;

	/**
	 * Ajoute les zéros d'entête à la valeur spécifiée
	 * 
	 * @param size
	 *            Taille de la chaine retournée
	 * @param value
	 *            Valeur à convertir
	 * @return Valeur convertie
	 */
	public static String addLeadingZero(final int size, final Object value) {

		final int intValue = IntegerHelper.tryParse(value);
		return String.format(getLeadingZeroFormat(size), intValue);
	}

	/**
	 * Obtient le format des zéros d'entête en fonction de la taille spécifiée
	 * 
	 * @param size
	 *            Taille de la chaine
	 * @return Format
	 */
	private static String getLeadingZeroFormat(final int size) {

		final StringBuilder strBld = new StringBuilder(3);
		return strBld.append("%0").append(size).append("d").toString();
	}

	/**
	 * Détermine si la chaine spécifiée est nulle, vide, ou pleine d'espaces
	 * 
	 * @param value
	 *            Valeur à tester
	 * @return Vrai si la valeur est nulle, vide ou pleine d'espaces ; Sinon
	 *         Faux
	 */
	public static boolean isNullOrEmptyOrSpace(final String value) {

		return value == null || CHAINE_VIDE.equalsIgnoreCase(value.trim());
	}

	/**
	 * Retourne Vrai si la chaîne de caractères est numérique
	 * 
	 * @param value
	 *            Chaîne de caractères
	 * @return Vrai si la chaîne de caractères est numérique ; Sinon Faux
	 */
	public static boolean isNumeric(final String value) {

		if (isNullOrEmptyOrSpace(value)) {
			return false;
		}

		final Pattern motif = Pattern.compile("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");
		final Matcher correspondance = motif.matcher(value);
		return correspondance.matches();
	}

	/**
	 * Normalise la valeur spécifiée
	 * 
	 * @param value
	 *            Valeur à normaliser
	 * @return Valeur normalisée
	 */
	public static String normalize(final Object value) {

		return normalize(value, DEFAULT_FORM);
	}

	/**
	 * Normalise la valeur spécifiée en fonction de la norme indiquée
	 * 
	 * @param value
	 *            Valeur à normaliser
	 * @param form
	 *            Norme à utiliser
	 * @return Valeur normalisée
	 */
	public static String normalize(final Object value, Form form) {

		String strValue = tryParse(value);
		return Normalizer.normalize(strValue, form == null ? DEFAULT_FORM
				: form);
	}

	/**
	 * Retourne une valeur aléatoire, comprise dans la liste des valeurs
	 * spécifiées
	 * 
	 * @param values
	 *            Valeurs
	 * @return Valeur aléatoire
	 */
	public static String random(final String... values) {

		String result = CHAINE_VIDE;

		if (values != null) {

			final List<String> valueList = new ArrayList<String>();
			for (final String s : values) {
				valueList.add(s);
			}

			final Random randomGenerator = new Random();
			final int index = randomGenerator.nextInt(valueList.size());
			result = valueList.get(index);
		}

		return result;
	}

	/**
	 * Retire tous les espaces de la chaine spécifiée
	 * 
	 * @param value
	 *            Valeur à nettoyer
	 * @return Valeur nettoyée
	 */
	public static String removeAllWhitespaceCharacters(final String value) {

		// Si la valeur est nulle, on retourne null sinon on retourne le
		// résultat du nettoyage
		return null == value ? value : value.replaceAll("\\s", "");
	}

	/**
	 * Répète une chaine de caractères autant de fois que voulu et retourne le
	 * résultat
	 * 
	 * @param value
	 *            Chaine de caractères à répèter
	 * @param nbRepetition
	 *            Nombre de répètition
	 * @return Résultat
	 */
	public static String repeat(final String value, final int nbRepetition) {

		if (isNullOrEmptyOrSpace(value)) {
			return CHAINE_VIDE;
		}

		final StringBuilder builder = new StringBuilder();

		for (int i = 0; i < nbRepetition; i++) {
			builder.append(value);
		}

		return builder.toString();
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type String
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @return Valeur convertie
	 */
	public static String tryParse(final Object value) {

		return tryParse(value, CHAINE_VIDE);
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type String
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @param defaultValue
	 *            Valeur par défaut
	 * @return Valeur convertie ; Sinon valeur par défaut
	 */
	public static String tryParse(final Object value, final String defaultValue) {

		final String strResult = String.valueOf(value);
		return "null".equalsIgnoreCase(strResult) ? defaultValue : strResult;
	}

	/**
	 * Initialise une nouvelle instance privée de la classe StringHelper
	 */
	private StringHelper() {

	}
}