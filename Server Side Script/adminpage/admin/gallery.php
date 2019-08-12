<link href="stylesheet/Gallery.css" rel="stylesheet" type="text/css" />
<script>
$(function() {
	function LoadGalleryListData(){
		//for auto loading galleryList data
		$.ajax({
				type:"POST",
				url:"ajax/galleryAjax.php?gList",
				beforeSend:function(){
					$("#loadingGalleryList").slideDown();
					$("#loadingGalleryList").html("Loading List");
				},
				success:function(msg){
					$("#gList").html(msg);
					$("#loadingGalleryList").slideUp(1500);
				}
			});
	}
	$( "#galleryAdd" ).button().click(function( event ) {
			var gName = $("#galleryName").val();
			var gID =$("#gID").val();
			if(gID ==""){
				$.ajax({
					type:"POST",
					url:"ajax/galleryAjax.php?gName=" + gName,
					beforeSend:function(){
						$("#loadingGallery").slideDown();
						$("#loadingGallery").html("Saving");
					},
					success:function(msg){
						$("#loadingGallery").html(msg);
						$("#loadingGallery").slideUp(1500);
						Clear();
						LoadGalleryListData();//Load GalleryListData
					}
				});
			}
			else{
				$.ajax({
					type:"POST",
					url:"ajax/galleryAjax.php?gID=" + gID + "&gName=" + gName,
					beforeSend:function(){
						$("#loadingGallery").slideDown();
						$("#loadingGallery").html("Updating");
					},
					success:function(msg){
						$("#loadingGallery").html(msg);
						$("#loadingGallery").slideUp(1500);
						Clear();
						LoadGalleryListData();//Load GalleryListData
					}
				});
			}
	});
	LoadGalleryListData();//Auto Load GalleryListData
	$( "#galleryClear" ).button().click(function( event ) {Clear();});
	function Clear(){
		$('#gID').val('');
		$('#galleryName').val('');
	}
  });
</script>
<div class="Gallery">
<input id="gID" type="hidden">
  <table width="100%">
    <tr>
      <td width="24%"><div align="right">Gallery Name: </div></td>
      <td width="56%">
	  	<div align="left">
        	<input id="galleryName" class="galleryName ui-widget ui-state-default ui-corner-all" type="text" name="galleryName"/>
      	</div>
	  </td>
	  <td width="20%">
		<div align="left">
			<input type="submit" id="galleryAdd" style="margin:4px;" value="Save">
			<input type="submit" id="galleryClear" style="margin:4px;" value="Clear">
		</div>
	  </td>
    </tr>
	<tr>
      <td colspan="3" width="100%"><div style="font-size:14px;" id="loadingGallery" align="center"></div></td>
    </tr>
  </table>
</div>
<div class="GalleryList">
   <div style="font-size:14px;" id="loadingGalleryList" align="center"></div>
   <div id="gList">
	  <!--gallery List data-->
  </div>
</div>
</div>