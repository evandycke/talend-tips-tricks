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
	 * Param�tres du fichier CSV
	 */
	private CsvFileParams params;

	/**
	 * CsvFileHolder est la classe repr�sentant le responsable de
	 * l'instanciation.
	 * 
	 * @author user
	 * 
	 */
	private static class CsvFileHolder {

		/**
		 * Instance unique non pr�initialis�e
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
	 * Initialise une nouvelle instance priv�e de la classe CsvFileHelper
	 */
	private CsvFileHelper() {

	}

	/**
	 * Obtient les param�tres du fichier CSV
	 * 
	 * @return Param�tres du fichier CSV
	 */
	public CsvFileParams getParams() {
		return params;
	}

	/**
	 * D�termine si la ligne sp�cifi�e correspond aux param�tres d�finis pour le
	 * fichier CSV
	 * 
	 * @param line
	 *            Ligne � contr�ler
	 * @return Vrai si la ligne est valide ; Sinon Faux
	 */
	public boolean isValid(final String line) {

		boolean result = false;

		// Si la ligne est renseign�e, on peut la splitter
		if (!StringHelper.isNullOrEmptyOrSpace(line)) {

			final String[] lineSplitted = line.split(this.getParams()
					.getSeparator(), -1);

			// On v�rifie que le nombre d'�l�ments splitt�s correspond aux
			// param�tres du fichier
			result = this.getParams().getNbColonnes() == lineSplitted.length;
		}

		return result;
	}

	/**
	 * D�finit les param�tres du fichier CSV
	 * 
	 * @param value
	 *            Param�tres du fichier CSV
	 */
	public void setParams(final CsvFileParams value) {
		this.params = value;
	}
}
