Ext.define('Apptest2.apptest2.shared.com.model.appbasicsetup.usermanagement.SessionDataModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "dataId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "customerId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "userId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "sessionKey",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "dataType",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "numberValue",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "dateTimeValue",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "stringValue",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "booleanValue",
          "type": "boolean",
          "defaultValue": ""
     }, {
          "name": "jsonValue",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "appSessionId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});