$(document).ready(function(){
    showEmployee();
});
function showEmployee(){
        $.ajax({
            url: "http://localhost:8081/api/employees",
            method: "GET",
            dataType: "json",
            success: function(data) {
                console.log(data);
                var str = "";
                for(i=0;i<data.length;i++){

                str += "<div class='col-sm-4 card' style='width: 18rem;'>";
                str += "<div class='card-body'>";
                str += "<h5 class='card-title'>Id :&nbsp;"+data[i].id+"</h5>";
                str += "<p class='card-text'>FirstName : &nbsp;"+data[i].firstName+"</p>";
                str += "<p class='card-text'>LastName : &nbsp;"+data[i].lastName+"</p>";
                str += "<p class='card-text'>Email : &nbsp;"+data[i].email+"</p>";
                str += "<p class='card-text'>DepartmentCode : &nbsp;<span  onclick='loadDepartment(id);' id='deptCode"+i+"'>"+data[i].departmentCode+"</span></p>";
                str += "<p class='card-text'>OrganizationCode : &nbsp;<span onclick='loadOrganization(id);' id='orgCode"+i+"'>"+data[i].organizationCode+"</span></p>";
                str += "<p><a class='btn btn-sm btn-secondary' onclick=editEmployee('"+data[i].id+"')>Edit</a>&nbsp;&nbsp;&nbsp";
                str += "<a class='btn btn-sm btn-danger' onclick=deleteEmployee('"+data[i].id+"')>Delete</a>"
                str += "</div>";
                str += "</div>";
                }
                $("#employeedetail").html(str);

            },
            error: function(jqXHR, textStatus, errorThrown) {

                console.error("Request failed: " + textStatus, errorThrown);
            }
        });
      }

  function loadDepartment(id){
  id = '#'+id;

        $.ajax({
            url: "http://localhost:8888/api/departments/"+$(id).text(),
            method: "GET",
            dataType: "json",
            success: function(data) {
                console.log(data);
                $('#exampleModal').modal('show');
                $('#modelContent').html('');
                 var str = "<div class='col-sm-12 card' >";
                 str += "<div class='card-body'>";
                 str += "<h5 class='card-title'>Id :&nbsp;"+data.id+"</h5>";
                 str += "<p class='card-text'>DepartmentCode : "+data.departmentCode+"</p>";
                 str += "<p class='card-text'>DepartmentName :"+data.departmentName+"</p>";
                 str += "<p class='card-text'>DepartmentDescription :"+data.departmentDescription+"</p>";
                 str += "</div>";
                 str += "</div>";
                 $('#modelContent').html(str);

            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error("Request failed: " + textStatus, errorThrown);
            }
        });

  }

  function loadOrganization(id){
    id = '#'+id;

          $.ajax({
              url: "http://localhost:8085/api/organizations/"+$(id).text(),
              method: "GET",
              dataType: "json",
              success: function(data) {
                  console.log(data);
                  $('#exampleModal').modal('show');
                  $('#modelContent').html('');
                   var str = "<div class='col-sm-12 card' >";
                   str += "<div class='card-body'>";
                   str += "<h5 class='card-title'>Id :&nbsp;"+data.id+"</h5>";
                   str += "<p class='card-text'>OrganizationCode : "+data.organizationCode+"</p>";
                   str += "<p class='card-text'>OrganizationName :"+data.organizationName+"</p>";
                   str += "<p class='card-text'>OrganizationDescription :"+data.organizationDescription+"</p>";
                   str += "</div>";
                   str += "</div>";
                   $('#modelContent').html(str);

              },
              error: function(jqXHR, textStatus, errorThrown) {
                  console.error("Request failed: " + textStatus, errorThrown);
              }
          });

    }

    function deleteEmployee(id){
                  $.ajax({
                       url: "http://localhost:8081/api/employees/"+id,
                       method: "DELETE",
                      // dataType: "json",
                         success: function(data) {
                          alert("Employee deleted with id "+id);
                           window.location.href = 'http://localhost:8789/allemployee';

                           },
                          error: function(jqXHR, textStatus, errorThrown) {
                          alert('Something error occurred');

                           console.error("Request failed: " + textStatus, errorThrown);
                          }
                       });
                }

    function editEmployee(id){
                     alert('emp id'+id);
                        $.ajax({
                               url: "http://localhost:8081/api/employees/"+id,
                                method: "GET",
                                 //dataType: "json",
                                 success: function(data) {
                                 console.log(data);
                                 $('#employeeModal').modal('show');
                                 localStorage.setItem("empid",id);
                                  $("#firstName").val(data.employee.firstName);
                                  $("#lastName").val(data.employee.lastName);
                                  $("#email").val(data.employee.email);

                                  $("#departmentCode").val(data.department.departmentCode);
                                  $("#organizationCode").val(data.organizationDto.organizationCode);

                                 },
                                 error: function(jqXHR, textStatus, errorThrown) {

                                 console.error("Request failed: " + textStatus, errorThrown);
                                 }
                             });
    }

    $("#updateEmployee").on("click",function(){
                    var formData = {

                                firstName: $("#firstName").val(),
                                lastName: $("#lastName").val(),
                                email: $("#email").val(),
                                departmentCode : $("#departmentCode").val(),
                                organizationCode : $("#organizationCode").val()
                            };
                    console.log(formData);

                        $.ajax({
                            url: "http://localhost:8081/api/employees/"+localStorage.getItem("empid"),
                            type: "PUT",
                            //dataType: "json",
                            data:JSON.stringify(formData),
                            contentType: "application/json; charset=utf-8",
                            accept: "application/json",
                            success: function(data) {
                                window.location.href = 'http://localhost:8789/allemployee';

                            },
                            error: function(jqXHR, textStatus, errorThrown) {

                                console.error("Request failed: " + textStatus, errorThrown);
                            }
                        });
          });

