package org.jboss.tools.rsp.server.tomcat.impl;

import org.jboss.tools.rsp.server.LauncherSingleton;
import org.jboss.tools.rsp.server.ServerManagementServerLauncher;
import org.jboss.tools.rsp.server.generic.GenericServerExtensionModel;

public class TomcatServerMain extends ServerManagementServerLauncher {

	public static void main(String[] args) throws Exception {
		TomcatServerMain instance = new TomcatServerMain(args[0]);
		LauncherSingleton.getDefault().setLauncher(instance);
		instance.launch();
		instance.shutdownOnInput();
	}
	
	public TomcatServerMain(String string) {
		super(string);
	}
	
	@Override
	public void launch(int port) throws Exception {
		GenericServerExtensionModel model = new GenericServerExtensionModel(serverImpl.getModel(), 
				Activator.getDelegateProviderImpl(),
				Activator.getServerTypeModelStreamImpl());
		model.registerExtensions();
		super.launch(port);
	}

}
