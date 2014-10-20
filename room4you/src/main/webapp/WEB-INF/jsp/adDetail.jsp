<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1><c:out value="${ad.title}" /></h1>




<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>Ad Description</th>
			<th>Ad Street</th>
			<th>ZIP</th>
			<th>City</th>
			
		</tr>
	</thead>
	<tbody>
			<tr>
				<td>
					${ad.id}
				</td>
				<td>
					${ad.description}
				</td>
				<td>
					${ad.street}
				</td>
				<td>
					${ad.zip}
				</td>
				<td>
					${ad.city}
				</td>
			</tr>
	</tbody>
</table>
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBEGI1Wn8tNYbkCxFF0lupb81SQgLhtHEU&sensor=false">
</script>



        <h1>Your new room location</h1>   

	
			<script>
			  var geocoder;
			  var map;
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
			
			</script>
			
			
			
			</head>
			<body>
			<div id="googleMap" style="width:500px;height:380px;"></div>
			</body>
		</html>

