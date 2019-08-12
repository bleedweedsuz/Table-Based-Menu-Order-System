<?php 
class Connection {
		function Connect(){
			$username="suzworks_euser";
			$password="7lxLt)]8?1Af";
			$localhost="localhost";
			$database="suzworks_emenu";
			try{
				mysql_connect($localhost,$username,$password) or die(mysql_error());
				mysql_select_db($database);
			}
			catch(Exception $ex)
			{
				echo 'No connection';
			}
		}
}
class userDetail extends Connection{
		function __construct(){
			parent::Connect();
		}
		function Insert($userName,$userPassword,$userType,$uName,$uAddress,$uPhone){
			try{
				$qry = "Insert into userdetail values ('','$userName','$userPassword','$userType','$uName','$uAddress','$uPhone')";
				if(mysql_query($qry)){
					echo 'Inserted';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Delete($uID){
			try{
				$qry = "delete from userdetail where uID = '$uID'";
				if(mysql_query($qry)){
					echo 'Deleted';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Update($uID,$userName,$userPassword,$userType,$uName,$uAddress,$uPhone){
			try{
				$qry = "update userdetail set userName='$userName',userPassword='$userPassword',userType='$userType',uName='$uName',uAddress='$uAddress',uPhone='$uPhone' where uID ='$uID'";
				if(mysql_query($qry)){
					echo 'Updated';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Select(){
			try{
				$flag =false;
				$qry = "select * from userdetail";
				$Select = mysql_query($qry);
				while($row = mysql_fetch_array($Select)){
					$flag = true;
					$jsonData[] = array('uID'=>$row['uID'],'userName'=>$row['userName'],'userPassword'=>$row['userPassword'],'userType'=>$row['userType'],'uName'=>$row['uName'],'uAddress'=>$row['uAddress'],'uPhone'=>$row['uPhone']);
				}
				if($flag){ 
					echo json_encode($jsonData);
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function SelectUser($uName,$uPassword){
			try{
				$qry="select * from userdetail where userName='$uName' and userPassword='$uPassword'";
				$select= mysql_fetch_array(mysql_query($qry));
				if($select){
					if($select["userType"] =="Admin"){
						echo "1";
					}
					else if($select["userType"] =="Other"){
						echo "2";
					}
					else{
						echo "0";
					}
				}
				else{
					echo "0";
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function MakeList(){
			echo '<table width="100%">
					<tr>
						<td width="13%" class="UserDetailsInfoHeader"><div align="center">User ID</div></td>
						<td width="13%" class="UserDetailsInfoHeader"><div align="center">User Name</div></td>
						<td width="13%" class="UserDetailsInfoHeader"><div align="center">Password</div></td>
						<td width="13%" class="UserDetailsInfoHeader"><div align="center">User Type</div></td>
						<td width="13%" class="UserDetailsInfoHeader"><div align="center">Full Name</div></td>
						<td width="13%" class="UserDetailsInfoHeader"><div align="center">Address</div></td>
						<td width="13%" class="UserDetailsInfoHeader"><div align="center">Phone No</div></td>
						<td width="13%" class="UserDetailsInfoHeader"><div align="center">Edit</div></td>
						<td width="13%" class="UserDetailsInfoHeader"><div align="center">Delete</div></td>
					</tr>';
			//Query userdetails
			$qry="select * from userdetail order by uID desc";
			$select =mysql_query($qry);
			while($row = mysql_fetch_array($select)){
				$ID = $row[0];
				echo '  <script>
							$("#editUser'.$ID.'").button().click(function( event ) {
							    $("#uID").val("'.$row[0].'");
								$("#uName").val("'.$row[1].'");
								$("#uPassword").val("'.$row[2].'");
								$("#uFName").val("'.$row[4].'");
								$("#uAddress").val("'.$row[5].'");
								$("#uPhoneno").val("'.$row[6].'");
								$("#uType").val("'.$row[3].'");
							});
							$("#delUser'.$ID.'").button().click(function( event ) {
								$.ajax({
									type:"POST",
									url:"ajax/userAjax.php?uDelete='.$ID.'",
									success:function(msg){
										//LoadGalleryListData();//Load GalleryListData
										$("#userListTr'.$ID.'").hide();
									}
								});
							});
						</script>
					';
				echo '
					<tr id="userListTr'.$ID.'" style="font-size:14px;border-bottom-width: thin;border-bottom-style: solid;border-bottom-color: #DBDBDB;">
						<td class="UserDetailsInfoList"><div align="center">'.$row[0].'</div></td>
						<td class="UserDetailsInfoList"><div align="center">'.$row[1].'</div></td>
						<td class="UserDetailsInfoList"><div align="center">'.$row[2].'</div></td>
						<td class="UserDetailsInfoList"><div align="center">'.$row[3].'</div></td>
						<td class="UserDetailsInfoList"><div align="center">'.$row[4].'</div></td>
						<td class="UserDetailsInfoList"><div align="center">'.$row[5].'</div></td>
						<td class="UserDetailsInfoList"><div align="center">'.$row[6].'</div></td>
						<td class="UserDetailsInfoList">
							<div align="center"><input type="submit" id="editUser'.$ID.'" value="edit"></div>
						</td>
						<td class="UserDetailsInfoList">
							<div align="center"><input type="submit" id="delUser'.$ID.'" value="del"></div>
						</td>
					</tr>
				  ';
			  }
			  echo '</table>';
		}
}
class foodItem extends Connection{
		function __construct(){
			parent::Connect();
		}
		function Insert($fName,$fPrice,$fDescription,$galleryID,$CID){
			try{
				$qry = "Insert into food_item values ('','$fName','$fPrice','$fDescription','$galleryID','$CID')";
				if(mysql_query($qry)){
					echo 'Inserted';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Delete($foodID){
			try{
				$qry = "delete from food_item where foodID = '$foodID'";
				if(mysql_query($qry)){
					echo 'Deleted';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Update($foodID,$fName,$fPrice,$fDescription,$galleryID,$CID){
			try{
				$qry = "update food_item set fName='$fName',fPrice='$fPrice',fDescription='$fDescription',galleryID='$galleryID',CID='$CID' where foodID ='$foodID'";
				if(mysql_query($qry)){
					echo 'Updated';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Select($CID){
			try{
				$flag =false;
				$qry = "select * from food_item where CID ='$CID'";
				$Select = mysql_query($qry);
				while($row = mysql_fetch_array($Select)){
					$flag = true;
					$gID = $row['galleryID'];
					$qry2="select `image` from photo where pID=(select MAX(pID) from photo where galleryID='$gID')";
					$select2=mysql_fetch_array(mysql_query($qry2));
					$data ="";
					if($select2){
						$data = $select2[0];
					}
					$jsonData[] = array('foodID'=>$row['foodID'],'fName'=>$row['fName'],'fPrice'=>$row['fPrice'],'fDescription'=>$row['fDescription'],
						'photo'=>$data,'CID'=>$row['CID'],'galleryID'=>$row['galleryID']);
				}
				if($flag){ 
					echo json_encode($jsonData);
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function SelectFoodItemsList(){
			try{
				echo '<table width="100%">
						<tr>
							<td width="13%" class="tableDetailsInfoHeader"><div align="center">FoodID</div></td>
							<td width="13%" class="tableDetailsInfoHeader"><div align="center">Name</div></td>
							<td width="13%" class="tableDetailsInfoHeader"><div align="center">Price</div></td>
							<td width="13%" class="tableDetailsInfoHeader"><div align="center">Category</div></td>
							<td width="13%" class="tableDetailsInfoHeader"><div align="center">Gallery</div></td>
							<td width="13%" class="tableDetailsInfoHeader"><div align="center">Edit</div></td>
							<td width="13%" class="tableDetailsInfoHeader"><div align="center">Delete</div></td>
						</tr>';
				$qry = "select * from food_item left outer join gallery_info on food_item.galleryID = gallery_info.galleryID order by food_item.foodID desc";
				$Select = mysql_query($qry);
				while($row = mysql_fetch_array($Select)){
					$ID = $row['foodID'];
					echo '<script>
						$("#EditFood'.$ID.'").button().click(function(event){							
							$("#fName").val("'.$row['fName'].'");
							$("#fDescription").val("'.$row['fDescription'].'");
							$("#fPrice").val("'.$row['fPrice'].'");
							$("#galleryID").val("'.$row['galleryID'].'");
							$("#fID").val("'.$row['foodID'].'");
							$("#fCategory").val("'.$row['CID'].'");
						});
						$("#DeleteFood'.$ID.'").button().click(function(event){
							$.ajax({
								type:"POST",
								url:"ajax/foodAjax.php?fDelete='.$ID.'",
								beforeSend:function(){
									$("#loadingFood").slideDown();
									$("#loadingFood").html("Deleting");
								},
								success:function(msg){
									$("#loadingFood").slideUp(2000);
									$("#deleteEditTr'.$ID.'").hide();
								}
							});
						});
					  </script>';
					echo '<tr id="deleteEditTr'.$ID.'" style="font-size:14px;border-bottom-width: thin;border-bottom-style: solid;border-bottom-color: #DBDBDB;">
							<td width="13%" class="tableDetailsInfoList"><div align="center">'.$row['foodID'].'</div></td>
							<td width="13%" class="tableDetailsInfoList"><div align="center">'.$row['fName'].'</div></td>
							<td width="13%" class="tableDetailsInfoList"><div align="center">Rs.'.$row['fPrice'].'/-</div></td>
							<td width="13%" class="tableDetailsInfoList"><div align="center">'.$row['CID'].'</div></td>
							<td width="13%" class="tableDetailsInfoList"><div align="center">'.$row['galleryName'].'</div></td>							
							<td width="23%" class="tableDetailsInfoList"><div align="center">
								<input type="submit" id="EditFood'.$ID.'" style="margin:4px;" value="edit"></div>
							</td>
							<td width="23%" class="tableDetailsInfoList"><div align="center">
								<input type="submit" id="DeleteFood'.$ID.'" style="margin:4px;" value="del"></div>
							</td>
						</tr>';
				}
				echo '</table>';
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
	}
class itemCategory extends Connection{
	function __construct(){
		parent::Connect();
	}
	function Insert($cName,$pID){
		try{
			$qry = "Insert into item_category values ('','$cName','$pID')";
			if(mysql_query($qry)){
				echo 'Inserted';
			}
			else{
				echo 'Error';
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function Delete($CID){
		try{
			$qry = "delete from item_category where CID = '$CID'";
			if(mysql_query($qry)){
				echo 'Deleted';
			}
			else{
				echo 'Error';
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function Update($CID,$cName,$pID){
		try{
			$qry = "update item_category set cName='$cName',pID='$pID' where CID ='$CID'";
			if(mysql_query($qry)){
				echo 'Updated';
			}
			else{
				echo 'Error';
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function Select(){
		try{
			$flag =false;
			$qry = "select item_category.CID,item_category.cName,photo.image from item_category inner join photo on item_category.pID=photo.pID";
			$Select = mysql_query($qry);
			while($row = mysql_fetch_array($Select)){
				$flag = true;
				$jsonData[] = array('CID'=>$row['CID'],'cName'=>$row['cName'],'photo'=>$row['image']);
			}
			if($flag){ 
				echo json_encode($jsonData);
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function SelectAsOptions(){
		try{
			$qry = "select * from item_category";
			$Select = mysql_query($qry);
			while($row = mysql_fetch_array($Select)){
				echo '
					<option value="'.$row['CID'].'">'.$row['cName'].'</option>
				';
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function SelectItemCategory(){
		try{
			echo '
				<table width="100%">
				<tr>
					<td width="23%" class="tableDetailsInfoHeader"><div align="center">Category ID</div></td>
					<td width="23%" class="tableDetailsInfoHeader"><div align="center">Name</div></td>
					<td width="23%" class="tableDetailsInfoHeader"><div align="center">Icon ID</div></td>
					<td width="23%" class="tableDetailsInfoHeader"><div align="center">Edit</div></td>
					<td width="23%" class="tableDetailsInfoHeader"><div align="center">Delete</div></td>
				</tr>
			';
			$qry = "select * from item_category order by CID desc";
			$Select = mysql_query($qry);
			while($row = mysql_fetch_array($Select)){
				$ID = $row['CID'];
				echo '<script>
						$("#EditCategory'.$ID.'").button().click(function(event){
							$("#cID").val("'.$row["CID"].'");
							$("#cName").val("'.$row["cName"].'");
							$("#IconID").val("'.$row["pID"].'");
						});
						$("#DeleteCategory'.$ID.'").button().click(function(event){
							$.ajax({
								type:"POST",
								url:"ajax/itemAjax.php?iDelete='.$ID.'",
								beforeSend:function(){
									$("#loading").slideDown();
									$("#loading").html("Deleting");
								},
								success:function(msg){
									$("#loading").slideUp(2000);
									$("#deleteTr'.$ID.'").hide();
								}
							});
						});
					  </script>';
				echo '
					<tr id="deleteTr'.$ID.'" style="font-size:14px;border-bottom-width: thin;border-bottom-style: solid;border-bottom-color: #DBDBDB;">
						<td width="23%" class="tableDetailsInfoList"><div align="center">'.$row['CID'].'</div></td>
						<td width="23%" class="tableDetailsInfoList"><div align="center">'.$row['cName'].'</div></td>
						<td width="23%" class="tableDetailsInfoList"><div align="center">'.$row['pID'].'</div></td>
						<td width="23%" class="tableDetailsInfoList"><div align="center">
							<input type="submit" id="EditCategory'.$ID.'" style="margin:4px;" value="edit">
						</div></td>
						<td width="23%" class="tableDetailsInfoList"><div align="center">
							<input type="submit" id="DeleteCategory'.$ID.'" style="margin:4px;" value="del">
						</div></td>
					</tr>
				';
			}
			echo '</table>';
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
}
class galleryInfo extends Connection{
	function __construct(){
		parent::Connect();
	}
	function Insert($galleryName){
		try{
			$qry = "Insert into gallery_info values ('','$galleryName')";
			if(mysql_query($qry)){
				echo 'Inserted';
			}
			else{
				echo 'Error';
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function Delete($galleryID){
		try{
			$qry = "delete from gallery_info where galleryID = '$galleryID'";
			if(mysql_query($qry)){
				echo 'Deleted';
			}
			else{
				echo 'Error';
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function Update($galleryID,$galleryName){
		try{
			$qry = "update gallery_info set galleryName='$galleryName' where galleryID ='$galleryID'";
			if(mysql_query($qry)){
				echo 'Updated';
			}
			else{
				echo 'Error';
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function Select(){
		try{
			$flag =false;
			$qry = "select * from gallery_info";
			$Select = mysql_query($qry);
			while($row = mysql_fetch_array($Select)){
				$flag = true;
				$jsonData[] = array('galleryID'=>$row['galleryID'],'galleryName'=>$row['galleryName']);
			}
			if($flag){ 
				echo json_encode($jsonData);
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function MakeList(){
		try{
			echo '<script>$( "#galleryList" ).accordion({collapsible: true, autoHeight: false, heightStyle: "Container"});</script><div id="galleryList">';
			$qry="select * from gallery_info order by galleryName asc";
			$Select=mysql_query($qry);
			while($row=mysql_fetch_array($Select)){
				$ID = $row[0];
				//----------------------------------------------------------------------------------------->
				echo "<h3 class='gHeader".$ID."'>".$row[1]."</h3><div id='gContainer".$ID."'><div class='GalleryListContainer".$ID."' >
				<div class='GalleryListContainerHeader'>
					<script>
						function LoadSpecificPhotoFromGallery".$ID."(){
							$.ajax({
								url: 'ajax/galleryAjax.php?gIDSelected=".$ID."',
								type: 'POST',
								beforeSend:function(){
									$(\"#loadingGalleryList\").html(\"Loading..\");
									$(\"#loadPortal".$ID."\").html(\"<img src='icon/loading.gif' />\");
								},
								success:function(msg){
								    $(\"#loadingGalleryList\").html(\"Loaded\");
									$(\"#loadingGalleryList\").slideUp(200);	
									$(\"#loadPortal".$ID."\").html(msg);
								}       
						   });
						}
						$('#uploadForm".$ID."').on('submit',(function(e) {
							e.preventDefault();
							$.ajax({
								url: 'upload.php?gID=".$ID."',
								type: 'POST',
								data:  new FormData(this),
								contentType: false,
								cache: false,
								processData:false,
								beforeSend:function(){
									$(\"#loadingGalleryList\").slideDown();
									$(\"#loadingGalleryList\").html(\"uploading..\");
								},
								success:function(msg){
									if(msg=='Error'){
										$(\"#loadingGalleryList\").html('<div class=\"Error\">Sorry, only JPG, JPEG, PNG & GIF files are allowed.</div>');
										$(\"#loadingGalleryList\").slideUp(3000);										
									}
									else{
										$(\"#photo".$ID."\").val('');
										$(\"#loadingGalleryList\").html('Uploaded');
										LoadSpecificPhotoFromGallery".$ID."();
									}
								}       
						   });
						}));
						
						$('#gContainer".$ID."').css('height:auto');
						$('#NewImage".$ID."').button().click(function( event ) {});
						$('#EditGallery".$ID."').button().click(
							function( event ) {
								$('#gID').val('".$ID."');
								$('#galleryName').val('".$row[1]."');
							}
						);
						$('#DeleteGallery".$ID."').button().click(
							function( event ) {
								$.ajax({
									type:\"POST\",
									url:\"ajax/galleryAjax.php?gDelete=".$ID."\",
									success:function(msg){
										$(\"#gContainer".$ID."\").slideUp(500);
										$(\".gHeader".$ID."\").slideUp(500);
									}
								});
							}
						);
						$('#RefreshGallery".$ID."').button().click(
							function( event ) {
								LoadSpecificPhotoFromGallery".$ID."();
							}
						);
						
					</script>
					<form id='uploadForm".$ID."' action='upload.php' method='post' style='float:left;width:auto;'>
						<div id='uploadFormLayer'>
							<input name='photo' id='photo".$ID."' type='file' class='inputFile' /><input type='submit' id='NewImage".$ID."' style='margin:4px;'  value='Upload Image' class='btnSubmit' />
						</div>
					</form>
					<input type='submit' id='EditGallery".$ID."' style='margin:4px;' value='Edit'>
					<input type='submit' id='DeleteGallery".$ID."' style='margin:4px;' value='Delete'>
					<input type='submit' id='RefreshGallery".$ID."' style='margin:4px;' value='Refresh'>
					<div class=\"clear\"></div>
				</div>
				";
				echo "<div id='loadPortal".$ID."'>";
					$photo = new photo();
					$photo->SelectByGid($ID);
				echo "</div>";
				//List of Photo from selected gallery ID
				echo "<div class=\"clear\"></div></div></div>";
			}
			echo '</div>';
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function MakeListChooseIcon($BoxID,$DialogName){
		try{
			$sessionID= rand(0,999999);
			echo '<script>$( "#gListMaker'.$sessionID.'" ).accordion({collapsible: true,heightStyle: "Container"});</script>';
			echo '<div id="gListMaker'.$sessionID.'">';
			$qry="select * from gallery_info order by galleryName asc";
			$Select=mysql_query($qry);
			while($row=mysql_fetch_array($Select)){
				$ID = $row[0];
				echo "<h3>".$row[1]."</h3>";
				echo '<div>';
					$photo = new photo();
					$photo->SelectByGidChooseImg($ID,$BoxID,$DialogName);
					echo "<div class=\"clear\"></div>";
				echo '</div>';	
			}
			echo '</div>';
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function MakeListChooseIcon2($BoxID,$DialogName){
		try{
			$sessionID= rand(0,999999);
			$qry="select * from gallery_info order by galleryName asc";
			$Select=mysql_query($qry);
			echo '<div id="div'.$sessionID.'">';
			while($row=mysql_fetch_array($Select)){
				$ID = $row[0];
				echo '<script>
							$("#gListBtn'.$ID.$sessionID.'").button().click(function(event){
								$("#'.$BoxID.'").val("'.$ID.'");
								$("#'.$DialogName.'").dialog("close");
							});
						</script>
					';
				echo '<input type="submit" id="gListBtn'.$ID.$sessionID.'" style="margin:4px;height:50px;font-size:14px;" value="'.$row[1].'">';	
			}
			echo '</div>';
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
	function GetGalleryFromID($galleryID){
		try{
			$flag =false;
			$qry="select photo.image from gallery_info inner join photo on gallery_info.galleryID=photo.galleryID where gallery_info.galleryID='$galleryID'";
			$select = mysql_query($qry);
			while($row=mysql_fetch_array($select)){
				$flag=true;
				$jsonData[] = array('image'=>$row['image']);
			}
			if($flag){ 
				echo json_encode($jsonData);
			}
		}
		catch(Exception $ex){
			echo $ex;
		}
	}
}
class photo extends Connection{
		function __construct(){
			parent::Connect();
		}
		function Insert($image,$galleryID){
			try{
				$qry = "Insert into photo values ('','$image','$galleryID')";
				if(mysql_query($qry)){
					echo 'Uploaded';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}	
		function Delete($pID){
			try{
				$qry = "delete from photo where pID = '$pID'";
				if(mysql_query($qry)){
					echo 'Deleted';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Update($pID,$image,$galleryID){
			try{
				$qry = "update photo set image='$image',galleryID='$galleryID' where pID ='$pID'";
				if(mysql_query($qry)){
					echo 'Updated';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Select(){
			try{
				$flag =false;
				$qry = "select * from photo";
				$Select = mysql_query($qry);
				while($row = mysql_fetch_array($Select)){
					$flag = true;
					$jsonData[] = array('pID'=>$row['pID'],'image'=>$row['image'],'galleryID'=>$row['galleryID']);
				}
				if($flag){ 
					echo json_encode($jsonData);
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function SelectByGid($gID){
			try{
				$isLoad=false;
				$qry2="select * from photo where galleryID='".$gID."'";
				$Select2=mysql_query($qry2);
				while($row2=mysql_fetch_array($Select2)){
					$isLoad=true;
					$ID = $row2['pID'];
					echo '<script>
							$( "#gOpen'.$ID.'" ).button().click(function( event ) {
								var winW = $(window).width() -100;
								var winH = $(window).height() -100;
								$( "#dialog'.$ID.'" ).dialog({
									  modal: true,
									  buttons: {
										Ok: function() {
										  $( this ).dialog( "close" );
										}
									},
									height: winH,
									width: winW
								});
								$( "#dialog'.$ID.'").html("<div align=\'center\' style=\"width:100%;height:100%;\" ><img  src=\''.$row2['image'].'\' width=\'98%\' height=\'98%\'/img></div>");
							});
							$( "#gDel'.$ID.'" ).button().click(function( event ) {
								$.ajax({
										type:"POST",
										url:"ajax/galleryAjax.php?pIDSelected='.$ID.'",
										success:function(msg){
											$("#loadingGallery").html(msg);
											$("#loadingGallery").slideUp(1500);
											$("#IconContainer'.$ID.'").hide(300);
										}
									});
							});
						</script>';
					echo '<div id="IconContainer'.$ID.'" class="Icons" style="background:url('.$row2['image'].') center no-repeat; background-size:cover;">
								<div id="gOpen'.$ID.'" class="IconBtn" align="center">open</div>
								<div id="gDel'.$ID.'" class="IconBtn" align="center">del</div>
								<div id="dialog'.$ID.'" title="Image Viewer" style="width:500px;height:90%;"></div>
							</div>
							
					';
				}
				if(!$isLoad){
					//echo '<div class="Icons" style="background:url(icon/error.png) center no-repeat; background-size:cover;"></div>';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function SelectByGidChooseImg($gID,$BoxID,$DialogName){
			try{
				$qry2="select * from photo where galleryID='".$gID."'";
				$Select2=mysql_query($qry2);
				while($row2=mysql_fetch_array($Select2)){
					$ID = $row2['pID'];
					echo '<script>
							function SelectIcon'.$ID.'(){
								$("#'.$BoxID.'").val("'.$ID.'");
								$("#'.$DialogName.'").dialog("close");
							}
						</script>';
					echo '<div onClick="SelectIcon'.$ID.'();" class="Icons" style="background:url('.$row2['image'].') center no-repeat; background-size:cover;cursor:pointer;"></div>';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
}	
class order extends Connection{
		function __construct(){
			parent::Connect();
		}
		function Insert($tableNO,$foodID,$quantity,$note,$time,$state,$token,$price){
			try{
				$qry="select token from `order` where token='$token'";
				$select=mysql_fetch_array(mysql_query($qry));
				if($select){			
					$qry = "update `order` set note='$note' where token='$token'";
					if(mysql_query($qry)){
						echo '2';
					}
					else{
						echo '-2';
					}
				}
				else{		
					$time =date('y:m:d h:i:s');
					$qry = "Insert into `order` values ('','$tableNO','$foodID','$quantity','$note','$time','$state','$token','0',$price)";
					if(mysql_query($qry)){
						echo '1';
					}
					else{
						echo '-1';
					}
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Delete($SN){
			try{
				$qry = "delete from `order` where SN = '$SN'";
				if(mysql_query($qry)){
					echo 'Deleted';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Update($SN,$tableNO,$foodID,$quantity,$note,$time,$state){
			try{
				$qry = "update `order` set tableNO='$tableNO',foodID='$foodID',quantity='$quantity',note='$note',time='$time',state='$state' where SN ='$SN'";
				if(mysql_query($qry)){
					echo 'Updated';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Select(){
			try{
				$flag =false;
				$qry = "select * from `order` inner join food_item on `order`.`foodID`=food_item.foodID where `order`.`leave`='0'";
				$Select = mysql_query($qry);
				while($row = mysql_fetch_array($Select)){
					$flag = true;
					$jsonData[] = array('SN'=>$row['SN'],'tableNO'=>$row['tableNO'],'foodID'=>$row['foodID'],'quantity'=>$row['quantity'],'note'=>$row['note'],'time'=>$row['time'],'state'=>$row['state'],'token'=>$row['token'],'Name'=>$row['fName'],'Price'=>$row['Price']);
				}
				if($flag){ 
					echo json_encode($jsonData);
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function SelectPriceDetails($TOKEN){
			try{
				$flag =false;
				$qry = "select * from `order` inner join food_item on `order`.`foodID`=food_item.foodID where `order`.`leave`='0' and `order`.`token` like '$TOKEN%'";
				$Select = mysql_query($qry);
				while($row = mysql_fetch_array($Select)){
					$flag = true;
					$jsonData[] = array('SN'=>$row['SN'],'tableNO'=>$row['tableNO'],'foodID'=>$row['foodID'],'quantity'=>$row['quantity'],'note'=>$row['note'],'time'=>$row['time'],'state'=>$row['state'],'token'=>$row['token'],'Name'=>$row['fName'],'Price'=>$row['Price']);
				}
				if($flag){ 
					echo json_encode($jsonData);
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Leave($TOKEN){
			try{
				$qry = "update `order` set `leave`='1' where `token` like '$TOKEN%'";
				if(mysql_query($qry)){
					echo '1';
				}
				else{
					echo '0';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
	}
class restaurantTable extends Connection{
		function __construct(){
			parent::Connect();
		}
		function Insert($tableNO){
			try{
				$qry = "Insert into restaurant_table values ('','$tableNO')";
				if(mysql_query($qry)){
					echo 'Inserted';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Delete($SN){
			try{
				$qry = "delete from restaurant_table where SN = '$SN'";
				if(mysql_query($qry)){
					echo 'Deleted';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Update($SN,$tableNO){
			try{
				$qry = "update restaurant_table set tableNO='$tableNO' where SN ='$SN'";
				if(mysql_query($qry)){
					echo 'Updated';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Select(){
			try{
				$flag =false;
				$qry = "select * from restaurant_table";
				$Select = mysql_query($qry);
				while($row = mysql_fetch_array($Select)){
					$flag = true;
					$jsonData[] = array('SN'=>$row['SN'],'tableNO'=>$row['tableNO']);
				}
				if($flag){ 
					echo json_encode($jsonData);
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
	}
class restaurantDetail extends Connection{
		function __construct(){
			parent::Connect();
		}
		function Update($res_name,$res_info,$res_bestdish,$res_tutorial){
			try{
				$qry = "update restaurant_detail set res_name='$res_name',res_info='$res_info',res_bestdish='$res_bestdish',res_tutorial='$res_tutorial' where SN ='1'";
				if(mysql_query($qry)){
					echo 'Updated';
				}
				else{
					echo 'Error';
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		function Select(){
			try{
				$flag =false;
				$qry = "select * from restaurant_detail";
				$Select = mysql_query($qry);
				while($row = mysql_fetch_array($Select)){
					$flag = true;
					$jsonData[] = array('SN'=>$row['SN'],'res_name'=>$row['res_name'],'res_info'=>$row['res_info'],'res_bestdish'=>$row['res_bestdish'],'res_tutorial'=>$row['res_tutorial']);
				}
				if($flag){ 
					echo json_encode($jsonData);
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
		public $rName,$rInfo,$rBDish,$rTut;
		function SetData(){
			try{
				$qry = "select * from restaurant_detail";
				$Select = mysql_query($qry);
				while($row = mysql_fetch_array($Select)){
					$this->rName = $row['res_name'];
					$this->rInfo = $row['res_info'];
					$this->rBDish = $row['res_bestdish'];
					$this->rTut = $row['res_tutorial'];
				}
			}
			catch(Exception $ex){
				echo $ex;
			}
		}
}
class uploadImage{
	public $Path = "gallery/";
	public $gID;
	public function __construct($gID){
		$this->gID = $gID;
	}
	function upload(){
		try{
			if(is_array($_FILES)) {
				$target_file = basename($_FILES["photo"]["name"]);
				$imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);
				$imageFileType = strtolower($imageFileType);
				if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg" && $imageFileType != "gif" ) {
					echo "Error";
					return;
				}
				if(is_uploaded_file($_FILES['photo']['tmp_name'])) {
					$this->Path .=date('y_m_d_h_i_s_').rand(10000,99999);
					$sourcePath = $_FILES['photo']['tmp_name'];
					$targetPath = $this->Path.$_FILES['photo']['name'];
					$targetPath = strtolower($targetPath);
					if(move_uploaded_file($sourcePath,$targetPath)) {
						$photo= new photo();
						$photo->Insert($targetPath,$this->gID);
					}
				}
			}
		}
		catch(Exception $e){
			echo $e;
		}
	}
}
class TokenGenerator extends Connection{
	function __construct(){
		parent::Connect();
	}
	function GenerateToken(){
		$VAL = 1;
		$qry="select MAX(SN) from `order`";
		$SN= mysql_fetch_array(mysql_query($qry));
		if($SN){
			$VAL = $SN[0] + 1;
		}
		$dateVal = date('Ymdhis');
		$randVal = rand(1111,9999999);
		$TOKEN = "T-" . $dateVal."-".$randVal."-".$VAL;
		echo $TOKEN;
	}
	function CheckIfPaidOrNot($TOKEN){
		$qry="select SN from `order` where `token` like '$TOKEN%' and `leave`='0'";
		$select=mysql_fetch_array(mysql_query($qry));
		if($select){
			echo '1';
		}
		else{
			echo '0';
		}
	}
}
?>