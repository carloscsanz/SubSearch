function validateForm2(){
	
    var allWords = document.forms["AdvancedSearch"]["AllWords"].value;
    var anyWord = document.forms["AdvancedSearch"]["AnyWord"].value;
    var notAnyWord = document.forms["AdvancedSearch"]["NotAnyWord"].value;
    var category = document.forms["AdvancedSearch"]["Category"].value;
    var author = document.forms["AdvancedSearch"]["Author"].value;
    var dateSince = document.forms["AdvancedSearch"]["DateSince"].value;
    var dateUntil = document.forms["AdvancedSearch"]["DateUntil"].value;
    
    if(allWords != null && allWords != ""){
    	return true;
    }
    
    if(anyWord != null && anyWord != ""){
    	return true;
    }
    
    if(notAnyWord != null && notAnyWord != ""){
    	return true;
    }
    
    if(author != null && author != ""){
    	return true;
    }
    
    if(dateSince != null && dateSince != ""){
    	return true;
    }
    
    if(dateUntil != null && dateUntil != ""){
    	return true;
    }
    
    if(category != null && category != "*"){
    	return true;
    }
    
    return false;
}