$(document).ready(function(){
    showDepartment();
});
function showDepartment(){
        $.ajax({
            url: "http://localhost:8888/api/departments",
            method: "GET",
            dataType: "json",
            success: function(data) {
                console.log(data);
                var str = "";
                for(i=0;i<data.length;i++){

                str += "<div class='col-sm-4 card' style='width: 18rem;'>";
                str += "<div class='card-body'>";
                str += "<h5 class='card-title'>Id :&nbsp;"+data[i].id+"</h5>";
                str += "<p class='card-text'>DepartmentCode : &nbsp;"+data[i].code+"</p>";
                str += "<p class='card-text'>DepartmentName : &nbsp;"+data[i].name+"</p>";
                str += "<p class='card-text'>DepartmentDescription : &nbsp;"+data[i].description+"</p>";
                str += "<p><a class='btn btn-sm btn-secondary' onclick = editDepartment('"+data[i].code+"')>Edit</a>&nbsp;&nbsp;&nbsp";
                str += "<a class='btn btn-sm btn-danger' onclick=deleteDepartment('"+data[i].code+"')>Delete</a>"
                str += "</p>";
                str += "</div>";
                str += "</div>";
                }
                $("#departmentdetail").html(str);

            },
            error: function(jqXHR, textStatus, errorThrown) {

                console.error("Request failed: " + textStatus, errorThrown);
            }
        });
      }

      function deleteDepartment(deptCode){
                $.ajax({
                    url: "http://localhost:8888/api/departments/"+deptCode,
                    method: "DELETE",
                    //dataType: "json",
                    success: function(data) {
                        alert("Department deleted with deptCode "+deptCode);
                        window.location.href = 'http://localhost:8789/alldepartment';

                    },
                    error: function(jqXHR, textStatus, errorThrown) {

                        console.error("Request failed: " + textStatus, errorThrown);
                    }
                });
      }

      function editDepartment(deptCode){
             //   alert('dept code'+deptCode);
                        $.ajax({
                            url: "http://localhost:8888/api/departments/"+deptCode,
                            method: "GET",
                            //dataType: "json",
                            success: function(data) {
                            console.log(data);
                                $('#departmentModal').modal('show');

                                $('#departmentCode').val(data.code);
                                $('#departmentName').val(data.name);
                                $('#departmentDescription').val(data.description);

                            },
                            error: function(jqXHR, textStatus, errorThrown) {

                                console.error("Request failed: " + textStatus, errorThrown);
                            }
                        });
      }

      $("#updateDepartment").on("click",function(){
                var formData = {

                            code: $("#departmentCode").val(),
                            name: $("#departmentName").val(),
                            description: $("#departmentDescription").val()
                        };
                console.log(formData);
                    $.ajax({
                        url: "http://localhost:8888/api/departments/"+$("#departmentCode").val(),
                        type: "PUT",
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

