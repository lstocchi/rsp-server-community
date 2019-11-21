package org.jboss.tools.rsp.server.felix.servertype.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.tools.rsp.api.dao.ListServerActionResponse;
import org.jboss.tools.rsp.api.dao.ServerActionRequest;
import org.jboss.tools.rsp.api.dao.ServerActionWorkflow;
import org.jboss.tools.rsp.api.dao.WorkflowResponse;
import org.jboss.tools.rsp.eclipse.core.runtime.Status;
import org.jboss.tools.rsp.launching.memento.JSONMemento;
import org.jboss.tools.rsp.server.generic.servertype.GenericServerBehavior;
import org.jboss.tools.rsp.server.spi.servertype.IServer;
import org.jboss.tools.rsp.server.spi.util.StatusConverter;

public class FelixServerDelegate extends GenericServerBehavior {
	public FelixServerDelegate(IServer server, JSONMemento behaviorMemento) {
		super(server, behaviorMemento);
	}
	
	@Override
	public ListServerActionResponse listServerActions() {
		ListServerActionResponse ret = new ListServerActionResponse();
		ret.setStatus(StatusConverter.convert(Status.OK_STATUS));
		List<ServerActionWorkflow> allActions = new ArrayList<>();
		ServerActionWorkflow wf1 = FelixWipeCacheRestartAction.getInitialWorkflow(this);
		allActions.add(wf1);
		ret.setWorkflows(allActions);
		return ret;
	}
	
	@Override
	public WorkflowResponse executeServerAction(ServerActionRequest req) {
		if( FelixWipeCacheRestartAction.ACTION_WIPE_CACHE_RESTART_ID.equals(req.getActionId() )) {
			return new FelixWipeCacheRestartAction(this).handle(req);
		}
		return cancelWorkflowResponse();
	}

}