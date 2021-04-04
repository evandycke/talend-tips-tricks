package fr.talend.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import fr.talend.numeric.IntegerHelper;
import fr.talend.string.StringHelper;

/**
 * DateHelper est la classe des outils de manipulation des objets de type Date.
 * 
 * @author user
 * 
 */
public final class DateHelper {

	/**
	 * Chaine vide
	 */
	private static final String EMPTY_STRING = "";

	/**
	 * Format de date par défaut
	 */
	private static final String DEFAULT_PATTERN = "dd/MM/yyyy";

	/**
	 * Format de date Long
	 */
	private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Tableau des principaux formats de date utilisés
	 */
	private static final String [] FORMAT_DATE = {

		//en format tiret
		"yyyy-MM-dd",
		//en format j/m/a
		"M/d/yy",
		"M/dd/yy",
		"MM/dd/yy",
		//en format j/m/a h:m
		"M/d/yy H:mm",
		"M/dd/yy HH:mm",
		"M/d/yy HH:mm",
		"MM/dd/yy H:mm",
		//en format j/m/a h:m:s
		"yyyy/MM/dd HH:mm:ss.SSS",
		"yyyy-MM-dd HH:mm:ss.SSS",
		"yyyy-MM-dd HH:mm:ss.SS",
		"yyyy-MM-dd HH:mm:ss.S",
		"yyyy-MM-dd H:mm:ss",
		//en format date courte 
		DEFAULT_PATTERN

	};


	/**
	 * Détermine si la valeur spécifiée est postérieure à la valeur de référence
	 * 
	 * @param value
	 *            Valeur
	 * @param refValue
	 *            Valeur de référence
	 * @return Vrai si la valeur spécifiée est postérieure à la valeur de
	 *         référence ; Sinon Faux
	 */
	public static boolean after(final Object value, final Object refValue) {

		final Date dateValue = tryParse(value, DateHelper.getCurrentDate());
		final Date dateRefValue = tryParse(refValue,
				DateHelper.getCurrentDate());

		return dateValue.after(dateRefValue);
	}

	/**
	 * Détermine si la valeur spécifiée est antérieure à la valeur de référence
	 * 
	 * @param value
	 *            Valeur
	 * @param refValue
	 *            Valeur de référence
	 * @return Vrai si la valeur spécifiée est antérieure à la valeur de
	 *         référence ; Sinon Faux
	 */
	public static boolean before(final Object value, final Object refValue) {

		final Date dateValue = tryParse(value, DateHelper.getCurrentDate());
		final Date dateRefValue = tryParse(refValue,
				DateHelper.getCurrentDate());

		return dateValue.before(dateRefValue);
	}

	/**
	 * Permet de tester si une date est comprise entre deux autres (bornes inclues ou exclues)
	 * @param date
	 * @param dateMin
	 * @param dateMax
	 * @param bornesInclues
	 * @return true si compris entre. False dans les autres cas.
	 */
	public static boolean between (Date date, Date dateMin, Date dateMax, boolean bornesInclues){
		boolean rslt = false;
		if(!isNull(date) && !isNull(dateMin) && !isNull(dateMax)){
			//si on inclue les bornes
			if(bornesInclues && (dateMin.before(date) || date.equals(dateMin)) && (dateMax.after(date) || dateMax.equals(date))){
				rslt = true;
			}
			//si les bornes ne sont pas inclues
			else if(dateMin.before(date) && dateMax.after(date)){
					rslt = true;
			}
		}
		return rslt;
	}

	/**
	 * Formate la date spécifiée au format spécifié
	 * 
	 * @param value
	 *            Date
	 * @param format
	 *            Format
	 * @return Date formatée
	 */
	public static String formatDate(final String format, final Date value) {

		final String defaultValue = DateHelper.EMPTY_STRING;

		if (value == null) {
			return defaultValue;
		}

		if (StringHelper.isNullOrEmptyOrSpace(format)) {
			return defaultValue;
		}

		try {
			final DateFormat dateFormat = new SimpleDateFormat(format,
					Locale.FRANCE);
			return dateFormat.format(value);
		} catch (IllegalArgumentException e) {
			return defaultValue;
		}
	}

	/**
	 * Formate la date spécifiée en une représentIllegalArgumentExceptionation
	 * complete jour date mois année heure minute seconde fuseau horaire
	 * 
	 * @param value
	 *            Date
	 * @return Date formatée
	 */
	public static String formatDateComplete(final Date value) {
		return formatDate(DATE_TIME_PATTERN, value);
	}

