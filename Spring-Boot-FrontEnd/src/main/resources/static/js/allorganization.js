$(document).ready(function(){
    showOrganization();
});
function showOrganization(){
        $.ajax({
            url: "http://localhost:8085/api/organizations",
            method: "GET",
            dataType: "json",
            success: function(data) {
                console.log(data);
                var str = "";
                for(i=0;i<data.length;i++){

                str += "<div class='col-sm-4 card' style='width: 18rem;'>";
                str += "<div class='card-body'>";
                str += "<h5 class='card-title'>Id :&nbsp;"+data[i].id+"</h5>";
                str += "<p class='card-text'>OrganizationCode : &nbsp;"+data[i].code+"</p>";
                str += "<p class='card-text'>OrganizationName : &nbsp;"+data[i].name+"</p>";
                str += "<p class='card-text'>OrganizationDescription : &nbsp;"+data[i].description+"</p>";
                str += "<p><a class='btn btn-sm btn-secondary' onclick=editOrganization('"+data[i].code+"')>Edit</a>&nbsp;&nbsp;&nbsp";
                str += "<a class='btn btn-sm btn-danger' onclick=deleteOrganization('"+data[i].code+"')>Delete</a>"
                str += "</p>";
                str += "</div>";
                str += "</div>";
                }
                $("#organizationdetail").html(str);

            },
            error: function(jqXHR, textStatus, errorThrown) {

                console.error("Request failed: " + textStatus, errorThrown);
            }
        });
      }

       function deleteOrganization(orgCode){
       alert("org code is "+ orgCode);
              $.ajax({
                          url: "http://localhost:8085/api/organizations/"+orgCode,
                          method: "DELETE",
                         // dataType: "json",
                          success: function(data) {
                              alert("Organization deleted with orgCode "+orgCode);
                              window.location.href = 'http://localhost:8789/allorganization';

                          },
                          error: function(jqXHR, textStatus, errorThrown) {

                              console.error("Request failed: " + textStatus, errorThrown);
                          }
                      });
            }

            function editOrganization(orgCode){
                            //alert('dept code'+orgCode);
                                    $.ajax({
                                        url: "http://localhost:8085/api/organizations/"+orgCode,
                                        method: "GET",
                                        //dataType: "json",
                                        success: function(data) {
                                        console.log(data);
                                            $('#orgModal').modal('show');

                                            $('#organizationCode').val(data.code);
                                            $('#organizationName').val(data.name);
                                            $('#organizationDescription').val(data.description);

                                        },
                                        error: function(jqXHR, textStatus, errorThrown) {

                                            console.error("Request failed: " + textStatus, errorThrown);
                                        }
                                    });
                  }

                  $("#updateOrganization").on("click",function(){
                            var formData = {

                                        code: $("#organizationCode").val(),
                                        name: $("#organizationName").val(),
                                        description: $("#organizationDescription").val()
                                    };
                            console.log(formData);
                                $.ajax({
                                    url: "http://localhost:8085/api/organizations/"+$("#organizationCode").val(),
                                    type: "PUT",
                                    //dataType: "json",
                                    data:JSON.stringify(formData),
                                    contentType: "application/json; charset=utf-8",
                                    accept: "application/json",
                                    success: function(data) {
                                        window.location.href = 'http://localhost:8789/allorganization';

                                    },
                                    error: function(jqXHR, textStatus, errorThrown) {

                                        console.error("Request failed: " + textStatus, errorThrown);
                                    }
                                });
                  });