<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>



<h1><c:out value="${ad.title}" /></h1>


<div class="container-fluid">

<table class="table table-bordered table-hover table-striped">
	<tbody>
			<tr>
				<td>
					<p>
						<strong>From</strong>
					</p>
				</td>
				<td>
					<p>${ad.availableFrom}</p>
				</td>	
			</tr>
			<tr>
				<td>
					<p>
						<strong>Rent</strong>
					</p>
				</td>
				<td>
					<p>${ad.rentPerMonth}</p>
				</td>	
			</tr>
			
			<tr>
				<td>
					<p><strong>Address</strong></p>
				</td>
				<td>
					<address >
					<a href="" data-toggle="modal" data-target="#locationModal">${ad.street}<br>
					${ad.zip} ${ad.city}<br>
					</a>								
					</address>					
				</td>		
			</tr>
					<tr>
				<td>
					<p><strong>Description</strong></p>
				</td>
				<td>
					<p>${ad.description}								
					</p>		
				
				</td>			
			</tr>
			</tr>
			<tr>
				<td>
					<p><strong>We are looking for</strong></p>
				</td>
				<td>
					<p>TODO...</p>	
				
				</td>		
			</tr>
			<tr>
				<td>
					<p><strong>We are</strong></p>
				</td>
				<td>
					<p>TODO...</p>					
				</td>		
			</tr>
			<tr>
				<td>
					<p><strong>Contact</strong></p>
				</td>
				<td>
					<a href="<spring:url value="/message/${ad.id}.html"/>" class="btn btn-default btn-md" role="button">
						<span class="glyphicon glyphicon-envelope"></span>
					</a>
				</td>			
			</tr>
	</tbody>
</table>

<img src="data:image/jpeg;base64,${imageForJSP}" alt="image1" class="img-thumbnail">

</div>


<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBEGI1Wn8tNYbkCxFF0lupb81SQgLhtHEU&sensor=false">
</script>


<script>
  var geocoder;
  var map;
  var mapCenter;
  function initialize(adr) {
	var street = "${ad.street}";
	var zip=${ad.zip};
	var city="${ad.city}";
	var address=street+", "+zip+""+city;
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(46.952106, 7.444181);
    var mapOptions = {
      zoom: 8,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById("googleMap"), mapOptions);
   codeAddress(address);
  }

  function codeAddress(adr) {
    var address = adr;
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
    	  mapCenter = results[0].geometry.location;
        map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }		  
 
 	google.maps.event.addDomListener(window, 'load', initialize);
 	
 	//Resize google map after showing in modal
 	$(document).ready(function(){
  	$("#locationModal").on('shown.bs.modal', function () {
  		 google.maps.event.trigger(map, "resize");
  		 map.setCenter(mapCenter);
  	});
 	});
 	


</script>


<!-- Modal Show Google Maps in Modal-->
<div class="modal fade" id="locationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Location</h4>
      </div>
      <div class="modal-body">
      <div class="container">
                    <div class="row">                        
          				<div id="googleMap"  style="width:450px;height:380px;"></div>
                    </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
			

