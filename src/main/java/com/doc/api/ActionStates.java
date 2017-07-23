package com.doc.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@XmlRootElement
@Data
public class ActionStates {
	@Id
	protected String state;
	protected String remarks;
}
