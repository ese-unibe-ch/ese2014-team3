package pojo;

public class SearchAdsForm {

		private String searchTextCity="";
		private String searchTextZip="";
		private boolean searchSharedApartment = true;		
		private int searchTextMaxPrice=Integer.MAX_VALUE;
		private int searchTextMinPrice=0;	
		private int searchTextNbrRoomMatesMax=Integer.MAX_VALUE;
		private int searchTextNbrRoomMatesMin=0;		
		private float searchTextNbrRoomsMax=Float.MAX_VALUE;
		private float searchTextNbrRoomsMin=0;
		
		


		public String getSearchTextCity() {
			return searchTextCity;
		}




		public void setSearchTextCity(String searchTextCity) {
			this.searchTextCity = searchTextCity;
		}




		public String getSearchTextZip() {
			return searchTextZip;
		}




		public void setSearchTextZip(String searchTextZip) {
			this.searchTextZip = searchTextZip;
		}




		public boolean isSearchSharedApartment() {
			return searchSharedApartment;
		}




		public void setSearchSharedApartment(boolean searchSharedApartment) {
			this.searchSharedApartment = searchSharedApartment;
		}




		public int getSearchTextMaxPrice() {
			return searchTextMaxPrice;
		}




		public void setSearchTextMaxPrice(int searchTextMaxPrice) {
			this.searchTextMaxPrice = searchTextMaxPrice;
		}




		public int getSearchTextMinPrice() {
			return searchTextMinPrice;
		}




		public void setSearchTextMinPrice(int searchTextMinPrice) {
			this.searchTextMinPrice = searchTextMinPrice;
		}




		public int getSearchTextNbrRoomMatesMax() {
			return searchTextNbrRoomMatesMax;
		}




		public void setSearchTextNbrRoomMatesMax(int searchTextNbrRoomMatesMax) {
			this.searchTextNbrRoomMatesMax = searchTextNbrRoomMatesMax;
		}




		public int getSearchTextNbrRoomMatesMin() {
			return searchTextNbrRoomMatesMin;
		}




		public void setSearchTextNbrRoomMatesMin(int searchTextNbrRoomMatesMin) {
			this.searchTextNbrRoomMatesMin = searchTextNbrRoomMatesMin;
		}




		public float getSearchTextNbrRoomsMax() {
			return searchTextNbrRoomsMax;
		}




		public void setSearchTextNbrRoomsMax(float searchTextNbrRoomsMax) {
			this.searchTextNbrRoomsMax = searchTextNbrRoomsMax;
		}




		public float getSearchTextNbrRoomsMin() {
			return searchTextNbrRoomsMin;
		}




		public void setSearchTextNbrRoomsMin(float searchTextNbrRoomsMin) {
			this.searchTextNbrRoomsMin = searchTextNbrRoomsMin;
		}
		

		
		@Override
		public String toString() {
			return "SearchAdsForm [searchTextCity=" + searchTextCity
					+ ", searchTextZip=" + searchTextZip
					+ ", searchSharedApartment=" + searchSharedApartment
					+ ", searchTextMaxPrice=" + searchTextMaxPrice
					+ ", searchTextMinPrice=" + searchTextMinPrice
					+ ", searchTextNbrRoomMatesMax="
					+ searchTextNbrRoomMatesMax
					+ ", searchTextNbrRoomMatesMin="
					+ searchTextNbrRoomMatesMin + ", searchTextNbrRoomsMax="
					+ searchTextNbrRoomsMax + ", searchTextNbrRoomsMin="
					+ searchTextNbrRoomsMin + "]";
		}



}
