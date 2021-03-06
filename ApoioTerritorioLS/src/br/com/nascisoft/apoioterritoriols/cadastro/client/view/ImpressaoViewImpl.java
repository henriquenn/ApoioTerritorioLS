package br.com.nascisoft.apoioterritoriols.cadastro.client.view;

import java.util.List;

import br.com.nascisoft.apoioterritoriols.login.entities.Cidade;
import br.com.nascisoft.apoioterritoriols.login.entities.Mapa;
import br.com.nascisoft.apoioterritoriols.login.entities.Regiao;
import br.com.nascisoft.apoioterritoriols.login.util.StringUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImpressaoViewImpl extends Composite implements
		ImpressaoView {
	
	private static CadastroSurdoViewUiBinderUiBinder uiBinder = 
		GWT.create(CadastroSurdoViewUiBinderUiBinder.class);
	private Presenter presenter;
	
	@UiField TabLayoutPanel cadastroSurdoTabLayoutPanel;
	@UiField ListBox pesquisaImpressaoCidadeListBox;
	@UiField ListBox pesquisaImpressaoRegiaoListBox;
	@UiField ListBox pesquisaImpressaoMapaListBox;
	@UiField PopupPanel waitingPopUpPanel;
	
	private Long identificadorMapaAtual = null;
	
	@UiTemplate("CadastroViewUiBinder.ui.xml")
	interface CadastroSurdoViewUiBinderUiBinder 
		extends UiBinder<Widget, ImpressaoViewImpl> {
	}

	public ImpressaoViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void initView() {
		this.selectThisTab();	
		this.limparPesquisa();
		if (this.presenter.getLoginInformation().isAdmin()) {
			boolean existeAdmin = true;
			try {
				this.cadastroSurdoTabLayoutPanel.getTabWidget(4);
			} catch (AssertionError ex) {
				existeAdmin = false;
			} catch (IndexOutOfBoundsException ex) {
				existeAdmin = false;
			}
			if (!existeAdmin) {
				this.cadastroSurdoTabLayoutPanel.add(new HTML(""), new HTML("<a href=/Admin.html>Admin</a>"));
			}
		}
	}
	
	@Override
	public void selectThisTab() {
		this.cadastroSurdoTabLayoutPanel.selectTab(2, false);
	}
	
	@Override
	public void showWaitingPanel() {
		waitingPopUpPanel.setVisible(true);
		waitingPopUpPanel.show();
	}
	
	@Override
	public void hideWaitingPanel() {
		waitingPopUpPanel.hide();
		waitingPopUpPanel.setVisible(false);
	}
	
	private void limparPesquisa() {
		this.pesquisaImpressaoCidadeListBox.setSelectedIndex(0);
		this.pesquisaImpressaoRegiaoListBox.setSelectedIndex(0);
		this.pesquisaImpressaoRegiaoListBox.clear();
		this.pesquisaImpressaoRegiaoListBox.addItem("-- Escolha uma região --", "");
		this.pesquisaImpressaoRegiaoListBox.setEnabled(false);
		this.pesquisaImpressaoMapaListBox.clear();
		this.pesquisaImpressaoMapaListBox.addItem("-- Escolha um mapa --", "");
		this.pesquisaImpressaoMapaListBox.setSelectedIndex(0);
		this.pesquisaImpressaoMapaListBox.setEnabled(false);
	}


	@Override
	public void setCidadeList(List<Cidade> cidades) {
		this.pesquisaImpressaoCidadeListBox.clear();
		if (cidades.size() > 1) {
			this.pesquisaImpressaoCidadeListBox.addItem("-- Escolha uma cidade --", "");
			
			this.pesquisaImpressaoRegiaoListBox.clear();
			this.pesquisaImpressaoRegiaoListBox.addItem("-- Escolha uma região --", "");
			
			this.pesquisaImpressaoMapaListBox.clear();
			this.pesquisaImpressaoMapaListBox.addItem("-- Escolha um mapa --", "");	
		} else {
			this.presenter.onPesquisaCidadeListBoxChange(cidades.get(0).getId());
		}
		for (Cidade cidade : cidades) {
			this.pesquisaImpressaoCidadeListBox.addItem(cidade.getNome(), cidade.getId().toString());
		}	
	}
	
	@Override
	public void setRegiaoList(List<Regiao> regioes) {
		this.pesquisaImpressaoRegiaoListBox.setEnabled(true);
		this.pesquisaImpressaoRegiaoListBox.clear();
		this.pesquisaImpressaoRegiaoListBox.addItem("-- Escolha uma região --", "");

		this.pesquisaImpressaoMapaListBox.clear();
		this.pesquisaImpressaoMapaListBox.addItem("-- Escolha um mapa --", "");
		
		for (Regiao regiao : regioes) {
			this.pesquisaImpressaoRegiaoListBox.addItem(regiao.getLetra() + " - " + regiao.getNome(), regiao.getId().toString());
		}
	}

	@Override
	public void setMapaList(List<Mapa> mapas) {
		this.pesquisaImpressaoMapaListBox.setEnabled(true);
		this.pesquisaImpressaoMapaListBox.clear();
		this.pesquisaImpressaoMapaListBox.addItem("-- Escolha um mapa --", "");
		for (Mapa mapa : mapas) {
			this.pesquisaImpressaoMapaListBox.addItem(mapa.getNome(), mapa.getId().toString());
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	@Override
	public void setTabSelectionEventHandler(SelectionHandler<Integer> handler) {
		this.cadastroSurdoTabLayoutPanel.addSelectionHandler(handler);
	}
	

	@UiHandler("pesquisaImpressaoCidadeListBox")
	void onPesquisaImpressaoCidadeListBoxChange(ChangeEvent event) {
		String value = this.pesquisaImpressaoCidadeListBox.getValue(this.pesquisaImpressaoCidadeListBox.getSelectedIndex());
		if (!StringUtils.isEmpty(value)) {
			this.presenter.onPesquisaCidadeListBoxChange(Long.valueOf(value));
		} 
		
		this.pesquisaImpressaoRegiaoListBox.setEnabled(false);
		this.pesquisaImpressaoRegiaoListBox.setSelectedIndex(0);
		
		this.pesquisaImpressaoMapaListBox.setEnabled(false);
		this.pesquisaImpressaoMapaListBox.setSelectedIndex(0);
	}

	@UiHandler("pesquisaImpressaoRegiaoListBox")
	void onPesquisaImpressaoRegiaoListBox(ChangeEvent event) {
		if (!this.pesquisaImpressaoRegiaoListBox.getValue(this.pesquisaImpressaoRegiaoListBox.getSelectedIndex()).isEmpty()) {
			this.pesquisaImpressaoMapaListBox.setEnabled(true);
			this.presenter.onPesquisaRegiaoListBoxChange(
					Long.valueOf(pesquisaImpressaoRegiaoListBox.getValue(pesquisaImpressaoRegiaoListBox.getSelectedIndex())));
		} else {
			this.pesquisaImpressaoMapaListBox.setEnabled(false);
			this.pesquisaImpressaoMapaListBox.setSelectedIndex(0);
		}
	}
	
	@UiHandler("pesquisaImpressaoMapaListBox")
	void onPesquisaImpressaoMapaListBox(ChangeEvent event) {
		if (presenter != null) {
			String mapa = this.pesquisaImpressaoMapaListBox.getValue(this.pesquisaImpressaoMapaListBox.getSelectedIndex());
			if (!StringUtils.isEmpty(mapa)) {
				this.identificadorMapaAtual = Long.valueOf(mapa);
				this.presenter.abrirImpressao(identificadorMapaAtual, true);
			}
		}
	}

}
