var viewModel = {
	isActive : ko.observable(""),
	isWrong : ko.observable(""),
	email : ko.observable(""),
	password : ko.observable(""),
	toggleRememberMe : function() {
		if (this.isActive() === "active") {
			this.isActive("");
		} else {
			this.isActive("active");
		}
	},
	login : function() {
		$.ajax({
			type : 'post',
			url : 'login',
			data : {
				login : "login",
				email : this.email,
				password : this.password
			},
			success: function(result,status,xhr){
			    console.log("Daaa");
				console.log(result);
			},
			error: function(xhr,status,error){
				console.log("kooo");
				viewModel.isWrong("error");
			}
		})
	}
	
};
ko.applyBindings(viewModel);