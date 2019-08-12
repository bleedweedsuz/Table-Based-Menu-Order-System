<link href="stylesheet/User.css" rel="stylesheet" type="text/css" />
<script>
$(function() {
	function UserLoadLists(){
		$.ajax({
			type:"POST",
			url:"ajax/userAjax.php?uList=uList",
			beforeSend:function(){
				$("#loadingUser").slideDown();
				$("#loadingUser").html("Loading");
			},
			success:function(msg){
				$("#loadingUser").slideUp(600);
				$("#userLoadContainer").html(msg);
			}
		});
	}
	$( "#UserSave" ).button().click(function( event ) {
		var uName = $("#uName").val();
		var uPassword = $("#uPassword").val();
		var uFName = $("#uFName").val();
		var uAddress = $("#uAddress").val();
		var uPhoneno = $("#uPhoneno").val();
		var uType = $("#uType").val();
		var uID = $("#uID").val();
		if(uID == ""){
			$.ajax({
				type:"POST",
				url:"ajax/userAjax.php?uName=" + uName + "&uPassword="+ uPassword + "&uType=" + uType +	"&uFName=" + uFName + "&uAddress=" + uAddress+"&uPhone=" + uPhoneno,
				beforeSend:function(){
					$("#loadingUser").slideDown();
					$("#loadingUser").html("Saving..");
				},
				success:function(msg){
					UserLoadLists();
					$("#loadingUser").html("Inserted");
					$("#loadingUser").slideUp(600);
					Clear();
				}
			});
		}
		else{
			$.ajax({
				type:"POST",
				url:"ajax/userAjax.php?uName=" + uName + "&uPassword="+ uPassword + "&uType=" + uType +	"&uFName=" + uFName + "&uAddress=" + uAddress+"&uPhone=" + uPhoneno + "&uID=" +uID,
				beforeSend:function(){
					$("#loadingUser").slideDown();
					$("#loadingUser").html("Updating..");
				},
				success:function(msg){
					UserLoadLists();
					$("#loadingUser").html("Updated");
					$("#loadingUser").slideUp(600);
					Clear();
				}
			});
		}
	});
	$("#UserClear").button().click(function(){ Clear();});
	function Clear(){
		$("#uID").val("");$("#uName").val("");$("#uPassword").val("");$("#uFName").val("");$("#uAddress").val("");$("#uPhoneno").val("");
	}
	UserLoadLists();
  });
</script>
<div class="UserEntry">
  <table width="100%">
    <tr>
      <td width="13%"><div align="right">User Name</div></td>
      <td width="36%"><div align="left"><input id="uName" type="text"  class="inputBack ui-widget ui-state-default ui-corner-all" name="UserName"/></div></td>
      <td width="10%"><div align="right">Password</div></td>
      <td width="41%"><div align="left"><input id="uPassword" type="password" class="inputBack ui-widget ui-state-default ui-corner-all" name="Password"/></div></td>
    </tr>
	<tr>
      <td width="13%"><div align="right">Full Name</div></td>
	  <td width="36%"><div align="left"><input id="uFName" type="text" class="inputBack ui-widget ui-state-default ui-corner-all" name="FullName"/></div></td>
      <td width="10%"><div align="right">Address</div></td>
      <td width="41%"><div align="left"><input id="uAddress" type="text" class="inputBack ui-widget ui-state-default ui-corner-all" name="Address"/></div></td>
    </tr>
	<tr>
      <td width="13%"><div align="right">Phone No.</div></td>
      <td width="36%"><div align="left">
        <input id="uPhoneno" type="text" class="inputBack ui-widget ui-state-default ui-corner-all" name="PhoneNo"/>
		</div>	  </td>
    <td width="13%"><div align="right">User Type</div></td>
        <td width="36%"><div align="left">
			<select id="uType" class="inputBack ui-widget ui-state-default ui-corner-all">
				<option value="Admin">Admin</option>
				<option value="Other">Other</option>
			</select>
		</div>	  
	</td>
    </tr>
	<tr>
	  <td><input id="uID" type="hidden"/></td>
	  <td>
	  	<div align="left">
			<input type="submit" id="UserSave" style="margin:4px;" value="Save Now">
			<input type="submit" id="UserClear" style="margin:4px;" value="Clear">
		</div>
	  </td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
    </tr>
	<tr>
      <td colspan="4" width="100%"><div style="font-size:14px;" id="loadingUser" align="center"></div></td>
    </tr>
  </table>
</div>
<div id="userLoadContainer" class="UserDetailsInfo">

</div>