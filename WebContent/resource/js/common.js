console.clear();

function MobileSideBar__init() {
    $('.mobile-top-bar .btn-toggle-mobile-side-bar').click(function () {
        var $this = $(this);

        if ($this.hasClass('active')) {
            $this.removeClass('active');
            $('.mobile-side-bar').removeClass('active');
        } else {
            $this.addClass('active')
            $('.mobile-side-bar').addClass('active');
        }
    });
}

$(function () {
    MobileSideBar__init();
});


var editor1__initialValue = $('#origin1').html();
		var editor1 = new toastui.Editor({
			el : document.querySelector('#viewer1'),
			height : '600px',
			initialValue : editor1__initialValue,
			viewer : true,
			plugins : [ toastui.Editor.plugin.codeSyntaxHighlight ]
});