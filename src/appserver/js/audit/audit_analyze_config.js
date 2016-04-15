Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var record = new Ext.data.Record.create([
        {name: 'audit_warn_number', mapping: 'audit_warn_number'},
        {name: 'verity_warn_number', mapping: 'verity_warn_number'},
        {name: 'client_warn_number', mapping: 'client_warn_number'},
        {name: 'admin_warn_number', mapping: 'admin_warn_number'},
        {name: 'build_warn_number', mapping: 'build_warn_number'},
        {name: 'full_warn_number', mapping: 'full_warn_number'},
        {name: 'decode_warn_number', mapping: 'decode_warn_number'},
        {name: 'discard_warn_number', mapping: 'discard_warn_number'},
        {name: 'storage_warn_number', mapping: 'storage_warn_number'},
        {name: 'replay_warn_number', mapping: 'replay_warn_number'},

        {name: 'audit_police_number', mapping: 'audit_police_number'},
        {name: 'verity_police_number', mapping: 'verity_police_number'},
        {name: 'client_police_number', mapping: 'client_police_number'},
        {name: 'admin_police_number', mapping: 'admin_police_number'},
        {name: 'build_police_number', mapping: 'build_police_number'},
        {name: 'full_police_number', mapping: 'full_police_number'},
        {name: 'decode_police_number', mapping: 'decode_police_number'},
        {name: 'discard_police_number', mapping: 'discard_police_number'},
        {name: 'storage_police_number', mapping: 'storage_police_number'},
        {name: 'replay_police_number', mapping: 'replay_police_number'}
    ]);

    var proxy = new Ext.data.HttpProxy({
        url: "../../AuditReportConfigAction_find.action"
    });

    var reader = new Ext.data.JsonReader({
        totalProperty: "totalCount",
        root: "root"
    }, record);

    var store = new Ext.data.GroupingStore({
        id: "store.info",
        proxy: proxy,
        reader: reader
    });

    store.load();
    store.on('load', function () {
        var audit_warn_number = store.getAt(0).get('audit_warn_number');
        var verity_warn_number = store.getAt(0).get('verity_warn_number');
        var client_warn_number = store.getAt(0).get('client_warn_number');
        var admin_warn_number = store.getAt(0).get('admin_warn_number');
        var build_warn_number = store.getAt(0).get('build_warn_number');
        var full_warn_number = store.getAt(0).get('full_warn_number');
        var decode_warn_number = store.getAt(0).get('decode_warn_number');
        var discard_warn_number = store.getAt(0).get('discard_warn_number');
        var storage_warn_number = store.getAt(0).get('storage_warn_number');
        var replay_warn_number = store.getAt(0).get('replay_warn_number');

        var audit_police_number = store.getAt(0).get('audit_police_number');
        var verity_police_number = store.getAt(0).get('verity_police_number');
        var client_police_number = store.getAt(0).get('client_police_number');
        var admin_police_number = store.getAt(0).get('admin_police_number');
        var build_police_number = store.getAt(0).get('build_police_number');
        var full_police_number = store.getAt(0).get('full_police_number');
        var decode_police_number = store.getAt(0).get('decode_police_number');
        var discard_police_number = store.getAt(0).get('discard_police_number');
        var storage_police_number = store.getAt(0).get('storage_police_number');
        var replay_police_number = store.getAt(0).get('replay_police_number');


        Ext.getCmp('analyze_audit_warn_number').setValue(audit_warn_number);
        Ext.getCmp('analyze_verity_warn_number').setValue(verity_warn_number);
        Ext.getCmp('analyze_client_warn_number').setValue(client_warn_number);
        Ext.getCmp('analyze_admin_warn_number').setValue(admin_warn_number);
        Ext.getCmp('analyze_build_warn_number').setValue(build_warn_number);
        Ext.getCmp('analyze_full_warn_number').setValue(full_warn_number);
        Ext.getCmp('analyze_decode_warn_number').setValue(decode_warn_number);
        Ext.getCmp('analyze_discard_warn_number').setValue(discard_warn_number);
        Ext.getCmp('analyze_storage_warn_number').setValue(storage_warn_number);
        Ext.getCmp('analyze_replay_warn_number').setValue(replay_warn_number);

        Ext.getCmp('analyze_audit_police_number').setValue(audit_police_number);
        Ext.getCmp('analyze_verity_police_number').setValue(verity_police_number);
        Ext.getCmp('analyze_client_police_number').setValue(client_police_number);
        Ext.getCmp('analyze_admin_police_number').setValue(admin_police_number);
        Ext.getCmp('analyze_build_police_number').setValue(build_police_number);
        Ext.getCmp('analyze_full_police_number').setValue(full_police_number);
        Ext.getCmp('analyze_decode_police_number').setValue(decode_police_number);
        Ext.getCmp('analyze_discard_police_number').setValue(discard_police_number);
        Ext.getCmp('analyze_storage_police_number').setValue(storage_police_number);
        Ext.getCmp('analyze_replay_police_number').setValue(replay_police_number);

    });




    var formPanel = new Ext.form.FormPanel({
        frame:true,
        autoScroll:true,
        layout:'column',
        title: '审计分析配置',
        border:false,
        items:[
            {
                plain: true,
                columnWidth: .5,
                border: false,
                layout: 'form',
                items: [{
                    plain: true,
                    labelAlign: 'right',
                    labelWidth: 180,
                    defaultType: 'textfield',
                    border: false,
                    layout: 'form',
                    defaults: {
                        anchor: "80%",
                        allowBlank: false,
                        blankText: '该项不能为空！'
                    },
                    items: [{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '审计开启关闭警告值',
                        id: 'analyze_audit_warn_number',
                        name: 'audit_warn_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '鉴别失败事件警告值',
                        id: 'analyze_verity_warn_number',
                        name: 'verity_warn_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '终端一般操作警告值',
                        id: 'analyze_client_warn_number',
                        name: 'client_warn_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '管理员操作警告值',
                        id: 'analyze_admin_warn_number',
                        name: 'admin_warn_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '隧道建立删除警告值',
                        id: 'analyze_build_warn_number',
                        name: 'build_warn_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '完整性校验失败警告值',
                        id: 'analyze_full_warn_number',
                        name: 'full_warn_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '数据解密失败警告值',
                        id: 'analyze_decode_warn_number',
                        name: 'decode_warn_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '包被丢弃事件警告值',
                        id: 'analyze_discard_warn_number',
                        name: 'discard_warn_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '日志存储失败警告值',
                        id: 'analyze_storage_warn_number',
                        name: 'storage_warn_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '重放数据攻击警告值',
                        id: 'analyze_replay_warn_number',
                        name: 'replay_warn_number'
                    }]
                }]
            },
            {
                plain: true,
                columnWidth: .5,
                border: false,
                layout: 'form',
                items: [{
                    plain: true,
                    defaultType: 'textfield',
                    columnWidth: .5,
                    labelAlign: 'right',
                    labelWidth: 180,
                    border: false,
                    layout: 'form',
                    defaults: {
                        anchor: "80%",
                        allowBlank: false,
                        blankText: '该项不能为空！'
                    },
                    items: [{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '审计开启关闭告警值',
                        id: 'analyze_audit_police_number',
                        name: 'audit_police_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '鉴别失败事件告警值',
                        id: 'analyze_verity_police_number',
                        name: 'verity_police_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '终端一般操作告警值',
                        id: 'analyze_client_police_number',
                        name: 'client_police_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '管理员操作告警值',
                        id: 'analyze_admin_police_number',
                        name: 'admin_police_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '隧道建立删除告警值',
                        id: 'analyze_build_police_number',
                        name: 'build_police_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '完整性校验失败告警值',
                        id: 'analyze_full_police_number',
                        name: 'full_police_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '数据解密失败告警值',
                        id: 'analyze_decode_police_number',
                        name: 'decode_police_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '包被丢弃事件告警值',
                        id: 'analyze_discard_police_number',
                        name: 'discard_police_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '日志存储失败告警值',
                        id: 'analyze_storage_police_number',
                        name: 'storage_police_number'
                    },{
                        xtype: 'numberfield',
                        value:0,
                        allowDecimals: false,               //不允许输入小数
                        nanText: '请输入有效整数',           //无效数字提示
                        allowNegative: false,                //不允许输入负数
                        fieldLabel: '重放数据攻击告警值',
                        id: 'analyze_replay_police_number',
                        name: 'replay_police_number'
                    }]
                }]
            }
        ],
        buttons: [
            '->',
            {
                id: 'insert_win.info',
                text: '保存配置',
                handler: function () {
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url: "../../AuditReportConfigAction_save.action",
                            method: 'POST',
                            waitTitle: '系统提示',
                            waitMsg: '正在连接...',
                            success: function () {
                                Ext.MessageBox.show({
                                    title: '信息',
                                    width: 250,
                                    msg: '保存成功,点击返回页面!',
                                    buttons: Ext.MessageBox.OK,
                                    buttons: {'ok': '确定'},
                                    icon: Ext.MessageBox.INFO,
                                    closable: false
                                });
                            },
                            failure: function () {
                                Ext.MessageBox.show({
                                    title: '信息',
                                    width: 250,
                                    msg: '保存失败，请与管理员联系!',
                                    buttons: Ext.MessageBox.OK,
                                    buttons: {'ok': '确定'},
                                    icon: Ext.MessageBox.ERROR,
                                    closable: false
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title: '信息',
                            width: 200,
                            msg: '请填写完成再提交!',
                            buttons: Ext.MessageBox.OK,
                            buttons: {'ok': '确定'},
                            icon: Ext.MessageBox.ERROR,
                            closable: false
                        });
                    }
                }
            },
            {
                id: 'reset.info',
                text: '重置配置',
                handler: function () {
                    formPanel.getForm().reset();
                }
            }
        ]
    });

    var panel = new Ext.Panel({
        plain: true,
        width: 800,
        border: false,

        items: [formPanel]
    });
    new Ext.Viewport({
        layout: 'fit',
        renderTo: Ext.getBody(),
        autoScroll: true,

        items: [{
            frame: true,
            autoScroll: true,
            items: [panel]
        }]
    });

});


