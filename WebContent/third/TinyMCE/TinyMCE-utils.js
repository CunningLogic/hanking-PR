var TinyMCE = TinyMCE || {};

TinyMCE.GetTinyMceContent = function(editorId){
	
	
	// Gets the current editors selection as text
//	tinymce.activeEditor.selection.getContent({format: 'text'});
	
	// Get the raw contents of the currently active editor
//	alert(tinymce.activeEditor.getContent({format: 'raw'}));
	
//	alert(tinymce.activeEditor.getContent());
	
	
	var content = tinyMCE.activeEditor.getContent({format: 'raw'});
	
	return content;
};

TinyMCE.SetTinyMceContent = function(content, editorId){
	
	tinyMCE.activeEditor.setContent(content, {format: 'raw'});
//	tinyMCE.activeEditor.setContent('<span>some</span> html', {format: 'raw'});
//	tinyMCE.get('txtaDescription').setContent('<span>some</span> html', {format: 'raw'});
//	tinymce.EditorManager.activeEditor.selection.setContent('<span>some</span> html', {format: 'raw'});
};