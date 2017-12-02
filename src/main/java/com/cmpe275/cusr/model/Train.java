package com.cmpe275.cusr.model;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyTemporal;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import javax.persistence.EnumType;


@Entity
@Table(name="TRAIN")
public class Train {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TRAIN_ID")
	private long trainId;
	
	@Column(name="BOUND", nullable=false)
	private String bound;
	
	@Column(name="DEPARTURE_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date departureTime;
	
	@Column(name="TYPE", nullable=false)
	private String type;
	
	@Column(name="CAPACITY")
	private int capacity;
	
	@ElementCollection
	@CollectionTable(name="TRAIN_SCHEDULE")
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name="STATION")
	@Column(name="DEPART_TIME_AT_STATION")
	@Temporal(TemporalType.TIME)
	private Map<Station, Date> trainTimeTable;
	
	@ElementCollection
	@CollectionTable(name="TRAIN_STATUS")
	@MapKeyColumn(name="BOOK_DATE", nullable=false)
	@MapKeyTemporal(TemporalType.DATE)
	@Column(name="USED_SEATS")
	private Map<Date, Integer> trainStatus;
	
	public Train() {
		super();
	}
	
	public Train(String bound, Date departureTime, String type,  Map<Station, Date> trainTimeTable) {
		super();
		this.bound = bound;
		this.departureTime = departureTime;
		this.type = type;
		this.capacity = 1000;
		this.trainTimeTable = trainTimeTable;
		this.trainStatus = new HashMap<Date, Integer>();
	}

	public long getTrainId() {
		return trainId;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Map<Station, Date> getTrainTimeTable() {
		return trainTimeTable;
	}

	public void setTrainTimeTable(Map<Station, Date> trainTimeTable) {
		this.trainTimeTable = trainTimeTable;
	}

	public Map<Date, Integer> getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(Map<Date, Integer> trainStatus) {
		this.trainStatus = trainStatus;
	}
	
	//for testing purpose
		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			result.append("TrainBound: ");
			result.append(getBound());
			result.append("\nDeparture Time: ");
			result.append(getDepartureTime());
			result.append("\nExpress or Regular: ");
			result.append(getType());
			result.append("\nCapacity: ");
			result.append(getCapacity());
			for (Station s: trainTimeTable.keySet()) {
				result.append("\nstation: " + s + " depart at: " + trainTimeTable.get(s));
			}
			return result.toString();
		}

}

