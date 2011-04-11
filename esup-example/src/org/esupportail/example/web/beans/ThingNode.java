package org.esupportail.example.web.beans;

import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.esupportail.example.domain.beans.Thing;

/**
 * A class to represent the tree node for things.
 */
public class ThingNode extends TreeNodeBase {
	
	/**
	 * serialversionUID.
	 */
	private static final long serialVersionUID = 278589014441538822L;
	
	/**
	 * the type of the node.
	 */
	private static final String NODE_TYPE = "thing";
	
	/**
	 * The thing of the node.
	 */
	private Thing thing;
	
	/**
	 * @param thing
	 */
    public ThingNode(final Thing thing) {
        super(NODE_TYPE, thing.getValue(), true);
        this.thing = thing;
    }

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ThingNode)) {
			return false;
		}
		return getThing().equals(((ThingNode) obj).getThing());
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
		+ "[thing=" + thing + "]";
	}

	/**
	 * @return the thing
	 */
	public Thing getThing() {
		return thing;
	}

}
