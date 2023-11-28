<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/dashboard.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/1.2.1/css/buttons.dataTables.min.css" />
<Script src="https://code.jquery.com/jquery-1.12.3.js"
	type="text/javascript"></Script>
<Script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"
	type="text/javascript"></Script>
<Script
	src="https://cdn.datatables.net/buttons/1.2.1/js/dataTables.buttons.min.js"
	type="text/javascript"></Script>
<Script
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"
	type="text/javascript"></Script>
<Script
	src="https://cdn.datatables.net/buttons/1.2.1/js/buttons.html5.min.js"
	type="text/javascript"></Script>

<script>
	$(document).ready(function() {
		$(document).ready(function() {
			$('table').DataTable({
				dom : 'Blfrtip',
				buttons : [ {
					text : 'Export To Excel',
					extend : 'excelHtml5',
					exportOptions : {
						modifier : {
							selected : true
						},
						columns : [ 0, 1, 2, 3 ],
						format : {
							header : function(data, columnIdx) {
								return data;
							},
							body : function(data, column, row) {
								// Strip $ from salary column to make it numeric
								debugger;
								return column === 4 ? "" : data;
							}
						}
					},
					footer : false,
					customize : function(xlsx) {
						var sheet = xlsx.xl.worksheets['sheet1.xml'];
						//$('c[r=A1] t', sheet).text( 'Custom text' );
						//$('row c[r^="C"]', sheet).attr('s', '2');
					}
				} ]
			});
		});

	});
</script>