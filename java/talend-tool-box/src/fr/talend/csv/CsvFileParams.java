package fr.talend.csv;

import java.util.Locale;

import fr.talend.string.StringHelper;

/**
 * CsvFileParams repr�sente la classe des param�tres d'un fichier CSV.
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
	 * S�parateur du fichier CSV
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
	 *            S�parateur
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
	 * Obtient le s�parateur du fichier CSV
	 * 
	 * @return S�parateur du fichier CSV
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * D�termine si les param�tres du fichier CSV sont valides
	 * 
	 * @return Vrai si les param�tres sont valides ; Sinon Faux
	 */
	public boolean isValid() {

		return this.nbColonnes > 0
				&& !StringHelper.isNullOrEmptyOrSpace(this.separator)
				&& this.separator.length() == 1;
	}

	/**
	 * D�finit le nombre de colonnes du fichier CSV
	 * 
	 * @param value
	 *            Nombre de colonnes du fichier CSV
	 */
	public void setNbColonnes(final int value) {
		this.nbColonnes = value;
	}

	/**
	 * D�finit le s�parateur du fichier CSV
	 * 
	 * @param value
	 *            S�parateur du fichier CSV
	 */
	public void setSeparator(final String value) {
		this.separator = value;
	}

	/**
	 * Obtient la repr�sentation sous forme d'une chaine de caract�re de l'objet
	 * CsvFileParams
	 * 
	 * @return Repr�sentation sous forme d'une chaine de caract�re de l'objet
	 *         CsvFileParams
	 */
	@Override
	public String toString() {

		return String.format(Locale.FRANCE,
				"Nombre de colonnes : [%s] - S�parateur : [%s]",
				this.nbColonnes, this.separator);
	}
}