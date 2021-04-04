package fr.talend.cryptography;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import fr.talend.log.AppLog;
import fr.talend.string.StringHelper;

/**
 * FileHashHelper est la classe des outils de manipulation de la fonction de
 * hachage cryptographique.
 * 
 * @author elie.vandycke
 * 
 */
public final class FileHashHelper {

	/**
	 * Helper de logging
	 */
	private static AppLog log = AppLog.getInstanceForThisClass();

	/**
	 * Compare le md5sum donné avec l'empreinte du fichier local spécifié.
	 * L'utilisation de MD5 est déconseillée ! Il existe en effet une faille de
	 * sécurité par collision sur MD5. Utilisez plutôt SHA-1.
	 * 
	 * @param file
	 *            Fichier dont on doit calculer l'empreinte md5
	 * @param md5sum
	 *            Empreinte à comparer
	 * @return Vrai si l'empreinte du fichier correspond à md5sum ; Sinon Faux
	 */
	public static boolean compareMd5sum(final File file, final String md5sum) {

		boolean res = false;

		if (md5sum.equals(md5sum(file))) {
			res = true;
		}

		return res;
	}

	/**
	 * Compare le sha1sum donné avec l'empreinte du fichier spécifié
	 * 
	 * @param file
	 *            Fichier dont on doit calculer l'empreinte sha1
	 * @param sha1sum
	 *            Empreinte à comparer
	 * @return Vrai si l'empreinte du fichier correspond à sha1sum ; Sinon Faux
	 */
	public static boolean compareSha1sum(final File file, final String sha1sum) {

		boolean res = false;

		if (sha1sum.equals(sha1sum(file))) {
			res = true;
		}

		return res;
	}

	/**
	 * Obtient la représentation hexadecimale de la valeur spécifiée
	 * 
	 * @param bytes
	 *            Valeur
	 * @return Représentation hexadecimale
	 */
	private static String getHexString(final byte[] bytes) {

		final StringBuilder sb = new StringBuilder(bytes.length * 2);

		for (final byte b : bytes) {
			if (b <= 0x0F && b >= 0x00) {
				// On rajoute le 0 de poid fort ignoré à la conversion.
				sb.append('0');
			}
			sb.append(String.format("%x", b));
		}

		return sb.toString();
	}

	/**
	 * Retourne l'empreinte md5 de fichier spécifié ou null si le fichier n'a
	 * pas pu être lu.
	 * 
	 * @param file
	 *            Fichier dont on doit calculer l'empreinte md5
	 * @return Empreinte md5
	 */
	public static String md5sum(final File file) {

		String localMd5Sum = null;

		if (file.exists() && file.isFile() && file.canRead()) {
			try {
				final MessageDigest md = MessageDigest.getInstance("MD5");
				@SuppressWarnings("resource")
				final DigestInputStream dis = new DigestInputStream(
						new FileInputStream(file), md);
				dis.on(true);

				while (dis.read() != -1) {
					;
				}
				final byte[] b = md.digest();
				localMd5Sum = getHexString(b);
			} catch (Exception ex) {
				log.error(ex);
			}
		} else {
			log.error("Impossible de trouver le fichier : %s",
					file.getAbsolutePath());
		}

		return localMd5Sum;
	}

	/**
	 * Retourne l'empreinte md5 de fichier spécifié ou null si le fichier n'a
	 * pas pu être lu.
	 * 
	 * @param file
	 *            Fichier dont on doit calculer l'empreinte md5
	 * @return Empreinte md5
	 */
	public static String md5sum(final String file) {

		return StringHelper.isNullOrEmptyOrSpace(file) ? null
				: md5sum(new File(file));
	}

	/**
	 * Retourne l'empreinte sha1 de fichier spécifié ou null si le fichier n'a
	 * pas pu être lu.
	 * 
	 * @param file
	 *            Fichier dont on doit calculer l'empreinte sha1
	 * @return Empreinte sha1
	 */
	public static String sha1sum(final File file) {

		String localSha1Sum = null;

		if (file.exists() && file.isFile() && file.canRead()) {
			try {
				MessageDigest md = MessageDigest
						.getInstance(MessageDigestAlgorithms.SHA_1);
				@SuppressWarnings("resource")
				final DigestInputStream dis = new DigestInputStream(
						new FileInputStream(file), md);
				dis.on(true);

				while (dis.read() != -1) {
					;
				}
				final byte[] b = md.digest();
				localSha1Sum = getHexString(b);
			} catch (Exception ex) {
				log.error(ex);
			}
		} else {
			log.error("Impossible de trouver le fichier : %s",
					file.getAbsolutePath());
		}

		return localSha1Sum;
	}

	/**
	 * Retourne l'empreinte sha1 de fichier spécifié ou null si le fichier n'a
	 * pas pu être lu.
	 * 
	 * @param file
	 *            Fichier dont on doit calculer l'empreinte sha1
	 * @return Empreinte sha1
	 */
	public static String sha1sum(final String file) {

		return StringHelper.isNullOrEmptyOrSpace(file) ? null
				: sha1sum(new File(file));
	}

	/**
	 * Initialise une nouvelle instance privée de la classe FileHashHelper
	 */
	private FileHashHelper() {

	}
}
