<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Google jQuery file -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Bootstrap file -->
<link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<!-- Call Modal Button -->
	<button class="nav-link" data-remote="loginForm.jsp"
		data-toggle="modal" data-target="#theModal">Modal</button>

	<!-- Modal -->
	<div class="modal fade" id="theModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"></div>
				<div class="modal-body">... remote content from "data-remote"
					loads here ...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Call LoginForm.jsp Script -->
	<script>
		$('#theModal').on('show.bs.modal', function(e) {
	
			var button = $(e.relatedTarget);
			var modal = $(this);
			
			modal.find('.modal-body').load(button.data("remote"));
	
		});
	</script>
</body>
</html>