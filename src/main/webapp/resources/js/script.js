var usernameCheck;
function checkDB() {
    var name = $('#username').val();
    $.ajax({
        url: "/"+name,
        type: "GET",

        success: function (data) {
            if (data === "bad") {
                $('#usernameComment').html('<span id="uComment">This name already exist</span>');
                usernameCheck = false;
            }
            else {
                $('#uComment').remove();
                usernameCheck = true;
            }
        }
    });
    return usernameCheck;
}

function checkEmail() {
    var regEx = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var email = $('#email').val();
    if (!email.match(regEx)) {
        $('#emailComment').html('<span id="eComment">Bad email</span>');
        return false;
    } else {
        $('#eComment').remove();
        return true;
    }
}

function checkPasswordIdentity() {
    var password = $('#password').val();
    var passConfirm = $('#confirm').val();
    if (password !== passConfirm) {
        $('#passwordComment').html('<span id="pComment">Passwords do not match</span>');
        return false;
    } else {
        $('#pComment').remove();
        return true;
    }
}

function formSubmit(form) {
    if (checkDB() && checkEmail() && checkPasswordIdentity()) {
        form.submit();
        $('#bComment').remove();
        return true;
    }
    else {
        $('#buttonComment').html('<span id="bComment">Please fill out all fields</span>');
        return false;
    }

}