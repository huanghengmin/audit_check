/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-19
 * Time: 上午10:19
 * To change this template use File | Settings | File Templates.
 * 用户日志审计(用户操作审计表)
 */
Ext.onReady(function () {

    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var start = 0;
    var pageSize = 15;

    var record = new Ext.data.Record.create([
        {name: 'id', mapping: 'id'},
        {name: 'CN', mapping: 'CN'},
        {name: 'SN', mapping: 'SN'},
        {name: 'O', mapping: 'O'},
        {name: 'OU', mapping: 'OU'},
        {name: 'L', mapping: 'L'},
        {name: 'ST', mapping: 'ST'},
        {name: 'phone', mapping: 'phone'},
        {name: 'idcard', mapping: 'idcard'},
        {name: 'email', mapping: 'email'},
        {name: 'sourceip', mapping: 'sourceip'},
        {name: 'sourceport', mapping: 'sourceport'},
        {name: 'accessurl', mapping: 'accessurl'},
        {name: 'result', mapping: 'result'},
        {name: 'upbytes', mapping: 'upbytes'},
        {name: 'downbytes', mapping: 'downbytes'},
        {name: 'audittype', mapping: 'audittype'},
        {name: 'datetime', mapping: 'datetime'}
    ]);
    var proxy = new Ext.data.HttpProxy({
        url: "../../AuditClientLogAction_find.action"
    });
    var reader = new Ext.data.JsonReader({
        totalProperty: "total",
        root: "rows",
        id: 'id'
    }, record);

    var store = new Ext.data.GroupingStore({
        id: "store.info",
        proxy: proxy,
        reader: reader
    });
    store.load({
        params: {
            start: start, limit: pageSize
        }
    });
//    var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框
    var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var colM = new Ext.grid.ColumnModel([
//        boxM,
        rowNumber,
        {header: '审计时间', dataIndex: 'datetime', align: 'center', sortable: true, menuDisabled: true},
        {header: "通用名", dataIndex: "CN", align: 'center', sortable: true,  menuDisabled: true},
        {header: "序列号", dataIndex: "SN", align: 'center', sortable: true,width:130, menuDisabled: true},
        {
            header: '审计类别',
            dataIndex: 'audittype',
            renderer: show_type,
            align: 'center',
            width:200,
            sortable: true,
            menuDisabled: true
        },
        {header:'操作标记',align:'center',sortable:true,menuDisabled:true, renderer:show_info}
    ]);
    /*for(var i=6;i<14;i++){
     colM.setHidden(i,!colM.isHidden(i));                // 加载后 不显示 该项
     }
     colM.defaultSortable = true;*/
    var page_toolbar = new Ext.PagingToolbar({
        pageSize: pageSize,
        store: store,
        displayInfo: true,
        displayMsg: "显示第{0}条记录到第{1}条记录，一共{2}条",
        emptyMsg: "没有记录",
        beforePageText: "当前页",
        afterPageText: "共{0}页"
    });
    var grid_panel = new Ext.grid.GridPanel({
        id: 'grid.info',
        //title:'管理日志',
        plain: true,
        height: setHeight(),
        width: setWidth(),
        animCollapse: true,
        loadMask: {msg: '正在加载数据，请稍后...'},
        border: false,
        collapsible: false,
        cm: colM,
//        sm:boxM,
        store: store,
        stripeRows: true,
        autoExpandColumn: 'Position',
        disableSelection: true,
        bodyStyle: 'width:100%',
        enableDragDrop: true,
        selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
        viewConfig: {
            forceFit: true,
            enableRowBody: true,
            getRowClass: function (record, rowIndex, p, store) {
                return 'x-grid3-row-collapsed';
            }
        },
        tbar: ['起始日期：', {
            id: 'startDate.tb.info',
            xtype: 'datefield',
            name: 'startDate',
            emptyText: '点击输入日期',
            format: 'Y-m-d'
        }, {
            xtype: 'tbseparator'
        }, '结束日期：', {
            id: 'endDate.tb.info',
            xtype: 'datefield',
            name: 'endDate',
            emptyText: '点击输入日期',
            format: 'Y-m-d'
        }, {
            xtype: 'tbseparator'
        }, '通用名',
            new Ext.form.TextField({
                id: 'name.tb.info'
            }), {
                xtype: 'tbseparator'
            }, '日志类型', {
                id: 'audittype.tb.info',
                xtype: 'combo',
                store: new Ext.data.ArrayStore({
                    autoDestroy: true,
                    fields: ['value', 'key'],
                    data: [
                        //['006', '同一VPN隧道的建立与删除'],
                        ['008', '数据解密失败'],
                        ['009', '数据包被丢弃'],
                        ['011', '重放攻击事件'],
                        ['002', '用户鉴别事件'],
                        ['003', '授权用户一般操作'],
                        ['005', '隧道的建立与删除'],
                        ['007', '数据完整性校验失败']
                    ]
                }),
                valueField: 'value',
                displayField: 'key',
                mode: 'local',
                forceSelection: true,
                triggerAction: 'all',
                emptyText: '--请选择--',
                value: '',
                selectOnFocus: true,
                width: 150
            }, {
                xtype: 'tbseparator'
            }, {
                text: '查询',
                iconCls: 'query',
                listeners: {
                    click: function () {
                        var logLevel = Ext.fly('audittype.tb.info').dom.value == '--请选择--'
                            ? null
                            : Ext.getCmp('audittype.tb.info').getValue();
                        ;
                        var startDate = Ext.fly("startDate.tb.info").dom.value == '点击输入日期'
                            ? null
                            : Ext.fly('startDate.tb.info').dom.value;
                        var endDate = Ext.fly('endDate.tb.info').dom.value == '点击输入日期'
                            ? null
                            : Ext.fly('endDate.tb.info').dom.value;
                        var userName = Ext.fly('name.tb.info').dom.value == '--请选择--'
                            ? null
                            : Ext.getCmp('name.tb.info').getValue();
                        if (startDate != null && endDate != null) {
                            var myMask = new Ext.LoadMask(Ext.getBody(), {
                                msg: '正在处理,请稍后...',
                                removeMask: true
                            });
                            myMask.show();
                            Ext.Ajax.request({
                                url: '../../AuditAction_checkDate.action',
                                params: {startDate: startDate, endDate: endDate},
                                method: 'POST',
                                success: function (r, o) {
                                    myMask.hide();
                                    var respText = Ext.util.JSON.decode(r.responseText);
                                    var msg = respText.msg;
                                    var clear = respText.clear;
                                    if (!clear) {
                                        Ext.MessageBox.show({
                                            title: '信息',
                                            width: 280,
                                            msg: '结束时间不能早于开始时间',
                                            animEl: 'endDate.tb.info',
                                            buttons: {'ok': '确定'},
                                            icon: Ext.MessageBox.ERROR,
                                            closable: false,
                                            fn: function (e) {
                                                if (e == 'ok') {
                                                    Ext.getCmp('endDate.tb.info').setValue('');
                                                }
                                            }
                                        });
                                    } else {
                                        store.setBaseParam('startDate', startDate);
                                        store.setBaseParam('endDate', endDate);
                                        store.setBaseParam('audittype', logLevel);
                                        store.setBaseParam('name', userName);
                                        store.load({
                                            params: {
                                                start: start,
                                                limit: pageSize
                                            }
                                        });
                                    }
                                }
                            });
                        } else {
                            store.setBaseParam('startDate', startDate);
                            store.setBaseParam('endDate', endDate);
                            store.setBaseParam('audittype', logLevel);
                            store.setBaseParam('name', userName);
                            store.load({
                                params: {
                                    start: start,
                                    limit: pageSize
                                }
                            });
                        }

                    }
                }
            }],
        view: new Ext.grid.GroupingView({
            forceFit: true,
            groupingTextTpl: '{text}({[values.rs.length]}条记录)'
        }),
        bbar: page_toolbar
    });
    var port = new Ext.Viewport({
        layout: 'fit',
        renderTo: Ext.getBody(),
        items: [grid_panel]
    });
});


