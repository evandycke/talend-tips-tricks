package fr.talend.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import fr.talend.string.StringHelper;

/**
 * FileHelper est la classe des outils de manipulation de fichiers.
 * 
 * @author elie.vandycke
 * 
 */
public final class FileHelper {

	/**
	 * Chaine vide
	 */
	private static final String EMPTY_STRING = "";

	/**
	 * Séparateur d'un suffixe
	 */
	private static final char SEP_SUFFIXE = '.';

	/**
	 * Détermine si le nom de fichier spécifié correspond au format attendu
	 * 
	 * @param filename
	 *            Nom du fichier à tester
	 * @param format
	 *            Format attendu
	 * @return Vrai si le fichier est valide ; Sinon Faux
	 */
	public static boolean isValidName(String filename, String format) {

		boolean result = false;

		try {
			if (!StringHelper.isNullOrEmptyOrSpace(format) && filename != null) {
				Pattern pattern = Pattern.compile(format);
				Matcher matcher = pattern.matcher(filename);
				while (matcher.find()) {
					result = true;
				}
			}
		} catch (PatternSyntaxException e) {
			result = false;
		}

		return result;
	}

	/**
	 * Normalise l'extension spécifiée
	 * 
	 * @param extension
	 *            Extension à normaliser
	 * @return Extension normalisée
	 */
	public static String normalizeExtension(final String extension) {

		String result = null;

		if (StringHelper.isNullOrEmptyOrSpace(extension)) {
			// L'extension est nulle, vide ou pleine d'espaces, on retoure une
			// chaine vide
			result = EMPTY_STRING;
		} else if (extension.charAt(0) == SEP_SUFFIXE) {
			// L'extension est correcte, on la retourne telle qu'elle est
			result = extension;
		} else {
			// L'extension ne commence pas par un point, on corrige
			final StringBuilder strBld = new StringBuilder(2);
			result = strBld.append(SEP_SUFFIXE).append(extension).toString();
		}

		return result;
	}

	/**
	 * Retire l'extension actuelle d'un fichier
	 * 
	 * @param value
	 *            Valeur (Nom du fichier ou chemin complet du fichier)
	 * @param extension
	 *            Extension
	 * @return Fichier sans l'extension
	 */
	public static String removeExtension(final String value,
			final String extension) {

		// Rien n'a été spécifié concernant la valeur, on retourne une chaine
		// vide
		if (StringHelper.isNullOrEmptyOrSpace(value)) {
			return EMPTY_STRING;
		}

		// L'extension n'a pas été précisée, on retourne la valeur
		if (StringHelper.isNullOrEmptyOrSpace(extension)) {
			return value;
		}

		final String ext = normalizeExtension(extension);

		return value.substring(0, value.length() - ext.length());
	}

	/**
	 * Remplace l'extension actuelle d'un fichier par une nouvelle extension
	 * 
	 * @param value
	 *            Valeur (Nom du fichier ou chemin complet du fichier)
	 * @param oldExtension
	 *            Ancienne extension
	 * @param newExtension
	 *            Nouvelle extension
	 * @return Fichier avec à la nouvelle extension
	 */
	public static String replaceExtension(final String value,
			final String oldExtension, final String newExtension) {

		// Rien n'a été spécifié concernant la valeur, on retourne une chaine
		// vide
		if (StringHelper.isNullOrEmptyOrSpace(value)) {
			return EMPTY_STRING;
		}

		// Les extensions (ancienne ou nouvelle) n'ont pas été précisées, on
		// retourne la valeur
		if (StringHelper.isNullOrEmptyOrSpace(oldExtension)
				|| StringHelper.isNullOrEmptyOrSpace(newExtension)) {
			return value;
		}

		final String oldExt = normalizeExtension(oldExtension);
		final String newExt = normalizeExtension(newExtension);

		return value.replaceAll(oldExt, newExt);
	}

	/**
	 * Initialise une nouvelle instance privée de la classe FileHelper
	 */
	private FileHelper() {

	}
}