	/**
	 * Formate la date spécifiée en une représentation courte JJ/MM/AAAA
	 * 
	 * @param value
	 *            Date
	 * @return Date formatée
	 */
	public static String formatDateCourt(final Date value) {

		return isNull(value) ? null : formatDate(DEFAULT_PATTERN, value);
	}

	/**
	 * Formate la date spécifiée en une représentation longue jour date mois
	 * année
	 * 
	 * @param value
	 *            Date
	 * @return Date formatée
	 */
	public static String formatDateLong(final Date value) {
		return formatDate("EEEE d MMM yyyy", value);
	}

	/**
	 * Obtient la date du jour
	 * 
	 * @return Date du jour
	 */
	public static Date getCurrentDate() {

		final Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Obtient la date et l'heure du jour
	 * 
	 * @return Date et heure du jour
	 */
	public static Date getCurrentDateTime() {

		return Calendar.getInstance(Locale.FRANCE).getTime();
	}

	/**
	 * Obtient une date en fonction des valeurs spécifiées
	 * 
	 * @param year
	 *            Année
	 * @param month
	 *            Mois (base 1)
	 * @param day
	 *            Jour
	 * @return Date
	 */
	public static Date getDate(final int year, final int month, final int day) {

		final Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Obtient une date/heure en fonction des valeurs spécifiées
	 * 
	 * @param year
	 *            Année
	 * @param month
	 *            Mois (base 1)
	 * @param day
	 *            Jour
	 * @param hour
	 *            Heures
	 * @param minutes
	 *            Minutes
	 * @param seconds
	 *            Secondes
	 * @return Date
	 */
	public static Date getDateTime(final int year, final int month,
			final int day, final int hour, final int minutes, final int seconds) {

		final Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Obtient la date et l'heure courante au format plat
	 * 
	 * @return Date et l'heure courante au format plat
	 */
	public static String getFlatCurrentDateTime() {

		return getFlatCurrentDateTime(getCurrentDateTime());
	}

	/**
	 * Obtient la date et l'heure courante au format plat
	 * 
	 * @return Date et l'heure courante au format plat
	 */
	public static String getFlatCurrentDateTime(final Date value) {

		if (value == null) {
			return EMPTY_STRING;
		}

		final Calendar cal = Calendar.getInstance(Locale.FRANCE);
		cal.setTime(value);

		final StringBuilder strBld = new StringBuilder(6);
		return strBld
				.append(StringHelper.addLeadingZero(4, cal.get(Calendar.YEAR)))
				.append(StringHelper.addLeadingZero(2,
						cal.get(Calendar.MONTH) + 1))
						.append(StringHelper.addLeadingZero(2,
								cal.get(Calendar.DAY_OF_MONTH)))
								.append(StringHelper.addLeadingZero(2,
										cal.get(Calendar.HOUR_OF_DAY)))
										.append(StringHelper.addLeadingZero(2, cal.get(Calendar.MINUTE)))
										.append(StringHelper.addLeadingZero(2, cal.get(Calendar.SECOND)))
										.toString();
	}
	/**
	 * Permet de tester si une String (avec un pattern donné) est une date
	 * @param stringDate
	 * @param pattern
	 * @return true s'il s'agit d'une date, sinon false
	 */
	public static boolean isDate(String stringDate, String pattern) {

		Date testDate = null;

		try {
			testDate = parse(pattern, stringDate);
		} catch (ParseException e) {
			return false;
		}

		String formatDate = formatDate(pattern, testDate);
		if(formatDate.equalsIgnoreCase(stringDate) || formatDate.length()== stringDate.length()) {

			return true;
		}

		return false;

	}

	/**
	 * Détermine si la valeur spécifiée est nulle ou non
	 * 
	 * @param value
	 *            Valeur à tester
	 * @return Vrai si la valeur est nulle ; Sinon Faux
	 */
	public static boolean isNull(final Date value) {

		return value == null;
	}

	/**
	 * Convertit l'objet spécifié en objet de type Date
	 * 
	 * @param format
	 *            Modèle de date
	 * @param value
	 *            Valeur à convertir
	 * @return Valeur convertie
	 * @throws ParseException
	 */
	private static Date parse(final String format, final Object value)
			throws ParseException {

		if (value == null) {
			return null;
		}

		if (value instanceof Date) {
			return (Date) value;
		}

		String pattern = format;

		if (StringHelper.isNullOrEmptyOrSpace(format)) {
			pattern = DEFAULT_PATTERN;
		}

		final String strValue = String.valueOf(value);
		final SimpleDateFormat sdf = new SimpleDateFormat(pattern,
				Locale.FRANCE);
		sdf.setLenient(false);
		return sdf.parse(strValue);
	}

	/**
	 * Retourne une date aléatoire, comprise entre les valeurs spécifiées
	 * 
	 * @param min
	 *            Année minimum
	 * @param max
	 *            Année maximum
	 * @return Valeur aléatoire (y compris min/max)
	 */
	public static Date random(final int min, final int max) {

		final GregorianCalendar cal = new GregorianCalendar();

		// Une année au hasard
		final int annee = IntegerHelper.random(min, max);
		cal.set(GregorianCalendar.YEAR, annee);

		// Un jour dans l'année au hasard
		final int jourAnnee = IntegerHelper.random(1,
				GregorianCalendar.DAY_OF_YEAR);
		cal.set(GregorianCalendar.DAY_OF_YEAR, jourAnnee);

		return cal.getTime();
	}

	/**
	 * Permet de transformer la date en parametre en une date au bon format.
	 * @param dateATransformer en String
	 * @return la date au format dd/MM/yyyy ou "Format non géré"
	 */
	public static String transformDate(final String dateATransformer){
		//valeur par défaut retournée
		String resultat = dateATransformer + " Format non géré";
		if(!StringHelper.isNullOrEmptyOrSpace(dateATransformer)){
			//Pour boucler sur les différents formats, il nous faut un booleen et un compteur
			boolean transformationReussie = false;
			int tabFormatLen = 0;
			//date ou l'on stockera la dateATransformer au format date
			Date dateInitial = null;

			//tant que l'on a pas réussie à trouver le bon format et qu'il en reste à tester, on boucle
			while (!transformationReussie && tabFormatLen < FORMAT_DATE.length){
				//par défaut on condidère que l'on a réussie la transformation

				try{
					if(isDate(dateATransformer, FORMAT_DATE[tabFormatLen])){
						dateInitial = parse(FORMAT_DATE[tabFormatLen], dateATransformer);
						transformationReussie=true;
					}
					else{
						tabFormatLen++;
					}
				}catch(ParseException e){
					//en cas d'échec de parsage, on met le booleen à false et on boucle s'il reste des valeurs à tester
					transformationReussie=false;
					tabFormatLen++;
				}
			}
			//si la transformation a bien réussie, on format la date en String sur le format par défaut
			if(transformationReussie){
				if(FORMAT_DATE[tabFormatLen].length()>15){
					resultat = formatDateComplete(dateInitial);
				}
				else{
					resultat = formatDateCourt(dateInitial);
				}
			}
		}
		return resultat;
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type Date
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @return Valeur convertie
	 */
	public static Date tryParse(final Object value) {

		return tryParseFormat(DEFAULT_PATTERN, value);
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type Date
	 * 
	 * @param value
	 *            Valeur à convertir
	 * @param defaultValue
	 *            Valeur par défaut
	 * @return Valeur convertie ; Sinon valeur par défaut
	 */
	public static Date tryParse(final Object value, final Date defaultValue) {

		return tryParseFormat(DEFAULT_PATTERN, value, defaultValue);
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type Date
	 * 
	 * @param format
	 *            Modèle de date
	 * @param value
	 *            Valeur à convertir
	 * @return Valeur convertie
	 */
	public static Date tryParseFormat(final String format, final Object value) {

		return tryParseFormat(format, value, null);
	}

	/**
	 * Essaie de convertir l'objet spécifié en objet de type Date
	 * 
	 * @param format
	 *            Modèle de date
	 * @param value
	 *            Valeur à convertir
	 * @param defaultValue
	 *            Valeur par défaut
	 * @return Valeur convertie ; Sinon valeur par défaut
	 */
	public static Date tryParseFormat(final String format, final Object value,
			final Date defaultValue) {

		try {
			final Date result = parse(format, value);
			return result == null ? defaultValue : result;
		} catch (ParseException ex) {
			return defaultValue;
		}
	}

	/**
	 * Initialise une nouvelle instance privée de la classe DateHelper
	 */
	private DateHelper() {

	}
}
