CKEDITOR.plugins.add('insertcode', {
    requires: ['dialog'],
    init: function(a){
        a.addCommand('insertcode', new CKEDITOR.dialogCommand('insertcode'));
        a.ui.addButton('insertcode', {
            label: "插入代码",
            command: 'insertcode',
            icon: this.path + 'images/music.png'
        });
        CKEDITOR.dialog.add('insertcode', this.path + 'dialogs/insertcode.js');
    }
});