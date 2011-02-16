/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.blank.domain.beans;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Locale;

import org.esupportail.blank.services.i18n.BundleService;

/**
 * The class that represent users.
 */
@SuppressWarnings("unchecked")
public class User implements Serializable {

	/**
	 * For serialize.
	 */
	private static final long serialVersionUID = 7427732897404494181L;

	/**
	 * For Sorting.
	 */
	public static Comparator<User> ORDER_DISPLAYNAME = new Comparator() {
		public int compare(Object o1, Object o2) {
			return ((User) o1).getDisplayName().compareTo(
					((User) o2).getDisplayName());
		}
	};

	/**
	 * Id of the user.
	 */
	private String id;

	/**
	 * Display Name of the user.
	 */
	private String displayName;

	/**
	 * True for administrators.
	 */
	private boolean admin;

	/**
	 * The prefered language.
	 */
	private String language;

	/**
	 * The prefered accessibility mode.
	 */
	private String accessibilityMode;

	/**
	 * Bean constructor.
	 */
	public User() {
		super();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		return id.equals(((User) obj).getId());
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "User#" + hashCode() + "[id=[" + id + "], displayName=["
				+ displayName + "], admin=[" + admin + "], language=["
				+ language + "], accessibility=[" + accessibilityMode + "]]";
	}

	/**
	 * @return the id of the user.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the displayName.
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * @param displayName
	 *            The displayName to set.
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param admin
	 *            The admin to set.
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * @return Returns the admin.
	 */
	public boolean isAdmin() {
		return this.admin;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the user display language.
	 */
	public String getDisplayLanguage() {
		Locale locale = new Locale(language);
		return locale.getDisplayLanguage(locale);
	}

	/**
	 * @return the accessibilityMode
	 */
	public String getAccessibilityMode() {
		return accessibilityMode;
	}

	/**
	 * @param accessibilityMode
	 *            the accessibilityMode to set
	 */
	public void setAccessibilityMode(String accessibilityMode) {
		this.accessibilityMode = accessibilityMode;
	}

	/**
	 * @return the user display accessibility mode
	 */
	public String getDisplayAccessibilityMode() {
		if (accessibilityMode.equals("default"))
			return BundleService.getString("PREFERENCES.ACCESSIBILITY.DEFAULT");
		if (accessibilityMode.equals("inaccessible"))
			return BundleService
					.getString("PREFERENCES.ACCESSIBILITY.INACCESSIBLE");
		else
			return BundleService
					.getString("PREFERENCES.ACCESSIBILITY.SCREENREADER");
	}

}