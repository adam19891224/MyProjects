CKEDITOR.dialog.add('insertcode', function(editor){
    var escape = function(value){
        return value;
    };
    return {
        title: '插入代码',
        resizable: CKEDITOR.DIALOG_RESIZE_BOTH,
        minWidth: 720,
        minHeight: 480,
        contents: [{
            id: 'cb',
            name: 'cb',
            label: 'cb',
            title: 'cb',
            elements: [{
                type: 'select',
                label: 'Language',
                id: 'lang',
                required: true,
                'default': 'csharp',
                items: [['Bash/shell', 'bash'], ['C#', 'csharp'], ['C++', 'cpp'], ['CSS', 'css'], ['Delphi', 'delphi'], ['Diff', 'diff'], ['Groovy', 'groovy'], ['Html', 'xhtml'], ['JavaScript', 'js'], ['Java', 'java'], ['JavaFX', 'jfx'], ['Perl', 'perl'], ['PHP', 'php'], ['Plain Text', 'plain'], ['PowerShell', 'ps'], ['Python', 'py'], ['Ruby', 'rails'], ['Scala', 'scala'], ['SQL', 'sql'], ['Visual Basic', 'vb'], ['XML', 'xml']]
            }, {
                type: 'textarea',
                style: 'width:700px;height:420px',
                label: 'Code',
                id: 'code',
                rows: 31,
                'default': ''
            }]
        }],
        onOk: function(){
            code = this.getValueOf('cb', 'code');
            lang = this.getValueOf('cb', 'lang');
            html = '' + escape(code) + '';
            var text = "<pre><code class='lang-"+ lang +"' >"+ html +"</code></pre>";
            editor.insertHtml(text);
        },
        onLoad: function(){
        }
    };
});