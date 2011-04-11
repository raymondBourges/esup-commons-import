/**
 * ESUP-Portail Example Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-example
 */
package org.esupportail.example.web.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.esupportail.commons.services.cas.CasException;
import org.esupportail.commons.services.cas.CasService;
import org.esupportail.commons.utils.Assert;
import org.esupportail.commons.utils.HttpUtils;
import org.esupportail.commons.utils.strings.StringUtils;
import org.esupportail.portal.ws.client.PortalGroup;
import org.esupportail.portal.ws.client.PortalService;
import org.esupportail.portal.ws.client.PortalUser;
import org.esupportail.portal.ws.client.exceptions.PortalErrorException;
import org.esupportail.portal.ws.client.exceptions.PortalGroupNotFoundException;
import org.esupportail.portal.ws.client.exceptions.PortalUserNotFoundException;

/**
 * A bean to manage portal group memberships.
 */
public class GroupsController extends AbstractContextAwareController {
	
	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 575049557218964094L;

	/**
	 * The uid.
	 */
	private String uid;

	/**
	 * The groupId.
	 */
	private String groupId;
	
	/**
	 * The portal service.
	 */
	private PortalService portalService;
	
	/**
	 * The CAS service.
	 */
	private CasService casService;
	
	/**
	 * The CAS target service.
	 */
	private String casTargetService;
	
	/**
	 * The portal user.
	 */
	private PortalUser portalUser;

	/**
	 * The portal users.
	 */
	private List<PortalUser> portalUsers;

	/**
	 * The portal group.
	 */
	private PortalGroup portalGroup;

	/**
	 * The portal sub groups.
	 */
	private List<PortalGroup> portalSubGroups;

	/**
	 * The portal groups of the user.
	 */
	private List<PortalGroup> portalUserGroups;
	
	/**
	 * The portal groups that match the token.
	 */
	private List<PortalGroup> portalSearchGroups;
	
	/**
	 * true if the user belong to the group.
	 */
	private boolean member;

	/**
	 * Constructor.
	 */
	public GroupsController() {
		super();
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#afterPropertiesSetInternal()
	 */
	@Override
	public void afterPropertiesSetInternal() {
		super.afterPropertiesSetInternal();
		Assert.notNull(portalService, 
				"property portalService of class " + this.getClass().getName() + " can not be null");
		Assert.notNull(casService, 
				"property casService of class " + this.getClass().getName() + " can not be null");
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractContextAwareController#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		uid = null;
		groupId = null;
		portalUser = null;
		portalUsers = null;
		portalUserGroups = null;
		portalGroup = null;
		portalSubGroups = null;
		portalSearchGroups = null;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode() 
		+ "[uid=[" + uid + "], groupId=[" + groupId + "]]";
	}

	/**
	 * @return true if the current user is allowed to view the page.
	 */
	public boolean isPageAuthorized() {
		return getCurrentUser() != null;
	}
	
	/**
	 * JSF callback.
	 * @return a String.
	 */
	public String enter() {
		if (!isPageAuthorized()) {
			addUnauthorizedActionMessage();
			return null;
		}
		return "navigationGroups";
	}
	
	/**
	 * JSF callback.
	 * @return a String.
	 */
	public String test() {
		portalUser = null;
		portalUsers = null;
		portalUserGroups = null;
		portalGroup = null;
		portalSubGroups = null;
		portalSearchGroups = null;
		boolean error = false;
		try {
			try {
				if (uid != null) {
					portalUser = portalService.getUser(uid);
					portalUserGroups = portalService.getUserGroups(portalUser);
				}
			} catch (PortalUserNotFoundException e) {
				addErrorMessage(null, "GROUPS.MESSAGE.PORTAL_USER_NOT_FOUND", uid);
				error = true;
			}
			if (uid != null) {
				portalUsers = portalService.searchUsers(uid);
			}
			try {
				if (groupId != null) {
					portalGroup = portalService.getGroupById(groupId);
					portalSubGroups = portalService.getSubGroups(portalGroup);
				}
			} catch (PortalGroupNotFoundException e) {
				addErrorMessage(null, "GROUPS.MESSAGE.PORTAL_GROUP_NOT_FOUND", groupId);
				error = true;
			}
			if (groupId != null) {
				portalSearchGroups = portalService.searchGroupsByName(groupId);
			}
			try {
				if (!error && uid != null && groupId != null) {
					member = portalService.isUserMemberOfGroup(portalUser, portalGroup);
				}
			} catch (PortalUserNotFoundException e) {
				addErrorMessage(null, "GROUPS.MESSAGE.PORTAL_USER_NOT_FOUND", uid);
			} catch (PortalGroupNotFoundException e) {
				addErrorMessage(null, "GROUPS.MESSAGE.PORTAL_USER_NOT_FOUND", uid);
			}
		} catch (PortalErrorException e) {
			addErrorMessage(null, "GROUPS.MESSAGE.PORTAL_ERROR", e.getMessage());
		}
		return null;
	}

	/**
	 * JSF callback.
	 * @return a String.
	 */
	public String retrievePt() {
		try {
			String pt = casService.getProxyTicket(casTargetService);
			addInfoMessage(null, "GROUPS.MESSAGE.PT", pt);
		} catch (CasException e) {
			addErrorMessage(null, "GROUPS.MESSAGE.CAS_ERROR", e.getMessage());
		}
		return null;
	}

	/**
	 * @param portalService the portalService to set
	 */
	public void setPortalService(final PortalService portalService) {
		this.portalService = portalService;
	}

	/**
	 * @param casService the casService to set
	 */
	public void setCasService(final CasService casService) {
		this.casService = casService;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(final String groupId) {
		this.groupId = StringUtils.nullIfEmpty(groupId);
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(final String uid) {
		this.uid = StringUtils.nullIfEmpty(uid);
	}

	/**
	 * @return the member
	 */
	public boolean isMember() {
		return member;
	}

	/**
	 * @return the portalGroup
	 */
	public PortalGroup getPortalGroup() {
		return portalGroup;
	}

	/**
	 * @return the portalSubGroups
	 */
	public List<PortalGroup> getPortalSubGroups() {
		return portalSubGroups;
	}

	/**
	 * @return the portalUser
	 */
	public PortalUser getPortalUser() {
		return portalUser;
	}

	/**
	 * @return the portalUserGroups
	 */
	public List<PortalGroup> getPortalUserGroups() {
		return portalUserGroups;
	}

	/**
	 * @return the portalSearchGroups
	 */
	public List<PortalGroup> getPortalSearchGroups() {
		return portalSearchGroups;
	}

	/**
	 * @return the portalUsers
	 */
	public List<PortalUser> getPortalUsers() {
		return portalUsers;
	}
	
	/**
	 * @return the preferences.
	 */
	public Map<String, Object> getPrefs() {
		return HttpUtils.getPortalPrefs();
	}
	
	/**
	 * @return the names of the preferences.
	 */
	public Set<String> getPrefNames() {
		return HttpUtils.getPortalPrefs().keySet();
	}

	/**
	 * @return the casTargetService
	 */
	public String getCasTargetService() {
		return casTargetService;
	}

	/**
	 * @param casTargetService the casTargetService to set
	 */
	public void setCasTargetService(final String casTargetService) {
		this.casTargetService = casTargetService;
	}
	
}
