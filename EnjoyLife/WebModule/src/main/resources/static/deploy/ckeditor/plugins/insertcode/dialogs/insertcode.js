CKEDITOR.dialog.add('insertcode', function(editor){
    var escape = function(value){
        return value;
    };
    return {
        title: '插入代码',
        resizable: CKEDITOR.DIALOG_RESIZE_BOTH,
        minWidth: 600,
        minHeight: 300,
        contents: [{
            id: 'cb',
            name: 'cb',
            label: 'cb',
            title: 'cb',
            elements: [{
                type: 'select',
                label: '选择语言',
                id: 'lang',
                style: "margin-top: 5px",
                required: true,
                'default': 'java',
                items: [['Bash/shell', 'bash'], ['C++', 'cpp'], ['CSS', 'css'], ['Groovy', 'groovy'], ['Html', 'xhtml'], ['JavaScript', 'javascript'], ['Java', 'java'], ['Python', 'py'], ['Ruby', 'rails'], ['Scala', 'scala'], ['SQL', 'sql'], ['XML', 'xml']]
            }, {
                type: 'textarea',
                rows: 15,
                cols: 20,
                label: '代码段',
                id: 'code',
                'default': ''
            }]
        }],
        onOk: function(){
            var code = this.getValueOf('cb', 'code');
            var lang = this.getValueOf('cb', 'lang');
            html = '' + escape(code) + '';
            //注意这里的代码，网上很多教程都是因为这里组合的时候出错而不能运行
            var text = "<pre><code class='lang-"+ lang +"' >"+ html +"</code></pre>";
            editor.insertHtml(text);
        },
        onLoad: function(){
        }
    };
});