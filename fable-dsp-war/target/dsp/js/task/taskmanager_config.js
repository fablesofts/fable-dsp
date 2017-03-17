var columnOperator = [
        {
            id: 'contain',
            text: '包含'
        },
        {
            id: '!contain',
            text: '不包含'
        },
        {
            id: 'equal',
            text: '等于'
        },
        {
            id: '!equal',
            text: '不等于'
        },
        {
            id: 'greater',
            text: '大于'
        },
        {
            id: '！greater',
            text: '小于等于'
        },
        {
            id: 'smaller',
            text: '小于'
        },
        {
            id: '！smaller',
            text: '大于等于'
        },
        {
            id: 'fixlength',
            text: '定长'
        },
        {
            id: 'regexp',
            text: '正则表达式'
        }
];

var filterType = [
        {
            id: '0',
            text: '包含'
        },
        {
            id: '1',
            text: '不包含'
        }
];

var fileType = [
        {
            id: '*',
            text: '所有文件'
        },
        {
            id: '*.txt',
            text: '文本文件'
        },
        {
            id: '*.doc;*.docx',
            text: 'word文档'
        },
        {
            id: '*.xls;*.xlsx',
            text: 'excel文档'
        }
];

var synchroType = [
   {
    id: '0',
    text: '全量同步'
   },
   {
    id: '1',
    text: '增量同步',
    children:[
              {
            	  id:'3',
            	  text:'触发器同步'
              },
              {
            	  id:'4',
            	  text:'时间戳同步'
              }
              ]
   },
   {
    id: '2',
    text: '日志同步'
   }
   ];

var taskType = [
                {
                    id: '0',
                    text: '数据库→数据库'
                },
                {
                    id: '1',
                    text: '文件 →文件'
                },
                {
                    id: '2',
                    text: '文件 →数据库'
                },
                {
                    id: '3',
                    text: '数据库 →文件'
                }
        ];
var schedulModel = [
	{
	    id: '0',
	    text: '周期模式'
	},{
	    id: '1',
	    text: '定期模式'
	}
];
var timeSchedul = [
	{
	    id: '0',
	    text: '0'
	},{
	    id: '15',
	    text: '15'
	},{
	    id: '30',
	    text: '30'
	},{
	    id: '45',
	    text: '45'
	},{
		id:'59',
		text:'59'
	}              
];
var weekDay =  [{
    id: '1',
    text: '星期日'
},{
    id: '2',
    text: '星期一'
},{
    id: '3',
    text: '星期二'
},{
    id: '4',
    text: '星期三'
},{
    id: '5',
    text: '星期四'
},{
    id: '6',
    text: '星期五'
},{
    id: '7',
    text: '星期六'
}];
var sourceFile = [{
	id: '0',
	text: '删除原文件'
},{
	id: '1',
	text: '另存原文件'
}];

var targetFile = [{
	id: '0',
	text: '覆盖重名文件'
},{
	id: '1',
	text: '跳过重名文件'
}]
