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
var RulesTable = function () {
	var oTable;
	var selected = [];
	var handleTable = function () {
		var oLogTables;
		
		var viewTable = function(ids){
			
			var table=$('#ruleslog_table');
			oLogTables = table.dataTable({
				"lengthChange":false,
		    	"filter":false,
		    	"sort":false,
		    	"info":true,
		    	"bRetrieve": true,
		    	"processing":true,
		        // set the initial value
		        "displayLength": 3,
		        "dom": "t<'row'<'col-md-6'i><'col-md-6'p>>",
//		        "sPaginationType": "bootstrap_full_number",   //bootstrap_extended
//		        "oLanguage": {
//		            "sLengthMenu": "_MENU_ records per page",
//		            "oPaginate": {
//		                "sPrevious": "Prev",
//		                "sNext": "Next",
//		            	"zeroRecords": "No records to display"
//		            }
//		        },
		     /*   
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
		            	return '<div class="actions"><button><a  data-toggle="modal"  href="#view_log" id="openrluesviewmodal">view</a></button></div>';
		            },
		            'class':'center'
		        }
		    ],
		    */
		    "columns": [
		    //   {"orderable": false },
		       { title: "ID",   data: "id" },
		       { title: "Rule ID",   data: "ruleId" },
		       { title: "Content",  data: "content"},
		       { title: "Create Time", data: "createdTimeStr" },
		      // { title: "Action" ,"class":"center"}
		    ],
		     	        "serverSide": true,
		     	        "serverMethod": "GET",
		     	        "ajaxSource": rootURI+"ruleslogList/"+ids+"?rand="+Math.random(),
		     	       //"ajaxSource": rootURI+"ruleslogList?rand="+Math.random(),
//		        "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
//		           $.ajax( {
//		             "dataType": 'json', 
//		             "type": "POST", 
//		             "url": sSource, 
//		             "data": aoData,
////		             "contentType":"application/json",
//		             "success": function(resp){ 		            	
//		            	 fnCallback(resp);
//		             },
//		             "error":function(XMLHttpRequest, textStatus, errorThrown){
//		            	 alert(errorThrown);
//		             }
//		           } );
//		         },
//		        "fnServerParams": function ( aoData ) {
//		           aoData.push( { "name": "more_data", "value": "my_value" } );
//		         },
//		        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {				
//		        	$('td:eq(0)', nRow).html( '<input type="checkbox" class="checkboxes" value="1"/>' );
//					return nRow;
//				},

			});	
		};
		
	
		var table=$('#rules_table');
			oTable = table.dataTable({
			"lengthChange":false,
        	"filter":true,
        	"sort":false,
        	"info":true,
        	"processing":true,
        	"scrollX":"100%",
           	"scrollXInner":"100%",
        	//"bDestroy":true,
            // set the initial value
            "displayLength": 10,
            "dom": "t<'row'<'col-md-6'i><'col-md-6'p>>",
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
                    	return '<div class="actions"><a class="btn btn-default btn-sm" data-toggle="modal"  href="#view_log" id="openrluesviewmodal">view</a></div>';
                    },
                    'class':'center'
                }
            ],
            "columns": [
               {"orderable": false },
	           { title: "Rule Id",   data: "ruleId" },
	           { title: "Rule Name",   data: "ruleName" },
	           { title: "Rule Group",   data: "groupName" },
	           { title: "Rule Iutput",  data: "ruleInput"},
	           { title: "Rule Output", data: "ruleOutput" },
	           { title: "descr", data: "descr" },
	           { title: "Status",    data: "status" },
	           { title: "Action" ,"class":"center"}
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"rulesList?rand="+Math.random(),
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
			
		//打开删除对话框前判断是否已选择要删除的行
		$("#openDeleteRulesModal").on("click",function(event){
				if(selected.length==0){
					handleAlerts("Please select the rows which you want to delete.","warning","");				
					return false;
				}
			});
		$("#openActiveRulesModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts("Please select the rows which you want to Active.","warning","");				
				return false;
			}
		});
		$("#openDeactiveRulesModal").on("click",function(event){
			if(selected.length==0){
				handleAlerts("Please select the rows which you want to deactive.","warning","");				
				return false;
			}
		});
		//删除操作
		$('#deleteBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "DELETE", 
             "url": rootURI+"rules/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 oTable.$('th span').removeClass();
		            	 handleAlerts("delete the rules successfully.","success","");
					 }
					 else{
						 handleAlerts("Failed to delete the data. " +data.info,"danger","");
					 }
				}             	 
             },
             "error":function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(errorThrown);
             }
           });
        }); 
		
		//搜索表单提交操作
		/*$("#searchForm").on("submit", function(event) {
			event.preventDefault();
			var jsonData=$(this).serializeJson();
			var jsonDataStr=JSON.stringify(jsonData);			
			oTable.fnFilter(jsonDataStr);
			return false;
		});
		*/
		//搜索积分规则
		 var search = function(){
		    	event.preventDefault();
				var jsonData=$("#searchForm").serializeJson();
				var jsonDataStr=JSON.stringify(jsonData);			
				oTable.fnFilter(jsonDataStr);
				return false;
		    };
		
		//激活规则
		$('#activateBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"activaterules/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	oTable.api().draw();
		            	oTable.$('th span').removeClass();
		            	 handleAlerts("activateBtn the rules successfully.","success","");
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
		//禁用规则
		$('#deactivateBtn').on('click', function (e) {
			$.ajax( {
             "dataType": 'json', 
             "type": "POST", 
             "url": rootURI+"deactivaterules/"+selected.join(), 
             "success": function(data,status){
            	 if(status == "success"){					
					 if(data.status){
						 selected=[];						 
		            	 oTable.api().draw();
		            	 oTable.$('th span').removeClass();
		            	 handleAlerts("deactivateBtn the rules successfully.","success","");
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
		

		
		$("#openEditRulesModal").on("click",function(event){
			if(selected.length>1){
				handleAlerts("Only one row can be edited.","warning","");
				event.stopPropagation();
			}else if(selected.length==0)
			{
				handleAlerts("Please choose one row.","warning","");
				event.stopPropagation();
			}
			else{
				var data = oTable.api().row($("tr input:checked").parents('tr')).data();
	            var ruleName = data.ruleName;
	            var ruleInput = data.ruleInput;
	            var ruleOutput = data.ruleOutput;
	            var descr = data.descr;
	            var ruleId =data.ruleId;
	            $("#editRulesForm input[name='ruleName']").val(ruleName);
	            $("#editRulesForm input[name='ruleInput']").val(ruleInput);
	            $("#editRulesForm input[name='ruleOutput']").val(ruleOutput);
	            $("#editRulesForm input[name='descr']").val(descr);
	            $("#editRulesForm input[name='ruleId']").val(ruleId);
			}
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
	            	var id = data.ruleId;
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
            var id = data.ruleId;
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
   
        
        table.on('click', 'tbody tr a',function(){
            var data = oTable.api().row($(this).parents('tr')).data();
           var ids=data.ruleId;
           if(oLogTables!=null){
        	   oLogTables.fnDestroy();
        	   viewTable(ids);
           }else
        	   viewTable(ids);
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
    
    var AddRules = function(){
    	$.ajax( {
            "dataType": 'json', 
            "type":'POST', 
            "url": rootURI+"addrules", 
            "data": $('#addRulesForm').serialize(),
//            "processData":false,
//            "contentType":"application/json",
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
			$("#add_rules").modal("hide");
    };
    //处理表单验证方法
    var addFormValidation = function() {
            var addform = $('#addRulesForm');
            var errorDiv = $('.alert-danger', addform);            
            addform.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                	ruleName: {
                        minlength: 4,
                        required: true
                    },
                    ruleInput: {
                    	digits:true,
                        required: true,
                        maxlength:60                        
                    },
                    ruleOutput: {
                    	digits:true,
                        required: true,
                        maxlength:60                         
                    },
                    descr: {
                        required: true,
                        maxlength:1000,
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
                onfocusout:function(element){
                	$(element).valid();
                },

                submitHandler: function (form) {                	
                    errorDiv.hide();
                    AddRules();
                }
            });
    };
   
    
  //处理表单验证方法
    var addFormValidation = function() {
            var addform = $('#addRulesForm');
            var errorDiv = $('.alert-danger', addform);            
            addform.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                	ruleName: {
                        minlength: 4,
                        required: true
                    },
                    ruleInput: {
                    	digits:true,
                        required: true,
                        maxlength:60                        
                    },
                    ruleOutput: {
                    	digits:true,
                        required: true,
                        maxlength:60                         
                    },
                    descr: {
                        required: true,
                        maxlength:1000,
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
                onfocusout:function(element){
                	$(element).valid();
                },

                submitHandler: function (form) {                	
                    errorDiv.hide();
                    AddRules();
                }
            });
    };
    
    var EditRules = function(){
    	$.ajax( {
            "dataType": 'json', 
            "type": "POST", 
            "url": rootURI+"editrules", 
            "data": $('#editRulesForm').serialize(),
//            "processData":false,
//            "contentType":"application/json",
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
		  $("#edit_rules").modal("hide");

    };
    //处理表单验证方法
    var editFormValidation = function() {
            var addform = $('#editRulesForm');
            var errorDiv = $('.alert-danger', addform);            
            addform.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",  // validate all fields including form hidden input                
                rules: {
                	ruleName: {
                        minlength: 4,
                        required: true
                    },
                    ruleInput: {
                    	digits:true,
                        required: true,
                        maxlength:60                        
                    },
                    ruleOutput: {
                    	digits:true,
                        required: true,
                        maxlength:60                         
                    },
                    descr: {
                        required: true,
                        maxlength:1000,
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
                onfocusout:function(element){
                	$(element).valid();
                },

                submitHandler: function (form) {                	
                    errorDiv.hide();
                    EditRules();
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
            	ruleId: {
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
    return {
        //main function to initiate the module
        init: function (rootPath) {
        	rootURI=rootPath;
        	handleTable();  
        	addFormValidation();
        	editFormValidation();
        	searchValidation();
        }

    };

}();