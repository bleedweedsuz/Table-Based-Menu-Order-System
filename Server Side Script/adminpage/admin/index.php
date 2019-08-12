<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Admin_Manager</title>
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
  <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script>
  $(function() {
		$( "#tPage" ).tabs({beforeLoad: function( event, ui ) {ui.jqXHR.fail(function() {ui.panel.html(":( sorry there is something wrong in server!");});}});
	});
  </script>
</head>
<body>
 
<div id="tPage">
  <ul>
    <li><a href="gallery.php">Gallery</a></li>
	<li><a href="user.php">Users</a></li>
	<li><a href="item.php">Item's</a></li>
	<li><a href="restaurant.php">Restaurant Info</a></li>
  </ul>
</div>
</body>
</html>