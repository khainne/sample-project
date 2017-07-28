$(document).ready(function() {
	initModal();
	
	if ($("#securityQuestionForm").length) {
		initSecurityQuestionForm();
	}
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


function initSecurityQuestionForm() {
	var securitySelects = $('.js-security-question-select');
	filterQuestions();
	securitySelects.on("change", function(e) {
		var select = $(this);
		var value = select.val();
		var hiddenInput = select.next();
		if(value != -1) {
			hiddenInput.val(value);
		} else {
			hiddenInput.val('');
		}
		filterQuestions();
	});
	function filterQuestions() {
		securitySelects.each(function() {
			$(this).find("span option").unwrap();
		});
		securitySelects.each(function(i) {
			var currentSelect = $(this);
			var currentSelectValue = currentSelect.val();
			securitySelects.each(function(j) {
				if(i != j && currentSelectValue != -1) {
					$(this).find("option[value='" + currentSelectValue + "']").wrap('<span/>');
				}
			});
		}); 
	}
}

