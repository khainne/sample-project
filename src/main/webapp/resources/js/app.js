jQuery.i18n.properties({
    name:'messages', 
    path: context + '/resources/js/i18n/', 
    mode:'both',
    language: local,
    async: true
});

$(document).ready(function() {
	initModal();
	autoFocus();
	if ($("#securityQuestionForm").length) {
		initSecurityQuestionForm();
	}
	
	var selectAreas = $("select");
	if (selectAreas.length) {
		selectAreas.each(function() {
			var selectArea = $(this);
			if( selectArea.next().hasClass('error')) {
				selectArea.addClass('error');
			}
		});
	}
	
	
});

function autoFocus() {
	$("form").find(".js-auto-focus").focus();
};



function initModal() {

	var modals = $('.modal');
	var modalOverlay = $('#modal-overlay');
	var modalCloseButtons = $('.js-modal-close');
	
	
	$('[data-modal-id]').on("click", function(e){
	   e.preventDefault();
	   var data = $(this).data();
	   modalShow($('#' + data.modalId));
	   
	});
	
	function modalShow(modal) {
		$(".modal").addClass("is-hidden").delay(100).queue(function(next){
		    modal.removeClass('is-hidden');
		    next();
		}).delay(100).queue(function(next){
			modal.addClass('is-visible');
		    next();
		    var firstInput = modal.find("input:first");
			firstInput.focus();
		});
		
		modalOverlay.addClass('is-visible');
		
	}
	
	modalOverlay.on("click", function(e) {
		e.preventDefault();
		closeModal();
	});
	
	modalCloseButtons.on("click", function(e) {
		e.preventDefault();
		closeModal();
	});
	
	function closeModal() {
		$('.js-modal-error-display').slideUp("slow", function() {
		   $("this").empty();
		});;
		modalOverlay.removeClass('is-visible');
		modals.removeClass('is-visible');
	}

	
	var sessionTimer;
	var aboutToLogout = false;
	var count = 0; 
	var max_count = 60;
	var secondTimer;
	
	if(loggedIn) {
		var sessionTimer = setInterval(showSessionModal, 300000);
		
		$(document).on('click scroll', function() {
			console.log('session timer reset');
			aboutToLogout = false;
			count = 0;
			clearInterval(sessionTimer);
			sessionTimer = setInterval(showSessionModal, 300000);
		});
		
		function showSessionModal() {
			if(!aboutToLogout) {
				clearInterval(sessionTimer);
				clearInterval(secondTimer)
				modalShow($('#modal-session'));
				aboutToLogout = true;
				timedCount();
			}
		}
		
		function timedCount() {
		    count = count + 1;
		   	remaining_time = max_count - count;
		   	if( remaining_time == 0 && aboutToLogout ){
		   		location.href = context + "logout";

			} else {
		    	$('.js-seconds-timer').html(remaining_time);
		    	secondTimer = setTimeout(function(){timedCount()}, 1000);
			}
		}
	}
	
}


function initSecurityQuestionForm() {
	var securitySelects = $('.js-security-question-select');
	filterQuestions();
	securitySelects.on("change", function(e) {
		var select = $(this);
		var value = select.val();
		var hiddenInput = select.siblings('input[type=hidden]');
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

