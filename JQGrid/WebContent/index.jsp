<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Table</title>
<link rel="stylesheet" type="text/css" href="assets/css/jquery-ui.css">
<!-- for autocomplete -->
<!-- <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<!-- <script src = "https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script src = "https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- internal lib -->
<script src="assets/js/jquery-1.7.2.min.js"></script>
<script src="assets/js/grid.locale-en.js"></script>
<script src="assets/js/jquery.jqGrid.min.js"></script>

<link rel="stylesheet" href="assets/css/ui.jqgrid.css">

<!-- css lib -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Add icon library -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="SubGrid" type="text/javascript">
	
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var lastsel;
		var suuGrid_id = "page"

		$("#list").jqGrid({
			mtype : "POST",
			datatype : "JSON",
			height : "auto",
			url : "Gridservlet",

			//editurl: "AddData",	
			//editurl: "EditData",
			//editurl: "DelData",
			editurl : "Gridservlet",

			colNames : [ 'Id', 'Name', 'Age', 'Location', 'Delete' ],

			colModel : [
			//{name : 'act', index : 'act', width : 62},
			{
				name : 'id',
				index : 'id',
				width : 200,
				editable : false,
				sortable : true
			}, {
				name : 'name',
				index : 'name',
				width : 200,
				editable : true,
				sortable : true
			}, {
				name : 'age',
				index : 'age',
				width : 200,
				editable : true,
				sortable : true
			}, {
				name : 'location',
				index : 'location',
				width : 200,
				editable : true,
				sortable : true
			}, {
				name : 'Delete',
				formatter : buttonFormatter,
				width : 50
			},

			],

			pager : '#page',
			rowNum : 10,
			rowList : [ 10, 40, 60 ],
			sortname : 'id',
			sortorder : 'desc',
			//cmTemplate : { firstsortorder : "desc" },
			viewrecords : true,
			gridview : true,
			multiselect : false,
			//                                         subGrid : true,
			caption : "JQGrid  DB",
			jsonReader : {
				repeatitems : false,
			},

			//editurl : "EditData",

			//for Button at first coloum
			/*  gridComplete: function(){
				var ids = jQuery("#list").jqGrid('getDataIDs');
				for(var i=0;i < ids.length;i++){
					var cl = ids[i];
					be = "<input style='height:22px;width:20px;' type='button' value='E' class='btn button' onclick=\"jQuery('#list').editRow('"+cl+"');\"  />"; 
					se = "<input style='height:22px;width:20px;' type='button' value='S' class='btn button' onclick=\"jQuery('#list').saveRow('"+cl+"');\"  />"; 
					ca = "<input style='height:22px;width:20px;' type='button' value='C' class='btn button' onclick=\"jQuery('#list').restoreRow('"+cl+"');\" />";
					
					jQuery("#list").jqGrid('setRowData',ids[i],{act:be+se+ca});
					
				}
				
			}, 
			editurl: "EditData",*/

			//for delete item on click
			/* 
			onSelectRow: function(id){
					Delete();
			       $("#grid").delRowData(id);
			      $("#grid").trigger('reloadGrid');
			}, 
			editurl: "DelData",*/

			//for sub type of rows
			/*  grouping:true,
			   	groupingView : {
			   		groupField : ['id'],
			   		groupColumnShow : [true],
			   		groupText : ['<b>{0} - {1} Item(s)</b>'],
			   		groupCollapse : true,
					groupOrder: ['desc']   		
			   	}, */

			editoptions : {
				dataInit : function(elem) {
					$(elem).focus(function() {
						this.select();
					})
				},
				dataEvents : [ {
					type : 'keydown',
					fn : function(e) {
						var key = e.charCode || e.keyCode;
						if (key == 9)//tab
						{

							jQuery('#list').jqGrid('restoreRow', lastsel);
							jQuery('#list').jqGrid('editRow', id, true);
							lastsel = id;
							EditData();

						}
					}
				} ]
			},

		//                                         	   	subGridRowExpanded: function(subgridId,rowId)
		//                                                 {

		//                                                 	var subgridtableid,pagerId;

		//                                             	   	subgridtableid=subgridId+"_t";
		//                                             	   	pagerId= "p_"+subgridtableid;

		//                                             	   	$("#"+subgridId).html("<table id='"+subgridtableid+"'class= 'scroll' style='width:100%'></table> <div id='" + pagerId+"'class ='scroll'></div>");

		//                                             	   	jQuery("#" + subgridtableid).jqGrid({

		//                                             	   		url:"SubGrid?rowId="+rowId,
		//                                             	   		mtype:"GET",
		//                                         				datatype: "JSON",
		//                                         				height: "auto",

		//                                         				colNames : [ 'Id', 'Name','Age'],

		//                                                     	colModel : [
		//                                                         	{name : 'id', index : 'id', width : 200,editable : false, sortable:true },
		//                                                         	{name : 'name', index : 'name', width : 200,editable : true, sortable:true },
		//                                                         	{name : 'age', index : 'age', width : 200,editable : true, sortable:true}
		//                                                         	],

		//                                                         	rowNum : 10,
		//                                                             viewrecords : true,
		//                                                             gridview : true,
		//                                                             jsonReader : {
		//                                                              	repeatitems : false,
		//                                                             },

		//                                             	   	});
		//                                             	   	$("#"+subgridtableid).jqGrid('navGrid',"#"+pagerId,
		//                                             	   	{
		//                                             	   		edit:false,
		//                                             	   		add:false,
		//                                             	   		del:false
		//                                             	   	})
		//                                                 },

		//Edit on Select time
		/*  onSelectRow: function(id){
		  
		   if(id != null)
		       {
		   	jQuery('#list').jqGrid('restoreRow',lastsel);
		       jQuery('#list').jqGrid('editRow',id,true);
		       lastsel=id;
		       EditData();
		       }
		   }*/

		});

		$('#list').navGrid('#page', {

			add : true,
			edit : false,
			del : true,
			refresh : true,
			view : false,
			search : true,
		},
		//for the Edit Dialog
		{
			editCaption : "The Edit Dialog",
			recreateForm : true,
			closeAfterEdit : true,
			reloadAfterSubmit : true,
			onclickSubmit : function(response, postdata) {

			}
		},
		//for the Add Dialog
		{
			addCaption : "The Add Dialog",
			recreateForm : true,
			closeOnEscape : true,
			closeAfterEdit : true,
			reloadAfterSubmit : true,
			onclickSubmit : function(response, postdata) {

				Add();
			}

		},

		//for the Delete Dialog
		{
			delCaption : "The Delete Dialog",
			recreateForm : true,
			closeAfterEdit : true,
			reloadAfterSubmit : true,
			onclickSubmit : function(response, postdata) {

				Delete();
			}
		},
		//for search option
		{
			recreateFilter : true
		});

	});

	$("#deldata").click(function() {

		var rowid = $("#list").jqGrid("getGridParam", "selrow");
		jQuery("#list").jqGrid("delRowData", rowid);
		alert(rowid);
		DelData();
	});

	function buttonFormatter(cellvalue, options, rowObject) {
		return '<button type="button" class="btn button" onClick=delFunction1();>D</button>';
	};

	function delFunction1() {

		var rowId = $("#list").jqGrid('getGridParam', 'selrow');

		//  var grid = $('#list');
		//  var sel_id = grid.jqGrid('getGridParam', 'selrow');
		//  var myCellData = grid.jqGrid('getCell', sel_id, 'Name');
		//  alert("Selected  Name: " + myCellData);
		if (rowId == null) {
			alert("Plase Select the row");
		} else {

			$.ajax({
				url : 'DelData',

				mtype : "service",
				data : {
					id : rowId,

				},
				success : function(data) {
					alert(data);
					$("#list").trigger("reloadGrid")
				}

			})
		}
	};

	function Add() {

		var id = $("#id").val();
		var name = $("#name").val();
		var age = $("#age").val();
		var location = $("#location").val();

		$.ajax({

			//editurl: "Gridservlet",
			type : "POST",
			data : {
				id : id,
				name : name,
				age : age,
				location : location,
			},
			url : 'AddData',
			success : function(data) {
				alert("Added Successfully.");
				$("#list").trigger("reloadGrid")
			}

		})
	};

	function Edit() {

		var rowId = $("#list").jqGrid('getGridParam', 'selrow');

		var id = $("#id").val();
		var name = $("#name").val();
		var age = $("#age").val();
		var location = $("#location").val();

		$.ajax({
			url : 'EditData',
			//editurl: "Gridservlet",
			type : "POST",
			data : {
				id : rowId,
				name : name,
				age : age,
				location : location,
			},
			success : function() {
				alert("Data Edited Successfully");
				$("#list").trigger("reloadGrid")
			}
		})
	};

	function Delete() {

		var rowId = $("#list").jqGrid('getGridParam', 'selrow');

		var id = $("#id").val();
		var name = $("#name").val();
		var age = $("#age").val();
		var location = $("#location").val();

		$.ajax({
			url : 'DelData',
			//editurl: "Gridservlet",
			type : "POST",
			data : {
				id : rowId,

			},
			success : function() {
				alert("Data DELETED Successfully");
				$("#list").trigger("reloadGrid")
			}

		})
	}

	function SubGird() {

		var rowId = $("#list").jqGrid('getGridParam', 'selrow');

		$.ajax({
			url : 'SubGrid',
			type : "POST",
			data : {
				id : rowId,
			},
			success : function() {
				alert("Subgrid");
				//$("#list_d").trigger("reloadGrid")
			}

		})
	}
