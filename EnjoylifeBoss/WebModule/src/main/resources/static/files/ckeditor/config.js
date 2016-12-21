/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    config.filebrowserUploadUrl = "/blogs/upload.html";
    config.extraPlugins = 'insertcode';//插入多个插件用逗号分隔，如‘insertcode,insertblock’
    config.allowedContent = true;//禁止过滤标签，即插入代码时，如果这里不设为true，插入的<code class='lang-java'>最后会过滤到class属性，变成<code>
};
