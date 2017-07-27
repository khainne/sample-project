$(document).ready(function() {
	initModal();
});



function initModal() {
	
	var modals = $('.modal');
	var modalOverlay = $('#modal-overlay');
	var modalCloseButtons = $('.js-modal-close');
	
	
	$('[data-modal-id]').on("click", function(e){
	   e.preventDefault();
	   var data = $(this).data();
	   var modal = $('#' + data.modalId);
	   modal.addClass('is-visible');
	   modalOverlay.addClass('is-visible');
	   
	   var firstInput = modal.find("input:first");
	   console.log(firstInput);
	   firstInput.focus();
	   
	});
	
	modalOverlay.on("click", function(e) {
		e.preventDefault();
		closeModal();
	});
	
	modalCloseButtons.on("click", function(e) {
		e.preventDefault();
		closeModal();
	});
	
	function closeModal() {
		modalOverlay.removeClass('is-visible');
		modals.removeClass('is-visible');
	}

}

