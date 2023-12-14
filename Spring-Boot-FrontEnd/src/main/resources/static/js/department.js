$(document).ready(function(){
    $("#addDepartment").on('click',function(){
        var formData = {
                    code: $("#departmentCode").val(),
                    name: $("#departmentName").val(),
                    description: $("#departmentDescription").val()
                };
        console.log(formData);
            $.ajax({
                url: "http://localhost:8888/api/departments",
                type: "POST",
                //dataType: "json",
                data:JSON.stringify(formData),
                contentType: "application/json; charset=utf-8",
                accept: "application/json",
                success: function(data) {
                    window.location.href = 'http://localhost:8789/alldepartment';

                },
                error: function(jqXHR, textStatus, errorThrown) {

                    console.error("Request failed: " + textStatus, errorThrown);
                }
            });


    });
});