</script>

<!--CSS  -->
<style>
.btn {
	background-color: white; /* Green */
	border: 2px solid #4CAF50;
	color: black;
	padding: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 4px 2px;
	cursor: pointer;
}

.button {
	border-radius: 10px;
}

.btn:hover {
	background-color: #FEEB75;
	color: white;
}
</style>
<script type="text/javascript">
	$("#studentId").autocomplete(
	{
        source:  ["het","prachit","dilip","chetna","anee","hardi"]
	},
	{
		autoFocus: true,
		minLength: 1
	});
</script>
</head>
<body>
	<div>
		<label><b>Enter Student</b>
		<input type="text" name="student" id="studentId">
		</label>
	</div>

	<table id="list">
		<tr>
			<td />
		</tr>
	</table>
	<div id="page"></div>

	<!-- <input type="BUTTON" id="add" value="Add" onclick="Addbtn()" class="btn button" /> -->
	<!-- <input type="BUTTON" id="edit" value="Edit"  onclick="Editbtn()" class ="btn button" /> -->
	<!-- <input type="BUTTON" id="deldata" value="Delete" onclick="Delete()" class = "btn button"/> -->

	<!--for subgrid  -->
	<!-- <table id="list_sub"><tr><td/></tr></table>
<div id="page_sub"></div> -->


</body>
</html>