function show_type(value, p, r) {
    if (value == "002") {
        //return '<span style="color:green;">用户鉴别失败事件</span>';
        return '<div style="border:1px solid green;background-color:green;">用户鉴别失败事件</div>';
    } else if (value == "003") {
        //return '<span style="color:green;">授权用户一般操作</span>';
        return '<div style="border:1px solid green;background-color:green;">授权用户一般操作</div>';
    } else if (value == "005") {
        if(r.get("result")=="1"){
            return '<div style="border:1px solid green;background-color:green;">隧道的建立</div>';
        }else{
            return '<div style="border:1px solid green;background-color:green;">隧道的删除</div>';
        }
    }/* else if (value == "006") {
        return '<span style="color:green;">同一VPN隧道的建立与删除</span>';
    }*/ else if (value == "007") {
        //return '<span style="color:green;">数据完整性校验失败</span>';
        return '<div style="border:1px solid green;background-color:green;">数据完整性校验失败</div>';
    } else if (value == "008") {
        //return '<span style="color:green;">数据解密失败</span>';
        return '<div style="border:1px solid green;background-color:green;">数据解密失败</div>';
    } else if (value == "009") {
        //return '<span style="color:green;">数据包被丢弃</span>';
        return '<div style="border:1px solid green;background-color:green;">数据包被丢弃</div>';
    } else if (value == "011") {
        //return '<span style="color:green;">重放攻击事件</span>';
        return '<div style="border:1px solid green;background-color:green;">重放攻击事件</div>';
    }
};

