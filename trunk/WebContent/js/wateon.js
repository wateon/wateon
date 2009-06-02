
function popUpCenter(url, title, width, height) {
	var left = (screen.width) ? (screen.width - width) / 2 : 0;
	var top = (screen.height) ? (screen.height - height) / 2 : 0;
	var settings = 'top=' + top + ', left=' + left + ', width=' + width + ', height=' + height + ', scrollbar=yes';
	window.open(url, title, settings);
}

