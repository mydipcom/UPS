var RightsTable = function () {
	var handleTable = function () {
		var table=$('#rights_table');
		var oTable = table.dataTable({
			"bLengthChange":false,
        	"bFilter":false,
        	"bSort":true,
        	"bInfo":true,
        	"bProcessing":true,                
            // set the initial value
            "iDisplayLength": 10,
            "sDom": "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-6'i><'col-md-6'p>>",
            //"sPaginationType": "bootstrap",
            "oLanguage": {
                "sLengthMenu": "_MENU_ records per page",
                "oPaginate": {
//                    "sPrevious": "Prev",
//                    "sNext": "Next",
                	"zeroRecords": "No records to display"
                }
            },
            "aoColumnDefs": [{
                    'bSortable': false,
                    'aTargets': [0]
                }
            ],
            "aoColumns": [
	           { "sTitle": "ID",   "mData": "nodeId" },
	           { "sTitle": "Rights Name",  "mData": "name" },
	           { "sTitle": "URI", "mData": "uri" },
	           { "sTitle": "Request Method", "mData": "method" },
	           { "sTitle": "Parent ID",  "mData": "pid" },
	           { "sTitle": "Is Menu",    "mData": "isMenu" },
	           { "sTitle": "Group Name",    "mData": "groupName" },
	           { "sTitle": "Group Sort",    "mData": "groupSort" },
	           { "sTitle": "Status",    "mData": "status" }
	        ],
	        "bServerSide": true,
	        "sServerMethod": "POST",
	        "sAjaxSource": "/bps/rightsList?rand="+Math.random(),
//	        "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
//	           $.ajax( {
//	             "dataType": 'json', 
//	             "type": "POST", 
//	             "url": sSource, 
//	             "data": aoData,
//	             "contentType":"application/json",
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
//	         } 
		})
		
		$('#rights_table a.delete').live('click', function (e) {
            e.preventDefault();

            if (confirm("Are you sure to delete this row ?") == false) {
                return;
            }

            var nRow = $(this).parents('tr')[0];
            oTable.fnDeleteRow(nRow);
            alert("Deleted! Do not forget to do some ajax to sync with backend :)");
        })  
        
        
	};

    return {

        //main function to initiate the module
        init: function () {
        	handleTable();
        }

    };

}();