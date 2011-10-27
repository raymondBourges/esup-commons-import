/**
 * ESUP-Portail Blank Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.formation.domain.beans;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * The class that represent users.
 */
@Entity
@SuppressWarnings("unchecked")
@NamedQueries({
	@NamedQuery(
		    name="allUsers",
		    query="SELECT u FROM User u"
		    ),
    @NamedQuery(
    		name="userByLogin",
    		query="SELECT u FROM User u WHERE u.login = :login"
    ) 
})
public class User implements Serializable {

	/**
	 * For serialize.
	 */
	private static final long serialVersionUID = 7427732897404494181L;

	/**
	 * For Sorting.
	 */
	@SuppressWarnings("rawtypes")
	public static Comparator<User> ORDER_DISPLAYNAME = new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {
			return ((User) o1).getDisplayName().compareTo(
					((User) o2).getDisplayName());
		}
	};
	/**
	 * User id
	 */
	@Id
	@GeneratedValue
	private long id;
	
	/**
	 * Login of the user.
	 */
	@Column(unique=true)
	private String login;

	/**
	 * Display Name of the user.
	 */
	@Transient
	private String displayName;

	/**
	 * True for administrators.
	 */
	@Transient
	private boolean admin;

	/**
	 * The prefered language.
	 */
	@Transient
	private String language;
	
	//Fetch lazy par defaut
	/*@OneToMany(cascade={CascadeType.ALL},mappedBy="owner")
	private List<Task> taches;
*/
	/**
	 * Bean constructor.
	 */
	public User() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		return login.equals(((User) obj).getLogin());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "User#" + hashCode() + "[id=[" + login + "], displayName=["
				+ displayName + "], admin=[" + admin + "], language=["
				+ language + "]]";
	}

	/**
	 * @return the login of the user.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
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
		if (language!=null){
			Locale locale = new Locale(language);
			return locale.getDisplayLanguage(locale);
		}
		else
			return null;
	}
	/**
	 * @return the id of the user.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
/*
	public List<Task> getTaches() {
		return taches;
	}

	public void setTaches(List<Task> taches) {
		this.taches = taches;
	}*/
	
}