package fr.talend.csv;

import java.util.Locale;

import fr.talend.string.StringHelper;

/**
 * CsvFileParams représente la classe des paramètres d'un fichier CSV.
 * 
 * @author elie.vandycke
 * 
 */
public class CsvFileParams {

	/**
	 * Nombre de colonnes du fichier CSV
	 */
	private int nbColonnes;

	/**
	 * Séparateur du fichier CSV
	 */
	private String separator;

	/**
	 * Initialise une nouvelle instance de la classe CsvFileParams
	 */
	public CsvFileParams() {

		this.nbColonnes = 0;
	}

	/**
	 * Initialise une nouvelle instance de la classe CsvFileParams
	 * 
	 * @param nbColonnes
	 *            Nombre de colonnes
	 * @param separator
	 *            Séparateur
	 */
	public CsvFileParams(final int nbColonnes, final String separator) {

		this.nbColonnes = nbColonnes;
		this.separator = separator;
	}

	/**
	 * Obtient le nombre de colonnes du fichier CSV
	 * 
	 * @return Nombre de colonnes du fichier CSV
	 */
	public int getNbColonnes() {
		return nbColonnes;
	}

	/**
	 * Obtient le séparateur du fichier CSV
	 * 
	 * @return Séparateur du fichier CSV
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * Détermine si les paramètres du fichier CSV sont valides
	 * 
	 * @return Vrai si les paramètres sont valides ; Sinon Faux
	 */
	public boolean isValid() {

		return this.nbColonnes > 0
				&& !StringHelper.isNullOrEmptyOrSpace(this.separator)
				&& this.separator.length() == 1;
	}

	/**
	 * Définit le nombre de colonnes du fichier CSV
	 * 
	 * @param value
	 *            Nombre de colonnes du fichier CSV
	 */
	public void setNbColonnes(final int value) {
		this.nbColonnes = value;
	}

	/**
	 * Définit le séparateur du fichier CSV
	 * 
	 * @param value
	 *            Séparateur du fichier CSV
	 */
	public void setSeparator(final String value) {
		this.separator = value;
	}

	/**
	 * Obtient la représentation sous forme d'une chaine de caractère de l'objet
	 * CsvFileParams
	 * 
	 * @return Représentation sous forme d'une chaine de caractère de l'objet
	 *         CsvFileParams
	 */
	@Override
	public String toString() {

		return String.format(Locale.FRANCE,
				"Nombre de colonnes : [%s] - Séparateur : [%s]",
				this.nbColonnes, this.separator);
	}
}