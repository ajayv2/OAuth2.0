$(document).ready(function(){
    addDepartment();
    addOrganization();
});

function addDepartment(){
    $.ajax({
        url: "http://localhost:8888/api/departments",
        method: "GET",
        dataType: "json",
        success: function(data) {
            for(i=0;i<data.length;i++){
                $('#departmentCode').append(`<option value="${data[i].code}">
                                                                   ${data[i].name}
                                                              </option>`);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Request failed: " + textStatus, errorThrown);
        }
    });
}
function addOrganization(){
    $.ajax({
        url: "http://localhost:8085/api/organizations",
        method: "GET",
        dataType: "json",
        success: function(data) {

            for(i=0;i<data.length;i++){
                    $('#organizationCode').append(`<option value="${data[i].code}">
                                             ${data[i].name}
                                             </option>`);
                        }
        },
        error: function(jqXHR, textStatus, errorThrown) {

            console.error("Request failed: " + textStatus, errorThrown);
        }
    });
}

$("#addEmployee").on('click',function(){
    var formData = {
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val(),
                email: $("#email").val(),
                departmentCode: $("#departmentCode").val(),
                organizationCode: $("#organizationCode").val(),

            };
    console.log(formData);
        $.ajax({
            url: "http://localhost:8081/api/employees",
            type: "POST",
            //dataType: "json",
            data:JSON.stringify(formData),
            contentType: "application/json; charset=utf-8",
            accept: "application/json",
            success: function(data) {
                console.log(data);
                window.location.href = 'http://localhost:8789/allemployee';
            },
            error: function(jqXHR, textStatus, errorThrown) {

                console.error("Request failed: " + textStatus, errorThrown);
            }
        });


});
