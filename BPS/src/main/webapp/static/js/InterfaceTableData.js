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
                    	return '<div class="actions"><a class="btn btn-default btn-sm" data-toggle="modal"  href="#view_interface" >view</a></div>';
                    },
                    'class':'center'
                }
            ],
            "columns": [
               {"orderable": false },
	           { title: "Interface Name",   data: "name" },
	           { title: "Describe",   data: "descr" },
	           { title: "Action" ,"class":"center"}
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"interfaceList?rand="+Math.random(),
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
       	    
        }

    };

}();