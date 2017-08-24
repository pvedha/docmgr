package com.doc.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionStates {
	@Id
	protected String state;
	protected String remarks;
}
