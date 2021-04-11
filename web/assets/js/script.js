function validateSignUp() {
    var email = document.getElementById("txtEmail").value;
    var name = document.getElementById("txtName").value;
    var password = document.getElementById("txtPassword").value;
    var confirmPassword = document.getElementById("txtConfirmPassword").value;
    
    const regex = /(\w*\d*)+@(\w+\.\w+){1}(\.\w+)?/;
    
    if (!regex.test(email)) {
        alert("Wrong format email");
        return false;
    }
    
    if (email.length > 256) {
        alert("Email not more than 256 characters");
        return false;
    }
    
    if (name.length > 100) {
        alert("Name not more than 100 characters");
        return false;
    }
    
    if (!(password === confirmPassword)) {
        alert("Confirm password not matched");
        return false;
    }
}

function validateQuestion() {
    var optionA = document.getElementById("optionA").value;
    var optionB = document.getElementById("optionB").value;
    var optionC = document.getElementById("optionC").value;
    var optionD = document.getElementById("optionD").value;
    
    if (optionA === optionB) {
        alert("Option A can't equal option B");
        return false;
    }
    
    if (optionA === optionC) {
        alert("Option A can't equal option C");
        return false;
    }
    
    if (optionA === optionD) {
        alert("Option A can't equal option D");
        return false;
    }
    
    if (optionB === optionC) {
        alert("Option B can't equal option C");
        return false;
    }
    
    if (optionB === optionD) {
        alert("Option B can't equal option D");
        return false;
    }
    
    if (optionC === optionD) {
        alert("Option C can't equal option D");
        return false;
    }
    
    return true;
}

function validateSearch() {
    var searchValue = document.getElementById("searchValue").value;
    var categoryValue = document.getElementById("categoryValue").value;
    var amount = document.getElementById("amount").value;

    if (searchValue === "" && categoryValue === "") {
        alert("Please input name or choose category to search!");
        return false;
    }
    if (amount === "") {
        alert("Please input amount to search!!!");
        return false;
    }
    if (!validateDate())
        return false;
    return true;
}

function validateDate() {
    var rentalDate = document.getElementById("rentalDate").value;
    var returnDate = document.getElementById("returnDate").value;
    if (rentalDate === "") {
        alert("Please input rental date to search!");
        return false;
    }
    if (returnDate === "") {
        alert("Please input return date to search!");
        return false;
    }
    var rentalTime = Date.parse(rentalDate);
    var returnTime = Date.parse(returnDate);
    var currentDate = new Date();
    var currentTime = currentDate.getTime();
    if (((currentTime - 86400000) - rentalTime) > 0) {
        alert("Rental Date must be today or greater than today");
        return false;
    }
    if (((currentTime - 86400000) - returnTime) > 0) {
        alert("Return Date must be today or greater than today");
        return false;
    }
    if ((rentalTime - returnTime) > 0) {
        alert("Rental Date must equal or greater Return Date");
        return false;
    }
    return true;
}

function validateSearchOrder() {
    var txtSearchValue = document.getElementById("txtSearchValue").value;
    var txtFromDate = document.getElementById("txtFromDate").value;
    var txtToDate = document.getElementById("txtToDate").value;
    
    if (txtSearchValue === "" && (txtFromDate === "" || txtToDate === "")) {
        alert("Please input name or choose date to search!");
        return false;
    }
    return true;
}