Ext.onReady(function () {

    var store = new Ext.data.JsonStore({
        url: "../../AuditReportAction_getReportCount.action",
        root: 'rows',
        totalProperty: "total",
        fields: ['name', 'warn_count','police_count','count', 'code']
    });

    new Ext.Viewport({
        layout: 'fit',
        autoScroll: true,
        renderTo: Ext.getBody(),
        items: [{
            xtype: 'linechart',
            url: '../../js/ext/resources/charts.swf',
            store: store,
            //定义tip内容
            tipRenderer: function (chart, record) {
        if ("审计开启与关闭" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '审计开启与关闭事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,可能存在安全隐患！";
            }else if (record.data.count > record.data.warn_count) {
                return '审计开启与关闭事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,可能存在安全隐患！";
            } else {
                return '审计开启与关闭事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        } else if ("鉴别失败" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '鉴别失败事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,可能存在终端攻击事件！";
            } else if (record.data.count > record.data.warn_count) {
                return '鉴别失败事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,可能存在终端攻击事件！";
            } else {
                return '鉴别失败事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        } else if ("终端一般操作" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '终端一般操作事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,可能存在安全隐患！";
            }else if (record.data.count > record.data.warn_count) {
                return '终端一般操作事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,可能存在安全隐患！";
            } else {
                return '终端一般操作事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        } else if ("管理员操作" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '管理员操作事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,可能存在安全隐患！";
            }else if (record.data.count > record.data.warn_count) {
                return '管理员操作事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,可能存在安全隐患！";
            } else {
                return '管理员操作事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        } else if ("隧道建立删除" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '隧道建立删除事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,可能存在安全隐患！";
            }else if (record.data.count > record.data.warn_count) {
                return '隧道建立删除事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,可能存在安全隐患！";
            }else {
                return '隧道建立删除事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        }/*else if("同一隧道建立删除"==record.data.name){
         return  '同一隧道建立删除事件发生次数： '
         + Ext.util.Format.number(record.data.count, '0,0');
         }*/ else if ("完整性校验失败" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '完整性校验失败事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,可能需要确定发生原因！";
            }else if (record.data.count > record.data.warn_count) {
                return '完整性校验失败事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,可能需要确定发生原因！";
            } else {
                return '完整性校验失败事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        } else if ("数据解密失败" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '数据解密失败事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,可能需要确定发生原因！";
            }else if (record.data.count > record.data.warn_count) {
                return '数据解密失败事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,可能需要确定发生原因！";
            } else {
                return '数据解密失败事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        } else if ("包被丢弃事件" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '包被丢弃事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,可能存在终端非法攻击事件！";
            }else if (record.data.count > record.data.warn_count) {
                return '包被丢弃事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,可能存在终端非法攻击事件！";
            } else {
                return '包被丢弃事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        } else if ("日志存储失败" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '日志存储失败： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,存储系统可能发生异常！";
            }else if (record.data.count > record.data.warn_count) {
                return '日志存储失败： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,存储系统可能发生异常！";
            } else {
                return '日志存储失败： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        } else if ("重放数据攻击" == record.data.name) {
            if (record.data.count > record.data.police_count) {
                return '重放数据攻击事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于告警次数:"+record.data.police_count+"次,请确定重放攻击来源,发生攻击事件！";
            }else if (record.data.count > record.data.warn_count) {
                return '重放数据攻击事件发生次数： ' + Ext.util.Format.number(record.data.count, '0,0') + " 大于警告次数:"+record.data.warn_count+"次,请确定重放攻击来源,发生攻击事件！";
            } else {
                return '重放数据攻击事件发生次数： '
                    + Ext.util.Format.number(record.data.count, '0,0');
            }
        }
        /*审计开启与关闭
         鉴别失败
         一般操作
         管理员操作
         隧道建立删除
         同一隧道建立删除
         完整性校验失败
         数据解密失败
         包被丢弃事件
         日志存储失败
         重放数据攻击*/
    },
            //定义两个图表,一个是柱状图,一个是折线图
            series: [{
                type: 'column',
                displayName: '事件',
                xField: 'name',
                yField: 'count',
                style: {
                    color: 0x38B8BF,
                    size: 20
                }
            }, {
                type: 'line',
                displayName: '警告',
                xField: 'name',
                yField: 'warn_count',
                style: {
                    color: 0xFDFF44
                }
            }, {
                type: 'line',
                displayName: '告警',
                xField: 'name',
                yField: 'police_count',
                style: {
                    color: 0xFF524F
                }
            }],
            //定义图表样式
            chartStyle: {
                legend: {
                    display: "top"
                },
                xAxis: {
                    color: 0x69aBc8,
                    majorTicks: {color: 0x69aBc8, length: 4},
                    minorTicks: {color: 0x69aBc8, length: 2},
                    majorGridLines: {size: 1, color: 0xeeeeee}
                },
                yAxis: {
                    color: 0x69aBc8,
                    majorTicks: {color: 0x69aBc8, length: 4},
                    minorTicks: {color: 0x69aBc8, length: 2},
                    majorGridLines: {size: 1, color: 0xdfe8f6}
                }
            }
        }]
    });
    store.load();
});