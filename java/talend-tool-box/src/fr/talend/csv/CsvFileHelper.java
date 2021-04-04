package fr.talend.csv;

import fr.talend.string.StringHelper;

/**
 * CsvFileHelper est la classe des outils de manipulation de fichiers CSV.
 * 
 * @author elie.vandycke
 * 
 */
public class CsvFileHelper {

	/**
	 * Paramètres du fichier CSV
	 */
	private CsvFileParams params;

	/**
	 * CsvFileHolder est la classe représentant le responsable de
	 * l'instanciation.
	 * 
	 * @author user
	 * 
	 */
	private static class CsvFileHolder {

		/**
		 * Instance unique non préinitialisée
		 */
		private static final CsvFileHelper INSTANCE = new CsvFileHelper();
	}

	/**
	 * Obtient l'instance unique des outils de manipulation de fichiers CSV.
	 * 
	 * @return Outils de manipulation de fichiers CSV
	 */
	public static CsvFileHelper getInstance() {

		return CsvFileHolder.INSTANCE;
	}

	/**
	 * Initialise une nouvelle instance privée de la classe CsvFileHelper
	 */
	private CsvFileHelper() {

	}

	/**
	 * Obtient les paramètres du fichier CSV
	 * 
	 * @return Paramètres du fichier CSV
	 */
	public CsvFileParams getParams() {
		return params;
	}

	/**
	 * Détermine si la ligne spécifiée correspond aux paramètres définis pour le
	 * fichier CSV
	 * 
	 * @param line
	 *            Ligne à contrôler
	 * @return Vrai si la ligne est valide ; Sinon Faux
	 */
	public boolean isValid(final String line) {

		boolean result = false;

		// Si la ligne est renseignée, on peut la splitter
		if (!StringHelper.isNullOrEmptyOrSpace(line)) {

			final String[] lineSplitted = line.split(this.getParams()
					.getSeparator(), -1);

			// On vérifie que le nombre d'éléments splittés correspond aux
			// paramètres du fichier
			result = this.getParams().getNbColonnes() == lineSplitted.length;
		}

		return result;
	}

	/**
	 * Définit les paramètres du fichier CSV
	 * 
	 * @param value
	 *            Paramètres du fichier CSV
	 */
	public void setParams(final CsvFileParams value) {
		this.params = value;
	}
}
