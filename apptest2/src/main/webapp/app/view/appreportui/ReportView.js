Ext.define('Apptest2.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Apptest2.view.appreportui.ReportViewController',
	            'Apptest2.view.appreportui.datagrid.DataGridPanel',
	            'Apptest2.view.appreportui.datagrid.DataGridView',
	            'Apptest2.view.appreportui.querycriteria.QueryCriteriaView',
	            'Apptest2.view.appreportui.chart.ChartView',
	            'Apptest2.view.appreportui.datapoint.DataPointView',
	            'Apptest2.view.googlemaps.map.MapPanel',
	            'Apptest2.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
