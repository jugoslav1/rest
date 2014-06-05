package rs.vicko.restserver;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ToDo {
	private String summary;
	private String description;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ToDo [summary=" + summary + ", description=" + description
				+ "]";
	}
}
