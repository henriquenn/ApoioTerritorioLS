package br.com.nascisoft.apoioterritoriols.admin.client.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import br.com.nascisoft.apoioterritoriols.admin.vo.RelatorioVO;
import br.com.nascisoft.apoioterritoriols.login.util.StringUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.ChartArea;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.BarChart;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;
import com.google.gwt.visualization.client.visualizations.corechart.TextStyle;

public class AdminRelatorioViewImpl extends Composite implements
		AdminRelatorioView {

	private static AdminViewUiBinderUiBinder uiBinder = GWT
			.create(AdminViewUiBinderUiBinder.class);

	private Presenter presenter;
	@UiField TabLayoutPanel adminTabLayoutPanel;
	@UiField PopupPanel waitingPopUpPanel;
	@UiField VerticalPanel adminRelatorioVerticalPanel;
	@UiField ListBox adminRelatorioTipoRelatorioListBox;
	@UiField PopupPanel adminRelatorioDetalhesPopupPanel;
	@UiField HTML adminRelatorioDetalhesHTML;
	@UiField InlineLabel adminRelatorioTotalEnderecoLabel;
	@UiField TextBox adminRelatorioEmailTextBox;
	@UiField Button adminRelatorioExportButton;
	private RelatorioVO relatorio;

	@UiTemplate("AdminViewUiBinder.ui.xml")
	interface AdminViewUiBinderUiBinder extends
			UiBinder<Widget, AdminRelatorioViewImpl> {
	}

	public AdminRelatorioViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
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

	@Override
	public void initView() {
		this.selectThisTab();
		this.adminRelatorioVerticalPanel.clear();
		this.adminRelatorioTipoRelatorioListBox.clear();

		this.adminRelatorioTipoRelatorioListBox.addItem("Gráfico de Barras", "BAR");
		this.adminRelatorioTipoRelatorioListBox.addItem("Gráfico Torta", "PIE");
		this.adminRelatorioTipoRelatorioListBox.setSelectedIndex(0);
		
		this.presenter.buscarDadosRelatorio();
		
	}

	@Override
	public void selectThisTab() {
		this.adminTabLayoutPanel.selectTab(5, false);
	}

	@Override
	public void setTabSelectionEventHandler(SelectionHandler<Integer> handler) {
		this.adminTabLayoutPanel.addSelectionHandler(handler);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setDados(final RelatorioVO relatorio) {
		this.relatorio = relatorio;
		this.adminRelatorioTotalEnderecoLabel.setText(determinaTotalEnderecos()+ " endereços encontrados");

		disparaGrafico(this.adminRelatorioTipoRelatorioListBox.getValue(this.adminRelatorioTipoRelatorioListBox.getSelectedIndex()), 
				this.relatorio);
	}
	
	private int determinaTotalEnderecos() {
		int total = 0;
			for (String regiao : relatorio.keySet()) {
				total += relatorio.get(regiao).getConsolidado();
			}
		return total;
	}
	
	private void disparaGrafico(final String tipo, final RelatorioVO relatorio) {
		Runnable onLoadCallback = new Runnable() {
			public void run() {
				AbstractDataTable data = createTable(relatorio);
				if ("BAR".equals(tipo)) {
					Options opt = createBarOptions();					
					BarChart bar = new BarChart(data, opt);
					bar.addSelectHandler(createSelectHandler(bar));
					adminRelatorioVerticalPanel.add(bar);
				} else if ("PIE".equals(tipo)) {
					Options opt = createPieOptions();
					PieChart pie = new PieChart(data, opt);
					pie.addSelectHandler(createSelectHandler(pie));
					adminRelatorioVerticalPanel.add(pie);
				}
			}
		};

		// Load the visualization api, passing the onLoadCallback to be called
		// when loading is done.
		if ("BAR".equals(tipo)) {
			VisualizationUtils.loadVisualizationApi(onLoadCallback, BarChart.PACKAGE);
		} else if ("PIE".equals(tipo)) {
			VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
		}
	}

	private Options createBarOptions() {
		Options options = Options.create();
		
		TextStyle titulo = TextStyle.create();
		titulo.setFontSize(16);
		titulo.setColor("#454545");
		options.setTitleTextStyle(titulo);
		options.setTitle("Distribuição de endereços/surdos por região");
		
		ChartArea chartArea = ChartArea.create();
		chartArea.setTop(50);
		chartArea.setLeft(200);
		chartArea.setWidth(500);
		options.setChartArea(chartArea);
		
		options.setFontSize(9);
		options.setWidth(750);
		options.setHeight(700);
		
		AxisOptions axisOptions = AxisOptions.create();
		axisOptions.setTextPosition("in");
		options.setHAxisOptions(axisOptions);
		
		options.setLegend(LegendPosition.NONE);
		
		return options;
	}
	
	private Options createPieOptions() {
		Options options = Options.create();

		TextStyle titulo = TextStyle.create();
		titulo.setFontSize(16);
		titulo.setColor("#454545");
		options.setTitleTextStyle(titulo);
		options.setTitle("Distribuição de endereços/surdos por região");

		ChartArea chartArea = ChartArea.create();
		chartArea.setTop(50);
		chartArea.setWidth("60%");
		chartArea.setLeft(20);
		options.setChartArea(chartArea);

		options.setFontSize(11);
		options.setWidth(1300);
		options.setHeight(700);
		
		options.set("is3D", "true");
		options.set("pieSliceText", "value");
		
		return options;
	}

	private SelectHandler createSelectHandler(final CoreChart chart) {
		return new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {

				// May be multiple selections.
				int row = chart.getSelections().get(0).getRow();
				
				Set<String> chaves = relatorio.keySet();
				List<String> chavesOrdenadas = new ArrayList<String>();
				chavesOrdenadas.addAll(chaves);
				Collections.sort(chavesOrdenadas);
				
				adminRelatorioDetalhesPopupPanel.setPopupPosition((Window.getClientWidth()/2)-80,60);
				adminRelatorioDetalhesPopupPanel.setVisible(true);
				adminRelatorioDetalhesPopupPanel.show();
				
				adminRelatorioDetalhesHTML.setHTML(relatorio.get(chavesOrdenadas.get(row)).obtemDetalhesHTML(chavesOrdenadas.get(row)));

			}
		};
	}

	private AbstractDataTable createTable(RelatorioVO relatorio) {
		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Região");
		data.addColumn(ColumnType.NUMBER, "Endereços/Surdos na região");

		Set<String> chaves = relatorio.keySet();
		List<String> chavesOrdenadas = new ArrayList<String>();
		chavesOrdenadas.addAll(chaves);
		Collections.sort(chavesOrdenadas);

		data.addRows(chaves.size());

		for (int i = 0; i < chavesOrdenadas.size(); i++) {
			String regiao = chavesOrdenadas.get(i);
			data.setValue(i, 0, regiao);
			data.setValue(i, 1, relatorio.get(regiao).getConsolidado());
		}

		return data;
	}
	
	@UiHandler("adminRelatorioTipoRelatorioListBox")
	void onAdminRelatorioTipoRelatorioListBoxChange(ChangeEvent event) {
		this.adminRelatorioVerticalPanel.clear();
		disparaGrafico(this.adminRelatorioTipoRelatorioListBox.getValue(this.adminRelatorioTipoRelatorioListBox.getSelectedIndex()),
				this.relatorio);
	}
	
	@UiHandler("adminRelatorioExportButton")
	void onAdminRelatorioExportButtonClick(ClickEvent event) {
		if (!StringUtils.isEmpty(this.adminRelatorioEmailTextBox.getText())) {
			this.presenter.dispararExport(this.adminRelatorioEmailTextBox.getText()); 
		} else {
			Window.alert("O campo e-mail deve ser preenchido com um e-mail.");
		}
	}

}
