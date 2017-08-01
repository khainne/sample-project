jQuery.i18n.properties({
    name: 'messages',
    path: context + '/resources/js/i18n/',
    mode: 'both',
    language: local,
    async: true
});

$(document).ready(function() {
    initModal();
    autoFocus();
    showErrorsOnSelectInputs();
});

//if spring select tags aren't being used, bind the error display to the plain html selects
function showErrorsOnSelectInputs() {
    var selectAreas = $("select");
    if (selectAreas.length) {
        selectAreas.each(function() {
            var selectArea = $(this);
            if (selectArea.next().hasClass('error')) {
                selectArea.addClass('error');
            }
        });
    }
}

//auto focus first input on page
function autoFocus() {
    $("form").find(".js-auto-focus").focus();
};

function initModal() {

    var modals = $('.modal');
    var modalOverlay = $('#modal-overlay');
    var modalCloseButtons = $('.js-modal-close');

    //activate modal triggers
    $('[data-modal-id]').on("click", function(e) {
        e.preventDefault();
        var data = $(this).data();
        modalShow($('#' + data.modalId));

    });

    //show specific modal
    function modalShow(modal) {
        $(".modal").addClass("is-hidden").delay(100).queue(function(next) {
            modal.removeClass('is-hidden');
            next();
        }).delay(100).queue(function(next) {
            modal.addClass('is-visible');
            next();
            var firstInput = modal.find("input:first");
            firstInput.focus();
        });

        modalOverlay.addClass('is-visible');

    }
    //handle outside click to close modal
    modalOverlay.on("click", function(e) {
        e.preventDefault();
        closeModal();
    });

    //activate close buttons
    modalCloseButtons.on("click", function(e) {
        e.preventDefault();
        closeModal();
    });

    //close modal
    function closeModal() {
        $('.js-modal-error-display').slideUp("slow", function() {
            $("this").empty();
        });;
        modalOverlay.removeClass('is-visible');
        modals.removeClass('is-visible');
    }

    //auto logout modal
    var sessionTimer;
    var aboutToLogout = false;
    var count = 0;
    var max_count = 60;
    var secondTimer;

    //only care about auto logout if the user is logged in
    if (loggedIn) {
    	
    	//start timer that shows modal after 5 minutes
        var sessionTimer = setInterval(showSessionModal, 300000);
        //reset the timer if user interacts with the page
        $(document).on('click scroll', function() {
            console.log('session timer reset');
            aboutToLogout = false;
            count = 0;
            clearInterval(sessionTimer);
            sessionTimer = setInterval(showSessionModal, 300000);
        });
        
        //pop the modal to provide a count down
        function showSessionModal() {
            if (!aboutToLogout) {
                clearInterval(sessionTimer);
                clearInterval(secondTimer)
                modalShow($('#modal-session'));
                aboutToLogout = true;
                timedCount();
            }
        }

        //final countdown, will log user out once it hits zero
        function timedCount() {
            count = count + 1;
            remaining_time = max_count - count;
            if (remaining_time == 0 && aboutToLogout) {
                location.href = context + "logout";
            } else {
                $('.js-seconds-timer').html(remaining_time);
                secondTimer = setTimeout(function() {
                    timedCount()
                }, 1000);
            }
        }
    }

}