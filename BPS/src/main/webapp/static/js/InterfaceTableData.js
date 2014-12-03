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
var InterfaceTable = function () {
	var oTable;
	var selected;
	var handleTable = function () {
		selected = [];
		var oInparamTable;
		var oOutparamTable;
		
		var table=$('#interface_table');
		oTable = table.dataTable({
			    "lengthChange":false,
				"filter":false,
				"sort":false,
				"info":true,
				"bRetrieve": true,
				"processing":true,
				"bDestroy":true,
                "displayLength": 3,
				"dom": "t<'row'<'col-md-6'i><'col-md-6'p>>",
				//"columns": [
	 	        //   { title: "Interface Name",   data: "name" },
	 	        //   { title: "Describe", data: "descr"}
	 	        //   ],
	 	          // "serverSide": true,
	 	          // "serverMethod": "GET",
	 	         //  "ajaxSource": rootURI+"interfaceList?rand="+Math.random()
	 	        });
		 var inparamTable = function(ids){		
			var logTable=$('#inparam_table');
				oInparamTable = logTable.dataTable({
					"lengthChange":false,
					"filter":false,
					"sort":false,
					"info":true,
					"bRetrieve": true,
					"processing":true,
					"bDestroy":true,
	        // set the initial value
					"displayLength": 3,
					"dom": "t<'row'<'col-md-6'i><'col-md-6'p>>",
					"columns": [
		 	           { title: "ID",   data: "id" },
		 	           { title: "Param Name",   data: "name" },
		 	           { title: "Type",  data: "type"},
		 	           { title: "Describe", data: "description"},
		 	           ],
		 	           "serverSide": true,
		 	           "serverMethod": "GET",
		 	           "ajaxSource": rootURI+"interfaceinparam/"+ids+"?rand="+Math.random()
					});	
			};
	
			var outparamTable = function(ids){		
				var logTable=$('#outparam_table');
				oOutparamTable = logTable.dataTable({
					"lengthChange":false,
			    	"filter":false,
			    	"sort":false,
			    	"info":true,
			    	"bRetrieve": true,
			    	"processing":true,
			    	"bDestroy":true,
			        // set the initial value
			        "displayLength": 3,
			        "dom": "t<'row'<'col-md-6'i><'col-md-6'p>>",
			        "columns": [
			                    { title: "ID",   data: "id" },
					 	        { title: "Param Name",   data: "name" },
					 	        { title: "Type",  data: "type"},
					 	        { title: "Describe", data: "description"},
			 	        ],
	     	        "serverSide": true,
	     	        "serverMethod": "GET",
	     	        "ajaxSource": rootURI+"interfaceoutparam/"+ids+"?rand="+Math.random()
				});	
			};
		
			table.on('click', 'tbody tr a',function(){
	            var data = oTable.api().row($(this).parents('tr')).data();
	           var ids=data.name;
	           var descr=data.descr;
	            $("#interfaceinfoForm input[name='descr']").val(descr);
	           if(oInparamTable!=null){
	        	   oInparamTable.fnDestroy();
	        	   inparamTable(ids);  
	           }else{
	        	   inparamTable(ids);  
	           }
	           if(oOutparamTable!=null){
	        	   oOutparamTable.fnDestroy();
	        	   outparamTable(ids);  
	           }else{
	        	   outparamTable(ids);
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
		            	var id=data.name;
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
            var id = id=data.name;
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
        
        $("#openEditinterfaceModal").on("click",function(event){
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
	            var name = data.name;
	            var descr =data.descr;
	            $("#editInterfaceForm input[name='name']").val(name);
	            $("#editInterfaceForm input[name='descr']").val(descr);
			}
		});
        $("#openAddParamModal").on("click",function(event){
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
	            var name = data.name;
	           
	            $("#addInterfaceparamForm input[name='t.name']").val(name);
	           
			}
		});
      //删除操作
		$('#deleteBtn').on('click', function (e) {
			
			$.ajax( {
             "dataType": 'json', 
             "type": "DELETE", 
             "url": rootURI+"interface/"+selected.join(), 
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
        /* handle show/hide columns*/
        var tableColumnToggler = $('#column_toggler');		
		$('input[type="checkbox"]', tableColumnToggler).change(function () {
		    /* Get the DataTables object again - this is not a recreation, just a get of the object */
		    var iCol = parseInt($(this).attr("data-column"));
		    var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
		    oTable.fnSetColumnVis(iCol, (bVis ? false : true));
		});
 };
	
    
 	//搜索表单提交操作
	$("#searchForm").on("submit", function(event) {
		event.preventDefault();
		var jsonData=$(this).serializeJson();
		var jsonDataStr=JSON.stringify(jsonData);			
		oTable.fnFilter(jsonDataStr);
		return false;
	});	
    //添加操作
	var AddInterface = function(){
		event.stopPropagation();
		$.ajax( {
         "dataType": 'json', 
         "type":'POST', 
         "url": rootURI+"addinterface", 
         "data": $('#addInterfaceForm').serialize(),
         "success": function(resp,status){
        	 if(status == "success"){  
        		 if(resp.status){						 
	            	 oTable.api().draw();
	            	 handleAlerts("Added the data successfully.","success","");		            	 
				 }
				 else{
					 handleAlerts("Failed to add the data."+resp.info,"danger","");						 
				 }
			}             	 
         },
         "error":function(XMLHttpRequest, textStatus, errorThrown){
        	 alert(errorThrown);
         }
       });
    };
    var AddInterfaceParam = function(){
		event.stopPropagation();
		$.ajax( {
         "dataType": 'json', 
         "type":'POST', 
         "url": rootURI+"addinterfaceparam", 
         "data": $('#addInterfaceparamForm').serialize(),
         "success": function(resp,status){
        	 if(status == "success"){  
        		 if(resp.status){						 
	            	 oTable.api().draw();
	            	 handleAlerts("Added the data successfully.","success","");		            	 
				 }
				 else{
					 handleAlerts("Failed to add the data."+resp.info,"danger","");						 
				 }
			}             	 
         },
         "error":function(XMLHttpRequest, textStatus, errorThrown){
        	 alert(errorThrown);
         }
       });
    };
    var EditInterface = function(){
		event.stopPropagation();
		$.ajax( {
         "dataType": 'json', 
         "type":'POST', 
         "url": rootURI+"editinterface", 
         "data": $('#editInterfaceForm').serialize(),
         "success": function(resp,status){
        	 if(status == "success"){  
        		 if(resp.status){						 
	            	 oTable.api().draw();
	            	 handleAlerts("Added the data successfully.","success","");		            	 
				 }
				 else{
					 handleAlerts("Failed to add the data."+resp.info,"danger","");						 
				 }
			}             	 
         },
         "error":function(XMLHttpRequest, textStatus, errorThrown){
        	 alert(errorThrown);
         }
       });
    };
	
    var AddInterfaceValidation = function() {
        var form = $('#addInterfaceForm');
        var errorDiv = $('.alert-danger', form);            
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input                
            rules: {
             name: {
            	required: true,
            	minlength:4,
                		},
            
        	 descr: {
        		required: true,
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
            onfocusout:function(element){
            	$(element).valid();
            },
            submitHandler: function (form) { 
            	errorDiv.hide();
            	AddInterface();
            }
        });
    };
    var AddInterfaceparamValidation = function() {
        var form = $('#addInterfaceparamForm');
        var errorDiv = $('.alert-danger', form);            
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input                
            rules: {
             name: {
            	required: true,
            	minlength:3,
                		},
            
                descr: {
        		required: true,
    				},
    			type: {
                required: true,
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
            onfocusout:function(element){
            	$(element).valid();
            },
            submitHandler: function (form) { 
            	errorDiv.hide();
            	AddInterfaceParam();
            }
        });
    };
    var EditInterfaceValidation = function() {
        var form = $('#editInterfaceForm');
        var errorDiv = $('.alert-danger', form);            
        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input                
            rules: {
             name: {
            	required: true,
            	minlength:4,
                		},
            
        	 descr: {
        		required: true,
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
            onfocusout:function(element){
            	$(element).valid();
            },
            submitHandler: function (form) { 
            	errorDiv.hide();
            	EditInterface();
            }
        });
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
    
  
 
    
    
    return {
        //main function to initiate the module
        init: function (rootPath) {
        	rootURI=rootPath;
        	handleTable();  
        	AddInterfaceValidation();
        	EditInterfaceValidation();
        	AddInterfaceparamValidation();
        }

    };

}();