function show_info(value, p, r) {
    return String.format(
        '<a id="view_client.info" href="javascript:void(0);" onclick="view_info();return false;" style="color: green;">查看</a> &nbsp;&nbsp;&nbsp;'
    );
};

function view_info(){
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();

    var audit_type = recode.get("audittype");
    var audit_result = null;

    if(audit_type=="002"){
        if(recode.get("result")=="1") {
            audit_result = "成功";
            audit_type = "用户鉴别成功";
        }else {
            audit_result = "失败";
            audit_type = "用户鉴别失败";
        }
    }else if(audit_type=="003"){
        if(recode.get("result")=="1") {
            audit_result = "成功";
            audit_type = "授权用户一般操作";
        }else {
            audit_result = "失败";
            audit_type = "授权用户一般操作";
        }
    }else if(audit_type=="005"){
        if(recode.get("result")=="1") {
            audit_result = "隧道建立";
            audit_type = "隧道的建立";
        }else {
            audit_result = "隧道删除";
            audit_type = "隧道的删除";
        }
    }else if(audit_type=="007"){
        if(recode.get("result")=="1") {
            audit_result = "成功";
            audit_type = "数据完整性校验";
        }else {
            audit_result = "失败";
            audit_type = "数据完整性校验";
        }
    }else if(audit_type=="008"){
        if(recode.get("result")=="1") {
            audit_result = "成功";
            audit_type = "数据解密";
        }else {
            audit_result = "失败";
            audit_type = "数据解密";
        }
    }else if(audit_type=="009"){
        if(recode.get("result")=="1") {
            audit_result = "成功";
            audit_type = "数据包被丢弃";
        }else {
            audit_result = "失败";
            audit_type = "数据包被丢弃";
        }
    }else if(audit_type=="011"){
        if(recode.get("result")=="1") {
            audit_result = "发生重发";
            audit_type = "重放攻击事件";
        }else {
            audit_result = "未发生";
            audit_type = "重放攻击事件";
        }
    }
    var formPanel = new Ext.form.FormPanel({
        frame: true,
        width: 600,
        autoScroll: true,
        baseCls: 'x-plain',
        labelWidth: 120,
        labelAlign: 'right',
        defaultWidth: 300,
        layout: 'form',
        border: false,
        defaults: {
            width: 250
        },
        items:[
            new Ext.form.DisplayField({
                fieldLabel: '通用名',
                value: recode.get("CN")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '证书序列号',
                value: recode.get("SN")
            }),
           /* new Ext.form.DisplayField({
                fieldLabel: '组织',
                value: recode.get("O")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '机构',
                value: recode.get("OU")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '市',
                value: recode.get("L")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '省',
                value: recode.get("ST")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '电话',
                value: recode.get("phone")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '身份证',
                value: recode.get("idcard")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '邮件',
                value: recode.get("email")
            }),*/
            new Ext.form.DisplayField({
                fieldLabel: '源IP',
                value: recode.get("sourceip")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '源端口',
                value: recode.get("sourceport")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '访问地址',
                value: recode.get("accessurl")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '结果',
                value: audit_result
            }),
           /* new Ext.form.DisplayField({
                fieldLabel: '上传流量',
                value: recode.get("upbytes")
            }),
            new Ext.form.DisplayField({
                fieldLabel: '下载流量',
                value: recode.get("downbytes")
            }),*/
            new Ext.form.DisplayField({
                fieldLabel: '审计类型',
                value: audit_type
            }),
            new Ext.form.DisplayField({
                fieldLabel: '时间',
                value: recode.get("datetime")
            })
        ]
    });

    var select_Win = new Ext.Window({
        title:"审计详细",
        width:600,
        layout:'fit',
        height:300,
        modal:true,
        items:formPanel
    });
    select_Win.show();
};

function setHeight() {
    var h = document.body.clientHeight - 8;
    return h;
}

function setWidth() {
    return document.body.clientWidth - 8;
}
