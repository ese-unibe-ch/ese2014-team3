<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.netbeans.saas.*, org.netbeans.saas.google.*" %>

<%@ include file="../layout/taglib.jsp"%>

<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBEGI1Wn8tNYbkCxFF0lupb81SQgLhtHEU&sensor=false">
</script>



        <h1>Your new room location</h1>   

	
			<script>
			var myCenter=new google.maps.LatLng(51.508742,-0.120850);
			
			function initialize()
			{
			var mapProp = {
			  center:myCenter,
			  zoom:5,
			  mapTypeId:google.maps.MapTypeId.ROADMAP
			  };
			
			var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
			
			var marker=new google.maps.Marker({
			  position:myCenter,
			  });
			
			marker.setMap(map);
			}
			
			google.maps.event.addDomListener(window, 'load', initialize);
			</script>
			</head>
			
			<body>
			<div id="googleMap" style="width:500px;height:380px;"></div>
			</body>
		</html>