package com.clientData;


public class Message implements Comparable<Message>{
//	static SimpleDateFormat FORMATTER = 
//		new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	private String info;
	private String latitude;
	private String longitude;
	private String timeframe;

	public String getInfo() {
	    
		return info;
	}

	public void setInfo(String info) {
		this.info = info.trim();
	}
	// getters and setters omitted for brevity 
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude.trim();
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude.trim();
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe.trim();
	}
	
	public Message copy(){
		Message copy = new Message();
		copy.info = info;
		copy.latitude = latitude;
		copy.longitude = longitude;
		copy.timeframe = timeframe;
		return copy;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Info: ");
		sb.append(info);
		sb.append('\n');
		sb.append("Timeframe: ");
		sb.append(timeframe);
		sb.append('\n');
		sb.append("Latitude: ");
		sb.append(latitude);
		sb.append('\n');
		sb.append("Longitude: ");
		sb.append(longitude);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timeframe == null) ? 0 : timeframe.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (timeframe == null) {
			if (other.timeframe != null)
				return false;
		} else if (!timeframe.equals(other.timeframe))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}

	public int compareTo(Message another) {
		if (another == null) return 1;
		// sort descending, most recent first
		return another.timeframe.compareTo(timeframe);
	}
}
