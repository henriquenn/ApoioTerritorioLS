package br.com.nascisoft.apoioterritoriols.cadastro.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class CadastroEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		//TODO: autentica��o
		CadastroServiceAsync service = GWT.create(CadastroService.class);
		HandlerManager eventBus = new HandlerManager(null);
		CadastroController controller = new CadastroController(service, eventBus);
		controller.go(RootLayoutPanel.get());
	}

}
