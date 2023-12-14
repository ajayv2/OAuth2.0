$(document).ready(function(){
    $("#addOrganization").on('click',function(){
        var formData = {
                    code: $("#organizationCode").val(),
                    name: $("#organizationName").val(),
                    description: $("#organizationDescription").val()

                };
        console.log(formData);
            $.ajax({
                url: "http://localhost:8085/api/organizations",
                type: "POST",
                //dataType: "json",
                data:JSON.stringify(formData),
                contentType: "application/json; charset=utf-8",
                accept: "application/json",
                success: function(data) {
                    console.log(data);
                    window.location.href = 'http://localhost:8789/allorganization';

                },
                error: function(jqXHR, textStatus, errorThrown) {

                    console.error("Request failed: " + textStatus, errorThrown);
                }
            });


    });
});