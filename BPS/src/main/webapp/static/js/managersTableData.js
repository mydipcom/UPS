//jquery插件把表单序列化成json格式的数据start 
(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

var rootURI="/";
var ManagersTable = function () {
	var handleTable = function () {
		var selected = [];
		var table=$('#adminusers_table');
		var oTable = table.dataTable({
			"lengthChange":false,
        	"filter":false,
        	"sort":false,
        	"info":true,
        	"processing":true,                
            // set the initial value
            "displayLength": 10,
            "dom": "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-6'i><'col-md-6'p>>",
//            "sPaginationType": "bootstrap_full_number",   //bootstrap_extended
//            "oLanguage": {
//                "sLengthMenu": "_MENU_ records per page",
//                "oPaginate": {
//                    "sPrevious": "Prev",
//                    "sNext": "Next",
//                	"zeroRecords": "No records to display"
//                }
//            },
            "columnDefs": [{                    
                    'targets': 0,   
                    'render':function(data,type,row){
                    	return '<div class="checker"><span><input type="checkbox" class="checkboxes"/></span></div>';
                    },
                    //'defaultContent':'<div class="checker"><span><input type="checkbox" class="checkboxes" value="1"/></span></div>'                    
                },
                {                	
                	'targets':-1,
                	'data':null,//定义列名
                	'render':function(data,type,row){
                    	return '<button>View</button>';
                    },
                    'class':'center'
                }
            ],
            "columns": [
               {"orderable": false },
	           { title: "ID",   data: "adminId" },
	           { title: "Email",   data: "email" },
	           { title: "Role_Name",    data: "adminRole.roleName" },
	           { title: "Status",  data: "status"},
	           { title: "CreatedBy", data: "createdBy" },
	           { title: "CreatedTime", data: "createdTimeStr" },
	           { title: "UpdatedBy",  data: "updatedBy" },
	           { title: "UpdatedTime",    data: "updatedTimeStr" },  
	           { title: "Action" ,"class":"center"},
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"managersList?rand="+Math.random(),
//	        "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
//	           $.ajax( {
//	             "dataType": 'json', 
//	             "type": "POST", 
//	             "url": sSource, 
//	             "data": aoData,
////	             "contentType":"application/json",
//	             "success": function(resp){ 		            	
//	            	 fnCallback(resp);
//	             },
//	             "error":function(XMLHttpRequest, textStatus, errorThrown){
//	            	 alert(errorThrown);
//	             }
//	           } );
//	         },
//	        "fnServerParams": function ( aoData ) {
//	           aoData.push( { "name": "more_data", "value": "my_value" } );
//	         },
//	        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {				
//	        	$('td:eq(0)', nRow).html( '<input type="checkbox" class="checkboxes" value="1"/>' );
//				return nRow;
//			},

		});		

		//删除操作
		$('#deleteBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "DELETE", 
             "url": rootURI+"managers/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 oTable.$('th span').removeClass();
					 }
					 else{
						 alert(data.info);
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
        });  
		
		//添加操作
		$('#addUsersForm').on('submit', function (event) {
			event.preventDefault();
			var jsondata=$(this).serializeJson();
			$.ajax( {
             "dataType": 'json', 
             "type":'POST', 
             "url": rootURI+"addUsers", 
             "data": $(this).serialize(),
//             "processData":false,
//             "contentType":"application/json",
             "success": function(resp,status){
            	 if(status == "success"){  
            		 if(resp.status){						 
		            	 oTable.api().draw();
		            	 handleAlerts("Added the data successfully.","success","");		            	 
					 }
					 else{
						 handleAlerts("Failed to add the data.","danger","");						 
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
			$('#add_users').modal('hide');
			//return false;
        }); 
		
		$("#openEditRightModal").on("click",function(event){
			if(selected.length>1){
				handleAlerts("Only one row can be edited.","warning","");
				event.stopPropagation();
			}
			else{
				var data = oTable.api().row($("tr input:checked").parents('tr')).data();
	            var adminId = data.adminId;
	            var email =data.email;
	            $("#editUsersForm input[name='adminId']").val(adminId);
	            $("#editUsersForm input[name='email']").val(email);
			}
		});
		
		
		//编辑表单提交操作
		$("#editUsersForm").on("submit", function(event) {
			  event.stopPropagation();
			  var jsondata=$(this).serializeJson();
			  $.ajax( {
	             "dataType": 'json', 
	             "type": "POST", 
	             "url": rootURI+"editUsers", 
	             "data": $(this).serialize(),
//	             "processData":false,
//	             "contentType":"application/json",
	             "success": function(resp,status){
	            	 if(status == "success"){  
	            		 if(resp.status){
							 selected=[];
			            	 oTable.api().draw();
			            	 handleAlerts("Edited the data successfully.","success","");
						 }
						 else{
							 alert(resp.info);
						 }
					}             	 
	             },
	             "error":function(XMLHttpRequest, textStatus, errorThrown){
	            	 alert(errorThrown);
	             }
	           });
			  $('#edit_users').modal('hide');
			  return false;
		});
				
                       
		//全选
		
        table.find('.group-checkable').change(function () {
        	alert("in this");
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            var api=oTable.api();
            jQuery(set).each(function () {
            	var data = api.row($(this).parents('tr')).data();
            	var id = data.adminId;
                var index = $.inArray(id, selected);
                if (checked) {
                	selected.push( id );
                    $(this).attr("checked", true);
                    $(this).parents('tr').addClass("active");
                    $(this).parents('span').addClass("checked");
                } else {
                	selected.splice( index, 1 );
                    $(this).attr("checked", false);
                    $(this).parents('tr').removeClass("active");
                    $(this).parents('span').removeClass("checked");
                }
            });
            jQuery.uniform.update(set);
        });
        
        //单选
        table.on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");            
            var data = oTable.api().row($(this).parents('tr')).data();
            var id = data.adminId;
            var index = $.inArray(id, selected);     
            if ( index === -1 ) {
                selected.push( id );
                $(this).parents('span').addClass("checked");
                $(this).attr("checked","checked");
            } else {
                selected.splice( index, 1 );
                $(this).parents('span').removeClass("checked");
                $(this).removeAttr("checked");
            }
        });
               
        table.on('click', 'tbody tr button',function(){
        	alert("in this");
            var data = oTable.api().row($(this).parents('tr')).data();
            var html="<table class=\"table table-bordered\"><tr class='success'><th>ID</th><th>Admin Name</th><th>Content</th><th>Level</th><th>Create Time</th></tr>";
            $.ajax( {
                "dataType": 'json', 
                "type": "POST", 
                "url": rootURI+"managerslogview/"+data.adminId, 
                "success": function(data,status){
               	 if(status == "success"){
               		 alert("test");
               		 var adminslog=data.adminslog;
               		 $.each(adminslog,function(i, items){
               			 html=html+"<tr>";
               			 html=html+"<td>"+items.id+"</td>";
               			 html=html+"<td>"+items.adminId+"</td>";
               			 html=html+"<td>"+items.content+"</td>";
               			 html=html+"<td>"+items.level+"</td>";
               			 html=html+"<td>"+items.createdTimeStr+"</td>"+"</tr>";  
               		 });
               		 html=html+"</table>";
                     $("#dialogDiv").html(html);
               		 $("#dialogDiv").dialog("open");
    				}           	 
                },
                "error":function(XMLHttpRequest, textStatus, errorThrown){
               	 alert(errorThrown);
                }
              });
         
           });
        /* handle show/hide columns*/
        var tableColumnToggler = $('#column_toggler');		
		$('input[type="checkbox"]', tableColumnToggler).change(function () {
		    /* Get the DataTables object again - this is not a recreation, just a get of the object */
		    var iCol = parseInt($(this).attr("data-column"));
		    var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
		    oTable.fnSetColumnVis(iCol, (bVis ? false : true));
		});
        
        
	};
	
	
	//提示信息处理方法（是在页面中指定位置显示提示信息的方式）
	var handleAlerts = function(msg,msgType,position) {         
        Metronic.alert({
            container: position, // alerts parent container(by default placed after the page breadcrumbs)
            place: "prepent", // append or prepent in container 
            type: msgType,  // alert's type (success, danger, warning, info)
            message: msg,  // alert's message
            close: true, // make alert closable
            reset: true, // close all previouse alerts first
            focus: false, // auto scroll to the alert after shown
            closeInSeconds: 10, // auto close after defined seconds, 0 never close
            icon: "warning" // put icon before the message, use the font Awesone icon (fa-[*])
        });        

    };
    
    //处理表单验证方法
    var addFormValidation = function() {
            var addform = $('#addUsersForm');
            var errorDiv = $('.alert-danger', addform);            

            addform.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                    name: {
                        minlength: 2,
                        required: true
                    },
                    uri: {
                        required: true,
                        maxlength:60                        
                    },
                    method: {
                        required: true                        
                    },
                    pid: {
                        required: true,
                        number: true
                    }                    
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                	successDiv.hide();
                    errorDiv.show();                    
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {                	
                    errorDiv.hide();
                }
            });
    };

    return {
        //main function to initiate the module
        init: function (rootPath) {
        	rootURI=rootPath;
        	handleTable();  
        	addFormValidation();
        }

    };

}();