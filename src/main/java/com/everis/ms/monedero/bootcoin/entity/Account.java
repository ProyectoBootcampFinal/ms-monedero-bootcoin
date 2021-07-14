package com.everis.ms.monedero.bootcoin.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class Account {

	private String numberAccount;
	private String numberCard;
	private Integer balance;
	private List<Activity> activities;
}
