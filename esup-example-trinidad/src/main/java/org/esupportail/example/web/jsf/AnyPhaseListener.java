/**
 * ESUP-Portail example Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.web.jsf;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;
import org.esupportail.commons.services.database.DatabaseUtils;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public class AnyPhaseListener implements PhaseListener {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = -1282527789527600791L;

	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	public void beforePhase(PhaseEvent event) {
		PhaseId phaseId = event.getPhaseId();
		if (logger.isDebugEnabled()) {
			logger.debug("beforePhase : " + phaseId);
		}
		if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)
				|| phaseId.equals(PhaseId.INVOKE_APPLICATION)) {
			DatabaseUtils.open();
			DatabaseUtils.begin();
		}
	}

	/**
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent event) {
		PhaseId phaseId = event.getPhaseId();
		if (logger.isDebugEnabled()) {
			logger.debug("afterPhase : " + phaseId);
		}
		if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)
				|| phaseId.equals(PhaseId.INVOKE_APPLICATION)) {
			DatabaseUtils.commit();
			DatabaseUtils.close();
		}
	}

	/**
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
