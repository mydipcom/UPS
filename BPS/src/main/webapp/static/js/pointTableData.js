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
var PointUserTable = function () {
	var oTable;
	var selected;
	var oLogTable;
	var handleTable = function () {
		selected = [];
		var table=$('#point_users_table');
		oTable = table.dataTable({
			"lengthChange":false,
        	"filter":true,
        	"sort":false,
        	"info":true,
        	"processing":true,                
            // set the initial value
            "displayLength": 10,
            "dom": "Tt<'row'<'col-md-6'i><'col-md-6'p>>",
//            "sPaginationType": "bootstrap_full_number",   //bootstrap_extended
//            "oLanguage": {
//                "sLengthMenu": "_MENU_ records per page",
//                "oPaginate": {
//                    "sPrevious": "Prev",
//                    "sNext": "Next",
//                	"zeroRecords": "No records to display"
//                }
//            },
            "tableTools":{
	        	"sSwfPath":"../assets/global/plugins/datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf",
	        	"aButtons":[{
	                    "sExtends": "text",
	                    "sButtonText": "Import From Excel",
	                     "fnClick":function(nButton, oConfig, oFlash){
	                    	 $("#Import_Pointuser_Excel").modal("show");
	                     }
	                }, {
	                    "sExtends": "xls",
	                    "sButtonText": "Export To Excel"
	                }]
	        },
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
                    	return '<div class="actions"><a class="btn btn-sm dark" data-toggle="modal"  href="#pointchange_log" id="openrluesviewmodal">view</a></div>';
                    },
                    'class':'center'
                }
            ],
            "columns": [
               {"orderable": false },
	           { title: "User ID",   data: "userId" },
	           { title: "Bonus Point Balance",   data: "points" },
	           { title: "Status",    data: "status" },
	           { title: "Action" ,"class":"center"}
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"pointsList?rand="+Math.random(),
            "sScrollX":"100%",        	
	        "sScrollXInner":"100%",
	        
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
		
		 
		  
		  
		$("#deactivateBtn").on("click",function(event){
			if(selected.length<1){
				
				handleAlerts("Select at least one line change.","warning","","5");
				event.stopPropagation();
			}
			else{
				$("#Deactivate_Users").modal("show");
			}
		});
		
        //禁用操作
		$('#deactivateCon').on('click', function (e) {
			
			$.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"management_points/"+selected.join()+"?act=0&rand="+Math.random(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 handleAlerts("Deactivate the point users successfully.","success","","10");	
		            	// oTable.$('th span').removeClass();
					 }
					 else{
						 handleAlerts("Failed to deactivate the point users.","danger","","10");	
						 alert(data.info);
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
			$("#Deactivate_Users").model("hide");
			//return false;
        });  
		
		$("#activateBtn").on("click",function(event){
			if(selected.length<1){
				
				handleAlerts("Select at least one line change.","warning","","5");
				event.stopPropagation();
			}
			else{
				$("#Activate_Users").modal("show");
			}
		});
		//启用操作
		$('#activateCon').on('click', function (e) {
			
			$.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"management_points/"+selected.join()+"?act=1&rand="+Math.random(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 handleAlerts("Activate the point users successfully.","success","","10");	
		            	 
					 }
					 else{
						 handleAlerts("Failed to Activate the point users.","danger","","10");	
						 alert(data.info);
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
			$("#Deactivate_Users").model("hide");
			//return false;
        });  
		
		
		$("#changeBalancebtn").on("click",function(event){
			if(selected.length<1){
				
				handleAlerts("Select at least one line change.","warning","","5");
				event.stopPropagation();
			}else if(selected.length > 1){
				$("#Change_More_Balance").modal("show");
				
			}
			else{
				$("#Change_Balance").modal("show");
				
				var data = oTable.api().row($("tr input:checked").parents('tr')).data();
				
				var userId = data.userId;
	            $("#changeBalanceForm input[name='userId']").val(userId);
	            
	            var points = data.points;
	            $("#changeBalanceForm input[name='points']").val(points);
	            }
		});
		
		//EXCEL导入提交操作
		$("#Import_Pointuser_Excel").on("submit", function(event) {
			  event.stopPropagation();
			  $("#Import_Pointuser_Excel").modal("hide");
			  $("#spin").removeClass("display-hide");
			  handleAlerts("Improt the user .......","info","","0");
			  var jsondata=$(this).serializeJson();
			  $.ajaxFileUpload( {
	             "type": "POST", 
	             "url": rootURI+"importPointUser?rand="+Math.random(), 
	             "secureuri": false,
	             "fileElementId":"pointuser", 
	             "dataType": "json",
//	             "processData":false,
//	             "contentType":"application/json",
	             "success": function(resp,status){
	            	 if(status == "success"){  
	            		 if(resp.status){
							 selected=[];
			            	 oTable.api().draw();
			            	 handleAlerts("Improt the user successfully.","success","","10");
			            	
			              }
						 else{
						     handleAlerts(resp.info,"danger","","10");
						 }
	            		 
					} 
	            	 $("#spin").addClass("display-hide");
	             },
	             "error":function(XMLHttpRequest, textStatus, errorThrown){
	            	 $("#spin").addClass("display-hide");
	            	 alert(errorThrown);
	             }
	           });
			  return false;
		});   
		
       //全选
		 $(".group-checkable").on('change',function () {
	            var set = jQuery(this).attr("data-set");
	            var checked = jQuery(this).is(":checked");
	            selected=[];
	            if(checked){            	
		            var api=oTable.api();            
		            jQuery(set).each(function () {            	
		            	var data = api.row($(this).parents('tr')).data();
		            	var id = data.userId;
		                var index = $.inArray(id, selected);
		                selected.push( id );
	                    $(this).attr("checked", true);
	                    $(this).parents('tr').addClass("active");
	                    $(this).parents('span').addClass("checked");
		            });
	            }
	            else{
	            	jQuery(set).removeAttr("checked");
	            	jQuery(set).parents('tr').removeClass("active");
	            	jQuery(set).parents('span').removeClass("checked");
	            }
	            jQuery.uniform.update(set);
	        });

      
        //单选
        table.on('change', 'tbody tr .checkboxes', function () {
            $(this).parents('tr').toggleClass("active");            
            var data = oTable.api().row($(this).parents('tr')).data();
            var id = data.userId;
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
                
        /* handle show/hide columns*/
        var tableColumnToggler = $('#column_toggler');		
		$('input[type="checkbox"]', tableColumnToggler).change(function () {
		    /* Get the DataTables object again - this is not a recreation, just a get of the object */
		    var iCol = parseInt($(this).attr("data-column"));
		    var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
		    oTable.fnSetColumnVis(iCol, (bVis ? false : true));
		});
		 
		 
		 table.on('click', 'tbody tr a',function(){
	           var data = oTable.api().row($(this).parents('tr')).data();
	           var ids=data.userId;
	           if(oLogTable!=null){
	        	   oLogTable.fnDestroy();
	        	   viewLogTable(ids); 
	           }else{
	        	   viewLogTable(ids);
	           }
	           });
		 
		 var viewLogTable = function(ids){			
				var logTable=$('#pointchangelog');
				oLogTable = logTable.dataTable({
					"lengthChange":false,
			    	"filter":false,
			    	"sort":false,
			    	"info":true,
			    	"bRetrieve": true,
			    	"processing":true,
			    	"bDestroy":true,
			        // set the initial value
			        "displayLength": 6,
			        "dom": "t<'row'<'col-md-6'i><'col-md-6'p>>",
			        "columns": [
			 	           { title: "Point",   data: "points" },
			 	           { title: "PointBalance",   data: "pointsBalance" },
			 	           { title: "Content",  data: "content"},
			 	           { title: "From", data: "from"},
			 	           { title: "Create Time", data: "createdTimeStr" },
			 	        ],
	     	        "serverSide": true,
	     	        "serverMethod": "GET",
	     	        "ajaxSource": rootURI+"pointlogListById?id="+ids+"&rand="+Math.random()
				});	
			};
 };
	
    
    //search
    var search = function(){
    	event.preventDefault();
		var jsonData=$("#searchForm").serializeJson();
		var jsonDataStr=JSON.stringify(jsonData);			
		oTable.fnFilter(jsonDataStr);
		return false;
    };
    
	
    //修改积分
	var changeBalance = function (){
		 var jsondata=$(this).serializeJson();
		 $.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"changeUserPoints?rand="+Math.random(), 
             "data": $("#changeBalanceForm").serialize(),
             "success": function(resp,status){
            	 if(status == "success"){  
            		 if(resp.status){
						 selected=[];
		            	 oTable.api().draw();
		            	 handleAlerts("Change the balance successfully.","success","","10");
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
		 $("#Change_Balance").modal("hide");
		 return false;
	};
	
	//批量修改积分
	var changeMoreBalance = function(){
		  event.stopPropagation();
		  var jsondata=$(this).serializeJson();
		  $.ajax( {
            "dataType": 'json', 
            "type": "POST", 
            "url": rootURI+"changeMoreUserPoints/"+selected.join()+"?rand="+Math.random(), 
            "data": $('#changeMoreBalanceForm').serialize(),
            "success": function(resp,status){
           	 if(status == "success"){  
           		 if(resp.status){
						 selected=[];
		            	 oTable.api().draw();
		            	 handleAlerts("Change the balance successfully.","success","","10");
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
		  $("#Change_More_Balance").modal("hide");
		   return false;
	};
	
	//提示信息处理方法（是在页面中指定位置显示提示信息的方式）
	var handleAlerts = function(msg,msgType,position,closeInSeconds) {         
        Metronic.alert({
            container: position, // alerts parent container(by default placed after the page breadcrumbs)
            place: "prepent", // append or prepent in container 
            type: msgType,  // alert's type (success, danger, warning, info)
            message: msg,  // alert's message
            close: true, // make alert closable
            reset: true, // close all previouse alerts first
            focus: false, // auto scroll to the alert after shown
            closeInSeconds: closeInSeconds, // auto close after defined seconds, 0 never close
            icon: "warning" // put icon before the message, use the font Awesone icon (fa-[*])
        });        

    };
    
    //修改积分验证方法
    var changeBalanceValidation = function() {
            var form = $('#changeBalanceForm');
            var errorDiv = $('.alert-danger', form);            
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                	amount: {
                		digits:true,
                        required: true,
                        min:0
                    }
                },
               invalidHandler: function (event, validator) { //display error alert on form submit              
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
                	changeBalance();
                }
            });
    };
    
  //批量修改积分验证方法
    var changeMoreBalanceValidation = function() {
            var form = $('#changeMoreBalanceForm');
            var errorDiv = $('.alert-danger', form);            

            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                	Amount: {
                		digits:true,
                        required: true,
                        min:0
                    }
                },
               invalidHandler: function (event, validator) { //display error alert on form submit              
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
                	changeMoreBalance();
                }
            });
    };
    
    //searchValidate
    var searchValidation = function() {
            var form = $('#searchForm');
            var errorDiv = $('.alert-danger', form);            
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                	first_points: {
                		digits:true,
                        min:0
                    },
                    end_points:{
                    	digits:true,
                        min:0
                    }
                },
               invalidHandler: function (event, validator) { //display error alert on form submit              
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
                	search();
                }
            });
    };
    
    //addValidate
    var addValidation = function() {
            var form = $('#addPointUserForm');
            var errorDiv = $('.alert-danger', form);            
            form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                	userId: {
                		 required: true
                    },
                    points:{
                    	digits:true,
                        min:0
                    }
                },
               invalidHandler: function (event, validator) { //display error alert on form submit              
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
                	addPointUser();
                }
            });
    };
    
    var addPointUser = function(){
    	$.ajax({
    		"type":"POST",
    		"dataType":"json",
    		"url":rootURI+"addPointUser?rand="+Math.random(),
    		"data":$("#addPointUserForm").serialize(),
    		"success":function(data){
    			if(data.status){
    				selected=[];
	            	oTable.api().draw();
    				handleAlerts("Add point user successfully.","success","","10");
    			}else{
    				handleAlerts(data.info,"danger","","10");
    			}
    		},
    		"error":function(XMLHttpRequest, textStatus, errorThrown){
           	 alert(errorThrown);
            }
    		
    	});
    	$("#add_pointuser").modal("hide");
    	return false;
    };
    
    return {
        //main function to initiate the module
        init: function (rootPath) {
        	rootURI=rootPath;
        	handleTable();  
       	    changeBalanceValidation();
       	    changeMoreBalanceValidation();
         	searchValidation();
         	addValidation();
        }

    };

}();