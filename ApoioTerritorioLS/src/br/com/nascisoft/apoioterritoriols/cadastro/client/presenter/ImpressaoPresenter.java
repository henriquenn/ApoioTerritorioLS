package br.com.nascisoft.apoioterritoriols.cadastro.client.presenter;

import java.util.List;
import java.util.logging.Level;

import br.com.nascisoft.apoioterritoriols.cadastro.client.CadastroServiceAsync;
import br.com.nascisoft.apoioterritoriols.cadastro.client.event.AbrirImpressaoMapaEvent;
import br.com.nascisoft.apoioterritoriols.cadastro.client.view.CadastroView;
import br.com.nascisoft.apoioterritoriols.cadastro.client.view.ImpressaoView;
import br.com.nascisoft.apoioterritoriols.cadastro.vo.SurdoVO;

import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ImpressaoPresenter extends AbstractPresenter
		implements ImpressaoView.Presenter {
	
	private final ImpressaoView view;
	
	public ImpressaoPresenter(CadastroServiceAsync service,
			HandlerManager eventBus, ImpressaoView view) {
		super(service, eventBus);
		this.view = view;
		this.view.setPresenter(this);
	}

	@Override
	CadastroView getView() {
		return this.view;
	}

	@Override
	public void setTabSelectionEventHandler(SelectionHandler<Integer> handler) {
		this.view.setTabSelectionEventHandler(handler);
	}

	@Override
	public void onAbrirImpressao(Long identificadorMapa) {
		service.obterSurdosCompletos(null, null, identificadorMapa, new AsyncCallback<List<SurdoVO>>() {
			
			@Override
			public void onSuccess(List<SurdoVO> result) {
				view.onAbrirImpressao(result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				logger.log(Level.SEVERE, "Falha ao obter informa��es para abrir o mapa", caught);
				Window.alert("Falha ao obter informacoes para abrir o mapa. \n" + caught.getMessage());				
			}
		});
	}

	@Override
	public void abrirImpressao(Long identificadorMapa) {
		eventBus.fireEvent(new AbrirImpressaoMapaEvent(identificadorMapa));
	}

}