package vo;

public class CalendarVO {
	private int date_idx;
	private String date_content, id, team_name, start_day, end_day;
	
	public String getStart_day() {
		return start_day;
	}
	public void setStart_day(String start_day) {
		this.start_day = start_day;
	}
	public String getEnd_day() {
		return end_day;
	}
	public void setEnd_day(String end_day) {
		this.end_day = end_day;
	}
	public int getDate_idx() {
		return date_idx;
	}
	public void setDate_idx(int date_idx) {
		this.date_idx = date_idx;
	}
	public String getDate_content() {
		return date_content;
	}
	public void setDate_content(String date_content) {
		this.date_content = date_content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
}
