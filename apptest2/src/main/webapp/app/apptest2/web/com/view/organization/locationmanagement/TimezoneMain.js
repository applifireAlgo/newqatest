Ext.define('Apptest2.apptest2.web.com.view.organization.locationmanagement.TimezoneMain', {
     "xtype": "timezone",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "TimezoneMainController",
     "restURL": "/Timezone",
     "defaults": {
          "split": true
     },
     "requires": ["Apptest2.apptest2.shared.com.model.organization.locationmanagement.TimezoneModel", "Apptest2.apptest2.web.com.controller.organization.locationmanagement.TimezoneMainController", "Apptest2.apptest2.shared.com.viewmodel.organization.locationmanagement.TimezoneViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Timezone",
               "name": "TimezoneTreeContainer",
               "itemId": "TimezoneTreeContainer",
               "restURL": "/Timezone",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "TimezoneTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": [{
                         "name": "utcdifference",
                         "itemId": "utcdifference",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "UTC Difference",
                         "margin": "5 5 5 5",
                         "fieldLabel": "UTC Difference",
                         "fieldId": "0928F39D-ECC7-4DDC-9F64-3196E83CF945",
                         "minValue": "0",
                         "maxValue": "11",
                         "bindable": "utcdifference"
                    }]
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Timezone",
                    "title": "Timezone",
                    "name": "Timezone",
                    "itemId": "TimezoneForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "timeZoneId",
                         "itemId": "timeZoneId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Time Zone Id",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Time Zone Id<font color='red'> *<\/font>",
                         "fieldId": "61B695D3-7E23-481B-88DB-A0222BFB0939",
                         "minLength": "0",
                         "maxLength": "64",
                         "hidden": true,
                         "value": "",
                         "bindable": "timeZoneId"
                    }, {
                         "name": "utcdifference",
                         "itemId": "utcdifference",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "UTC Difference",
                         "margin": "5 5 5 5",
                         "fieldLabel": "UTC Difference<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "0928F39D-ECC7-4DDC-9F64-3196E83CF945",
                         "minValue": "0",
                         "maxValue": "11",
                         "bindable": "utcdifference",
                         "columnWidth": 0.5
                    }, {
                         "name": "gmtLabel",
                         "itemId": "gmtLabel",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "GMT",
                         "margin": "5 5 5 5",
                         "fieldLabel": "GMT",
                         "fieldId": "F30AB3E0-57F7-429C-A9EE-E8E0C073E9DA",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "gmtLabel",
                         "columnWidth": 0.5
                    }, {
                         "name": "timeZoneLabel",
                         "itemId": "timeZoneLabel",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Time Zone",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Time Zone",
                         "fieldId": "B79FB691-E789-4E96-854B-3B26F346951C",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "timeZoneLabel",
                         "columnWidth": 0.5
                    }, {
                         "name": "country",
                         "itemId": "country",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Country",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Country",
                         "fieldId": "E7E82EB8-5347-43F9-BB46-43091D0B0355",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "country",
                         "columnWidth": 0.5
                    }, {
                         "name": "cities",
                         "itemId": "cities",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "City",
                         "margin": "5 5 5 5",
                         "fieldLabel": "City",
                         "fieldId": "FD8428E1-8256-4D6E-BBDC-BAAE09407B6B",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "cities",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "72920342-882C-475C-BC26-6087CB74CBB4",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 165,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 165,
                              "customId": 253
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 165,
                              "customId": 254,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 165,
                              "customId": 255,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Timezone",
                    "title": "Details Grid",
                    "name": "TimezoneGrid",
                    "itemId": "TimezoneGrid",
                    "restURL": "/Timezone",
                    "store": [],
                    "columns": [{
                         "header": "Time Zone Id",
                         "dataIndex": "timeZoneId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "UTC Difference",
                         "dataIndex": "utcdifference",
                         "flex": 1
                    }, {
                         "header": "GMT",
                         "dataIndex": "gmtLabel",
                         "flex": 1
                    }, {
                         "header": "Time Zone",
                         "dataIndex": "timeZoneLabel",
                         "flex": 1
                    }, {
                         "header": "Country",
                         "dataIndex": "country",
                         "flex": 1
                    }, {
                         "header": "City",
                         "dataIndex": "cities",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Timezone",
               "title": "Timezone",
               "name": "Timezone",
               "itemId": "TimezoneForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "timeZoneId",
                    "itemId": "timeZoneId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Time Zone Id",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Time Zone Id<font color='red'> *<\/font>",
                    "fieldId": "61B695D3-7E23-481B-88DB-A0222BFB0939",
                    "minLength": "0",
                    "maxLength": "64",
                    "hidden": true,
                    "value": "",
                    "bindable": "timeZoneId"
               }, {
                    "name": "utcdifference",
                    "itemId": "utcdifference",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "UTC Difference",
                    "margin": "5 5 5 5",
                    "fieldLabel": "UTC Difference<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "0928F39D-ECC7-4DDC-9F64-3196E83CF945",
                    "minValue": "0",
                    "maxValue": "11",
                    "bindable": "utcdifference",
                    "columnWidth": 0.5
               }, {
                    "name": "gmtLabel",
                    "itemId": "gmtLabel",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "GMT",
                    "margin": "5 5 5 5",
                    "fieldLabel": "GMT",
                    "fieldId": "F30AB3E0-57F7-429C-A9EE-E8E0C073E9DA",
                    "minLength": "0",
                    "maxLength": "256",
                    "bindable": "gmtLabel",
                    "columnWidth": 0.5
               }, {
                    "name": "timeZoneLabel",
                    "itemId": "timeZoneLabel",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Time Zone",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Time Zone",
                    "fieldId": "B79FB691-E789-4E96-854B-3B26F346951C",
                    "minLength": "0",
                    "maxLength": "256",
                    "bindable": "timeZoneLabel",
                    "columnWidth": 0.5
               }, {
                    "name": "country",
                    "itemId": "country",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Country",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Country",
                    "fieldId": "E7E82EB8-5347-43F9-BB46-43091D0B0355",
                    "minLength": "0",
                    "maxLength": "256",
                    "bindable": "country",
                    "columnWidth": 0.5
               }, {
                    "name": "cities",
                    "itemId": "cities",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "City",
                    "margin": "5 5 5 5",
                    "fieldLabel": "City",
                    "fieldId": "FD8428E1-8256-4D6E-BBDC-BAAE09407B6B",
                    "minLength": "0",
                    "maxLength": "256",
                    "bindable": "cities",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "72920342-882C-475C-BC26-6087CB74CBB4",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 165,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 165,
                         "customId": 253
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 165,
                         "customId": 254,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 165,
                         "customId": 255,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});