/**
 * ESUP-Portail Repository - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-repository
 */
package org.esupportail.example.web.controllers;

import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.esupportail.example.domain.beans.Department;
import org.esupportail.example.domain.beans.Thing;
import org.esupportail.example.web.beans.DepartmentNode;
import org.esupportail.example.web.beans.ThingNode;

/**
 * Bean to present and manage downloads.
 */
public class TreeController extends AbstractContextAwareController {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -5568857986030068711L;

	/**
	 * The tree model passed to JSP pages.
	 */
	private TreeModelBase treeModel;
	
	/**
	 * Bean constructor.
	 */
	public TreeController() {
		super();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode();
	}

	/**
	 * @see org.esupportail.example.web.controllers.AbstractDomainAwareBean#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		treeModel = null;
	}

	/**
	 * @return true if the current user is allowed to view the page.
	 */
	public boolean isPageAuthorized() {
		return true;
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
		if (treeModel == null) {
			refreshTree();
		}
		return "navigationTree";
	}

	/**
	 * JSF callback.
	 * @return a string.
	 */
	public String refreshTree() {
		treeModel = new TreeModelBase(getRootNode());
		return null;
	}
	
	/**
	 * @return the root node.
	 */
    @SuppressWarnings("unchecked")
	private TreeNode getRootNode() {
    	TreeNode rootNode = new TreeNodeBase("root", "", "root", false);
    	for (Department department : getDomainService().getVisibleDepartments(getCurrentUser())) {
    		TreeNode departmentNode = new DepartmentNode(department);
    		for (Thing thing : getDomainService().getThings(department)) {
				departmentNode.getChildren().add(new ThingNode(thing));
			}
    		rootNode.getChildren().add(departmentNode);
    	}
    	return rootNode;
    }

	/**
	 * @return the tree of the projects
	 */
	public TreeModel getTreeModel() {
        return treeModel;
    }

}
