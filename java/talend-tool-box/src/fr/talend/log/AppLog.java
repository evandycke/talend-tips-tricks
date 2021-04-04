package fr.talend.log;

import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Helper pour loguer - sans erreur et d'une manière optimisée - les événements
 * de l'application.
 * 
 * @author elie.vandycke
 * 
 */
public class AppLog {

	/**
	 * Chaine vide
	 */
	private static final String EMPTY_STRING = "";

	/**
	 * Loggeur
	 */
	private static AppLog appLog = getInstanceForThisClass();

	/**
	 * Journalise les valeurs spécifiées
	 * 
	 * @param log
	 *            Simple wrapper API around multiple logging APIs
	 * @param level
	 *            Niveau
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public static void doLog(final Log log, final int level,
			final String message, final Object... args) {

		switch (level) {
		case org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_TRACE:

			if (log.isTraceEnabled()) {
				log.trace(formatMessage(message, args));
			}

			break;

		case org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_DEBUG:

			if (log.isDebugEnabled()) {
				log.debug(formatMessage(message, args));
			}

			break;

		case org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_INFO:
			if (log.isInfoEnabled()) {
				log.info(formatMessage(message, args));
			}

			break;

		case org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_WARN:
			if (log.isWarnEnabled()) {
				log.warn(formatMessage(message, args));
			}

			break;

		case org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_ERROR:
			if (log.isErrorEnabled()) {
				log.error(formatMessage(message, args));
			}

			break;

		default:
			if (log.isWarnEnabled()) {
				log.warn("[Unknown log_level:  " + level + "] "
						+ formatMessage(message, args));
			}

			break;
		}
	}

	/**
	 * Formate un message avec les arguments spécifiés
	 * 
	 * @param msg
	 *            Message à formater
	 * @param args
	 *            Arguments qui seront utilisés dans le message
	 * @return Message formaté
	 */
	private static String formatMessage(String msg, final Object... args) {

		String message = EMPTY_STRING;
		if (args.length > 0) {
			try {
				message = String.format(msg, args);
			} catch (Exception e) {
				message += ", args=" + ArrayUtils.toString(args, "");
				appLog.log.warn(e.getMessage() + ". Message=" + message);

			}
		}

		return message;
	}

	/**
	 * Obtient une instance du loggeur pour la classe spécifiée
	 * 
	 * @param clazz
	 *            Classe pour laquelle on souhaite obtenir une instance du
	 *            loggeur
	 * @return Instance du loggeur
	 */
	public static AppLog getInstance(final Class<?> clazz) {

		AppLog loggeur = new AppLog();
		loggeur.log = LogFactory.getLog(clazz);
		return loggeur;
	}

	/**
	 * Obtient une instance du loggeur pour cette classe
	 * 
	 * @return Instance du loggeur
	 */
	public static AppLog getInstanceForThisClass() {

		AppLog loggeur = new AppLog();
		StackTraceElement myCaller = Thread.currentThread().getStackTrace()[2];
		loggeur.log = LogFactory.getLog(myCaller.getClassName());

		return loggeur;
	}

	/**
	 * Simple wrapper API around multiple logging APIs
	 */
	private Log log = null;

	/**
	 * Logue un message de debug
	 * 
	 * @param list
	 *            Liste à déboguer
	 */
	public void debug(final Collection<?> list) {
		debug(list, null);
	}

	/**
	 * Logue un message de debug
	 * 
	 * @param list
	 *            Liste à déboguer
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public void debug(final Collection<?> list, final String message,
			final Object... args) {

		if (message != null) {
			debug(message, args);
		}

		for (Object objectItem : list) {
			doLog(this.log,
					org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_DEBUG,
					objectItem.toString());
		}
	}

	/**
	 * Logue un message de debug
	 * 
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public void debug(final String message, final Object... args) {
		doLog(this.log,
				org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_DEBUG,
				message, args);
	}

	/**
	 * Logue un message
	 * 
	 * @param level
	 *            Niveau
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public void doLog(final int level, final String message,
			final Object... args) {
		doLog(this.log, level, message, args);
	}

	/**
	 * Logue une erreur
	 * 
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public void error(final String message, final Object... args) {
		doLog(this.log,
				org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_ERROR,
				message, args);
	}

	/**
	 * Logue une erreur
	 * 
	 * @param throwable
	 *            Erreur
	 */
	public void error(final Throwable throwable) {
		if (this.log.isErrorEnabled()) {
			this.log.error(throwable.getMessage(), throwable);
		}
	}

	/**
	 * Logue une erreur
	 * 
	 * @param throwable
	 *            Erreur
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public void error(final Throwable throwable, final String message,
			final Object... args) {
		if (this.log.isErrorEnabled()) {
			this.log.error(formatMessage(message, args), throwable);
		}
	}

	/**
	 * Logue une information
	 * 
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public void info(final String message, final Object... args) {
		doLog(this.log,
				org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_INFO,
				message, args);
	}

	/**
	 * Logue une trace
	 * 
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public void trace(final String message, final Object... args) {
		doLog(this.log,
				org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_TRACE,
				message, args);
	}

	/**
	 * Logue un avertissement
	 * 
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public void warn(final String message, final Object... args) {
		doLog(this.log,
				org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_WARN,
				message, args);
	}

	/**
	 * Logue un avertissement
	 * 
	 * @param throwable
	 *            Erreur
	 * @param message
	 *            Message
	 * @param args
	 *            Arguments
	 */
	public void warn(final Throwable throwable, final String message,
			final Object... args) {
		if (this.log.isWarnEnabled()) {
			this.log.warn(formatMessage(message, args), throwable);
		}
	}
}