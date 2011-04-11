package org.esupportail.example.web.beans;

import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.esupportail.example.domain.beans.Department;

/**
 * 
 * @author Benjamin
 *
 */
public class DepartmentNode extends TreeNodeBase {
	
	/**
	 * serialversionUID.
	 */
	private static final long serialVersionUID = 278589014441538822L;
	
	/**
	 * the type of the node.
	 */
	private static final String NODE_TYPE = "department";
	
	/**
	 * The department of the node.
	 */
	private Department department;
	
	/**
	 * @param department
	 */
    public DepartmentNode(final Department department) {
        super(NODE_TYPE, department.getLabel(), false);
        this.department = department;
    }

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DepartmentNode)) {
			return false;
		}
		return getDepartment().equals(((DepartmentNode) obj).getDepartment());
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + hashCode() 
		+ "[department=" + department + "]";
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

}
